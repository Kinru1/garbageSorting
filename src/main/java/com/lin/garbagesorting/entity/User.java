package com.lin.garbagesorting.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@ToString
@ApiModel(value="")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;

	@ApiModelProperty(value="姓名", hidden=false, required=true, dataType="String", example = "")
	private String name;

	@ApiModelProperty(value="用户名", hidden=false, required=true, dataType="String", example = "")
	private String username;

	@ApiModelProperty(value="密码", hidden=false, required=true, dataType="String", example = "")
	private String password;

	@ApiModelProperty(value="手机号", hidden=false, required=true, dataType="String", example = "")
	private String phone;

	@ApiModelProperty(value="性别", hidden=false, required=true, dataType="String", example = "")
	private String sex;

	@ApiModelProperty(value="状态：1为正常 2为禁用", hidden=false, required=true, dataType="Integer", example = "")
	private Integer status;

	@TableField(fill = FieldFill.INSERT) //插入时填充字段
	@ApiModelProperty(value="创建时间", hidden=false, required=true, dataType="Date", example = "")
	private LocalDateTime createTime;

	@TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
	@ApiModelProperty(value="更新时间", hidden=false, required=true, dataType="Date", example = "")
	private LocalDateTime updateTime;

	@TableField(fill = FieldFill.INSERT) //插入时填充字段
	@ApiModelProperty(value="创建者", hidden=false, required=true, dataType="Integer", example = "")
	private Long createUser;

	@TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
	@ApiModelProperty(value="更新者", hidden=false, required=true, dataType="Integer", example = "")
	private Long updateUser;

	@ApiModelProperty(value="类型 1为普通用户 2为物业 3为管理人员", hidden=false, required=true, dataType="Integer", example = "")
	private Integer type;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableLogic(value="0",delval="1")
	@ApiModelProperty(value="是否删除", hidden=false, required=false, dataType="Date", example = "")
	private int logDelete;

	@ApiModelProperty(value="用户ID", hidden=false, required=true,  example = "")
	private Long userId;
}
