package com.lin.garbagesorting.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
* @since 2023-03-13
*/
@Getter
@Setter
@ApiModel(value = "Swiper对象", description = "")
public class Swiper implements Serializable {

private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 图片路径
    @ApiModelProperty("图片路径")
    @Alias("图片路径")
    private String swiperImg;

    // 逻辑删除
    @ApiModelProperty("逻辑删除")
    @Alias("逻辑删除")
    @TableLogic(value = "0", delval = "id")
    private Integer logDelete;
}