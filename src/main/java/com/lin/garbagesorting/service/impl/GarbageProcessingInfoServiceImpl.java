package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.GarbageProcessingInfo;
import com.lin.garbagesorting.entity.GarbageSortingInfo;
import com.lin.garbagesorting.entity.Office;
import com.lin.garbagesorting.mapper.GarbageProcessingInfoMapper;
import com.lin.garbagesorting.mapper.GarbageSortingInfoMapper;
import com.lin.garbagesorting.mapper.OfficeMapper;
import com.lin.garbagesorting.service.GarbageProcessingInfoService;
import com.lin.garbagesorting.utils.LocalDateTimeConvertUtils;
import com.lin.garbagesorting.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class GarbageProcessingInfoServiceImpl extends ServiceImpl<GarbageProcessingInfoMapper,GarbageProcessingInfo> implements GarbageProcessingInfoService {

	@Resource
	private GarbageProcessingInfoMapper garbageProcessingInfoMapper;

	@Resource
	private OfficeMapper officeMapper;

	@Resource
	private GarbageSortingInfoMapper garbageSortingInfoMapper;


	public BaseMapper<GarbageProcessingInfo> getMapper() {
		return garbageProcessingInfoMapper;
	}

	public GarbageProcessingInfo selectGPI(GarbageProcessingInfo garbageProcessingInfo,String username){
		LambdaQueryWrapper<Office> qWrapper = new LambdaQueryWrapper();
//        //添加过滤条件
        qWrapper.eq(StringUtils.isNotEmpty(username),Office::getOfUsername,username);
        Office office = officeMapper.selectOne(qWrapper);
		garbageProcessingInfo.setGpComunity(office.getOfCommunity());
		SnowFlake worker = new SnowFlake(1, 1, 1);
		garbageProcessingInfo.setGpId(worker.nextId());
//		QueryWrapper<GarbageSortingInfo> Wrapper = new QueryWrapper();
//
//		LocalDateTime gpDay = garbageProcessingInfo.getGpDay();
//		String convertTime = LocalDateTimeConvertUtils.Convert(gpDay);

//		Wrapper.like("create_time",convertTime).eq("community",office.getOfCommunity());
//		GarbageSortingInfo gsi = garbageSortingInfoMapper.selectOne(Wrapper);
//		garbageProcessingInfo.setGpTotal(gsi.getGsTotal());

		return garbageProcessingInfo;
	}



}
