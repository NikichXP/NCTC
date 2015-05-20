/**
 * Created by Ubuntu on 20.05.2015.
 */
function getOrderById(id) {
    $.get("api/order/view?id=" + id, function (data) {
        alert(data);
    });
}