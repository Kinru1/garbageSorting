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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/garbage")
public class GarbageController {

    @Resource
    private GarbageService garbageService;


    @PostMapping
    @SaCheckPermission("garbage.add")
    public R save(@RequestBody Garbage garbage) {
//        User user = SessionUtils.getUser();
//        garbage.setUser(user.getName());
//        garbage.setUserid(user.getId());
//        garbage.setDate(DateUtil.today());
//        garbage.setTime(DateUtil.now());
        garbageService.save(garbage);
        return R.success();
    }

    @PutMapping
    @SaCheckPermission("garbage.edit")
    public R update(@RequestBody Garbage garbage) {
        garbageService.updateById(garbage);
        return R.success();
    }


    @DeleteMapping("/{id}")
    @SaCheckPermission("garbage.delete")
    public R delete(@PathVariable Integer id) {
        garbageService.removeById(id);
        return R.success();
    }


    @PostMapping("/del/batch")
    @SaCheckPermission("garbage.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        garbageService.removeByIds(ids);
        return R.success();
    }

    @GetMapping
    @SaCheckPermission("garbage.list")
    public R findAll() {
        return R.success(garbageService.list());
    }

    @GetMapping("/{id}")
    @SaCheckPermission("garbage.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(garbageService.getById(id));
    }

    @GetMapping("/page")
    @SaCheckPermission("garbage.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Garbage> queryWrapper = new QueryWrapper<Garbage>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(garbageService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
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
