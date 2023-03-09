package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Announcement;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.entity.Tenant;
import com.lin.garbagesorting.service.AnnouncementService;
import com.lin.garbagesorting.service.OfficeService;
import com.lin.garbagesorting.service.TenantService;
import com.lin.garbagesorting.utils.StringUtil;
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

@Api(tags = "公告管理")
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Resource
    private AnnouncementService announcementService;

    @Resource
    private TenantService tenantService;

    @Resource
    private OfficeService officeService;

    @ApiOperation(value = "新增公告", notes = "新增公告")
    @PostMapping
    @SaCheckPermission("announcement.add")
    public R saveByAdmin(@RequestBody Announcement announcement) {
        announcementService.save(announcement);
        return R.success();
    }


    @ApiOperation(value = "新增公告", notes = "新增公告")
    @PostMapping("/myAdd")
    @SaCheckPermission("announcement.myAdd")
    public R save(@RequestBody Announcement announcement,@RequestParam String username) {
        LambdaQueryWrapper<Office> lqWrapper = new LambdaQueryWrapper();
//        //添加过滤条件
        lqWrapper.eq(StringUtils.isNotEmpty(username), Office::getOfUsername,username);
        announcementService.save(announcement);
        return R.success();
    }

    @ApiOperation(value = "修改公告", notes = "修改公告")
    @PutMapping
    @SaCheckPermission("announcement.edit")
    public R update(@RequestBody Announcement announcement) {
        announcementService.updateById(announcement);
        return R.success();
    }

    @ApiOperation(value = "删除公告", notes = "删除公告")
    @DeleteMapping("/{id}")
    @SaCheckPermission("announcement.delete")
    public R delete(@PathVariable Integer id) {
        announcementService.removeById(id);
        return R.success();
    }

    @ApiOperation(value = "批量删除公告", notes = "批量删除公告")
    @PostMapping("/del/batch")
    @SaCheckPermission("announcement.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        announcementService.removeByIds(ids);
        return R.success();
    }

    @ApiOperation(value = "查所有公告", notes = "查所有公告")
    @GetMapping
    @SaCheckPermission("announcement.list")
    public R findAll() {
        return R.success(announcementService.list());
    }



    @ApiOperation(value = "查询ID公告", notes = "ID查询公告")
    @GetMapping("/{id}")
    @SaCheckPermission("announcement.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(announcementService.getById(id));
    }




//    @ApiOperation(value = "分页公告", notes = "分页公告")
//    @GetMapping("/page")
//    @SaCheckPermission("announcement.list")
//    public R findPage(@RequestParam(defaultValue = "") String announcementTitle,
//                           @RequestParam Integer pageNum,
//                           @RequestParam Integer pageSize) {
//        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<Announcement>().orderByDesc("id");
////
////        LambdaQueryWrapper<Office> lqWrapper = new LambdaQueryWrapper();
////
////        QueryWrapper<Tenant> queryWrapper2 = new QueryWrapper();
////
////        lqWrapper.eq(StringUtils.isNotEmpty(username), Office::getOfUsername,username);
////        if(StringUtil.isNotNull(officeService.getOne(lqWrapper).getOfCommunity())){
////            String community  = officeService.getOne(lqWrapper).getOfCommunity();
////        }
//        //业主的
////        if(StringUtil.isNotNull(tenantService.getOne(queryWrapper2).getTenantCommunity())){
////            String community  = tenantService.getOne(queryWrapper2).getTenantCommunity();
////        }
//
//        //模糊查询
//        queryWrapper.like(!"".equals(announcementTitle), "announcement_title", announcementTitle);
//        return R.success(announcementService.page(new Page<>(pageNum, pageSize), queryWrapper));
//    }


    @ApiOperation(value = "分页公告", notes = "分页公告")
    @GetMapping("/page")
    @SaCheckPermission("announcement.List")
    public R findAllPage(@RequestParam(defaultValue = "") String announcementTitle,
                      @RequestParam Integer pageNum,
                      @RequestParam Integer pageSize) {
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<Announcement>().orderByDesc("id");
        queryWrapper.like(!"".equals(announcementTitle), "announcement_title", announcementTitle);
        return R.success(announcementService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }



    /**
    * 导出接口
    */
    @ApiOperation(value = "导出公告", notes = "导出公告")
    @GetMapping("/export")
    @SaCheckPermission("announcement.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Announcement> list = announcementService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Announcement信息表", "UTF-8");
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
    @ApiOperation(value = "导入公告", notes = "导入公告")
    @PostMapping("/import")
    @SaCheckPermission("announcement.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Announcement> list = reader.readAll(Announcement.class);

        announcementService.saveBatch(list);
        return R.success();
    }

}
