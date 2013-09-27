package model;

import java.util.List;

/**
 *
 * @author Dawn Bykowski
 * @version 1.00
 */
public interface IMenuDAO {
//
//    public abstract DBAccessor getDb();
//
//    public abstract void setDb(DBAccessor db);

    public abstract List<MenuItem> getAllMenuItems() throws DataAccessException;
    
    public abstract MenuItem getMenuItemById(String id) throws DataAccessException;
    
    /**
     *
     * @param menuItem
     * @throws DataAccessException
     * @throws Exception
     */
    public abstract void deleteMenuItems(MenuItem menuItem) throws DataAccessException, Exception;
    
}

