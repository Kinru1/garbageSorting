package com.lin.garbagesorting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.entity.Tenant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfficeMapper extends BaseMapper<Office> {
    List<Tenant> getAllTenant (@Param("username") String username);

}
