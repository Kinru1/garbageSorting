package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.OfficeDao;
import com.lin.dao.OfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:43
 */

@Service
public class OfficeService extends BaseService<OfficeDao> {

	@Autowired
	private OfficeMapper officeMapper;

	public IMapper<OfficeDao> getMapper() {
		return officeMapper;
	}
}
