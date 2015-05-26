var id;

$(document).ready(function () {
    var driverUuid = getCookie('uuid');
    $.ajax({
        method: 'POST',
        url: 'api/order/getOrderInProgressByDriverUUID',
        contentType: "application/json; charset=utf-8",
        data: driverUuid,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            id = data;
            ymaps.ready(function () {
                init();
                fillPageWithData()
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('You have no "in progress" order.');
            //TODO Add redirect
        }
    });
    $('body').click(updatePrice);
    $('#addPointOnPath').click(addPointOnPath);
    $("#updateCurrentPath").click(submitUpdate);
    $('#removeCurrentPath').click(updatePrice);
    $('#completeCurrentPath').click(updatePrice);
    $('#completeOrder').click(updatePrice);
});

function fillPageWithData() {
    $.get("api/order/view?id=" + id, function (data) {
        var obj = JSON.parse(data);
        $("#contactName").attr("value", obj.contactName);
        $("#contactPhone").attr("value", obj.contactPhone);

        $("#requestedSeatsCount").attr("value", obj.requestedSeatsCount);
        $("#fromAddress").attr("value", obj.fromAddress);
        $("#fromX").attr("value", obj.fromX);
        $("#fromY").attr("value", obj.fromY);

        var flag = false;
        for (var i = 0; i < obj.toAddress.length; i++) {
            var outer = document.getElementById("importantInfo");

            var input = document.createElement("input");
            if (obj.pathCompleted[i] == "true" || flag) input.setAttribute("disabled", "disabled")
            else flag = true;
            input.setAttribute("type", "text");
            input.setAttribute("id", "toAddress" + i);
            input.setAttribute("onchange", "clearToXY(this)");
            input.setAttribute("placeholder", "To address " + i);
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

            var input5 = document.createElement("input");
            input5.setAttribute("hidden", "hidden");
            input5.setAttribute("type", "text");
            input5.setAttribute("id", "pathId" + i);
            input5.setAttribute("value", obj.pathId[i]);

            var input6 = document.createElement("input");
            input6.setAttribute("hidden", "hidden");
            input6.setAttribute("type", "text");
            input6.setAttribute("id", "pathCompleted" + i);
            input6.setAttribute("value", obj.pathCompleted[i]);

            var button = document.getElementById("addPointOnPath");

            outer.insertBefore(input, button);
            outer.insertBefore(input2, button);
            outer.insertBefore(input3, button);
            outer.insertBefore(input4, button);
            outer.insertBefore(input5, button);
            outer.insertBefore(input6, button);

            makeSearch(input);
            $("#toAddress" + i).change(function () {
                buildPath($("[id^='toAddress']").length - 1);
            });
        }

        $("#timeRequested").attr("value", obj.timeRequested);
        $("#timeOfDriverArrival").attr("value", obj.timeOfDriverArrival);

        $("#music").attr("value", obj.musicType);

        if (obj.smokingFriendly == "true") $('input:checkbox[id=smokingFriendly]').prop('checked', true);
        if (obj.animalFriendly == "true") $('input:checkbox[id=animalFriendly]').prop('checked', true);
        if (obj.wifi == "true") $('input:checkbox[id=wifi]').prop('checked', true);
        if (obj.airConditioner == "true") $('input:checkbox[id=airConditioner]').prop('checked', true);

        $("#totalMultiplier").attr("value", obj.totalMultiplier);
        $("#totalLength").attr("value", obj.totalLength);
        $("#totalPrice").attr("value", obj.totalPrice);

        $("#customerPreCreateComment").attr("value", obj.customerPreCreateComment);
    });
}

function submitUpdate() {
    $("#addPointOnPath").prop('disabled', true);
    $("#updateCurrentPath").prop('disabled', true);
    $("#removeCurrentPath").prop('disabled', true);
    $("#completeCurrentPath").prop('disabled', true);
    $("#completeOrder").prop('disabled', true);

    var JSONdata = {
        driverUserUuid: getCookie("uuid"),

        fromAddress: $("#fromAddress").val(),
        fromX: $("#fromX").val(),
        fromY: $("#fromY").val(),
        toAddress: getArrayOfToAddresses("toAddress"),
        distance: getArrayOfToAddresses("distance"),
        toX: getArrayOfToAddresses("toX"),
        toY: getArrayOfToAddresses("toY"),
        pathId: getArrayOfToAddresses("pathId"),

        totalLength: $("#totalLength").val(),
        totalPrice: $("#totalPrice").val()
    };

    function getArrayOfToAddresses(idWithoutNumber) {
        var arr = [];
        for (var i = 0; i < $("[id^=" + idWithoutNumber + "]").length; i++) {
            arr[i] = $("#" + idWithoutNumber + i).val()
        }
        return arr;
    }

    if (!validateBasicOrderData()) {
        $("#basic-order-submit").prop('disabled', false);
        return;
    }
    $.ajax({
        method: 'POST',
        url: 'api/order/updateInProgress',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert("Order successfully created.\nOrder details were sent to your email.");
            document.location.href = "customer.jsp";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#basic-order-submit").prop('disabled', false);
            alert("Bad response from server.");
        }
    })
}

