<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta charset="utf-8" />
    <title>用户注册</title>
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
                    请注册</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control"  name="username" id="username" placeholder="请输入注册账户名"  required
                       autofocus />
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="请输入注册密码"  required
                       autofocus />
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="cellphone" id="loginNumber" placeholder="请输入手机号"  required
                       autofocus />
                <input type="text" class="form-control" name="verification" id="verification" placeholder="请输入验证码"  required
                       autofocus />
                <button type="button" id="myButton" onclick="settime(this)"  data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">
                     获取验证码
                </button>

            </div>
            <div class="col-lg-10">
                <button type="button" id="btn" onclick="regUsers()"  class="btn btn-success col-lg-12">
                    注册</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">

    function regUsers(){
        $.ajax({
                url:"<%=request.getContextPath() %>/comment/regUser",
                    type:"post",
            data:$("#from").serialize(),
            dataType:"json",
                success:function(data) {
            if (data == "2") {
                $.messager.alert("提示", "用户已经存在!");
            } else {
                location.href = "<%=request.getContextPath() %>/index.jsp";
            }
        }
        });
    }
    var countdown=60;
    function settime(val) {
        var loginnumber = $("#loginNumber").val();
        if (null == loginnumber || "" == loginnumber ||loginnumber.length!=11) {
            alert("请输入有效手机账号");
            return;
        }
        if(countdown == 60){
            getCode();
        }
        if (countdown == 0) {
            val.removeAttribute("disabled");
            val.value="获取验证码";
            countdown = 60;
        } else {
            val.setAttribute("disabled", true);
            val.value="重新发送(" + countdown + ")";
            countdown--;
            setTimeout(function() {
                settime(val)
            },1000)
        }
    }

    function getCode(){
        $.ajax({
            url:"<%=request.getContextPath() %>/comment/getCode",
            type:"post",
            data:{
                loginNumber:$("#loginNumber").val()
            },
            success:function(result){
                if (result.code!=0) {
                    alert(result.msg);
                }
            }
        })
    }
</script>
</body>
</html>