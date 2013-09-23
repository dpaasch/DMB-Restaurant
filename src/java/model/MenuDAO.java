package model;

import db.accessor.DBAccessor;
import db.accessor.DBGeneric;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dawn Bykowski
 */
public class MenuDAO implements IMenuDAO {

    // Variable declarations //
    private static DBAccessor db = null;
    private static final String FIND_ALL_MENU_ITEMS = "SELECT * FROM menu",
            FIND_MENU_ITEM_BY_ID = "SELECT menu_id FROM menu";
    private static final String IAE_ERR = "Error: URL not found or empty.",
            CNF_ERR = "Error: Failed to load the JDBC driver.",
            SQL_ERR = "Error: Unable to connect to the database.";

    /**
     * Creates a new MenuDAO object by setting the DBAccessor private variable.
     *
     * @param db : The DBAccessor identifier expressed as an DBAccessor object.
     */
    public MenuDAO(DBAccessor db) {
        setDb(db);
    }

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
     * @throws SQLException if database access error occurs. For example, an
     * invalid url could cause this; or, a database that is no longer available
     * due to network or access permission problems.
     */
    private void openDBConnection() throws IllegalArgumentException, ClassNotFoundException, SQLException {
        try {
            db.openDBConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/restaurant",
                    "root", "dawn00");
        } catch (IllegalArgumentException iae) {
            System.err.println(IAE_ERR);
        } catch (ClassNotFoundException cnf) {
            System.err.println(CNF_ERR);
        } catch (SQLException sql) {
            System.err.println(SQL_ERR);
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
    public void closeDBConnection() throws SQLException {
        db.closeDBConnection();
    }

    /**
     * Returns the list of all menu items.
     *
     * @return menuItems : The value of the private variable that identifies the
     * menu items. Defaults to null if no value is passed in.
     */
    @Override
    public List getAllMenuItems() throws SQLException, Exception {
        this.openDBConnection();
        List<Map> rawData = new ArrayList<Map>();
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        try {
            rawData = db.findAllRecords(FIND_ALL_MENU_ITEMS, true);
        } catch (SQLException sql) {
            throw new SQLException(SQL_ERR);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }

        MenuItem menuItem = null;

        for (Map map : rawData) {
            menuItem = new MenuItem();
            String id = map.get("menu_id").toString();
            menuItem.setId(new Integer(id));
            String name = map.get("item_name").toString();
            menuItem.setItemName(name);
            String price = map.get("item_price").toString();
            menuItem.setItemPrice(new Double(price));
            menuItems.add(menuItem);
        }
        return menuItems;
    }

    @Override
    public MenuItem getMenuItemById(String id) throws SQLException, Exception {
        this.openDBConnection();
        Map record;
        try {
            record = db.findRecordById("menu", "menu_id", new Integer(id), true);
        } catch (SQLException sql) {
            throw new SQLException(SQL_ERR);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }

        MenuItem menuItem = new MenuItem();
        menuItem.setId(new Integer(record.get("menu_id").toString()));
        menuItem.setItemName(record.get("item_name").toString());
        menuItem.setItemPrice(new Double(record.get("item_price").toString()));
        return menuItem;
    }

    public final DBAccessor getDb() {
        return db;
    }

    public final void setDb(DBAccessor db) {
        this.db = db;
    }

    // for testing
    public static void main(String[] args) throws SQLException, Exception {
        MenuDAO dao = new MenuDAO(new DBGeneric());
        List<MenuItem> allMenuItems = dao.getAllMenuItems();
        System.out.println(" getAllMenuItems() ...");
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }

        String[] orderedMenuItems = {"1", "2", "3"};
        List<MenuItem> mi = new ArrayList<>();
        System.out.println("\n\n getMenuItemById() ... ");
        for (String s : orderedMenuItems) {
            MenuItem m = dao.getMenuItemById(s);
            mi.add(m);
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
    }
}
