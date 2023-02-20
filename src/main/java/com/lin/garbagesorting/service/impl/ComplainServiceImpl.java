package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Complain;
import com.lin.garbagesorting.mapper.ComplainMapper;
import com.lin.garbagesorting.service.ComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComplainServiceImpl extends ServiceImpl<ComplainMapper,Complain> implements ComplainService {

	@Resource
	private ComplainMapper complainMapper;

	public BaseMapper<Complain> getMapper() {
		return complainMapper;
	}
}
