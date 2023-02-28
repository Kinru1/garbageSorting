package com.lin.garbagesorting.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;


@ApiModel(value="")
@Data
public class Announcement  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="创建时间", hidden=false, required=false, dataType="Date", example = "")

	private LocalDateTime creatTime;

	@ApiModelProperty(value="公告类型", hidden=false, required=false, dataType="String", example = "")
	private String announcementType;

	@ApiModelProperty(value="公告内容", hidden=false, required=false, dataType="String", example = "")
	private String announcementContent;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int delete;


}
