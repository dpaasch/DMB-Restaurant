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
        <h1>Blue Bistro Administration</h1><br/><br/>
        <p> Select the menu item you wish to modify and then choose Edit or Delete</p>
        <h4>
            <form name="adminForm" id="adminForm" method="POST" action="Update">
                <table>
                    <tr>
                        <td>&nbsp;</td>
                        <td>Menu Item</td>
                        <td>Menu Price</td>
                        <%
                            List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
                            int i = 0;

                            for (MenuItem menuItem : menuItems) {
                                Long id = menuItem.getId();
                                String itemName = menuItem.getItemName();
                                double itemPrice = menuItem.getItemPrice();
                                out.println("<tr>");
                                out.println("<td><input type='checkbox' name='menuItem' value='${menuItem.Id}" + i + "'/></td>"
                                        + "<td>" + itemName + "</td>"
                                        + "<td>$" + itemPrice + "</td>");
                                out.println("</tr>");
                                i++;
                            }
                        %>       
                        <td></td>
                        <td><input type="submit" name="action" id="action" value="Add/Edit"/></td>
                        <td><input type="submit" name="action" id="action" value="Delete"/></td>
                </table>   
            </form>
        </h4>
    </body>
</html>




