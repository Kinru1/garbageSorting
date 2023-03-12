package com.lin.garbagesorting.uapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.garbagesorting.entity.Challenge;
import com.lin.garbagesorting.service.ChallengeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Api(tags = "问答挑战管理")
@RestController
@RequestMapping("/uniChallenge")
public class UniChallangeApi {

    @Resource
    private ChallengeService challengeService;

    @GetMapping
    public List<Challenge> getRandomQuestions() {

        QueryWrapper<Challenge> wrapper = new QueryWrapper();

        wrapper.orderByAsc("RAND()");
        wrapper.last("LIMIT 10");
        System.out.println( challengeService.list(wrapper));
        return challengeService.list(wrapper);
    }
}
