<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lender后台管理系统</title>
<jsp:include page="/pub/commons/common-js.jsp"></jsp:include>	
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'',split:true" style="width:180px;">
	<jsp:include page="/pub/commons/header.jsp"></jsp:include>
	</div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">		
         	<li>
         		<span>用户管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'${pageContext.request.contextPath }/user/userCreate.html'}">新增用户</li>
	         		<li data-options="attributes:{'url':'${pageContext.request.contextPath }/user/userList.html'}">查询用户</li>
	         	</ul>
         	</li>
         	
         	<li>
         		<span>权限管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'${pageContext.request.contextPath }/right/rightList.html'}">菜单管理</li>
	         		
	         	</ul>
         	</li>
         	<li>
         		<span>角色管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'${pageContext.request.contextPath}/role/roleAdd.html'}">新增角色</li>
	         		<li data-options="attributes:{'url':'${pageContext.request.contextPath }/role/roleList.html'}">查询角色</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		</div>
    </div>
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>