/**
 * Created by Ubuntu on 11.05.2015.
 */
var dataJson = {
    "id": "1",
    "publicToken": "",
    "customerUserId": "1",
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
    "fromAddress": ["", ""],
    "fromX": ["", ""],
    "fromY": ["", ""],
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
