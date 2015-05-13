<%--
  Created by IntelliJ IDEA.
  User: Ubuntu
  Date: 11.05.2015
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Test</title>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.maskedinput.js"></script>
</head>
<body onload="getMusicType()">
<form id="order-form">
    <input type="text" id="contactName" placeholder="Contact name"/><br>
    <input type="text" id="contactPhone" placeholder="Contact phone"/><br>
    <input type="text" id="requestedSeatsCount" placeholder="Requested seats count"/><br>
    <input type="text" id="type" hidden/>
    <input type="text" id="fromAddress" placeholder="From address"/><br>
    <input type="text" id="fromX" hidden value = "1"/>
    <input type="text" id="fromY" hidden value = "1"/>
    <input type="text" id="toAddress0" placeholder="To address"/><br>
    <input type="text" id="toX0" hidden value = "2"/>
    <input type="text" id="toY0" hidden value = "2"/>
    <input onchange="showOrHideDatePicker()" type="checkbox" id="asSoonAsPossible">asSoonAsPossible</input><br>
    <input id="timeRequested" style="visibility: visible" type="text"/><br>

    Driver sex:<br>
    <input type="radio" checked name="sex" data-value="Male">Male</input><br>
    <input type="radio" name="sex" data-value="Any">Any</input><br>
    <input type="radio" name="sex" data-value="Female">Female</input><br>

    Car class:<br>
    <input type="radio" checked name="carClass" data-value="Basic">Regular</input><br><%--TODO get from DB--%>
    <input type="radio" name="carClass" data-value="Premium">Elite</input><br>

    <p id="musicTypes"></p>

    <input type="checkbox" id="smokingFriendly">smokingFriendly</input><br>
    <input type="checkbox" id="animalFriendly">animalFriendly</input><br>
    <input type="checkbox" id="wifi">wifi</input><br>
    <input type="checkbox" id="airConditioner">airConditioner</input><br>
    <textarea id="customerPreCreateComment" placeholder="Additional comments to your order"></textarea><br>
    Total multiplier:<input disabled type="text" id="totalMultiplier"/><br>
    Total length:&nbsp<input disabled type="text" id="totalLength"/><br>
    Total price:&nbsp<input disabled type="text" id="totalPrice"/><br>

    <input type="button" id="basic-order-submit" value="Create test Order."/>
</form>
<br><br>
<script>
    jQuery(function($){
        $("#timeRequested").mask("99/99/9999 99:99",{placeholder:"dd/mm/yyyy hh:mm"});
    });
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/createorder.js"></script>
</body>
</html>