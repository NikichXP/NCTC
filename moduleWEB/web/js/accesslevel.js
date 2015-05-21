/**
 * Created by Ubuntu on 11.05.2015.
 */

function getAccessLevels() {
    var uuid = getCookie("uuid");
    $.ajax({
        method: 'POST',
        url: 'api/user/getAccessLevelsByUuid',
        contentType: "text/plain; charset=utf-8",
        data: uuid,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            var str = "";
            for (var i = 0; i < obj.userAccessLevel.length; i++) {
                str = str + '<a href="' + obj.userAccessLevel[i].level
                    + '.jsp">Login like: '
                    + obj.userAccessLevel[i].level
                    + '</a>' + '<br>';
            }
            document.getElementById("accessLevel").innerHTML = str;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(uuid + " Error!");
        }
    })
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}