<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2018/10/31
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表查询</title>
    <%@ include file="/common/mystyle.jsp" %>
</head>
<div class="panel panel-default" data-height="350">
    <div class="panel-heading">
        <h3 class="panel-title">用户管理</h3>
    </div>
    <table id="admin"></table>
</div>
<div id="ff"></div>
<script type="text/javascript">
    $(function(){
        $("#admin").bootstrapTable({
            url: '<%=request.getContextPath()%>/admin/queryadmin',  //请求后台的URL（*）
            height:600,
                      //是否显示父子表
            //发送到服务器的数据编码类型sortable: false,                     //是否启用排序
            sortOrder: "asc",
            checkboxHeader:true,
            method:"post",
            striped: true,  	// 斑马线效果     默认false
            //只允许选中一行
            singleSelect:true,
            //选中行是不选中复选框或者单选按钮
            clickToSelect:true,
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            uniqueId: "id",                 //每一行的唯一标识，一般为主键列
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,     //  最少留两列
            detailView: false,
            contentType:'application/x-www-form-urlencoded;charset=UTF-8',   //数据编码纯文本  offset=0&limit=5
            toolbar:'#tabToolBar',   //  工具定义位置
            columns:[
                {field:'chkid',title:'选择',checkbox:true,align:'center',width:50},
                {field:'uid',title:'ID',align:'center',width:50,},
                {field:'username',title:'管理员姓名',align:'center',width:100},
                {field:'cao',title:'管理员赋角色',align:'center',width:100,
                    formatter:function(value,row,index){   //  格式化  当前单元格内容
                        return  '<button  type="button" class="btn btn-success" onclick="adminrole('+row.uid+')">管理员赋角色</button>' +
                            '<button  type="button" class="btn btn-danger" onclick="deleteadmin('+row.uid+')">删除管理员</button>';
                    }
                },
            ],
            //传递参数（*）
            queryParams: function(params) {
                var whereParams = {
                    /*
                        分页  自定义的参数         默认传 limit（展示几条）    offset（从第几条开始    起始条数）
                    */
                    "page":this.pageNum,
                    "rows":this.pageSize,

                    //"pro_name":params.search,//精确搜索产品名称
                }
                return whereParams;
            },
            //前台--排序字段
            //sortName:'proPrice',
            //sortOrder:'desc',
            //前台--搜索框
            //search:true,
            //启动回车键做搜索功能
            //searchOnEnterKey:true,
            //分页方式   后台请求的分页方式
            sidePagination:'server',
            pagination: true,                   //是否显示分页（*）
            pageNum: 1,                       //每页的记录行数（*）
            pageSize: 6,                       //每页的记录行数（*）
            pageList: [3, 6, 9,12],        //可供选择的每页的行数（*）
        });
    });

    function deleteadmin(uid) {
        $.ajax({
            url:'<%=request.getContextPath()%>/admin/deleteadmin?uid='+uid,
            async:false,
            type:"post",
            success:function(resutlt){
                alert("删除成功！！！")
                $("#admin").bootstrapTable("refresh");

            }
        });
    }
    function adminrole(uid){
        $("#ff").dialog({
            title: '用户赋角色',
            width: 600,
            height: 400,
            href:"<%=request.getContextPath()%>/admin/toaddrole?uid="+uid,
            closed: false,
            /* queryParams: {userid:id}, */
            modal: true,
            iconCls:"icon-save",
            buttons:[{      //  底部 按钮
                text:'OK',
                iconCls:"icon-ok",
                handler:function(){    //提交   表单信息   添加   修改  用一个
                    var rows=$('#adminrole1').datagrid("getSelections");
                    //  第一步  获取  复选框选中的值
                    var roleIds="";
                    $.each(rows,function (index,obj){
                        roleIds+=obj.id+",";
                    });
                    $.ajax({
                        type:"post",
                        url:"<%=request.getContextPath()%>/admin/saveRole",
                        data:{
                            "userId":$("#uid").val(),
                            "roleIds":roleIds
                        },
                        success:function (msg){
                            $.messager.alert('我的消息','提交成功！','info');
                            $("#ff").dialog("close");
                            $('#adminrole1').datagrid("load");
                        }
                    });


                }
            },{
                text:'关闭',
                iconCls:"icon-no",
                handler:function(){
                    $("#ff").dialog("close");
                }
            }],
            onLoad:function (){  // uploaddify  要做 dialog  加载时完成
                //editor.readonly(true);   // 设置  kindeditor   修改时只读
            }
        });
    }
    /* function  adminrole(uid){
   dialog("%=request.getContextPath()%>/admin/toaddrole?uid="+uid,"%=request.getContextPath()%>/admin/saveRole","管理员赋角色");
    };
    function getJspHtml(urlStr){
        var  jspHtml = "";
//sync  (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
//注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        $.ajax({
            url:urlStr,
            type:'post',
            //同步的ajax
            async:false,
            success:function(data){
                //alert(data);//data--addProduct.jsp页面内容
                jspHtml = data;
            },
            error:function(){
                bootbox.alert("请求失败");
            }
        });
        return jspHtml;
    };
    function  dialog(HTMLurl,submitUrl,title){
        var dialog = bootbox.dialog({
            title: title,
            message: getJspHtml(HTMLurl),   //调用方法
            buttons:{
                "save":{
                    label: '保存',
                    //自定义样式
                    className: "btn-success",
                    callback: function() {
                        var rows=$('#adminrole1').bootstrapTable("getAllSelections");
                        //  第一步  获取  复选框选中的值
                        var roleIds="";
                        $.each(rows,function (index,obj){
                            roleIds+=obj.id+",";
                        });
                        $.ajax({
                            url:submitUrl,
                            type:'post',
                            data:{
                                "userId":$("#uid").val(),
                                "roleIds":roleIds
                            },
                            success:function(data){
                                bootbox.alert("保存成功");
                                $("#admin").bootstrapTable("refresh");
                            },
                            error:function(){
                                bootbox.alert("操作失败");
                            }
                        });
                    }
                },
                "unSave":{
                    label: '取消',
                    //自定义样式
                    className: "btn-info",
                    callback: function() {
                        // return false;  //dialog不关闭
                    }
                }
            }
        });
    }

*/
</script>
</body>
</html>
