package com.lin.garbagesorting.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(value="")

public class Challenge implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="是否正确；1:正确；0:错误", hidden=false, required=false, dataType="Integer", example = "")

	private Integer whether;

	@ApiModelProperty(value="问题id ", hidden=false, required=false, dataType="Integer", example = "")

	private Integer questionId;

	@ApiModelProperty(value="垃圾名", hidden=false, required=false, dataType="String", example = "")

	private String garbageName;

	@ApiModelProperty(value="垃圾类型", hidden=false, required=false, dataType="String", example = "")
	private String garbageType;

	public void setWhether(Integer whether){
		this.whether = whether;
	}
	public Integer getWhether(){
		return whether;
	}
	public void setQuestionId(Integer questionId){
		this.questionId = questionId;
	}
	public Integer getQuestionId(){
		return questionId;
	}
	public void setGarbageName(String garbageName){
		this.garbageName = garbageName;
	}
	public String getGarbageName(){
		return garbageName;
	}
	public void setGarbageType(String garbageType){
		this.garbageType = garbageType;
	}
	public String getGarbageType(){
		return garbageType;
	}
	public Challenge(){
		super();
	}

}
