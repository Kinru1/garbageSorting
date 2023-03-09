package com.lin.garbagesorting.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.dto.UserDto;
import com.lin.garbagesorting.entity.*;
import com.lin.garbagesorting.mapper.UserMapper;
import com.lin.garbagesorting.service.*;
import com.lin.garbagesorting.utils.SHAUtil;
import com.lin.garbagesorting.utils.SnowFlake;
import com.lin.garbagesorting.utils.StringUtil;
import com.lin.garbagesorting.vo.UserVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


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

    @Resource
    RoleOperationService roleOperationService;

    @Resource
    RoleService roleService;

    @Resource
    OperationService operationService;

    @Resource
    TenantService tenantService;

    @Resource
    OfficeService officeService;
    @Override
public UserVo login(User user) {
    User users;
    try {
        users = getOne(new UpdateWrapper<User>().eq("username", user.getUsername())
           );
    } catch (Exception e) {
        throw new RuntimeException("数据库异常");
    }

        if (!StringUtil.isNotNull(users)) {
            throw new NullPointerException("用户名不存在");
    }

//        String securePass = SaSecureUtil.aesEncrypt(Constants.LOGIN_USER_KEY, user.getPassword());
//        if (!securePass.equals(dbUser.getPassword())) {
//            throw new ServiceException("用户名或密码错误");
//        }
   // if (!BCrypt.checkpw(user.getPassword(), dbUser.getPassword()))
        if (!SHAUtil.SHA256Encrypt(user.getPassword()).equals(users.getPassword()))
    {
        throw new RuntimeException("密码错误");
    }
    // 登录
   // StpUtil.login(dbUser.getUserId());  // loginId
    //String tokenValue = StpUtil.getTokenInfo().getTokenValue();
//        LoginDTO loginDTO = new LoginDTO(dbUser, tokenValue);

    // 查询用户的菜单树（2层）
    int flag = users.getType();
    List<Operation> all = getOperations(flag);  // 水平
    List<Operation> menus = getTreeOperations(all); // 树
    // 页面的按钮权限集合
    List<Operation> auths = all.stream().filter(Operation -> Operation.getType() == 3).collect(Collectors.toList());
    return UserVo.builder().user(users).menus(menus).auths(auths).build();
}


    @Override
    public void updatePassword(UserDto userDto) throws Exception {
        User user = getOne(new UpdateWrapper<User>().eq("user_id", userDto.getUserID()));
        if (StringUtil.isNull(user)) {
            throw new Exception("未找到用户");
        }
        //boolean checkpw = BCrypt.checkpw(userDto.getPassword(), user.getPassword());
//        if (!checkpw) {
//            throw new Exception("原密码错误");
//        }
        String newPassword = userDto.getPassword();
        user.setPassword(BCrypt.hashpw(newPassword));
        updateById(user);   // 设置到数据库

    }
    public User insertUser(User user) {
        // 设置昵称
        if (!StringUtil.isNotEmpty(user.getUsername())) {
            user.setUsername("用户" + RandomStringUtils.randomAlphabetic(6));
        }
        if (!StringUtil.isNotEmpty(user.getPassword())) {
            user.setPassword(
                    SHAUtil.SHA256Encrypt("123456")); // 加密用户密码
        }

        // 雪花
        SnowFlake worker = new SnowFlake(1, 1, 1);
        user.setUserId(worker.nextId());
            save(user);
            if(user.getType()==1){
                Tenant tenant= new Tenant();
                tenant.setTenantUsername(user.getUsername());
                tenant.setTenantId(worker.nextId());
                tenantService.save(tenant);
            }else if(user.getType()==2){
                Office office = new Office();
                office.setOfUsername(user.getUsername());
                office.setOfId(worker.nextId());
                officeService.save(office);
            }else {
                System.out.println("管理员");
            }



        return user;
    }

    public List<Operation> getOperations(int type) {
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("type", type));
        List<RoleOperation> roleOperations = roleOperationService.list(new QueryWrapper<RoleOperation>().eq("role_id", role.getId()));
        List<Integer> OperationIds = roleOperations.stream().map(RoleOperation::getOperationId).collect(Collectors.toList());
        List<Operation> OperationList = operationService.list();
        List<Operation> all = new ArrayList<>();  // 水平的菜单树
        for (Integer OperationId : OperationIds) {
            OperationList.stream().filter(Operation -> Operation.getId().equals(OperationId)).findFirst()
                    .ifPresent(all::add);
        }
        return all;
    }
    private List<Operation> getTreeOperations(List<Operation> all) {
        // 菜单树 1级 -> 2级
        List<Operation> parentList = all.stream().filter(Operation -> Operation.getType() == 1
                || (Operation.getType() == 2 && Operation.getFatherId() == null)).collect(Collectors.toList());// type==1 是目录  或者  pid = null
        for (Operation Operation : parentList) {
            Integer pid = Operation.getId();
            List<Operation> level2List = all.stream().filter(Operation1 -> pid.equals(Operation1.getFatherId())).collect(Collectors.toList());// 2级菜单
            Operation.setChildren(level2List);
        }
        return parentList.stream().sorted(Comparator.comparing(Operation::getOrders)).collect(Collectors.toList());  // 排序
    }


}
