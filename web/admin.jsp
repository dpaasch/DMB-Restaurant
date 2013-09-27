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
        <h4>
        <table>
            <tr>
                <td>&nbsp;</td>
                <td>Menu Item</td>
                <td>&nbsp;</td>
                <td>Menu Price</td>
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
        </table>
        </h4>
    </center>
</form>
</center>

</body>
</html>

