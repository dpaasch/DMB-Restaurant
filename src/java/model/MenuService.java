package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class is responsible solely for the menu items & their prices.
 *
 * @author Dawn Bykowski
 */
public class MenuService {

    private final Map<String, Double> menuItems = new HashMap<String, Double>();
    private String itemName;  // Menu item name
    private double itemPrice;  // Menu item price
    private final String NPE_ERR = " Error: Menu item cannot be null";
    private final String IAE_ERR = "Error: Menu item not found";

    // Constructor will auto fill the hashmap with the menu item values.
    public MenuService() {
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
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Retrieves the price of the menu item from the Hashmap as a double.
     *
     * @param menuItem : the menu item
     * @return itemPrice
     */
    public double getItemPrice(String menuItem) {
        try {
            itemPrice = menuItems.get(menuItem);
        } catch (NullPointerException npe) {
            System.err.println(NPE_ERR);
        } catch (IllegalArgumentException iae) {
            System.err.println(IAE_ERR);
        }
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
        @Override
    public String toString() {
        return "MenuModel{" + "itemName=" + itemName + ", itemPrice=" + itemPrice + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.itemName);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.itemPrice) ^ (Double.doubleToLongBits(this.itemPrice) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuService other = (MenuService) obj;
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (Double.doubleToLongBits(this.itemPrice) != Double.doubleToLongBits(other.itemPrice)) {
            return false;
        }
        return true;
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
