package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.Challenge;
import com.lin.garbagesorting.mapper.ChallengeMapper;
import com.lin.garbagesorting.service.ChallengeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChallengeServiceImpl extends ServiceImpl<ChallengeMapper,Challenge> implements ChallengeService {

	@Resource
	private ChallengeMapper challengeMapper;

	public BaseMapper<Challenge> getMapper() {
		return challengeMapper;
	}
}
