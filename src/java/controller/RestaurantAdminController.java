package controller;

import db.accessor.DBConnector;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataAccessException;
import model.MenuItem;
import model.MenuService;

/**
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 */
public class RestaurantAdminController extends HttpServlet {

    private final static String RESULT_PAGE = "/admin.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataAccessException {  
        // app initialization parameters
        String email = this.getServletContext().getInitParameter("email");
        request.setAttribute("email", email);
        // create session
        String backgroundColor = 
                this.getServletContext().getInitParameter("backgroundColor");     
        HttpSession session = request.getSession();
        session.setAttribute("backgroundColor", backgroundColor);

        // servlet initialization parameter
        String driverClassName = 
                this.getServletConfig().getInitParameter("driverClassName");
        String url = this.getServletConfig().getInitParameter("url");
        String userName = this.getServletConfig().getInitParameter("userName");
        String password = this.getServletConfig().getInitParameter("password");
        
        DBConnector dbConnector = new DBConnector(driverClassName, url, 
                userName, password);

        // Retrieve the menu to display on the main admin page
        MenuService ms = new MenuService(dbConnector);
        List<MenuItem> menuItems = ms.getAllMenuItems();
        request.setAttribute("menuItems", menuItems);


        // This object lets you forward both the request and response
        // objects to a destination page
        RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
        view.forward(request, response);
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
            Logger.getLogger(RestaurantMenuController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RestaurantMenuController.class.getName()).log(Level.SEVERE, null, ex);
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
