package com.lin.garbagesorting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by LGeneratorins on 2023/02/15 14:40
 */

@ApiModel(value="")
public class Complain implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="投诉类型", hidden=false, required=false, dataType="String", example = "")
	private String type;

	@ApiModelProperty(value="投诉内容", hidden=false, required=false, dataType="String", example = "")
	private String content;

	@ApiModelProperty(value="电话号", hidden=false, required=false, dataType="Integer", example = "")
	private Integer phone;

	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setPhone(Integer phone){
		this.phone = phone;
	}
	public Integer getPhone(){
		return phone;
	}
	public Complain(){
		super();
	}

}
