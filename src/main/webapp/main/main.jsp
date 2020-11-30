<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学App后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
    <!--顶部导航-->
    <nav class="navbar navbar-inverse">

            <!-- 导航条标题 -->
            <div class="navbar-header">
                <!--自适应-->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${path}/main/main.jsp">应学视频App后台管理系统</a>
            </div>

            <!-- 导航栏上工具-->
            <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
                <!--右边工具栏-->
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">欢迎:   <span class="text-info">${sessionScope.admin.username}</span></a></li>
                    <li><a href="${path}/admin/loginOut">退出系统 <span class="glyphicon glyphicon-log-out"></span> </a></li>
                </ul>
            </div>
    </nav>



    <!--栅格系统-->
    <div class="container-fluid">
        <div class="row">
            <!--左边手风琴部分-->
            <div class="col-md-2">
                <!--菜单-->
                <div class="panel-group text-center" id="accordion" role="tablist" aria-multiselectable="true">

                    <!--面板1-->
                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">

                                <button class="btn btn-info"><a href="javascript:;" onclick="javascript:$('#content').load('${path}/user/showUser.jsp');" style="color: white;text-decoration: none;">用户展示</a></button>
                                <br> <br>
                                 <button class="btn btn-info"><a href="javascript:;"onclick="javascript:$('#content').load('${path}/user/userStatistics.jsp');" style="color: white;text-decoration: none;"> 用户统计</a></button>
                                <br> <br>
                                 <button class="btn btn-info"><a href="javascript:;"onclick="javascript:$('#content').load('${path}/user/userStatistics.jsp');" style="color: white;text-decoration: none;">用户分布</a></button>
                                <br>
                            </div>
                        </div>
                    </div>

                    <!--面板2-->
                    <div class="panel panel-danger">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseOne">
                                    分类管理
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <button class="btn btn-danger"><a href="javascript:;" onclick="javascript:$('#content').load('${path}/category/cate.jsp');" style="color: white;text-decoration: none;">分类展示</a></button>
                            </div>
                        </div>
                    </div>

                    <!--面板3-->

                    <div class="panel panel-warning">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseOne">
                                    视频管理
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <button class="btn btn-warning"><a href="javascript:;" onclick="javascript:$('#content').load('${path}/video/showVideo.jsp');" style="color: white;text-decoration: none;">视频资料</a></button>
                            </div>
                        </div>
                    </div>
                    <!--面板4-->
                    <div class="panel panel-success">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="collapseOne">
                                   日志管理
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                            <div class="panel-body">
                                <button class="btn btn-success"><a href="javascript:;" onclick="javascript:$('#content').load('${path}/log/showLog.jsp');" style="color: white;text-decoration: none;">日志信息</a></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <!--巨幕开始-->
            <div class="col-sm-10" id="content">
                <div class="col-sm-12">
                    <div class="jumbotron" style="text-align: center;">
                        <h1>欢迎来到应学视频App后台管理系统</h1>
                    </div>

                <%--轮播图--%>
                    <!--创建轮播图-->
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="width: 1740px;">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="5"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="${path}/bootstrap/img/1.jpg" style="width: 1740px; height: 520px;" alt="...">
                                <div class="carousel-caption">
                                    <p>多喜乐，常安宁</p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/2.jpg" style="width: 1740px;height: 520px;" alt="...">
                                <div class="carousel-caption">
                                    <p>多喜乐，常安宁</p>

                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/3.jpg" style="width: 1740px;height: 520px;" alt="...">
                                <div class="carousel-caption">
                                    <p>多喜乐，常安宁</p>

                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/4.jpg" style="width: 1740px;height: 520px;" alt="...">
                                <div class="carousel-caption">
                                    <p>多喜乐，常安宁</p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/5.jpg" style="width: 1740px;height: 520px;" alt="...">
                                <div class="carousel-caption">
                                    <p>多喜乐，常安宁</p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/6.jpg" style="width: 1740px;height: 520px;" alt="...">
                                <div class="carousel-caption">
                                    <p>多喜乐，常安宁</p>
                                </div>
                            </div>

                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        </a>

                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        </a>

                    </div>
                </div>
            </div>


        </div>
    </div>
    </div>


        <!--页脚-->
        <nav class="navbar navbar-default navbar-fixed-bottom" style="padding-top: 15px;">
            <div class="container"  style="text-align: center;">
                <p>百知教育hux@qq.com</p>
                <hr>
            </div>
        </nav>




    <!--栅格系统-->

</body>
</html>
