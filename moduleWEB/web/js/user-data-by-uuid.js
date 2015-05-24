$(document).ready(function () {
    setUserDataInHeader();
});

function setUserDataInHeader() {
    var uuid = getCookie("uuid");
    if (uuid != null) {
        $.ajax({
            method: 'POST',
            url: 'api/user/getUserDataByUuid',
            contentType: "text/plain; charset=utf-8",
            data: uuid,
            dataType: 'text',
            success: function (data, textStatus, jqXHR) {
                var obj = JSON.parse(data);
                setData(obj.userData);
            },
            error: function (jqXHR, textStatus, errorThrown) {
               alert(uuid + "Error!");
            }
        })
    }
}

function setData(data) {
    document.getElementById("name-line").innerHTML = "Hello " + data[0].name;
    document.getElementById("phone-line").innerHTML = "phone " + data[0].phone;
    document.getElementById("email-line").innerHTML = "email " + data[0].myMail;
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

