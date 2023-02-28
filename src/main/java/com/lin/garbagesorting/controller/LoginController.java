package com.lin.garbagesorting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.service.UserService;
import com.lin.garbagesorting.utils.JwtUtils;
import com.lin.garbagesorting.vo.LoginVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@Controller
@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserService userService;

    @ApiOperation(value ="登录接口")
    public R login(@RequestBody User users){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",users.getUsername());
        wrapper.eq("password",users.getPassword());
        User user = userService.getOne(wrapper);
        int isDisabled = user.getStatus();
        System.out.println(user);

        //return JwtUtils.getJwtToken(user.getUsername(),user.getType());
        return null;
    }


}
