<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/2
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>ALBUM</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <link rel="stylesheet" href="${basePath}/static/common/css/album.css">
</head>
<body>
<section class="content">
    <div class="container-fluid" style="width: 80%;">
        <header>
            <h1>ALBUM</h1>
        </header>
        <div id="page" class="carousel slide" data-ride="carousel" data-interval="">
            <!-- 圆点指示器 -->
            <ol class="carousel-indicators" style="position: fixed; bottom: 30px;">
                <li data-target="#page" data-slide-to="0" class="active"></li>
                <c:forEach var="pageCount" items="${pageCount}">
                    <li data-target="#page" data-slide-to="${pageCount - 1}"></li>
                </c:forEach>
            </ol>

            <!-- 轮播项目 -->
            <div class="carousel-inner" style="overflow-y: scroll">
                <div id="item1" class="item active">
                    <div id="cards1" class="cards"></div>
                </div>
                <c:forEach var="pageCount" items="${pageCount}">
                    <div id="item${pageCount}" class="item">
                        <div id="cards${pageCount}" class="cards"></div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- 项目切换按钮 -->
    <a id="previous" class="left carousel-control" href="#page" data-slide="prev" data-toggle="tooltip" data-placement="right" title="上一页">
        <span class="icon icon-chevron-left"></span>
    </a>
    <a id="next" class="right carousel-control" href="#page" data-slide="next" data-toggle="tooltip" data-placement="left" title="下一页">
        <span class="icon icon-chevron-right"></span>
    </a>
</section>
<div class="modal fade" id="detail">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
                <h4 class="modal-title">Album Details</h4>
            </div>
            <div class="modal-body">
                <div class="list">
                    <header id="dHead">

                    </header>
                    <div class="items items-hover" id="dItems">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">全部播放</button>
            </div>
        </div>
    </div>
</div>

<script src="${basePath}/static/common/js/album.js"></script>
</body>
</html>
