package model;

import db.accessor.DB_MySQL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is solely responsible for all items related to the actual customer
 * order.
 *
 * @author Dawn Bykowski
 */
public class OrderService {

    private IMenuDAO dao;
    // this is the list of items the customer ordered
    private ArrayList<MenuItem> orderedMenuItems = new ArrayList<MenuItem>();
    private OrderCalculator orderCalculator = new OrderCalculator(orderedMenuItems);
    
    
    // Constructor: Takes the choices made by the customer for the order, as parameters.
    public OrderService(IMenuDAO dao, String[] s) throws DataAccessException {
        setDao(dao);
        addToOrderedMenuItems(s);
    }

    private void addToOrderedMenuItems(String[] s) throws DataAccessException {
        for (String string : s) {
            orderedMenuItems.add(dao.getMenuItemById(string));
        }
    }

    public ArrayList<MenuItem> getOrderedMenuItems() {
        return orderedMenuItems;
    }

    public void setOrderedMenuItems(ArrayList<MenuItem> orderedMenuItems) {
        this.orderedMenuItems = orderedMenuItems;
    }

    public IMenuDAO getDao() {
        return dao;
    }

    public final void setDao(IMenuDAO dao) {
        this.dao = dao;
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
    public static void main(String[] args) throws DataAccessException {
        String[] mItem = {"1", "3"};
        OrderService orderService = new OrderService(new MenuDAO(new DB_MySQL()), mItem);
        List<MenuItem> orderedMenuItems = orderService.getOrderedMenuItems();
        for (MenuItem m : orderedMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
        double subTotal = orderService.getSubTotal();
        double tax = orderService.getTax();
        double total = orderService.getTotal();
        System.out.println(subTotal + "..." + tax + "..." + total);

    }
}