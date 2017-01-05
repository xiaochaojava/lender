<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出借人登录页面1</title>
</head>
<body>
	<table type="center">
		<form method="post" action="doLogin.html">
			<tr>
				<td colspan="2" align="center">出借人登录页面</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td colspan="2"><input id="login" type="button" value="登录" onclick="submit()"></td>
			</tr>
		</form>
	</table>
	<script type="text/javascript">
    	function submit(){
    		var username = $("[name=username]").val();
    		var password = $("[name=password]").val();
    		//alert(username+","+password);
    		//document.getElementById("form").action="${pageContext.request.contextPath}/sysadmin/user/doLogin.html?username="+username+"&password="+password;
    		document.getElementById("form").submit();
    	};
    </script>
</body>
</html>