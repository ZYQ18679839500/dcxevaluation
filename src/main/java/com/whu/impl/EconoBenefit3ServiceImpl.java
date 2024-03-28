package com.whu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whu.mapper.EconoBenefit3Mapper;
import com.whu.pojo.EconoBenefit3;
import com.whu.service.EconoBenefit3Service;

@Service
public class EconoBenefit3ServiceImpl implements EconoBenefit3Service {
    @Autowired
    EconoBenefit3Mapper econoBenefit3Mapper;

    @Override
    public EconoBenefit3 queryScoreByProjectIdAndState(Long projectId, int state, Long expertId) {
        return econoBenefit3Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
    }

    @Override
    public List<EconoBenefit3> queryScoresByProjectIdAndState(Long projectId, int state) {
        return econoBenefit3Mapper.queryScoresByProjectIdAndState(projectId, state);
    }

    @Override
    public int insertScore(EconoBenefit3 econoBenefit3) {
        return econoBenefit3Mapper.insertScore(econoBenefit3);
    }
}
