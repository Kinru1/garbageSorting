package com.lin.garbagesorting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Created by LGeneratorins on 2023/02/15 14:43
 */

@ApiModel(value="")

public class GarbageSite implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer gsId;

	@ApiModelProperty(value="垃圾站点地址", hidden=false, required=false, dataType="String", example = "")
	private String gsLocation;

	@ApiModelProperty(value="垃圾站点电话", hidden=false, required=false, dataType="String", example = "")
	private String gsPhone;

	@ApiModelProperty(value="垃圾站点负责人", hidden=false, required=false, dataType="String", example = "")
	private String gsLeader;

	@ApiModelProperty(value="垃圾站点状态：1为正常 2为关闭", hidden=false, required=false, dataType="String", example = "")
	private String gsStatus;

	public void setGsId(Integer gsId){
		this.gsId = gsId;
	}
	public Integer getGsId(){
		return gsId;
	}
	public void setGsLocation(String gsLocation){
		this.gsLocation = gsLocation;
	}
	public String getGsLocation(){
		return gsLocation;
	}
	public void setGsPhone(String gsPhone){
		this.gsPhone = gsPhone;
	}
	public String getGsPhone(){
		return gsPhone;
	}
	public void setGsLeader(String gsLeader){
		this.gsLeader = gsLeader;
	}
	public String getGsLeader(){
		return gsLeader;
	}
	public void setGsStatus(String gsStatus){
		this.gsStatus = gsStatus;
	}
	public String getGsStatus(){
		return gsStatus;
	}
	public GarbageSite(){
		super();
	}
	@Override
	public String toString() {
		return "GarbageSite{" +
				"gsId=" + gsId + ", " + 
				"gsLocation=" + gsLocation + ", " + 
				"gsPhone=" + gsPhone + ", " + 
				"gsLeader=" + gsLeader + ", " + 
				"gsStatus=" + gsStatus + 
				'}';
	}
}
