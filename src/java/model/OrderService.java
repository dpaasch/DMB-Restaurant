package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is solely responsible for all items related to the actual customer
 * order.
 *
 * @author Dawn Bykowski
 */
public class OrderService {

    private MenuService menuService = new MenuService();
    private List<MenuItem> orderedMenuItems = new ArrayList<MenuItem>();  // this is the list of items the customer ordered
    private double subTotal, tax, total, tip, grandTotal;
    private final double TAX = 0.051;

    // Constructor: Takes the choices made by the customer for the order, as parameters.
    public OrderService() {
        
    }

    public List<MenuItem> getOrderedMenuItems(String[] menuItem) {
        orderedMenuItems = menuService.getOrderedMenuItems(menuItem);
        return orderedMenuItems;
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

    public double getSubTotal() {
        subTotal = 0;
        for (MenuItem m : orderedMenuItems) {
            subTotal += m.getItemPrice();
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
        tip = tax * 3;
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
    public static void main(String[] args) {
        String[] mItem = {"Signature Steak", "Baked Potato"};
        OrderService orderService = new OrderService();
        List<MenuItem> orderedMenuItems = orderService.getOrderedMenuItems(mItem);
        for (MenuItem m : orderedMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
        double subTotal = orderService.getSubTotal();
        double tax = orderService.getTax();
        double total = orderService.getTotal();
        System.out.println(subTotal + "..." + tax + "..." + total);
    }
}
