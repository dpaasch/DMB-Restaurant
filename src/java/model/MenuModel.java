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
    private String menuItemName;  // Menu item name
    private double menuItemPrice;  // Menu item price
    private final String NPE_ERR = " Error: Menu item cannot be null";
    private final String IAE_ERR = "Error: Menu item not found";

    // Constructor will auto fill the hashmap with the menu item values.
    public MenuModel() {
        menuItems.put("Signature Steak", 25.95);
        menuItems.put("Lobster", 44.75);
        menuItems.put("House Salad", 4.95);
        menuItems.put("Greek Salad", 6.95);
        menuItems.put("Baked Potato", 3.50);
        menuItems.put("Rice Pilaf", 4.75);
        menuItems.put("Soft Drink", 1.95);
        menuItems.put("Mixed Drink", 6.95);
    }

    public String getItemName() {
        return menuItemName;
    }

    public void setItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    /**
     * Retrieves the price of the menu item from the Hashmap as a double.
     *
     * @param menuItem : the menu item
     * @return menuItemPrice
     */
    public double getItemPrice(String menuItem) {
        try {
            menuItemPrice = menuItems.get(menuItem);
        } catch (NullPointerException npe) {
            System.err.println(NPE_ERR);
        } catch (IllegalArgumentException iae) {
            System.err.println(IAE_ERR);
        }
        return menuItemPrice;
    }

    public void setItemPrice(double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    // for testing
//    public static void main(String[] args) {
//        List<String> menuItems = new ArrayList<String>();
//        menuItems.add("Lobster");
//        menuItems.add("GreekSalad");
//        menuItems.add("BakedPotato");
//        menuItems.add("SoftDrink");
//
//        OrderModel order = new OrderModel(menuItems);
//        System.out.println(order.getLineItems());
//    }
}
