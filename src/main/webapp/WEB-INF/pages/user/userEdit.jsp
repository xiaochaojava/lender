<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="userEditForm" class="itemForm" method="post">
	<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>用户名:</td>
	            <td><input  type="text" name="username" readonly="true" style="width: 280px;"></input></td>
	        </tr>

	        <tr>
	            <td>真实名字:</td>
	            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>手机号:</td>
	            <td><input class="easyui-validatebox" type="text" name="phone" data-options="required:true" validtype="mobile"/></td>
	        </tr>
	        <tr>
	            <td>邮箱:</td>
	            <td>
	                <input class="easyui-validatebox" type="text" name="email" data-options="required:true" validType="email" missingMessage="邮箱必须填写" invalidMessage="请填写正确的邮箱格式" />
	            </td>
	        </tr>
	        <tr>
	            <td>用户头像:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                 <input type="hidden" name="picture"/>
	            </td>
	        </tr>
	        <tr>
	            <td>用户描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="description"></textarea>
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
	var userEditEditor ;
	$(function(){
		//和form下的desc组件绑定
		userEditEditor = KindEditorUtil.createEditor("#userEditForm [name=description]");
		//KindEditorUtil.init({fun:function(node){
			//KindEditorUtil.changeItemParam(node, "userEditForm");
		//}});
	});
	//验证手机号码的合法性
	$.extend($.fn.validatebox.defaults.rules, {
		 //用户账号验证(只能包括 _ 数字 字母)
	    account: {//param的值为[]中值
	        validator: function (value, param) {
	            if (value.length<param[0] || value.length>param[1]) {
	                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
	                return false;
	            } else {
	                if (!/^[\w]+$/.test(value)) {
	                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
	                    return false;
	                } else {
	                    return true;
	                }
	            }
	        }, message: ''
	    },
	    password:{
			   validator: function (value, param) {
		            if (value.length<param[0] || value.length>param[1]) {
		                $.fn.validatebox.defaults.rules.account.message = '密码长度必须在' + param[0] + '至' + param[1] + '范围';
		                return false;
		            } else {
		                return true;
		            }
		        }, message: ''
		 },
	    //移动手机号码验证 
	    mobile: {//value值为文本框中的值 
	        validator: function (value) {
	            var reg = /^1[3|4|5|8|9]\d{9}$/; 
	            return reg.test(value); 
	        }, 
	        message: '输入手机号码格式不准确.' 
	    } 
	})
	function submitForm(){
		//表单校验
		if(!$('#userEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		userAddEditor.sync();//将输入的内容同步到多行文本中
		var paramJson = [];
		$("#userEditForm .params li").each(function(i,e){
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
		
		$("#userEditForm [name=userParams]").val(paramJson);
		
		$.post("${pageContext.request.contextPath}/user/editUser.html",$("#userEditForm").serialize(), function(data){
			if(data.status == 200){
				KindEditorUtil.closeCurrentWindow();
				$.messager.alert('提示','修改用户成功!');
				$("#uList").datagrid('reload');
			}
		});
	}
	
	function clearForm(){
		$('#userEditForm').form('reset');
		itemAddEditor.html('');
	}
</script>
