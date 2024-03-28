package com.whu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whu.mapper.EconoBenefit1Mapper;
import com.whu.pojo.EconoBenefit1;
import com.whu.service.EconoBenefit1Service;

@Service
public class EconoBenefit1ServiceImpl implements EconoBenefit1Service {

    @Autowired
    EconoBenefit1Mapper econoBenefit1Mapper;

    @Override
    public EconoBenefit1 queryScoreByProjectIdAndState(Long projectId, int state, Long expertId) {
        return econoBenefit1Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
    }

    @Override
    public List<EconoBenefit1> queryScoresByProjectIdAndState(Long projectId, int state) {
        return econoBenefit1Mapper.queryScoresByProjectIdAndState(projectId, state);
    }

    @Override
    public int insertScore(EconoBenefit1 econoBenefit1) {
        return econoBenefit1Mapper.insertScore(econoBenefit1);
    }
}
