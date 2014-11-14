<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-9-13
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jq/jquery-ui-1.11.1/jquery-ui.css">
    <link id="jquery-ui-theme" rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jq/jquery-ui-themes-1.11.1/themes/xxx/theme.css">
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-1.8.2.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-cookie/jquery.cookie.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-ui-1.11.1/jquery-ui.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jq/jquery-layout/jquery.layout-latest.js"
            type="text/javascript"></script>
    <!ztree -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jq/jquery-ztree-v3.5/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/jq/jquery-ztree-v3.5/js/jquery.ztree.all-3.5.js"></script>
    <link href="${pageContext.request.contextPath}/plugins/jq/jquery.jqGrid-4.4.3/css/ui.jqgrid.css" rel="stylesheet"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/jq/jquery.jqGrid-4.4.3/js/i18n/grid.locale-cn.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/jq/jquery.jqGrid-4.4.3/js/jquery.jqGrid.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/jq/jquery-contextMenu/jquery.contextmenu.r2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-main-szc-1.0.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/layer-v1.8.5/layer/layer.min.js"></script>
    <!引入highcharts -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/highCharts/Highcharts-4.0.3/js/highcharts.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/highCharts/Highcharts-4.0.3/js/modules/exporting.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/highCharts/Highcharts-4.0.3/js/highcharts-3d.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/highCharts/Highcharts-4.0.3/js/themes/dark-green.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jq/jquery-easyvalidate/js/easy_validator.pack.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jq/jquery-easyvalidate/js/jquery.bgiframe.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var jqueryuithme = $.cookie("jquery-ui-theme");
            if (jqueryuithme == undefined || jqueryuithme == null) {

            } else {
                $("#jquery-ui-theme").attr("href", '${pageContext.request.contextPath}/plugins/jq/jquery-ui-themes-1.11.1/themes/' + jqueryuithme + '/theme.css');
            }
            //主题切换
            $("#themeSwitch").selectmenu({
                icons: { button: "ui-icon-circle-triangle-s"},
                change: function (event, ui) {
                    var value = ui.item.value;
                    var href = $("#jquery-ui-theme").attr("href", "${pageContext.request.contextPath}/plugins/jq/jquery-ui-themes-1.11.1/themes/" + value + "/theme.css");
                    //写入cookie
                    $.cookie('jquery-ui-theme', value, {expires: 7});
                }
            });
        });
    </script>
</head>
<body>
<div class="ui-layout-north ui-widget ui-widget-header ui-widget-content">
    <table id="topTable" style="padding: 0px;margin: -5px 0px 0px;" width="100%">
        <tr>
            <td width="40px">
                <img src="${ pageContext.request.contextPath}/images/logo.jpg" height="40" align="top"
                     style="margin-top:5px"/>
            </td>
            <td>
                <span style="font-size: 17px;color:#FFFF33">JqueryUI、ssh全注解、ZTree、JqGrid、<br/>演示系统</span><br/>
            </td>
            <td>
                <div style="float:right; color: #fff;font-size: 12px;margin-top: 2px">
                    <div>
                        <label for="currentUserId">欢迎：</label>
                        <span id="currentUserId" title="${userInfo.username }">${userInfo.username }</span>
                    </div>
                    <div style="text-align: right;">
                        <select id="themeSwitch" style="vertical-align: middle">
                            <option value="base">基本</option>
                            <option value="black-tie">黑白条纹</option>
                            <option value="blitzer">红色</option>
                            <option value="cupertino">淡蓝</option>
                            <option value="dot-luv">黑蓝条纹</option>
                            <option value="excite-bike">蓝黄条纹</option>
                            <option value="hot-sneaks">深色条纹</option>
                            <option value="humanity">深橘黄</option>
                            <option value="mint-choc">--咖啡色</option>
                            <option value="redmond">深蓝</option>
                            <option value="smoothness">灰色</option>
                            <option value="south-street">深灰</option>
                            <option value="start">蓝绿</option>
                            <option value="swanky-purse">土色方格</option>
                            <option value="trontastic">黑绿色</option>
                            <option value="ui-darkness">黑黄色</option>
                            <option value="ui-lightness">橘黄</option>
                            <option value="vader">黑灰</option>
                        </select>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
<div class="ui-layout-center ui-widget-content">
    <div id="tabs">
        <ul>
            <li><a href="welcome.jsp">首页</a></li>
        </ul>
    </div>
</div>
<div class="ui-layout-west ui-widget-content">
    <ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
</div>
<div class="ui-layout-south ui-widget-header">版本信息</div>
<div class="ui-layout-east ui-widget-content" style="width: 500">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/jq/jquery-themeSwitcher/jquery.themeswitcher.js"></script>
    <div id="switcher" style="display: inline"></div>
    <script>
        $('#switcher').themeswitcher();
    </script>
</div>
</body>
</html>
