<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Starter Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<%--    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">--%>

    <!-- Custom styles for this template -->
<%--    <link href="starter-template.css" rel="stylesheet">--%>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<%--    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->--%>
<%--    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color:white;
        }
        h2{
            width: 500px;
        }
        button{
            margin-top: 15px;
        }
        #AfterClickBtn{
            width: 250px;
            height: 300px;
            position: absolute;
            top: 200px;
            right: 200px;
        }
        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin .checkbox {
            font-weight: normal;
        }
        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">用户管理系统</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">首页</a></li>
                <li class="active"><a href="login.jsp">添加</a></li>
                <li><a href="display.jsp">查询</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

    <div class="container">
        <form class="form-signin">
            <h2 class="form-signin-heading">请输入要插入的person信息</h2>
            <input type="text" name="username" id="username" class="form-control" placeholder="username(necessary)">
            <input type="text" name="name" id="name" class="form-control" placeholder="name(necessary)">
            <input type="text" name="age" class="form-control" placeholder="age" id="age">
            <input type="text" name="telenum" class="form-control" placeholder="telenum" id="telenum">
            <button type="button" class="btn btn-primary btn-lg center-block" id="btn1" disabled onclick="login()">提交</button>
        </form>
    </div>

    <div class="container">
        <form class="form-signin">
            <h2 class="form-signin-heading">请输入要删除的user信息</h2>
            <input type="text" name="username" id="username2" class="form-control" placeholder="username(necessary)">
            <button type="button" class="btn btn-primary btn-lg center-block" id="btn2" disabled onclick="login2()">提交</button>
        </form>

    </div> <!-- /container -->

    <div id="AfterClickBtn">

    </div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<%--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>--%>
<script src="/js/index.js?ver=1.1"></script>
</body>
</html>
