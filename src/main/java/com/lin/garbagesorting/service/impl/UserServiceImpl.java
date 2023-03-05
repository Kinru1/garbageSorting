package com.lin.garbagesorting.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Operation;
import com.lin.garbagesorting.entity.Role;
import com.lin.garbagesorting.entity.RoleOperation;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.mapper.UserMapper;
import com.lin.garbagesorting.service.OperationService;
import com.lin.garbagesorting.service.RoleOperationService;
import com.lin.garbagesorting.service.RoleService;
import com.lin.garbagesorting.service.UserService;
import com.lin.garbagesorting.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



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

    @Resource
    RoleOperationService roleOperationService;

    @Resource
    RoleService roleService;

    @Resource
    OperationService operationService;

    @Override
public UserVo login(User user) {
    User dbUser;
    try {
        dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername())
           );
    } catch (Exception e) {
        throw new RuntimeException("数据库异常");
    }
    if (dbUser == null) {
        throw new RuntimeException("未找到用户");
    }
//        String securePass = SaSecureUtil.aesEncrypt(Constants.LOGIN_USER_KEY, user.getPassword());
//        if (!securePass.equals(dbUser.getPassword())) {
//            throw new ServiceException("用户名或密码错误");
//        }
   // if (!BCrypt.checkpw(user.getPassword(), dbUser.getPassword()))
        if (!user.getPassword().equals( dbUser.getPassword()))
    {
        throw new RuntimeException("用户名或密码错误");
    }
    // 登录
   // StpUtil.login(dbUser.getUserId());  // loginId
    //String tokenValue = StpUtil.getTokenInfo().getTokenValue();
//        LoginDTO loginDTO = new LoginDTO(dbUser, tokenValue);

    // 查询用户的菜单树（2层）
    int flag = dbUser.getType();
    List<Operation> all = getOperations(flag);  // 水平
    List<Operation> menus = getTreeOperations(all); // 树
    // 页面的按钮权限集合
    List<Operation> auths = all.stream().filter(Operation -> Operation.getType() == 3).collect(Collectors.toList());
    return UserVo.builder().user(dbUser).menus(menus).auths(auths).build();
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
