package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dawn Bykowski
 */
public class MenuDatabase {

    private final Map<String, MenuItem> menuItemMap = new HashMap<String, MenuItem>();
    private MenuItem[] menuItems = {
        new MenuItem(1, "Signature Steak", 25.95),
        new MenuItem(2, "Lobster", 44.75),
        new MenuItem(3, "House Salad", 4.95),
        new MenuItem(4, "Greek Salad", 6.95),
        new MenuItem(5, "Baked Potato", 3.50),
        new MenuItem(6, "Rice Pilaf", 4.75),
        new MenuItem(7, "Soft Drink", 1.95),
        new MenuItem(8, "Mixed Drink", 6.95)};

    public MenuDatabase() {
        for (MenuItem m : menuItems) {
            menuItemMap.put(m.getItemName(), m);
        }
    }

    public List getAllMenuItems() {
        List<MenuItem> mItems = new ArrayList<MenuItem>();
        for (MenuItem m : menuItems) {
            mItems.add(m);
        }
        return mItems;
    }

    public MenuItem getMenuItem(String key) {
        MenuItem menuItem = menuItemMap.get(key);        
        return menuItem;
    }
    
//    public static void main(String[] args) {
//        MenuDatabase mdb = new MenuDatabase();
//        List<MenuItem> mi = mdb.getAllMenuItems();
//        for (MenuItem m : mi) {
//            System.out.println(m.getItemName());
//        }
//
//    }
}
