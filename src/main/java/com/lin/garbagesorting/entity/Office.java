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

public class Office  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Long ofId;

	@ApiModelProperty(value="物业电话", hidden=false, required=false, dataType="Integer", example = "")
	private String ofPhone;

	@ApiModelProperty(value="物业用户名", hidden=false, required=false, dataType="String", example = "")
	private String ofUsername;

	@ApiModelProperty(value="物业所属小区", hidden=false, required=false, dataType="String", example = "")
	private String ofCommunity;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;


}
