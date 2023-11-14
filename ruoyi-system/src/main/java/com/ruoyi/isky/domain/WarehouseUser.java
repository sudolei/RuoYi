package com.ruoyi.isky.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户表 seg_warehouse_user
 * 
 * @author sunlei
 * @date 2019-11-05
 */
public class WarehouseUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 用户名 */
	private String userName;
	/** 用户权限 */
	private String userRole;
	/** 状态 */
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
