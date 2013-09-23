package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible solely for the menu items & their prices.
 *
 * @author Dawn Bykowski
 */
public class MenuService {

    private MenuDatabase mdb = new MenuDatabase();
    private final String NPE_ERR = " Error: Menu item cannot be null";

    public MenuService() {
    }

    public List getAllMenuItems() {
        List allMenuItems = mdb.getAllMenuItems();
        return allMenuItems;

    }

    public List getOrderedMenuItems(String[] key) {
        List<MenuItem> orderedMenuItem = new ArrayList<MenuItem>();
        if (key == null) {
            throw new NullPointerException(NPE_ERR);
        } else {
            for (String s : key) {
                orderedMenuItem.add(mdb.getMenuItem(s));
            }
            return orderedMenuItem;
        }
    }
    // for testing
//    public static void main(String[] args) {
//        MenuService ms = new MenuService();
//        List<MenuItem> allMenuItems = ms.getAllMenuItems();
//        for (MenuItem m : allMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());            
//        }
//        String[] mItem = {"Signature Steak", "Baked Potato"};
//      List<MenuItem> orderedMenuItems = ms.getOrderedMenuItems(mItem);
//        System.out.println("\nOrdered Items:");
//        for (MenuItem m : orderedMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());            
//        } 
//    }
}