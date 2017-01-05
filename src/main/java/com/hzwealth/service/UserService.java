package com.hzwealth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.common.util.Encrypt;
import com.hzwealth.mapper.UserMapper;
import com.hzwealth.pojo.User;

@Service
public class UserService extends BaseService<User>{
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 
	 * 查询列表
	 */
	public List<User> findAllUser(){
		return userMapper.select(null);
	}
	/**
	 * 根据用户名和密码查询user
	 * @return
	 */
	public List<User> findUserByUAndP(String username,String password){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		return userMapper.findUser(map);
	}
	/**
	 * 根据用户名获取用户
	 * @param username
	 * @return
	 */
	public User getByUserName(String username){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("username", username);
		return userMapper.findUser(map).get(0);
	} 
	/**
	 * 用户名是否已存在
	 * @param username
	 * @return
	 */
	public Boolean checkName(String username){
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("username", username);
		return userMapper.checkUser(map)>0;
	}
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user){
		user.setCreateTime(new Date());
		user.setModifyTime(new Date());
		user.setState("0");  //启动
		user.setPassword(Encrypt.md5hash(user.getPassword(), user.getUsername()));
		userMapper.insert(user);
	}
	/**
	 * 修改用户
	 * @param user
	 */
	public void editUser(User user){
		user.setModifyTime(new Date());
		if(user.getPassword()!=null&&!"".equals(user.getPassword())){
			user.setPassword(Encrypt.md5hash(user.getPassword(), user.getUsername()));
		}
		userMapper.updateByPrimaryKeySelective(user);
	}
	/**
	 * 删除用户
	 * @param ids
	 */
	public void deleteUser(Long[] ids){
		for(Long id :ids){
			User user = queryById(id);
			String state = "";
			state = "0".equals(user.getState()) ? "1":"0";
			user.setState(state);
			user.setModifyTime(new Date());
			userMapper.updateByPrimaryKeySelective(user);
		}
	}
	
}
