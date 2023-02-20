package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.ComplainDao;
import com.lin.dao.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:40
 */

@Service
public class ComplainService extends BaseService<ComplainDao> {

	@Autowired
	private ComplainMapper complainMapper;

	public IMapper<ComplainDao> getMapper() {
		return complainMapper;
	}
}
