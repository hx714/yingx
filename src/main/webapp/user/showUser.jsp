<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    function update(id,status){
        let b = confirm("确定修改吗？");
        if(b == true){
            $.ajax({
               url:"${pageContext.request.contextPath}/user/update",
               data:{'id':id,"status":status},
               datatype:"JSON",
               success:function (res) {
                   $('#user').trigger('reloadGrid');
               }
            });
        }
    }

    $(function () {
        $("#btn").click(function () {
            let b = confirm("确定导出吗？");
            if(b == true){
                $.post("${path}/user/getUserEasyPoi",{},function (res) {
                    if(res.success==true){
                        alert(res.message);
                    }else{
                        alert(res.message);
                    }
                },"JSON");
            }
        });
    });


    $(function () {
       $("#user").jqGrid({
           styleUI:"Bootstrap",//构建一个boottrap风格表格
           url:"${pageContext.request.contextPath}/user/all",//用来发送服务端地址
           datatype:"json",//定义服务器端返回数据格式为json
           rowNum:10,
           pager:'#userPage',
           autowidth:true,//自适应
           height:"auto",
           colNames:["编号","头像","名字","密码","状态","手机号","注册时间"],//用来定义表格中显示列
           colModel:[
               {name:"id",align:'center',editable:true},
               {name:"picImg",align:'center',editable:true,formatter:function (value,options,rows) {
                       return "<img src='${pageContext.request.contextPath}/user/images/"+value+"' style='width:160px;height:160px'>";
                   }
               },
               {name:"nickName",align:'center',editable:true,search:false},
               {name:"password",align:'center',editable:true},
               {name:"status",align:'center',editable:true,formatter:function (value,options,rows) {
                       if(value=='正常') return "<button class='btn btn-success' onclick='update(\""+rows.id+"\",\""+value+"\")'>"+value+"</button>"
                       else return "<button class='btn btn-danger' onclick='update(\""+rows.id+"\",\""+value+"\")'>"+value+"</button>"
                   }
               },
               {name:"phone",align:'center',editable:true,search:false},
               {name:"createDate",align:'center',editable:true,formatter:'date',formatoptions:{newformat:'Y-m-d'},},
           ],
           pager:"#userPage",
           page:1,
           rowNum:3,
           viewrecords:true,//显示数据库中的总条数
           autowidth:true,
           hiddengrid:false,//控制表格
           editurl:"${path}/user/edit",}).navGrid("#userPage",
           {add:false,edit:true,search:false,refresh:true},
           {closeAfterEdit:false,reloadAfterSubmit:false},//对修改配置
           {closeAfterAdd:false,reloadAfterSubmit:false},//对添加配置
           {closeAfterDelete:false,reloadAfterSubmit:false}//对删除配置
       );
    });

</script>

<%--设置面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="profile"><a href="#user" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
        <li role="presentation" class="profile"><a href="#user1" data-toggle="modal" role="tab" data-toggle="tab">用户统计</a></li>
    </ul>

    <div>
        <button class="btn btn-info" id="btn">导出用户信息</button>
        <button class="btn btn-success">导入用户</button>
    </div><br>

    <%--表单--%>
    <table id="user" />

    <table id="user1" />

    <%--分页工具栏--%>
    <div id="userPage" />

</div></div>