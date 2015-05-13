<%--
  Created by IntelliJ IDEA.
  User: Ubuntu
  Date: 11.05.2015
  Time: 3:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>UserDas</title>
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.css" rel="stylesheet">
  <!--external css-->
  <link href="css/font-awesome/css/font-awesome.css" rel="stylesheet"/>
  <link href="css/user-styles.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
</head>

<body>


<section id="container">
  <!--header start-->
  <header class="header black-bg">
    <!--logo end-->
    <div class="top-menu">
      <ul class="nav pull-right top-menu">
        <li><a class="logout" href="#" onclick="deleteAllCookies()">Logout</a></li>
      </ul>
    </div>
    <!--user information-->
    <div>
      User :<p id="user_name"></p>
      phone :<p id="user_phone"></p>
      e-mail :<p id="user_mail"></p>
    </div>
  </header>
  <!--main content start-->
  <section id="main-content">
    <section class="wrapper">
      <div class="row">
        <div class="col-md-12">
          <div class="content-panel">
            <h4><i class="fa fa-angle-right"></i> Orders</h4>
            <hr>
            <table id="orderTable" class="table">
              <thead>
              <tr>
                <th>#</th>
                <th>StartOrder</th>
                <th>EndOrder</th>
                <th>Price</th>
              </tr>
              </thead>
            </table>
          </div>
          <! --/content-panel -->
        </div>
        <!-- /col-md-12 -->
        <div class="col-md-12 mt">
          <div class="content-panel">
            <h4><i class="fa fa-angle-right"></i> History orders</h4>
            <hr>
            <table id="historyOrderTable" class="table">
              <thead>
              <tr>
                <th>#</th>
                <th>StartOrder</th>
                <th>EndOrder</th>
                <th>Price</th>
              </tr>
              </thead>
            </table>
          </div>
          <! --/content-panel -->
        </div>
        <!-- /col-md-12 -->
      </div>
    </section>
    <! --/wrapper -->
  </section>
  <!-- /MAIN CONTENT -->
  <section>
    <!--footer start-->
    <footer class="site-footer">
      <div class="text-center">
        <div id="user-panel">
          <ul>
            <li>
              <div class="submit" id="order-history-submit">History Order</div>
            </li>
            <li>
              <div class="submit" id="create-order-submit">Create Order</div>
            </li>
            <li>
              <div class="submit" id="setting-submit">Setting</div>
            </li>
          </ul>
        </div>
      </div>
    </footer>
    <!--footer end-->
  </section>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/check_cookie.js" type="text/javascript"></script>
  <script src="js/user-rest-scripts.js" type="text/javascript"></script>

</section>
</body>
</html>