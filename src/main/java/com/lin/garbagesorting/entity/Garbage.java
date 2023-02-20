package com.lin.garbagesorting.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;


@ApiModel(value="")

public class Garbage  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer garbageId;

	@ApiModelProperty(value="垃圾名", hidden=false, required=true, dataType="String", example = "")
	private String garbageName;

	@ApiModelProperty(value="垃圾类型", hidden=false, required=true, dataType="String", example = "")
	private String garbageType;

	public void setGarbageId(Integer garbageId){
		this.garbageId = garbageId;
	}
	public Integer getGarbageId(){
		return garbageId;
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
	public Garbage(){
		super();
	}
	@Override
	public String toString() {
		return "Garbage{" +
				"garbageId=" + garbageId + ", " + 
				"garbageName=" + garbageName + ", " + 
				"garbageType=" + garbageType + 
				'}';
	}
}
