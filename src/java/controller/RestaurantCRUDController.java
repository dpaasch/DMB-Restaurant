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
        String[] menuItems = request.getParameterValues("menuItems[]");
        String delete = request.getParameter("delete");
        String edit = request.getParameter("edit");
        MenuService ms = new MenuService();

        if (delete.equals("Delete")) {
            try {
                ms.deleteMenuItem(menuItems);
                List<MenuItem> updatedMenu = ms.getAllMenuItems();
                request.setAttribute("menuItems", updatedMenu);

            } catch (DataAccessException e) {
                System.out.println(e.getLocalizedMessage());
            }

            // This object lets you forward both the request and response
            // objects to a destination page
            RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
            view.forward(request, response);
        } else if (edit.equals("Edit")){
            String[] menuIds = request.getParameterValues("menuItems[]");
            MenuItem menuItem = ms.getMenuItemById(menuIds[0]);
            request.setAttribute("menuItem", menuItem);
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
