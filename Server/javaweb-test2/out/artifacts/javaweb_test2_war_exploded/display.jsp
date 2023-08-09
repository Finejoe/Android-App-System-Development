<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="UersAndPerson.JDBCmanager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="UersAndPerson.TestDB" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="UersAndPerson.person" %>
<%@ page import="UersAndPerson.users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        body {
            padding-top: 50px;
        }
        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }
        h1{
            padding-top: 150px;
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
                <li><a href="login.jsp">添加</a></li>
                <li class="active"><a href="display.jsp">查询</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <h2 class="displayH2">
        数据库person信息
    </h2>
    <%
        Connection con = null;
        Statement sta = null;
        con=new JDBCmanager().ClaimConnection();
        try {
            sta=con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List list=null;
        List list2=null;
        Iterator it;
        list = TestDB.GetPersonData(sta);
        list2=TestDB.GetUsersData(sta);
    %>
    <table class="table table-bordered">
        <tr>
            <td>username</td>
            <td>name</td>
            <td>age</td>
            <td>telenu</td>
        </tr>
        <%
            it=list.iterator();
            while (it.hasNext()) {
                person s = (person) it.next();
        %>

        <tr>
            <td><%=s.getUsername()%></td>
            <td><%=s.getName()%></td>
            <td><%=s.getAge()%></td>
            <td><%=s.getTeleno()%></td>
        </tr>
        <%
            }
        %>
    </table>
    <h2 class="displayH2">
        数据库users信息
    </h2>
    <table class="table table-bordered">
        <tr>
            <td>username</td>
            <td>pass</td>
        </tr>
        <%
            it=list2.iterator();
            while (it.hasNext()) {
                users s = (users) it.next();
        %>

        <tr>
            <td><%=s.getUsername()%></td>
            <td><%=s.getPass()%></td>
        </tr>
        <%
            }
        %>
    </table>

</div><!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>


<%--<title>student system</title>--%>
<%--<link href="/css/index.css" rel="stylesheet">--%>
