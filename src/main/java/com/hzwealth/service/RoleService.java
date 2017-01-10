package com.hzwealth.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.mapper.RightMapper;
import com.hzwealth.mapper.RoleMapper;
import com.hzwealth.mapper.RoleRightMapper;
import com.hzwealth.pojo.Right;
import com.hzwealth.pojo.Role;
import com.hzwealth.pojo.RoleRight;

@Service
public class RoleService extends BaseService<Role>{
	 
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleRightMapper roleRightMapper;
	
	@Autowired
	private RightMapper rightMapper;

	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> findRoles(){
		return roleMapper.select(null);
	}
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role){
		role.setCreateTime(new Date());
		role.setModifyTime(new Date());
		role.setState("0");  //启用
		roleMapper.insert(role);
	}
	/**
	 * 编辑角色
	 * @param role
	 */
	public void editRole(Role role){
		role.setModifyTime(new Date());
		roleMapper.updateByPrimaryKeySelective(role);
	}
	/**
	 * 禁用或启用角色
	 * @param role
	 */
	public void deleteRole(Long[] ids){
		for(Long id : ids){
			Role role = queryById(id);
			String state ="";
			state = "0".equals(role.getState()) ? "1" :"0";  //
			role.setState(state);
			role.setModifyTime(new Date());
			roleMapper.updateByPrimaryKeySelective(role);
		}
	}
	/**
	 * 根据角色返回所拥有的权限名
	 * @param roleId
	 * @return
	 */
	public List<String> getRightNameByRoleId(Long roleId){
		Map<String,Long> map = new HashMap<String,Long>();
		map.put("roleId", roleId);
		List<RoleRight> rrlist = roleRightMapper.findRightByRoleId(map);
		List<String> rightList = new ArrayList<>();
		for(RoleRight rr:rrlist){
			Right right = rightMapper.selectByPrimaryKey(rr.getRightId());
			rightList.add(right.getRightName());
		}
		return rightList;
	}
	/**
	 * 根据角色id找到所拥有的权限
	 * @param roleId
	 * @return
	 */
	public List<Right> getRightListByRoleId(Long roleId){
		Map<String,Long> map = new HashMap<String,Long>();
		map.put("roleId", roleId);
		List<Right> rlist =rightMapper.getRightListByRoleId(map);
		return rlist;
	}
	
	/**
	 * 根据角色获取所拥有的权限
	 * @param roleId
	 * @return
	 */
	public String getRightByRoleId(Long roleId){
		Map<String,Long> map = new HashMap<String,Long>();
		map.put("roleId", roleId);
		List<RoleRight> list = roleRightMapper.findRightByRoleId(map);
		StringBuilder sbu = new StringBuilder();
		for(RoleRight rr:list){
			sbu.append(rr.getRightId()).append(",");
		}
		if(sbu!=null && sbu.length()>1){
			sbu.delete(sbu.length()-1, sbu.length());
		}
		return sbu.toString();
	}
	/**
	 * 菜单的加载
	 * 准备ztree的数据，( [{"id","pid","name","url"}] )
	 * @param roleId
	 * @return
	 */
	public String getRightJson(Long roleId){
		List<Right> rightList = getRightListByRoleId(roleId);
		StringBuilder sBui = new StringBuilder();
		
		sBui.append("[");
		for(Right r : rightList){
			sBui.append("{");
			sBui.append("id:\"").append(r.getId()).append("\",");
			sBui.append("pId:\"").append(r.getParentId()).append("\",");
			sBui.append("name:\"").append(r.getRightName()).append("\",");
			sBui.append("url:\"").append(r.getRightPath()).append("\"");
			sBui.append("}");
		}
		if(sBui.length()>0){
			sBui.delete(sBui.length()-1, sBui.length());
		}
		sBui.append("]");
		
		return sBui.toString();
	}
	
	
	/**
	 * 菜单授权Ztree树  授权模块
	 * 准备ztree的数据，所有启用的模块( [{"id","pid","name","checked"},{"id","pid","name","checked"}] )
	 * @param roleId
	 * @return
	 */
	public String getRightJsonStr(Long roleId){
		String rightIds = getRightByRoleId(roleId);
		List<Right> rightList = rightMapper.select(null);
		StringBuilder sBui = new StringBuilder();
		sBui.append("[");
		for(Right r : rightList){
			sBui.append("{");
			sBui.append("id:\"").append(r.getId()).append("\",");
			sBui.append("pId:\"").append(r.getParentId()).append("\",");
			sBui.append("name:\"").append(r.getRightName()).append("\",");
			//判断依据，check是否选中
			sBui.append("checked:\"");
			if(rightIds.contains(r.getId()+"")){
				sBui.append("true");
			}else{
				sBui.append("false");
			}
			sBui.append("\"");
			sBui.append("},");
		}
		if(sBui.length()>0){
			sBui.delete(sBui.length()-1, sBui.length());
		}
		sBui.append("]");
		
		return sBui.toString();
	}
	/**
	 * 展示菜单list  菜单管理模块
	 * @param roleId
	 * @return
	 */
	public String getRJsonStr(Long roleId){
		List<Right> rightList = rightMapper.select(null);
		JSONArray array = new JSONArray();
		for(Right right :rightList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", right.getId());
			jsonObject.put("name", right.getRightName());
			jsonObject.put("pId", right.getParentId());
			array.put(jsonObject);
		}
		return array.toString();
	}
	/**
	 * 保存角色的菜单的集合
	 * @param roleId
	 * @param rightIds
	 */
	public void saveRights(Long roleId, String rightIds){
		RoleRight rr = new RoleRight();
		rr.setRoleId(roleId);
		//先删除
		roleRightMapper.delete(rr);
		//获取了用户选中的模块对象
		if(rightIds!=null && rightIds.length()>0){
			for(String rId: rightIds.split(",")){
				//再保存
				RoleRight roleRight = new RoleRight();
				roleRight.setRoleId(roleId);
				roleRight.setRightId(Long.valueOf(rId));
				roleRightMapper.insertSelective(roleRight);
			}
		}
		
	}
	
}
