package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.entity.Tenant;
import com.lin.garbagesorting.mapper.OfficeMapper;
import com.lin.garbagesorting.service.OfficeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeMapper,Office> implements OfficeService {

	@Resource
	private OfficeMapper officeMapper;

	public BaseMapper<Office> getMapper() {
		return officeMapper;
	}

	public List<Tenant> getAllTenant(String username){
		List<Tenant> tenants = officeMapper.getAllTenant(username);
		return tenants;
	}
}
