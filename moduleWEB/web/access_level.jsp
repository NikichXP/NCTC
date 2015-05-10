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
<body>
<h1>Choose access level</h1>

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

</body>
</html>
