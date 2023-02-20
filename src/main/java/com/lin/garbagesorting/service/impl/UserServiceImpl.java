package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.mapper.UserMapper;
import com.lin.garbagesorting.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

	@Resource
	private UserMapper userMapper;

	public BaseMapper<User> getMapper() {
		return userMapper;
	}
}
