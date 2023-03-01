package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Challenge;
import com.lin.garbagesorting.service.ChallengeService;
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
* @since 2023-03-01
*/
@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Resource
    private ChallengeService challengeService;
    
    @PostMapping
    @SaCheckPermission("challenge.add")
    public R save(@RequestBody Challenge challenge) {
//        User user = SessionUtils.getUser();
//        challenge.setUser(user.getName());
//        challenge.setUserid(user.getId());
//        challenge.setDate(DateUtil.today());
//        challenge.setTime(DateUtil.now());
        challengeService.save(challenge);
        return R.success();
    }

    @PutMapping
    @SaCheckPermission("challenge.edit")
    public R update(@RequestBody Challenge challenge) {
        challengeService.updateById(challenge);
        return R.success();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("challenge.delete")
    public R delete(@PathVariable Integer id) {
        challengeService.removeById(id);
        return R.success();
    }

    @PostMapping("/del/batch")
    @SaCheckPermission("challenge.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        challengeService.removeByIds(ids);
        return R.success();
    }

    @GetMapping
    @SaCheckPermission("challenge.list")
    public R findAll() {
        return R.success(challengeService.list());
    }

    @GetMapping("/{id}")
    @SaCheckPermission("challenge.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(challengeService.getById(id));
    }

    @GetMapping("/page")
    @SaCheckPermission("challenge.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Challenge> queryWrapper = new QueryWrapper<Challenge>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(challengeService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    @SaCheckPermission("challenge.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Challenge> list = challengeService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Challenge信息表", "UTF-8");
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
    @SaCheckPermission("challenge.import")
    public R imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Challenge> list = reader.readAll(Challenge.class);

        challengeService.saveBatch(list);
        return R.success();
    }

}
