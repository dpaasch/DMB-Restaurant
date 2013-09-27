package model;

import db.accessor.DB_MySQL;
import java.sql.SQLException;
import java.util.List;

/**
 * The MenuService class is responsible for communicating with the mock database
 * (MenuDatabase) to retrieve the menu items.
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 */
public class MenuService {

    // Variable declarations //
    private IMenuDAO dao;
//    private MenuDatabase mdb = new MenuDatabase();

    /**
     * Default MenuService constructor
     */
    public MenuService() {
        dao = new MenuDAO(new DB_MySQL());
    }

    /**
     * Returns the list of menu items retrieved from the mock database
     * (MenuDatabase).
     *
     * @return the value of the private variable identifying the allMenuItems
     * object
     */
    public List getAllMenuItems() throws DataAccessException {
        return dao.getAllMenuItems();
    }

    public void deleteItem(MenuItem menuItem) throws SQLException, Exception {
    }

    // for testing
    public static void main(String[] args) throws Exception {
        MenuService ms = new MenuService();
        List<MenuItem> allMenuItems = ms.getAllMenuItems();
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }

//        String[] mItem = {"1", "4"};
//        List<MenuItem> orderedMenuItems = ms..getOrderedMenuItems(mItem);
//        System.out.println("\nOrdered Items:");
//        for (MenuItem m : orderedMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
//        }
    }
}
