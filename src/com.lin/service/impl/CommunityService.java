package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.CommunityDao;
import com.lin.dao.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:40
 */

@Service
public class CommunityService extends BaseService<CommunityDao> {

	@Autowired
	private CommunityMapper communityMapper;

	public IMapper<CommunityDao> getMapper() {
		return communityMapper;
	}
}
