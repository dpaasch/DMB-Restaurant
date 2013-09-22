/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dawn Bykowski
 */
public interface IMenuDAO {
    
    public abstract List getAllMenuItems() throws SQLException, Exception;
    
    public abstract MenuItem getMenuItemById(String id) throws SQLException, Exception;
    
}
