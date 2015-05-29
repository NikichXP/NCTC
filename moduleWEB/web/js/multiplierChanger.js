$(document).ready(function () {
    getTariffs("basic");
    getOrderTypeMultipliers();

});

function getTariffs(orderTypeName) {
    $.ajax({
        method: 'POST',
        url: 'api/tariff/getAll',
        contentType: "text/plain; charset=utf-8",
        data: orderTypeName,
        dataType: 'text',
        success: function (data) {
            var obj = JSON.parse(data);
            var i;

            var day;
            var night;
            for (i = 0; i < obj.hourlyRates.length; i++) {
                if (obj.hourlyRates[i].fromTimeHHmm == "07:00") day = obj.hourlyRates[i].multiplier;
                else night = obj.hourlyRates[i].multiplier;

            }


            var str = '<table border="1" align="center" style="border-color: #0099FF"><tr><td>Day hourly rate</td><td><input id="dayHourlyRate" value="' + day + '" /></td><td><button id="dayHourlyRateButton" style="width: 70px;height:30px; background-color: #0099FF ">Modify</button></td></tr>';

            str += '<tr><td>Night hourly rate</td><td><input id="nightHourlyRate" value="' + night + '" /></td><td><button id="nightHourlyRateButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr>';

            for (i = 0; i < obj.distanceRates.length; i++) {
                if (obj.distanceRates[i].fromTimeHHmm == "07:00") day = obj.distanceRates[i].multiplier;
                else night = obj.distanceRates[i].multiplier;

            }


            str += '<tr><td>Day distance rate</td><td><input id="dayDistanceRate" value="' + day + '" /></td><td><button id="dayDistanceRateButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr>';

            str += '<tr><td>Night distance rate</td><td><input id="nightDistanceRate" value="' + night + '" /></td><td><button id="nightDistanceRateButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr></table>';


            document.getElementById("tariffs").innerHTML = str;

            $("[data-value='Male']").attr("multiplier", obj.male);
            $("[data-value='Female']").attr("multiplier", obj.female);
            $("[data-value='Any']").attr("multiplier", obj.any);
            $("#smokingFriendly").attr("multiplier", obj.smokingFriendly);
            $("#animalFriendly").attr("multiplier", obj.animalFriendly);
            $("#wifi").attr("multiplier", obj.wifi);
            $("#airConditioner").attr("multiplier", obj.airConditioner);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " " + errorThrown);
        }
    })
}
function getOrderTypeMultipliers() {
    $.ajax({
        method: 'POST',
        url: 'api/tariff/getOrderTypeMultipliers',
        contentType: "text/plain; charset=utf-8",

        dataType: 'text',
        success: function (data) {
            var obj = JSON.parse(data);

            var str = '<table align="center" border="1" style="border-color: #0099FF"><tr><td>Basic:</td><td><input id="basic" value="' + obj.OrderTypeMultipliers[0].basic + '"/></td><td><button id="basicButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr>' +
                '<tr><td>Cargo:</td><td><input id="cargo" value="' + obj.OrderTypeMultipliers[0].cargo + '"/></td><td><button id="cargoButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr>' +
                '<tr><td>Sober driver:</td><td><input id="sober" value="' + obj.OrderTypeMultipliers[0].sober_driver + '"/></td><td><button id="soberButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr>' +
                '<tr><td>Guest delivery:</td><td><input id="guest" value="' + obj.OrderTypeMultipliers[0].guest_delivery + '"/></td><td><button id="guestButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr>' +
                '<tr><td>Meet my guest:</td><td><input id="meet" value="' + obj.OrderTypeMultipliers[0].meet_my_guest + '"/></td><td><button id="meetButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr>' +
                '<tr><td>Celebration taxi:</td><td><input id="celebration" value="' + obj.OrderTypeMultipliers[0].celebration_taxi + '"/></td><td><button id="celebrationButton" style="width: 70px;height:30px ; background-color: #0099FF">Modify</button></td></tr></table>'
            document.getElementById("OrderTypeRates").innerHTML = str;

            $(":button").click(function () {
                var str = $(this).attr("id");
                str = str.replace("Button", "");


                $.ajax({
                    method: 'POST',
                    url: 'api/tariff/' + $(this).attr("id"),
                    contentType: "text/plain; charset=utf-8",
                    data: $("#" + str).val(),

                    dataType: 'text',
                    success: function (data) {


                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(jqXHR.responseText + " " + errorThrown);
                    }
                })

            });

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " " + errorThrown);
        }
    })

}
