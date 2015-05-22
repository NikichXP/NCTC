/**
 * Created by Ubuntu on 20.05.2015.
 */
$(document).ready(function () {
    getUrlVars();
    getOrderById(getUrlVars()["id"]);
});

function getOrderById(id) {
    $.get("api/order/view?id=" + id, function (data) {
        var obj = JSON.parse(data);
        $("#requestedSeatsCount").attr("value", obj.requestedSeatsCount);
        $("#fromAddress").attr("value", obj.fromAddress);
        $("#fromX").attr("value", obj.fromX);
        $("#fromY").attr("value", obj.fromY);

        //TODO add dynamic generation
        $("#toAddress0").attr("value", obj.toAddress[0]);
        $("#toX0").attr("value", obj.toX[0]);
        $("#toY0").attr("value", obj.toY[0]);
        $("#distance0").attr("value", obj.distance[0]);

        $("#timeRequested").attr("value", obj.timeRequested);

        $('input:radio[name=sex][data-value=' + obj.sex + ']').prop('checked', true);

        //TODO override getCarClass() method dynamic generation in createorder.js : carClass = disabled. Make copy file.
        $('input:radio[name=carClass][data-value=' + obj.carClass + ']').prop('checked', true);

        $("#music").attr("value", obj.musicType);

        if(obj.smokingFriendly == "true") {
            $('input:checkbox[id=smokingFriendly]').prop('checked', true);
        }
        if(obj.animalFriendly == "true") {
            $('input:checkbox[id=animalFriendly]').prop('checked', true);
        }
        if(obj.wifi == "true") {
            $('input:checkbox[id=wifi]').prop('checked', true);
        }
        if(obj.airConditioner == "true") {
            $('input:checkbox[id=airConditioner]').prop('checked', true);
        }

        //TODO this value is empty
        $("#totalMultiplier").attr("value", obj.totalMultiplier);
        $("#totalLength").attr("value", obj.totalLength);
        $("#totalPrice").attr("value", obj.totalPrice);

        $("#customerPreCreateComment").attr("value", obj.customerPreCreateComment);

        //TODO don't work map operation: need make function makeSearch(element) but i could not run it.
    });
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}
