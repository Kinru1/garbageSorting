package com.lin.garbagesorting.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import java.util.Date;



@ApiModel(value="")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="姓名", hidden=false, required=true, dataType="String", example = "")
	private String name;

	@ApiModelProperty(value="用户名", hidden=false, required=true, dataType="String", example = "")
	private String username;

	@ApiModelProperty(value="密码", hidden=false, required=true, dataType="String", example = "")
	private String password;

	@ApiModelProperty(value="手机号", hidden=false, required=true, dataType="String", example = "")
	private String phone;

	@ApiModelProperty(value="性别", hidden=false, required=true, dataType="String", example = "")
	private String sex;

	@ApiModelProperty(value="状态：1为正常 2为禁用", hidden=false, required=true, dataType="Integer", example = "")
	private Integer status;

	@ApiModelProperty(value="创建时间", hidden=false, required=true, dataType="Date", example = "")
	private Date createTime;

	@ApiModelProperty(value="更新时间", hidden=false, required=true, dataType="Date", example = "")
	private Date updateTime;

	@ApiModelProperty(value="创建者", hidden=false, required=true, dataType="Integer", example = "")
	private Integer createUser;

	@ApiModelProperty(value="更新者", hidden=false, required=true, dataType="Integer", example = "")
	private Integer updateUser;

	@ApiModelProperty(value="类型 1为普通用户 2为物业 3为管理人员", hidden=false, required=true, dataType="Integer", example = "")
	private Integer type;

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public String getSex(){
		return sex;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setCreateUser(Integer createUser){
		this.createUser = createUser;
	}
	public Integer getCreateUser(){
		return createUser;
	}
	public void setUpdateUser(Integer updateUser){
		this.updateUser = updateUser;
	}
	public Integer getUpdateUser(){
		return updateUser;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getType(){
		return type;
	}
	public User(){
		super();
	}

}
