<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/pub/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/pub/js/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pub/js/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script type="text/javascript">
		var zTreeObj;
		var setting = {
			check : {
				enable : true
			},
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		$(document).ready(function() {
			initZtree();	//直接初始化化ztree树
		});
		function initZtree() {
			var zNodes = eval('${roleRightJsonStr}');
			zTreeObj = $.fn.zTree.init($('#jkTree'), setting, zNodes);	//jkTree 树的id，支持多个树
			zTreeObj.expandAll(true);		//展开所有树节点
		}	
		//获取所有选择的节点
		function submitCheckedNodes(treeNode) {
			var nodes = new Array();
			nodes = zTreeObj.getCheckedNodes(true);		//取得选中的结点
			var str = "";
			for (i = 0; i < nodes.length; i++) {
				if (str != "") {
					str += ",";
				}
				str += nodes[i].id;
			}
			$('#rightIds').val(str);		//页面的隐藏域中
		}
	</script>	
<div style="padding:10px 10px 10px 10px">
<form name="icform" method="post" id="roleRightForm">
	<input type="hidden" name="roleId" value="${roleId}"/>
	<input type="hidden" id="rightIds" name="rightIds"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${pageContext.request.contextPath }/pub/skin/default/images/icon/folder_edit.png"/>
   角色的模块设置
  </div>  
    <div>
		<ul id="jkTree" class="ztree"></ul>
	</div>
</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitCheckedNodes();submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	function submitForm(){
		$.post("${pageContext.request.contextPath}/role/roleRightJsonStr.html",$("#roleRightForm").serialize(), function(data){
			if(data.status == 200){
				KindEditorUtil.closeCurrentWindow();
				$.messager.alert('提示','分配权限成功!');
				$("#roleList").datagrid('reload');
			}
		});
	}
</script>
