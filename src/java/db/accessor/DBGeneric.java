package db.accessor;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class DBGeneric implements DBAccessor {

    private Connection dbConnection;
    private final String IAE_ERR = "Error: URL not found or empty.",
            CNF_ERR = "Error: Failed to load the JDBC driver.",
            SQL_ERR = "Error: Unable to connect to the database.";

    /**
     * openDBConnection() : This method opens the connection to a database
     *
     * @throws IllegalArgumentException, ClassNotFoundException, SQLException
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
     * closeDBConnection() : This method closes the connection to a database
     *
     * @throws SQLException
     */
    @Override
    public void closeDBConnection() throws SQLException {
        dbConnection.close();
    }

    @Override
    public List findAllRecords(String sqlStmnt, boolean closeConnection)
            throws SQLException, Exception {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        final List list = new ArrayList();
        Map record = null;

        try {
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery(sqlStmnt);
            metaData = rs.getMetaData();
            final int fields = metaData.getColumnCount();

            while (rs.next()) {
                record = new HashMap();
                for (int i = 1; i <= fields; i++) {
                    try {
                        record.put(metaData.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {
                        // no need to do anything... if it fails, just ignore it and continue
                    }
                }
                list.add(record);
            }

        } catch (SQLException sql) {
            throw new SQLException(SQL_ERR);
        } catch (Exception e) {
            throw new Exception("EXCEPTION");
        } finally {
            try {
                stmt.close();
                dbConnection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public Map findRecordById(String table, String pkField, Object keyValue,
            boolean closeConnection) throws SQLException, Exception {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        final Map record = new HashMap();

        try {
            stmt = dbConnection.createStatement();
            String sql2;

            if (keyValue instanceof String) {
                sql2 = "= '" + keyValue + "'";
            } else {
                sql2 = "=" + keyValue;
            }

            final String sql = "SELECT * FROM " + table + " WHERE " + pkField + sql2;
            rs = stmt.executeQuery(sql);
            metaData = rs.getMetaData();
            metaData.getColumnCount();
            final int fields = metaData.getColumnCount();
            // Retrieve the raw data from the ResultSet and copy the values into a Map
            // with the keys being the column names of the table.
            if (rs.next()) {
                for (int i = 1; i <= fields; i++) {
                    record.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                stmt.close();
                if (closeConnection) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        return record;
    }

    // for testing
    public static void main(String[] args) throws IllegalArgumentException,
            ClassNotFoundException, SQLException {
        DBGeneric db = new DBGeneric();
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