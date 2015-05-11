<%@ page import="javax.ejb.EJB" %>
<%@ page import="com.netcracker.facade.local_int.User" %>
<%@ page import="com.netcracker.entity.UserEntity" %>
<%@ page import="com.netcracker.entity.UserAccessLevelEntity" %>
<%@ page import="java.util.Collection" %>
<%-- 22:04 09.05.2015 by Viktor Taranenko --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

</head>
<body onload="getAccessLevels()">
<script>
    document.write('<h1 style="text-align: center">Choose access level</h1><br>');
    document.write('<p id="accessLevel" style="text-align: center"></p>');
</script>

<%!
    @EJB
    User user;
    Cookie[] cookie;
%>

<%
    try {
        cookie = request.getCookies();
        for (Cookie c : cookie) {
            if (c.getName().equals("uuid")) {
                UserEntity userEntity = user.findByUuid(c.getValue());
                Collection<UserAccessLevelEntity> userAccessLevelEntities = userEntity.getUserAccessLevelEntities();
                for (UserAccessLevelEntity userAccessLevelEntity : userAccessLevelEntities) {
                    %><a href="#"><%=userAccessLevelEntity.getName()%></a><br><%
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script src="js/accesslevel.js"></script>
</body>
</html>
