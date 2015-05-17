$(document).ready(function(){
    setList()
});



function setList(){
    var uuid = getCookie("uuid");
    $.ajax({
        method: 'POST',
        url: 'api/user_dash/customer_list',
        contentType: "text/plain; charset=utf-8",
        data: uuid,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            drawTable(obj.orderHistory, "table");
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

    var div = document.getElementById(table);
    var createDiv = document.createElement("div");
    var node = document.createTextNode("data :" + rowData.dateOrderCreate
                                        + " ferst point :" + rowData.startOrder
                                        + " next point :" + rowData.endOrder
                                        + " status :" + rowData.statusOrder
                                        + " price: " + rowData.price + "$");
    createDiv.appendChild(node);
    createDiv.className = "button";
    createDiv.onclick = function () {
        alert(rowData.endOrder);
        $.redirect("Test.html", {'arg1': 'value1', 'arg2': 'value2'}, 'POST');
    };
    div.appendChild(createDiv);

}

;(function( $ ){
    /**
     * jQuery Redirect
     * @param {string} target - Url of the redirection
     * @param {Object} values - (optional) An object with the data to send. If not present will look for values as QueryString in the target url.
     * @param {string} method - (optional) The HTTP verb can be GET or POST (defaults to POST)
     */
    $.redirect = function( target, values, method ) {
        method = (method && method.toUpperCase() == 'GET') ? 'GET' : 'POST';

        if (!values)
        {
            var obj = $.parse_url(target);
            target = obj.url;
            values = obj.params;
        }

        var form = $('<form>').attr({
            method: method,
            action: target
        });

        iterateValues(values, [], form);
        $('body').append(form);
        form.submit();
    };

//Private Functions
    $.parse_url = function(url)
    {
        if (url.indexOf('?') == -1)
            return { url: url, params: {} }

        var parts = url.split('?'),
            url = parts[0],
            query_string = parts[1],
            elems = query_string.split('&'),
            obj = {};

        for(var i in elems)
        {
            var pair = elems[i].split('=');
            obj[pair[0]] = pair[1];
        }

        return {url: url, params: obj};
    }
    var getInput = function(name, value, parent) {
        var parentString;
        if( parent.length > 0 ) {
            parentString = parent[0];
            for( var i = 1; i < parent.length; ++i ) {
                parentString += "[" + parent[i] + "]";
            }
            name = parentString + "[" + name + "]";
        }

        return $("<input>").attr({
            type: "hidden",
            name: name,
            value: value
        });
    };

    var iterateValues = function(values, parent, form) {
        var iterateParent = [];
        for(var i in values)
        {
            if( typeof values[i] == "object" ) {
                iterateParent = parent.slice();
                iterateParent.push(i);
                iterateValues(values[i], iterateParent, form);
            } else {
                getInput(i, values[i], parent).appendTo(form);
            }
        }
    };

})( jQuery );




