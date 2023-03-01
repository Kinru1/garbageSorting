package com.lin.garbagesorting.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by LGeneratorins on 2023/02/15 14:40
 */
@Data
@ApiModel(value="")
public class Community  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="小区ID", hidden=false, required=true, dataType="Integer", example = "")
	private Integer comId;

	@ApiModelProperty(value="小区名", hidden=false, required=false, dataType="String", example = "")
	private String comName;

	@ApiModelProperty(value="小区地址", hidden=false, required=false, dataType="String", example = "")
	private String comPlace;

	@ApiModelProperty(value="小区负责人", hidden=false, required=false, dataType="Integer", example = "")
	private Integer comAdmin;

	@ApiModelProperty(value="小区电话", hidden=false, required=false, dataType="Integer", example = "")
	private Integer comPhone;

	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value="创建时间", hidden=false, required=false, dataType="Date", example = "")
	private LocalDateTime createTime;

	@TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
	@ApiModelProperty(value="修改时间", hidden=false, required=false, dataType="Date", example = "")
	private LocalDateTime updateTime;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;


}
