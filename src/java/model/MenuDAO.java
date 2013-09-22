package model;

import db.accessor.DBAccessor;
import db.accessor.DBGeneric;
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

    private DBAccessor db;
    private static final String FIND_ALL_MENU_ITEMS = "SELECT * FROM menu",
            FIND_MENU_ITEM_BY_ID = "SELECT menu_id FROM menu";
    private static final String IAE_ERR = "Error: URL not found or empty.",
            CNF_ERR = "Error: Failed to load the JDBC driver.",
            SQL_ERR = "Error: Unable to connect to the database.";

    /**
     * Constructor takes a database access parameter as a variable
     *
     * @param db
     */
    public MenuDAO(DBAccessor db) {
        this.db = db;
    }

    private void openDBConnection() throws IllegalArgumentException, ClassNotFoundException, SQLException {
        try {
            db.openDBConnection("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/restaurant", "root", "dawn00");
        } catch (IllegalArgumentException ex) {
            System.err.println(IAE_ERR);
        } catch (ClassNotFoundException ex) {
            System.err.println(CNF_ERR);
        } catch (SQLException ex) {
            System.err.println(SQL_ERR);
        }
    }

    @Override
    public List getAllMenuItems() throws SQLException, Exception {
        this.openDBConnection();        
        List<Map> rawData = new ArrayList<Map>();
        List<MenuItem> records = new ArrayList<MenuItem>();        
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
            records.add(menuItem);
        }
        return records;
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

    public DBAccessor getDb() {
        return db;
    }

    public void setDb(DBAccessor db) {
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
    //        DBGeneric dbG = new DBGeneric();
//        List menuList;
//        
//        try {
//        dbG.openDBConnection("com.mysql.jdbc.Driver",
//                    "jdbc:mysql://localhost:3306/restaurant", "root", "dawn00");
//        menuList = dbG.findAllRecords("SELECT * FROM menu", true);
//            for (Object obj : menuList) {
//                System.out.println(obj);
//            }
//        } catch (ClassNotFoundException cnf) {
//            System.out.println(cnf.getLocalizedMessage());
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }
}
