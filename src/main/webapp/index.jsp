<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta charset="utf-8" />
    <title>用户登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>redis</title>
    <link  href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" >

    <!-- 引入bootstrap-treeview的css -->
    <link  href="<%=request.getContextPath()%>/js/treeview/bootstrap-treeview.min.css" rel="stylesheet" >

    <!-- 引入bootstrap-addTabs的css -->
    <link  href="<%=request.getContextPath()%>/js/addTabs/addTabs.css" rel="stylesheet" >

    <!-- 引入jquery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

    <!-- 引入bootstrap的js-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>

    <!-- 引入bootstrap的js-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/treeview/bootstrap-treeview.min.js"></script>

    <!-- 引入bootstrap的js-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/addTabs/addTabs.js"></script>
    <style type="text/css">
        body {padding-top:55px;}
    </style>
    <style>
        #from
        {
            background-color: #96b97d;
        }
        .mycenter
        {
            margin-top: 50px;
            margin-left: auto;
            margin-right: auto;
            height: 350px;
            width: 500px;
            padding: 5%;
            padding-left: 5%;
            padding-right: 5%;
        }
        .mycenter mysign
        {
            width: 440px;
        }
        .mycenter input, checkbox, button
        {
            margin-top: 2%;
            margin-left: 10%;
            margin-right: 10%;
        }
        .mycheckbox
        {
            margin-top: 10px;
            margin-left: 40px;
            margin-bottom: 10px;
            height: 10px;
        }
    </style>
</head>
<body>
<form id="from">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2>
                    请登录</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control"  name="username" id="username" placeholder="请输入账户名"  onblur="checkUserName()" required
                       autofocus />
            </div>
            <div class="col-lg-10">
            </div>
              <div class="col-lg-10">
              <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码"  required
                       autofocus />
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10 mycheckbox checkbox">
                <input type="checkbox" class="col-lg-1">记住密码</input>
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <button type="button" id="btn" onclick="logins()" class="btn btn-success col-lg-12">
                    登录</button>
                <button type="button" id="btn" onclick="login()" class="btn btn-success col-lg-12">
                    注册</button>
            </div>
        </div>
    </div>
</form>
<div class="container footer">
<div class="span24">
    <div class="footerAd"><img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势" /></div>
</div>
<div class="span24">
    <ul class="bottomNav">
        <li>
            <a >关于我们</a>
            |
        </li>
        <li>
            <a>联系我们</a>
            |
        </li>
        <li>
            <a>招贤纳士</a>
            |
        </li>
        <li>
            <a>法律声明</a>
            |
        </li>
        <li>
            <a>友情链接</a>
            |
        </li>
        <li>
            <a target="_blank">支付方式</a>
            |
        </li>
        <li>
            <a  target="_blank">配送方式</a>
            |
        </li>
        <li>
            <a>服务声明</a>
            |
        </li>
        <li>
            <a>广告声明</a>

        </li>
    </ul>
</div>
<div class="span24">
    <div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
</div>
</div>
<script type="text/javascript">

    //验证用户名
    function checkUserName(){
        var username = $("#username").val();
        if(username ==null || username==''){
            alert("用户名不能为空");
            return false;
        }
        $.ajax({
            url:"<%=request.getContextPath()%>/comment/userName",
            type:"post",
            data:{"username":username},
            dataType:"json",
            success:function(data){
                if(data==2){
                    alert("当前用户名不存在请重新输入");
                }
            },error:function(){
                alert("用户失败");
            }
        })
    }

    //验证名字，密码是否正确
    function logins(){
        var username = $("#username").val();
        alert(username)
        if(username ==null || username==''){
            alert("用户名不能为空");
            return false;
        }
        var password = $("#password").val();
        if(password==null || password=='' ){
            alert("密码不能为空");
            return false;
        }
        $.ajax({
            url:"<%=request.getContextPath()%>/comment/login",
            type:"post",
            data:$("#from").serialize(),
            dataType:"json",
            success:function(data){
                if(data==2){
                    alert("密码错误请重新输入");
                    return false;
                }
                location.href="<%=request.getContextPath()%>/Main.jsp"
            },error:function(){
                alert("用户登录失败");
            }
        })
    }

    function login() {
        location.href="<%=request.getContextPath()%>/reguder.jsp"
    }


</script>
</body>
</html>