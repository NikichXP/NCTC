<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Test</title>
    <link href="css/order-style.css" rel="stylesheet">
    <script src="//api-maps.yandex.ru/2.1/?lang=en-US" type="text/javascript"></script>
    <script src="js/jquery.js"></script>
    <script src="js/mapSearchByAddressWOLocate.js" type="text/javascript"></script>
    <script src="js/update-in-progress-order.js"></script>
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
        <%--add all paths here! --%>
            <input type="button" id="addPathPoint" value="Add path point" disabled>
            <input type="button" id="removeCurrentPath" value="Remove current path" disabled>
            <input type="button" id="submitUpdate" value="Submit update" disabled>
            <input type="button" id="completeCurrentPath" value="Complete current path" disabled>
            <input type="button" id="completeOrder" value="Complete order" disabled>
            <input type="button" id="revertUpdate" value="Revert update" disabled><br>
        </div>
        <div id="timeOption">
            <input id="timeRequested" type="text" disabled/>
            <input id="timeOfDriverArrival" type="text" disabled/>
        </div>

        <div id="musicTypesBox">
            Music type:
            <div id="musicTypes"></div>
        </div>
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
            <textarea id="customerPreCreateComment" disabled></textarea>
        </div>

        <div class="submitButtonContainer">
            <input id="basic-order-refuse" type="button" value="Refuse order."/>
        </div>
    </div>
    <div id="map"></div>
</div>
</body>
</html>