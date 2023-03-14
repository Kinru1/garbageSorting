package com.lin.garbagesorting.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by LGeneratorins on 2023/02/15 14:39
 */

@ApiModel(value="")
@Data
public class ChallengeResult  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="得分", hidden=false, required=false, dataType="Integer", example = "")
	private Integer score;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="挑战时间", hidden=false, required=false, dataType="Date", example = "")
	private LocalDateTime challengeTime;

	@ApiModelProperty(value="用户名", hidden=false, required=false, dataType="String", example = "")
	private String username;



}
