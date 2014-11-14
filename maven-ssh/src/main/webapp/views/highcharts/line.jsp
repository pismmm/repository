<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-9-28
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>

    <script type="text/javascript">
        var i = 1, chart;
        $(function () {
            $('#container').highcharts({
                title: {
                    text: 'Monthly Average Temperature',
                    x: -20 //center
                },
                subtitle: {
                    text: 'Source: WorldClimate.com',
                    x: -20
                },
                xAxis: {
                    categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                },
                yAxis: {
                    title: {
                        text: 'Temperature (°C)'
                    },
                    plotLines: [
                        {
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }
                    ]
                },
                tooltip: {
                    valueSuffix: '°C'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [
                    {
                        name: 'Tokyo',
                        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                    },
                    {
                        name: 'New York',
                        data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
                    },
                    {
                        name: 'Berlin',
                        data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
                    },
                    {
                        name: 'London',
                        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                    }
                ],
                labels:{
                    items:[{
                        //标签代码（html代码）
                        html:'<p style="font-size:20">Copyright © 2012-2013 </p><a href="http://www.52wulian.org" style="font-size:20;text-decoration: underline;">我爱物联网</a>',
                        style:{ //设置标签位置
                            left:'100px',
                            top:'50px'
                        }
                    }],
                    style:{	//设置标签颜色
                        color:'rgb(0,0,255)'
                    }
                }

            });
            chart = $('#container').highcharts();
            //更改标题
            $("#toggleTooltip")
                    .button()
                    .click(function (event) {
                        chart.setTitle({ style: { color: 'red' }},
                                // 更新标题的颜色
                                { style: { color: '#760000' }} // 更新副标题的颜色
                        );
                        $(".highcharts-tooltip").toggle();
                    }
            );
        });

    </script>
</head>
<body>
<div id="container" style="min-width:700px;height:400px"></div>
<br>
<button id="toggleTooltip">显示/隐藏Tooltip</button>
</body>
</html>
