<%-- 
    Document   : menu
    Created on : Sep 11, 2013, 9:06:59 PM
    Author     : Dawn Bykowski
--%>

<%@page import="model.MenuItem"%>
<%@page import="model.MenuItem"%>
<%@page import="java.util.List"%>
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
        <form name="menu" id="menu" action="summary.do" method="POST">
            <h1>Please make your menu selections</h1>
            <center>
                <br/>
                <% 
                    List<MenuItem> menuItems = (List<MenuItem>)request.getAttribute("menuItem");
                for (MenuItem m : menuItems) {
                    %>
                    <input type="checkbox" name="menuItem" value="<%= m.getItemName() %>"/>
                           <%= m.getItemPrice() %><br/>
                           
                <% } %>
                          
                 <br/>
                    <br/>
                    
                                
                <br/>
                <br/>
                <input type="submit" name="submit" id="submit" value="Submit Order"/>
            </center>
        </form>
    </center>
    <br/> 
    <br/> <a href="home.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>
