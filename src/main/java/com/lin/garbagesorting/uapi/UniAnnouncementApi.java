package com.lin.garbagesorting.uapi;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Announcement;
import com.lin.garbagesorting.entity.Garbage;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.service.AnnouncementService;
import com.lin.garbagesorting.service.OfficeService;
import com.lin.garbagesorting.service.TenantService;
import com.lin.garbagesorting.utils.LocalDateTimeConvertUtils;
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

@Api(tags = "uniAPP公告管理")
@RestController
@RequestMapping("/uniAnnouncement")
public class UniAnnouncementApi {

    @Resource
    private AnnouncementService announcementService;


    @ApiOperation(value = "查所有公告", notes = "查所有公告")
    @GetMapping
    @SaCheckPermission("uniAnnouncement.list")
    public List<Announcement> findAll() {

        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<Announcement>().orderByDesc("id");
        queryWrapper.last("LIMIT 3");
        List<Announcement> announcementList =announcementService.list(queryWrapper);
//// 假设Person对象已经存在且已经被实例化并加入到personList中
//        for (Announcement announcement : announcementList) {
//            announcement.setCreateTime(LocalDateTimeConvertUtils.Convert(announcement.getCreateTime()));
//        }
        return announcementList;
    }


    @ApiOperation(value = "查询ID公告", notes = "ID查询公告")
    @GetMapping("/{id}")
    @SaCheckPermission("announcement.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(announcementService.getById(id));
    }



}
