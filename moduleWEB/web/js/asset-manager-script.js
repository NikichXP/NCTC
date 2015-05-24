$(document).ready(function () {
    setAssetManager('api/admin_asset_manager/cars', "cars");
});

var fix = false;

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
        drawRow(data[i], table);
    }
}
function drawRow(rowData, table) {
    var div = document.getElementById(table);
    var createMainDiv = document.createElement("div");
    createMainDiv.class = "main_div"
    var createDiv = document.createElement("div");
    createDiv.className = "button";
    var node = document.createTextNode("id :" + rowData.id + " name :" + rowData.name);
    createDiv.appendChild(node);
    createDiv.onclick = function () {
        onEntityClick(rowData.id, createMainDiv);
    };
    createMainDiv.appendChild(createDiv);
    div.appendChild(createMainDiv);

}
function onEntityClick(createDiv, createMainDiv) {
    if (fix == true) {
        removeAll();
    } else {
        createSettingOptionForCar(createMainDiv);
        createEditCar(createMainDiv, createDiv);
    }
    //removeAll();

}


function createEditCar(createMainDiv, createDiv) {
    var edit = document.createElement("div");
    edit.className = "button";
    edit.id = "edit-my-car";
    edit.appendChild(document.createTextNode("EDIT"));
    edit.onclick = function () {

        var JSONdata = {
            id: createDiv,
            model: $("#model").val(),
            userId: $("#userId").val(),
            seatCount: $("#seatCount").val(),
            classId: $("#classCar").val(),
            licencePlate: $("#licencePlate").val(),
            requiredDriverCategory: $("#requiredDriverCategory").val(),
            airConditioner: $("#conditioner").val()
        };
        alert(JSON.stringify(JSONdata))
        setCar(JSONdata);
        removeAll();
    }
    createMainDiv.appendChild(edit);
    setEditCarById(createDiv);
}
function setEditCarById(carId) {
    $.ajax({
        method: 'POST',
        url: 'api/car_car/getCarDataById',
        contentType: "text/plain; charset=utf-8",
        data: carId,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert(data);
            drawInputForCar(obj.carData[0]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}
function drawInputForCar(rowData) {
    document.getElementById("model").value = rowData.model;
    document.getElementById("seatCount").value = rowData.countSeat;
    document.getElementById("licencePlate").value = rowData.licencePlate;
    //document.getElementById("classCar").value = rowData.classCar;
    document.getElementById("userId").value = rowData.carDriverId;
    //document.getElementById("requiredDriverCategory").value = rowData.requiredDriverCategory;
    document.getElementById("conditioner").value = rowData.conditioner;
}


function removeAll() {
    $("#fixind-feald").remove();
    $("#add-my-car").remove();
    $("#edit-my-car").remove();
    fix = false;
}

function createSettingOptionForCar(mainDiv) {
    var ul = document.createElement("ul");
    ul.id = "fixind-feald";
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
    fix = true;
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


//----------------------------------------------------------------------------------------------------------------------
function addCarSection() {
    if (fix == true) {
        removeAll();
    } else {
        var getDiv = document.getElementById("addSettingCar")
        createSettingOptionForCar(getDiv);
        createAddCar(getDiv);
    }
}
function createAddCar(getDiv) {
    var add = document.createElement("div");
    add.className = "button";
    add.id = "add-my-car";
    add.appendChild(document.createTextNode("ADD"))
    add.onclick = function () {
        var JSONdata = {
            model: $("#model").val(),
            userId: $("#userId").val(),
            seatCount: $("#seatCount").val(),
            classId: $("#classCar").val(),
            licencePlate: $("#licencePlate").val(),
            requiredDriverCategory: $("#requiredDriverCategory").val(),
            airConditioner: $("#conditioner").val()
        };
        alert(JSON.stringify(JSONdata));
        setCar(JSONdata);
        removeAll();
    }
    getDiv.appendChild(add);
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
            alert("Wrong car credentials.");
        }
    })
}

//----------------------------------------------------------------------------------------------------------------------
//Get Selects
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