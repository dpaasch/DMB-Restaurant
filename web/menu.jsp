<%-- 
    Document   : menu
    Created on : Sep 11, 2013, 9:06:59 PM
    Author     : Dawn Bykowski
--%>

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
        <form name="menu" id="menu" action="RestaurantController" method="POST"> 
            <!-- onsubmit="return(validateMenuForm());" -->
            <h1>Please make your menu selections</h1>
            <center>
                <br/>
                <h3>Main Course Choice: </h3>  
                <input type="checkbox" name="menuItem" id="menuItem" value="Signature Steak"/>Signature Steak
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="Lobster"/>Lobster
                <h3>Salad Choice: </h3>
                <input type="checkbox" name="menuItem" id="menuItem" value="House Salad"/>House Salad
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="Greek Salad"/>Greek Salad
                <h3>Side Choice: </h3>
                <input type="checkbox" name="menuItem" id="menuItem" value="Baked Potato"/>Baked Potato
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="ricePilaf"/>Rice Pilaf
                <h3>Beverage Choice: </h3>
                <input type="checkbox" name="menuItem" id="menuItem" value="soda"/>Soft Drink
                &nbsp;&nbsp;
                <input type="checkbox" name="menuItem" id="menuItem" value="alcohol"/>Alcoholic Beverage
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
