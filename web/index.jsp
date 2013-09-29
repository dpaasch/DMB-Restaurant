<%-- 
    Document   : index - Blue Bistro
    Created on : Sep 11, 2013, 8:38:52 PM
    Author     : Dawn Bykowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Blue Bistro Home</title>
    </head>
    <body>
    <center>
        <h1>Welcome to The Blue Bistro!</h1><br/>    
        <img src="images/bistro.jpg"><br/><br/>
        <button onclick="location.href = 'RestaurantMenuController'" name="placeOrder" id="placeOrder">
            <h4>Place Order</h4>
        </button>
        &Tab;
        <button onclick="location.href = 'RestaurantAdminController'" name="administerMenu" id="administerMenu">
            <h4>Administer Menu</h4>
        </button>
        <br/><br/>
    </center> 
    <!-- <a href="RestaurantAdminController" style="font-style: italic">Administer Blue Bistro</a> -->
</body>
</html>