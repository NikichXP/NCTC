/**
 * Created by Ubuntu on 19.05.2015.
 */
var uuid = getCookie("uuid");

$(document).ready(function() {
    getOrderHistory();
});

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function getOrderHistory(){
    if(uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/history',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            data: uuid,
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                drawTable(obj.orderHistory, "driverOrdersHistory");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
}


function drawTable(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawBody(data[i], table, "viewOrder.jsp");
    }
}


function drawBody(rowData, table, url) {
    //TODO Need to remake this method
    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("Date: " + rowData.dateOrderCreate + "\n"
    + " From: " + rowData.startOrder
    + " To: " + rowData.endOrder
    + " Price: " + parseFloat(rowData.price).toFixed(2) + "$");
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        document.location.href = url + "?id=" + rowData.id;
    };
    div.appendChild(createDiv);
}