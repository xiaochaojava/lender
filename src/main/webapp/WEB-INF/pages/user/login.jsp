<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<jsp:include page="/pub/commons/common-js.jsp"></jsp:include>
</head>
<body style="background-color: #F3F3F3">
    <form id="form" action="doLogin.html"  method="post">
    <div class="easyui-dialog" title="管理员登录" data-options="closable:false,draggable:false" style="width:400px;height:300px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            	用户名: <input id="loginname"  type="text" name="username" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px"/>
	            </div>
	        </div>
	          <script type="text/javascript">
                    setTimeout(function () {
                        if (!$("#loginname").val()) {
                            $("#loginname").get(0).focus();
                        }
                    }, 0);
                </script>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;码: <input id="nloginpwd" type="password" name="password" class="easyui-textbox" style="width:200px;height:32px" data-options=""/>
	            </div>
	            <input type="hidden" name="loginpwd" id="loginpwd" value="" class="hide" />
	        </div>
	        <div>
	            <input id="login" type="button" value="登录" class="easyui-linkbutton"  iconCls="icon-ok" style="width:100px;height:32px;margin-left: 50px"></input>
	        </div>
	         <label id="loginpwd_succeed" class="blank invisible"></label>
             <label id="loginpwd_error" class="hide"></label>
       	</div>
    </div>
    </form>
    <script type="text/javascript">
    	$("#login").click(function(){
    		var username = $("[name=username]").val();
    		var password = $("[name=password]").val();
    		//alert(username+","+password);
    		//document.getElementById("form").action="${pageContext.request.contextPath}/user/doLogin.html?username="+username+"&password="+password;
    		//document.getElementById("form").submit();
    		  if ($('#form').form('validate')){
    			   $.post('${pageContext.request.contextPath}/user/doLogin.html',{username:username,password:password},function(result){
    				   if (result) {
    	                    var obj = eval(result);
    	                    if (obj.status == 200) {
    	                 
    	                    	obj.success = "${pageContext.request.contextPath}/user/lenderIndex.html";
    	                        var isIE = !-[1,];
    	                        if (isIE) {
    	                            var link = document.createElement("a");
    	                            link.href = obj.success;				//直接跳转到前台首页
    	                            link.style.display = 'none';
    	                            document.body.appendChild(link);
    	                            link.click();
    	                        } else {
    	                            window.location = obj.success;
    	                        }
    	                        return;
    	                    }else{ 
    	                    $("#login").removeAttr("disabled");
    	                    //verc();  //验证码刷新
    	                      $("#nloginpwd").attr({ "class": "text highlight2" });
    	                      $("#loginpwd_error").html("<font color='red' size='3px'>账号或密码错误!</font>").show().attr({ "class": "error" });	
    	                    }
    	                }
    			   },'json');
    			   
    			  }
    	});
    
   	 $(function() {
   		 
   		  //按回车就可以提交
   		  $().bind('keyup', function(event) {
   		   if (event.keyCode == '13') {//13为回车(当输入完某个框后有键盘抬起事件触发就调用submit方法)
   		   	alert("asdf");
   			   $('#form').submit();//必须将原生的form转换为easyui的form才可以使用,否则他会去找一个原生form的action属性
   		   }
   		  });
   		 });
    </script>
</body>
</html>