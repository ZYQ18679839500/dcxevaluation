package com.whu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whu.mapper.EconoBenefit2Mapper;
import com.whu.pojo.EconoBenefit2;
import com.whu.service.EconoBenefit2Service;

@Service
public class EconoBenefit2ServiceImpl implements EconoBenefit2Service {

    @Autowired
    EconoBenefit2Mapper econoBenefit2Mapper;

    @Override
    public EconoBenefit2 queryScoreByProjectIdAndState(Long projectId, int state, Long expertId) {
        return econoBenefit2Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
    }

    @Override
    public List<EconoBenefit2> queryScoresByProjectIdAndState(Long projectId, int state) {
        return econoBenefit2Mapper.queryScoresByProjectIdAndState(projectId, state);
    }

    @Override
    public int insertScore(EconoBenefit2 econoBenefit2) {
        return econoBenefit2Mapper.insertScore(econoBenefit2);
    }
}
