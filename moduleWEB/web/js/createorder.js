/**
 * Created by Ubuntu on 11.05.2015.
 */

//AJAX POST for registration
$('#basic-order-submit').click(function(){
    if (!validateRegistrationData()) return;
    var JSONdata = {
        customerUserUuid: $("#customerUserUuid").val(),
        contactName: $("#contactName").val(),
        contactPhone: $("#contactPhone").val(),
        requestedSeatsCount: $("#requestedSeatsCount").val(),
        type: $("#type").val(),

        fromAddress: $("#fromAddress").val(),
        fromX: $("#fromX").val(),
        fromY: $("#fromY").val(),
        toAddress: getArrayOfToAdresses("toAddress"),
        toX: getArrayOfToAdresses("toX"),
        toY: getArrayOfToAdresses("toY"),

        sex: $("#sex").val(),
        carClass: $("#carClass").val(),
        musicType: $("#musicType").val(),
        smokingFriendly: $("#smokingFriendly").val(),
        aminalFriendly: $("#aminalFriendly").val(),
        wifi: $("#wifi").val(),
        airConditioner: $("#airConditioner").val(),

        customerPreCreateComment: $("#customerPreCreateComment").val(),
        totalLength: $("#totalLength").val(),
        totalPrice: $("#totalPrice").val()
    };
    redirectWithAccessLevels(JSON.stringify(JSONdata), "api/user/create");

    function getArrayOfToAdresses(idWithoutNumber) {
        var arr = [];
        for (var i = 0; i < $("[id^="+idWithoutNumber+"]").length; i++) {
            arr[i] = $("#" + idWithoutNumber + i).val()
        }
        return arr;
    }
});

var dataJson = {
    "id": "1",
    "publicToken": "",
    "customerUserId": "1",
    "customerUserUuid": "1",
    "driverUserId": "1",
    "contactName": "",
    "contactPhone": "",
    "requestedSeatsCount": "",
    "type": "",
    "state": "",
    "timeCreated": "",
    "timeRequested": "",
    "timeOfDriverArrival": "",
    "timeStarted": "",
    "timeCompleted": "",
    "fromAddress": "",
    "fromX": "",
    "fromY": "",
    "toAddress": ["", ""],
    "toX": ["", ""],
    "toY": ["", ""],
    "sex": "",
    "carClass": "",
    "musicType": "",
    "smokingFriendly": false,
    "aminalFriendly": false,
    "wifi": false,
    "airConditioner": false,
    "customerPreCreateComment": "",
    "customerPostCompleteComment": "",
    "customerRefuseCause": "",
    "driverRefuseCause": "",
    "customerRefuseComment": "",
    "driverRefuseComment": "",
    "totalLength": "",
    "totalMultiplier": "",
    "totalPrice": ""
};

function createOrder() {
    $.ajax({
        method: 'POST',
        url: 'api/order/create',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(dataJson),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(textStatus + "\n" + data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Bad response from server.");
        }
    })
}
