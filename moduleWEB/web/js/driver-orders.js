var uuid = getCookie("uuid");

$(document).ready(function(){
    getQueuedOrders();
    getAssignedOrders();
});

function getQueuedOrders(){
    if(uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/getQueuedOrders',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                drawTable(obj.orders, "driverQueuedOrdersPanel");
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
                drawTable(obj.orders, "driverAssignedOrdersPanel");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
}


function drawTable(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawBody(data[i], table);
    }
}
function drawBody(rowData, table) {

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
        document.location.href = "editOrder.jsp?id=" + rowData.id;
    };
    div.appendChild(createDiv);
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
            end = cookie.indexOf(";", offset)
            if (end == -1) {
                end = cookie.length;
            }
            setStr = decodeURI(cookie.substring(offset, end));
        }
    }
    return (setStr);
}