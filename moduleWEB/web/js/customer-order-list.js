$(document).ready(function () {
    setList()
});


function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function setList() {
    var uuid = getCookie("uuid");
    if (uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/user_dash/customer_list',
            contentType: "text/plain; charset=utf-8",
            data: uuid,
            dataType: 'text',
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                createTable(obj.orderHistory, "table");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert(jqXHR.responseText + " Error!");
            }
        })
    } else {
        document.location.href = "index.html";
    }
}

function createTable(data, table) {
    var title = '<h4>CURRENT ORDERS:</h4>';
    var tb = title + '<table style="width:100%">';

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
    document.location.href = "viewCustomerOrder.html?id=" + id;
};