package model;

import db.accessor.DBAccessor;
import java.util.List;

/**
 *
 * @author Dawn Bykowski
 * @version 1.00
 */
public interface IMenuDAO {

//    public abstract DBAccessor getDb();
//
//    public abstract void setDb(DBAccessor db);

    public abstract List<MenuItem> getAllMenuItems() throws DataAccessException;
    
    public abstract MenuItem getMenuItemById(String id) throws DataAccessException;

    public abstract void deleteMenuItem(MenuItem menuItem) throws DataAccessException, Exception;
    
    public void saveMenuItem(MenuItem menuItem) throws DataAccessException;
    
}

