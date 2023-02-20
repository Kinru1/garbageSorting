package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.GarbageSiteDao;
import com.lin.dao.GarbageSiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:43
 */

@Service
public class GarbageSiteService extends BaseService<GarbageSiteDao> {

	@Autowired
	private GarbageSiteMapper garbageSiteMapper;

	public IMapper<GarbageSiteDao> getMapper() {
		return garbageSiteMapper;
	}
}
