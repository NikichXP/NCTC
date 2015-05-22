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
                drawTableQueuedOrders(obj.orders, "driverQueuedOrdersPanel");
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
                drawTableAssignedOrders(obj.orders, "driverAssignedOrdersPanel");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
}


function drawTableAssignedOrders(data, table) {
    for (var i = 0; i < data.length; i++) {
       // drawBody(data[i], table, "viewAssignedOrder.jsp");
        drawBody(data[i], table, "viewOrder.jsp");
    }
}

function drawTableQueuedOrders(data, table) {
    for (var i = 0; i < data.length; i++) {
       // drawBody(data[i], table, "viewQueuedOrder.jsp");
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

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function getOrder(){
    if(uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/driver/getOrder',
            contentType: "text/plain; charset=utf-8",
            dataType: 'text',
            data: uuid,
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                drawTableAssignedOrders(obj.orders, "driverAssignedOrdersPanel");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + " Error! driverAssignedOrders");
            }
        })
    }
}