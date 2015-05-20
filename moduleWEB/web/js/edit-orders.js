/**
 * Created by Juger on 20.05.2015.
 */
$(document).ready(function(){
   setEditData();
});

var id;

function setEditData(){
    id = getUrlVars()["id"];
    alert(id);
    uuid = getCookie("uuid");
    gerRequest(id, 'api/order/existing', uuid);
}

function gerRequest(id, url, uuid) {
    $.get(url + "?id=" + id + "&uuid=" + uuid, function (data) {
        alert(data);
    });
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

//AJAX POST for registration
$("#basic-order-submit").click(function () {
    $("#basic-order-submit").prop('disabled', true);
    var JSONdata = {
        id: id,
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
        url: 'api/order/edit',
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
});

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
                    +'">' + obj.musicType[i].name + '</option>';
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
    if(getCookie("uuid").length != 36){
        alert("Wrong uuid cookie");
        return false;
    }
    if(!validateNames($("#contactName").val(), namesRegEx)){
        alert("Contact name:\nPlease, use only alphabetic characters!");
        return false;
    }
    var contactPhone = $("#contactPhone").val();
    contactPhone = contactPhone.replace(/\s/g, "").replace(/\+/g, "");
    if(!validateNames(contactPhone, phoneRegEx)){
        alert("Phone number:\nPlease, use only digits. Length from 6 to 12.");
        return false;
    }
    if(!validateNames($("#requestedSeatsCount").val(), seatsCount)){
        alert("Requested seats count:\nDigits only.");
        return false;
    }
    if($("#fromAddress").val().length == 0 || $("#fromX").val().length == 0 || $("#fromY").val().length == 0){
        alert("Select proper start address.");
        return false;
    }
    if($("#toAddress0").val().length == 0 || $("#toX0").val().length == 0 || $("#toY0").val().length == 0) {
        alert("Select proper destination address.");
        return false;
    }
    if($("#toAddress" + counter).val().length == 0 || $("#toX" + counter).val().length == 0
        || $("#toY" + counter).val().length == 0){
        alert("Select proper destination address.");
        return false;
    }
    if(!$("#asSoonAsPossible").is(':checked') && !validateNames($("#timeRequested").val(), dateTime)){
        alert("Wrong requested time");
        return false;
    }
    if(!$("#totalLength").val() > 0 || !$("#totalMultiplier").val() > 0 || !$("#totalPrice").val() > 0){
        alert("Bad totals.");
        return false;
    }
    return true;
}

function validateNames (input, regEx) {
    if(input.length > 0){
        return regEx.test(input);
    }
    return false;
}

function getCookie(name) {
    var cookie = " " + document.cookie;
    var search = " " + name + "=";
    var setStr = null;
    var offset = 0;
    var end = 0;
    if (cookie.length > 0) {
        offset = cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = cookie.indexOf(";", offset);
            if (end == -1) {
                end = cookie.length;
            }
            setStr = decodeURI(cookie.substring(offset, end));
        }
    }
    return(setStr);
}