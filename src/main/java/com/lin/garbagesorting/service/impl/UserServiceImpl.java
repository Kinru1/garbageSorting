package com.lin.garbagesorting.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.mapper.UserMapper;
import com.lin.garbagesorting.service.UserService;
import com.lin.garbagesorting.utils.JwtUtils;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
//    public PageInfo getList(Integer page, Integer rows, String name) {
//        Example example = new Example(RoleDto.class);
//        //增加排序
//        example.setOrderByClause("id desc");
//        example.createCriteria().andLike("name", StringUtil.buildLikeStr(name));
//        PageHelper.startPage(page, rows);
//        return new PageInfo<>(policeMapper.selectByExample(example));
//    }



}
