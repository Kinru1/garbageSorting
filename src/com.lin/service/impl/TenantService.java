package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.TenantDao;
import com.lin.dao.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/14 17:24
 */

@Service
public class TenantService extends BaseService<TenantDao> {

	@Autowired
	private TenantMapper tenantMapper;

	public IMapper<TenantDao> getMapper() {
		return tenantMapper;
	}
}
