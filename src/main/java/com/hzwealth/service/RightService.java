package com.hzwealth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.mapper.RightMapper;
import com.hzwealth.mapper.RoleRightMapper;
import com.hzwealth.pojo.Right;
import com.hzwealth.pojo.RoleRight;

@Service
public class RightService extends BaseService<Right>{
	
	@Autowired
	private RightMapper rightMapper;
	
	@Autowired
	private RoleRightMapper roleRightMapper;
	/**
	 * 寻找子孩子
	 * @param parentId
	 * @return
	 */
	public List<Right> findChildren(Long parentId){
		Right right = new Right();
		right.setParentId(parentId);
		List<Right> list = rightMapper.select(right);
		return list;
	}
	/**
	 * 删除菜单
	 */
	public void deleteRight(Long rightId){
		RoleRight rr = new RoleRight();
		rr.setRightId(rightId);
		roleRightMapper.delete(rr);
		rightMapper.deleteByPrimaryKey(rightId);
	}
	/**
	 * 修改菜单
	 * @param right
	 */
	public void editRight(Right right){
		rightMapper.updateByPrimaryKeySelective(right);
	}
	/**
	 * 添加菜单
	 * @param right
	 */
	public void addRight(Right right){
		rightMapper.insert(right);
	}
	
	
}
