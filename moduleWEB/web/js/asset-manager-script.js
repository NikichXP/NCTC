$(document).ready(function () {
    setAssetManager()
});


function setAssetManager() {

    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/cars',
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            //var obj = JSON.parse(data);
            alert(data);
            //drawTable(obj.orderHistory, "table");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
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
        document.location.href = "editOrder.jsp?id=" + rowData.id;

    };
    div.appendChild(createDiv);

}

