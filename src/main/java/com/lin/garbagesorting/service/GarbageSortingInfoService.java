package com.lin.garbagesorting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.garbagesorting.entity.GarbageSortingInfo;

public interface GarbageSortingInfoService  extends IService<GarbageSortingInfo> {

    GarbageSortingInfo saveTotal(GarbageSortingInfo garbageSortingInfo);
}
