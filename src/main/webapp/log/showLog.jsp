<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>日志管理</title>
    <script>
        $(function () {
            logInit();
        });
        function logInit() {
            $("#logTable").jqGrid({
                url : "${path}/log/all",
                datatype : "json",
                rowNum : 8,
                sortname : 'id',
                viewrecords : true,
                styleUI:"Bootstrap",
                editurl:"${path}/log/edit",
                autowidth:true,
                height:"auto",
                colNames : [ '编号', '用户名', '启用时间', '操作', '状态'],
                colModel:[
                    {name:"id",align:'center',editable:false},
                    {name:"name",align:'center',editable:true},
                    {name:"times",align:'center',editable:true,},
                    {name:"options",align:'center',editable:true,},
                    {name:"status",align:'center',editable:false},
                ],
                pager : '#logPage',
                page:1,
                rowNum : 3,
                viewrecords:true,
                autowidth:true,

            }).navGrid("#logPage",{add:false,edit:false,del:false,search:false,refresh:true})
        }
    </script>
</head>
<body>
<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>日志信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">日志信息</a></li>
    </ul>

    <%--表单--%>
    <table id="logTable" />

    <%--分页工具栏--%>
    <div id="logPage" />

</div>
</body>
</html>