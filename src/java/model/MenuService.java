package model;

import db.accessor.DB_MySQL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MenuService class is responsible for communicating with the mock database
 * (MenuDatabase) to retrieve the menu items.
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 */
public class MenuService {

    private IMenuDAO dao;

    /**
     * Default MenuService constructor
     */
    public MenuService() {
        dao = new MenuDAO(new DB_MySQL());
    }

    public List getAllMenuItems() throws DataAccessException {
        return dao.getAllMenuItems();
    }

    public MenuItem getMenuItemById(String id) throws DataAccessException {
        return dao.getMenuItemById(id);
    }
    
    public void deleteMenuItems(String[] menuItems) throws SQLException, Exception {
        for (String s : menuItems) {
            MenuItem menuItem = dao.getMenuItemById(s);
            dao.deleteMenuItems(menuItem);
        }
    }
    
    public void saveMenuItem(MenuItem menuItem) throws DataAccessException {
        dao.saveMenuItem(menuItem);
    
    }

    // for testing
    public static void main(String[] args) throws Exception {
        MenuService ms = new MenuService();
        List<MenuItem> allMenuItems = ms.getAllMenuItems();
        System.out.println(" getAllMenuItems() ... ");
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }

        String[] orderedMenuItems = {"1", "2", "3"};
        List<MenuItem> mi = new ArrayList<>();
        System.out.println("\n getMenuItemById() ... ");
        for (String s : orderedMenuItems) {
            MenuItem m = ms.getMenuItemById(s);
            mi.add(m);
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
    }
}
