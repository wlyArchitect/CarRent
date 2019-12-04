<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
	<title>菜单管理</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/dtree.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/font/dtreefont.css">

</head>
<body>
	 <ul id="menuTree" class="dtree" data-id="0"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/layui/layui.js"></script>
	<script type="text/javascript">
	var menuTree;
	layui.extend({
		dtree:'${pageContext.request.contextPath}/resources/layui_ext/dist/dtree'
	}).use([ 'jquery', 'layer', 'form','dtree' ], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;
		var dtree=layui.dtree;

	    //初始化树
	    menuTree = dtree.render({
	      elem: "#menuTree",
		  dataStyle: "layuiStyle",  //使用layui风格的数据格式
	      response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
          dataFormat: "list",  //配置data的风格为list
	      //d-tree会把节点的数据都提交,spread=true/false,我们需要手动修改u为1
          url: "${pageContext.request.contextPath}/menu/loadMenuManagerLeftTreeJson?spread=1" // 使用url加载（可与data加载同时存在）
	    });

		 //监听树的节点 单击事件
	    dtree.on("node('menuTree')" ,function(obj){
	        // console.log(obj.param)
	        //只需要拿到树节点的id值,名字变成nodeId,不是原来的json数据中的id名
	    	 var id=obj.param.nodeId;
	    	 //window.parent拿到父窗口 .right拿到的是子右窗口
	    	 window.parent.right.reloadTable(id);
	    });
	});
	</script>
</body>
</html>