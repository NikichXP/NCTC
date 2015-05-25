/**
 * Created by Евгений on 24.05.2015.
 */
$(document).ready(function(){
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
            alert("1");
            var obj = JSON.parse(data);
            var str = 'OrderTypeRate:<br><input id="orderTypeRate" value="' + obj.orderTypeRate + '" /><br>1-Day,2-Night<br>';
            for (var i = 0; i < obj.hourlyRates.length-1; i++) {
                var innerObj = obj.hourlyRates[i];
                str += (i+1)+':<br><input id="multiplier" value="' + innerObj.multiplier + '" /><br>';
            }

            for (var i = 0; i < obj.distanceRates.length-1; i++) {
                var innerObj = obj.distanceRates[i];
                str += (i+1)+'<br><input id="multiplier" value="' + innerObj.multiplier + '" /><br>';
            }

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
function getOrderTypeMultipliers(){
    $.ajax({
        method: 'POST',
        url: 'api/tariff/getOrderTypeMultipliers',
        contentType: "text/plain; charset=utf-8",

        dataType: 'text',
        success: function (data) {
            var obj = JSON.parse(data);
            alert(data);
            var str ='Basic:<input id="basic" value="'+obj.OrderTypeMultipliers[0].basic+'"/><button id="basicButton"></button><br>' +
                'Cargo:<input id="cargo" value="'+obj.OrderTypeMultipliers[0].cargo+'"/><button id="cargoButton"></button><br>' +
                'Sober driver:<input id="sober" value="'+obj.OrderTypeMultipliers[0].sober_driver+'"/><button id="soberButton"></button><br>' +
                'Guest delivery:<input id="guest" value="'+obj.OrderTypeMultipliers[0].guest_delivery+'"/><button id="guestButton"></button><br>' +
                'Meet my guest:<input id="meet" value="'+obj.OrderTypeMultipliers[0].meet_my_guest+'"/><button id="meetButton"></button><br>' +
                'Celebration taxi:<input id="celebration" value="'+obj.OrderTypeMultipliers[0].celebration_taxi+'"/><button id="celebrationButton"></button><br>'
            document.getElementById("OrderTypeRates").innerHTML = str;

            $(":button").click(function () {
                var str = $(this).attr("id");
                str=str.replace("Button","");


                $.ajax({
                    method: 'POST',
                    url: 'api/tariff/'+$(this).attr("id"),
                    contentType: "text/plain; charset=utf-8",
                    data:  $("#"+str).val(),//is not right , use substring to this.attr("id")

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
