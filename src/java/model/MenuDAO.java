package model;

import db.accessor.DBAccessor;
import db.accessor.DBGeneric;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static final String ID = "menu_id", ITEM_NAME = "item_name", ITEM_PRICE = "item_price";

    /**
     * default constructor
     */
    public MenuDAO() {
    }

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
    public List<MenuItem> getAllMenuItems() throws SQLException, Exception {
        this.openDBConnection();

        List<Map> rawData = new ArrayList<Map>();
        List<MenuItem> records = new ArrayList<MenuItem>();
        try {
            this.openDBConnection();
            db.findAllRecords(FIND_ALL_MENU_ITEMS, true);
        } catch (SQLException sql) {
            throw new SQLException(SQL_ERR);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }

        MenuItem menuItem = null;

        for (Map map : rawData) {
            menuItem = new MenuItem();

            String id = map.get("ID").toString();
            menuItem.setId(new Integer(id));
            String itemName = map.get("ITEM_NAME").toString();
            menuItem.setItemName(itemName);
            String itemPrice = map.get("ITEM_PRICE").toString();
            menuItem.setItemPrice(new Double(itemPrice));

            records.add(menuItem);
        }
        return records;
    }


    public DBAccessor getDb() {
        return db;
    }

    public void setDb(DBAccessor db) {
        this.db = db;
    }

    // for testing
    public static void main(String[] args) throws SQLException, Exception {
        DBGeneric dbG = new DBGeneric();
        List menuList;
        
        try {
        dbG.openDBConnection("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/restaurant", "root", "dawn00");
        menuList = dbG.findAllRecords("SELECT * FROM menu", true);
            for (Object obj : menuList) {
                System.out.println(obj);
            }
        } catch (ClassNotFoundException cnf) {
            System.out.println(cnf.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


}
