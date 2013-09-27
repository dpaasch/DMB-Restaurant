<%-- 
    Document   : menu
    Created on : Sep 11, 2013, 9:06:59 PM
    Author     : Dawn Bykowski
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="model.MenuItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Blue Bistro Menu</title>
        <script type="text/javascript"></script>
    </head>
    <body>
    <center>
        <form name="menu" id="menu" action="RestaurantOrderController" method="POST">
            <h1>Please make your menu selections</h1>
            <center>
                <br/>
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
                    <h3>
                        <input type="checkbox" name="orderedItems[]" value="<%= id%>" /><td><%= itemName%></td>
                    </h3>
                    <%
                        }
                    %>
                    <br/>
                    <input type="submit" name="submit" id="submit" value="Submit Order"/>
            </center>
        </form>
    </center>

    <br/> 
    <br/> <a href="index.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>

