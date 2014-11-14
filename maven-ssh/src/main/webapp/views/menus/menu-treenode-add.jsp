<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-9-26
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        .form-label, .form-input {
            display: block;
        }

        .form-input-text {
            margin-bottom: 12px;
            width: 95%;
            padding: .4em;
        }

        fieldset {
            padding: 0;
            border: 0;
            margin-top: 25px;
        }

        h1 {
            font-size: 1.2em;
            margin: .6em 0;
        }

        div#users-contain {
            width: 350px;
            margin: 20px 0;
        }

        div#users-contain table {
            margin: 1em 0;
            border-collapse: collapse;
            width: 100%;
        }

        div#users-contain table td, div#users-contain table th {
            border: 1px solid #eee;
            padding: .6em 10px;
            text-align: left;
        }

        .validateTips {
            border: 1px solid transparent;
            padding: 0.3em;
        }
    </style>
</head>
<body>
<p class="validateTips">带*是必填项</p>

<form id="treeNodeAddForm">
    <fieldset>
        <input type="hidden" id="pid" name="menu.pid">
        <label for="name" class="form-label">菜单名称<span style="color: #FF0000;">*</span></label>
        <input type="text" name="menu.name" id="name"
               class="form-input form-input-text ui-widget-content ui-corner-all">
        <label for="webSite" class="form-label">网址</label>
        <input type="text" name="menu.webSite" id="webSite"
               class="form-input form-input-text ui-widget-content ui-corner-all">
        <label for="iconOpen" class="form-label">默认图标</label>
        <input type="text" name="menu.iconOpen" id="iconOpen"
               class="form-input form-input-text ui-widget-content ui-corner-all">
        <label for="icon" class="form-label">展开时图标</label>
        <input type="text" name="menu.icon" id="icon"
               class="form-input form-input-text ui-widget-content ui-corner-all">
        <label for="iconClose" class="form-label">折叠时图标</label>
        <input type="text" name="menu.iconClose" id="iconClose"
               class="form-input form-input-text ui-widget-content ui-corner-all">
    </fieldset>
</form>
</body>
</html>
