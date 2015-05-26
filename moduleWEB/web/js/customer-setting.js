/**
 * Created by Ubuntu on 26.05.2015.
 */
$(document).ready(function () {
    getDataToUserField();
});

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
    //document.location.href = "customer.jsp"
})

function editUser(stringify) {
    $.ajax({
        method: 'POST',
        url: "api/user/editUserByUUID",
        contentType: "application/json; charset=utf-8",
        data: stringify,
        dataType:'text',
        success: function (data,textStatus,jqXHR ) {
           alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
    })

}
