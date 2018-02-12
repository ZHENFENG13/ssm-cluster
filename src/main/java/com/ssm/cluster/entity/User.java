package com.ssm.cluster.entity;

import java.io.Serializable;

/**

 * @author 13
 * @date 2018-02-11 13:39:08
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//登录名
	private String userName;
	//加密后的密码字段
	private String password;
	//用户角色
	private String roleName;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：登录名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：登录名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：加密后的密码字段
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：加密后的密码字段
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：用户角色
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：用户角色
	 */
	public String getRoleName() {
		return roleName;
	}
}
