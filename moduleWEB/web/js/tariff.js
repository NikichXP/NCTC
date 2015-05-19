function getTariffs(orderTypeName) {
    $.ajax({
        method: 'POST',
        url: 'api/tariff/getAll',
        contentType: "text/plain; charset=utf-8",
        data: orderTypeName,
        dataType: 'text',
        success: function (data) {
            var obj = JSON.parse(data);
            var str = '<p id="orderTypeRate" multiplier="' + obj.orderTypeRate + '" />';
            for (var i = 0; i < obj.hourlyRates.length; i++) {
                var innerObj = obj.hourlyRates[i];
                str += '<p id="hourlyRates'+i+'" fromTimeHHmm="' + innerObj.fromTimeHHmm
                    +'" toTimeHHmm="' + innerObj.toTimeHHmm
                    +'" multiplier="' + innerObj.multiplier + '" />';
            }

            for (var i = 0; i < obj.distanceRates.length; i++) {
                var innerObj = obj.distanceRates[i];
                str += '<p id="distanceRates'+i+'" fromTimeHHmm="' + innerObj.fromTimeHHmm
                    +'" toTimeHHmm="' + innerObj.toTimeHHmm
                    +'" multiplier="' + innerObj.multiplier + '" />';
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