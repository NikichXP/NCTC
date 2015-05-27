/**
 * Created by Ubuntu on 11.05.2015.
 */

$(document).ready(function () {
    getCarClass();
    getMusicType();
    $('body').click(updateMultiplierAndPrice);
    $("#basic-order-submit").click(submitOrder);
});

function submitOrder() {
    $("#basic-order-submit").prop('disabled', true);
    var JSONdata = {
        customerUserUuid: getCookie("uuid"),
        contactName: $("#contactName").val(),
        contactPhone: $("#contactPhone").val(),
        requestedSeatsCount: $("#requestedSeatsCount").val(),
        type: $("#type").val(),

        fromAddress: $("#fromAddress").val(),
        fromX: $("#fromX").val(),
        fromY: $("#fromY").val(),
        toAddress: getArrayOfToAddresses("toAddress"),
        distance: getArrayOfToAddresses("distance"),
        toX: getArrayOfToAddresses("toX"),
        toY: getArrayOfToAddresses("toY"),

        sex: $("input[name=sex]:checked").attr("data-value"),
        carClass: $("input[name=carClass]:checked").attr("data-value"),
        musicType: $("#musicType option:selected").text(),
        asSoonAsPossible: $("#asSoonAsPossible").is(':checked'),
        timeRequested: $("#timeRequested").val(),

        smokingFriendly: $("#smokingFriendly").is(':checked'),
        animalFriendly: $("#animalFriendly").is(':checked'),
        wifi: $("#wifi").is(':checked'),
        airConditioner: $("#airConditioner").is(':checked'),

        customerPreCreateComment: $("#customerPreCreateComment").val(),
        totalLength: $("#totalLength").val(),
        totalMultiplier: $("#totalMultiplier").val(),
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
            document.location.href = "customer.html";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#basic-order-submit").prop('disabled', false);
            alert("Bad response from server.");
        }
    })
}

function getMusicType() {
    $.ajax({
        method: 'POST',
        url: 'api/music/type',
        dataType: 'text',
        success: function (data) {
            var obj = JSON.parse(data);
            var str = '<select id="musicType">';
            for (var i = 0; i < obj.musicType.length; i++) {
                str = str + '<option value="' + obj.musicType[i].id
                    + '">' + obj.musicType[i].name + '</option>';
            }
            str = str + '</select><br>';
            document.getElementById("musicTypes").innerHTML = str;
        },
        error: function (jqXHR) {
            alert("Bad response from server.\n" + jqXHR.responseText);
        }
    })
}

function getCarClass() {
    $.ajax({
        method: 'POST',
        url: 'api/car/class',
        dataType: 'text',
        success: function (data) {
            var obj = JSON.parse(data);
            var str = '';
            for (var i = 0; i < obj.carClass.length; i++) {
                if (i == 0) {
                    str = str + '<input type="radio" checked name="carClass" data-value="'
                        + obj.carClass[i].name + '" multiplier="' + obj.carClass[i].tariff_multiplier
                        + '">' + obj.carClass[i].name + '</input>';
                } else {
                    str = str + '<input type="radio" name="carClass" data-value="'
                        + obj.carClass[i].name + '" multiplier="' + obj.carClass[i].tariff_multiplier
                        + '">' + obj.carClass[i].name + '</input>';
                }
            }

            document.getElementById("carClass").innerHTML = str;
        },
        error: function (jqXHR) {
            alert("Bad response from server.\n" + jqXHR.responseText);
        }
    })
}


function showOrHideDatePicker() {
    if (document.getElementById("timeRequested").style.visibility == "visible") {
        document.getElementById("timeRequested").style.visibility = "hidden";
    }
    else {
        document.getElementById("timeRequested").style.visibility = "visible";
    }
}

