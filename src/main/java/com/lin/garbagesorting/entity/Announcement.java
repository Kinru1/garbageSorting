package com.lin.garbagesorting.entity;


import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.*;
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

	@TableField(fill = FieldFill.INSERT) //插入和更新时填充字段
	@ApiModelProperty(value="创建时间", hidden=false, required=false, dataType="Date", example = "")

	private LocalDateTime createTime;

	@ApiModelProperty(value="公告类型", hidden=false, required=false, dataType="String", example = "")
	private String announcementType;

	@ApiModelProperty(value="公告ID", hidden=false, required=false, dataType="int", example = "")
	private Integer announcementId;

	@ApiModelProperty(value="公告内容", hidden=false, required=false, dataType="String", example = "")
	private String announcementContent;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;

	// 公告图片
	@ApiModelProperty("公告图片")

	private String announcementImg;

	// 公告文件
	@ApiModelProperty("公告文件")

	private String announcementFile;
}
