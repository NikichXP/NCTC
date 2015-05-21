/**
 * Created by Ubuntu on 19.05.2015.
 */
var uuid = getCookie("uuid");

$(document).ready(function() {
    getOrderHistory();
});

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
            end = cookie.indexOf(";", offset)
            if (end == -1) {
                end = cookie.length;
            }
            setStr = decodeURI(cookie.substring(offset, end));
        }
    }
    return (setStr);
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

    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("date :" + rowData.dateOrderCreate
    + " first point :" + rowData.startOrder
    + " next point :" + rowData.endOrder
    + " status :" + rowData.statusOrder
    + " price: " + rowData.price + "$");
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        document.location.href = url + "?id=" + rowData.id;
    };
    div.appendChild(createDiv);
}