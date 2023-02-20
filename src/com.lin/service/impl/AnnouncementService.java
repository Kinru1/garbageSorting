package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.AnnouncementDao;
import com.lin.dao.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:38
 */

@Service
public class AnnouncementService extends BaseService<AnnouncementDao> {

	@Autowired
	private AnnouncementMapper announcementMapper;

	public IMapper<AnnouncementDao> getMapper() {
		return announcementMapper;
	}
}
