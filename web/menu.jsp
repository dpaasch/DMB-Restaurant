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
    </head>
    <body bgcolor="${backgroundColor}" >
    <center>
        <form name="menu" id="menu" action="RestaurantOrderController" method="POST">
            <h2>Please make your menu selections</h2>
            <img src="images/grilled-steak.jpg">
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
                <h3>
                    <td>&nbsp;
                        <input type="checkbox" name="orderedItems[]" value="<%= id%>"/> 
                        &nbsp; <%= itemName%>
                    </td>          
                    <td><%= itemPrice%></td>
                </h3>
                </tr>
                <%
                    }
                %>
            </table>
            <br/>
            <input type="submit" name="submit" id="submit" value="Submit Order"/>
            <br/>
            <h4><a href="index.jsp">The Blue Bistro Home</a><br/>
                <a href="mailto:${email}"> Contact Us </a></h4>
        </form>       
    </center>
</body>
</html>

