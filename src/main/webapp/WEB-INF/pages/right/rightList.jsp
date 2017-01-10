<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/pub/js/zTree/css/zTreeStyle/demo.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/pub/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/zTree/js/jquery.ztree.exedit-3.5.js"></script>
	
	<script type="text/javascript">
	var setting = {
		async : {
			enable : true,//开启异步加载处理
			url : encodeURI(encodeURI("${pageContext.request.contextPath }/right/list.html")),
			autoParam : [ "id" ],
			dataFilter : filter,
			contentType : "application/json",
			type : "get"
		},
		view : {
			expandSpeed : "",
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : false
		},
		edit : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeRemove : beforeRemove,
			beforeRename : beforeRename,
		}
	};
	function filter(treeId, parentNode, childNodes) {
		var nodes =  JSON.parse(childNodes.data);
		if (!nodes)
			return null;
		for (var i = 0, l = nodes.length; i < l; i++) {
			nodes[i].name = nodes[i].name.replace(/\.n/g, '.');
		}
		return nodes;
	}
	function beforeRemove(treeId, treeNode) {
		if (confirm("确认删除节点--" + treeNode.name + "--吗?")) {
			var param = "rightId=" + treeNode.id;
			$.post(encodeURI(encodeURI("${pageContext.request.contextPath }/right/deleteRight.html?"
					+ param)));
		} else {
			return false;
		}
	}
	function beforeRename(treeId, treeNode, newName) {
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			return false;
		}
		var param = "rightId=" + treeNode.id + "&name=" + newName;
		alert(param);
		$.post(encodeURI(encodeURI("${pageContext.request.contextPath }/right/editRight.html?"
				+ decodeURI(param))));
		return true;
	}

	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
			return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_" + treeNode.tId);
		if (btn)
			btn.bind("click", function() {
				var Ppname = prompt("请输入新节点名称");
				if (Ppname == null) {
					return;
				} else if (Ppname == "") {
					alert("节点名称不能为空");
				} else {
					var param ="&pId="+ treeNode.id + "&name=" + Ppname;
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					$.post(
							encodeURI(encodeURI("${pageContext.request.contextPath }/right/save.html?"
									+ param)), function(data) {
								if ($.trim(data) != null) {
									var treenode = $.trim(data);
									zTree.addNodes(treeNode, {
										pId : treeNode.id,
										name : Ppname
									}, true);
								}
							})
				}

			});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
	};
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);

	});
</script>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>


