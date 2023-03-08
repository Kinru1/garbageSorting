package com.lin.garbagesorting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
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

	@ApiModelProperty(value="业主用户名", hidden=false, required=false, dataType="String", example = "")
	private String tenantUsername;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;



}
