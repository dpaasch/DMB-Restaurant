<%-- 
    Document   : delete
    Created on : Sep 28, 2013, 5:57:58 PM
    Author     : tim
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.MenuItem"%>
<%@page import="model.MenuItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> </h1>
            <%
            ArrayList<MenuItem> updateddMenuItems = null;
            Object updatedItems = request.getAttribute("updateddMenuItems");
            if (updatedItems != null) {
                updateddMenuItems = (ArrayList<MenuItem>) updatedItems;
            }
        %>
        <%
            for (MenuItem m : updateddMenuItems) {%>
        <h3> 
            <%= m.getItemName() + " ... " + m.getItemPrice()%>
            <% }%>
        </h3>
     
    </body>
</html>
