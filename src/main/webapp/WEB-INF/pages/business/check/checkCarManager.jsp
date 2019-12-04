<%--
  Created by IntelliJ IDEA.
  User: q
  Date: 2019/12/4
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <!-- 添加bus_check表,汽车入库 -->
    <title>车辆入库管理</title>
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="${whContextPath }/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${whContextPath }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出租单号:</label>
            <div class="layui-input-inline">
                <input type="text" name="search_rentid"  id="search_rentid" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
        </div>
    </div>
</form>


<div id="content" style="display: none;">
    <!-- 检查单位的表单 -->
    <blockquote class="layui-elem-quote" style="margin-top: 10px;">
        <h2>检查单表单</h2>
        <hr>
        <form class="layui-form" method="post" id="checkFrm" lay-filter="checkFrm">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">检查单号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="checkid" readonly="readonly"
                               placeholder="请输入检查单号" id="checkid" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">出租单号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="rentid" readonly="readonly"
                               placeholder="请输入出租单号" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">检查时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="checkdate" readonly="readonly"
                               placeholder="请选择检查时间" id="checkdate" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">存在问题:</label>
                    <div class="layui-input-inline">
                        <!-- lay-verify="required" 必填  -->
                        <input type="text" name="problem" lay-verify="required" placeholder="请输入出存在问题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">赔付金额:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="paymoney" placeholder="请输入赔付金额"
                               lay-verify="required|number" value="0" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">操作员:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="opername" placeholder="请输入操作员"
                               readonly="readonly" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">问题描述:</label>
                <div class="layui-input-block">
						<textarea placeholder="请输入问题描述" lay-verify="required" name="checkdesc"
                                  class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center;">
                <!-- lay-submit=""提交 -->
                <button type="button" lay-submit="" lay-filter="doSubmit"
                        class="layui-btn layui-btn-normal  layui-icon layui-icon-release"
                        id="doSubmit">保存</button>
            </div>
        </form>
    </blockquote>

    <!-- todo 2.出租单  车辆  客户的信息展开 -->
    <div style="padding: 10px; background-color: #F2F2F2;">
        <div class="layui-row layui-col-space8">
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">车辆信息</div>
                    <div class="layui-card-body" id="car_carnumber">
                    </div>
                    <div class="layui-card-body" id="car_cartype">
                    </div>
                    <div class="layui-card-body" id="car_color">
                    </div>
                    <div class="layui-card-body" id="car_price">
                    </div>
                    <div class="layui-card-body" id="car_rentprice">
                    </div>
                    <div class="layui-card-body" id="car_deposit">
                    </div>
                    <div class="layui-card-body" id="car_description">
                    </div>
                    <div class="layui-card-body">
                        车辆图片:<img alt="" width="200" height="150" id="car_carimg" src="">
                    </div>
                </div>
            </div>
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">出租单信息</div>
                    <div class="layui-card-body" id="rent_rentid">
                    </div>
                    <div class="layui-card-body" id="rent_price">
                    </div>
                    <div class="layui-card-body" id="rent_begindate">
                    </div>
                    <div class="layui-card-body" id="rent_returndate">
                    </div>
                    <div class="layui-card-body" id="rent_opername">
                    </div>
                </div>
            </div>
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">客户信息</div>
                    <div class="layui-card-body" id="customer_identity">
                    </div>
                    <div class="layui-card-body" id="customer_custname">
                    </div>
                    <div class="layui-card-body" id="customer_sex">
                    </div>
                    <div class="layui-card-body" id="customer_address">
                    </div>
                    <div class="layui-card-body" id="customer_phone">
                    </div>
                    <div class="layui-card-body" id="customer_career">
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="${whContextPath }/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table', 'laydate' ],
        function() {
            var $ = layui.jquery;
            var layer = layui.layer;
            var form = layui.form;
            var table = layui.table;
            var laydate = layui.laydate;

            //根据出租单号查询
            $("#doSearch").click(function() {
                var rentid = $("#search_rentid").val();
                $.post("${whContextPath}/check/checkRentExist", {
                    rentid : rentid
                }, function(obj) {
                    //接收的是一个Rent对象
                    if (obj === "") {
                        layer.msg("您输入的出租单号不存在,请更正后再查询");
                        $("#content").hide();
                    } else {
                        if (obj.rentflag == 1) {
                            layer.msg("您输入的出租单相关车辆已经归还，无需再入库");
                            $("#content").hide();
                        } else {
                            //未归还的出租单号,信息初始化
                            initCheckFormData(rentid);
                            $("#content").show();
                        }
                    }
                })
            });

             //TODO 加载表单数据 和 卡片面板的数据
             //todo 后台通过Map将所有的信息展示出来
            //只需要传入出租单的id,就可以获取客户信息id,汽车信息id
            function initCheckFormData(rentid){
                $.post("${whContextPath}/check/initCheckFormData",{rentid:rentid},function(obj){
                    //检查单信息
                    var check=obj.check;
                    form.val("checkFrm",check);

                    //车辆信息
                    var car=obj.car;
                    $("#car_carnumber").html("车辆号牌: "+car.carnumber);
                    $("#car_cartype").html("车辆类型: "+car.cartype);
                    $("#car_color").html("车辆颜色: "+car.color);
                    $("#car_price").html("购买价格: "+car.price);
                    $("#car_rentprice").html("出租价格: "+car.rentprice);
                    $("#car_deposit").html("出租押金: "+car.deposit);
                    $("#car_description").html("车辆描述: "+car.description);
                    $("#car_carimg").attr("src","${whContextPath}/file/downloadShowFile?path="+car.carimg);

                    //出租单信息
                    var rent=obj.rent;
                    $("#rent_rentid").html("出租单号: "+rent.rentid);
                    $("#rent_price").html("出租价格: "+rent.price);
                    $("#rent_begindate").html("起租时间: "+rent.begindate);
                    $("#rent_returndate").html("还车时间: "+rent.returndate);
                    $("#rent_opername").html("操作员: "+rent.opername);

                    //客户信息
                    var customer=obj.customer;
                    $("#customer_identity").html("身份证号: "+customer.identity);
                    $("#customer_custname").html("客户姓名: "+customer.custname);
                    $("#customer_sex").html("客户性别: "+(customer.sex==1?'男':'女'));
                    $("#customer_address").html("客户地址: "+customer.address);
                    $("#customer_phone").html("客户电话: "+customer.phone);
                    $("#customer_career").html("客户职位: "+customer.career);

                })
            }

            //保存
            form.on("submit(doSubmit)",function(){
                var params=$("#checkFrm").serialize();
                $.post("${whContextPath}/check/saveCheck",params,function(obj){
                    layer.msg(obj.msg);
                    //隐藏信息
                    $("#content").hide();
                });
                return false;
            });
        });
</script>
</body>
</html>
