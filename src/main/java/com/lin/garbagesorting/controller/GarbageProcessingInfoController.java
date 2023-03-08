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
import com.lin.garbagesorting.service.GarbageProcessingInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/garbageProcessingInfo")
@Api(tags = "垃圾处理信息管理")
public class GarbageProcessingInfoController {

    @Resource
    private GarbageProcessingInfoService garbageProcessingInfoService;

    @ApiOperation(value = "新增垃圾处理信息", notes = "新增垃圾处理信息")
    @PostMapping
    @SaCheckPermission("garbageProcessingInfo.add")
    public R save(@RequestBody GarbageProcessingInfo garbageProcessingInfo,@RequestParam String username) {
          //User user = StpUtil.getSession().get(LOGIN_USER_KEY);
//        garbageProcessingInfo.setUser(user.getName());
//        garbageProcessingInfo.setUserid(user.getId());
//        garbageProcessingInfo.setDate(DateUtil.today());
//        garbageProcessingInfo.setTime(DateUtil.now());
        log.info(garbageProcessingInfo.toString());
        GarbageProcessingInfo gpi= garbageProcessingInfoService.selectGPI(garbageProcessingInfo,username);
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
    @GetMapping("/page")
    @SaCheckPermission("garbageProcessingInfo.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<GarbageProcessingInfo> queryWrapper = new QueryWrapper<GarbageProcessingInfo>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
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
