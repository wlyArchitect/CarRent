<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
	<meta charset="utf-8">
	<title>菜单管理</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/dtree.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/font/dtreefont.css">

	<style type="text/css">
		.select-test{position: absolute;max-height: 500px;height: 350px;overflow: auto;width: 100%;z-index: 123;display: none;border:1px solid silver;top: 42px;}
		.layui-show{display: block!important;}
	</style>
</head>
<body class="childrenBody">
	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<!--
	这个form表单只提交了一个数据:菜单名称
	-->
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">菜单名称:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="title"  autocomplete="off" class="layui-input">
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
	<table class="layui-hide" id="menuTable" lay-filter="menuTable"></table>
	<div style="display: none;" id="menuToolBar">
		    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
	</div>
	<div  id="menuBar" style="display: none;">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
	<!-- 数据表格结束 -->

	<!-- todo 2.添加和修改的弹出层开始 -->
	<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-form-item">
				  <label class="layui-form-label">父级菜单：</label>
				   <div class="layui-input-block">
				      <div class="layui-unselect layui-form-select" id="pid_div">
				        <div class="layui-select-title">
	                      <!-- 隐藏选择的id -->
				          <input type="hidden" name="pid"  id="pid">
				          <input type="text" name="pid_str" id="pid_str" placeholder="请选择" readonly="" class="layui-input layui-unselect">
				          <i class="layui-edge"></i>
				        </div>
				      </div>
				      <div class="layui-card select-test" id="menuSelectDiv">
				        <div class="layui-card-body">
				          <div id="toolbarDiv"><ul id="menuTree" class="dtree" data-id="0" style="width: 100%;"></ul></div>
				        </div>
				      </div>
				  </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单名称:</label>
				<div class="layui-input-block">
	                <!-- 隐藏它的id -->
					<input type="hidden" name="id">
					<input type="text" name="title"  placeholder="请输入菜单名称" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单地址:</label>
				<div class="layui-input-block">
					<input type="text" name="href" placeholder="请输入菜单地址" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">菜单图标:</label>
					<div class="layui-input-inline">
						<input type="text" name="icon"   placeholder="请输入菜单图标" lay-verify="required" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">TARGET:</label>
					<div class="layui-input-inline">
						<input type="text" name="target"  placeholder="请输入TRAGET"  autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否展开:</label>
					<div class="layui-input-inline">
						 <input type="radio" name="spread" value="1" title="展开">
						 <input type="radio" name="spread" value="0" title="不展开"  checked="checked">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">是否可用:</label>
					<div class="layui-input-inline">
						 <input type="radio" name="available" value="1" checked="checked" title="可用">
						 <input type="radio" name="available" value="0" title="不可">
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
	<script src="${pageContext.request.contextPath }/resources/layui/layui.js"></script>
	<script type="text/javascript">
	    //供全局使用这个 渲染数据表格 参数
	    var tableIns;
	    layui.extend({
			dtree:'${pageContext.request.contextPath}/resources/layui_ext/dist/dtree'
		}).use([ 'jquery', 'layer', 'form', 'table','dtree'  ], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var dtree=layui.dtree;
			//渲染数据表格
			 tableIns=table.render({
				 elem: '#menuTable'   //渲染的目标对象
			    ,url:'${pageContext.request.contextPath}/menu/loadAllMenu' //数据接口
			    ,title: '用户数据表'//数据导出来的标题
			    ,toolbar:"#menuToolBar"   //表格的工具条
			    ,height:'full-150'
			    ,cellMinWidth:100 //设置列的最小默认宽度
			    ,page: true  //是否启用分页
			    ,cols: [[   //列表数据
			     {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID',align:'center',width:'80'}
			      ,{field:'pid', title:'父节点ID',align:'center',width:'100'}
			      ,{field:'title', title:'菜单名称',align:'center',width:'120'}
			      ,{field:'href', title:'菜单地址',align:'center',width:'220'}
			      ,{field:'spread', title:'是否展开',align:'center',width:'100',templet:function(d){
			    	  return d.spread=='1'?'<font color=blue>展开</font>':'<font color=red>不展开</font>';
			      }}
			      ,{field:'target', title:'TARGET',align:'center',width:'100'}
			      ,{field:'icon', title:'菜单图标',align:'center',width:'100',templet:function(d){
			          // 图标需要美化,因为获取出来的是字符串
			    	  return "<div class='layui-icon'>"+d.icon+"</div>";
			      }}
			      ,{field:'available', title:'是否可用',align:'center',width:'100',templet:function(d){
			    	  return d.available=='1'?'<font color=blue>可用</font>':'<font color=red>不可用</font>';
			      }}
			      ,{fixed: 'right', title:'操作', toolbar: '#menuBar', width:180,align:'center'}
			    ]]
			})

			//todo 1.模糊查询
			$("#doSearch").click(function(){
			    //form表单 序列化 获取参数
				var params=$("#searchFrm").serialize();
				alert(params);
				//刷新,传入参数
				tableIns.reload({
					url:"${pageContext.request.contextPath}/menu/loadAllMenu?"+params,
                    //用于在最后一页直接全查询,返回第一页,而不是继续在第一页
	                page:{curr:1}
				})
			});

			//监听头部工具栏事件
			table.on("toolbar(menuTable)",function(obj){
				 switch(obj.event){
				    case 'add':
				      openAddMenu();
				    break;
				  };
			})
			//监听行工具事件
		   table.on('tool(menuTable)', function(obj){
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			   if(layEvent === 'del'){ //删除
				  //先判断当前菜单有没有子节点
				  $.post("${pageContext.request.contextPath}/menu/checkMenuHasChildren?id="+data.id,function(obj){
				      alert("子节点个数"+obj.code);
					  if(obj.code>=0){
						  layer.msg("当前菜单有子节点,请先删子节点");
					  }else{
						  layer.confirm('真的删除【'+data.title+'】这个菜单吗', function(index){
						       //向服务端发送删除指令
						       $.post("${whContext}/menu/deleteMenu",{id:data.id},function(res){
						    	    layer.msg(res.msg);
						    	    //刷新数据 表格
									tableIns.reload();
									//刷新左边的树
									window.parent.left.menuTree.reload();
									//刷新添加和修改的下拉树
									menuTree.reload();
						       })
	                      });
					  }
				  })
			   } else if(layEvent === 'edit'){
			      //编辑
			     openUpdateMenu(data);
			   }
			 });
			//todo 4.菜单项的增添
			var url;
			var mainIndex;
			//打开添加页面
			function openAddMenu(){
				mainIndex=layer.open({
					type:1,
					title:'添加菜单',
					content:$("#saveOrUpdateDiv"),
					area:['800px','450px'],
					success:function(index){
						//清空表单数据
						$("#dataFrm")[0].reset();
						//防止下次选择时还停留上次的选择界面:去除选择的
						$("#menuSelectDiv").removeClass("layui-show");
						url="${pageContext.request.contextPath}/menu/addMenu";
					}
				});
			}
			//打开修改页面
			function openUpdateMenu(data){
				mainIndex=layer.open({
					type:1,
					title:'修改用户',
					content:$("#saveOrUpdateDiv"),
					area:['800px','450px'],
	               //初始化数据展示
					success:function(index){
						form.val("dataFrm",data);
						url="${pageContext.request.contextPath}/menu/updateMenu";
						//反选下拉树,显示之前选择的数据
						var pid=data.pid;
					    var params = dtree.dataInit("menuTree", pid);
					    //移除打开的样式
					    $("#menuSelectDiv").removeClass("layui-show");
					    //给下拉框的text框赋值
					    $("#pid_str").val(params.context);
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
					//添加成功后刷新左边的树和右边下拉选择时的树
					//刷新左边的树
					window.parent.left.menuTree.reload();
					//刷新添加和修改的下拉树
					menuTree.reload();
				})
			});
			//初始化添加和修改页面的下拉树
			var menuTree = dtree.render({
				  elem: "#menuTree",
				  dataStyle: "layuiStyle",  //使用layui风格的数据格式
			      response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
		          dataFormat: "list",  //配置data的风格为list
	//树提交的spread是true false, 把当前节点的数据都提交出去,强制把spread=1
	url: "${pageContext.request.contextPath}/menu/loadMenuManagerLeftTreeJson?spread=1",  // 使用url加载（可与data加载同时存在）
				  icon: "2",
				  accordion:true
				});
				$("#pid_div").on("click",function(){
				  $(this).toggleClass("layui-form-selected");
				  $("#menuSelectDiv").toggleClass("layui-show layui-anim layui-anim-upbit");
				});
				//树被点击时
				dtree.on("node(menuTree)", function(obj){
				  $("#pid_str").val(obj.param.context);
				  //nodeId取出父节点的id值,通过一个隐藏域存储节点id
				  $("#pid").val(obj.param.nodeId);
				  $("#pid_div").toggleClass("layui-form-selected");
				  $("#menuSelectDiv").toggleClass("layui-show layui-anim layui-anim-upbit");
				});
		});
		//todo 3菜单项(menuManager.jsp界面)传入id,显示不同的表格
		function reloadTable(id){
			tableIns.reload({
				url:"${pageContext.request.contextPath}/menu/loadAllMenu?id="+id
			})
		}
	</script>
</body>
</html>