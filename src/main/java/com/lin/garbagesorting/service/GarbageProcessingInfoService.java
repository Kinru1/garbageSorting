package com.lin.garbagesorting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.garbagesorting.entity.GarbageProcessingInfo;

public interface GarbageProcessingInfoService  extends IService<GarbageProcessingInfo> {

     GarbageProcessingInfo selectGPI(GarbageProcessingInfo garbageProcessingInfo,String username);
}
