<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/pub/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="userAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>用户名:</td>
	            <td><input id="username" class="easyui-validatebox" type="text" name="username" data-options="required:true" missingMessage="用户名必须填写"  validtype="accountName['${pageContext.request.contextPath }/user/checkName.html','account']" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>密码:</td>
	            <td><input id="password" class="easyui-validatebox" type="password" name="password" data-options="required:true" missingMessage="密码必须填写" invalidMessage="6-20位 " validtype="password[6,20]" />

	            </td>
	        </tr>
	        <tr>
	            <td>确认密码:</td>
	            <td><input class="easyui-validatebox" type="password"  data-options="required:true"  missingMessage="确认密码必须填写" invalidMessage="两次输入密码不匹配" validtype="equalTo['#password']" />

	            </td>
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
	            <td>角色:</td>
	            <td>
	                <input id="roleId" class="easyui-combobox" name="roleId" data-options="valueField: 'id',  textField: 'name', url: '${pageContext.request.contextPath}/role/findRoles.html'"  />
	            </td>
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
	var userAddEditor ;
	$(function(){
		//和form下的desc组件绑定
		userAddEditor = KindEditorUtil.createEditor("#userAddForm [name=description]");
		KindEditorUtil.init({fun:function(node){
			KindEditorUtil.changeItemParam(node, "userAddForm");
		}});
	});
	
	//验证手机号码的合法性
	$.extend($.fn.validatebox.defaults.rules, {
		
	    accountName: {  
            validator: function (value, param) {  
                if (!/^[a-zA-Z][a-zA-Z0-9_]{3,15}$/i.test(value)) {  
                    $.fn.validatebox.defaults.rules.accountName.message = '账户名称不合法（字母开头，允许4-16字节，允许字母数字下划线）';  
                    return false;  
                } else {  
                    var postdata = {};  
                    postdata={"username":$("#username").val().trim()};
                    var result = $.ajax({  
                        url: param[0],  
                        data: postdata,  
                        type: 'post',  
                        dataType: 'json',  
                        async: false,  
                        cache: false
                    }).responseText;  
                    var json=jQuery.parseJSON(result);
                    if (json.status == 100) {  
                        $.fn.validatebox.defaults.rules.accountName.message = '账户名称已存在！';  
                        return false;  
                    } else {  
                        return true;  
                    }  
                }  
            },  
            message: ''  
        },  
	    password:{
			   validator: function (value, param) {
		            if (value.length<param[0] || value.length>param[1]) {
		                $.fn.validatebox.defaults.rules.password.message = '密码长度必须在' + param[0] + '至' + param[1] + '范围';
		                return false;
		            } else {
		                return true;
		            }
		        }, message: ''
		 },
		/*必须和某个字段相等*/
        equalTo: { 
        	validator: function (value, param) {
        		return $(param[0]).val() == value; 
        		}, message: '字段不匹配'
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
		if(!$('#userAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		userAddEditor.sync();//将输入的内容同步到多行文本中
		var paramJson = [];
		$("#userAddForm .params li").each(function(i,e){
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
		
		$("#userAddForm [name=userParams]").val(paramJson);
		
		$.post("${pageContext.request.contextPath}/user/save.html",$("#userAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增用户成功!');
			}
		});
	}
	
	function clearForm(){
		$('#userAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
