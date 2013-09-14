package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is solely responsible for all items related to the actual customer
 * order.
 *
 * @author Dawn Bykowski
 */
public class OrderModel {

    private MenuModel menu;  // used to declare the model object instantiated below
    private List<String> itemList;
    private List<String> lineItems;
//    private List<String> orderList;  // this is the list of items the customer ordered
//    private List<String> orderedMenuItems;  // the ordered menu items
    private String menuItemName;
    private double menuItemPrice, subTotal, tax, total;
    private final double TAX_PERCENTAGE = 0.051;

    // Constructor: Takes the choices made by the customer for the order, as parameters.
    public OrderModel(List<String> itemList) {
        menu = new MenuModel();
        this.itemList = itemList;
    }

    public List<String> getOrderList() {
        return itemList;
    }

    public void setOrderList(List<String> itemList) {
        this.itemList = itemList;
    }

    public List<String> getOrderedMenuItems() {
        lineItems = new ArrayList();
        for (String s : itemList) {
            lineItems.add(s + " ... " + menu.getMenuItemPrice(s));
        }
        return lineItems;
    }

    public double getSubTotal() {
        subTotal = 0;
        for (String s : itemList) {
            subTotal += menu.getMenuItemPrice(s);
        }
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        tax = subTotal * TAX_PERCENTAGE;
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        total = subTotal + tax;
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // methods to obtain data from the MenuModel class
    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public double getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }
}
