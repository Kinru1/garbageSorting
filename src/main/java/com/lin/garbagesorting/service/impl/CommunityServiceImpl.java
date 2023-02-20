package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Community;
import com.lin.garbagesorting.mapper.CommunityMapper;
import com.lin.garbagesorting.service.CommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper,Community> implements CommunityService {

	@Resource
	private CommunityMapper communityMapper;

	public BaseMapper<Community> getMapper() {
		return communityMapper;
	}
}
