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
        //alert(getCookie(name));
    }
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
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
