package controller;

import java.io.IOException;
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

        try {
            String action = request.getParameter("action");
            List<MenuItem> updatedMenuItems = null;
            MenuService ms = new MenuService();
            MenuItem menuItem;

            // delete functionality handled within this section
            if (action.equals("Delete Item")) {
                ms.deleteMenuItem(request.getParameterValues("menuItem"));
                updatedMenuItems = ms.getAllMenuItems();
                request.setAttribute("menuItems", updatedMenuItems);

                RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
                view.forward(request, response);
                // insert & update functionality handled here
            } else if (action.equals("Add/Edit Item")) {
                // forward to the update page
                RequestDispatcher view = request.getRequestDispatcher(UPDATE_PAGE);
                view.forward(request, response);

            } else if (action.equals("Submit Update")) {
                String itemId = request.getParameter("itemId");
                // need to convert itemId into a Long object
                Long objItemId = (itemId.equals("null") || itemId.length() == 0) ? null : new Long(itemId);
                String itemName = request.getParameter("itemName");
                double itemPrice = Double.valueOf(request.getParameter("itemPrice"));
                // insert
                if (objItemId == null) {
                    menuItem = new MenuItem(objItemId, itemName, itemPrice);
                    ms.saveMenuItem(menuItem);
                    request.setAttribute("menuItems", updatedMenuItems);
//                } else {
//                    String[] menuIds = request.getParameterValues("menuItem");
//                    if (menuIds != null) {
//                        menuItem = ms.getMenuItemById(menuIds[0]);
//                        ms.saveMenuItem(menuItem);
//                        request.setAttribute("menuItems", updatedMenuItems);
//                    }

                    updatedMenuItems = ms.getAllMenuItems();
                    request.setAttribute("menuItems", updatedMenuItems);

                    RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
                    view.forward(request, response);
                }
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
