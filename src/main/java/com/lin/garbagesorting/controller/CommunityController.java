package com.lin.garbagesorting.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Community;
import com.lin.garbagesorting.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "小区管理")
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Resource
    private CommunityService communityService;

    @ApiOperation(value = "新增小区", notes = "新增小区")
    @PostMapping
    @SaCheckPermission("community.add")
    public R save(@RequestBody Community community) {
        communityService.save(community);
        return R.success();
    }

    @ApiOperation(value = "修改小区", notes = "修改小区")
    @PutMapping
    @SaCheckPermission("community.edit")
    public R update(@RequestBody Community community) {
        communityService.updateById(community);
        return R.success();
    }

    @ApiOperation(value = "删除小区", notes = "删除小区")
    @DeleteMapping("/{id}")
    @SaCheckPermission("community.delete")
    public R delete(@PathVariable("id") Integer id) {
        communityService.removeById(id);
        return R.success();
    }

    @ApiOperation(value = "批量删除小区", notes = "批量删除小区")
    @PostMapping("/del/batch")
    @SaCheckPermission("community.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        communityService.removeByIds(ids);
        return R.success();
    }

    @ApiOperation(value = "所有小区", notes = "所有小区")
    @GetMapping
    @SaCheckPermission("community.list")
    public R findAll() {
        return R.success(communityService.list());
    }

    @ApiOperation(value = "精准查询小区", notes = "精准查询小区")
    @GetMapping("/{id}")
    @SaCheckPermission("community.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(communityService.getById(id));
    }


    @ApiOperation(value = "分页查询小区", notes = "分页查询小区")
    @GetMapping("/page")
    @SaCheckPermission("community.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Community> queryWrapper = new QueryWrapper<Community>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(communityService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @ApiOperation(value = "导出小区", notes = "导出小区")
    @GetMapping("/export")
    @SaCheckPermission("community.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Community> list = communityService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Community信息表", "UTF-8");
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
    @ApiOperation(value = "导入小区", notes = "导入小区")
    @PostMapping("/import")
    @SaCheckPermission("community.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Community> list = reader.readAll(Community.class);

        communityService.saveBatch(list);
        return R.success();
    }

}
