package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The OrderCalculator class is responsible for all order calculations (subTotal,
 * tax, total, tip, grandTotal).  It will use the OrderService.
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 */
public class OrderCalculator {
    
    // Variable declarations //
    private List<MenuItem> orderedItems = new ArrayList<MenuItem>();    
    private double subTotal, tax, total, tip, grandTotal;
    private final double TAX = 0.051, TIP_RATE = 0.20, ZERO = 0.00;

    public OrderCalculator(List<MenuItem> orderedItems) {
        this.orderedItems = orderedItems;
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
        for (MenuItem m : orderedItems) {
            subTotal += m.getItemPrice();
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
        tip = subTotal * TIP_RATE;
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

    
}
