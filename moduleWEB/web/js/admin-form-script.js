/**
 * Created by Евгений on 18.05.2015.
 */

$(document).ready(function(){
setForm();
});

function setForm(){

    $.ajax({
        method: 'POST',
        url: 'api/user/getUserDataByid',
        contentType: "text/plain; charset=utf-8",
        data:"3",

        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            alert(data)
            drawForm(obj.userData, "#form");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " Error!");
        }
    })
}


function drawForm(rowData, table) {
    var in1 = document.createElement('input');
    in1.type = 'text';
    in1.placeholder=rowData.firstName;

    var in2 = document.createElement('input');
    in2.type = 'text';
    in2.placeholder=rowData.lastName;

    var in3 = document.createElement('input');
    in3.type = 'text';
    in3.placeholder=rowData.phone;

    var in4 = document.createElement('input');
    in4.type = 'text';
    in4.placeholder=rowData.email;




    $(table).append(in1);
    $(table).append(in2);
    $(table).append(in3);
    $(table).append(in4);


}
