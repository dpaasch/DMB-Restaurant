package model;

import db.accessor.DBAccessor;
import db.accessor.DB_MySQL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dawn Bykowski
 */
public class MenuDAO implements IMenuDAO {

    // Variable declarations //
    private DBAccessor db;
    // Constructors
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver",
            URL = "jdbc:mysql://localhost:3306/restaurant",
            USERNAME = "root",
            PASSWORD = "dawn00",
            TABLE = "menu",
            PK_FIELD = "menu_id",
            ITEM_ID = "menu_id",
            ITEM_NAME = "item_name",
            ITEM_PRICE = "item_price",
            FIND_ALL_MENU_ITEMS = "SELECT * FROM menu",
            FIND_MENU_ITEM_BY_ID = "SELECT menu_id FROM menu";

    /**
     * Default Constructor creates a new MenuDAO object.
     *
     */
    public MenuDAO(DBAccessor db) {
        setDb(db);
    }

    /**
     * Returns the list of all menu items.
     *
     * @return menuItems : The value of the private variable that identifies the
     * menu items. Defaults to null if no value is passed in.
     */
    @Override
    public List<MenuItem> getAllMenuItems() throws DataAccessException {

        List<Map> rawData = new ArrayList<Map>();
        List<MenuItem> records = new ArrayList<MenuItem>();
        try {
            this.openDBConnection();
            rawData = db.findAllRecords(FIND_ALL_MENU_ITEMS, true);
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }

        MenuItem menuItem = null;

        for (Map map : rawData) {
            menuItem = new MenuItem();
            String id = map.get(ITEM_ID).toString();
            menuItem.setItemId(new Long(id));
            String name = map.get(ITEM_NAME).toString();
            menuItem.setItemName(name);
            String price = map.get(ITEM_PRICE).toString();
            menuItem.setItemPrice(new Double(price));
            records.add(menuItem);
        }
        return records;
    }

    @Override
    public MenuItem getMenuItemById(String ID) throws DataAccessException {
        Map rawData = new HashMap();

        try {
            this.openDBConnection();
            rawData = db.findRecordById(TABLE, PK_FIELD, ID, true);
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
            // NOTE: Need to use a better exception
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }

        MenuItem menuItem = new MenuItem();
        String id = rawData.get(ITEM_ID).toString();
        menuItem.setItemId(new Long(id));
        String itemName = rawData.get(ITEM_NAME).toString();
        menuItem.setItemName(itemName);
        String itemPrice = rawData.get(ITEM_PRICE).toString();
        menuItem.setItemPrice(new Double(itemPrice));

        return menuItem;
    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) throws DataAccessException {
        this.openDBConnection();

        try {
            db.deleteRecords(TABLE, ITEM_ID, menuItem.getItemId(), true);
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }
    }

    @Override
    public void saveMenuItem(MenuItem menuItem) throws DataAccessException {

        this.openDBConnection();

        List<String> colDescriptors = new ArrayList<String>();
        colDescriptors.add(ITEM_NAME);
        colDescriptors.add(ITEM_PRICE);

        List colValues = new ArrayList();
        colValues.add(menuItem.getItemName());
        colValues.add(menuItem.getItemPrice());

        try {
            // if the id is null, it's a new record, else it's an update
            if (menuItem.getItemId() == null) {
                db.insertRecord(TABLE, colDescriptors, colValues, true);
            } else {
                db.updateRecords(TABLE, colDescriptors, colValues, ITEM_ID,
                        menuItem.getItemId(), true);
            }
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
            // NOTE: Need to use a better exception
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }
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
    private void openDBConnection() throws DataAccessException {
        try {
            db.openDBConnection(DRIVER_CLASS, URL, USERNAME, PASSWORD);
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
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

    public final DBAccessor getDb() {
        return db;
    }

    public final void setDb(DBAccessor db) {
        this.db = db;
    }

    // for testing
    public static void main(String[] args) throws DataAccessException, Exception {
        MenuDAO dao = new MenuDAO(new DB_MySQL());
        MenuItem menuItem;
        List<MenuItem> allMenuItems = dao.getAllMenuItems();
        dao.openDBConnection();

        // get MENU
        System.out.println(" ... ORIGINAL MENU ... ");
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
        // get by id
        String[] orderedMenuItems = {"5"};
        List<MenuItem> mi = new ArrayList<>();
        for (String s : orderedMenuItems) {
            MenuItem m = dao.getMenuItemById(s);
            mi.add(m);
            System.out.println("\nRetrieved menu item by id: " + m.getItemName() + " ... " + m.getItemPrice());
        }
        // delete
        menuItem = new MenuItem(Long.valueOf(8), "Mixed Drink", 7.00);
        dao.deleteMenuItem(menuItem);
        System.out.println("\nDeleted item: " + menuItem.getItemName() + " ... " + menuItem.getItemPrice());

        // insert
        menuItem = new MenuItem(null, "Mixed Drink", 7.25);
        dao.saveMenuItem(menuItem);
        System.out.println("\nInserted: " + menuItem.getItemName() + " @$" + menuItem.getItemPrice());

        // update
        menuItem = new MenuItem(Long.valueOf("6"), "Rice Pilaf", 3.75);
        dao.saveMenuItem(menuItem);
        System.out.println("\nUpdated: " + menuItem.getItemName() + " ( " + menuItem.getItemId() + ")"
                + " ... " + menuItem.getItemPrice());

        //get MENU
        System.out.println(
                "\n ... CURRENT MENU ... ");
        allMenuItems = dao.getAllMenuItems();
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
    }
}