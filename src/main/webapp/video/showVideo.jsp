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
    <title>视频信息</title>
    <script>
        $(function () {
            videoInit();
        });
        function videoInit() {
            $("#videoTable").jqGrid({
                url : "${path}/video/all",
                datatype : "json",
                rowNum : 8,
                sortname : 'id',
                viewrecords : true,
                styleUI:"Bootstrap",
                editurl:"${path}/video/edit",
                autowidth:true,
                height:"auto",
                colNames : [ '编号', '标题', '简介', '视频', '上传时间', '收藏次数', '点赞次数','类别id', '用户id', '分组id'],
                colModel:[
                    {name:"id",align:'center',editable:false},
                    {name:"title",align:'center',editable:true},
                    {name:"britf",align:'center',editable:true,},
                    {name:"videoPath",align:'center',editable:true,width:300,edittype:"file",
                        formatter:function (cellvalue, options, rowObject) {
                            return "<video id='media' src='"+cellvalue+"'controls='controls' width='300px' height='150px' poster='"+rowObject.coverPath+"' />";
                        }
                    },
                    {name:"uploadTime",align:'center',editable:false},
                    {name:"likeCount",align:'center',editable:false},
                    {name:"playCount",align:'center',editable:false},
                    {name:"categoryId",align:'center',editable:false,
                    },
                    {name:"userId",align:'center',editable:false},
                    {name:"groupId",align:'center',editable:false}
                ],
                pager : '#videoPage',
                page:1,
                rowNum:2,
                viewrecords:true,
                autowidth:true,

            }).navGrid("#videoPage",{add:true,edit:false,del:true,search:false,refresh:true},
                {},//edit
                {
                    closeAfterAdd: true,//关闭添加框
                    afterSubmit:function (data) {
                        //信息入库  返回id
                        //文件上传
                        console.log(data.responseText);
                        $.ajaxFileUpload({
                           url:"${path}/video/upload",
                            type:"post",
                            data:{'id':data.responseText},
                            fileElementId:"videoPath",//文件选择框的id属性
                            success:function () {
                                //上传成功刷新页面
                                $("#videoTable").trigger("reloadGrid");
                            }
                        });
                        return "true";
                    }
                },//add

                {}//del
            )


        }
    </script>
</head>
<body>
<%--设置面板--%>
<div class="panel panel-warning">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">视频信息</a></li>
    </ul>

    <%--表单--%>
    <table id="videoTable" />

    <%--分页工具栏--%>
    <div id="videoPage" />

</div>
</body>
</html>