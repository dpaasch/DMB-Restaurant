<%-- 
    Document   : orderSummary - Blue Bistro
    Created on : Sep 11, 2013, 8:38:52 PM
    Author     : Dawn Bykowski
--%>

<%@page import="model.MenuItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Blue Bistro Order Summary</title>
    </head>
    <body>
        <h2>Your Blue Bistro Order</h2>
                <img src="images/grilled-steak.jpg">
        <%
            Object subTotal = request.getAttribute("subTotal");
            Object tax = request.getAttribute("tax");
            Object total = request.getAttribute("total");
            Object tip = request.getAttribute("tip");
            Object grandTotal = request.getAttribute("grandTotal");
            ArrayList<MenuItem> orderedMenuItems = null;
            Object orderedItems = request.getAttribute("orderedMenuItems");
            if (orderedItems != null) {
                orderedMenuItems = (ArrayList<MenuItem>) orderedItems;
            }
        %>
        <%
            for (MenuItem m : orderedMenuItems) {%>
        <h3> 
            <%= m.getItemName() + " ... " + m.getItemPrice()%>
            <% }%>
        </h3>
        <br/>
        <table>
            <h4>
                <tr> <td> SubTotal: </td><td> $${subTotal} </td> </tr>
                <tr> <td> WI Tax: </td><td> $${tax} </td> </tr>
                <tr> <td> Total: </td><td> $${total} </td> </tr> 
                <tr> <td> Suggested Gratuity: </td><td> $${tip} </td> </tr> 
                <tr> <td>Grand Total: </td><td> $${grandTotal} </td> </tr> 
            </h4>
        </table>
        <br/>
        <br/>
    <center>
        <button onclick="location.href = 'RestaurantMenuController'" 
                name="placeOrder" id="placeOrder">Place Another Order
        </button>
    </center>
    <br/>
    <h4><a href="index.jsp">The Blue Bistro Home</a><br/>
        <a href="mailto:${email}"> Contact Us </a></h4>   
</body>
</html>

