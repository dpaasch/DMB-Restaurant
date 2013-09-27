<%-- 
    Document   : admin - Blue Bistro
    Created on : Sep 25, 2013, 8:04:05 PM
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
        <title>Blue Bistro Administration</title>
    </head>
    <body>
        <h1>Blue Bistro Administration</h1>
        <p> Select the menu item you wish to modify</p>
        <table>
                                <%
                        List<MenuItem> menuItems = null;
                        Object menu = request.getAttribute("menuItems");
                        if (menu != null) {
                            menuItems = (List<MenuItem>) menu;
                        }

                        for (MenuItem menuItem : menuItems) {
                            int id = menuItem.getId();
                            String itemName = menuItem.getItemName();
                    %>
            <tr>
                <td><th>Menu Item</th></td>
                <td><th>Menu Price</th></td>
            </tr>
        </table>

    </body>
</html>

