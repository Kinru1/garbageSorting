package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Tenant;
import com.lin.garbagesorting.service.TenantService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Resource
    private TenantService tenantService;


    @PostMapping
    @SaCheckPermission("tenant.add")
    public R save(@RequestBody Tenant tenant) {
//        User user = SessionUtils.getUser();
//        tenant.setUser(user.getName());
//        tenant.setUserid(user.getId());
//        tenant.setDate(DateUtil.today());
//        tenant.setTime(DateUtil.now());
        tenantService.save(tenant);
        return R.success();
    }


    @PutMapping
    @SaCheckPermission("tenant.edit")
    public R update(@RequestBody Tenant tenant) {
        tenantService.updateById(tenant);
        return R.success();
    }


    @DeleteMapping("/{id}")
    @SaCheckPermission("tenant.delete")
    public R delete(@PathVariable Integer id) {
        tenantService.removeById(id);
        return R.success();
    }


    @PostMapping("/del/batch")
    @SaCheckPermission("tenant.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        tenantService.removeByIds(ids);
        return R.success();
    }

    @GetMapping
    @SaCheckPermission("tenant.list")
    public R findAll() {
        return R.success(tenantService.list());
    }

    @GetMapping("/{id}")
    @SaCheckPermission("tenant.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(tenantService.getById(id));
    }

    @GetMapping("/page")
    @SaCheckPermission("tenant.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<Tenant>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(tenantService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    @SaCheckPermission("tenant.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Tenant> list = tenantService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Tenant信息表", "UTF-8");
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
    @SaCheckPermission("tenant.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Tenant> list = reader.readAll(Tenant.class);

        tenantService.saveBatch(list);
        return R.success();
    }

}
