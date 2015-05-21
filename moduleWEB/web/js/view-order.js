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
        $("#requestedSeatsCount").attr("value", obj.requestedSeatsCount);
        $("#fromAddress").attr("value", obj.fromAddress);
        $("#fromX").attr("value", obj.fromX);
        $("#fromX").attr("value", obj.fromY);

        $("#toAddress0").attr("value", obj.toAddress[0]);

    });
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
