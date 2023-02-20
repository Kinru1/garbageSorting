package com.lin.garbagesorting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="")
public class Tenant implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer tenantId;

	@ApiModelProperty(value="业主家庭住址", hidden=false, required=false, dataType="String", example = "")
	private String tenantLocation;

	@ApiModelProperty(value="业主所属小区", hidden=false, required=true, dataType="String", example = "")
	private String tenantCommunity;

	@ApiModelProperty(value="业主名", hidden=false, required=false, dataType="String", example = "")
	private String tenantOwer;

	public void setTenantId(Integer tenantId){
		this.tenantId = tenantId;
	}
	public Integer getTenantId(){
		return tenantId;
	}
	public void setTenantLocation(String tenantLocation){
		this.tenantLocation = tenantLocation;
	}
	public String getTenantLocation(){
		return tenantLocation;
	}
	public void setTenantCommunity(String tenantCommunity){
		this.tenantCommunity = tenantCommunity;
	}
	public String getTenantCommunity(){
		return tenantCommunity;
	}
	public void setTenantOwer(String tenantOwer){
		this.tenantOwer = tenantOwer;
	}
	public String getTenantOwer(){
		return tenantOwer;
	}
	public Tenant(){
		super();
	}
	@Override
	public String toString() {
		return "Tenant{" +
				"tenantId=" + tenantId + ", " + 
				"tenantLocation=" + tenantLocation + ", " + 
				"tenantCommunity=" + tenantCommunity + ", " + 
				"tenantOwer=" + tenantOwer + 
				'}';
	}
}
