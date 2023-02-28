package com.lin.garbagesorting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by LGeneratorins on 2023/02/15 14:40
 */
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

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int delete;

}
