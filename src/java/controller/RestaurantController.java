package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OrderModel;

/**
 *
 * @author Dawn Bykowski
 */
public class RestaurantController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<String> menuItems = new ArrayList<String>();
//        String[] mItemArray = new ArrayList<String>();
        if (request.getParameterValues("entree") != null
                || request.getParameterValues("salad") != null
                || request.getParameterValues("side") != null
                || request.getParameterValues("drink") != null) {
            menuItems.add(request.getParameter("entree"));
            menuItems.add(request.getParameter("salad"));
            menuItems.add(request.getParameter("side"));
            menuItems.add(request.getParameter("drink"));
        }

//            OrderModel order = new OrderModel(menuItems);
//            List<String> lineItems = order.getLineItems();
//            double subTotal = order.getSubTotal();
//            double tax = order.getTax();
//            double total = order.getTotal();
//            double tip = order.getTip();
//            double grandTotal = order.getGrandTotal();
//
//            request.setAttribute("menuItems", lineItems);
//            request.setAttribute("subTotal", subTotal);
//            request.setAttribute("tax", tax);
//            request.setAttribute("total", total);
//            request.setAttribute("tip", tip);
//            request.setAttribute("grandTotal", grandTotal);

            // This object lets you forward both the request and response
            // objects to a destination page
            RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
            view.forward(request, response);
//        } finally {            
//            out.close();

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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response
        )
            throws ServletException
        , IOException {
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response
        )
            throws ServletException
        , IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>
    
}