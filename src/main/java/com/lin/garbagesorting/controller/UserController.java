package com.lin.garbagesorting.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.dto.UserDto;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.service.UserService;
import com.lin.garbagesorting.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RestController
@RequestMapping("user")
@Api(tags = "API基础用户管理")
public class UserController {
    @Autowired
    UserService userService;
//
//
//
//
//    @ApiOperation(value = "查询用户", notes = "查询用户", response = User.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "name", value = "姓名", required = false, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "password", value = "密码", required = false, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "phone", value = "手机号", required = false, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "sex", value = "性别", required = false, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "type", value = "类型", required = false, dataType = "int", paramType = "query")
//    })
//    @PostMapping
//    public R<String> add(@RequestBody User user, HttpServletRequest request){
//        log.info("新增员工{}",user.toString());
//        if(StringUtil.isNotNull(user.getPassword())){
//            user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        }
//        user.setStatus(1);
//        LambdaQueryWrapper<User> qWrapper = new LambdaQueryWrapper();
//        //添加过滤条件
//        qWrapper.eq(StringUtils.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());
//        if(StringUtil.isNotNull(userService.getOne(qWrapper))){
//            return R.error("用户名已存在");
//        }
//        userService.save(user);
//        return R.success("新增用户成功 ");
//    }
//
//    @ApiOperation(value = "删除用户", notes = "删除用户", response = User.class)
//    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "query")
//    @DeleteMapping
//    public R delete(Long id){
//        log.info("删除用户，id为：{}",id);
//        userService.removeById(id);
//        return R.success("用户信息删除成功");
//    }
//
//
//
//    @ApiOperation(value = "修改用户", notes = "修改用户", response = User.class)
//    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "query")
//    @PutMapping
//    public R<String> update(@RequestBody User user,HttpServletRequest request){
//        log.info(user.toString());
//        userService.updateById(user);
//        return R.success("员工信息修改成功");
//    }
//
//    @GetMapping("/page")
//    public R<Page> page(int page, int pageSize, String name){
//        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);
//
//        //构造分页构造器
//        Page pageInfo = new Page(page,pageSize);
//
//        //构造条件构造器
//        LambdaQueryWrapper<User> qWrapper = new LambdaQueryWrapper();
//        //添加过滤条件
//        qWrapper.like(StringUtils.isNotEmpty(name),User::getName,name);
//        //添加排序条件
//        qWrapper.orderByDesc(User::getUpdateTime);
//
//        //执行查询
//        userService.page(pageInfo,qWrapper);
//
//        return R.success(pageInfo);
//    }
//
//
//    @ApiOperation(value = "查询用户", notes = "查询用户", response = User.class)
//    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
//    @GetMapping("/{username}")
//    public R<User> getByUsername(@PathVariable String username){
//        LambdaQueryWrapper<User> qWrapper = new LambdaQueryWrapper();
//        //添加过滤条件
//        qWrapper.eq(StringUtils.isNotEmpty(username),User::getUsername,username);
//        User user = userService.getOne(qWrapper);
//        if(StringUtil.isNotNull(user)){
//            return R.success(user);
//        }
//        return R.error("没有查到对应用户");
//    }
//    @RequestMapping("/hello")
//    public String hello(){
//        return "1";
//    }
@PutMapping
@SaCheckPermission("user.edit")
public R update(@RequestBody User user) {
    userService.updateById(user);
    return R.success(user);
}


    @DeleteMapping("/{id}")
    @SaCheckPermission("user.delete")
    public R delete(@PathVariable Integer id) {
        userService.removeById(id);
        return R.success();
    }

    @PostMapping("/del/batch")
    @SaCheckPermission("user.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        userService.removeByIds(ids);
        return R.success();
    }

    @GetMapping
    @SaCheckPermission("user.list")
    public R findAll() {
        return R.success(userService.list());
    }

    @GetMapping("/{id}")
    @SaCheckPermission("user.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(userService.getById(id));
    }

    @GetMapping("/page")
    @SaCheckPermission("user.list")
    public R findPage(@RequestParam(defaultValue = "") String username,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().orderByDesc("id");
        queryWrapper.like(StrUtil.isNotBlank(username), "name", username);
        // and
        return R.success(userService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    @SaCheckPermission("user.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("User信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }


    @PostMapping
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public R saveUser(@RequestBody User user){
        LambdaQueryWrapper<User> qWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(user.getUsername())){
            if(StringUtil.isNotNull(qWrapper.eq(User::getUsername,user.getUsername()))){
                return  R.error("新增失败");
            }else{
                userService.insertUser(user);
            }
        }
        return R.success();
}@PostMapping("change")
    public R passwordChange(@RequestBody UserDto userDto) throws Exception {
        userService.updatePassword(userDto);
        return R.success();
    }
}