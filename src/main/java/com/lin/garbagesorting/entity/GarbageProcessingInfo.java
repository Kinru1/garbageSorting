package com.lin.garbagesorting.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	private Long gpId;

	@ApiModelProperty(value="垃圾源小区", hidden=false, required=false, dataType="String", example = "")
	private String gpComunity;

	@ApiModelProperty(value="垃圾送往站点", hidden=false, required=false, dataType="String", example = "")
	private String gpSite;


	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime gpDay;

	@ApiModelProperty(value="垃圾总量", hidden=false, required=false, dataType="null", example = "")
	private double gpTotal;

	@ApiModelProperty(value="", hidden=false, required=false, dataType="Integer", example = "")
	private Integer gpGsId;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;

	@ApiModelProperty(value="垃圾处理时间", hidden=false, required=false, dataType="Date", example = "")
	private String date;

	@ApiModelProperty(value="垃圾送往站点", hidden=false, required=false, dataType="String", example = "")
	private  String processingPeopleName;
}
