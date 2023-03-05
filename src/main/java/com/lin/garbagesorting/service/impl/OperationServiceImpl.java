package com.lin.garbagesorting.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lin.garbagesorting.entity.Operation;
import com.lin.garbagesorting.entity.RoleOperation;
import com.lin.garbagesorting.mapper.OperationMapper;
import com.lin.garbagesorting.mapper.RoleOperationMapper;
import com.lin.garbagesorting.service.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation> implements OperationService {
    @Resource
    RoleOperationMapper roleOperationMapper;

    @Override
    public List<Operation> tree() {
        List<Operation> allData = list();

        return childrenTree(null, allData); // 从第一级开始往下递归获取树
    }

    // 删除权限
    @Override
    @Transactional
    public void deleteOperation(Integer id) {
        // 删除 role_Operation表数据
        roleOperationMapper.delete(new UpdateWrapper<RoleOperation>().eq("Operation_id", id));
        remove(new UpdateWrapper<Operation>().set("pid", id)); // 删除子菜单
        removeById(id);
    }

    // 递归生成树
    private List<Operation> childrenTree(Integer pid, List<Operation> allData) {
        List<Operation> list = new ArrayList<>();
        for (Operation Operation : allData) {
            if (Objects.equals(Operation.getFatherId(), pid)) {  // null, 一级
                list.add(Operation);
                List<Operation> childrenTree = childrenTree(Operation.getId(), allData);  // 递归调用， 摘取二级节点、三级、四级...
                Operation.setChildren(childrenTree);
            }
        }
        return list;
    }
}
