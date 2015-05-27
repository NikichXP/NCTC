$(document).ready(function () {
    setAssetManager('api/admin_asset_manager/cars', "cars");
    setAssetManager('api/admin_asset_manager/drivers', 'drivers');
});

var fix = false;
var idRegEx = /^[0-9]{6,12}$/;
var phoneRegEx = /^\+?[0-9]{6,12}$/;
var namesRegEx = /^[a-zA-Z\s'\-]+$/;
var emailRegEx = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
var passRegEx = /^....+$/;
//var license = /

function validateCar(){

}
function validateRegistrationData() {
    //validates login credentials (e-mail or phone number) field
    if (!validateNames($("#firstName").val(), namesRegEx)) {
        alert("First name:\nPlease, use only alphabetic characters!");
        return false;
    }
    if (!validateNames($("#lastName").val(), namesRegEx)) {
        alert("Last name:\nPlease, use only alphabetic characters!");
        return false;
    }
    var phone = $("#phone").val();
    phone = phone.replace(/\s/g, "").replace(/\+/g, "");
    if (!validateNames(phone, phoneRegEx)) {
        alert("Phone number:\nPlease, use only digits. Length from 6 to 12.");
        return false;
    }
    if (!validateNames($("#email").val(), emailRegEx)) {
        alert("E-mail:\nPlease, enter a valid e-mail.");
        return false;
    }
    if (!validateNames($("#regpass").val(), passRegEx)) {
        alert("Password:\nPassword must be at least 4 characters long.");
        return false;
    }
    if ($("#passconfirm").val() !== $("#regpass").val()) {
        alert("Confirm password:\nPasswords don't match.");
        return false;
    }
    return true;
}
function validateNames (input, regEx) {
    if(input.length > 0){
        return regEx.test(input);
    }
    return false;
}


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
    var node = document.createTextNode(rowData.id + ". " + rowData.name);
    createDiv.appendChild(node);
    if ('cars' == table) {
        createDiv.onclick = function () {
            onCarEntityClick(rowData.id, createMainDiv);
        };
    } else {
        createDiv.onclick = function () {
            onDriverEntityClick(rowData.id, createMainDiv);
        };
    }

    createMainDiv.appendChild(createDiv);
    div.appendChild(createMainDiv);
}


function onCarEntityClick(createDiv, createMainDiv) {
    if (fix == true) {
        removeAll();
    } else {
        createSettingOptionForCar(createMainDiv);
        createEditCar(createMainDiv, createDiv);
    }

}
function onDriverEntityClick(createDiv, createMainDiv) {
    if (fix == true) {
        removeAll();
    } else {
        createSettingOptionForDriver(createMainDiv);
        createEditDriver(createMainDiv, createDiv);
    }

}

