package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.GarbageSortingInfo;
import com.lin.garbagesorting.mapper.GarbageSortingInfoMapper;
import com.lin.garbagesorting.service.GarbageSiteService;
import com.lin.garbagesorting.service.GarbageSortingInfoService;
import com.lin.garbagesorting.utils.SnowFlake;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GarbageSortingInfoServiceImpl extends ServiceImpl<GarbageSortingInfoMapper,GarbageSortingInfo> implements GarbageSortingInfoService {

	@Resource
	private GarbageSortingInfoMapper garbageSortingInfoMapper;

	public BaseMapper<GarbageSortingInfo> getMapper() {
		return garbageSortingInfoMapper;
	}

	public GarbageSortingInfo saveTotal(GarbageSortingInfo garbageSortingInfo){
		double recy =  garbageSortingInfo.getGsTotalRecyclable();
		double hazard =  garbageSortingInfo.getGsTotalHazardous();
		double other = garbageSortingInfo.getGsTotalOther();
		double kitchen = garbageSortingInfo.getGsTotalKitchen();
		double total  =	recy + hazard +other + kitchen;
		garbageSortingInfo.setGsTotal(total);

		return garbageSortingInfo;
	}

	public double getLastTotal(Integer day,String community,String garbageType) {
		double gsi=  garbageSortingInfoMapper.getLastTotal(day,community,garbageType);
		return gsi;
	}
//	public double getLastTotalRecy(Integer day,String community) {
//		double glr=  garbageSortingInfoMapper.getLastTotal(day,community);
//		return glr;
//	}
//	public double getLastTotalOther(Integer day,String community) {
//		double glo=  garbageSortingInfoMapper.getLastTotal(day,community);
//		return glo;
//	}
//	public double getLastTotalKitchen(Integer day,String community) {
//		double glk=  garbageSortingInfoMapper.getLastTotal(day,community);
//		return glk;
//	}
//	public double getLastTotalHazardous(Integer day,String community) {
//		double glh=  garbageSortingInfoMapper.getLastTotal(day,community);
//		return glh;
//	}
}
