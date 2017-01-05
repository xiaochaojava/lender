package com.hzwealth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzwealth.common.vo.EasyUIResult;
import com.hzwealth.common.vo.SysResult;
import com.hzwealth.pojo.Role;
import com.hzwealth.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	/**
	 * 查询所有的角色，组织成json数据发送给前台，主要用于下拉框的实现（用户新增的下拉框）
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findRoles")
	public void findRoles(HttpServletRequest request,HttpServletResponse response){
		List<Role> list = roleService.findRoles();
		StringBuilder str = new StringBuilder();
		//json串[{"id":1,"name":"name1"},{"id":2,"name":"name2"}]
		str.append("[");
		for(Role role:list){
			str.append("{");
			str.append("\"id\":").append(role.getId()).append(",");
			str.append("\"name\":").append("\"").append(role.getRoleName()).append("\"");
			str.append("},");
		}
		if(str.length()>1){
			str.delete(str.length()-1, str.length());
		}
		str.append("]");
		try {
			response.setCharacterEncoding("utf-8");//指定为utf-8  
			response.getWriter().write(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("返回信息出错！");
		}
		
	}
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public SysResult save(Role role){
		roleService.addRole(role);
		return SysResult.ok();
	}
	/**
	 * 角色列表
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult list(Integer page, Integer rows){
		PageHelper.startPage(page, rows,true);
		List<Role> roleList = roleService.findRoles();
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
	}
	/**
	 * 编辑角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/editRole")
	@ResponseBody
	public SysResult editRole(Role role){
		roleService.editRole(role);
		return SysResult.ok();
	}
	/**
	 * 删除角色(改变角色的状态)
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public SysResult deleteRole(Long[] ids){
		roleService.deleteRole(ids);
		return SysResult.ok();
	}
	/**
	 * 保存权限
	 * @param roleId
	 * @param rightIds
	 * @return
	 */
	@RequestMapping("/saveRight")
	@ResponseBody
	public SysResult saveRight(Long roleId,String rightIds){
		roleService.saveRights(roleId, rightIds);
		return SysResult.ok();
	}
	/**
	 * 转向分配权限的页面
	 * @param roleId
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/roleRight")
	public String roleRight(String roleId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long id = null;
		if(roleId!=null){
			id = Long.valueOf(roleId);
		}	
		String roleRightJsonStr = roleService.getRightJsonStr(id);		
//		return SysResult.ok(roleRightJsonStr);
		request.setAttribute("roleRightJsonStr", roleRightJsonStr);
		request.setAttribute("roleId", roleId);
		return "/role/roleRight";
	}
	/**
	 * 获取当前角色的权限树的数据，直接以json方式返回
	 * @param roleId
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/roleRightJsonStr")
	@ResponseBody
	public SysResult roleRightJsonStr(Long roleId,String rightIds) throws IOException{
		roleService.saveRights(roleId, rightIds);
		return SysResult.ok();
	}
	
}
