$(document).ready(function () {
    setAssetManager('api/admin_asset_manager/cars', "cars");
    setAssetManager('api/admin_asset_manager/drivers', "drivers");
    getCarClass();
    getDriverCategory();
});

var count;
function setAssetManager(url, table) {
    $.ajax({
        method: 'POST',
        url: url,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            drawTable(obj.dataEntity, table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}

function drawTable(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i], table, i);
    }
}
function drawRow(rowData, table, i) {
    count = i;
    div = document.getElementById(table);
    var createMainDiv = document.createElement("div");
    createMainDiv.class = "main_div"
    createMainDiv.id = i;
    var createDiv = document.createElement("div");
    createDiv.className = "button";
    var node = document.createTextNode("id :" + rowData.id + " name :" + rowData.name);
    createDiv.appendChild(node);
    createDiv.onclick = function () {
        editCarById(rowData.id);
    };
    createMainDiv.appendChild(createDiv);
    div.appendChild(createMainDiv);

}

function createSettingOptionForCar(mainDiv) {
    var ul = document.createElement("ul");
    var li1 = document.createElement("li");
    var li2 = document.createElement("li");
    var li3 = document.createElement("li");
    var li4 = document.createElement("li");
    var li5 = document.createElement("li");
    var li6 = document.createElement("li");
    var li7 = document.createElement("li");

    var model = document.createElement("input");
    model.id = "model";
    model.type = "text-field";
    model.placeholder = "model"
    var seatCount = document.createElement("input");
    seatCount.id = "seatCount";
    seatCount.type = "text-field";
    seatCount.placeholder = "seat count"
    var licencePlate = document.createElement("input");
    licencePlate.id = "licencePlate";
    licencePlate.type = "text-field";
    licencePlate.placeholder = "licence plate"
    var userId = document.createElement("input");
    userId.id = "userId";
    userId.type = "text-field";
    userId.placeholder = "driver id"
    getCarClass(li5);
    getDriverCategory(li6);
    getAir(li7);
    li1.appendChild(model);
    li2.appendChild(seatCount);
    li3.appendChild(licencePlate);
    li4.appendChild(userId);
    ul.appendChild(li1);
    ul.appendChild(li2);
    ul.appendChild(li3);
    ul.appendChild(li4);
    ul.appendChild(li5);
    ul.appendChild(li6);
    ul.appendChild(li7);
    mainDiv.appendChild(ul);
}


