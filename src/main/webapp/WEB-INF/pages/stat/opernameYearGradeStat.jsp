<%--
  Created by IntelliJ IDEA.
  User: q
  Date: 2019/12/4
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <!-- 参照bar-tick-align.html -->
    <title>业务员年度统计</title>
    <link rel="stylesheet" href="${whContextPath }/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${whContextPath }/resources/css/public.css" media="all" />
</head>
<body style="height: 100%; margin: 0">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">选择年份:</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="year" readonly="readonly" placeholder="yyyy">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->
<div id="container" style="height: 70%"></div>
<script type="text/javascript" src="${whContextPath }/resources/echarts/js/echarts.min.js"></script>
<script type="text/javascript" src="${whContextPath }/resources/echarts/js/jquery-3.1.1.min.js"></script>
<script src="${whContextPath }/resources/layui/layui.js"></script>

<script type="text/javascript">
    layui.use([ 'jquery', 'layer', 'form', 'table','laydate'  ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate=layui.laydate;

        laydate.render({
            elem:'#year',
            type:'year',
            value:new Date()
        });
        $("#doSearch").click(function(){
            getData();
        });
        function getData(){
            var year=$("#year").val();
            if(year===""){
                year=new Date().getFullYear();
            }
            $.get("${whContextPath}/stat/loadOpernameYearGradeStat",{year:year},function(data){
                var dom = document.getElementById("container");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                app.title = '坐标轴刻度与标签对齐';

                option = {
                    title : {
                        text: '业务员年度销售额统计',
                        x:'center'
                    },
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : data.name,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'销售额',
                            type:'bar',
                            barWidth: '60%',
                            data:data.value
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            });
        }
        $(function(){
            getData();
        })
    });

</script>
</body>
</html>