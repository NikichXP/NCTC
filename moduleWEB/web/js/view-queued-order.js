/**
 * Created by Ubuntu on 20.05.2015.
 */

var obj;

$(document).ready(function () {
    $(function ($) {
        $("#timeOfDriverArrival").mask("99/99/9999 99:99", {placeholder: "dd/mm/yyyy HH:mm"});
    });
    getUrlVars();
    ymaps.ready(function(){
        init();
        getOrderById(getUrlVars()["id"]);
    });

    $("#assignButton").click(function () {
        $("#assignButton").prop('disabled', true);
        var JSONdata = {
            id: obj.id,
            driverUserUuid: getCookie("uuid"),
            timeOfDriverArrival: $("#timeOfDriverArrival").val()
        }
        $.ajax({
            method: 'POST',
            url: 'api/driver/assign',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(JSONdata),
            dataType: 'text',
            success: function (data, textStatus, jqXHR) {
                alert(data);
                document.location.href = "driver.html";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                $("#assignButton").prop('disabled', false);
            }
        })
    });

});

function getOrderById(id) {
    $.get("api/order/viewQueuedOrder?id=" + id, function (data) {
        obj = JSON.parse(data);
        $("#contactName").attr("value", obj.contactName);
        $("#contactPhone").attr("value", obj.contactPhone);
        $("#requestedSeatsCount").attr("value", obj.requestedSeatsCount);
        $("#fromAddress").attr("value", obj.fromAddress);
        $("#fromX").attr("value", obj.fromX);
        $("#fromY").attr("value", obj.fromY);

        for(var i = 0; i < obj.toAddress.length; i++){
            var outer = document.getElementById("importantInfo");
            var input = document.createElement("input");

            input.setAttribute("type", "text");
            input.setAttribute("id", "toAddress" + i);
            input.setAttribute("onchange", "clearToXY(this)");
            input.setAttribute("placeholder", "To address " + i);
            input.setAttribute("disabled", "disabled");
            input.setAttribute("value", obj.toAddress[i]);

            var input2 = document.createElement("input");
            input2.setAttribute("hidden", "hidden");
            input2.setAttribute("type", "text");
            input2.setAttribute("id", "toX" + i);
            input2.setAttribute("value", obj.toX[i]);

            var input3 = document.createElement("input");
            input3.setAttribute("hidden", "hidden");
            input3.setAttribute("type", "text");
            input3.setAttribute("id", "toY" + i);
            input3.setAttribute("value", obj.toY[i]);

            var input4 = document.createElement("input");
            input4.setAttribute("disabled", "disabled");
            input4.setAttribute("type", "text");
            input4.setAttribute("id", "distance" + i);
            input4.setAttribute("value", obj.distance[i]);

            var button = document.getElementById("addPathPoint");

            outer.insertBefore(input, button);
            outer.insertBefore(input2, button);
            outer.insertBefore(input3, button);
            outer.insertBefore(input4, button);

            makeSearch(input);
            $("#toAddress" + i).change(function () {
                buildPath($("[id^='toAddress']").length - 1);
            });
        }

        $("#timeRequested").attr("value", obj.timeRequested);
        $("#timeOfDriverArrival").attr("value", obj.timeRequested);

        $('input:radio[name=sex][data-value=' + obj.sex + ']').prop('checked', true);

        $("#car").attr("value", obj.carClass);
        $("#music").attr("value", obj.musicType);

        if(obj.smokingFriendly == "true") {
            $('input:checkbox[id=smokingFriendly]').prop('checked', true);
        }
        if(obj.animalFriendly == "true") {
            $('input:checkbox[id=animalFriendly]').prop('checked', true);
        }
        if(obj.wifi == "true") {
            $('input:checkbox[id=wifi]').prop('checked', true);
        }
        if(obj.airConditioner == "true") {
            $('input:checkbox[id=airConditioner]').prop('checked', true);
        }

        $("#totalMultiplier").attr("value", parseFloat(obj.totalMultiplier).toFixed(2));
        $("#totalLength").attr("value", parseFloat(obj.totalLength).toFixed(2));
        $("#totalPrice").attr("value", parseFloat(obj.totalPrice).toFixed(2));

        $("#customerPreCreateComment").attr("value", obj.customerPreCreateComment);
    });
}


function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}