<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-9-30
  Time: 8:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <tr>
        <td> 账号:</td>
        <td><input id="username" name="username" class="ui-widget-content ui-corner-all"/>
        </td>
    </tr>
    <tr>
        <td>密码:</td>
        <td><input id="password" name="password" class="ui-widget-content ui-corner-all"/>
        </td>
    </tr>
    <tr>
        <td> 验证码:</td>
        <td>
            <input name="validateCode" class="ui-widget-content ui-corner-all" style="width: 120px"
                   onblur="validateAuthCode(this.value)"/>
            <img src="${pageContext.request.contextPath}/authCode" onclick="changeImg()" id="authCode"
                 style="vertical-align: middle">

            <div id="info"></div>
            <script type="text/javascript">
                function changeImg() {
                    $("#authCode").attr("src", "${pageContext.request.contextPath}/authCode.action?d=" + new Date().valueOf());
                }
            </script>
        </td>
    </tr>
</table>
</body>
</html>
