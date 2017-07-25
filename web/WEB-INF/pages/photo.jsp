<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>JPG</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <style type="text/css">
        .box {
            width: 220px;
            margin-bottom: 20px;
            margin-right: 20px;
        }
        .box img {
            max-width: 100%
        }
    </style>
</head>
<body>
<section class="content">
    <div class="container-fluid" style="width: 80%">
        <header>
            <h1>PHOTO</h1>
        </header>
        <div class="photos" id="photos">

        </div>
    </div>
</section>
<script src="${basePath}/static/Masonry/masonry-docs.min.js"></script>
<script src="${basePath}/static/common/js/photo.js"></script>
</body>
</html>
