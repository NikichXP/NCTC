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
    var cookie = " " + document.cookie;
    var search = " " + name + "=";
    var setStr = null;
    var offset = 0;
    var end = 0;
    if (cookie.length > 0) {
        offset = cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = cookie.indexOf(";", offset)
            if (end == -1) {
                end = cookie.length;
            }
            setStr = decodeURI(cookie.substring(offset, end));
        }
    }
    return (setStr);
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