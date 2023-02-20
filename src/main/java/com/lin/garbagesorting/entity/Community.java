package com.lin.garbagesorting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by LGeneratorins on 2023/02/15 14:40
 */

@ApiModel(value="")
public class Community  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer comId;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="String", example = "")
	private String comName;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="String", example = "")
	private String comPlace;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Integer", example = "")
	private Integer comAdmin;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Integer", example = "")
	private Integer comPhone;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Date", example = "")
	private Date createTime;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Date", example = "")
	private Date updateTime;

	public void setComId(Integer comId){
		this.comId = comId;
	}
	public Integer getComId(){
		return comId;
	}
	public void setComName(String comName){
		this.comName = comName;
	}
	public String getComName(){
		return comName;
	}
	public void setComPlace(String comPlace){
		this.comPlace = comPlace;
	}
	public String getComPlace(){
		return comPlace;
	}
	public void setComAdmin(Integer comAdmin){
		this.comAdmin = comAdmin;
	}
	public Integer getComAdmin(){
		return comAdmin;
	}
	public void setComPhone(Integer comPhone){
		this.comPhone = comPhone;
	}
	public Integer getComPhone(){
		return comPhone;
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
	public Community(){
		super();
	}
	@Override
	public String toString() {
		return "Community{" +
				"comId=" + comId + ", " + 
				"comName=" + comName + ", " + 
				"comPlace=" + comPlace + ", " + 
				"comAdmin=" + comAdmin + ", " + 
				"comPhone=" + comPhone + ", " + 
				"createTime=" + createTime + ", " + 
				"updateTime=" + updateTime + 
				'}';
	}
}
