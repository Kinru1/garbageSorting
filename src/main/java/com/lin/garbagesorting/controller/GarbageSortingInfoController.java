package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.GarbageSortingInfo;
import com.lin.garbagesorting.service.GarbageSortingInfoService;
import com.lin.garbagesorting.utils.SnowFlake;
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

@Api(tags = "垃圾分类信息管理")
@RestController
@RequestMapping("/garbageSortingInfo")
public class GarbageSortingInfoController {

    @Resource
    private GarbageSortingInfoService garbageSortingInfoService;

    @ApiOperation(value = "添加垃圾分类信息", notes = "添加垃圾分类信息")
    @PostMapping
    @SaCheckPermission("garbageSortingInfo.add")
    public R save(@RequestBody GarbageSortingInfo garbageSortingInfo) {
        GarbageSortingInfo GSI = garbageSortingInfoService.saveTotal(garbageSortingInfo);
        SnowFlake worker = new SnowFlake(2, 2, 2);
        GSI.setGsId(worker.nextId());
        garbageSortingInfoService.save(GSI);
        return R.success();
    }



    @ApiOperation(value = "修改垃圾分类信息", notes = "修改垃圾分类信息")
    @PutMapping
    @SaCheckPermission("garbageSortingInfo.edit")
    public R update(@RequestBody GarbageSortingInfo garbageSortingInfo) {
        GarbageSortingInfo GSI = garbageSortingInfoService.saveTotal(garbageSortingInfo);
        garbageSortingInfoService.updateById(GSI);
        return R.success();
    }

    @ApiOperation(value = "删除垃圾分类信息", notes = "删除垃圾分类信息")
    @DeleteMapping("/{id}")
    @SaCheckPermission("garbageSortingInfo.delete")
    public R delete(@PathVariable Integer id) {
        garbageSortingInfoService.removeById(id);
        return R.success();
    }
    @ApiOperation(value = "批量删除垃圾分类信息", notes = "批量删除垃圾分类信息")
    @PostMapping("/del/batch")
    @SaCheckPermission("garbageSortingInfo.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        garbageSortingInfoService.removeByIds(ids);
        return R.success();
    }


    @ApiOperation(value = "查询所有分类信息", notes = "查询所有分类信息")
    @GetMapping
    @SaCheckPermission("garbageSortingInfo.list")
    public R findAll() {
        return R.success(garbageSortingInfoService.list());
    }

    @ApiOperation(value = "根据ID查询垃圾分类信息", notes = "根据ID查询垃圾分类信息")
    @GetMapping("/{id}")
    @SaCheckPermission("garbageSortingInfo.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(garbageSortingInfoService.getById(id));
    }





    @ApiOperation(value="查询最近的垃圾分类", notes = "根据ID查询垃圾分类信息")
    @GetMapping("/selectLast")
    @SaCheckPermission("garbageSortingInfo.selectLast")
    public R findLastGarbageInfo(@RequestParam Integer day,@RequestParam String community,@RequestParam String garbageType) {
        double LastTotalGarbageInfo = garbageSortingInfoService.getLastTotal(day,community,garbageType);
        return R.success(LastTotalGarbageInfo);
    }





    @ApiOperation(value = "分页垃圾分类信息", notes = "分页垃圾分类信息")
    @GetMapping("/page")
    @SaCheckPermission("garbageSortingInfo.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<GarbageSortingInfo> queryWrapper = new QueryWrapper<GarbageSortingInfo>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(garbageSortingInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


    @ApiOperation(value = "导出垃圾分类信息", notes = "导出垃圾分类信息")
    @GetMapping("/export")
    @SaCheckPermission("garbageSortingInfo.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<GarbageSortingInfo> list = garbageSortingInfoService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("GarbageSortingInfo信息表", "UTF-8");
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
    @ApiOperation(value = "导入垃圾分类信息", notes = "导入垃圾分类信息")
    @PostMapping("/import")
    @SaCheckPermission("garbageSortingInfo.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<GarbageSortingInfo> list = reader.readAll(GarbageSortingInfo.class);

        garbageSortingInfoService.saveBatch(list);
        return R.success();
    }

}
