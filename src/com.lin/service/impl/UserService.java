package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.UserDao;
import com.lin.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/14 17:13
 */

@Service
public class UserService extends BaseService<UserDao> {

	@Autowired
	private UserMapper userMapper;

	public IMapper<UserDao> getMapper() {
		return userMapper;
	}
}
