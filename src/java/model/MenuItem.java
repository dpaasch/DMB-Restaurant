package model;

/**
 * MenuItem is a low-level class that provides information about the menu item;
 * the identifier (id), the item name (itemName), and the item price
 * (itemPrice).
 *
 * @author dawn bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 * @version 1.01 - Changed from Long to long for id
 */
public class MenuItem {

    // Variable declarations //
    private long id;
    private String itemName;  // Menu item name
    private double itemPrice;  // Menu item price
    private final String IAE_ERR = " Error: Value cannot be 0, less than 0, or null",
            NPE_ERR = " Error: Value cannot be null or empty";

    /**
     * Default MenuItem constructor
     */
    public MenuItem() {
    }
    
    /**
     * Creates a new MenuItem object by setting the id, itemName and itemPrice
     * private variables.
     * @param itemName : The value of the private variable that identifies the
     * menu item name. Defaults to null if no value is passed in.
     * @param itemPrice : The value of the private variable that identifies the
     * menu item price. Defaults to null if no value is passed in.
     */
    public MenuItem(String itemName, double itemPrice) {
        setItemName(itemName);
        setItemPrice(itemPrice);
    }

    /**
     * Creates a new MenuItem object by setting the id, itemName and itemPrice
     * private variables.
     * @param id : The menu item identifier expressed as an int. Defaults to
     * null if no value is passed in.
     * @param itemName : The value of the private variable that identifies the
     * menu item name. Defaults to null if no value is passed in.
     * @param itemPrice : The value of the private variable that identifies the
     * menu item price. Defaults to null if no value is passed in.
     */
    public MenuItem(long id, String itemName, double itemPrice) {
         setId(id);
         setItemName(itemName);
         setItemPrice(itemPrice);
    }

    /**
     * Returns the value of the menu item identifier received in the form of the
     * private variable.
     *
     * @return id : The value of the private variable that identifies the menu
     * item. 
     */
    public final long getId() {
        return id;
    }

    /**
     * Sets the value of the private variable for the menu item identifier.
     *
     * @param id : The menu item identifier expressed as a long object. 
     *
     */
    public final void setId(long id) throws IllegalArgumentException {
        this.id = id;
    }

    /**
     * Returns the value of the menu item name received in the form of the
     * private variable.
     *
     * @return itemName : The value of the private variable that identifies the
     * menu item name. Defaults to null if no value is passed in.
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
        if (itemName != null || !"".equals(itemName)) {
            this.itemName = itemName;
        } else {
            itemName = null;
        }
    }

    /**
     * Returns the value of the menu item price received in the form of the
     * private variable.
     *
     * @return itemPrice : The value of the private variable that identifies the
     * menu item price. Defaults to null if no value is passed in.
     */
    public final double getItemPrice() {
        return itemPrice;
    }

    /**
     * Sets the value of the private variable for the menu item price.
     *
     * @param itemPrice : The menu item price expressed as a double. Defaults to
     * null if no value is passed in.
     * @throws IllegalArgumentException : if itemPrice parameter < 0
     */
    public final void setItemPrice(double itemPrice) {
        if (itemPrice >= 0) {
            this.itemPrice = itemPrice;
        } else {
            throw new IllegalArgumentException(IAE_ERR);
        }
    }

    // for testing
    public static void main(String[] args) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(Long.valueOf("9"));
        System.out.println("Printing value of long object for id: " + menuItem.getId());
        menuItem.setItemName("Surf & Turf");
        menuItem.setItemPrice(32.75);
        System.out.println(menuItem.getId() + " " + menuItem.getItemName()
                + " ... " + menuItem.getItemPrice());
    }
}
