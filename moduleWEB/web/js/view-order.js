/**
 * Created by Ubuntu on 20.05.2015.
 */
$(document).ready(function () {
    getUrlVars();
    getOrderById(getUrlVars()["id"]);
});

function getOrderById(id) {
    $.get("api/order/view?id=" + id, function (data) {
        alert(data);
        var obj = JSON.parse(data);
        alert(obj.fromAddress);
        $("#requestedSeatsCount").attr("value", obj.requestedSeatsCount);

    });
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
