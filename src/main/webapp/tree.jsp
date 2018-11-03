<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2018/11/2
  Time: 16:22
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
<ul id="power"></ul>
<input type="hidden" value="${rid}" id="roleId">
<script type="text/javascript">
    $(function(){
        $("#power").tree({
            url: '<%=path %>/admin/querypower',
            //data: data,
            checkbox: true,
            cascadeCheck: false,
            onLoadSuccess:function (data){  // 加载成功后执行的方法
                var pow=eval('${rid}');
                $.ajax({
                    url:"<%=path%>/admin/queryroletree?rid="+pow,
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        $(data).each(function(i,obj){
                            var t= $('#power').tree('find', obj.tid);
                            $('#power').tree('check', t.target);
                        });

                    }
                })
            },

            onCheck: function (node, checked) {
                if (checked) {
                    var parentNode = $("#power").tree('getParent', node.target);
                    if (parentNode != null) {
                        $("#power").tree('check', parentNode.target);
                    }
                } else {
                    var childNode = $("#power").tree('getChildren', node.target);
                    if (childNode.length > 0) {
                        for (var i = 0; i < childNode.length; i++) {
                            $("#power").tree('uncheck', childNode[i].target);
                        }
                    }
                }
            },


        });
    });
</script>
</body>
</html>
