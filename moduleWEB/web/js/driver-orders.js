var uuid = getCookie("uuid");

$(document).ready(function(){
    getQueuedOrders();
    getAssignedOrders();
});

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function getQueuedOrders(){
    if(uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/getQueuedOrders',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                createTableQueuedOrders(obj.orders, "driverQueuedOrdersPanel");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + " Error! driverQueuedOrders");
            }
        })
    }
}

function getAssignedOrders(){
    if(uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/getAssignedOrders',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            data: uuid,
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                createTableAssignedOrders(obj.orders, "driverAssignedOrdersPanel");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
}
function createTableAssignedOrders(data, table) {
    var title = '<h4>ASSIGNED ORDERS:</h4>';
    var tb = title + '<table style="width:100%">';

    for (var i = 0; i < data.length; i++) {
        var date = "Date: " + data[i].dateOrderCreate.split(".")[0];
        var from = "From: " + data[i].startOrder;
        var to = "To: " + data[i].endOrder;
        var distance = "Distance: " + parseFloat(data[i].distance).toFixed(2) + " km.";
        var price = "Price: " + parseFloat(data[i].price).toFixed(2) + " &#8372";

        var div = '<div class="button" onclick="redirectAssignedOrder(' + data[i].id + ')" style="width: 100%">View</div>';

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

function createTableQueuedOrders(data, table) {
    var title = '<h4>QUEUED ORDERS:</h4>';
    var tb = title + '<table style="width:100%">';

    for (var i = 0; i < data.length; i++) {
        var date = "Date: " + data[i].dateOrderCreate.split(".")[0];
        var from = "From: " + data[i].startOrder;
        var to = "To: " + data[i].endOrder;
        var distance = "Distance: " + parseFloat(data[i].distance).toFixed(2) + " km.";
        var price = "Price: " + parseFloat(data[i].price).toFixed(2) + " &#8372";

        var div = '<div class="button" onclick="redirectQueuedOrder(' + data[i].id + ')" style="width: 100%">View</div>';

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

function redirectAssignedOrder(id) {
    document.location.href = "viewAssignedOrder.jsp?id=" + id;
};

function redirectQueuedOrder(id) {
    document.location.href = "viewQueuedOrder.jsp?id=" + id;
};