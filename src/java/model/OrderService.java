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

    private MenuService menuItem;  // used to declare the model object instantiated below
    private List<String> itemList;  // this is the list of items the customer ordered
    private List<String> lineItems;  // the ordered menu items (will be used by controller) 
    private double subTotal, tax, total, tip, grandTotal;
    private final double TAX = 0.051;
    private final String NPE_ERR = " Error: Menu item cannot be null";

    // Constructor: Takes the choices made by the customer for the order, as parameters.
    public OrderService(List<String> itemList) {
        menuItem = new MenuService();
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
                lineItems.add(li + " ... $" + menuItem.getItemPrice(li));
            }
        }
        return lineItems;
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
        for (String st : itemList) {
            subTotal = subTotal + menuItem.getItemPrice(st);
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

    @Override
    public String toString() {
        return "OrderModel{" + "subTotal=" + subTotal + ", tax=" + tax + ", total=" + total + ", tip=" + tip + ", grandTotal=" + grandTotal + '}';
    }
    
        @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.subTotal) ^ (Double.doubleToLongBits(this.subTotal) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.tax) ^ (Double.doubleToLongBits(this.tax) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.tip) ^ (Double.doubleToLongBits(this.tip) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.grandTotal) ^ (Double.doubleToLongBits(this.grandTotal) >>> 32));
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
        final OrderService other = (OrderService) obj;
        if (Double.doubleToLongBits(this.subTotal) != Double.doubleToLongBits(other.subTotal)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tax) != Double.doubleToLongBits(other.tax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tip) != Double.doubleToLongBits(other.tip)) {
            return false;
        }
        if (Double.doubleToLongBits(this.grandTotal) != Double.doubleToLongBits(other.grandTotal)) {
            return false;
        }
        return true;
    }
    // for testing
//    public static void main(String[] args) {
//        List<String> menuItems = new ArrayList<String>();
//        menuItems.add("Lobster");
//        menuItems.add("GreekSalad");
//        menuItems.add("BakedPotato");
//        menuItems.add("SoftDrink");
//        
//        OrderService order = new OrderService(menuItems);
//        System.out.println(order.getLineItems());
//        double subTotal = order.getSubTotal();
//        double tax = order.getTax();
//        double total = order.getTotal();
//        System.out.println(subTotal + "..." + tax + "..." + total);
//    }

}
