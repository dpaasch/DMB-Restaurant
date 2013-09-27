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
        <h1>Welcome to The Blue Bistro!</h1>
        <br/>    
        <img src="images/bistro.jpg">
        <br/>
        <button onclick="location.href = 'RestaurantMenuController'" name="placeOrder" 
                id="placeOrder"/>
        <p>Place Order</p>
        <br/>
    </button>
    <br/>
    <br/>
</center> 
    
    <br/> 
    <br/> <!--<a href="RestaurantAdminController" style="font-style: italic">Administer Blue Bistro</a> -->
</body>
</html>