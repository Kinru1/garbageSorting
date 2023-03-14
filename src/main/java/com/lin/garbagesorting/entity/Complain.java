package com.lin.garbagesorting.entity;

import cn.hutool.core.annotation.Alias;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(value="")
public class Complain implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="投诉类型", hidden=false, required=false, dataType="String", example = "")
	private String type;

	@ApiModelProperty(value="投诉内容", hidden=false, required=false, dataType="String", example = "")
	private String content;

	@ApiModelProperty(value="电话号", hidden=false, required=false, dataType="Integer", example = "")
	private Integer phone;

	@ApiModelProperty(value="投诉ID", hidden=false, required=false, dataType="Integer", example = "")
	private Integer complainId;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;

	// 投诉图片
	@ApiModelProperty("投诉图片")
	@Alias("投诉图片")
	private String img;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField(fill = FieldFill.INSERT) //插入和更新时填充字段
	@ApiModelProperty("投诉时间")
	private String date;
}
