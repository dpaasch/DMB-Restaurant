package model;

import java.util.List;

/**
 * MenuItem is a low-level class that provides information about the menu item;
 * the identifier (id), the item name (itemName), and the item price
 * (itemPrice).
 *
 * @author tim
 */
public class MenuItem {

    private int id;
    private String itemName;  // Menu item name
    private double itemPrice;  // Menu item price
    private final String IAE_ERR = " Error: Menu item value cannot be < 0",
            NPE_ERR = " Error: Menu item value cannot be null or empty";

    public MenuItem() {
    }

    public MenuItem(int id, String itemName, double itemPrice) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    /**
     * Returns the value of the menu item identifier received in the form of the
     * private variable.
     *
     * @return id : The value of the private variable that identifies the menu
     * item. If there is no value, it is set to null.
     */
    public final int getId() {
        return id;
    }

    /**
     * Sets the value of the private variable for the menu item identifier.
     *
     * @param id : The menu item identifier expressed as an int. Defaults to
     * null if no value is passed in.
     * @throws IllegalArgumentException : if id parameter < 0
     *
     */
    public final void setId(int id) throws IllegalArgumentException {
        if (id >= 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException(IAE_ERR);
        }
    }

    /**
     * Returns the value of the menu item name received in the form of the
     * private variable.
     *
     * @return itemName : The value of the private variable that identifies the
     * menu item name. If there is no value, it is set to null.
     */
    public final String getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the private variable for the menu item name.
     *
     * @param itemName : The menu item name expressed as a String. Defaults to
     * null if no value is passed in.
     *
     */
    public final void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Returns the value of the menu item price received in the form of the
     * private variable.
     *
     * @return itemPrice : The value of the private variable that identifies the
     * menu item price. If there is no value, it is set to null.
     */
    public final double getItemPrice() {
        return itemPrice;
    }

    /**
     * Sets the value of the private variable for the menu item price.
     *
     * @param itemPrice : The menu item price expressed as a double. Defaults to
     * null if no value is passed in.
     *
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    // for testing
    public static void main(String[] args) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1);
        menuItem.setItemName(" ");
        menuItem.setItemPrice(52.73);
        System.out.println(menuItem.getId() + " " + menuItem.getItemName() + " ... " + menuItem.getItemPrice());
    }

}
