package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.service.OfficeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/office")
public class OfficeController {

    @Resource
    private OfficeService officeService;

    
    @PostMapping
    @SaCheckPermission("office.add")
    public R save(@RequestBody Office office) {
//        User user = SessionUtils.getUser();
//        office.setUser(user.getName());
//        office.setUserid(user.getId());
//        office.setDate(DateUtil.today());
//        office.setTime(DateUtil.now());
        officeService.save(office);
        return R.success();
    }


    @PutMapping
    @SaCheckPermission("office.edit")
    public R update(@RequestBody Office office) {
        officeService.updateById(office);
        return R.success();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("office.delete")
    public R delete(@PathVariable Integer id) {
        officeService.removeById(id);
        return R.success();
    }


    @PostMapping("/del/batch")
    @SaCheckPermission("office.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        officeService.removeByIds(ids);
        return R.success();
    }

    @GetMapping
    @SaCheckPermission("office.list")
    public R findAll() {
        return R.success(officeService.list());
    }

    @GetMapping("/{id}")
    @SaCheckPermission("office.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(officeService.getById(id));
    }

    @GetMapping("/page")
    @SaCheckPermission("office.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Office> queryWrapper = new QueryWrapper<Office>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(officeService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    @SaCheckPermission("office.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Office> list = officeService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Office信息表", "UTF-8");
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
    @SaCheckPermission("office.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Office> list = reader.readAll(Office.class);

        officeService.saveBatch(list);
        return R.success();
    }

}