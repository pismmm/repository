<%--
  Created by IntelliJ IDEA.
  User: shizhenchao
  Date: 2014-9-13
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>演示系统-登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jq/jquery-ui-1.11.1/jquery-ui.css">
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-ui-1.11.1/jquery-1.11.1.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-ui-1.11.1/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-validation-1.13.0/dist/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validate-extend-zh_cn.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-metadata/jquery-metadata-2.1.js"></script>
    <style>
        .no-close .ui-dialog-titlebar-close {
            display: none;
        }
    </style>
    <script>
        var validateAuthCode;//验证验证码
        $(function () {
            //验证form
            $.metadata.setType("attr", "validate");
            $("#loginForm").validate();
            $("#dialog").dialog({
                autoOpen: true,
                height: '250',
                width: '400',
                modal: true,
                dialogClass: "no-close",//隐藏关闭按钮方式2
                show: {
                    effect: "blind",
                    duration: 1000
                },
                hide: {
                    effect: "explode",
                    duration: 1000
                },
                open: function (event, ui) {
                    //$(".ui-dialog-titlebar-close").hide();//隐藏关闭按钮方式1
                },
                buttons: {
                    "登录": function () {
                        var url = "${pageContext.request.contextPath}/login";
                        var data = $("#loginForm").serialize();
                        $.ajax({
                            url: url,
                            type: 'post',
                            data: data,
                            success: function (data) {
                                if (data && data.jsonResult.flag) {
                                    location.href = "views/main.jsp";
                                }
                            },
                            error: function (data) {

                            }
                        })
                    },

                    "重置": function () {
                        $("#loginForm")[0].reset();
                    }
                }
            });
            $("#dialog").dialog("open");
        });
        validateAuthCode = function (val) {
            $.post("${pageContext.request.contextPath}/validateAuthCode", {authCode: val}, function (data) {
                if (data && data.jsonResult.flag) {
                }
            })
        }
    </script>
</head>
<body>
<div id="dialog">
    <form action="loginAction" method="post" id="loginForm">
        <table>
            <tr>
                <td> 账号:
                </td>
                <td><input id="username" name="username" class="ui-widget-content ui-corner-all"
                           validate="{required:true}"/>
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
    </form>
</div>
<div class="login-info-dialog"></div>
</body>
</html>