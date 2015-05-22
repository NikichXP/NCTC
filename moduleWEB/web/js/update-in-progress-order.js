$(document).ready(function () {
    var driverUuid = getCookie('uuid');
    $.ajax({
        method: 'POST',
        url: 'api/order/getOrderInProgressByDriverUUID',
        contentType: "application/json; charset=utf-8",
        data: driverUuid,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
            fillPageWithData();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('You have no "in progress" order.');
            //TODO Add redirect
        }
    });
    $('body').click(updatePrice);
    $("#basic-order-submit").click(submitOrder);
    $("#updateCurrentPath").click(submitUpdate);
});

function fillPageWithData() {

}

function submitUpdate() {
    $("#updateCurrentPath").prop('disabled', true);
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
        url: 'api/order/create',
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

function clearToXY(element) {
    $("#toX" + element.id.slice(-1)).val("");
    $("#toY" + element.id.slice(-1)).val("");
}

function updatePrice() {
    var totalMultiplier = $("#totalMultiplier").val();
    $("#totalPrice").val(totalMultiplier * $("#totalLength").val());
}