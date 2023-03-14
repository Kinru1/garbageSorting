package com.lin.garbagesorting.uapi;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lin.garbagesorting.common.R;
import com.lin.garbagesorting.entity.Swiper;
import com.lin.garbagesorting.service.SwiperService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/uniSwiper")
public class UniSwiperApi {

    @Resource
    private SwiperService swiperService;
    
    @PostMapping
    @SaCheckPermission("swiper.add")
    public R save(@RequestBody Swiper swiper) {

        swiperService.save(swiper);
        return R.success();
    }


    @PutMapping
    @SaCheckPermission("swiper.edit")
    public R update(@RequestBody Swiper swiper) {
        swiperService.updateById(swiper);
        return R.success();
    }


    @DeleteMapping("/{id}")
    @SaCheckPermission("swiper.delete")
    public R delete(@PathVariable Integer id) {
        swiperService.removeById(id);
        return R.success();
    }
    @GetMapping("/images")
    public List<String> getImages() {
        QueryWrapper<Swiper> queryWrapper = new QueryWrapper<Swiper>().orderByDesc("id");
        queryWrapper.select("swiper_img");
        List<Swiper> imagePaths = swiperService.list(queryWrapper);
        List<Swiper> objects = imagePaths ;// 获取对象列表

        List<String> properties = objects.stream()
                .map(Swiper::getSwiperImg)
                .collect(Collectors.toList());
        return properties;
    }

    @PostMapping("/del/batch")
    @SaCheckPermission("swiper.deleteBatch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        swiperService.removeByIds(ids);
        return R.success();
    }

    @GetMapping
    @SaCheckPermission("swiper.list")
    public R findAll() {
        return R.success(swiperService.list());
    }

    @GetMapping("/{id}")
    @SaCheckPermission("swiper.list")
    public R findOne(@PathVariable Integer id) {
        return R.success(swiperService.getById(id));
    }


    @GetMapping("/page")
    @SaCheckPermission("swiper.list")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Swiper> queryWrapper = new QueryWrapper<Swiper>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return R.success(swiperService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


}
