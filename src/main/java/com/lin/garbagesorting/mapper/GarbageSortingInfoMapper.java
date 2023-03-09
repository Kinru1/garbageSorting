package com.lin.garbagesorting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.garbagesorting.entity.GarbageSortingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GarbageSortingInfoMapper extends BaseMapper<GarbageSortingInfo> {

    double getLastTotal(@Param("day") Integer day, @Param("community") String community, @Param("garbageType") String garbageType);
    double getAllLastTotal(@Param("day") Integer day, @Param("garbageType") String garbageType);
}

