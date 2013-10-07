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
        <title>Bykowski Bistro Menu Updates</title>
    </head>
    <body bgcolor="${backgroundColor}">
        <h1>Add/Edit Menu Item</h1>
                <img src="images/grilled-steak.jpg">
    <center>
        <form name="update" id="update" action="RestaurantDBController" method="POST" />
        <p style="text-align: center">Enter the item name and item price that you wish to add/edit</p>
        <h3>
            <input type="hidden" name="itemId" id="itemId" value="${menuItem.itemId}" /> 
            Item Name: <input type="text" name="itemName" id="itemName" value="${menuItem.itemName}" /> 
            Item Price: <input type="text" name="itemPrice" id="itemPrice" value="${menuItem.itemPrice}" />
        </h3>

        <br> 
        <input type="submit" name="action" id="action" value="Submit Update"/>
            <br/>
            <h4><a href="index.jsp">The Bykowski Bistro Home</a><br/>
                <a href="mailto:${email}"> Contact Us </a></h4>
        </form>       
</center>
</body>
</html>