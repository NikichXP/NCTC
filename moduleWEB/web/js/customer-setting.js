/**
 * Created by Ubuntu on 26.05.2015.
 */
$(document).ready(function () {
    getDataToUserField();
});

var phoneRegEx = /^\+?[0-9]{6,12}$/;
var namesRegEx = /^[a-zA-Z\s'\-]+$/;
var emailRegEx = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
var passRegEx = /^....+$/;

//TODO need add publicToken validation
function validateData() {
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
    if (!validateNames($("#newPassword").val(), passRegEx)) {
        alert("Password:\nPassword must be at least 4 characters long.");
        return false;
    }
    if ($("#confirmPassword").val() !== $("#newPassword").val()) {
        alert("Confirm password:\nPasswords don't match.");
        return false;
    }
    return true;
}

function validateNames(input, regEx) {
    if (input.length > 0) {
        return regEx.test(input);
    }
    return false;
}

function getDataToUserField() {
    var uuid = getCookie("uuid");
    if (uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/user/getAllUserByUUID',
            contentType: "text/plain; charset=utf-8",
            data: uuid,
            dataType: 'text',
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                alert(data);
                setDataToUserField(obj.userData[0]);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(uuid + "Error!");
            }
        })
    }
}

function setDataToUserField(date) {
    $("#firstName").val(date.firstName);
    $("#lastName").val(date.lastName);
    $("#phone").val(date.phone);
    $("#alternativePhone").val(date.alternativePhone);
    $("#email").val(date.email);
    $("#newPassword").val(date.newPassword);
    $("#confirmPassword").val(date.confirmPassword);
    $("#userSex").val(date.userSex);
    $("#animalFriendly").val(date.animalFriendly);
    $("#smokingFriendly").val(date.smokingFriendly);
}


$("#back").click(function () {
    document.location.href = "customer.jsp";
})

$("#edit").click(function () {
    if (validateData()) {
        var JSONdata = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            uuid: getCookie("uuid"),
            phone: $("#phone").val(),
            alternativePhone: $("#alternativePhone").val(),
            email: $("#email").val(),
            pass: $("#newPassword").val(),
            userSex: $("#userSex").val(),
            animalFriendly: $("#animalFriendly").val(),
            smokingFriendly: $("#smokingFriendly").val()
        };
        alert(JSON.stringify(JSONdata))
        editUser(JSON.stringify(JSONdata))
        document.location.href = "customer.jsp"
    }

})

function editUser(stringify) {
    $.ajax({
        method: 'POST',
        url: "api/user/editUserByUUID",
        contentType: "application/json; charset=utf-8",
        data: stringify,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
    })

}
