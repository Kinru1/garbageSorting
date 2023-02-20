package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.GarbageProcessingInfoDao;
import com.lin.dao.GarbageProcessingInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:41
 */

@Service
public class GarbageProcessingInfoService extends BaseService<GarbageProcessingInfoDao> {

	@Autowired
	private GarbageProcessingInfoMapper garbageProcessingInfoMapper;

	public IMapper<GarbageProcessingInfoDao> getMapper() {
		return garbageProcessingInfoMapper;
	}
}
