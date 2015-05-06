/**
 * Created by Ubuntu on 06.05.2015.
 */
var user = {
    name: "Alex"
};
jQuery.ajax({
    type: "POST",
    url: "api/user/login",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(user),
    dataType: "json",
    success: function (result) {
        alert('Time: ' + result.time
        + ', message: ' + result.message);
    },
    error: function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.status + ' ' + jqXHR.responseText);
    }
});