a<%-- 
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
        <h1>Blue Bistro Menu Administration</h1>
        <br/>
        <p> Select the menu item you wish to modify</p>
        <form name="admin" id="admin" action="RestaurantCRUDController" method="POST">
            <table border="2">
                <h4>
                    <tr><th>&nbsp;</th> <th>Menu Item</th> <th>Menu Price</th> <th colspan="3">Menu Action</th>  
                            <%
                                List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
                                int i = 0;

                                for (MenuItem menuItem : menuItems) {
                                    Long id = menuItem.getId();
                                
                                    out.println("<tr>");
                                    out.println("<td><input type='checkbox' name='menuItem" + i + "'/></td>"
                                            + "<td>" + menuItem.getItemName() + "</td>"
                                            + "<td>$" + menuItem.getItemPrice() + "</td>"
                                            + "<td><input type='submit' name='edit' id='edit' value='Edit' action='RestaurantCrudController?formAction=edit&id=" + menuItem.getId() + "'/></td>"
                                            + "<td><input type='submit' name='delete' id='delete' value ='Delete' action='RestaurantCrudController?formAction=delete&id=" + menuItem.getId() + "'/></td>");
                                         
                                    out.println("</tr>");
                                    i++;
                                }
                            %>   
                </h4>
            </table>
        </form>
    <br/> 
    <br/> <a href="index.jsp" style="font-style: italic">The Blue Bistro Home</a>
</body>
</html>


