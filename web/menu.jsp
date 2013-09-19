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
        <form name="menu" id="menu" action="summary.do" method="POST">
            <h1>Please make your menu selections</h1>
            <!-- <h3>You may choose only one item from each category.</h3> -->
            <center>
                <br/>
                <h3>Entree Choice: </h3>  
                <input type="checkbox" name="entree" id="entree" value="Signature Steak"/>Signature Steak
                &nbsp;&nbsp;
                <input type="checkbox" name="entree" id="entree" value="Lobster"/>Lobster
                <h3>Salad Choice: </h3>
                <input type="checkbox" name="salad" id="salad" value="House Salad"/>House Salad
                &nbsp;&nbsp;
                <input type="checkbox" name="salad" id="salad" value="Greek Salad"/>Greek Salad
                <h3>Side Choice: </h3>
                <input type="checkbox" name="side" id="side" value="Baked Potato"/>Baked Potato
                &nbsp;&nbsp;
                <input type="checkbox" name="side" id="side" value="Rice Pilaf"/>Rice Pilaf
                <h3>Beverage Choice: </h3>
                <input type="checkbox" name="drink" id="drink" value="Soft Drink"/>Soft Drink
                &nbsp;&nbsp;
                <input type="checkbox" name="drink" id="drink" value="Mixed Drink"/>Mixed Drink
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
