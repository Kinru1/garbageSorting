package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Garbage;
import com.lin.garbagesorting.service.GarbageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
@Api(tags = "垃圾管理")
@RestController
@RequestMapping("/garbage")
public class GarbageController {

    @Resource
    private GarbageService garbageService;


    @ApiOperation(value = "新增垃圾", notes = "新增垃圾")
    @PostMapping
    @SaCheckPermission("garbage.add")
    public R save(@RequestBody Garbage garbage) {
        garbageService.save(garbage);
        return R.success();
    }
    @GetMapping("/type")
    @SaCheckPermission("garbage.type")
    public List<Map<String, Object>> getGarbageByType() {
        return garbageService.getGarbageList();
    }

    @ApiOperation(value = "修改垃圾", notes = "修改垃圾")
    @PutMapping
    @SaCheckPermission("garbage.edit")
    public R update(@RequestBody Garbage garbage) {
        garbageService.updateById(garbage);
        return R.success();
    }

    @ApiOperation(value = "精准查询垃圾", notes = "精准查询垃圾")
    @DeleteMapping("/{id}")
    @SaCheckPermission("garbage.delete")
    public R delete(@PathVariable Integer id) {
        garbageService.removeById(id);
        return R.success();
    }





    @ApiOperation(value = "批量删除垃圾", notes = "批量删除垃圾")
    @PostMapping("/del/batch")
    @SaCheckPermission("garbage.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        garbageService.removeByIds(ids);
        return R.success();
    }


    @ApiOperation(value = "所有垃圾", notes = "所有垃圾")
    @GetMapping
    @SaCheckPermission("garbage.list")
    public R findAll() {
        List<Garbage>  garbage= garbageService.list();
        //       List<Garbage> garbageList = garbage.stream()
        List<Garbage> modifiedList = garbageService.list().stream()
                .map(obj -> {
                    // 对象的属性进行替换
                    log.info(obj.getGarbageImg());
                    return obj;
                })
                .collect(Collectors.toList());
        return R.success(garbageService.list());
    }


    @ApiOperation(value = "精准查询垃圾", notes = "精准查询垃圾")
    @GetMapping("/{id}")
    @SaCheckPermission("garbage.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(garbageService.getById(id));
    }

    @ApiOperation(value = "分页查询垃圾", notes = "分页查询垃圾")
    @GetMapping("/page")
    @SaCheckPermission("garbage.list")
    public R findPage(@RequestParam(defaultValue = "") String garbageName,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Garbage> queryWrapper = new QueryWrapper<Garbage>().orderByDesc("id");
        queryWrapper.like(!"".equals(garbageName), "name", garbageName);
        return R.success(garbageService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


    @ApiOperation(value = "导出垃圾", notes = "导出垃圾")
    @GetMapping("/export")
    @SaCheckPermission("garbage.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Garbage> list = garbageService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Garbage信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
    * excel 导入
    * @param file
    * @throws Exception
    */
    @ApiOperation(value = "导入垃圾", notes = "导入垃圾")
    @PostMapping("/import")
    @SaCheckPermission("garbage.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Garbage> list = reader.readAll(Garbage.class);

        garbageService.saveBatch(list);
        return R.success();
    }





}
