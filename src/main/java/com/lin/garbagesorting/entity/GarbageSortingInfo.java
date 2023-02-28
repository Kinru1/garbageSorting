package com.lin.garbagesorting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

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

	@ApiModelProperty(value="有害垃圾", hidden=false, required=false, dataType="null", example = "")
	private double gsTotalHazardous;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Integer", example = "")
	private Integer gsId;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int delete;
}
