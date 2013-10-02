package model;

import db.accessor.DBAccessor;
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
        DBAccessor db = new DB_MySQL();
        dao = new MenuDAO(db);
    }

//    public void saveMenuItem(MenuItem menuItem) throws DataAccessException {
//        dao.saveMenuItem(menuItem);
//    }
//
//        public void deleteMenuItem(String[] menuIds) throws DataAccessException {
//        for (String s : menuIds) {
//            MenuItem m = dao.getMenuItemById(s);
//            System.out.println("Menu Item id: " + s);
//            dao.deleteMenuItem(m);
//        }
//    }
        
    public List<MenuItem> getAllMenuItems() throws DataAccessException {
        return dao.getAllMenuItems();
    }
//
//    public MenuItem getMenuItemById(String id) throws DataAccessException {
//        return dao.getMenuItemById(id);
//    }

// for testing
    public static void main(String[] args) throws DataAccessException, Exception {
        MenuDAO dao = new MenuDAO(new DB_MySQL());
        MenuItem menuItem;
        List<MenuItem> allMenuItems = dao.getAllMenuItems();

        // get MENU
        System.out.println(" ... ORIGINAL MENU ... ");
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
        // get by itemId
        String[] orderedMenuItems = {"5"};
        List<MenuItem> mi = new ArrayList<>();
        for (String s : orderedMenuItems) {
            MenuItem m = dao.getMenuItemById(s);
            mi.add(m);
            System.out.println("\nRetrieved menu item by itemId: " 
                    + m.getItemName() + "(" + m.getId() + ") ... " + m.getItemPrice());
        }
        // delete
        MenuItem miDeletable = dao.getMenuItemById("8");
        dao.deleteMenuItem(miDeletable);
        System.out.println("\nDeleted item: " + miDeletable.getItemName() 
                + " ... " + miDeletable.getItemPrice());

        // insert
        menuItem = new MenuItem(null, "Mixed Drink", 7.25);
        dao.saveMenuItem(menuItem);
        System.out.println("\nInserted: " + menuItem.getItemName() + " @ $" 
                + menuItem.getItemPrice());

        // update
        MenuItem miUpdateable = dao.getMenuItemById("6");
        if (miUpdateable != null) {
        miUpdateable.setItemPrice(4.75);
        dao.saveMenuItem(miUpdateable);
        System.out.println("\nUpdated: " + miUpdateable.getItemName() + " ( " 
                    + Long.valueOf(miUpdateable.getId()) + ") ... " + miUpdateable.getItemPrice());
        }
        //get MENU
        System.out.println(
                "\n ... CURRENT MENU ... ");
        allMenuItems = dao.getAllMenuItems();
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
    }
}