<%-- 
    Document   : index - Blue Bistro
    Created on : Sep 11, 2013, 8:38:52 PM
    Author     : Dawn Bykowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%response.sendRedirect("RestaurantMenuController");%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Restaurant Home</title>
    </head>
    <body>
        
    <center>
        <button onclick="location.href = 'menu.jsp';" name="placeOrder" 
                id="placeOrder"/>
        <p>Place Order</p>
        <br/>
    </button>
    <br/>
    <br/>
    <img src="images/bistro.jpg">
</center>
</body>
</html>