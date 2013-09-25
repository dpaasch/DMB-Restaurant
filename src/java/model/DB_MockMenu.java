package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The DB_MockMenu class contains an array of initialized Menu Item objects.  
 * It provides a methods to retrieve all menu items, or just a single menu item.
 * 
 * @author dawn bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 * 
 */
public class DB_MockMenu {

    // Variable declarations //
    // Map to hold each menu item for retrieval at a later time
    private final Map<String, MenuItem> menuItemMap = new HashMap<String, MenuItem>();
    
    // Array of menuItems: Consists of a unique identifier, item name, and item price
    private MenuItem[] menuItems = {
        new MenuItem(1, "Signature Steak", 25.95),
        new MenuItem(2, "Lobster", 44.75),
        new MenuItem(3, "House Salad", 4.95),
        new MenuItem(4, "Greek Salad", 6.95),
        new MenuItem(5, "Baked Potato", 3.50),
        new MenuItem(6, "Rice Pilaf", 4.75),
        new MenuItem(7, "Soft Drink", 1.95),
        new MenuItem(8, "Mixed Drink", 6.95)};

    /**
     * Creates a new DB_MockMenu object that represents the menu items.  The 
     * variable m holds the current value from the menuItems array which is 
     * passed to the menuItemMap.
     */
    public DB_MockMenu() {
        for (MenuItem m : menuItems) {
            menuItemMap.put(m.getItemName(), m);
        }
    }

    /**
     * Returns the list of menu items.
     * @return mItems (menu items)
     */
    public List getAllMenuItems() {
        List<MenuItem> mItems = new ArrayList<MenuItem>();
        for (MenuItem m : menuItems) {
            mItems.add(m);
        }
        return mItems;
    }

    /**
     * Returns the Menu Item object in the form of a private variable
     * @param key : the identifier for finding the menu item.  Defaults to
     * null if no value is passed in.
     * @return the value of the private variable identifying the menu item object.
     */
    public MenuItem getMenuItem(String key) {
        MenuItem menuItem = menuItemMap.get(key);        
        return menuItem;
    }
    
    /**
     * Returns the state of the DB_MockMenu object
     * @return menuItemMap & menuItems objects as Strings
     */ 
    @Override
    public String toString() {
        return "MenuDatabase{" + "menuItemMap=" + menuItemMap + ", menuItems=" + menuItems + '}';
    }
    
    /**
     * Returns the hash code value for the object on which this method is invoked.
     * Supported for the benefit of hashing based collections such as Hashtable,
     * HashMap, Hashset, etc.
     * @return the hash code value as an integer
     */
      @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.menuItemMap);
        hash = 89 * hash + Arrays.deepHashCode(this.menuItems);
        return hash;
    }

    /**
     * Compares the equality of the current object with an object of the same type
     * @param obj
     * @return true if objects are equal, false if they are not equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DB_MockMenu other = (DB_MockMenu) obj;
        if (!Objects.equals(this.menuItemMap, other.menuItemMap)) {
            return false;
        }
        if (!Arrays.deepEquals(this.menuItems, other.menuItems)) {
            return false;
        }
        return true;
    }  
    
    // for testing
//    public static void main(String[] args) {
//        DB_MockMenu mdb = new DB_MockMenu();
//        List<MenuItem> allMenuItems = mdb.getAllMenuItems();
//        System.out.println(" getAllMenuItems: ");
//        for (MenuItem m : allMenuItems) {
//            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
//        }
//        System.out.println("\n getMenuItem: " + mdb.getMenuItem("Lobster").getItemName());
//    }
}
