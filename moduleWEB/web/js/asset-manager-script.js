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
            drawCars(obj.cars, "table");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}

function drawCars(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawBodyCars(data[i], table);
    }
}
function drawBodyCars(rowData, table) {

    var createDiv = document.createElement("div");
    var node = document.createTextNode("id :" + rowData.carId + " model :" + rowData.model);
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        alert(rowData.carId);
    };
    var table = document.getElementById("assetManagerTable");
    var tbody = document.createElement("tbody");
    var tr = document.createElement("tr");
    var td1 = document.createElement("td")
    var td2 = document.createElement("td")
    td1.appendChild(createDiv);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tbody.appendChild(tr);
    table.appendChild(tbody);
}

