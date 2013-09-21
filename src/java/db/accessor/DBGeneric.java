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

    public List findRecords(String sqlStmnt, boolean closeConnection)
            throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        final List itemList = new ArrayList();
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
                } // end for
                System.out.println(record);                
                itemList.add(record.get(metaData.getColumnName(1)));
            } // end while
            
        } catch (SQLException sql) {
            throw new SQLException(SQL_ERR);
        } finally {
            // Make sure we close the statement and connection objects no matter what.
            // Since these also throw checked exceptions, we need a nested try-catch
            try {
                stmt.close();
                dbConnection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return itemList;
    }
    
    public static void main(String[] args) throws IllegalArgumentException,
            ClassNotFoundException, SQLException {
        DBGeneric db = new DBGeneric();
        List<String> menuList = new ArrayList<String>();
       
        try {
            db.openDBConnection("com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/menu", "root", "dawn00");
            menuList = db.findRecords("SELECT * FROM menu", true);
            for (Object obj : menuList) {
                System.out.println(obj);
            }
        } catch(ClassNotFoundException cnf) {
            System.out.println(DBGeneric.class.getName());
        }
    }
}