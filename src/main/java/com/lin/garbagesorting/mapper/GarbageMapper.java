package com.lin.garbagesorting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.garbagesorting.entity.Garbage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GarbageMapper extends BaseMapper<Garbage> {

}