function editCarById(carId) {
    $.ajax({
        method: 'POST',
        url: 'api/car_car/getCarDataById',
        contentType: "text/plain; charset=utf-8",
        data: carId,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}
function drawFormCar(rowData) {
    document.getElementById("idForCar").value = rowData.carId;
    document.getElementById("model").value = rowData.model;
    document.getElementById("seatCount").value = rowData.countSeat;
    document.getElementById("licencePlate").value = rowData.licencePlate;
    document.getElementById("classCar").value = rowData.classCar;
    document.getElementById("userDriverId").value = rowData.carDriverId;
    document.getElementById("requiredDriverCategory").value = rowData.requiredDriverCategory;
    document.getElementById("conditioner").value = rowData.conditioner;
}

//function editDriverById(driverId){
//    $.ajax({
//        method: 'POST',
//        url: 'api/user/getUserDataById',
//        contentType: "text/plain; charset=utf-8",
//        data: driverId,
//        dataType: 'text',
//        success: function (data, textStatus, jqXHR) {
//            var obj = JSON.parse(data);
//            drawFormDriver(obj.userData[0]);
//        },
//        error: function (jqXHR, textStatus, errorThrown) {
//            alert(jqXHR.responseText + " Error!");
//        }
//    })
//
//}
//function drawFormDriver(rowData){
//    document.getElementById("idForDriver").value = rowData.userId;
//    document.getElementById("firstName").value = rowData.firstName;
//    document.getElementById("lastName").value = rowData.lastName;
//    document.getElementById("phone").value = rowData.phone;
//    document.getElementById("email").value = rowData.email;
//    document.getElementById("regpass").value = rowData.getPassword;
//}
//
//function addDriver(){
//    var JSONdata = {
//        firstName: $("#firstName").val(),
//        lastName: $("#lastName").val(),
//        phone: $("#phone").val(),
//        email: $("#email").val(),
//        carId: $("#carId").val(),
//        pass: $("#regpass").val()
//    };
//    setDriver(JSONdata);
//}
//function editDriver(){
//    var JSONdata = {
//        id: $("#idForDriver").val(),
//        firstName: $("#firstName").val(),
//        lastName: $("#lastName").val(),
//        phone: $("#phone").val(),
//        email: $("#email").val(),
//        carId: $("#carId").val(),
//        pass: $("#regpass").val()
//    };
//    setDriver(JSONdata);
//}
//function setDriver(JSONdata){
//    $.ajax({
//        method: 'POST',
//        url: "api/user/create_driver",
//        contentType: "application/json; charset=utf-8",
//        data: JSON.stringify(JSONdata),
//        dataType:'text',
//        success: function (data,textStatus,jqXHR ) {
//            alert(data);
//        },
//        error: function (jqXHR, textStatus, errorThrown) {
//            alert("Wrong user credentials.");
//        }
//    })
//
//
//}
//function deleteDriver(){
//    var id = $("#idForDriver").val();
//    var JSONdata = {
//        id: $("#idForDriver").val()
//    };
//    $.ajax({
//        method: 'POST',
//        url: "api/user/delete",
//        contentType: "application/json; charset=utf-8",
//        data: JSON.stringify(JSONdata),
//        dataType:'text',
//        success: function (data,textStatus,jqXHR ) {
//            alert(data);
//        },
//        error: function (jqXHR, textStatus, errorThrown) {
//            alert("Wrong user credentials.");
//        }
//    })
//}

function addCar() {

    if ($('#model').length > 0) {
        var JSONdata = {
            model: $("#model").val(),
            userId: $("#userId").val(),
            seatCount: $("#seatCount").val(),
            classId: $("#classCar").val(),
            licencePlate: $("#licencePlate").val(),
            requiredDriverCategory: $("#requiredDriverCategory").val(),
            airConditioner: $("#conditioner").val()
        };
        setCar(JSONdata);
    } else {
        var getDiv = document.getElementById("addSettingCar")
        createSettingOptionForCar(getDiv);
    }


}
function editCar() {
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
function setCar(JSONdata) {
    $.ajax({
        method: 'POST',
        url: "api/car_car/create_car",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Wrong user credentials.");
        }
    })
}
function deleteCar() {
    var id = $("#idForCar").val();
    var JSONdata = {
        id: $("#idForCar").val()
    };
    $.ajax({
        method: 'POST',
        url: "api/car_car/delete",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Wrong user credentials.");
        }
    })
}

function getAir(mainDiv) {
    var select = document.createElement("select");
    select.id = "conditioner";
    var option = document.createElement("option");
    option.value = "true";
    var node = document.createTextNode("true");
    option.appendChild(node);
    select.appendChild(option);
    option = document.createElement("option");
    option.value = "false";
    node = document.createTextNode("false");
    option.appendChild(node);
    select.appendChild(option);
    mainDiv.appendChild(select);
}

function getCarClass(mainDiv) {
    $.ajax({
        method: 'POST',
        url: 'api/car/class',
        dataType: 'text',
        success: function (data) {
            var select = document.createElement("select");
            select.id = "classCar";
            var option;
            var node;
            var obj = JSON.parse(data);
            for (var i = 0; i < obj.carClass.length; i++) {
                option = document.createElement("option");
                option.value = obj.carClass[i].name;
                node = document.createTextNode(obj.carClass[i].name);
                option.appendChild(node);
                select.appendChild(option);
            }
            mainDiv.appendChild(select)
        },
        error: function (jqXHR) {
            alert("Bad response from server.\n" + jqXHR.responseText);
        }
    })
}

function getDriverCategory(mainDiv) {
    $.ajax({
        method: 'POST',
        url: 'api/admin_asset_manager/driver_category',
        dataType: 'text',
        success: function (data) {
            var select = document.createElement("select");
            select.id = "requiredDriverCategory";
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
            mainDiv.appendChild(select)
        },
        error: function (jqXHR) {
            alert("Bad response from server.\n" + jqXHR.responseText);
        }
    })
}