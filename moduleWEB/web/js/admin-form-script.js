/**
 * Created by Евгений on 18.05.2015.
 */

$(document).ready(function () {
    setForm();
});

function setForm() {

    var queryString = window.location.search;
    queryString = queryString.substring(4);

    $.ajax({
        method: 'POST',
        url: 'api/user/getUserDataById',
        contentType: "text/plain; charset=utf-8",
        data: queryString,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert(data);
            drawForm(obj.userData[0], "#form");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
    //  alert( $("#in1").parse);

}


function drawForm(rowData, table) {
    var in1 = document.createElement('input');
    in1.type = 'text';
    in1.value = rowData.firstName;
    in1.id = 'in1';

    var in2 = document.createElement('input');
    in2.type = 'text';
    in2.value = rowData.lastName;
    in2.id = 'in2';

    var in3 = document.createElement('input');
    in3.type = 'text';
    in3.value = rowData.phone;
    in3.id = 'in3';

    var in4 = document.createElement('input');
    in4.type = 'text';
    in4.value = rowData.email;
    in4.id = 'in4';


    $(table).append(in1);
    $(table).append(in2);
    $(table).append(in3);
    $(table).append(in4);


}


$("#test").click(function () {

    alert("driver add....");
    if (!validateRegistrationData()) return;
    alert("12")
    var queryString = window.location.search;
    queryString = queryString.substring(4);
    var JSONdata = {

        id:queryString,
        firstName: $("#in1").val(),
        lastName: $("#in2").val(),
        phone: $("#in3").val(),
        email: $("#in4").val()
    };

    updateUser(JSONdata);

});


var phoneRegEx = /^\+?[0-9]{6,12}$/;
var namesRegEx = /^[a-zA-Z\s'\-]+$/;
var emailRegEx = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;


function validateRegistrationData() {

    //validates login credentials (e-mail or phone number) field
    if (!validateName($("#in1").val(), namesRegEx)) {
        alert("First name:\nPlease, use only alphabetic characters!");
        return false;
    }

    if (!validateName($("#in2").val(), namesRegEx)) {
        alert("Last name:\nPlease, use only alphabetic characters!");
        return false;
    }
    var phone = $("#in3").val();
    phone = phone.replace(/\s/g, "").replace(/\+/g, "");
    if (!validateName(phone, phoneRegEx)) {
        alert("Phone number:\nPlease, use only digits. Length from 6 to 12.");
        return false;
    }
    if (!validateName($("#in4").val(), emailRegEx)) {
        alert("E-mail:\nPlease, enter a valid e-mail.");
        return false;
    }

    return true;
}


function updateUser(JSONdata) {

    alert(JSON.stringify(JSONdata))
    $.ajax({
        method: 'POST',
        url: "api/user/resUpUser",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
            alert("Euegene");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Wrong user credentials.");
        }
    });
}
function validateName(input, regEx) {
    if (input.length > 0) {
        return regEx.test(input);
    }
    return false;
}
$("#delete").click(function () {
    var queryString = window.location.search;
    queryString = queryString.substring(4);
    deleteDriver(queryString);
});


function deleteDriver(id){
    alert(id);
    var JSONdata = {
        id: id
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



