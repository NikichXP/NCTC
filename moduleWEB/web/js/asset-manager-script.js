$(document).ready(function () {
    setAssetManagerCars();
    setAssetManagerDrivers();
});


function setAssetManagerCars() {

    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/cars',
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert(data);
            drawTableCars(obj.cars, "cars");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}

function drawTableCars(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawRowCars(data[i], table);
    }
}
function drawRowCars(rowData, table) {
    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("id :" + rowData.carId + " model :" + rowData.model);
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        alert(rowData.carId)

    };
    div.appendChild(createDiv);
}

function setAssetManagerDrivers() {

    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/drivers',
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert(data);
            drawTableDrivers(obj.cars, "drivers");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}
function drawTableDrivers(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawRowDrivers(data[i], table);
    }
}
function drawRowDrivers(rowData, table) {
    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("id :" + rowData.driverId + " name :" + rowData.name);
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        alert(rowData.driverId)
    };
    div.appendChild(createDiv);
}