package com.hzwealth.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzwealth.common.vo.EasyUIResult;
import com.hzwealth.common.vo.SysConstant;
import com.hzwealth.common.vo.SysResult;
import com.hzwealth.pojo.User;
import com.hzwealth.service.RightService;
import com.hzwealth.service.RoleService;
import com.hzwealth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 查询所有用户
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult list(Integer page, Integer rows){
		PageHelper .startPage(page, rows,true);
		List<User> userList = userService.findAllUser();
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());	
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(HttpServletRequest request,HttpServletResponse response,String username,String password){
		if(username==null){
			return SysResult.build(220, "用户名为空");
		}
		//进行shiro判断
		Subject subject = SecurityUtils.getSubject();
		//通过token来封装用户的username和password，传递给subject
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try{
			subject.login(token);	//登录后，获取当前登陆用户，写入session
			//拿到当前登陆用户
			User curUser = (User)subject.getPrincipal();
//			String deptName = curUser.getDept().getDeptName();	//加载dept对象
			request.getSession().setAttribute(SysConstant.CURRENT_USER_INFO, curUser);	//写入session
			String rightJson = roleService.getRightJson(curUser.getRoleId());
			request.getSession().setAttribute("rightJson", rightJson);
		}catch(Exception ex){
			return SysResult.build(230, ex.getMessage());
		}
		return SysResult.ok();
	}
	
	/**
	 * 检查用户名是否已存在
	 * @param username
	 * @return
	 */
	@RequestMapping("/checkName")
	@ResponseBody
	public SysResult checkName(String username){
		if(userService.checkName(username)){
			return SysResult.build(100, "用户名已存在");
		}
		return SysResult.ok();
	}
	
	/**
	 * 退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
//		session.invalidate();
		Enumeration<String> en = request.getSession().getAttributeNames();
		while(en.hasMoreElements()){
			request.getSession().removeAttribute(en.nextElement().toString());
		}
		return "/user/login";
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public SysResult save(User user){
		userService.addUser(user);
		return SysResult.ok();
	}
	/**
	 * 修改用户 
	 * @param user
	 * @return
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	public SysResult editUser(User user){
		userService.editUser(user);
		return SysResult.ok();
	}
	/**
	 * 禁用或启用用户
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public SysResult deleteUser(Long[] ids){
		userService.deleteUser(ids);
		return SysResult.ok();
	}
}
