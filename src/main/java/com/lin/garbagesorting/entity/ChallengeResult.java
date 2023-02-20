package com.lin.garbagesorting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import java.util.Date;

/**
 * Created by LGeneratorins on 2023/02/15 14:39
 */

@ApiModel(value="")

public class ChallengeResult  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="得分", hidden=false, required=false, dataType="Integer", example = "")
	private Integer score;

	@ApiModelProperty(value="挑战时间", hidden=false, required=false, dataType="Date", example = "")
	private Date challengeTime;

	@ApiModelProperty(value="用户名", hidden=false, required=false, dataType="String", example = "")
	private String username;

	public void setScore(Integer score){
		this.score = score;
	}
	public Integer getScore(){
		return score;
	}
	public void setChallengeTime(Date challengeTime){
		this.challengeTime = challengeTime;
	}
	public Date getChallengeTime(){
		return challengeTime;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public ChallengeResult(){
		super();
	}

}
