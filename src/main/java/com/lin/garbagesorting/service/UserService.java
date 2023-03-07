package com.lin.garbagesorting.service;


import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.protobuf.ServiceException;
import com.lin.garbagesorting.dto.UserDto;
import com.lin.garbagesorting.entity.Operation;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.vo.UserVo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface UserService  extends IService<User> {
    UserVo login(User user);
    List<Operation> getOperations(int type);
    User insertUser(User user);
    void updatePassword(UserDto userDto) throws Exception;

}
