package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dawn Bykowski
 * @version 1.00
 */
public interface IMenuDAO {
    
    public abstract List getAllMenuItems() throws SQLException, Exception;
    
    public abstract MenuItem getMenuItemById(String id) throws SQLException, Exception;
    
}
