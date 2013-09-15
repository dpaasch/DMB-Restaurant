<%-- 
    Document   : orderSummary - Blue Bistro
    Created on : Sep 11, 2013, 8:38:52 PM
    Author     : Dawn Bykowski
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="css/restaurant.css">
        <title>Restaurant Order Summary</title>
    </head>
    <body>
        <h1>Your Blue Bistro Order Summary</h1>
        <%
            Object lineItems = request.getAttribute("lineItems");
            List<String> lineItemList = new ArrayList<String>();
            if (lineItems != null) {
                lineItemList = (ArrayList<String>) lineItems;
            }
            for (String s : lineItemList) {
        %>
        <h4>
            <%= s %>
            <% } %>
        </h4>
        <div></div>

        <br/> 
        <br/> <a href="menu.jsp" style="font-style: italic">Place another order</a>
        <br/>
        <br/> <a href="home.jsp" style="font-style: italic">The Blue Bistro Home</a>
    </body>
</html>
