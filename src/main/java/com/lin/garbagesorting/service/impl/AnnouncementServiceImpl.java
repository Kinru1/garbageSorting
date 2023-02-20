package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Announcement;
import com.lin.garbagesorting.mapper.AnnouncementMapper;
import com.lin.garbagesorting.service.AnnouncementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper,Announcement> implements AnnouncementService {

	@Resource
	private AnnouncementMapper announcementMapper;

	public BaseMapper<Announcement> getMapper() {
		return announcementMapper;
	}
}
