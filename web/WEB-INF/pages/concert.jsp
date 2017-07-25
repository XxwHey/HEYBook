<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/9
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>CONCERT</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <link rel="stylesheet" href="${basePath}/static/common/css/concert.css">
</head>
<body>
<section class="content">
    <div class="container-fluid" style="width: 80%;">
        <div class="list">
            <header>
                <h1>CONCERT
                    <small id="time"></small>
                </h1>
            </header>
            <div id="concert" class="items items-hover" style="height: 70%; overflow-y: scroll"></div>
            <footer>
                <ul class="pager pager-justify">
                    <li class="previous disabled"></li>
                    <li class="next"></li>
                </ul>
            </footer>
        </div>
    </div>
</section>
<script src="${basePath}/static/common/js/concert.js"></script>
</body>
</html>
