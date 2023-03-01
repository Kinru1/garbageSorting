package com.lin.garbagesorting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * Created by LGeneratorins on 2023/02/15 14:43
 */
@Data
@ApiModel(value="")

public class GarbageSite implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="站点ID", hidden=false, required=true, dataType="Integer", example = "")
	private Integer gsId;

	@ApiModelProperty(value="垃圾站点地址", hidden=false, required=false, dataType="String", example = "")
	private String gsLocation;

	@ApiModelProperty(value="垃圾站点电话", hidden=false, required=false, dataType="String", example = "")
	private String gsPhone;

	@ApiModelProperty(value="垃圾站点负责人", hidden=false, required=false, dataType="String", example = "")
	private String gsLeader;

	@ApiModelProperty(value="垃圾站点状态：1为正常 0为关闭", hidden=false, required=false, dataType="String", example = "")
	private int gsStatus;


	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;



}
