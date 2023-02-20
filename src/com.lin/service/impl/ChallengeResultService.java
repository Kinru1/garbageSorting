package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.ChallengeResultDao;
import com.lin.dao.ChallengeResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:39
 */

@Service
public class ChallengeResultService extends BaseService<ChallengeResultDao> {

	@Autowired
	private ChallengeResultMapper challengeResultMapper;

	public IMapper<ChallengeResultDao> getMapper() {
		return challengeResultMapper;
	}
}
