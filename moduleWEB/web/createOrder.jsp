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
  <script src="//api-maps.yandex.ru/2.1/?lang=en-US" type="text/javascript"> </script>
</head>
<body onload="getMusicType()">
<div id="order-form" style="float: left;">
  <input type="text" id="contactName" placeholder="Contact name"/><br>
  <input type="text" id="contactPhone" placeholder="Contact phone"/><br>
  <input type="text" id="requestedSeatsCount" placeholder="Requested seats count"/><br>
  <input type="text" id="fromAddress" placeholder="From address" onchange="makeSearch(this)"/>
  <input type="text" id="fromX" disabled/>
  <input type="text" id="fromY" disabled/><br>
  <input type="text" id="toAddress0" placeholder="To address" onchange="makeSearch(this)"/>
  <input type="text" id="toX0" disabled/>
  <input type="text" id="toY0" disabled/><br>
  <input type="button" id="addressAdder" value = "Add" onclick = "createToAddress();"><br>

  <input onchange="showOrHideDatePicker()" type="checkbox" id="asSoonAsPossible">asSoonAsPossible</input><br>
  <input id="timeRequested" style="visibility: visible" type="text"/><br>

  Driver sex:<br>
  <input type="radio" checked name="sex" data-value="Male">Male</input><br>
  <input type="radio" name="sex" data-value="Any">Any</input><br>
  <input type="radio" name="sex" data-value="Female">Female</input><br>

  Car class:<br>
  <input type="radio" checked name="carClass" data-value="Basic">Basic</input><br><%--TODO get from DB--%>
  <input type="radio" name="carClass" data-value="Premium">Premium</input><br>

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
</div>
<div id="map" style="width:600px; height:600px; float: right;"></div>

<script>
  jQuery(function($){
    $("#timeRequested").mask("99/99/9999 99:99",{placeholder:"dd/mm/yyyy hh:mm"});
  });
  var counter = 1;
  var isDeleteExists = false;
  function createToAddress() {
      var outer = document.getElementById("order-form");
      var br = document.createElement("br");
      br.setAttribute("id", "br"+counter);

      var input = document.createElement("input");
      input.setAttribute("type", "text");
      input.setAttribute("id", "toAddress"+counter);
      input.setAttribute("onchange", "makeSearch(this)");
      input.setAttribute("placeholder", "To address "+counter);

      var input2 = document.createElement("input");
      input2.setAttribute("disabled", "disabled");
      input2.setAttribute("type", "text");
      input2.setAttribute("id", "toX"+counter);

      var input3 = document.createElement("input");
      input3.setAttribute("disabled", "disabled");
      input3.setAttribute("type", "text");
      input3.setAttribute("id", "toY"+counter);

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
      outer.insertBefore(br, addressRemover);
      outer.insertBefore(input, addressRemover);
      outer.insertBefore(input2, addressRemover);
      outer.insertBefore(input3, addressRemover);
      counter++;
  }

  function deleteToAddress() {
      document.getElementById("br" + (counter - 1)).remove();
      document.getElementById("toAddress" + (counter - 1)).remove();
      document.getElementById("toX" + (counter - 1)).remove();
      document.getElementById("toY" + (counter - 1)).remove();
      counter--;
      buildPath(counter - 1);
      if (counter == 1) {
          document.getElementById("addressRemover").remove();
          isDeleteExists = false;
      }
  }
</script>
<script src="static/js/mapSearchByAddress.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/createorder.js"></script>
</body>
</html>