/**
 * Created by Ubuntu on 19.05.2015.
 */
var uuid = getCookie("uuid");

$(document).ready(function () {
    getOrderHistory();
});

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

$('#leftButtonHistory').click(function(){
    if (uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/historyByDate',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            data: uuid,
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                createTable(obj.orderHistory, "driverOrdersHistory");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
});
$('#centralButtonHistory').click(function(){
    if (uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/historyByPrice',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            data: uuid,
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                createTable(obj.orderHistory, "driverOrdersHistory");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
});
$('#rightButtonHistory').click(function(){
    if (uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/historyByLength',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            data: uuid,
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                createTable(obj.orderHistory, "driverOrdersHistory");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
});

function getOrderHistory() {
    if (uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/historyByDate',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            data: uuid,
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                createTable(obj.orderHistory, "driverOrdersHistory");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
}

function createTable(data, table) {
    var tb = '<table style="width:100%">';

    for (var i = 0; i < data.length; i++) {
        var date = "Date: " + data[i].dateOrderCreate.split(".")[0];
        var from = "From: " + data[i].startOrder;
        var to = "To: " + data[i].endOrder;
        var distance = "Distance: " + parseFloat(data[i].distance).toFixed(2) + " km.";
        var price = "Price: " + parseFloat(data[i].price).toFixed(2) + " &#8372";

        var div = '<div class="button" onclick="redirect(' + data[i].id + ')" style="width: 100%">View</div>';

        tb = tb + '<tr>'
            + '<td>&nbsp' + date + '</td>'
            + '<td>&nbsp' + from + '</td>'
            + '<td>&nbsp' + to + '</td>'
            + '<td>&nbsp' + distance + '</td>'
            + '<td>&nbsp' + price + '</td>'
            + '<td>' + div + '</td>'
            + '</tr>';
    }
    tb = tb + '</table>';
    document.getElementById(table).innerHTML = tb;
}

function redirect(id) {
    document.location.href = "viewOrder.html?id=" + id;
};