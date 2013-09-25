package model;

import db.accessor.DB_MySQL;
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

//    /**
//     * Returns the list of ordered menu items retrieved from the mock database
//     * (MenuDatabase).
//     *
//     * @param key : the identifier for finding the menu item. Defaults to null
//     * if no value is passed in.
//     * @return the value of the private variable identifying the orderedMenuItem
//     * object.
//     * @throws NullPointerException if key is null
//     */
//    public List getOrderedMenuItems(String[] key) throws NullPointerException, SQLException, Exception {
//        return dao.getOrderedMenuItems(key);
//    }

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
