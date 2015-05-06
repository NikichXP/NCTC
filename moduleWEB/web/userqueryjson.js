/**
 * Created by Ubuntu on 06.05.2015.
 */
var user = {
    name: "Alex"
};
jQuery.ajax({
    type: "POST",
    url: "api/test/user",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(user),
    dataType: "json",
    success: function (result) {
        alert('Время: ' + result.time
        + ', сообщенеи: ' + result.message);
    },
    error: function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.status + ' ' + jqXHR.responseText);
    }
});