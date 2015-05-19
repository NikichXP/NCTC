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
function editCar() {
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
function drawRowCars(rowData, table) {
    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("id :" + rowData.carId + " model :" + rowData.model);
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        alert(rowData.carId)
        editCar();
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
function editDriverData(userData) {
    document.getElementById("firstName").value = userData.firstName;
    document.getElementById("lastName").value = userData.lastName;
    document.getElementById("phone").value = userData.phone;
    document.getElementById("email").value = userData.email;
    document.getElementById("regpass").value = userData.getPassword;
    document.getElementById("passconfirm").value = userData.getPassword;
    document.getElementById("accessLevel").value = userData.accessLevel;
    //document.getElementById("carId").value = userData.name;
}
function editDriver() {
    $.ajax({
        method: 'POST',
        url: 'api/user/getUserDataById',
        contentType: "text/plain; charset=utf-8",
        data:'3',
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert(data);
            editDriverData(obj.userData[0]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}

function drawRowDrivers(rowData, table) {
    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("id :" + rowData.driverId + " name :" + rowData.name);
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        alert(rowData.driverId);
        editDriver();
    };
    div.appendChild(createDiv);
}

function addDriver(){
    alert("driver addid");
}

function addCar(){
    alert("carsdffdjkg");
}
