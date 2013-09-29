<%-- 
    Document   : insertUpdate
    Created on : Sep 29, 2013, 10:51:23 AM
    Author     : Dawn Bykowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Blue Bistro Menu Updates</title>
    </head>
    <body>
        <h1>Edit Menu Item</h1>
        <form name="insertUpdate" id="insertUpdate" action="RestaurantCRUDController" method="POST" />
    <center>
        <table border="1">
            <tr>
                <td>Item Name: <input type="text" name="itemName" value="${item.itemName}"/></td>
                <td>Item Price: <input type="text" name="itemPrice" value="${item.itemPrice}"/></td>
            </tr>
        </table>
    </center>
</form>
<br/>
</body>
</html>
