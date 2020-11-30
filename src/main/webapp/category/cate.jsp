<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
       cateInit();
    });
    function cateInit() {
        $("#cateTable").jqGrid({
            url : "${path}/cate/one",
            datatype : "json",
            rowNum : 8,
            sortname : 'id',
            viewrecords : true,
            styleUI:"Bootstrap",
            editurl:"${path}/cate/edit",
            autowidth:true,
            height:"auto",
            colNames : [ '编号', '类名', '级别', '父类别id'],
            colModel:[
                {name:"id",align:'center',editable:false},
                {name:"name",align:'center',editable:true},
                {name:"levels",align:'center',editable:false},
                {name:"parentId",align:'center',editable:false}
            ],
            pager : '#catePage',
            page:1,
            rowNum:3,
            viewrecords:true,
            autowidth:true,
            subGrid:true,//开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded:function (subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            },
        }).navGrid("#catePage",{add:true,edit:true,del:true,search:false,refresh:true},
            {closeAfterEdit: true,reloadAfterSubmit: true,},//edit
            {closeAfterAdd: true, reloadAfterSubmit: true,},//add
            {closeAfterDelete: true, reloadAfterSubmit: true,
                afterSubmit:function (data) {
                    console.log(data);
                    //设置提示信息
                    $("#message").html(data.responseJSON.message);

                    //展示警告框
                    $("#showMsg").show();
                    setTimeout(function () {
                        $("#showMsg").hide();
                    },2000);
                    return "true";
                }
            }//del
            );

    }

    //开启子表格样式
    function addSubGrid(subgridId, rowId) {
        var subgridTableTd= subgridId + "Table";
        var pagerId= subgridId+"Page";
        $("#"+subgridId).html("" +
            "<table id='"+subgridTableTd+"' />" +
            "<div id='"+pagerId+"' />"
        );

        $("#"+subgridTableTd).jqGrid({
            url : "${path}/cate/two?pId=" + rowId,
            datatype : "json",
            rowNum : 10,
            sortname : 'num',
            sortorder : "asc",
            styleUI:"Bootstrap",
            editurl:"${path}/cate/edit?parentId=" + rowId,
            autowidth:true,
            height:"auto",
            colNames : [ '编号', '类名', '级别', '父类别id'],
            colModel:[
                {name:"id",align:'center',editable:false},
                {name:"name",align:'center',editable:true},
                {name:"levels",align:'center',editable:false},
                {name:"parentId",align:'center',editable:false,
                    edittype:'select',editoptions:{dataUrl:"${path}/cate/selectOne"},
                }
            ],
            pager : "#"+pagerId,
            page:1,
            rowNum:3,
            viewrecords:true,
            autowidth:true,
        }).navGrid("#"+pagerId, {add:true,edit:true,del:true,search:false,refresh:true},
            {closeAfterEdit: true, reloadAfterSubmit: true,},//edit
            {closeAfterAdd: true, reloadAfterSubmit: true,},//add
            {closeAfterDelete: true, reloadAfterSubmit: true,
                afterSubmit:function (data) {
                    console.log(data);
                    //设置提示信息
                    $("#message").html(data.responseJSON.message);

                    //展示警告框
                    $("#showMsg").show();
                    setTimeout(function () {
                        $("#showMsg").hide();
                    },2000);
                    return "true";
                }
            } //del
        );
    }
</script>

<%--设置面板--%>
<div class="panel panel-danger">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>
    <%--警告框--%>
    <div id="showMsg" style="width:300px;display: none" class="alert alert-danger alert-dismissible" role="alert">
        <span id="message"/>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage" />

</div>