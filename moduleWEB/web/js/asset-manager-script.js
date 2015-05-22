$(document).ready(function () {
    setAssetManagerCars();
    setAssetManagerDrivers();
    getCarClass();
    getDriverCategory();
});

function setAssetManagerCars() {
    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/cars',
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
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
    myCarId = rowData.carId;
    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("id :" + rowData.carId + " model :" + rowData.model);
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        editCarById(rowData.carId);
    };
    div.appendChild(createDiv);
}
function editCarById(carId){
    $.ajax({
        method: 'POST',
        url: 'api/car_car/getCarDataById',
        contentType: "text/plain; charset=utf-8",
        data: carId,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            //alert(data);
            drawFormCar(obj.carData[0]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}
function drawFormCar(rowData){
    document.getElementById("idForCar").value = rowData.carId;
    document.getElementById("model").value = rowData.model;
    document.getElementById("seatCount").value = rowData.countSeat;
    document.getElementById("licencePlate").value = rowData.licencePlate;
    document.getElementById("classCar").value = rowData.classCar;
    document.getElementById("userDriverId").value = rowData.carDriverId;
    document.getElementById("requiredDriverCategory").value = rowData.requiredDriverCategory;
    document.getElementById("conditioner").value = rowData.conditioner;
}

function setAssetManagerDrivers() {

    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/drivers',
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
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
        editDriverById(rowData.driverId);
    };
    div.appendChild(createDiv);
}
function editDriverById(driverId){
    $.ajax({
        method: 'POST',
        url: 'api/user/getUserDataById',
        contentType: "text/plain; charset=utf-8",
        data: driverId,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            drawFormDriver(obj.userData[0]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })

}
function drawFormDriver(rowData){
    document.getElementById("idForDriver").value = rowData.userId;
    document.getElementById("firstName").value = rowData.firstName;
    document.getElementById("lastName").value = rowData.lastName;
    document.getElementById("phone").value = rowData.phone;
    document.getElementById("email").value = rowData.email;
    document.getElementById("regpass").value = rowData.getPassword;
}

function addDriver(){
    var JSONdata = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        phone: $("#phone").val(),
        email: $("#email").val(),
        carId: $("#carId").val(),
        pass: $("#regpass").val()
    };
    //alert(JSON.stringify(JSONdata))
    setDriver(JSONdata);
}
function editDriver(){
    var JSONdata = {
        id: $("#idForDriver").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        phone: $("#phone").val(),
        email: $("#email").val(),
        carId: $("#carId").val(),
        pass: $("#regpass").val()
    };
    setDriver(JSONdata);
}
function setDriver(JSONdata){
    $.ajax({
        method: 'POST',
        url: "api/user/create_driver",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType:'text',
        success: function (data,textStatus,jqXHR ) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Wrong user credentials.");
        }
    })


}
function deleteDriver(){
    var id = $("#idForDriver").val();
    var JSONdata = {
        id: $("#idForDriver").val()
    };
    $.ajax({
        method: 'POST',
        url: "api/user/delete",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType:'text',
        success: function (data,textStatus,jqXHR ) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Wrong user credentials.");
        }
    })
}

function addCar(){
    var JSONdata = {
        model: $("#model").val(),
        userId: $("#userDriverId").val(),
        seatCount: $("#seatCount").val(),
        classId: $("#classCar").val(),
        licencePlate: $("#licencePlate").val(),
        requiredDriverCategory: $("#requiredDriverCategory").val(),
        airConditioner: $("#conditioner").val()
    };
    setCar(JSONdata);

}
function editCar(){
    var JSONdata = {
        id: $("#idForCar").val(),
        model: $("#model").val(),
        userId: $("#userDriverId").val(),
        seatCount: $("#seatCount").val(),
        classId: $("#classCar").val(),
        licencePlate: $("#licencePlate").val(),
        requiredDriverCategory: $("#requiredDriverCategory").val(),
        airConditioner: $("#conditioner").val()
    };
    //alert(JSON.stringify(JSONdata))
    setCar(JSONdata);
}
function setCar(JSONdata){
    $.ajax({
        method: 'POST',
        url: "api/car_car/create_car",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType:'text',
        success: function (data,textStatus,jqXHR ) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Wrong user credentials.");
        }
    })
}
function deleteCar(){
    var id = $("#idForCar").val();
    var JSONdata = {
        id: $("#idForCar").val()
    };
    $.ajax({
        method: 'POST',
        url: "api/car_car/delete",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType:'text',
        success: function (data,textStatus,jqXHR ) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Wrong user credentials.");
        }
    })
}

function getCarClass() {
    $.ajax({
        method: 'POST',
        url: 'api/car/class',
        dataType: 'text',
        success: function (data) {
            var select = document.getElementById("classCar");
            var option;
            var node;
            var obj = JSON.parse(data);
            for (var i = 0; i < obj.carClass.length; i++) {
                option = document.createElement("option");
                option.value = obj.carClass[i].name;
                node = document.createTextNode(obj.carClass[i].name);
                option.appendChild(node);
                select.appendChild(option)
            }

            option.appendChild(node);
            select.appendChild(option);


            document.getElementById("carClass").innerHTML = str;
        },
        error: function (jqXHR) {
            alert("Bad response from server.\n" + jqXHR.responseText);
        }
    })
}

function getDriverCategory() {
    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/driver_category',
        dataType: 'text',
        success: function (data) {
            var select = document.getElementById("requiredDriverCategory");
            var option;
            var node;
            var obj = JSON.parse(data);
            for (var i = 0; i < obj.driverCategoryClass.length; i++) {
                option = document.createElement("option");
                option.value = obj.driverCategoryClass[i].name;
                node = document.createTextNode(obj.driverCategoryClass[i].name);
                option.appendChild(node);
                select.appendChild(option)
            }

            option.appendChild(node);
            select.appendChild(option);


            document.getElementById("carClass").innerHTML = str;
        },
        error: function (jqXHR) {
            alert("Bad response from server.\n" + jqXHR.responseText);
        }
    })
}