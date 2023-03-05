package com.lin.garbagesorting.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.garbagesorting.entity.Operation;
import com.lin.garbagesorting.entity.RoleOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Mapper
public interface OperationMapper extends BaseMapper<Operation> {



}
