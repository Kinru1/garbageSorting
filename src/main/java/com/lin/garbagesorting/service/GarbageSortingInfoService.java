package com.lin.garbagesorting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.garbagesorting.entity.GarbageSortingInfo;

import java.util.List;

public interface GarbageSortingInfoService  extends IService<GarbageSortingInfo> {

    GarbageSortingInfo saveTotal(GarbageSortingInfo garbageSortingInfo);
    double getLastTotal(Integer day,String community,String garbageType);
//    double getLastTotalRecy(Integer day,String community);
//    double getLastTotalOther(Integer day,String community);
//    double getLastTotal(Integer day,String community);
//    double getLastTotal(Integer day,String community);

}