var dateTime = /^([1-9]|([012][0-9])|(3[01]))\/([0]?[1-9]|1[012])\/\d\d\d\d [012]?[0-9]:[0-6][0-9]$/;

function validateBasicOrderData() {
    if (getCookie("uuid").length != 36) {
        alert("Wrong uuid cookie");
        return false;
    }
    if ($("#toAddress0").val().length == 0 || $("#toX0").val().length == 0 || $("#toY0").val().length == 0) {
        alert("Select proper destination address.");
        return false;
    }
    if ($("#toAddress" + counter).val().length == 0 || $("#toX" + counter).val().length == 0
        || $("#toY" + counter).val().length == 0) {
        alert("Select proper destination address.");
        return false;
    }
    if (!$("#totalLength").val() > 0 || !$("#totalMultiplier").val() > 0 || !$("#totalPrice").val() > 0) {
        alert("Bad totals.");
        return false;
    }
    return true;
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

var counter = 0;
var isDeleteExists = false;
function addPointOnPath() {
    var outer = document.getElementById("importantInfo");

    var indexOfInProgress = $("[id^='toAddress']").filter($("[disabled!='disabled']")).attr("id").match(/\d+/);
    var lastIndex = $("[id^='toAddress']").filter($("[disabled='disabled']")).length;

    for (var i = lastIndex; i >= indexOfInProgress; i--) {
        var input = document.getElementById("toAddress" + i);
        input.setAttribute("id", "toAddress" + (i + 1));
        input.setAttribute("placeholder", "To address " + (i + 1));
        input.setAttribute("disabled", "disabled");

        var input2 = document.getElementById("toX" + i);
        input2.setAttribute("id", "toX" + (i + 1));

        var input3 = document.getElementById("toY" + i);
        input3.setAttribute("id", "toY" + (i + 1));

        var input4 = document.getElementById("distance" + i);
        input4.setAttribute("id", "distance" + (i + 1));

        document.getElementById("pathId" + i).remove();
        document.getElementById("pathCompleted" + i).remove();
    }

    var addedInput = document.createElement("input");
    addedInput.setAttribute("type", "text");
    addedInput.setAttribute("id", "toAddress" + indexOfInProgress);
    addedInput.setAttribute("onchange", "clearToXY(this), buildPath(" + (parseInt(lastIndex) + 1) + ")");
    addedInput.setAttribute("placeholder", "To address " + indexOfInProgress);

    var addedInput2 = document.createElement("input");
    addedInput2.setAttribute("hidden", "hidden");
    addedInput2.setAttribute("type", "text");
    addedInput2.setAttribute("id", "toX" + indexOfInProgress);

    var addedInput3 = document.createElement("input");
    addedInput3.setAttribute("hidden", "hidden");
    addedInput3.setAttribute("type", "text");
    addedInput3.setAttribute("id", "toY" + indexOfInProgress);

    var addedInput4 = document.createElement("input");
    addedInput4.setAttribute("disabled", "disabled");
    addedInput4.setAttribute("type", "text");
    addedInput4.setAttribute("id", "distance" + indexOfInProgress);

    var lastToAddress = document.getElementById("toAddress" + (parseInt(indexOfInProgress) + 1));
    alert(lastToAddress.id);
    outer.insertBefore(addedInput, lastToAddress);
    outer.insertBefore(addedInput2, lastToAddress);
    outer.insertBefore(addedInput3, lastToAddress);
    outer.insertBefore(addedInput4, lastToAddress);
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

function clearToXY(element) {
    $("#toX" + element.id.slice(-1)).val("");
    $("#toY" + element.id.slice(-1)).val("");
}

function updatePrice() {
    var totalMultiplier = $("#totalMultiplier").val();
    $("#totalPrice").val(totalMultiplier * $("#totalLength").val());
}