package com.lin.garbagesorting.entity;

import cn.hutool.core.annotation.Alias;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(value="")
public class GarbageSortingInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="可回收垃圾总量", hidden=false, required=false, dataType="null", example = "")
	private double gsTotalRecyclable;

	@ApiModelProperty(value="其他垃圾总量", hidden=false, required=false, dataType="null", example = "")
	private double gsTotalOther;

	@ApiModelProperty(value="厨余垃圾总量", hidden=false, required=false, dataType="null", example = "")
	private double gsTotalKitchen;

	@ApiModelProperty(value="有害垃圾总量", hidden=false, required=false, dataType="null", example = "")
	private double gsTotalHazardous;

	@ApiModelProperty(value="", hidden=false, required=false)
	private Long gsId;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;

	// 垃圾总量
	@ApiModelProperty("垃圾总量")
	@Alias("垃圾总量")

	private double gsTotal;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;

	@ApiModelProperty(value="小区名", hidden=false, required=false, dataType="String", example = "")
	private String community;
}
