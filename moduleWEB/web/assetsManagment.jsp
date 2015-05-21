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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>YATS</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/customer-style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="container">

<!--header-->
<div class="header">
    <!--RENAME CLASS AND ID ACCORDING TO TEAM CONVENTION-->
    <div class="header-text">
        <!--HARDCODED for now. AngularJS script will operate this section later-->
        <h1 id="name-line"></h1>
        <h5 id="phone-line"></h5>
        <h5 id="email-line"></h5>
    </div>
    <!--RENAME CLASS AND ID ACCORDING TO TEAM CONVENTION-->
    <div class="header-logout" id="logout-button">
        <h2>Log Out</h2>
    </div>
</div>

<!--Main section. On this particular page Taxi Orders appear in this section in form of divs.
blocks contain brief information about order-->
<div class="mainSection" id="customerOrdersPanel">
    <div class="col-md-12 mt">

        <h4> ASSET NANAGMENT</h4>
        <hr>
        <table id="assetManagerTable" class="table">
            <thead>
            <tr>
                <th>CAR</th>
                <th>DRIVER</th>
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
                    <ul>
                        <li><input type="text-field" placeholder="id" id="idForCar"/></li>
                        <li><input type="text-field" placeholder="model" id="model"/></li>
                        <li><input type="text-field" placeholder="seat count" id="seatCount"/></li>
                        <li><input type="text-field" placeholder="licence plate" id="licencePlate"/></li>
                        <%--<li><input type="text-field" placeholder="required driver category"--%>
                                   <%--id="requiredDriverCategory"/></li>--%>
                        <li><input type="text-field" placeholder="driver id" id="userDriverId"/></li>
                        <li>
                            <div>
                                <select name="language" class="form-control" id="requiredDriverCategory">
                                </select>
                            </div>
                        </li>
                        <li>
                            <div>
                                <select name="language" class="form-control" id="classCar">
                                </select>
                            </div>
                        </li>
                        <li>
                            <div>
                                <select name="language" class="form-control" id="conditioner">
                                    <option value="true">true</option>
                                    <option value="false">false</option>
                                </select>
                            </div>
                        </li>
                    </ul>
                    <div class="button" id="addCar" onclick="addCar()">
                        ADD
                    </div>
                    <div class="button" id="editCar" onclick="editCar()">
                        EDIT
                    </div>
                    <div class="button" id="deleteCar" onclick="deleteCar()">
                        Delete
                    </div>
                </td>
                <td align="center">
                    <ul>
                        <li><input type="text-field" placeholder="id" id="idForDriver"/></li>
                        <li><input type="text-field" placeholder="First name" id="firstName"/></li>
                        <li><input type="text-field" placeholder="Last name" id="lastName"/></li>
                        <li><input type="text-field" placeholder="Phone number" id="phone"/></li>
                        <li><input type="text-field" placeholder="E-mail" id="email"/></li>
                        <li><input type="password" placeholder="Password" id="regpass"/></li>
                        <li><input type="password" placeholder="Confirm password" id="passconfirm"/></li>
                        <li><input type="text-field" placeholder="car id" id="carId"/></li>
                    </ul>
                    <div class="button" id="addDriver" onclick="addDriver()">
                        ADD
                    </div>
                    <div class="button" id="editDriver" onclick="editDriver()">
                        EDIT
                    </div>
                    <div class="button" id="deleteDriver" onclick="deleteDriver()">
                        Delete
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>

</div>


<!--footer-->
<div class="footer">

</div>
<!--scripts provided by Bootstrap-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!--script check life cookie, end delete cookie on click logout-button-->
<script src="js/cookie.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<!--set information about user-->
<script src="js/userdatabyuuid.js" type="text/javascript"></script>
<script src="js/asset-manager-script.js"></script>
</body>
