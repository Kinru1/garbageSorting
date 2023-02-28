package com.lin.garbagesorting.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by LGeneratorins on 2023/02/15 14:41
 */
@Data
@ApiModel(value="")
public class GarbageProcessingInfo  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="", hidden=false, required=true, dataType="Integer", example = "")
	private Integer gpId;

	@ApiModelProperty(value="垃圾源小区", hidden=false, required=false, dataType="String", example = "")
	private String gpComunity;

	@ApiModelProperty(value="垃圾送往站点", hidden=false, required=false, dataType="String", example = "")
	private String gpSite;

	@ApiModelProperty(value="处理日期", hidden=false, required=false, dataType="Date", example = "")
	private LocalDateTime gpDay;

	@ApiModelProperty(value="垃圾总量", hidden=false, required=false, dataType="null", example = "")
	private double gpTotal;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Integer", example = "")
	private Integer gpGsId;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int delete;

	private String date;
	@Override
	public String toString() {
		return "GarbageProcessingInfo{" +
				"gpId=" + gpId + ", " + 
				"gpComunity=" + gpComunity + ", " + 
				"gpSite=" + gpSite + ", " + 
				"gpDay=" + gpDay + ", " + 
				"gpTotal=" + gpTotal + ", " + 
				"gpGsId=" + gpGsId + 
				'}';
	}
}
