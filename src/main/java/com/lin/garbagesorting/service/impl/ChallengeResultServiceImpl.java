package com.lin.garbagesorting.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.garbagesorting.entity.ChallengeResult;
import com.lin.garbagesorting.mapper.ChallengeResultMapper;
import com.lin.garbagesorting.service.ChallengeResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChallengeResultServiceImpl extends ServiceImpl<ChallengeResultMapper,ChallengeResult> implements ChallengeResultService {

	@Resource
	private ChallengeResultMapper challengeResultMapper;

	public BaseMapper<ChallengeResult> getMapper() {
		return challengeResultMapper;
	}
}
