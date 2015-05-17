$(document).ready(function(){
    setList()
});



function setList(){
    var uuid = getCookie("uuid");
    if(uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/user_dash/customer_list',
            contentType: "text/plain; charset=utf-8",
            data: uuid,
            dataType: 'text',
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                drawTable(obj.orderHistory, "table");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + " Error!");
            }
        })
    }
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

function drawTable(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawBody(data[i], table);
    }
}
function drawBody(rowData, table) {

    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("data :" + rowData.dateOrderCreate
                                        + " ferst point :" + rowData.startOrder
                                        + " next point :" + rowData.endOrder
                                        + " status :" + rowData.statusOrder
                                        + " price: " + rowData.price + "$");
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        alert(rowData.endOrder);
    };
    div.appendChild(createDiv);

}






