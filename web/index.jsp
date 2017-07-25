<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/27
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>上上谦__薛之谦个人网站</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <c:import url="${basePath}/WEB-INF/pages/common/cssHeader.jsp"/>
    <link rel="stylesheet" href="${basePath}/static/common/css/index.css">
    <link rel="stylesheet" href="${basePath}/static/APlayer/audioplayer.css">
    <script src="${basePath}/static/VueJS/vue.js"></script>
    <%--favicon--%>
    <link rel="shortcut icon" href="${basePath}/static/common/images/faviconqian.ico">
</head>
<body style="overflow-y: hidden;">
<nav class="navbar navbar-default" role="navigation" style="padding: 10px; z-index: 99999">
    <div class="container-fluid">
        <!-- 导航头部 -->
        <div class="navbar-header">
            <!-- 移动设备上的导航切换按钮 -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- 品牌名称或logo -->
            <a id="index" href="#">
                <img src="static/common/images/logo.png">
            </a>
        </div>
        <!-- 导航项目 -->
        <div class="collapse navbar-collapse navbar-collapse-example">
            <!-- 一般导航项目 -->
            <ul class="nav navbar-nav" style="padding-left: 20px;">
                <li><a href="#" onclick="loadPage('music.do')">
                    <span><i class="icon-music"></i></span>&nbsp;MP3</a></li>
                <li><a href="#" onclick="loadPage('photo.do')">
                    <span><i class="icon-picture"></i></span>&nbsp;JPG</a></li>
                <li><a href="#" onclick="loadPage('album.do')">
                    <span><i class="icon-headphones"></i></span>&nbsp;ALBUM</a></li>
                <li><a href="#"><span><i class="icon-film"></i></span>&nbsp;VIDEO</a></li>
                <li><a href="#"><span><i class="icon-plane"></i></span>&nbsp;SCHEDULE</a></li>
                <li><a href="#"><span><i class="icon-newspaper-o"></i></span>&nbsp;NEWS</a></li>
                <li><a href="#" onclick="loadPage('concert.do')">
                    <span><i class="icon-calendar"></i></span>&nbsp;CONCERT</a></li>
                <li><a href="#" onclick="loadPage('biography.do')">
                    <span><i class="icon-user"></i></span>&nbsp;BIOGRAPHY</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="padding-right: 20px;">
                <li style="padding-top: 3px"><input id="search" type="text" class="form-control" placeholder="站内搜索">
                </li>
                <li onclick="searchTest()"><a href="#">登录</a></li>
                <li><h4> | </h4></li>
                <li><a href="#">注册</a></li>
            </ul>
        </div><!-- END .navbar-collapse -->
    </div>
</nav>

<div id="mainContent" class="content scrollbar-hover" style="overflow-y: scroll">

</div>

<%--<footer>--%>
    <%--<div>--%>
        <%--<audio id="player">--%>
            <%--<source src="${basePath}/static/common/audio/gaoshang.mp3">--%>
        <%--</audio>--%>
    <%--</div>--%>
<%--</footer>--%>

<c:import url="${basePath}/WEB-INF/pages/common/jsHeader.jsp"/>
<script src="${basePath}/static/common/js/index.js"></script>
<script src="${basePath}/static/APlayer/audioplayer.min.js"></script>
</body>
</html>