package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.GarbageProcessingInfo;
import com.lin.garbagesorting.mapper.GarbageProcessingInfoMapper;
import com.lin.garbagesorting.service.GarbageProcessingInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GarbageProcessingInfoServiceImpl extends ServiceImpl<GarbageProcessingInfoMapper,GarbageProcessingInfo> implements GarbageProcessingInfoService {

	@Resource
	private GarbageProcessingInfoMapper garbageProcessingInfoMapper;

	public BaseMapper<GarbageProcessingInfo> getMapper() {
		return garbageProcessingInfoMapper;
	}
}
