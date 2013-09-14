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
        menuItems.put("SignatureSteak", 25.95);
        menuItems.put("Lobster", 44.75);
        menuItems.put("HouseSalad", 4.95);
        menuItems.put("GreekSalad", 6.95);
        menuItems.put("BakedPotato", 3.50);
        menuItems.put("RicePilaf", 4.75);
        menuItems.put("SoftDrink", 1.95);
        menuItems.put("AlcoholicBeverage", 6.95);
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
