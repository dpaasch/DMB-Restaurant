package restaurant.model;

import db.accessor.DBAccessor;
import java.util.List;

/**
 * Interface implemented by the MenuDAO. Contains methods that interact with the
 * database to manipulate the data.
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 */
public interface IMenuDAO {

    public abstract DBAccessor getDBAccessor();

    public abstract void setDBAccessor(DBAccessor dbAccessor);
    
    public abstract void saveMenuItem(MenuItem menuItem) throws DataAccessException;
    
    public abstract void deleteMenuItem(MenuItem menuItem) throws DataAccessException;

    public abstract List getAllMenuItems() throws DataAccessException;

    public abstract MenuItem getMenuItemById(String id) throws DataAccessException;


}
