package com.hzwealth.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hzwealth.mapper.base.mapper.SysMapper;
import com.hzwealth.pojo.RoleRight;

@Repository
public interface RoleRightMapper extends SysMapper<RoleRight>{
	public List<RoleRight> findRightByRoleId(Map map);
}
