package model;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 */
public class OrderCalculator {

    // this is the list of items the customer ordered
    private List<MenuItem> orderedMenuItems = new ArrayList<MenuItem>();
    private double subTotal, tax, total, tip, grandTotal, ZERO = 0.00;
    private final double TAX = 0.051, TIP_RATE = 0.20;

    public OrderCalculator(List<MenuItem> orderedMenuItems) {
        setOrderedMenuItems(orderedMenuItems);
    }

    public final void setOrderedMenuItems(List<MenuItem> orderedMenuItems) {
        this.orderedMenuItems = orderedMenuItems;
    }

    /**
     * Formatter for all double values.
     *
     * @param decimal
     * @return
     */
    public double formatValues(double decimal) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(decimal));
    }

    public double getSubTotal() {
        subTotal = ZERO;
        for (MenuItem menuItem : orderedMenuItems) {
            subTotal += menuItem.getItemPrice();
        }
        return formatValues(subTotal);
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        tax = ZERO;
        tax = getSubTotal() * TAX;
        return formatValues(tax);
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        total = ZERO;
        total = getSubTotal() + tax;
        return formatValues(total);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTip() {
        tip = ZERO;
        tip = tax * TIP_RATE;
        return formatValues(tip);
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public double getGrandTotal() {
        grandTotal = ZERO;
        grandTotal = total + tip;
        return formatValues(grandTotal);
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
    
        // for testing
    public static void main(String[] args) throws SQLException, Exception {
        MenuDAO dao = new MenuDAO();
        String[] orderedMenuItems = {"1", "2", "3"};
        List<MenuItem> mi = new ArrayList<>();
        System.out.println("\n Ordered Items ... ");
        for (String s : orderedMenuItems) {
            MenuItem m = dao.getMenuItemById(s);
            mi.add(m);
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
                OrderCalculator oc = new OrderCalculator(mi);
                System.out.println("Order Total: " + oc.getTotal());
    }
}
