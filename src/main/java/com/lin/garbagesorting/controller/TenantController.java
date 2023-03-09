package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.entity.Tenant;
import com.lin.garbagesorting.entity.User;
import com.lin.garbagesorting.service.OfficeService;
import com.lin.garbagesorting.service.TenantService;
import com.lin.garbagesorting.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "业主管理")
@RestController
@RequestMapping("/tenant")

public class TenantController {

    @Resource
    private TenantService tenantService;

    @Resource
    private OfficeService officeService;


    @ApiOperation(value = "新增自己小区业主", notes = "新增自己小区业主")
    @PostMapping("/myAdd")
    @SaCheckPermission("tenant.myAdd")
    public R saveTenant(@RequestBody Tenant tenant,@RequestParam String username) {


        LambdaQueryWrapper<Office> lqWrapper = new LambdaQueryWrapper();
//        //添加过滤条件
        lqWrapper.eq(StringUtils.isNotEmpty(username), Office::getOfUsername,username);

        String community = officeService.getOne(lqWrapper).getOfCommunity();
        tenant.setTenantCommunity(community);
        SnowFlake worker = new SnowFlake(1, 1, 1);
        tenant.setTenantId(worker.nextId());
        tenantService.save(tenant);
        return R.success();
    }


    @ApiOperation(value = "新增业主", notes = "新增业主")
    @PostMapping
    @SaCheckPermission("tenant.Add")
    public R save(@RequestBody Tenant tenant) {
        tenantService.save(tenant);
        return R.success();
    }

    @ApiOperation(value = "修改业主", notes = "修改业主")
    @PutMapping
    @SaCheckPermission("tenant.edit")
    public R update(@RequestBody Tenant tenant) {
        tenantService.updateById(tenant);
        return R.success();
    }

    @ApiOperation(value = "删除业主", notes = "删除业主")
    @DeleteMapping("/{id}")
    @SaCheckPermission("tenant.delete")
    public R delete(@PathVariable Integer id) {
        tenantService.removeById(id);
        return R.success();
    }

    @ApiOperation(value = "批量删除业主", notes = "批量删除业主")
    @PostMapping("/del/batch")
    @SaCheckPermission("tenant.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        tenantService.removeByIds(ids);
        return R.success();
    }


    @ApiOperation(value = "查询所有业主", notes = "查询所有业主")
    @GetMapping
    @SaCheckPermission("tenant.list")
    public R findAll() {
        return R.success(tenantService.list());
    }


    @ApiOperation(value = "根据ID查询业主", notes = "根据ID查询业主")
    @GetMapping("/{id}")
    @SaCheckPermission("tenant.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(tenantService.getById(id));
    }




    @ApiOperation(value = "分页查询自己小区的业主", notes = "分页查询自己小区的业主")
    @GetMapping("/page")
    @SaCheckPermission("tenant.myList")
    public R findPage(@RequestParam String username,@RequestParam(defaultValue = "") String  tenantOwer,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<Tenant>().orderByDesc("id");

        LambdaQueryWrapper<Office> lqWrapper = new LambdaQueryWrapper();
//        //添加过滤条件
        lqWrapper.eq(StringUtils.isNotEmpty(username), Office::getOfUsername,username);

        String community  = officeService.getOne(lqWrapper).getOfCommunity();
        //模糊查询
        queryWrapper.like(!"".equals(tenantOwer), "name", tenantOwer).eq("tenant_community",community);
        return R.success(tenantService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


    @ApiOperation(value = "分页查询所有业主", notes = "分页查询所有业主")
    @GetMapping("/pageALL")
    @SaCheckPermission("tenant.list")
    public R findALLPage(@RequestParam(defaultValue = "") String tenantOwer,
                      @RequestParam Integer pageNum,
                      @RequestParam Integer pageSize) {
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<Tenant>().orderByDesc("id");
        //模糊查询
        queryWrapper.like(!"".equals(tenantOwer), "tenant_ower", tenantOwer);
        return R.success(tenantService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


    @ApiOperation(value = "导出所有业主", notes = "导出所有业主")
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
    @ApiOperation(value = "根据EXCEL导入业主", notes = "根据EXCEL导入业主")
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
