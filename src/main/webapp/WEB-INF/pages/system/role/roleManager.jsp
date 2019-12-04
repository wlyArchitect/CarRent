<%--
  Created by IntelliJ IDEA.
  User: q
  Date: 2019/11/26
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
          isELIgnored="false" %>
<html>
<head>
    <title>角色管理</title>
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/wlyLogo.png">
    <link rel="stylesheet" href="${whContextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${whContextPath}/resources/css/public.css" media="all" />
    <link rel="stylesheet" href="${whContextPath}/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${whContextPath}/resources/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">角色名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="rolename"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">角色备注:</label>
            <div class="layui-input-inline">
                <input type="text" name="roledesc"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否可用:</label>
            <div class="layui-input-inline">
                <input type="radio" name="available" value="1" title="可用">
                <input type="radio" name="available" value="0" title="不可用">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
<div style="display: none;" id="roleToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div  id="roleBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="selectRoleMenu">分配菜单</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称:</label>
            <div class="layui-input-block">
                <!-- todo  隐藏角色id -->
                <input type="hidden" name="roleid">
                <input type="text" name="rolename"  placeholder="请输入角色名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色备注:</label>
            <div class="layui-input-block">
                <input type="text" name="roledesc" placeholder="请输入角色备注" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否可用:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="available" value="1" checked="checked" title="可用">
                    <input type="radio" name="available" value="0" title="不可用">
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->

<!-- 角色分配菜单的弹出层开始,用于展示菜单项 -->
<div style="display: none;" id="selectRoleMenu">
    <ul id="menuTree" class="dtree" data-id="0"></ul>
</div>
<!-- 角色分配菜单的弹出层结束 -->

<script src="${whContextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        dtree:'${whContextPath}/resources/layui_ext/dist/dtree'
    }).use([ 'jquery', 'layer', 'form', 'table','dtree'  ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree=layui.dtree;
        //渲染数据表格
        tableIns=table.render({
            elem: '#roleTable'   //渲染的目标对象
            ,url:'${whContextPath}/role/loadAllRole' //数据接口
            ,title: '用户数据表'//数据导出来的标题
            ,toolbar:"#roleToolBar"   //表格的工具条
            ,height:'500'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'roleid', title:'ID',align:'center'}
                ,{field:'rolename', title:'角色名称',align:'center'}
                ,{field:'roledesc', title:'角色备注',align:'center'}
                ,{field:'available', title:'是否可用',align:'center',templet:function(d){
                        return d.available=='1'?'<font color=blue>可用</font>':'<font color=red>不可用</font>';
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#roleBar', width:220,align:'center'}
            ]]
        });
        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            tableIns.reload({
                url:"${whContextPath}/role/loadAllRole?"+params,
                //用于在最后一页直接全查询,返回第一页,而不是继续在第一页
                page:{curr:1}
            })
        });
        //监听头部工具栏事件
        table.on("toolbar(roleTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddRole();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
        });
        //监听行工具事件
        table.on('tool(roleTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.rolename+'】这个角色吗', function(index){
                    //向服务端发送删除指令
                    $.post(" ${whContextPath}/role/deleteRole",{roleid:data.roleid},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){//编辑
                openUpdateRole(data);
            }else if(layEvent==='selectRoleMenu'){//分配菜单
                openselectRoleMenu(data);
            }
        });

        var url;
        var mainIndex;
        //打开添加页面
        function openAddRole(){
            mainIndex=layer.open({
                type:1,
                title:'添加角色',
                content:$("#saveOrUpdateDiv"),
                area:['800px','300px'],
                success:function(index){
                    //清空表单数据       
                    $("#dataFrm")[0].reset();
                    url="${whContextPath}/role/addRole";
                }
            });
        }
        //打开修改页面
        function openUpdateRole(data){
            mainIndex=layer.open({
                type:1,
                title:'修改用户',
                content:$("#saveOrUpdateDiv"),
                area:['800px','300px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${whContextPath}/role/updateRole";
                }
            });
        }
        //保存
        form.on("submit(doSubmit)",function(obj){
            //序列化表单数据
            var params=$("#dataFrm").serialize();
            $.post(url,params,function(obj){
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //批量删除
        function deleteBatch(){
            //得到选中的数据行
            var checkStatus = table.checkStatus('roleTable');
            var data = checkStatus.data;
            layer.alert(data.length);
            var params="";
            $.each(data,function(i,item){
                if(i==0){
                    params+="ids="+item.roleid;
                }else{
                    params+="&ids="+item.roleid;
                }
            });
            layer.confirm('真的删除选中的这些角色吗', function(index){
                //向服务端发送删除指令
                $.post("${whContextPath}/role/deleteBatchRole",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }

        //todo 打开分配菜单的弹出层
        function openselectRoleMenu(data){
            //提升树tree的作用域
            var menuTree;
            mainIndex=layer.open({
                type:1,
                title:'分配【'+data.rolename+'】的角色',
                content:$("#selectRoleMenu"),
                area:['400px','500px'],
                //设置按钮
                btnAlign:'c',
                btn:['<div class="layui-icon layui-icon-release">确认分配</div>','<div class="layui-icon layui-icon-close">取消分配</div>'],
                yes:function(index, layero){
                    var nodes = dtree.getCheckbarNodesParam("menuTree");
                    console.log(nodes);
                    var roleid=data.roleid;
                    var params="roleid="+roleid;

                    $.each(nodes,function(i,item){
                        params+="&ids="+item.nodeId;
                    });
                    // alert(params);
                    //保存角色和菜单的关系
                    $.post("${whContextPath}/role/saveRoleMenu",params,function(obj){
                        layer.msg(obj.msg);
                    })
                },
                //点击 "分配菜单" 后初始化树
                success:function(index){
                    layer.alert(data.roleid)
                    //初始化树
                    menuTree = dtree.render({
                        elem: "#menuTree",
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
                        dataFormat: "list",  //配置data的风格为list
                        checkbar: true,
                        checkbarType: "all", // 默认就是all，其他的值为： no-all  p-casc   self  only\
                        checkbarData: "choose" ,
                        url: "${whContextPath}/role/initRoleMenuTreeJson?roleid="+data.roleid // 使用url加载(可与data加载同时存在)
                    });
                }
            });
        }
    });
</script>
</body>
</html>
