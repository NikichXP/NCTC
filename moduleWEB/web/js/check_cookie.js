$(document).ready(function(){
    checkCookie("uuid");
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
