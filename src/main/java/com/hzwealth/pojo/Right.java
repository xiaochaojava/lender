package com.hzwealth.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 权限表
 * @author lixiaochao
 *
 */
@Table(name="tb_right")
public class Right{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="right_name")
	private String rightName;         //菜单名称
	
	@Column(name="right_path")
	private String rightPath;             //菜单路径
	
	@Column(name="parent_id")         //父菜单Id
	private Long parentId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightPath() {
		return rightPath;
	}

	public void setRightPath(String rightPath) {
		this.rightPath = rightPath;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
