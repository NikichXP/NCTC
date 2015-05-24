<%--
  Created by IntelliJ IDEA.
  User: Juger
  Date: 18.05.2015
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" ng-app="nctc">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>YATS</title>

    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/customer-style.css" rel="stylesheet">
    <link href="css/for-footer.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body class="container">


<div class="header">
    <div class="header-text">
        <h1 id="name-line"></h1>
        <h5 id="phone-line"></h5>
        <h5 id="email-line"></h5>
    </div>
    <div class="header-logout" id="logout-button">
        <h2>Log Out</h2>
    </div>
</div>
<div class="mainSection" id="customerOrdersPanel">
    <div class="col-md-12 mt">
        <h4> ASSET NANAGMENT</h4>
        <hr>
        <table id="assetManagerTable" class="table">
            <thead>
            <tr>
                <th align="center">CAR</th>
                <th align="center">DRIVER</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td align="center">
                    <div id="cars"></div>
                </td>
                <td align="center">
                    <div id="drivers"></div>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td align="center">

                </td>
                <td align="center">
                    <%--<ul>--%>
                        <%--<li><input type="text-field" placeholder="First name" id="firstName"/></li>--%>
                        <%--<li><input type="text-field" placeholder="Last name" id="lastName"/></li>--%>
                        <%--<li><input type="text-field" placeholder="Phone number" id="phone"/></li>--%>
                        <%--<li><input type="text-field" placeholder="E-mail" id="email"/></li>--%>
                        <%--<li><input type="password" placeholder="Password" id="regpass"/></li>--%>
                        <%--<li><input type="password" placeholder="Confirm password" id="passconfirm"/></li>--%>
                        <%--<li><input type="text-field" placeholder="car id" id="carId"/></li>--%>
                    <%--</ul>--%>

                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    <div id="for-footer"></div>

</div>

<div class="footer">
    <table id="buttonTable" class="table">
        <thead>
        <tr>
            <th align="center">CAR</th>
            <th align="center">DRIVER</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td align="center">
                <div id="addSettingCar">
                    <div class="button" id="addCar" onclick="addCarSection()">
                        ADD
                    </div>
                </div>

            </td>
            <td align="center">
                <div class="button" id="addDriver" onclick="addDriver()">
                    ADD
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/cookie.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<!--set information about user-->
<script src="js/user-data-by-uuid.js" type="text/javascript"></script>
<script src="js/asset-manager-script.js"></script>
</body>
