/**
 * Created by Ubuntu on 11.05.2015.
 */

//AJAX POST for registration
$("#basic-order-submit").click(function(){
    var JSONdata = {
        customerUserUuid: $("#customerUserUuid").val(),
        contactName: $("#contactName").val(),
        contactPhone: $("#contactPhone").val(),
        requestedSeatsCount: $("#requestedSeatsCount").val(),
        type: $("#type").val(),

        fromAddress: $("#fromAddress").val(),
        fromX: $("#fromX").val(),
        fromY: $("#fromY").val(),
        toAddress: getArrayOfToAdresses("toAddress"),
        toX: getArrayOfToAdresses("toX"),
        toY: getArrayOfToAdresses("toY"),

        sex: $("input[name=sex]:checked").attr("data-value"),
        carClass: $("input[name=carClass]:checked").attr("data-value"),
        musicType: $( "#musicType option:selected" ).text(),
        smokingFriendly: $("#smokingFriendly").is(':checked') ,
        animalFriendly: $("#animalFriendly").is(':checked') ,
        wifi: $("#wifi").is(':checked') ,
        airConditioner: $("#airConditioner").is(':checked') ,

        customerPreCreateComment: $("#customerPreCreateComment").val(),
        totalLength: $("#totalLength").val(),
        totalMultiplier: $("#totalMultiplier").val(),
        totalPrice: $("#totalPrice").val()
    };
    function getArrayOfToAdresses(idWithoutNumber) {
        var arr = [];
        for (var i = 0; i < $("[id^="+idWithoutNumber+"]").length; i++) {
            arr[i] = $("#" + idWithoutNumber + i).val()
        }
        return arr;
    }
    $.ajax({
        method: 'POST',
        url: 'api/order/create',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(textStatus + "\n" + data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Bad response from server.");
        }
    })
});