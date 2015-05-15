$(document).ready(function(){setHistory()});

function setHistory(){
    var uuid = getCookie("uuid");
    $.ajax({
        method: 'POST',
        url: 'api/user_dash/history',
        contentType: "text/plain; charset=utf-8",
        data: uuid,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            //alert(obj.orderHistory[0].status);
            //alert(obj.orderHistory[0].startOrder)
            //alert(obj.orderHistory[0].endOrder)
            //alert(obj.orderHistory[0].price)
            drawTable(obj.orderHistory, "#historyOrderTable");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
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
            setStr = unescape(cookie.substring(offset, end));
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
    row.append($("<td>" + rowData.startOrder + "</td>"));
    row.append($("<td>" + rowData.endOrder + "</td>"));
    row.append($("<td>" + rowData.price + "</td></tr></tbody>"));
}

function goBack(){

    document.location.href = "customer.jsp"

}


