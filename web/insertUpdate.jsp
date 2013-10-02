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
        <form name="insertUpdate" id="insertUpdate" action="RestaurantDBController" method="POST" />
        <table>   
            <tr>
                <td><input type="hidden" name="itemId" value="${itemId}"/></td>
                <td>Item Name: <input type="text" name="itemName" value="${itemName}"/></td>
                <td>Item Price: <input type="text" name="itemPrice" value="${itemPrice}"/></td>
            </tr>
        </table>
        <br> 
        <input type="submit" name="action" id="action" value="Submit Update"/>
    </form>
</center>
<br/> <a href="index.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>