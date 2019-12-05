<%--
  Created by IntelliJ IDEA.
  User: q
  Date: 2019/12/4
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <!-- 参照pie-simple.html -->
    <title>客户地区统计分析</title>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<!-- 导入Ecahrts图表需要的js文件 -->
<script type="text/javascript" src="${whContextPath}/resources/echarts/js/echarts.min.js"></script>
<script type="text/javascript" src="${whContextPath}/resources/echarts/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
    $.get("${whContextPath}/stat/loadCustomerAreaStatJson", function (data) {
        //todo 后台返回的数据List<> 符合这里展示的json数据格式[{},{}]
        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            title: {
                text: '汽车出租系统客户地址统计',
                subtext: '真实有效',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: data
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    });
</script>
</body>
</html>
