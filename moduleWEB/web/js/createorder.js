/**
 * Created by Ubuntu on 11.05.2015.
 */
function createOrder() {
    var orderData = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        phone: $("#phone").val(),
        email: $("#email").val(),
        pass: $("#reg-pass").val()
    };
    $.ajax({
        method: 'POST',
        url: 'api/order/create',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(orderData),
        dataType:'text',
        success: function (data,textStatus,jqXHR ) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Bad response from server.");
        }
    })
}