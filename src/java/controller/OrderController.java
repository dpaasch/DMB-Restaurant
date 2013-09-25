package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuDAO;
import model.MenuItem;
import model.OrderService;

/**
 *
 * @author Dawn Bykowski, dpaasch@mu.wctc.edu
 * @version 1.00
 */
public class OrderController extends HttpServlet {

    private final static String RESULT_PAGE = "/order.jsp";


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
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String[] menuItems = request.getParameterValues("menuItems[]");
            
            OrderService orderService = new OrderService(new MenuDAO(), menuItems);
            ArrayList<MenuItem> orderedMenuItems = orderService.getOrderedMenuItems();
            double subTotal = orderService.getSubTotal();
            double tax = orderService.getTax();
            double total = orderService.getTotal();
            double tip = orderService.getTip();
            double grandTotal = orderService.getGrandTotal();

            request.setAttribute("orderedMenuItems", orderedMenuItems);
            request.setAttribute("subTotal", subTotal);
            request.setAttribute("tax", tax);
            request.setAttribute("total", total);
            request.setAttribute("tip", tip);
            request.setAttribute("grandTotal", grandTotal);

            // This object lets you forward both the request and response
            // objects to a destination page
            RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
            view.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