var phoneRegEx = /^\+?[0-9]{6,12}$/;
var namesRegEx = /^[a-zA-Z\s'\-]+$/;
var dateTime = /^([1-9]|([012][0-9])|(3[01]))\/([0]?[1-9]|1[012])\/\d\d\d\d [012]?[0-9]:[0-6][0-9]$/;
var seatsCount = /^\d+$/;

function validateBasicOrderData() {
    if (getCookie("uuid").length != 36) {
        alert("Wrong uuid cookie");
        return false;
    }
    if (!validateNames($("#contactName").val(), namesRegEx)) {
        alert("Contact name:\nPlease, use only alphabetic characters!");
        return false;
    }
    var contactPhone = $("#contactPhone").val();
    contactPhone = contactPhone.replace(/\s/g, "").replace(/\+/g, "");
    if (!validateNames(contactPhone, phoneRegEx)) {
        alert("Phone number:\nPlease, use only digits. Length from 6 to 12.");
        return false;
    }
    if (!validateNames($("#requestedSeatsCount").val(), seatsCount)) {
        alert("Requested seats count:\nDigits only.");
        return false;
    }
    if ($("#fromAddress").val().length == 0 || $("#fromX").val().length == 0 || $("#fromY").val().length == 0) {
        alert("Select proper start address.");
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
    if (!$("#asSoonAsPossible").is(':checked') && !validateNames($("#timeRequested").val(), dateTime)) {
        alert("Wrong requested time");
        return false;
    }
    if (!$("#totalLength").val() > 1 || !$("#totalMultiplier").val() > 1 || !$("#totalPrice").val() > 1) {
        alert("Bad totals.");
        return false;
    }
    return true;
}

function validateNames(input, regEx) {
    if (input.length > 0) {
        return regEx.test(input);
    }
    return false;
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

jQuery(function ($) {
    $("#timeRequested").mask("99/99/9999 99:99", {placeholder: "dd/mm/yyyy HH:mm"});
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

function updateMultiplierAndPrice() {
    var totalMultiplier = 1;
    totalMultiplier *= $("#orderTypeRate").attr("multiplier");
    var i = 0;
    while ($("#distanceRates" + i).length > 0) {
        if ($("#asSoonAsPossible").is(':checked')) {
            var d = new Date();
            var n = (d.getHours() < 10 ? '0' : '') + d.getHours() + ":" + (d.getMinutes() < 10 ? '0' : '') + d.getMinutes();
            if (n >= $("#distanceRates" + i).attr("fromtimehhmm") && n <= $("#distanceRates" + i).attr("totimehhmm")) {
                totalMultiplier *= $("#distanceRates" + i).attr("multiplier");
                break;
            }
        } else if (validateTime($("#timeRequested").val())) {
            var n = $("#timeRequested").val().slice(-5);
            if (n >= $("#distanceRates" + i).attr("fromtimehhmm") && n <= $("#distanceRates" + i).attr("totimehhmm")) {
                totalMultiplier *= $("#distanceRates" + i).attr("multiplier");
                break;
            }
        }
        i++;
    }
    totalMultiplier *= $("input[name=carClass]:checked").attr("multiplier");
    totalMultiplier *= $("input[name=sex]:checked").attr("multiplier");
    if ($("#smokingFriendly").is(':checked')) totalMultiplier *= $("#smokingFriendly").attr("multiplier");
    if ($("#animalFriendly").is(':checked')) totalMultiplier *= $("#animalFriendly").attr("multiplier");
    if ($("#wifi").is(':checked')) totalMultiplier *= $("#wifi").attr("multiplier");
    if ($("#airConditioner").is(':checked')) totalMultiplier *= $("#airConditioner").attr("multiplier");
    $("#totalMultiplier").val(parseFloat(totalMultiplier).toFixed(2));
    $("#totalPrice").val(parseFloat(totalMultiplier * $("#totalLength").val()).toFixed(2));
}

function validateTime(input) {
    var timeRegex = /^[012]?[0-9]:[0-6][0-9]$/;
    if (input.length > 4) {
        return timeRegex.test(input.slice(-5));
    }
    return false;
}