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
        <form name="adminDelete" id="adminDelete" 
              action="RestaurantDBController" method="POST">
            <h1>Menu Administration</h1>
            <p style="text-align: center"> Select the menu item you wish to delete
                and then choose "Delete Item."</p>
            <p style="text-align: center">To add a new menu item, or 
                update an existing item, choose "Add/Edit Item."</p>
            <table>
                <%
                    List<MenuItem> menuItems = null;
                    Object menu = request.getAttribute("menuItems");
                    if (menu != null) {
                        menuItems = (List<MenuItem>) menu;
                    }

                    for (MenuItem menuItem : menuItems) {
                        Long id = menuItem.getItemId();
                        String itemName = menuItem.getItemName();
                        double itemPrice = menuItem.getItemPrice();
                %>
                <tr>
                <h4>
                    <td>
                        <input type="checkbox" name="menuItem" 
                               value="<%= id%>" /> <%= itemName%>
                    </td>          
                    <td> <%= itemPrice%></td>
                    </tr>
                </h4>
                <%
                    }
                %>
                <tr></tr>
            </table>
            <br/>            
            <input type="submit" name="action" id="action" value="Delete Item" />
        </form>
        <form name="adminUpdate" id="adminUpdate" action="insertUpdate.jsp" method="POST">
            <br/>
            <input type="submit" name="action" id="action" value="Add/Edit Item" />
        </form>
    </center>
    <br/> 
    <br/> <a href="index.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>
