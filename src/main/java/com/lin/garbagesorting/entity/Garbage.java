package com.lin.garbagesorting.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

@Data
@ApiModel(value="")

public class Garbage  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer garbageId;

	@ApiModelProperty(value="垃圾名", hidden=false, required=true, dataType="String", example = "")
	private String garbageName;

	@ApiModelProperty(value="垃圾类型", hidden=false, required=true, dataType="String", example = "")
	private String garbageType;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int delete;

	private String img;
}
