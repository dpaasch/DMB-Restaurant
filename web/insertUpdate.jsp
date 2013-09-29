<%-- 
    Document   : insertUpdate
    Created on : Sep 29, 2013, 10:51:23 AM
    Author     : Dawn Bykowski
--%>

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
        <form name="insertUpdate" id="insertUpdate" action="RestaurantUpdateController" method="POST" />
        <table>
            <tr>
                <td>Item Name: <input type="text" name="itemName" value="${item.itemName}"/></td>
                <td>Item Price: <input type="text" name="itemPrice" value="${item.itemPrice}"/></td>
            </tr>
        </table>
            <br>                                <input type="submit" name="submit" id="submit" value="Submit"/>
    </form>
</center>

<br/> 
<br/> <a href="index.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>