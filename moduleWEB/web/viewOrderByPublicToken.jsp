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
    <script src="//api-maps.yandex.ru/2.1/?lang=en-US" type="text/javascript"></script>
    <script src="js/mapSearchByAddressWOLocate.js" type="text/javascript"></script>
    <script src="js/view-order-public-token.js"></script>
</head>
<body>

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


            <input type="button" id="addPathPoint" value="" hidden>
        </div>
        <div id="timeOption">
            Time Requested:
            <input id="timeRequested" type="text" disabled style="visibility: visible"/>
            Time of Driver Arrival:
            <input id="timeOfDriverArrival" type="text" disabled style="visibility: visible"/>
            Time Started:
            <input id="timeStarted" type="text" disabled style="visibility: visible"/>
            Time Completed:
            <input id="timeCompleted" type="text" disabled style="visibility: visible"/>
        </div>

        <div id="carClassBox">
            Car class:
            <input type="text" id="car" disabled/>
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
    </div>
    <div id="map"></div>
</div>
</body>
</html>