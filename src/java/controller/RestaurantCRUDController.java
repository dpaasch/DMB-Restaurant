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
 * @author tim
 */
public class RestaurantCRUDController extends HttpServlet {

    private final static String RESULT_PAGE = "/admin.jsp";

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
            // delete 
            String d = request.getParameter("delete");
            if (d.equalsIgnoreCase("delete")) {
                String[] mItems = request.getParameterValues("menuItems[]");
                MenuService ms = new MenuService();
                try {
                    ms.deleteMenuItem(mItems);
                    List<MenuItem> updatedMenuItems = ms.getAllMenuItems();
                    request.setAttribute("menuItems", updatedMenuItems);
                } catch (DataAccessException e) {
                    System.out.println(e.getLocalizedMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
                // This object lets you forward both the request and response
                // objects to a destination page
                RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
                view.forward(request, response);
            }
            String edit = request.getParameter("edit");
            if (edit.equalsIgnoreCase("edit")) {
                String[] menuIds = request.getParameter("id");
                MenuService ms = new MenuService();
                try {
                    MenuItem menuItem = ms.getMenuItemById(request.getParameter(id));
                    if (menuItem != null) {
                        ms.updateMenuItem(menuItem);
                        request.setAttribute("menuItem", menuItem);
                    }
                } catch (DataAccessException e) {
                    System.out.println(e.getLocalizedMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
                // This object lets you forward both the request and response
                // objects to a destination page
                RequestDispatcher view = request.getRequestDispatcher("/insertUpdate.jsp");
                view.forward(request, response);
            }
        } catch (Exception e) {
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
            Logger.getLogger(RestaurantCRUDController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RestaurantCRUDController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RestaurantCRUDController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RestaurantCRUDController.class.getName()).log(Level.SEVERE, null, ex);
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
