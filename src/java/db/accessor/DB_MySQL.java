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
     * A utility method to explicitly open a db connection. Note that the
     * connection will remain open until explicitly closed by member methods.
     *
     * @param driverClassName - the fully qualified name of the driver class.
     * @param url - the connection URL, driver dependent.
     * @param username for database access permission, if required. Null and ""
     * values are allowed.
     * @param password for database access permission, if required. Null and ""
     * values are allowed
     * @throws IllegalArgumentException if url is null or zero length
     * @throws ClassNotFoundException if driver class cannot be found
     * @throws SQLException if database access error occuresultSet. For example, an
     * invalid url could cause this; or, a database that is no longer available
     * due to network or access permission problems.
     */
    @Override
    public void openDBConnection(String driverClassName, String url,
            String userName, String password) throws IllegalArgumentException,
            ClassNotFoundException, SQLException {
        if (url == null || url.length() <= 0) {
            throw new IllegalArgumentException(IAE_ERR);
        } else {
            try {
                userName = (userName == null) ? "" : userName;
                password = (password == null) ? "" : password;
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, userName, password);
            } catch (ClassNotFoundException cnf) {
                System.exit(1);  // terminate the program
                throw new ClassNotFoundException(CNF_ERR);
            } catch (SQLException sql) {
                System.exit(1);  // terminate the program
                throw new SQLException(SQL_ERR);
            }
        }
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
     * Used to perform a generalized SQL query to find all records.
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
     * Retrieves a record based on the primary key of a table.
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
	 * Deletes one or more records in a table based on a single, matching field value.
	 * 
	 * @param tableName - a <code>String</code> representing the table name
	 * @param whereField - a <code>String</code> representing the field name for the
	 * search criteria.
	 * @param whereValue - an <code>Object</code> containing the value for the search criteria.
	 * @param closeConnection - true if connection should be closed automatically; if
	 * false, connection must be explicitly closed using the closeConnection method.
	 * @return an <code>int</code> containing the number of records updated.
	 * @throws SQLException if database access error or illegal sql
	 * @throws Exception for all other problems
	 */
    @Override
    public int deleteRecords(String table, String whereField, Object whereValue, boolean closeConnection) throws SQLException, Exception {
        PreparedStatement preparedStatement = null;
        int recordsDeleted = 0;
        
        try{
            preparedStatement = buildDeleteStatement(dbConnection, table, whereField);
            // delete all records if whereField is null
			if(whereField != null ) {
				if(whereValue instanceof String){
					preparedStatement.setString( 1,(String)whereValue );
				} else if(whereValue instanceof Integer ){
					preparedStatement.setInt( 1,((Integer)whereValue).intValue() );
				} else if(whereValue instanceof Long ){
					preparedStatement.setLong( 1,((Long)whereValue).longValue() );
				} else if(whereValue instanceof Double ){
					preparedStatement.setDouble( 1,((Double)whereValue).doubleValue() );
				} else if(whereValue instanceof java.sql.Date ){
					preparedStatement.setDate(1, (java.sql.Date)whereValue );
				} else if(whereValue instanceof Boolean ){
					preparedStatement.setBoolean(1, ((Boolean)whereValue).booleanValue() );
				} else {
					if(whereValue != null) preparedStatement.setObject(1, whereValue);
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
				if(closeConnection) dbConnection.close();
			} catch(SQLException e) {
				throw e;
			} // end try
		} // end finally

		return recordsDeleted;
	}
        
	/*
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
            (sqlDeleteStatement.append( whereField )).append(" = ? ");
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