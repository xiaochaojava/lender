package com.hzwealth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.mapper.RightMapper;
import com.hzwealth.pojo.Right;

@Service
public class RightService extends BaseService<Right>{
	
	@Autowired
	private RightMapper rightMapper;
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
	
	
	
}
