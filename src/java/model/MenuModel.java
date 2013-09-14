package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible solely for the menu items & their prices.
 *
 * @author Dawn Bykowski
 */
public class MenuModel {

    private final Map<String, Double> menuItems = new HashMap<String, Double>();
    private double menuItemPrice;


    // Constructor will auto fill the hashmap with the menu item values.
    public MenuModel() {
        menuItems.put("Signature Steak", 25.95);
        menuItems.put("Lobster", 44.75);
        menuItems.put("House Salad", 4.95);
        menuItems.put("Greek Salad", 6.95);
        menuItems.put("Baked Potato", 3.50);
        menuItems.put("Rice Pilaf", 4.75);
        menuItems.put("Soft Drink", 1.95);
        menuItems.put("Alcoholic Beverage", 6.95);
    }

    /**
     * Retrieves the price of the menu item from the Hashmap as a double.
     * @param menuItem : the menu item 
     * @return menuItemPrice
     */
    public double getMenuItemPrice(String menuItem) {
        menuItemPrice = menuItems.get(menuItem);
        return menuItemPrice;
    }

    public void setMenuItemPrice(double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }
    
    
}
