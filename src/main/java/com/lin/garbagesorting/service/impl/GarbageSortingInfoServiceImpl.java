package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.GarbageSortingInfo;
import com.lin.garbagesorting.mapper.GarbageSortingInfoMapper;
import com.lin.garbagesorting.service.GarbageSiteService;
import com.lin.garbagesorting.service.GarbageSortingInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GarbageSortingInfoServiceImpl extends ServiceImpl<GarbageSortingInfoMapper,GarbageSortingInfo> implements GarbageSortingInfoService {

	@Resource
	private GarbageSortingInfoMapper garbageSortingInfoMapper;

	public BaseMapper<GarbageSortingInfo> getMapper() {
		return garbageSortingInfoMapper;
	}
}
