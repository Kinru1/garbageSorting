package com.lin.garbagesorting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.garbagesorting.entity.Garbage;

import java.util.List;
import java.util.Map;

public interface GarbageService  extends IService<Garbage> {
    public List<Map<String, Object>> getGarbageList() ;
}
