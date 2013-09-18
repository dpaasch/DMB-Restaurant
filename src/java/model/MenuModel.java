package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is responsible solely for the menu items & their prices.
 *
 * @author Dawn Bykowski
 */
public class MenuModel {

    private String itemName;  // Menu item name
    private double itemPrice;  // Menu item price

    // Constructor requires a menuItem
    public MenuModel(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;      
    }

    // This list of items will move into a database
    public List getMenuItems() {
        List menu = new ArrayList();
        menu.add(new MenuModel("Signature Steak", 25.95));
        menu.add(new MenuModel("Lobster", 44.75));
        menu.add(new MenuModel("House Salad", 4.95));
        menu.add(new MenuModel("Greek Salad", 6.95));
        menu.add(new MenuModel("Baked Potato", 3.50));
        menu.add(new MenuModel("Rice Pilaf", 4.75));
        menu.add(new MenuModel("Soft Drink", 1.95));
        menu.add(new MenuModel("Mixed Drink", 6.95));        
        return menu;        
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
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
        final MenuModel other = (MenuModel) obj;
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
//        List<String> menu = new ArrayList<String>();
//        menu.add("Lobster");
//        menu.add("Greek Salad");
//        menu.add("Baked Potato");
//        menu.add("Soft Drink");
//
//        String list = (String)menu.get(2);
//        for (String s: menu) {
//            System.out.println(s);
//        }
//    }    
}
