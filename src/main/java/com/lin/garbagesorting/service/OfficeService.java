package com.lin.garbagesorting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.entity.Tenant;

import java.util.List;

public interface OfficeService  extends IService<Office> {
    List<Tenant> getAllTenant (String username);
}
