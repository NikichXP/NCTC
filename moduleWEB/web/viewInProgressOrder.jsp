<%--
  Created by IntelliJ IDEA.
  User: Ubuntu
  Date: 19.05.2015
  Time: 23:43
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
</head>
<body onload="getTariffs('basic')">
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
      <input id="toAddress0" type="text" onchange="clearToXY(this); makeSearch(this)" placeholder="To address"/>
      <input type="text" id="toX0" hidden/>
      <input type="text" id="toY0" hidden/>
      <input type="text" id="distance0" disabled/>
      <input type="text" id="pathId0" hidden/>
      <%--add all paths here! --%>
      <input type="button" id="addressAdder" value="Add" onclick="createToAddress();"><br>
    </div>
    <div id="timeOption">
      <input id="timeRequested" type="text" disabled/>
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
        <div>Total multiplier:</div><input disabled type="text" id="totalMultiplier"/>
      </div>
      <div class="summarySection">
        <div>Total length:</div><input disabled type="text" id="totalLength"/>
      </div>
      <div class="summarySection">
        <div>Total price:</div><input disabled type="text" id="totalPrice"/>
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

<script> //TODO reorganise this code
jQuery(function ($) {
  $("#timeRequested").mask("99/99/9999 99:99", {placeholder: "dd/mm/yyyy hh:mm"});
});
var counter = 0;
var isDeleteExists = false;
function createToAddress() {
  if ($("#fromX").val().length > 0 && $("#toX0").val().length > 0 && $("#toX" + counter).val().length > 0) {
    setLock("#fromAddress");
    setLock("#toAddress" + counter);
    counter++;
    var outer = document.getElementById("importantInfo");

    var input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("id", "toAddress" + counter);
    input.setAttribute("onchange", "clearToXY(this); makeSearch(this)");
    input.setAttribute("placeholder", "To address " + counter);

    var input2 = document.createElement("input");
    input2.setAttribute("hidden", "hidden");
    input2.setAttribute("type", "text");
    input2.setAttribute("id", "toX" + counter);

    var input3 = document.createElement("input");
    input3.setAttribute("hidden", "hidden");
    input3.setAttribute("type", "text");
    input3.setAttribute("id", "toY" + counter);

    var input4 = document.createElement("input");
    input4.setAttribute("disabled", "disabled");
    input4.setAttribute("type", "text");
    input4.setAttribute("id", "distance" + counter);

    var addressAdder = document.getElementById("addressAdder");
    if (!isDeleteExists) {
      var addressRemover = document.createElement("input");
      addressRemover.setAttribute("id", "addressRemover");
      addressRemover.setAttribute("type", "button");
      addressRemover.setAttribute("onclick", "deleteToAddress()");
      addressRemover.setAttribute("value", "Remove");
      outer.insertBefore(addressRemover, addressAdder);
      isDeleteExists = true;
    }
    var addressRemover = document.getElementById("addressRemover");
    outer.insertBefore(input, addressRemover);
    outer.insertBefore(input2, addressRemover);
    outer.insertBefore(input3, addressRemover);
    outer.insertBefore(input4, addressRemover);
  } else {
    alert("Enter valid <b>From address</b> and <b>To address</b>.")
  }
}

function deleteToAddress() {
  document.getElementById("toAddress" + (counter)).remove();
  document.getElementById("toX" + (counter)).remove();
  document.getElementById("toY" + (counter)).remove();
  document.getElementById("distance" + (counter)).remove();
  counter--;
  buildPath(counter);
  if (counter == 0) {
    setUnlock("#fromAddress");
    setUnlock("#toAddress0");
    document.getElementById("addressRemover").remove();
    isDeleteExists = false;
  } else {
    setUnlock("#toAddress" + (counter));
  }
}

function setLock(name) {
  $(name).prop('disabled', true);
}
function setUnlock(name) {
  $(name).prop('disabled', false);
}

function clearFromXY() {
  $("#fromX").val("");
  $("#fromY").val("");
}

function clearToXY(element) {
  $("#toX" + element.id.slice(-1)).val("");
  $("#toY" + element.id.slice(-1)).val("");
}

$('body').click(updateMultiplierAndPrice);

function updateMultiplierAndPrice() {
  var totalMultiplier = $("#totalMultiplier").val();
  $("#totalPrice").val(totalMultiplier * $("#totalLength").val());
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/mapSearchByAddressWOLocate.js" type="text/javascript"></script>
<script src="js/createorder.js"></script>
</body>
</html>