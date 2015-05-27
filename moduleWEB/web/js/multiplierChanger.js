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
            var obj = JSON.parse(data);
            var i;

            var day;
            var night;
            for(i=0;i<obj.hourlyRates.length;i++){
                if(obj.hourlyRates[i].fromTimeHHmm=="07:00" ) day=obj.hourlyRates[i].multiplier;
                else night=obj.hourlyRates[i].multiplier;

            }


          //  var str = 'OrderTypeRate:<br><input id="orderTypeRate" value="' + obj.orderTypeRate + '" /><br>1-Day,2-Night<br>';
             alert(data);


              var  str = "1"+':<br><input id="dayHourlyRate" value="' + day + '" /><button id="dayHourlyRateButton" style="width: 40px;height:20px "></button><br>';

                str += "2"+':<br><input id="nightHourlyRate" value="' + night + '" /><button id="nightHourlyRateButton" style="width: 40px;height:20px "></button><br>';

            for(i=0;i<obj.distanceRates.length;i++){
                if(obj.distanceRates[i].fromTimeHHmm=="07:00" ) day=obj.distanceRates[i].multiplier;
                else night=obj.distanceRates[i].multiplier;

            }




                str += "1"+'<br><input id="dayDistanceRate" value="' + day + '" /><button id="dayDistanceRateButton" style="width: 40px;height:20px "></button><br>';

                str += "2"+'<br><input id="nightDistanceRate" value="' + night + '" /><button id="nightDistanceRateButton" style="width: 40px;height:20px "></button><br>';


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
            var str ='Basic:<input id="basic" value="'+obj.OrderTypeMultipliers[0].basic+'"/><button id="basicButton" style="width: 40px;height:20px "></button><br>' +
                'Cargo:<input id="cargo" value="'+obj.OrderTypeMultipliers[0].cargo+'"/><button id="cargoButton" style="width: 40px;height:20px "></button><br>' +
                'Sober driver:<input id="sober" value="'+obj.OrderTypeMultipliers[0].sober_driver+'"/><button id="soberButton" style="width: 40px;height:20px "></button><br>' +
                'Guest delivery:<input id="guest" value="'+obj.OrderTypeMultipliers[0].guest_delivery+'"/><button id="guestButton" style="width: 40px;height:20px "></button><br>' +
                'Meet my guest:<input id="meet" value="'+obj.OrderTypeMultipliers[0].meet_my_guest+'"/><button id="meetButton" style="width: 40px;height:20px "></button><br>' +
                'Celebration taxi:<input id="celebration" value="'+obj.OrderTypeMultipliers[0].celebration_taxi+'"/><button id="celebrationButton" style="width: 40px;height:20px "></button><br>'
            document.getElementById("OrderTypeRates").innerHTML = str;

            $(":button").click(function () {
                var str = $(this).attr("id");
                str=str.replace("Button","");


                $.ajax({
                    method: 'POST',
                    url: 'api/tariff/'+$(this).attr("id"),
                    contentType: "text/plain; charset=utf-8",
                    data:  $("#"+str).val(),

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
