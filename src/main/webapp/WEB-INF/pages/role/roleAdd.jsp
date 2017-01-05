<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="roleAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>角色名称:</td>
	            <td><input class="easyui-validatebox" type="text" name="roleName" data-options="required:true" missingMessage="角色名称必须填写"  style="width: 280px;"></input></td>
	        </tr>
     
	        <tr>
	            <td>备注:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="remark"></textarea>
	            </td>
	        </tr>
	    </table>
	   
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var roleAddEditor ;
	$(function(){
		//和form下的desc组件绑定
		roleAddEditor = KindEditorUtil.createEditor("#roleAddForm [name=remark]");
		KindEditorUtil.init({fun:function(node){
			KindEditorUtil.changeItemParam(node, "roleAddForm");
		}});
	});
	
	function submitForm(){
		//表单校验
		if(!$('#roleAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		roleAddEditor.sync();//将输入的内容同步到多行文本中
		var paramJson = [];
		$("#roleAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		paramJson = JSON.stringify(paramJson);//将对象转化为json字符串
		
		$("#roleAddForm [name=RoleParams]").val(paramJson);
		
		$.post("${pageContext.request.contextPath}/role/save.html",$("#roleAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增角色成功!');
			}
		});
	}
	
	function clearForm(){
		$('#roleAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
