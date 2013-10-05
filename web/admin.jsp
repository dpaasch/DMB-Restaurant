<%-- 
    Document   : admin
    Created on : Sep 28, 2013, 9:07:45 PM
    Author     : tim
--%>

<%@page import="model.MenuItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Blue Bistro Menu Administration</title>
    </head>
    <body>
    <center>
        <form name="admin" id="admin"  action="RestaurantDBController" method="POST">
            <h2>Menu Administration</h2>
                            <img src="images/grilled-steak.jpg">
            <p> Select the menu item you wish to delete and then choose 
                "Delete Item." </p><p>To add a new menu item, or update an existing 
                item, choose "Add/Edit Item."</p>
            <table>
                <h4>
                    <%
                        List<MenuItem> menuItems = null;
                        Object menu = request.getAttribute("menuItems");
                        if (menu != null) {
                            menuItems = (List<MenuItem>) menu;
                        }

                        for (MenuItem menuItem : menuItems) {
                            Long itemId = menuItem.getItemId();
                            String itemName = menuItem.getItemName();
                            double itemPrice = menuItem.getItemPrice();
                    %>
                    <tr>
                        <td>&nbsp;
                            <input type="checkbox" name="menuItem" id="menuItem" 
                                   value="<%= itemId%>" /> &nbsp; <%= itemName%>
                        </td>          
                        <td> <%= itemPrice%></td>
                    </tr>

                    <%
                        }
                    %>
                </h4>
            </table>
            <center>
                <input type="submit" name="action" id="action" value="Delete Item" />
                &Tab; &Tab;
                <input type="submit" name="action" id="action" value="Add/Edit Item"/>
            </center>
            <br/>
            <h4><a href="index.jsp">The Blue Bistro Home</a><br/>
                <a href="mailto:${email}"> Contact Us </a></h4>
        </form>       
    </center>
</body>
</html>
