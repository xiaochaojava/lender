package com.hzwealth.lender.shiro;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzwealth.pojo.RoleRight;
import com.hzwealth.pojo.User;
import com.hzwealth.service.RoleService;
import com.hzwealth.service.UserService;



/*
 * realm是什么时候触发这两个方法。
 */
public class AuthRealm extends AuthorizingRealm {
	private static Logger log = Logger.getLogger(AuthRealm.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	//授权，控制页面标签的权限
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("授权");
		//判断页面的标签的name值是否在集合中
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//获取当前登陆用户，拿到我们系统中的user，
		User curUser = (User)principals.fromRealm(this.getName()).iterator().next();
		
		//获取当前登陆用户的所拥有权限串
		List<String> permissions = roleService.getRightNameByRoleId(curUser.getRoleId());
		info.addStringPermissions(permissions); //内部进行比对
		
		return info;
	}

	//认证。登陆  调用subject.login的方法，就会自动回调doGetAuthenticationInfo方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("认证");
		
		/*
		 * 前台传入username,password。到数据库去查，当前用户是否存在，如果存在获取它的密码。
		 * （在业务层需要一个按userName来查询用户的方法）
		 * 和用户的密码加密后做比较，如果相同，就返回info对象，代表认证成功。直接返回欢迎页面，如果不成功，直接返回null。
		 * （配置自定义的加密算法）
		 */
		
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		User _user = userService.getByUserName(upToken.getUsername());	//系统中，里面的密码就是数据库中的密码
		if(_user==null){
			return null;
		}
		AuthenticationInfo info = new SimpleAuthenticationInfo(_user,_user.getPassword(),this.getName());
		
		return info;
	}

	

}
