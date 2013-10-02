package model;

/**
 * MenuItem is a low-level class that provitemIdes information about the menu
 * item; the itemIdentifier (itemId), the item name (itemName), and the item
 * price (itemPrice).
 *
 * @author dawn bykowski, dpaasch@my.wctc.edu
 * @version 1.01 
 * changes: id to itemId. getItemId to getId. setItemId to setId. 
 * setId validation from null to 0L
 */
public class MenuItem {

    // Variable declarations //
    private Long itemId;
    private String itemName;  // Menu item name
    private double itemPrice;  // Menu item price
    private final String IAE_ERR = "\n\n Error: Value cannot be 0, less than 0, or null (Value = ",
            NPE_ERR = "\n\n  Error: Value cannot be null or empty (Value = ";

    /**
     * Default MenuItem constructor
     */
    public MenuItem() {
    }

    /**
     * Creates a new MenuItem object by setting the itemId, itemName and
     * itemPrice private variables.
     *
     * @param itemName : The value of the private variable that itemIdentifies
     * the menu item name. Defaults to null if no value is passed in.
     * @param itemPrice : The value of the private variable that itemIdentifies
     * the menu item price. Defaults to null if no value is passed in.
     */
    public MenuItem(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    /**
     * Creates a new MenuItem object by setting the itemId, itemName and
     * itemPrice private variables.
     *
     * @param itemId : The menu item itemIdentifier expressed as an int.
     * Defaults to null if no value is passed in.
     * @param itemName : The value of the private variable that itemIdentifies
     * the menu item name. Defaults to null if no value is passed in.
     * @param itemPrice : The value of the private variable that itemIdentifies
     * the menu item price. Defaults to null if no value is passed in.
     */
    public MenuItem(Long itemId, String itemName, double itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    /**
     * Returns the value of the menu item itemIdentifier received in the form of
     * the private variable.
     *
     * @return itemId : The value of the private variable that itemIdentifies
     * the menu item.
     */
    public final Long getId() {
        return itemId;
    }

    /**
     * Sets the value of the private variable for the menu item itemIdentifier.
     *
     * @param itemId : The menu item itemIdentifier expressed as a Long object.
     *
     */
    public final void setId(Long itemId) throws IllegalArgumentException {
        if (itemId != 0L) {
            this.itemId = itemId;
        } else {
            throw new NullPointerException(IAE_ERR + "itemId)");
        }
    }

    /**
     * Returns the value of the menu item name received in the form of the
     * private variable.
     *
     * @return itemName : The value of the private variable that itemIdentifies
     * the menu item name. Defaults to null if no value is passed in.
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
        if (itemName == null) {
            throw new NullPointerException(NPE_ERR + "itemName)");
        }
        this.itemName = itemName;
    }

    /**
     * Returns the value of the menu item price received in the form of the
     * private variable.
     *
     * @return itemPrice : The value of the private variable that itemIdentifies
     * the menu item price. Defaults to null if no value is passed in.
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
        if (itemPrice <= 0) {
            throw new IllegalArgumentException(IAE_ERR + "itemPrice)");
        } else {
            this.itemPrice = itemPrice;
        }
    }

    // for testing
    public static void main(String[] args) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(Long.valueOf(6));
        System.out.println("Printing value of Long object for itemId: " + menuItem.getId());
        menuItem.setItemName("Salmon");
        menuItem.setItemPrice(32.50);
        System.out.println(menuItem.getId() + " " + menuItem.getItemName()
                + " ... " + menuItem.getItemPrice());
    }
}
