package com.lin.garbagesorting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.garbagesorting.entity.Garbage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GarbageMapper extends BaseMapper<Garbage> {
    public List<Garbage> getGarbageByType(@Param("type")String type) ;


}
