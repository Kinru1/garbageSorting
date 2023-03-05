package com.lin.garbagesorting.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* <p>
* 
* </p>
*
* @author 
* @since 2023-03-05
*/
@Getter
@Setter
@ApiModel(value = "Roles对象", description = "")
public class Role implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer id;

    // 名字
    @ApiModelProperty("名字")
    @Alias("名字")
    private String name;

    // 逻辑删除
    @ApiModelProperty("逻辑删除")
    @Alias("逻辑删除")
    @TableLogic(value = "0", delval = "id")
    private Integer logDelete;

    // 类型 1为普通用户 2为物业 3为管理人员
    @ApiModelProperty("类型 1为普通用户 2为物业 3为管理人员")
    @Alias("类型 1为普通用户 2为物业 3为管理人员")
    private Integer type;
}