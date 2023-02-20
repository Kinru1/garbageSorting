package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.GarbageDao;
import com.lin.dao.GarbageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:40
 */

@Service
public class GarbageService extends BaseService<GarbageDao> {

	@Autowired
	private GarbageMapper garbageMapper;

	public IMapper<GarbageDao> getMapper() {
		return garbageMapper;
	}
}
