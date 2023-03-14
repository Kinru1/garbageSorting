package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.GarbageSite;
import com.lin.garbagesorting.service.GarbageSiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "垃圾站点管理")
@RestController
@RequestMapping("/garbageSite")
public class GarbageSiteController {

    @Resource
    private GarbageSiteService  garbageSiteService;

    @ApiOperation(value = "新增垃圾站点", notes = "新增垃圾站点")
    @PostMapping
    @SaCheckPermission("garbageSite.add")
    public R save(@RequestBody GarbageSite garbageSite) {

        garbageSiteService.save(garbageSite);
        return R.success();
    }

    @ApiOperation(value = "修改垃圾站点", notes = "修改垃圾站点")
    @PutMapping
    @SaCheckPermission("garbageSite.edit")
    public R update(@RequestBody GarbageSite garbageSite) {
        garbageSiteService.updateById(garbageSite);
        return R.success();
    }


    @ApiOperation(value = "删除垃圾站点", notes = "删除垃圾站点")
    @DeleteMapping("/{id}")
    @SaCheckPermission("garbageSite.delete")
    public R delete(@PathVariable Integer id) {
        garbageSiteService.removeById(id);
        return R.success();
    }


    @ApiOperation(value = "批量删除垃圾站点", notes = "批量删除垃圾站点")
    @PostMapping("/del/batch")
    @SaCheckPermission("garbageSite.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        garbageSiteService.removeByIds(ids);
        return R.success();
    }


    @ApiOperation(value = "所有垃圾站点", notes = "所有垃圾站点")
    @GetMapping
    @SaCheckPermission("garbageSite.list")
    public R findAll() {
        return R.success(garbageSiteService.list());
    }


    @ApiOperation(value = "ID查垃圾站点", notes = "ID查垃圾站点")
    @GetMapping("/{id}")
    @SaCheckPermission("garbageSite.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(garbageSiteService.getById(id));
    }


    @ApiOperation(value = "分页垃圾站点", notes = "分页垃圾站点")
    @GetMapping("/page")
    @SaCheckPermission("garbageSite.list")
    public R findPage(@RequestParam(defaultValue = "") String gsLocation,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<GarbageSite> queryWrapper = new QueryWrapper<GarbageSite>().orderByDesc("id");
        queryWrapper.like(!"".equals(gsLocation), "gs_location", gsLocation);
        return R.success(garbageSiteService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @ApiOperation(value = "导出垃圾站点", notes = "导出垃圾站点")
    @GetMapping("/export")
    @SaCheckPermission("garbageSite.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<GarbageSite> list = garbageSiteService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("GarbageSite信息表", "UTF-8");
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
    @ApiOperation(value = "导入垃圾站点", notes = "导入垃圾站点")
    @PostMapping("/import")
    @SaCheckPermission("garbageSite.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<GarbageSite> list = reader.readAll(GarbageSite.class);

        garbageSiteService.saveBatch(list);
        return R.success();
    }

}
