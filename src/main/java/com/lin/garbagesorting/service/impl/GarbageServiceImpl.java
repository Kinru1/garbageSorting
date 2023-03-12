package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Garbage;
import com.lin.garbagesorting.mapper.GarbageMapper;
import com.lin.garbagesorting.service.GarbageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GarbageServiceImpl extends ServiceImpl<GarbageMapper,Garbage> implements GarbageService {

	@Resource
	private GarbageMapper garbageMapper;

	public BaseMapper<Garbage> getMapper() {
		return garbageMapper;
	}
	public List<Map<String, Object>> getGarbageList() {
		List<Map<String, Object>> result = new ArrayList<>();
		List<String> garbageTypes = Arrays.asList("有害垃圾", "厨余垃圾", "其他垃圾", "可回收垃圾");
		for (String garbageType : garbageTypes) {
			List<Garbage> garbageList = garbageMapper.getGarbageByType(garbageType);
			if (!garbageList.isEmpty()) {
				Map<String, Object> garbageTypeMap = new HashMap<>();
				garbageTypeMap.put("garbageType", garbageType);
				List<Map<String, String>> garbageDataList = new ArrayList<>();
				for (Garbage garbage : garbageList) {
					Map<String, String> garbageDataMap = new HashMap<>();
					garbageDataMap.put("img", garbage.getGarbageImg());
					garbageDataMap.put("name", garbage.getGarbageName());
					garbageDataList.add(garbageDataMap);
				}
				garbageTypeMap.put("garbage", garbageDataList);
				result.add(garbageTypeMap);
			}
		}

		return result;
	}
}
