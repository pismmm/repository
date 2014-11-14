<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-9-26
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-menus-szc-1.0.js"></script>
</head>
<body>
<button id="btn">Ztree Dialog</button>
<div id="btnDialog">
    <ul id="ulTree2" class="ztree"></ul>
</div>
<ul id="menusTreeForDeal" class="ztree"></ul>
<div id="treeNodeAddDialog" title="新增节点" class="ui-helper-hidden" style="font-size: 62.5%">
    <jsp:include page="menu-treenode-add.jsp"/>
</div>
</body>
</html>
