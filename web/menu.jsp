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
        <title>Menu</title>
        <script type="text/javascript"></script>
    </head>
    <body>
    <center>
        <form name="menu" id="menu" action="RestaurantMenuController" method="POST">
            <h1>Please make your menu selections</h1>
            <!-- <h3>You may choose only one item from each category.</h3> -->
            <center>
                <br/>
                <!-- <h3>Entree Choice: </h3>  
                <input type="checkbox" name="menuItem" id="menuItem" value="1"/>Signature Steak
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="2"/>Lobster
                <h3>Salad Choice: </h3>
                <input type="checkbox" name="menuItem" id="menuItem" value="3"/>House Salad
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="4"/>Greek Salad
                <h3>Side Choice: </h3>
                <input type="checkbox" name="menuItem" id="menuItem" value="5"/>Baked Potato
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="6"/>Rice Pilaf
                <h3>Beverage Choice: </h3>
                <input type="checkbox" name="menuItem" id="menuItem" value="7"/>Soft Drink
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="8"/>Mixed Drink -->
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
                <input type="checkbox" name="orderedItems[]" value="<%= id%>" /><%= itemName%><br/>
                <br/>
                <%
                    }
                %>
                <br/>
                <input type="submit" name="submit" id="submit" value="Submit Order"/>
            </center>
        </form>
    </center>
    <br/> 
    <br/> <a href="home.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>
