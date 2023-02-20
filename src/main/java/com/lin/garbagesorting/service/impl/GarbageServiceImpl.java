package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Garbage;
import com.lin.garbagesorting.mapper.GarbageMapper;
import com.lin.garbagesorting.service.GarbageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GarbageServiceImpl extends ServiceImpl<GarbageMapper,Garbage> implements GarbageService {

	@Resource
	private GarbageMapper garbageMapper;

	public BaseMapper<Garbage> getMapper() {
		return garbageMapper;
	}
}
