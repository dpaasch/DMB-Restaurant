<%-- 
    Document   : insertUpdate
    Created on : Sep 29, 2013, 10:51:23 AM
    Author     : Dawn Bykowski
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.MenuItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Blue Bistro Menu Updates</title>
    </head>
    <body>
        <h1>Add/Edit Menu Item</h1>
    <center>
        <form name="update" id="update" action="RestaurantDBController" method="POST" />
        <p style="text-align: center">Enter the item name and item price that you wish to add/edit</p>

        <%
            Object itemId = request.getAttribute("itemId");
            Object itemName = request.getAttribute("itemName");
            Object itemPrice = request.getAttribute("itemPrice");
        %>
        <h3>
            <input type="hidden" name="itemId" id="itemId" value ="${itemId}"/> 
            Item Name: <input type="text" name="itemName" id="itemName" value ="${itemName}" /> 
            Item Price: <input type="text" name="itemPrice" id="itemPrice" value="${itemPrice}"/>
        </h3>
        <br> 
        <input type="submit" name="action" id="action" value="Submit Update"/>
    </form>
</center>
<br/> <a href="index.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>