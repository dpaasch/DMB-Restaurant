/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.accessor;

import java.sql.SQLException;

/**
 *
 * @author Dawn Bykowski
 */
public interface DBAccessor {

    /**
     * openDBConnection: This method opens the connection to a database      
     */
    public abstract void openDBConnection(String driverClassName, String url, String userName, 
            String password) throws IllegalArgumentException, ClassNotFoundException, 
            SQLException;
    
    /**
     * closeDBConnection(): This method closes the connection to a database
     */
    public abstract void closeDBConnection() throws SQLException;
}
