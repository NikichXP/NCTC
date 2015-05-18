/**
 * Created by Евгений on 16.05.2015.
 */
$(document).ready(function(){
 setHistory();
});


function setHistory(){

    $.ajax({
        method: 'POST',
        url: 'api/admin_table/users_table',
        contentType: "text/plain; charset=utf-8",

        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert("1")
            drawTable(obj.users_tb, "#table");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}

function drawTable(data, table) {
    for (var i = 0; i < data.length; i++) {
        drawBody(data[i], table);
    }
}
function drawBody(rowData, table) {
    var row = $("<tbody><tr>");
    $(table).append(row);
    row.append($("<td></td>"));
    row.append($("<td>" + rowData.firstName + "</td>"))
    row.append($("<td>" + rowData.lastName + "</td>"));

    row.append($("<td>" + rowData.phone + "</td></tr></tbody>"));
}
