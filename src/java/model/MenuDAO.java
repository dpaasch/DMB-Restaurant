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
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver",
            URL = "jdbc:mysql://localhost:3306/restaurant", 
            USERNAME = "root",
            PASSWORD = "dawn00",
            TABLE = "menu",
            PK_FIELD = "menu_id",
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
            String id = map.get("menu_id").toString();
            menuItem.setId(new Long(id));
            String name = map.get("item_name").toString();
            menuItem.setItemName(name);
            String price = map.get("item_price").toString();
            menuItem.setItemPrice(new Double(price));
            records.add(menuItem);
        }
        return records;
    }

    @Override
    public MenuItem getMenuItemById(String id) throws DataAccessException {
        Map rawData = new HashMap();

        try {
            this.openDBConnection();
            rawData = db.findRecordById("menu", "menu_id", new Long(id), true);
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
            // NOTE: Need to use a better exception
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }

        MenuItem menuItem = new MenuItem();
        menuItem.setId(new Long(rawData.get("menu_id").toString()));
        menuItem.setItemName(rawData.get("item_name").toString());
        menuItem.setItemPrice(new Double(rawData.get("item_price").toString()));
        return menuItem;
    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) throws DataAccessException {
        this.openDBConnection();
        
        try {
            db.deleteRecords(TABLE, PK_FIELD, menuItem.getId(), true);
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
        colDescriptors.add("item_name");
        colDescriptors.add("item_price");

        List colValues = new ArrayList();
        colValues.add(menuItem.getItemName());
        colValues.add(menuItem.getItemPrice());

        try {
            // if the id is null, it's a new record, else it's an update
            if (menuItem.getId() == null) {
                db.insertRecord(TABLE, colDescriptors, colValues, true);
            } else {
                db.updateRecords(TABLE, colDescriptors, colValues, PK_FIELD,
                        menuItem.getId(), true);
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
        dao.openDBConnection();
//        // test get all items
//        List<MenuItem> allMenuItems = dao.getAllMenuItems();
//        System.out.println(" getAllMenuItems() ...");
//        for (MenuItem m : allMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
//        }
//        // test get by id
//        String[] orderedMenuItems = {"1", "2", "3"};
//        List<MenuItem> mi = new ArrayList<>();
//        System.out.println("\n getMenuItemById() ... ");
//        for (String s : orderedMenuItems) {
//            MenuItem m = dao.getMenuItemById(s);
//            mi.add(m);
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
//        }
        // test delete
        MenuItem menuItem = new MenuItem(Long.valueOf(8), "Mixed Drink", 6.95);
//        dao.deleteMenuItems(menuItem);
        System.out.println("Deleted item: " + menuItem.getItemName());

        // test insert       
//       MenuItem menuItem = new MenuItem("Baked Salmon", 24.75);
//        dao.saveMenuItem(menuItem);
//       List<MenuItem> allMenuItems = dao.getAllMenuItems();
//        System.out.println(" getAllMenuItems() ...");
//        for (MenuItem m : allMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
//        }

        //test update         
//        System.out.println(" saveMenuItem() ... update");
//        MenuItem menuItemUpdate = dao.getMenuItemById("6");
//        if (menuItemUpdate != null) {
//            menuItemUpdate.setItemPrice(4.75);
//            dao.saveMenuItem(menuItemUpdate);
//            System.out.println("Updated menu item: " + menuItemUpdate.getItemName());            
//        } else {
//            System.out.println("Could not find menu item id=6 to update");
//        }
    }
}
