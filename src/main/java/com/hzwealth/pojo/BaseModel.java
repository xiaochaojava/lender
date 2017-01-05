package com.hzwealth.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;


public abstract class BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3132759722116052662L;
	
	@Column(name="createtime")
	private Date createTime;   //禁用时间
	
	@Column(name="modifytime")
	private Date modifyTime;   //修改时间
	
	private String state;   //0是启用，1是禁用
	
	private Integer version = 0;//版本(用于乐观锁控制)
	
	@Column(name="sortidx")
	private Long sortIdx = System.currentTimeMillis();//排序号
	

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getSortIdx() {
		return sortIdx;
	}
	public void setSortIdx(Long sortIdx) {
		this.sortIdx = sortIdx;
	}
	
	
	
	
}
