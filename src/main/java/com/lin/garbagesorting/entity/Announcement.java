package com.lin.garbagesorting.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import java.util.Date;


@ApiModel(value="")

public class Announcement  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="创建时间", hidden=false, required=false, dataType="Date", example = "")

	private Date creatTime;

	@ApiModelProperty(value="公告类型", hidden=false, required=false, dataType="String", example = "")
	private String announcementType;

	@ApiModelProperty(value="公告内容", hidden=false, required=false, dataType="String", example = "")
	private String announcementContent;

	public void setCreatTime(Date creatTime){
		this.creatTime = creatTime;
	}
	public Date getCreatTime(){
		return creatTime;
	}
	public void setAnnouncementType(String announcementType){
		this.announcementType = announcementType;
	}
	public String getAnnouncementType(){
		return announcementType;
	}
	public void setAnnouncementContent(String announcementContent){
		this.announcementContent = announcementContent;
	}
	public String getAnnouncementContent(){
		return announcementContent;
	}
	public Announcement(){
		super();
	}

}
