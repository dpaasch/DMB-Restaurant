<%-- 
    Document   : index - Bykowski Bistro
    Created on : Sep 11, 2013, 8:38:52 PM
    Author     : Dawn Bykowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object obj = session.getAttribute("background");
    String backgroundColor = (obj == null) ? "#66CDAA" : obj.toString();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Bykowski Bistro Home</title>
    </head>
    <body bgcolor="<%= backgroundColor %> ">
    <center>
        <h1>Welcome to Bykowski Bistro!</h1><br/>    
        <img src="images/bistro.jpg"><br/><br/>
        <h4>
            <center>
                <button onclick="location.href = 'RestaurantMenuController'" 
                        name="placeOrder" id="placeOrder">Place Order
                </button>
                <button onclick="location.href = 'RestaurantAdminController'" 
                        name="administer" id="administer">Administer Bykowski Bistro</button>
            </center>
        </h4>
    </center> 

</body>
</html>