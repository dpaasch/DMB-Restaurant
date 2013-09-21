package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class is responsible solely for the menu items & their prices.
 *
 * @author Dawn Bykowski
 */
public class MenuService {
    
    private MenuDatabase mdb = new MenuDatabase();

//    private final Map<String, Double> menuItems = new HashMap<String, Double>();
//
//    private final String NPE_ERR = " Error: Menu item cannot be null";
//    private final String IAE_ERR = "Error: Menu item not found";
    
    public MenuService(List menuItem) {

    }
    
    public List getAllMenuItems() {
        List allMenuItems = mdb.getAllMenuItems();
        return allMenuItems;
        
    }

    // for testing
    public static void main(String[] args) {
        List<String> menuItems = new ArrayList<String>();
        menuItems.add("Lobster");
        menuItems.add("GreekSalad");
        menuItems.add("BakedPotato");
        menuItems.add("SoftDrink");
        
        OrderService order = new OrderService(menuItems);
        System.out.println(order.getLineItems());
    }       
}
