<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="roleList" title="角色列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/role/list.html',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">角色ID</th>
            <th data-options="field:'roleName',width:200">角色名称</th>
            <th data-options="field:'remark',width:100">备注</th>
            <th data-options="field:'state',width:60,align:'center',formatter:KindEditorUtil.formatItemStatus">状态</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">创建日期</th>
            <th data-options="field:'modifyTime',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="roleEditWindow" class="easyui-window" title="编辑角色" data-options="modal:true,closed:true,iconCls:'icon-save',href:'${pageContext.request.contextPath}/role/roleEdit.html'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="roleRightWindow" class="easyui-window" title="角色分配权限" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:80%;height:80%;padding:10px;">
</div>
<script>
	
    function getSelectionsIds(){
    	var roleList = $("#roleList");
    	var sels = roleList.datagrid("getSelections");
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
        	$(".tree-title:contains('新增角色')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个角色才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个角色!');
        		return ;
        	}
        	
        	$("#roleEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			//返回选中的第一条记录
        			var data = $("#roleList").datagrid("getSelections")[0];
        			//data.priceView = KindEditorUtil.formatPrice(data.price);
        			//form把这条选中的页面的值一个一个赋值到页面的html框中
        			$("#roleEditForm").form("load",data);
        			roleEditEditor.html(data.remark);
        		}
        	}).window("open");
        }
    },{
        text:'禁用/启用',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();			//获取用户选中多个id
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中角色!');
        		return ;
        	}
        	$.messager.confirm('确认','确定禁用/启用ID为 '+ids+' 的角色吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("${pageContext.request.contextPath}/role/deleteRole.html",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','启用/禁用该角色成功!',undefined,function(){
            					$("#roleList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'权限',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个角色才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个角色!');
        		return ;
        	}
    		
    		var $win;
    		$win = $('#roleRightWindow').window({
    		    href: '${pageContext.request.contextPath}/role/roleRight.html?roleId='+$("#roleList").datagrid("getSelections")[0].id,
    		    width: 500,
    		    height: 600,
    		    //top: ($(window).height() - 820) * 0.5,
    		    //left: ($(window).width() - 450) * 0.5,
    		    shadow: true,
    		    modal: true,
    		    iconCls: 'icon-add',
    		    closed: true,
    		    minimizable: false,
    		    maximizable: false,
    		    collapsible: false
    		});
    		$win.window('open');
        }
    }];
</script>