package com.lin.service;

import com.lin.base.BaseService;
import  com.lin.base.IMapper;
import com.lin.dao.ChallengeDao;
import com.lin.dao.ChallengeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LGeneratorins on 2023/02/15 14:39
 */

@Service
public class ChallengeService extends BaseService<ChallengeDao> {

	@Autowired
	private ChallengeMapper challengeMapper;

	public IMapper<ChallengeDao> getMapper() {
		return challengeMapper;
	}
}
