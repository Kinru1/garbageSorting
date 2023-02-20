package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Tenant;
import com.lin.garbagesorting.mapper.TenantMapper;
import com.lin.garbagesorting.service.TenantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper,Tenant> implements TenantService {

	@Resource
	private TenantMapper tenantMapper;

	public BaseMapper<Tenant> getMapper() {
		return tenantMapper;
	}
}
