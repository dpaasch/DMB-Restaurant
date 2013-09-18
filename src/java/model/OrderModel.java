package model;

import java.text.DecimalFormat;
import java.util.List;

/**
 * This class is solely responsible for all items related to the actual customer
 * order.
 *
 * @author Dawn Bykowski
 */
public class OrderModel {
    
    private List menu;  
    private String[] orderedItems;  // this is the list of items the customer ordered
    private double subTotal, tax, total, tip, grandTotal;
    private final double TAX = 0.051, TIP_RATE = 0.20;

    // Constructor: Takes the list of menu items, as well as the items ordered, as parameters.
    public OrderModel(List menu, String[] orderedItems) {
        this.menu = menu;
        this.orderedItems = orderedItems;
    }

    public List getMenu() {
        return menu;
    }

    public void setMenu(List menu) {
        this.menu = menu;
    }

    public String[] getOrderedItems() {
         return orderedItems;
    }

    public void setOrderedItems(String[] orderedItems) {
        this.orderedItems = orderedItems;
    }
    
    /**
     * Formats all double values. Could be moved into a formatter class.
     *
     * @param decimal
     * @return
     */
    public double formatValues(double decimal) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(decimal));
    }
    
    // This needs some work - 09/1/13 21:16
    public double getSubTotal() {
        subTotal = 0;
        for (String s: orderedItems) {
            int i = Integer.parseInt(s);
            MenuModel menuModel = (MenuModel)menu.get(i);
            subTotal += menuModel.getItemPrice();
        }       
        return formatValues(subTotal);
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        tax = getSubTotal() * TAX;
        return formatValues(tax);
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        total = getSubTotal() + tax;
        return formatValues(total);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTip() {
        tip = 0.00;
        tip = subTotal * TIP_RATE;
        return formatValues(tip);
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public double getGrandTotal() {
        grandTotal = 0.00;
        grandTotal = total + tip;
        return formatValues(grandTotal);
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    // for testing
//    public static void main(String[] args) {
//        List<String> menu = new ArrayList<String>();
//        menu.add("Lobster");
//        menu.add("Greek Salad");
//        menu.add("Baked Potato");
//        menu.add("Soft Drink");
//        
//        String[] orderedItems = {"Lobster","Soft Drink"};
//
//        OrderModel order = new OrderModel(menu, orderedItems);
//        System.out.println(menu);
//        System.out.println(orderedItems);
//        double subTotal = order.getSubTotal();
//        double tax = order.getTax();
//        double total = order.getTotal();
//        System.out.println(subTotal + "..." + tax + "..." + total);
//    }
}
