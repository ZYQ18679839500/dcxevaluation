package com.whu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whu.mapper.SocialBenefitMapper;
import com.whu.pojo.SocialBenefit;
import com.whu.service.SocialBenefitService;

@Service
public class SocialBenefitServiceImpl implements SocialBenefitService {
    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Override
    public SocialBenefit queryScoreByProjectIdAndState(Long projectId, int state, Long expertId) {
        return socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
    }

    @Override
    public List<SocialBenefit> queryScoresByProjectIdAndState(Long projectId, int state) {
        return socialBenefitMapper.queryScoresByProjectIdAndState(projectId, state);
    }

    @Override
    public int insertScore(SocialBenefit socialBenefit1) {
        return socialBenefitMapper.insertScore(socialBenefit1);
    }
}
