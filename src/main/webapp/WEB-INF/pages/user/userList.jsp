<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="uList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath }/user/list.html',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">用户ID</th>
            <th data-options="field:'username',width:200">用户名</th>
            <th data-options="field:'password',width:100">密码</th>
            <th data-options="field:'phone',width:100">电话</th>
            <th data-options="field:'name',width:100">真实名字</th>
            <th data-options="field:'email',width:150,align:'right'">邮箱</th>
            <th data-options="field:'state',width:60,align:'center',formatter:KindEditorUtil.formatItemStatus">状态</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">创建日期</th>
            <th data-options="field:'modifyTime',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="userEditWindow" class="easyui-window" title="编辑用户" data-options="modal:true,closed:true,iconCls:'icon-save',href:'${pageContext.request.contextPath}/user/userEdit.html'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="userEditPasswordWindow" class="easyui-window" title="修改密码" data-options="modal:true,closed:true,iconCls:'icon-save',href:'${pageContext.request.contextPath}/user/userEditPassword.html'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var uList = $("#uList");
    	var sels = uList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;		//x,y
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增用户')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个用户才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个用户!');
        		return ;
        	}
        	
        	$("#userEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			//返回选中的第一条记录
        			var data = $("#uList").datagrid("getSelections")[0];
        			//data.priceView = KindEditorUtil.formatPrice(data.price);
        			//form把这条选中的页面的值一个一个赋值到页面的html框中
        			$("#userEditForm").form("load",data);
        			userEditEditor.html(data.description);
        			//加载商品规格

        			KindEditorUtil.init({
        				"pics" : data.picture,
        			});
        		}
        	}).window("open");
        }
    },{
        text:'启用/禁用',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();			//获取用户选中多个id
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的用户吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("${pageContext.request.contextPath}/user/deleteUser.html",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除用户成功!',undefined,function(){
            					$("#uList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'修改密码',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个用户才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个用户!');
        		return ;
        	}
        	
        	$("#userEditPasswordWindow").window({
        		onLoad :function(){
        			//回显数据
        			//返回选中的第一条记录
        			var data = $("#uList").datagrid("getSelections")[0];
        			data.password="";
        			$("#userEditPasswordForm").form("load",data);
        		}
        	}).window("open");
        }
    }];
</script>