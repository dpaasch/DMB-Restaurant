package model;

import java.util.ArrayList;
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
    private IMenuDAO dao = new MenuDAO();
    // Instantiates the MenuDatabase for retrieval of menu items
//    private MenuDatabase mdb = new MenuDatabase();
    private final String NPE_ERR = " Error: Value cannot be null or empty";

    /**
     * Default MenuService constructor
     */
    public MenuService() {
        
    }

    /**
     * Returns the list of menu items retrieved from the mock database
     * (MenuDatabase).
     *
     * @return the value of the private variable identifying the allMenuItems
     * object
     */
    public List getAllMenuItems() throws Exception {
        List<MenuItem> allMenuItems = dao.getAllMenuItems();
        return allMenuItems;
    }

    /**
     * Returns the list of ordered menu items retrieved from the mock database
     * (MenuDatabase).
     *
     * @param key : the identifier for finding the menu item. Defaults to null
     * if no value is passed in.
     * @return the value of the private variable identifying the orderedMenuItem
     * object.
     * @throws NullPointerException if key is null
     */
    public List getOrderedMenuItems(String[] key) throws NullPointerException {
        List<MenuItem> orderedMenuItem = new ArrayList<MenuItem>();
        if (key != null) {
            for (String s : key) {
//                orderedMenuItem.add(mdb.getMenuItem(s));
            }
        } else {
            throw new NullPointerException(NPE_ERR);
        }
        return orderedMenuItem;
    }
// for testing
    public static void main(String[] args) throws Exception {
        MenuService ms = new MenuService();
        List<MenuItem> allMenuItems = ms.getAllMenuItems();
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());            
        }
//        String[] mItem = {"Signature Steak", "Baked Potato"};
//      List<MenuItem> orderedMenuItems = ms.getOrderedMenuItems(mItem);
//        System.out.println("\nOrdered Items:");
//        for (MenuItem m : orderedMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());            
//        } 
    }
}
