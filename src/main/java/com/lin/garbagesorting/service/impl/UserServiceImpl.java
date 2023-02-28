package com.lin.garbagesorting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.mapper.UserMapper;
import com.lin.garbagesorting.service.UserService;
import com.lin.garbagesorting.utils.JwtUtils;
import com.lin.garbagesorting.vo.LoginVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

//    @Override
//    public String login( loginvo) {
//        //获取账号和密码
//        String username = loginvo.getUsername();
//        String password = loginvo.getPassword();
//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//            throw new MyException(20010, "账号和密码不能为空!");
//        }
//        //判断账号和密码是否存在
//        QueryWrapper<LoginVo> wrapper = new QueryWrapper<>();
//        wrapper.eq("username", username);
//        LoginVo loginvo = baseMapper.selectOne(wrapper);
//        if (ObjectUtils.isEmpty(ucenterMember)) {
//            throw new MyException(20011, "账号不存在，请重新输入!");
//        }
//        //判断该用户是否被禁用
//        Boolean isDisabled = ucenterMember.getIsDisabled();
//        if (isDisabled) {
//            throw new MyException(20013, "该账号已禁用!");
//        }
//        //判断密码是否正确
//        //密码存储肯定是加密的，实际开发中数据库不会存储明文密码
//        //先将输入的密码加密，再和数据库密码比较
//        //MD5加密
//        if (!MD5.encrypt(password).equals(realPassword)) {
//            throw new MyException(20012, "密码错误，请重新输入!");
//        }
//        //登录成功,返回token(通过查出来的用户数据去生成token)
//        return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
//    }

}
