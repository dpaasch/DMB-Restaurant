package model;

import db.accessor.DB_MySQL;
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

    public void deleteMenuItem(String[] menuIds) throws DataAccessException {
        for (String s : menuIds) {
            MenuItem m = dao.getMenuItemById(s);
            System.out.println("Menu Item id: " + s);
            dao.deleteMenuItem(m);
        }
    }

    public void saveMenuItem(MenuItem menuItem) throws DataAccessException {
        dao.saveMenuItem(menuItem);

    }

    // for testing
    public static void main(String[] args) throws Exception {
        MenuService ms = new MenuService();
        MenuItem menuItem;
        List<MenuItem> allMenuItems = ms.getAllMenuItems();

        // get MENU
        System.out.println(" ... ORIGINAL MENU (MenuService class) ... ");
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
        // get by id
        String[] orderedMenuItems = {"5"};
        List<MenuItem> mi = new ArrayList<>();
        for (String s : orderedMenuItems) {
            MenuItem m = ms.getMenuItemById(s);
            mi.add(m);
            System.out.println("\nRetrieved menu item by id: " + m.getItemName() + " ... " + m.getItemPrice());
        }
        // delete
        String[] menuIds = {"10"}; 
        List<MenuItem> deletedMenuItems = new ArrayList<>();
        for (String s : menuIds) {
            menuItem = ms.getMenuItemById(s);
            deletedMenuItems.add(menuItem);
            ms.deleteMenuItem(menuIds);
            System.out.println("\nDeleted item: " + menuItem.getItemName() + " ... " + menuItem.getItemPrice());
        }        
        // insert
        menuItem = new MenuItem(Long.valueOf(0), "Mixed Drink", 7.25);
        ms.saveMenuItem(menuItem);
        System.out.println("\nInserted: " + menuItem.getItemName() + " @$" + menuItem.getItemPrice());
        
        menuItem = new MenuItem(Long.valueOf("6"), "Rice Pilaf", 4.75);
        ms.saveMenuItem(menuItem);
        System.out.println("\nUpdated: " + menuItem.getItemName() + " ( " + menuItem.getId() + ")"
                + " ... " + menuItem.getItemPrice());
        
        // get MENU
        System.out.println(" ... CURRENT MENU ... ");
        allMenuItems = ms.getAllMenuItems();
        for (MenuItem m : allMenuItems) {
            System.out.println("(" + m.getId() + ")" + m.getItemName() + " ... " + m.getItemPrice());
        }
    }
}
