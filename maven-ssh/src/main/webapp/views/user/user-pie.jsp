<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-9-28
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" charset="utf-8">
        var chart;
        $(function () {
            //管理员男女结构图
            $.ajax({
                url: '../user/userMaleAndFemale',
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if (!data) {
                        data = [];
                    } else {
                        chart = $('#userMaleAndFemale-container').highcharts({
                            chart: {
                                type: 'pie',
                                options3d: {
                                    enabled: true,
                                    alpha: 45,
                                    beta: 0
                                }
                            },
                            title: {
                                text: '系统管理员男女比例饼图'
                            },
                            tooltip: {
                                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    depth: 35,
                                    dataLabels: {
                                        enabled: true,
                                        format: '{point.name}'
                                    }
                                }
                            },
                            series: [
                                {
                                    type: 'pie',
                                    name: '管理员男女比例表',
                                    data: data
                                }
                            ]
                        });
                    }
                },
                error: function () {
                    alert("访问出错")
                }
            });
        });
    </script>
</head>
<body>
<div id="userMaleAndFemale-container" style="min-width:700px;height:400px"></div>
</body>
</html>
