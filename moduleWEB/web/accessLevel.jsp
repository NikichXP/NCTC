<%-- 22:04 09.05.2015 by Viktor Taranenko --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

</head>
<body onload="getAccessLevels()">
<%!
    Cookie[] c;
    boolean flag;
%>
<%
  /*  flag = false;
    c = request.getCookies();
    for (Cookie cook:c) {
        if (cook.getName().equals("uuid")) {
            if (user.findByUuid(cook.getValue()) == null) {

            } else {
                flag = true;
            }
        }
    }
    if (!flag) {
        response.sendRedirect("index.jsp");
    }*/
%>
<h1 style="text-align: center">Choose access level</h1><br>

<p id="accessLevel" style="text-align: center"></p>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script src="js/accesslevel.js"></script>
</body>
</html>
