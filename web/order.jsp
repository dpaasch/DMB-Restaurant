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
        <h1>Your Blue Bistro Order</h1>
        <%
            Object lineItems = request.getAttribute("menuItems");
            List<String> itemList = new ArrayList<String>();
            if (lineItems != null) {
                itemList = (ArrayList<String>) lineItems;
            }
            for (String li : itemList) {
        %>
        <h3> <%= li%>
            <% }%>
        </h3>
        <br/>
        <h4> 
            <table>
                <tr>
                    <td> SubTotal: </td>
                    <td> ${subTotal} </td>
                </tr>
                <tr>
                    <td> Tax: </td>
                    <td> ${tax} </td>
                </tr>
                <tr>
                    <td></td>
                    <td><div></div></td>
                </tr>
                <tr>
                    <td> Total: </td>
                    <td> ${total} </td>
                </tr>
            </table>
            <br/>
            <br/>
            <center>
                <input type="submit" name="submit" id="submit" 
                       onclick="location.href = 'menu.jsp';" value="Place another order"/>   
            </center>
            <br/>
            <br/> <a href="home.jsp" style="font-style: italic">The Blue Bistro Home</a>
    </body>
</html>
