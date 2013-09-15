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

    private MenuModel menuItem;  // used to declare the model object instantiated below
    private List<String> itemList;  // this is the list of items the customer ordered
    private List<String> lineItems;  // the ordered menu items (will be used by controller) 
    private String menuItemName;
    private double menuItemPrice, subTotal, tax, total;
    private final double TAX = 0.051;
    private final String NPE_ERR = " Error: Menu item cannot be null";

    // Constructor: Takes the choices made by the customer for the order, as parameters.
    public OrderModel(List<String> itemList) {
        menuItem = new MenuModel();
        if (itemList.isEmpty()) {
            throw new NullPointerException(NPE_ERR);
        }
        this.itemList = itemList;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }
    
    /*
     * Gets the ordered menu items as line items
     */
    public List<String> getLineItems() {
        lineItems = new ArrayList();
        for (String li : itemList) {
            if (li != null) {
            lineItems.add(li + " ... " + menuItem.getItemPrice(li));
            }
        }
        return lineItems;
    }

    public double getSubTotal() {
        subTotal = 0;
        for (String st : itemList) {
            subTotal = subTotal + menuItem.getItemPrice(st);
        }
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        tax = getSubTotal() * TAX;
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        total = getSubTotal() + tax;
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
//        double subTotal = order.getSubTotal();
//        double tax = order.getTax();
//        double total = order.getTotal();
//        System.out.println(subTotal + "..." + tax + "..." + total);
//    }
}
