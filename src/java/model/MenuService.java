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

    private MenuDAO dao;

    /**
     * Default MenuService constructor
     */
    public MenuService() {
        DBAccessor db = new DB_MySQL();
        dao = new MenuDAO(db);
    }

    public void saveMenuItem(MenuItem menuItem) throws DataAccessException {
        dao.saveMenuItem(menuItem);
    }

        public void deleteMenuItem(String[] ids) throws DataAccessException {
        for (String s : ids) {
            MenuItem m = dao.getMenuItemById(s);
            dao.deleteMenuItem(m);
        }
    }
        
    public List getAllMenuItems() throws DataAccessException {
        return dao.getAllMenuItems();
    }

    public MenuItem getMenuItemById(String id) throws DataAccessException {
        return dao.getMenuItemById(id);
    }
    
      /**
     * @return the dao
     */
    public MenuDAO getMenuDAO() {
        return dao;
    }
    public void setMenuDAO(MenuDAO dao) {
        this.dao = dao;
    }

// for testing
    public static void main(String[] args) throws DataAccessException, Exception {
        MenuService ms = new MenuService();
        MenuItem menuItem;
        List<MenuItem> allMenuItems = ms.getAllMenuItems();

        // get MENU
        System.out.println(" ... ORIGINAL MENU ... ");
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
        // get by itemId
        String[] orderedMenuItems = {"5"};
        List<MenuItem> mi = new ArrayList<>();
        for (String s : orderedMenuItems) {
            MenuItem m = ms.getMenuItemById(s);
            mi.add(m);
            System.out.println("\nRetrieved menu item by itemId: " 
                    + m.getItemName() + "(" + m.getId() + ") ... " + m.getItemPrice());
        }
        // delete
        String[] ids = {"8"};
        List<MenuItem> miDeletable = new ArrayList<>();
        for (String s : ids) {
            menuItem = ms.getMenuItemById(s);
            miDeletable.add(menuItem);
        ms.deleteMenuItem(ids);
        System.out.println("\nDeleted item: " + menuItem.getItemName() 
                + " ... " + menuItem.getItemPrice());
        }           
        // insert
        menuItem = new MenuItem(null, "Mixed Drink", 7.25);
        ms.saveMenuItem(menuItem);
        System.out.println("\nInserted: " + menuItem.getItemName() + " @ $" 
                + menuItem.getItemPrice());

        // update
        MenuItem miUpdateable = ms.getMenuItemById("6");
        if (miUpdateable != null) {
        miUpdateable.setItemPrice(4.75);
        ms.saveMenuItem(miUpdateable);
        System.out.println("\nUpdated: " + miUpdateable.getItemName() + " ( " 
                    + Long.valueOf(miUpdateable.getId()) + ") ... " + miUpdateable.getItemPrice());
        }
        //get MENU
        System.out.println("\n ... CURRENT MENU ... ");
        allMenuItems = ms.getAllMenuItems();
        for (MenuItem m : allMenuItems) {
            System.out.println(m.getItemName() + " ... " + m.getItemPrice());
        }
    }
}