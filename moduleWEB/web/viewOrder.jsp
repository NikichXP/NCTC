<%--
  Created by IntelliJ IDEA.
  User: Ubuntu
  Date: 21.05.2015
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Test</title>
    <link href="css/order-style.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/jquery.maskedinput.js"></script>
    <script src="js/tariff.js"></script>
    <script src="//api-maps.yandex.ru/2.1/?lang=en-US" type="text/javascript"></script>
    <script src="js/setuserdata.js" type="text/javascript"></script>
    <script src="js/mapSearchByAddress.js" type="text/javascript"></script>
    <script src="js/createorder.js"></script>
    <script src="js/view-order.js"></script>
</head>
<body>
<div id="tariffs" hidden></div>

<div id="container">
    <div id="order-form">
        <div id="contacts">
            <input id="contactName" type="text" disabled/>
            <input id="contactPhone" type="text" disabled/>
        </div>
        <div id="importantInfo">
            <input id="requestedSeatsCount" type="text" disabled/>
            <input id="fromAddress" type="text" disabled/>
            <input type="text" id="fromX" hidden/>
            <input type="text" id="fromY" hidden/>
            <input id="toAddress0" type="text" disabled/>
            <input type="text" id="toX0" hidden/>
            <input type="text" id="toY0" hidden/>
            <input type="text" id="distance0" disabled/>
            <input type="button" id="addressAdder" value="Add" onclick="createToAddress();" disabled><br>
        </div>
        <div id="timeOption">
            <input id="asSoonAsPossible" type="checkbox" onchange="showOrHideDatePicker()" disabled>As soon as
            possible</input>
            <input id="timeRequested" type="text" disabled style="visibility: visible"/>
        </div>
        <div id="genderOption">
            Driver sex:
            <div id="musicTypeBox">
                <input type="radio" checked name="sex" data-value="Male" disabled>Male</input>
                <input type="radio" name="sex" data-value="Any" disabled>Any</input>
                <input type="radio" name="sex" data-value="Female" disabled>Female</input>
            </div>
        </div>

        <div id="carClassBox">
            Car class:
            <div id="carClass"></div>
        </div>

        <div id="musicTypesBox">
            Music type:
            <input type="text" id="music" disabled/>
        </div>
        <!--Container for Order Options-->
        <div class="optionsContainer">
            <div class="option">
                <input id="smokingFriendly" type="checkbox" disabled/>Smoker</input>
            </div>
            <div class="option">
                <input id="animalFriendly" type="checkbox" disabled/>Suitable for animal transportation</input>
            </div>
            <div class="option">
                <input id="wifi" type="checkbox" disabled/>Wi-Fi</input>
            </div>
            <div class="option">
                <input id="airConditioner" type="checkbox" disabled/>Air conditioner</input>
            </div>
        </div>
        <div class="summaryInfoContainer">
            <div class="summarySection">
                <div>Total multiplier:</div>
                <input disabled type="text" id="totalMultiplier"/>
            </div>
            <div class="summarySection">
                <div>Total length:</div>
                <input disabled type="text" id="totalLength"/>
            </div>
            <div class="summarySection">
                <div>Total price:</div>
                <input disabled type="text" id="totalPrice"/>
            </div>
        </div>

        <div class="commentaryContainer">
            <textarea id="customerPreCreateComment" placeholder="Additional comments to your order" disabled></textarea>
        </div>

        <div class="submitButtonContainer">
            <input id="basic-order-submit" type="button" value="Ok" disabled/>
        </div>
    </div>
    <div id="map"></div>
</div>
</body>
</html>