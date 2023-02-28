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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author 
* @since 2023-02-28
*/
@RestController
@RequestMapping("/garbageSite")
public class GarbageSiteController {

    @Resource
    private GarbageSiteService  garbageSiteService;

    @PostMapping
    @SaCheckPermission("garbageSite.add")
    public R save(@RequestBody GarbageSite garbageSite) {
//        User user = SessionUtils.getUser();
//        garbageSite.setUser(user.getName());
//        garbageSite.setUserid(user.getId());
//        garbageSite.setDate(DateUtil.today());
//        garbageSite.setTime(DateUtil.now());
        garbageSiteService.save(garbageSite);
        return R.success();
    }

    @PutMapping
    @SaCheckPermission("garbageSite.edit")
    public R update(@RequestBody GarbageSite garbageSite) {
        garbageSiteService.updateById(garbageSite);
        return R.success();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("garbageSite.delete")
    public R delete(@PathVariable Integer id) {
        garbageSiteService.removeById(id);
        return R.success();
    }

    @PostMapping("/del/batch")
    @SaCheckPermission("garbageSite.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        garbageSiteService.removeByIds(ids);
        return R.success();
    }

    @GetMapping
    @SaCheckPermission("garbageSite.list")
    public R findAll() {
        return R.success(garbageSiteService.list());
    }

    @GetMapping("/{id}")
    @SaCheckPermission("garbageSite.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(garbageSiteService.getById(id));
    }

    @GetMapping("/page")
    @SaCheckPermission("garbageSite.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<GarbageSite> queryWrapper = new QueryWrapper<GarbageSite>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(garbageSiteService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
//    @GetMapping("/export")
//    @SaCheckPermission("garbageSite.export")
//    public void export(HttpServletResponse response) throws Exception {
//        // 从数据库查询出所有的数据
//        List<GarbageSite> list = garbageSiteService.list();
//        // 在内存操作，写出到浏览器
//        ExcelWriter writer = ExcelUtil.getWriter(true);
//
//        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
//        writer.write(list, true);
//
//        // 设置浏览器响应的格式
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//        String fileName = URLEncoder.encode("GarbageSite信息表", "UTF-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
//
//        ServletOutputStream out = response.getOutputStream();
//        writer.flush(out, true);
//        out.close();
//        writer.close();
//
//    }
//
//    /**
//    * excel 导入
//    * @param file
//    * @throws Exception
//    */
//    @PostMapping("/import")
//    @SaCheckPermission("garbageSite.import")
//    public R imp(MultipartFile file) throws Exception {
//        InputStream inputStream = file.getInputStream();
//        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<GarbageSite> list = reader.readAll(GarbageSite.class);
//
//        garbageSiteService.saveBatch(list);
//        return R.success();
//    }

}
