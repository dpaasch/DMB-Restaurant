package db.accessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dawn Bykowski
 */
public class DB_MySQL implements DBAccessor {

    // Variable declarations //
    private Connection dbConnection;
    private String url, userName, password;
    private final String IAE_ERR = "Error: URL not found or empty.",
            CNF_ERR = "Error: Failed to load the JDBC driver.",
            SQL_ERR = "Error: Unable to connect to the database.";

    /**
     * Author: Jim Lombardo, WCTC Lead Java Instructor A utility method to
     * explicitly open a db connection. Note that the connection will remain
     * open until explicitly closed by member methods.
     *
     * @param driverClassName - the fully qualified name of the driver class.
     * @param url - the connection URL, driver dependent.
     * @param username for database access permission, if required. Null and ""
     * values are allowed.
     * @param password for database access permission, if required. Null and ""
     * values are allowed
     * @throws IllegalArgumentException if url is null or zero length
     * @throws ClassNotFoundException if driver class cannot be found
     * @throws SQLException if database access error occuresultSet. For example,
     * an invalid url could cause this; or, a database that is no longer
     * available due to network or access permission problems.
     */
    @Override
    public void openDBConnection(String driverClassName, String url, String username, String password)
            throws IllegalArgumentException, ClassNotFoundException, SQLException {
        String msg = "Error: url is null or zero length!";
        if (url == null || url.length() == 0) {
            throw new IllegalArgumentException(msg);
        }
        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url, username, password);
    }

    /**
     * A utility method to explicitly close a db connection. Pooled connections
     * should never be closed, but rather returned to the pool.
     * <p>
     * As an alternative to using this method, other member methods is this
     * class offer a boolean switch to close the connection automatically.
     *
     * @throws SQLException if connection cannot be closed due to a db access
     * error.
     */
    @Override
    public void closeDBConnection() throws SQLException {
        dbConnection.close();
    }

    /**
     * Author: Jim Lombardo, WCTC Lead Java Instructor Used to perform a
     * generalized SQL query to find all records.
     *
     * @param sqlString - the sql statement (check your database for
     * compatibility)
     * @param closeConnection - true if connection should be closed
     * automatically; if false, connection must be explicitly closed using the
     * closeConnection method.
     * @return The found records as a List of Maps. Each Map is one record, with
     * column name as the key, and the column value as the value. The List will
     * be null if the query fails to return any records.
     * @throws SQLException if database access error or illegal sql
     * @throws Exception for all other problems
     */
    @Override
    public List findAllRecords(String sqlStmnt, boolean closeConnection)
            throws SQLException, Exception {
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;
        final List list = new ArrayList();
        Map record = null;

        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sqlStmnt);
            metaData = resultSet.getMetaData();
            final int fields = metaData.getColumnCount();

            while (resultSet.next()) {
                record = new HashMap();
                for (int i = 1; i <= fields; i++) {
                    try {
                        record.put(metaData.getColumnName(i), resultSet.getObject(i));
                    } catch (NullPointerException npe) {
                        // no need to do anything... if it fails, just ignore it and continue
                    }
                }
                list.add(record);
            }

        } catch (SQLException sql) {
            throw new SQLException(SQL_ERR);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        } finally {
            try {
                statement.close();
                dbConnection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    /**
     * Author: Jim Lombardo, WCTC Lead Java Instructor Retrieves a record based
     * on the primary key of a table.
     *
     * @param table - a <code>String</code> representing the table name.
     * @param pkField - a <code>String</code> representing the primary key field
     * name used as the search criteria.
     * @param keyValue - an <code>Object</code> representing the primary key
     * field value used for the search criteria. Typically this is a String or
     * numeric typewrapper class.
     * @param closeConnection - true if connection should be closed
     * automatically; if false, connection must be explicitly closed using the
     * closeConnection method.
     * @return a <code>Map</code> if the record is found; <code>null</code>
     * otherwise. The key is the columnName, the value is the field data.
     * @throws SQLException if database access error or illegal sql
     * @throws Exception for all other problems
     */
    @Override
    public Map findRecordById(String table, String pkField, Object keyValue,
            boolean closeConnection) throws SQLException, Exception {
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;
        final Map record = new HashMap();

        try {
            statement = dbConnection.createStatement();
            String sql2;

            if (keyValue instanceof String) {
                sql2 = "= '" + keyValue + "'";
            } else {
                sql2 = "=" + keyValue;
            }

            final String sql = "SELECT * FROM " + table + " WHERE " + pkField + sql2;
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();
            metaData.getColumnCount();
            final int fields = metaData.getColumnCount();
            // Retrieve the raw data from the ResultSet and copy the values into a Map
            // with the keys being the column names of the table.
            if (resultSet.next()) {
                for (int i = 1; i <= fields; i++) {
                    record.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
            }

        } catch (SQLException sql) {
            throw new SQLException(SQL_ERR);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        } finally {
            try {
                statement.close();
                if (closeConnection) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        return record;
    }

    /**
     * Author: Jim Lombardo, WCTC Lead Java Instructor 
     * Inserts a record into a table based on a <code>List</code> of column 
     * descriptors and a one-to-one mapping of an associated <code>List</code> 
     * of column values.
     *
     * @param tableName - a <code>String</code> representing the table name
     * @param colDescriptors - <code>List</code> containing the column
     * descriptors
     * @param colValues - <code>List</code> containing the column values. The
     * order of these values must match the order of the column descriptors.
     * @param closeConnection - true if connection should be closed
     * automatically; if false, connection must be explicitly closed using the
     * closeConnection method.
     * @return <code>true</code> if successful; <code>false</code> otherwise
     * @throws SQLException if database access error or illegal sql
     * @throws Exception for all other problems
     */
    @Override
    public boolean insertRecord(String tableName, List colDescriptors, List colValues, boolean closeConnection)
            throws SQLException, Exception {
        PreparedStatement preparedStatement = null;
        int recordsUpdated = 0;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
            preparedStatement = buildInsertStatement(dbConnection, tableName, colDescriptors);

            final Iterator i = colValues.iterator();
            int index = 1;
            while (i.hasNext()) {
                final Object obj = i.next();
                if (obj instanceof String) {
                    preparedStatement.setString(index++, (String) obj);
                } else if (obj instanceof Integer) {
                    preparedStatement.setInt(index++, ((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    preparedStatement.setLong(index++, ((Long) obj).longValue());
                } else if (obj instanceof Double) {
                    preparedStatement.setDouble(index++, ((Double) obj).doubleValue());
                } else if (obj instanceof java.sql.Date) {
                    preparedStatement.setDate(index++, (java.sql.Date) obj);
                } else if (obj instanceof Boolean) {
                    preparedStatement.setBoolean(index++, ((Boolean) obj).booleanValue());
                } else {
                    if (obj != null) {
                        preparedStatement.setObject(index++, obj);
                    }
                }
            }
            recordsUpdated = preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                preparedStatement.close();
                if (closeConnection) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        if (recordsUpdated == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Author: Jim Lombardo, WCTC Lead Java Instructor Updates one or more
     * records in a table based on a single, matching field value.
     *
     * @param tableName - a <code>String</code> representing the table name
     * @param colDescriptors - a <code>List</code> containing the column
     * descriptors for the fields that can be updated.
     * @param colValues - a <code>List</code> containing the values for the
     * fields that can be updated.
     * @param whereField - a <code>String</code> representing the field name for
     * the search criteria.
     * @param whereValue - an <code>Object</code> containing the value for the
     * search criteria.
     * @param closeConnection - true if connection should be closed
     * automatically; if false, connection must be explicitly closed using the
     * closeConnection method.
     * @return an <code>int</code> containing the number of records updated.
     * @throws SQLException if database access error or illegal sql
     * @throws Exception for all other problems
     */
    @Override
    public int updateRecords(String tableName, List colDescriptors, List colValues,
            String whereField, Object whereValue, boolean closeConnection)
            throws SQLException, Exception {
        PreparedStatement preparedStatement = null;
        int recordsUpdated = 0;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
            preparedStatement = buildUpdateStatement(dbConnection, tableName, colDescriptors, whereField);

            final Iterator i = colValues.iterator();
            int index = 1;
            boolean doWhereValueFlag = false;
            Object obj = null;

            while (i.hasNext() || doWhereValueFlag) {
                if (!doWhereValueFlag) {
                    obj = i.next();
                }

                if (obj instanceof String) {
                    preparedStatement.setString(index++, (String) obj);
                } else if (obj instanceof Integer) {
                    preparedStatement.setInt(index++, ((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    preparedStatement.setLong(index++, ((Long) obj).longValue());
                } else if (obj instanceof Double) {
                    preparedStatement.setDouble(index++, ((Double) obj).doubleValue());
                } else if (obj instanceof java.sql.Timestamp) {
                    preparedStatement.setTimestamp(index++, (java.sql.Timestamp) obj);
                } else if (obj instanceof java.sql.Date) {
                    preparedStatement.setDate(index++, (java.sql.Date) obj);
                } else if (obj instanceof Boolean) {
                    preparedStatement.setBoolean(index++, ((Boolean) obj).booleanValue());
                } else {
                    if (obj != null) {
                        preparedStatement.setObject(index++, obj);
                    }
                }

                if (doWhereValueFlag) {
                    break;
                } // only allow loop to continue one time
                if (!i.hasNext()) {          // continue loop for whereValue
                    doWhereValueFlag = true;
                    obj = whereValue;
                }
            }

            recordsUpdated = preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                preparedStatement.close();
                if (closeConnection) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        return recordsUpdated;
    }
    
    /**
     * Author: Jim Lombardo, WCTC Lead Java Instructor
     * Deletes one or more records in a table based on a single, matching field value.
     *
     * @param tableName - a <code>String</code> representing the table name
     * @param whereField - a <code>String</code> representing the field name for
     * the search criteria.
     * @param whereValue - an <code>Object</code> containing the value for the
     * search criteria.
     * @param closeConnection - true if connection should be closed
     * automatically; if false, connection must be explicitly closed using the
     * closeConnection method.
     * @return an <code>int</code> containing the number of records updated.
     * @throws SQLException if database access error or illegal sql
     * @throws Exception for all other problems
     */
    @Override
    public int deleteRecords(String table, String whereField, Object whereValue, boolean closeConnection) 
            throws SQLException, Exception {
        PreparedStatement preparedStatement = null;
        int recordsDeleted = 0;

        try {
            preparedStatement = buildDeleteStatement(dbConnection, table, whereField);
            // delete all records if whereField is null
            if (whereField != null) {
                if (whereValue instanceof String) {
                    preparedStatement.setString(1, (String) whereValue);
                } else if (whereValue instanceof Integer) {
                    preparedStatement.setInt(1, ((Integer) whereValue).intValue());
                } else if (whereValue instanceof Long) {
                    preparedStatement.setLong(1, ((Long) whereValue).longValue());
                } else if (whereValue instanceof Double) {
                    preparedStatement.setDouble(1, ((Double) whereValue).doubleValue());
                } else if (whereValue instanceof java.sql.Date) {
                    preparedStatement.setDate(1, (java.sql.Date) whereValue);
                } else if (whereValue instanceof Boolean) {
                    preparedStatement.setBoolean(1, ((Boolean) whereValue).booleanValue());
                } else {
                    if (whereValue != null) {
                        preparedStatement.setObject(1, whereValue);
                    }
                }
            }

            recordsDeleted = preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                preparedStatement.close();
                if (closeConnection) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        return recordsDeleted;
    }

    /*
     * Author: Jim Lombardo, WCTC Lead Java Instructor
     * Builds a java.sql.PreparedStatement for an sql insert
     * @param conn - a valid connection
     * @param tableName - a <code>String</code> representing the table name
     * @param colDescriptors - a <code>List</code> containing the column descriptors for
     * the fields that can be inserted.
     * @return java.sql.PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement buildInsertStatement(Connection dbConnection, String tableName, List colDescriptors)
            throws SQLException {
        StringBuffer sql = new StringBuffer("INSERT INTO ");
        (sql.append(tableName)).append(" (");
        final Iterator i = colDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(", ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ") VALUES (");
        for (int j = 0; j < colDescriptors.size(); j++) {
            sql.append("?, ");
        }
        final String finalSQL = (sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ")";
        //System.out.println(finalSQL);
        return dbConnection.prepareStatement(finalSQL);
    }
    
    /* Author: Jim Lombardo, WCTC Lead Java Instructor Updates one or more
     * Builds a java.sql.PreparedStatement for an sql update using only one where clause test
     * @param conn - a JDBC <code>Connection</code> object
     * @param tableName - a <code>String</code> representing the table name
     * @param colDescriptors - a <code>List</code> containing the column descriptors for
     * the fields that can be updated.
     * @param whereField - a <code>String</code> representing the field name for the
     * search criteria.
     * @return java.sql.PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement buildUpdateStatement(Connection dbConnection, String tableName, List colDescriptors, String whereField)
            throws SQLException {
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i = colDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        final String finalSQL = sql.toString();
        return dbConnection.prepareStatement(finalSQL);
    }
    
    /*
     * Author: Jim Lombardo, WCTC Lead Java Instructor
     * Builds a java.sql.PreparedStatement for an sql delete using only one where clause test
     * @param dbConnection - a JDBC <code>Connection</code> object
     * @param table - a <code>String</code> representing the table name
     * @param whereField - a <code>String</code> representing the field name for the
     * search criteria.
     * @return java.sql.PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement buildDeleteStatement(Connection dbConnection, String table, String whereField) throws SQLException {
        final StringBuffer sqlDeleteStatement = new StringBuffer("DELETE FROM ");
        sqlDeleteStatement.append(table);

        if (whereField != null) {
            sqlDeleteStatement.append(" WHERE ");
            (sqlDeleteStatement.append(whereField)).append(" = ? ");
        }
        final String finalDeleteSQL = sqlDeleteStatement.toString();
        return dbConnection.prepareStatement(finalDeleteSQL);
    }

    // for testing
    public static void main(String[] args) throws IllegalArgumentException,
            ClassNotFoundException, SQLException {
        DB_MySQL db = new DB_MySQL();
        List menuList;

        try {
            db.openDBConnection("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/restaurant", "root", "dawn00");
            menuList = db.findAllRecords("SELECT * FROM menu", true);
            for (Object obj : menuList) {
                System.out.println(obj);
            }
            
        } catch (ClassNotFoundException cnf) {
            System.out.println(cnf.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}