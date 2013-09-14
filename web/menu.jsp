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
                <input type="checkbox" name="main" id="main" value="SignatureSteak"/>Signature Steak
                &nbsp;&nbsp;
                <input type="checkbox" name="main" id="main" value="Lobster"/>Lobster
                <h3>Salad Choice: </h3>
                <input type="checkbox" name="salad" id="salad" value="HouseSalad"/>House Salad
                &nbsp;&nbsp;
                <input type="checkbox" name="salad" id="salad" value="GreekSalad"/>Greek Salad
                <h3>Side Choice: </h3>
                <input type="checkbox" name="side" id="side" value="BakedPotato"/>Baked Potato
                &nbsp;&nbsp;
                <input type="checkbox" name="side" id="side" value="RicePilaf"/>Rice Pilaf
                <h3>Beverage Choice: </h3>
                <input type="checkbox" name="drink" id="drink" value="SoftDrink"/>Soft Drink
                &nbsp;&nbsp;
                <input type="checkbox" name="drink" id="drink" value="AlcoholicBeverage"/>Alcoholic Beverage
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
