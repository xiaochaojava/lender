package com.hzwealth.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hzwealth.mapper.base.mapper.SysMapper;
import com.hzwealth.pojo.Right;

@Repository
public interface RightMapper extends SysMapper<Right>{
	public List<Right> getRightListByRoleId(Map map);
}
