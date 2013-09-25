package model;

import db.accessor.DBAccessor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dawn Bykowski
 * @version 1.00
 */
public interface IMenuDAO {

    public abstract DBAccessor getDb();

    public abstract void setDb(DBAccessor db);

    public abstract List<MenuItem> getAllMenuItems() throws SQLException, Exception;

    public abstract MenuItem getMenuItemById(String id) throws SQLException, Exception;
}
