package com.lin.garbagesorting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
@Data
public class RoleOperation implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer roleId;
    private Integer operationId;
}
