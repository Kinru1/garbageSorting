package com.lin.garbagesorting.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
@ApiModel(value = "Operation对象", description = "")
public class Operation implements Serializable {

private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 菜单名
    @ApiModelProperty("菜单名")
    @Alias("菜单名")
    private String name;

    // 路径
    @ApiModelProperty("路径")
    @Alias("路径")
    private String path;

    // 图标
    @ApiModelProperty("图标")
    @Alias("图标")
    private String icon;

    // 页面路径
    @ApiModelProperty("页面路径")
    @Alias("页面路径")
    private String page;

    // 父级菜单ID
    @ApiModelProperty("父级菜单ID")
    @Alias("父级菜单ID")
    private Integer fatherId;

    // 逻辑删除默认为0
    @ApiModelProperty("逻辑删除默认为0")
    @Alias("逻辑删除默认为0")
    @TableLogic(value = "0", delval = "1")
    private Integer logDelete;

    // 操作
    @ApiModelProperty("操作")
    @Alias("操作")
    private String operate;

    private Integer type;

    private Integer orders;
    @TableField(exist = false)
    private List<Operation> children;
}