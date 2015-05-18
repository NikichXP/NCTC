$(document).ready(function () {
    setAssetManager()
});


function setAssetManager() {

    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/cars',
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert(data);
            drawTable(obj.cars, "table");
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
    var createDiv = document.createElement("div");
    var node = document.createTextNode("id :" + rowData.carId + " model :" + rowData.model);
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        //document.location.href = "editOrder.jsp?id=" + rowData.id;
    };
    var table = document.getElementById("table");
    var tbody = document.createElement("tbody");
    var tr = document.createElement("tr");
    var td1 = document.createElement("td")
    var td2 = document.createElement("td")
    td1.appendChild(createDiv);
    tr.appendChild(td1, td2);
    tbody.appendChild(tr);
    table.appendChild(tbody);
}

