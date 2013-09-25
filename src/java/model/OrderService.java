package model;

import db.accessor.DBAccessor;
import db.accessor.DB_MySQL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 */
public class OrderService {

    private IMenuDAO menuDAO;
    // this is the list of items the customer ordered
    ArrayList<MenuItem> orderedMenuItems = new ArrayList<MenuItem>();
    OrderCalculator orderCalculator = new OrderCalculator(orderedMenuItems);

    // Constructor: Takes the choices made by the customer for the order, as parameters.
    public OrderService(IMenuDAO menuDAO, String[] s) throws SQLException, Exception {
        this.menuDAO = menuDAO;
        addToOrderedMenuItems(s);
    }

    private void addToOrderedMenuItems(String[] s) throws SQLException, Exception {
        for (String menuItemString : s) {
            orderedMenuItems.add(menuDAO.getMenuItemById(menuItemString));
        }
    }

    public IMenuDAO getMenuDAO() {
        return menuDAO;
    }

    public final void setMenuDAO(IMenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public ArrayList<MenuItem> getOrderedMenuItems() {
        return orderedMenuItems;
    }

    public void setOrderedMenuItems(ArrayList<MenuItem> orderedMenuItems) {
        this.orderedMenuItems = orderedMenuItems;
    }

    public double getSubTotal() {
        return orderCalculator.getSubTotal();
    }

    public double getTax() {
        return orderCalculator.getTax();
    }

    public double getTotal() {
        return orderCalculator.getTotal();
    }

    public double getTip() {
        return orderCalculator.getTip();
    }

    public double getGrandTotal() {
        return orderCalculator.getGrandTotal();
    }

    // for testing
    public static void main(String[] args) throws SQLException, Exception {
        String[] menuItems = {"1", "8"};
        OrderService orderService = new OrderService(new MenuDAO(), menuItems);
        System.out.println("Ordered Menu Items ...");
        List<MenuItem> orderedMenuItems = orderService.getOrderedMenuItems();
        for (MenuItem m : orderedMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
        double subTotal = orderService.getSubTotal();
        double tax = orderService.getTax();
        double total = orderService.getTotal();
        System.out.println("");
        System.out.println("SubTotal: $" + subTotal + "\nTax: $" + tax 
                + "\nTotal: $" + total);
    }
}
