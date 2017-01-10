package com.hzwealth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzwealth.common.vo.SysConstant;
import com.hzwealth.common.vo.SysResult;
import com.hzwealth.pojo.Right;
import com.hzwealth.pojo.User;
import com.hzwealth.service.RightService;
import com.hzwealth.service.RoleService;
/**
 * 菜单管理
 * @author lixiaochao
 *create Date : 2017/1/6  17:08
 */
@Controller
@RequestMapping("/right")
public class RightController {
	
	@Autowired
	private RightService rightService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 修改菜单
	 * @param right
	 * @return
	 */
	@RequestMapping("/editRight")
	@ResponseBody
	public SysResult editRight(Long rightId,String name){	
		try {
		name = java.net.URLDecoder.decode(name,"UTF-8");
		Right right = new Right();
		right.setId(rightId);
		right.setRightName(name);
		rightService.editRight(right);
		return SysResult.ok();
		} catch (Exception e) {
			return SysResult.build(501, "转码有误");
		}
		
	}
	/**
	 * 删除菜单
	 * @param rightId
	 * @return
	 */
	@RequestMapping("/deleteRight")
	@ResponseBody
	public SysResult deleteRight(Long rightId){
		rightService.deleteRight(rightId);
		return SysResult.ok();
	}
	/**
	 * 添加菜单
	 * @param right
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public SysResult save(String name,Long pId){
		try {
			name = java.net.URLDecoder.decode(name,"UTF-8");
			Right right = new Right();
			right.setRightName(name);
			right.setParentId(pId);
			rightService.addRight(right);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.build(501, "转码有误");
		}
	}
	/**
	 * 显示信息菜单
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public SysResult list(HttpServletRequest request){
		if(request==null||
				request.getSession().getAttribute(SysConstant.CURRENT_USER_INFO)==null){
			return SysResult.build(300, "当前用户未登录，请重新登录");
		}
		User user = (User)request.getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		String jsonStr = roleService.getRJsonStr(user.getRoleId());
		//request.getSession().setAttribute("jsonStr", jsonStr);
		//System.out.println(request.getSession().getAttribute("jsonStr"));
		return SysResult.ok(jsonStr);
	}
	
}
