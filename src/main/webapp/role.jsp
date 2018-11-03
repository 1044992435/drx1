<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2018/11/2
  Time: 14:13
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
<html>
<head>
    <title>Title</title>
    <%@ include file="/common/mystyle.jsp" %>

</head>

<body>

    <form>
        <input type="hidden" id="uid" value="${uid}">
        <table id="adminrole1">
        </table>
    </form>
</body>
<script>
$(function(){
    $("#adminrole1").datagrid({// 定义一下  数据表格
        width:500,
        //fit:true,
        url:"<%=path%>/admin/queryrole",
        title:"角色列表",
        toolbar:"#tb",  // 定义工具栏
        rownumbers:true,    //  显示序列号
        idField :"id",
        onLoadSuccess:function (data){  // 加载成功后执行的方法
            //alert(eval('${uid}'))
            var roleArray=eval('${uid}');
            var roleArray=$("#uid").val();
            $.ajax({
                url:"%=path%>/admin/queryRole?uid="+roleArray,
                type:"post",
                //data:{userid:id},
                dataType:"json",
                success:function(data){
                    for(var i=0;i<data.length;i++){
                        $("#adminrole1").datagrid("selectRecord",data[i].id);
                    }
                }
            })
        },
        //loader:myLoader,
        columns:[[     //  列属性     因为 他们可以合并单元格
            {field:'chkid',title:'选择',checkbox:true,align:'center',width:50},
            {field:'id',title:'ID',width:50,align:'center'},
            {field:'rname',title:'角色名称',align:'center',width:100}

        ]],
        /*    pagination:true,
           pageSize: 3,//每页显示的记录条数，默认为10
            pageList: [3,5,7],//可以设置每页记录条数的列表  */
    })
   /* $("#adminrole1").bootstrapTable({
        url:'=request.getContextPath()%>/admin/queryrole',  //请求后台的URL（*）
        height:400,
        method:"post",
        sortable: false,                     //是否启用排序
        sortOrder: "asc",
        checkboxHeader:true,
        method:"post",
        striped: true,  	// 斑马线效果     默认false
        //只允许选中一行
        singleSelect:false,
        //选中行是不选中复选框或者单选按钮
        clickToSelect:true,
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        uniqueId: "id",                 //每一行的唯一标识，一般为主键列
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,     //  最少留两列
        detailView: false,
        //是否显示父子表
        //发送到服务器的数据编码类型
        contentType:'application/x-www-form-urlencoded;charset=UTF-8',   //数据编码纯文本  offset=0&limit=5
        toolbar:'#tabToolBar',   //  工具定义位置
        columns:[
            {field:'chkid',title:'选择',checkbox:true,align:'center',width:50},
            {field:'id',title:'ID',width:50,align:'center'},
            {field:'rname',title:'角色名称',align:'center',width:100},

        ],
        //传递参数（*）
        queryParams: function(params) {
            var whereParams = {
                /!*
                    分页  自定义的参数         默认传 limit（展示几条）    offset（从第几条开始    起始条数）
                *!/
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
        pageList: [3, 6, 9,12],
        onLoadSuccess:function (data){  // 加载成功后执行的方法
            var roleArray=$("#uid").val();
            $.ajax({
                url:"%=path%>/admin/queryRole?uid="+roleArray,
                type:"post",
                //data:{userid:id},
                dataType:"json",
                success:function(data){
                    for(var i=0;i<data.length;i++){
                        $("#adminrole1").bootstrapTable("check",data[i].id);
                    }
                }
            })
        },//可供选择的每页的行数（*）
    });*/

})



</script>
</html>
