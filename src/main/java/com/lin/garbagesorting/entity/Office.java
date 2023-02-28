package com.lin.garbagesorting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(value="")

public class Office  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer ofId;

	@ApiModelProperty(value="物业电话", hidden=false, required=false, dataType="Integer", example = "")
	private Integer ofPhone;

	@ApiModelProperty(value="物业用户名", hidden=false, required=false, dataType="String", example = "")
	private String ofUsername;

	@ApiModelProperty(value="物业密码", hidden=false, required=false, dataType="String", example = "")
	private String ofPassword;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int delete;

	public void setOfId(Integer ofId){
		this.ofId = ofId;
	}
	public Integer getOfId(){
		return ofId;
	}
	public void setOfPhone(Integer ofPhone){
		this.ofPhone = ofPhone;
	}
	public Integer getOfPhone(){
		return ofPhone;
	}
	public void setOfUsername(String ofUsername){
		this.ofUsername = ofUsername;
	}
	public String getOfUsername(){
		return ofUsername;
	}
	public void setOfPassword(String ofPassword){
		this.ofPassword = ofPassword;
	}
	public String getOfPassword(){
		return ofPassword;
	}
	public Office(){
		super();
	}
	@Override
	public String toString() {
		return "Office{" +
				"ofId=" + ofId + ", " + 
				"ofPhone=" + ofPhone + ", " + 
				"ofUsername=" + ofUsername + ", " + 
				"ofPassword=" + ofPassword + 
				'}';
	}
}
