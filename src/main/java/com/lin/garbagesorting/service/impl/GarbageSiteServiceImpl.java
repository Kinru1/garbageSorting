package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.GarbageSite;
import com.lin.garbagesorting.mapper.GarbageSiteMapper;
import com.lin.garbagesorting.service.GarbageSiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GarbageSiteServiceImpl extends ServiceImpl<GarbageSiteMapper,GarbageSite> implements GarbageSiteService {

	@Resource
	private GarbageSiteMapper garbageSiteMapper;

	public BaseMapper<GarbageSite> getMapper() {
		return garbageSiteMapper;
	}
}
