<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="userEditPasswordForm" class="itemForm" method="post">
	<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>用户名:</td>
	            <td><input  type="text" name="username" readonly="true	" style="width: 280px;"></input></td>
	        </tr>

	        <tr>
	            <td>密码:</td>
	            <td><input class="easyui-validatebox" type="text" name="password" data-options="required:true" validType="password[8,20]" style="width: 280px;"></input></td>
	        </tr>
	       
	    </table>
	   
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

	//验证手机号码的合法性
	$.extend($.fn.validatebox.defaults.rules, {
	    password:{
			   validator: function (value, param) {
		            if (value.length<param[0] || value.length>param[1]) {
		                $.fn.validatebox.defaults.rules.password.message = '密码长度必须在' + param[0] + '至' + param[1] + '范围';
		                return false;
		            } else {
		                return true;
		            }
		        }, message: ''
		 }
	})
	function submitForm(){
		//表单校验
		if(!$('#userEditPasswordForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		var paramJson = [];
		$("#userEditPasswordForm .params li").each(function(i,e){
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
		
		$.post("${pageContext.request.contextPath}/user/editUser.html",$("#userEditPasswordForm").serialize(), function(data){
			if(data.status == 200){
				KindEditorUtil.closeCurrentWindow();
				$.messager.alert('提示','修改密码成功!');
				$("#uList").datagrid('reload');
			}
		});
	}
	
	function clearForm(){
		$('#userEditPasswordForm').form('reset');
		itemAddEditor.html('');
	}
</script>
