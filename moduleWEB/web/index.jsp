<%@ page import="com.netcracker.service.SessionHandler" %>
<%@ page import="com.netcracker.facade.local_int.User" %>
<%@ page import="javax.ejb.EJB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>YATS</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>


<body>
<%!
    Cookie[] c;
    boolean flag;
%>
<%
    flag = false;
    try {
        c = request.getCookies();
        out.write("test");
        out.write(Integer.toString(c.length));
        for (Cookie cook:c) {
            out.write(cook.getName()+"\n");
            out.write(cook.getPath()+"\n");
            out.write(cook.getValue()+"\n");
        }
        if (!flag) {
            out.write("noFlag");
        }
    } catch (Exception e) {
        out.write(e.toString());
    }
%>


<div class="container">
    <div class="row">

        <!--Logo header section-->
        <div class="logo text-center">
            <h1>YATS</h1>
            <h6>yet another taxi service</h6>
        </div>
    </div>

    <!--Log in section-->
    <div class="row">
        <span href="#" class="button" id="toggle-login">Log in</span>

        <div id="login">

            <form>
                <input type="text-field" placeholder="Email or phone number" id="login-cred"/>
                <input type="password" placeholder="Password" id="login-pass"/>

                <div class="submit" id="login-submit">Log in</div>
            </form>
        </div>
    </div>
    <!--Registration section-->
    <div class="row">
        <span href="#" class="button" id="toggle-registration">Registration</span>

        <div id="registration">
            <form>
                <input type="text-field" placeholder="First name" id="firstName"/>
                <input type="text-field" placeholder="Last name" id="lastName"/>
                <input type="text-field" placeholder="Phone number" id="phone"/>
                <input type="text-field" placeholder="E-mail" id="email"/>
                <input type="password" placeholder="Password" id="reg-pass"/>
                <input type="password" placeholder="Confirm password" id="pass-confirm"/>

                <div class="submit" id="registration-submit">Registration</div>
            </form>
        </div>
    </div>
    <!--Order without registration section-->
    <div class="row">
        <span href="#" class="button" id="toggle-anon-order">Order without registration</span>

        <div id="anon-order">
            <form>
                <input type="text-field" placeholder="Orders tracking number" id="tracking-id"/>

                <div class="submit" id="track-TO-submit">Show active order</div>
                <div class="submit" id="order-wo-reg-submit">Order without registration</div>
            </form>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.js"></script>

<script src="js/script.js"></script>

</body>
</html>
