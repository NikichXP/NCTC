$(document).ready(function () {
    setUserDataInPlaceHolder();
});

function setUserDataInPlaceHolder() {
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
                alert(uuid + " Error!");
            }
        })
    }
}

function setData(data) {
    document.getElementById("contactName").value = data[0].name;
    document.getElementById("contactPhone").value = data[0].phone;
}

function getCookie(name) {
    var cookie = " " + document.cookie;
    var search = " " + name + "=";
    var setStr = null;
    var offset = 0;
    var end = 0;
    if (cookie.length > 0) {
        offset = cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = cookie.indexOf(";", offset)
            if (end == -1) {
                end = cookie.length;
            }
            setStr = decodeURI(cookie.substring(offset, end));
        }
    }
    return (setStr);
}

