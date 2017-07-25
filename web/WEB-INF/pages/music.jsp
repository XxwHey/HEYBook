<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/1
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>MP3</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <link rel="stylesheet" href="${basePath}/static/common/css/music.css">
</head>
<body>
<section class="content">
    <div class="container-fluid" style="width: 80%;">
        <header>
            <h1>MP3</h1>
        </header>
        <div class="panel">
            <!-- 列表头部 -->
            <!-- 列表项组 -->
            <table class="table table-hover table-borderless">
                <thead id="musicApp">
                <tr>
                    <%--<th>--%>
                        <%--<div>--%>
                            <%--<label>--%>
                                <%--<input type="checkbox" class="checkbox-inline checkAll" name="checkbox">--%>
                            <%--</label>--%>
                        <%--</div>--%>
                    <%--</th>--%>
                    <th v-for="thead in theads" style="vertical-align: middle; text-align: center">{{ thead.name }}</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <!-- 列表底部 -->
        </div>
        <footer>
            <ul class="pager pager-justify">
                <li class="previous"></li>
                <li class="next"></li>
            </ul>
        </footer>
    </div>
</section>
<script src="${basePath}/static/common/js/music.js"></script>
</body>
</html>
