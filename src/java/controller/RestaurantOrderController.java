package controller;

import db.accessor.DB_MySQL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataAccessException;
import model.MenuDAO;
import model.MenuItem;
import model.OrderService;

/**
 *
 * @author Dawn Bykowski, dpaasch@my.wctc.edu
 * @version 1.00
 *
 */
public class RestaurantOrderController extends HttpServlet {

    private final static String RESULT_PAGE = "/order.jsp";
    double subTotal, tax, tip, total, grandTotal;

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
            throws ServletException, IOException, DataAccessException {
        response.setContentType("text/html;charset=UTF-8");

        String[] orderedItems = request.getParameterValues("orderedItems[]");
//        OrderService os = new OrderService(new MenuDAO(new DB_MySQL()), orderedItems);
//        ArrayList<MenuItem> orderedMenuItems = os.getOrderedMenuItems();
//
//        subTotal = os.getSubTotal();
//        tax = os.getTax();
//        total = os.getTotal();
//        tip = os.getTip();
//        grandTotal = os.getGrandTotal();

//        request.setAttribute("orderedMenuItems", orderedMenuItems);
        request.setAttribute("subTotal", subTotal);
        request.setAttribute("tax", tax);
        request.setAttribute("total", total);
        request.setAttribute("tip", tip);
        request.setAttribute("grandTotal", grandTotal);


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
            Logger.getLogger(RestaurantOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RestaurantOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
