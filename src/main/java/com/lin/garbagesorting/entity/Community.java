package com.lin.garbagesorting.entity;

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

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer comId;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="String", example = "")
	private String comName;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="String", example = "")
	private String comPlace;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Integer", example = "")
	private Integer comAdmin;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Integer", example = "")
	private Integer comPhone;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Date", example = "")
	private LocalDateTime createTime;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Date", example = "")
	private LocalDateTime updateTime;


	@Override
	public String toString() {
		return "Community{" +
				"comId=" + comId + ", " + 
				"comName=" + comName + ", " + 
				"comPlace=" + comPlace + ", " + 
				"comAdmin=" + comAdmin + ", " + 
				"comPhone=" + comPhone + ", " + 
				"createTime=" + createTime + ", " + 
				"updateTime=" + updateTime + 
				'}';
	}
}
