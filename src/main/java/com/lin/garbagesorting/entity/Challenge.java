package com.lin.garbagesorting.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(value="")
@Data
public class Challenge implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="是否正确；1:正确；0:错误", hidden=false, required=false, dataType="Integer", example = "")
	private Integer whether;

	@ApiModelProperty(value="问题id ", hidden=false, required=false, dataType="Integer", example = "")
	private Integer questionId;

	@ApiModelProperty(value="垃圾名", hidden=false, required=false, dataType="String", example = "")
	private String garbageName;

	@ApiModelProperty(value="垃圾类型", hidden=false, required=false, dataType="String", example = "")
	private String garbageType;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;

}
