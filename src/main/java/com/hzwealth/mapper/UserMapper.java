package com.hzwealth.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hzwealth.mapper.base.mapper.SysMapper;
import com.hzwealth.pojo.User;

@Repository
public interface UserMapper extends SysMapper<User>{
	public List<User> findUser(Map map);
	
	public Integer checkUser(Map map);
}
