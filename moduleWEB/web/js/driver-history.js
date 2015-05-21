/**
 * Created by Ubuntu on 19.05.2015.
 */
$(document).ready(function() {
    setHistory();
});

function setHistory(){
    var uuid = getCookie("uuid");
    $.ajax({
        method: 'POST',
        url: 'api/driver/history',
        contentType: "text/plain; charset=utf-8",
        data: uuid,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            drawTable(obj.orderHistory, "#historyOrderTable");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error! Driver History.");
        }
    })
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function drawTable(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawBody(data[i], table);
    }
}
function drawBody(rowData, table) {
    var row = $("<tbody><tr>")
    $(table).append(row);
    row.append($("<td></td>"));
    row.append($("<td>" + rowData.dateOrderCreate + "</td>"))
    row.append($("<td>" + rowData.startOrder + "</td>"));
    row.append($("<td>" + rowData.endOrder + "</td>"));
    row.append($("<td>" + rowData.price + "</td>"));
    row.append($("<td>" + rowData.statusOrder + "</td></tr></tbody>"));
}