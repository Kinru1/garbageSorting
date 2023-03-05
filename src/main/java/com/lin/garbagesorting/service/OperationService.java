package com.lin.garbagesorting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.garbagesorting.entity.Operation;

import java.util.List;


public interface OperationService extends IService<Operation> {
    List<Operation> tree();
    void deleteOperation(Integer id);
}
