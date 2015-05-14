$(document).ready(function(){
    checkCookie("uuid");
});

$("#logout-button").click(function(){
    deleteAllCookies();
});



function checkCookie(name) {
    var myCookie = getCookie(name);
    if (myCookie == null) {
        // do cookie doesn't exist stuff;
        alert("Please log in");
        document.location.href = "index.jsp";
    }
    else {
        // do cookie exists stuff
        alert(getCookie(name));
    }
}

function getCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
        var end = document.cookie.indexOf(";", begin);
        if (end == -1) {
            end = dc.length;
        }
    }
    return unescape(dc.substring(begin + prefix.length, end));
}

function deleteAllCookies() {
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
    document.location.href = "index.jsp";
}
