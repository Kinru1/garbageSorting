package com.lin.garbagesorting.uapi;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Garbage;
import com.lin.garbagesorting.service.GarbageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "Uniapp垃圾管理")
@RestController
@RequestMapping("/unigarbage")
public class UniGarbageApi {

    @Resource
    private GarbageService garbageService;


    @ApiOperation(value = "模糊查询垃圾", notes = "模糊查询垃圾")
    @GetMapping("/{name}")
    @SaCheckPermission("garbage.key")
    public List<Garbage> findListByKey(@PathVariable String name) {
        QueryWrapper<Garbage> queryWrapper = new QueryWrapper<Garbage>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "garbage_name", name);
        List<Garbage> garhage = garbageService.list(queryWrapper);
        return garhage;
    }


    @GetMapping("/type")
    @SaCheckPermission("garbage.type")
    public List<Map<String, Object>> getGarbageByType() {
        return garbageService.getGarbageList();
    }
}