function createEditCar(createMainDiv, createDiv) {
    var edit = document.createElement("div");
    var del = document.createElement("div");
    del.className = "button";
    edit.className = "button";
    del.id = "delete-my-car"
    edit.id = "edit-my-car";
    del.appendChild(document.createTextNode("DELETE"))
    edit.appendChild(document.createTextNode("EDIT"));
    del.onclick = function () {
        deleteCar(createDiv);
        removeAll();
        removeChild('drivers');
        setAssetManager('api/admin_asset_manager/drivers', "drivers");
    }
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
    createMainDiv.appendChild(del);
    setEditCarById(createDiv);
}
function deleteCar(carId) {
    var JSONdata = {
        id: carId
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
    removeChild('cars');
    setAssetManager('api/admin_asset_manager/cars', "cars");
}
function setEditCarById(carId) {
    $.ajax({
        method: 'POST',
        url: 'api/car_car/getCarDataById',
        contentType: "text/plain; charset=utf-8",
        data: carId,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
            var obj = JSON.parse(data);
            drawInputForCar(obj.carData[0]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}
function drawInputForCar(rowData) {
    document.getElementById("model").value = rowData.model;
    document.getElementById("seatCount").value = rowData.seatCount;
    document.getElementById("licencePlate").value = rowData.licencePlate;
    //document.getElementById("classCar").value = rowData.classId;
    //document.getElementById("userId").value = rowData.userId;
    //document.getElementById("requiredDriverCategory").value = rowData.requiredDriverCategory;
    document.getElementById("conditioner").value = rowData.airConditioner;
}

function createEditDriver(createMainDiv, createDiv) {
    var edit = document.createElement("div");
    var del = document.createElement("div");
    del.className = "button";
    edit.className = "button";
    del.id = "delete-my-car"
    edit.id = "edit-my-car";
    del.appendChild(document.createTextNode("DELETE"))
    edit.appendChild(document.createTextNode("EDIT"));
    del.onclick = function () {
        deleteDriver(createDiv);
    }
    edit.onclick = function () {
        var JSONdata = {
            id: createDiv,
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            phone: $("#phone").val(),
            email: $("#email").val(),
            carId: $("#carId").val(),
            pass: $("#regpass").val()
        };
        setDriver(JSONdata);
        removeAll();
    }
    createMainDiv.appendChild(edit);
    createMainDiv.appendChild(del);
    setEditDriverById(createDiv);
}
function deleteDriver(myId) {
    var JSONdata = {
        id: myId
    };
    $.ajax({
        method: 'POST',
        url: "api/user/delete",
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
function setDriver(carId) {
    $.ajax({
        method: 'POST',
        url: "api/user/create_driver",
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
function setEditDriverById(driverId) {

    $.ajax({
        method: 'POST',
        url: 'api/user/getUserDataById',
        contentType: "text/plain; charset=utf-8",
        data: driverId,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
            var obj = JSON.parse(data);
            drawInputForDriver(obj.userData[0]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })


}
function drawInputForDriver(rowData) {

    document.getElementById("firstName").value = rowData.firstName;
    document.getElementById("lastName").value = rowData.lastName;
    document.getElementById("phone").value = rowData.phone;
    document.getElementById("email").value = rowData.email;
    document.getElementById("regpass").value = rowData.pass;
    document.getElementById("passconfirm").value = rowData.pass;

}


function removeAll() {
    $("#fixind-feald").remove();
    $("#add-my-car").remove();
    $("#edit-my-car").remove();
    $("#delete-my-car").remove();
    fix = false;
}
function removeChild(element){
    var myNode = document.getElementById(element);
    var fc = myNode.firstChild;

    while( fc ) {
        myNode.removeChild( fc );
        fc = myNode.firstChild;
    }
}

function createSettingOptionForDriver(mainDiv) {
    var ul = document.createElement("ul");
    ul.id = "fixind-feald";
    var li1 = document.createElement("li");
    var li2 = document.createElement("li");
    var li3 = document.createElement("li");
    var li4 = document.createElement("li");
    var li5 = document.createElement("li");
    var li6 = document.createElement("li");
    var li7 = document.createElement("li");
    var ferstName = document.createElement("input");
    ferstName.id = "firstName";
    ferstName.type = "text-field";
    ferstName.placeholder = "ferst name"
    var secondName = document.createElement("input");
    secondName.id = "lastName";
    secondName.type = "text-field";
    secondName.placeholder = "second name"
    var phone = document.createElement("input");
    phone.id = "phone";
    phone.type = "text-field";
    phone.placeholder = "phone"
    var email = document.createElement("input");
    email.id = "email";
    email.type = "text-field";
    email.placeholder = "e-mail"
    var regpass = document.createElement("input");
    regpass.id = "regpass";
    regpass.type = "password";
    var passconfirm = document.createElement("input");
    passconfirm.id = "passconfirm";
    passconfirm.type = "password";
    var carId = document.createElement("input");
    carId.id = "carId";
    carId.type = "text-field";
    carId.placeholder = "car id";

    li1.appendChild(ferstName);
    li2.appendChild(secondName);
    li3.appendChild(phone);
    li4.appendChild(email);
    li5.appendChild(regpass);
    li6.appendChild(passconfirm);
    li7.appendChild(carId);

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
            alert(jqXHR + "Error!!");
        }
    })
}

function addDriverSection() {
    if (fix == true) {
        removeAll();
    } else {
        var getDiv = document.getElementById("addSettingDriver")
        createSettingOptionForDriver(getDiv);
        createAddDriver(getDiv);
    }
}
function createAddDriver(getDiv) {
    var add = document.createElement("div");
    add.className = "button";
    add.id = "add-my-car";
    add.appendChild(document.createTextNode("ADD"))
    add.onclick = function () {
        if (validateRegistrationData()) {
            var JSONdata = {
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                phone: $("#phone").val(),
                email: $("#email").val(),
                carId: $("#carId").val(),
                pass: $("#regpass").val()
            };
            alert(JSON.stringify(JSONdata))
            setDriver(JSONdata);
            removeAll();
            removeChild('drivers')
            alert("new alert");
            setAssetManager('api/admin_asset_manager/drivers', 'drivers');
        } else {

        }
    }
    getDiv.appendChild(add);
}
function setDriver(JSONdata) {
    $.ajax({
        method: 'POST',
        url: "api/user/create_driver",
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

//----------------------------------------------------------------------------------------------------------------------
//Get
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
        url: 'api/driverCategory/getCategory',
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