/**
 * Created by Евгений on 16.05.2015.
 */
$(document).ready(function () {
    setHistory();
});


function setHistory() {

    $.ajax({
        method: 'POST',
        url: 'api/admin_table/users_table',
        contentType: "text/plain; charset=utf-8",

        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);

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

    row.append($("<td>" + rowData.firstName + "</td>"))
    row.append($("<td>" + rowData.lastName + "</td>"));

    //var btn = document.createElement('input');
    //btn.id = rowData.id_d;
    //btn.type = 'button';
    //btn.value = 'Modify'
    //btn.class = 'modify'
    //btn.align = "center";
    // function test () {
    //    document.location.href = "adminForm.html?id="+rowData.id_d;
    //
    //};
    //
    //
    //$(table).append(btn);

    row.append($("<td>" + rowData.phone + "</td>"));
    row.append($("<td>" + rowData.email + "</td></tbody></tr>"));

    row.append($("<td id=" + rowData.id_d + 'd' + "></td></tr></tbody>"));
    var td = document.getElementById(rowData.id_d + 'd');
    var btn = document.createElement('div');
    btn.className = "button";


    btn.appendChild(document.createTextNode("Modify"));
    td.appendChild(btn);
    btn.onclick = function () {
        document.location.href = "adminForm.html?id=" + rowData.id_d;
    };

    // <div class='button' id="+rowData.id_d+" >Modify</div>
}


