package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.GarbageSortingInfoDao;
import com.lin.dao.GarbageSortingInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:42
 */

@Service
public class GarbageSortingInfoService extends BaseService<GarbageSortingInfoDao> {

	@Autowired
	private GarbageSortingInfoMapper garbageSortingInfoMapper;

	public IMapper<GarbageSortingInfoDao> getMapper() {
		return garbageSortingInfoMapper;
	}
}
