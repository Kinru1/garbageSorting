package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.GarbageProcessingInfo;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.service.GarbageProcessingInfoService;
import com.lin.garbagesorting.service.OfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/garbageProcessingInfo")
@CrossOrigin
@Api(tags = "垃圾处理信息管理")
public class GarbageProcessingInfoController {

    @Resource
    private GarbageProcessingInfoService garbageProcessingInfoService;

    @Resource
    private OfficeService officeService;



    @ApiOperation(value = "新增垃圾处理信息", notes = "新增垃圾处理信息")
    @PostMapping
    @SaCheckPermission("garbageProcessingInfo.addMy")
    public R saveMy(@RequestBody GarbageProcessingInfo garbageProcessingInfo) {
        String username = garbageProcessingInfo.getUsername();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       LocalDate date = LocalDate.parse(garbageProcessingInfo.getDate(), formatter);
        log.info(username);
        // 将 LocalDate 转换为 LocalDateTime，时间部分设置为午夜（00:00:00）
        LocalDateTime dateTime = date.atStartOfDay();

        GarbageProcessingInfo gpi= garbageProcessingInfoService.selectGPI(garbageProcessingInfo,username);
        gpi.setGpDay(dateTime);
        garbageProcessingInfoService.save(gpi);
        return R.success();
    }

    @ApiOperation(value = "修改垃圾处理信息", notes = "修改垃圾处理信息")
    @PutMapping
    @SaCheckPermission("garbageProcessingInfo.edit")
    public R update(@RequestBody GarbageProcessingInfo garbageProcessingInfo) {
        garbageProcessingInfoService.updateById(garbageProcessingInfo);
        return R.success();
    }

    @ApiOperation(value = "删除垃圾处理信息", notes = "删除垃圾处理信息")
    @DeleteMapping("/{id}")
    @SaCheckPermission("garbageProcessingInfo.delete")
    public R delete(@PathVariable Integer id) {
        garbageProcessingInfoService.removeById(id);
        return R.success();
    }

    @ApiOperation(value = "批量删除垃圾处理信息", notes = "批量删除垃圾处理信息")
    @PostMapping("/del/batch")
    @SaCheckPermission("garbageProcessingInfo.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        garbageProcessingInfoService.removeByIds(ids);
        return R.success();
    }



    @ApiOperation(value = "所有垃圾处理信息", notes = "所有垃圾处理信息")
    @GetMapping
    @SaCheckPermission("garbageProcessingInfo.list")
    public R findAll() {
        return R.success(garbageProcessingInfoService.list());
    }



    @ApiOperation(value = "精准垃圾处理信息", notes = "精准查询垃圾处理信息")
    @GetMapping("/{id}")
    @SaCheckPermission("garbageProcessingInfo.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(garbageProcessingInfoService.getById(id));
    }


    @ApiOperation(value = "垃圾处理信息分页", notes = "垃圾处理信息分页")
    @GetMapping("/myPage")
    @SaCheckPermission("garbageProcessingInfo.list")
    public R findPage(@RequestParam(defaultValue = "") String username,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<GarbageProcessingInfo> queryWrapper = new QueryWrapper<GarbageProcessingInfo>().orderByDesc("id");
        QueryWrapper<Office> qw2 = new QueryWrapper();
        String community = officeService.getOne(qw2.eq("of_username",username)).getOfCommunity();
        queryWrapper.like(!"".equals(username), "name", username).eq("gp_community",community);
        return R.success(garbageProcessingInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


    @ApiOperation(value = "垃圾自己小区处理信息分页", notes = "垃圾自己小区处理信息分页")

    @GetMapping("/page")
    @SaCheckPermission("garbageProcessingInfo.allList")
    public R findAllPage(@RequestParam(defaultValue = "") String community,
                      @RequestParam Integer pageNum,
                      @RequestParam Integer pageSize) {
        QueryWrapper<GarbageProcessingInfo> queryWrapper = new QueryWrapper<GarbageProcessingInfo>().orderByDesc("id");
        queryWrapper.like(!"".equals(community), "name", community);
        return R.success(garbageProcessingInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @ApiOperation(value = "导出垃圾处理信息", notes = "导出垃圾处理信息")
    @GetMapping("/export")
    @SaCheckPermission("garbageProcessingInfo.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<GarbageProcessingInfo> list = garbageProcessingInfoService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("GarbageProcessingInfo信息表", "UTF-8");
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
    @ApiOperation(value = "导入垃圾处理信息", notes = "导入垃圾处理信息")
    @PostMapping("/import")
    @SaCheckPermission("garbageProcessingInfo.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<GarbageProcessingInfo> list = reader.readAll(GarbageProcessingInfo.class);

        garbageProcessingInfoService.saveBatch(list);
        return R.success();
    }

}
