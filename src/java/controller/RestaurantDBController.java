package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataAccessException;
import model.MenuItem;
import model.MenuService;

/**
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 */
public class RestaurantDBController extends HttpServlet {

    private final static String RESULT_PAGE = "/admin.jsp",
            UPDATE_PAGE = "/insertUpdate.jsp";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataAccessException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        String color = this.getServletContext().getInitParameter("color");
        request.setAttribute("color", color);

        try {
            String action = request.getParameter("action");
            List<MenuItem> updatedMenuItems = null;
            MenuService ms = new MenuService();

            // delete functionality handled within this section
            if (action.equals("Delete Item")) {
                ms.deleteMenuItem(request.getParameterValues("menuItem"));
                updatedMenuItems = ms.getAllMenuItems();
                request.setAttribute("menuItems", updatedMenuItems);

                RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
                view.forward(request, response);
                // insert & update functionality handled here
            } else if (action.equals("Add/Edit Item")) {

                String[] idValues = request.getParameterValues("menuItem");
                if (idValues == null) {
                    MenuItem menuItem = new MenuItem();
                    request.setAttribute("menuItem", menuItem);
                } else {

                        MenuItem menuItem = ms.getMenuItemById(idValues[0]);
//                        System.out.println("\nRetrieved menu item by itemId: "
//                                + menuItem.getItemName() + "(" + menuItem.getItemId()
//                                + ") ... " + menuItem.getItemPrice());
                        request.setAttribute("menuItem", menuItem);

                }                
                        // forward to the update page
                        RequestDispatcher view = request.getRequestDispatcher(UPDATE_PAGE);
                        view.forward(request, response);

            } else if (action.equals("Submit Update")) {
                String itemId = request.getParameter("itemId");
                System.out.println(itemId);
                String itemName = request.getParameter("itemName");
                System.out.println(itemName);
                String itemPrice = request.getParameter("itemPrice");
                System.out.println(itemPrice);
                // need to convert itemId into a Long object
                Long objItemId = (itemId == null || itemId.length() == 0L) ? null : new Long(itemId);
                MenuItem menuItem = new MenuItem(objItemId, itemName, Double.valueOf(itemPrice));
                
                try {

                    ms.saveMenuItem(menuItem);
                    updatedMenuItems = ms.getAllMenuItems();

                } catch (DataAccessException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                request.setAttribute("menuItems", updatedMenuItems);

                RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
                view.forward(request, response);
            }

        } catch (DataAccessException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);


        } catch (DataAccessException ex) {
            Logger.getLogger(RestaurantDBController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RestaurantDBController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);


        } catch (DataAccessException ex) {
            Logger.getLogger(RestaurantDBController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RestaurantDBController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
