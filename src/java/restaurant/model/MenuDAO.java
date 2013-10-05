package restaurant.model;

import db.accessor.DBAccessor;
import db.accessor.DBConnector;
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
//    private ContextListener listener = new ContextListener();
    private DBAccessor dbAccessor;
    private DBConnector dbConnector;
    private String 
            DRIVER_CLASS = "",
            URL = "",
            USERNAME = "",
            PASSWORD = "";

    // Constants
    private static final String
            TABLE = "menu",
            PK_FIELD = "item_id",
            ITEM_ID = "item_id",
            ITEM_NAME = "item_name",
            ITEM_PRICE = "item_price",
            FIND_ALL_MENU_ITEMS = "SELECT * FROM menu",
            FIND_MENU_ITEM_BY_ID = "SELECT item_id FROM menu";

   public MenuDAO() {
    }
    
    /**
     * Default Constructor creates a new MenuDAO object.
     *
     */
    public MenuDAO(DBConnector dbConnector) {
        this.dbAccessor = new DB_MySQL();
        this.dbConnector = dbConnector;
        DRIVER_CLASS = dbConnector.getDriverClassName();
        URL = dbConnector.getUrl();
        USERNAME = dbConnector.getUserName();
        PASSWORD = dbConnector.getPassword();
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
     * invalitemId url could cause this; or, a database that is no longer available
     * due to network or access permission problems.
     */
    private void openDBConnection() throws DataAccessException {
        try {
            dbAccessor.openDBConnection(DRIVER_CLASS, URL, USERNAME, PASSWORD);
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
            // if the itemId is null, it's a new record, else it's an update
            if (menuItem.getItemId() == null) {
                dbAccessor.insertRecord(TABLE, colDescriptors, colValues, true);
            } else {
                dbAccessor.updateRecords(TABLE, colDescriptors, colValues, ITEM_ID,
                        menuItem.getItemId(), true);
            }
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
            // NOTE: Need to use a better exception
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }
    }
    
    @Override
    public void deleteMenuItem(MenuItem menuItem) throws DataAccessException {
        this.openDBConnection();

        try {
            dbAccessor.deleteRecords(TABLE, ITEM_ID, menuItem.getItemId(), true);
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * Returns the list of all menu items.
     *
     * @return menuItems : The value of the private variable that itemIdentifies the
     * menu items. Defaults to null if no value is passed in.
     */
    @Override
    public List<MenuItem> getAllMenuItems() throws DataAccessException {

        List<Map> rawData = new ArrayList<Map>();
        List<MenuItem> records = new ArrayList<MenuItem>();
        try {
            this.openDBConnection();
            rawData = dbAccessor.findAllRecords(FIND_ALL_MENU_ITEMS, true);
        } catch (SQLException sql) {
            throw new DataAccessException(sql.getLocalizedMessage());
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage());
        }

        MenuItem menuItem = null;

        for (Map map : rawData) {
            menuItem = new MenuItem();
            String itemId = map.get(ITEM_ID).toString();
            menuItem.setItemId(new Long(itemId));
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
            rawData = dbAccessor.findRecordById(TABLE, PK_FIELD, ID, true);
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
    public final DBAccessor getDBAccessor() {
        return dbAccessor;
    }

    @Override
    public final void setDBAccessor(DBAccessor dbAccessor) {
        this.dbAccessor = dbAccessor;
    }

    // for testing
//    public static void main(String[] args) throws DataAccessException, Exception {
//        MenuDAO dao = new MenuDAO(new DB_MySQL());
//        MenuItem menuItem;
//        List<MenuItem> allMenuItems = dao.getAllMenuItems();
//        dao.openDBConnection();
//
//        // get MENU
//        System.out.println(" ... ORIGINAL MENU ... ");
//        for (MenuItem m : allMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
//        }
//        // get by itemId
//        String[] orderedMenuItems = {"5"};
//        List<MenuItem> mi = new ArrayList<>();
//        for (String s : orderedMenuItems) {
//            MenuItem m = dao.getMenuItemById(s);
//            mi.add(m);
//            System.out.println("\nRetrieved menu item by itemId: " 
//                    + m.getItemName() + "(" + m.getItemId() + ") ... " + m.getItemPrice());
//        }
//        // delete
//        MenuItem miDeletable = dao.getMenuItemById("8");
//        dao.deleteMenuItem(miDeletable);
//        System.out.println("\nDeleted item: " + miDeletable.getItemName() 
//                + " ... " + miDeletable.getItemPrice());
//
//        // insert
//        menuItem = new MenuItem(null, "Mixed Drink", 7.25);
//        dao.saveMenuItem(menuItem);
//        System.out.println("\nInserted: " + menuItem.getItemName() + " @ $" 
//                + menuItem.getItemPrice());
//
//        // update
//        MenuItem miUpdateable = dao.getMenuItemById("6");
//        if (miUpdateable != null) {
//        miUpdateable.setItemPrice(3.75);
//        dao.saveMenuItem(miUpdateable);
//        System.out.println("\nUpdated: " + miUpdateable.getItemName() + " ( " 
//                    + Long.valueOf(miUpdateable.getItemId()) + ") ... " + miUpdateable.getItemPrice());
//        }
//        //get MENU
//        System.out.println(
//                "\n ... CURRENT MENU ... ");
//        allMenuItems = dao.getAllMenuItems();
//        for (MenuItem m : allMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
//        }
//    }
}