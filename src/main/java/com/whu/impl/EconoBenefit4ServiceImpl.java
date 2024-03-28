package com.whu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whu.mapper.EconoBenefit3Mapper;
import com.whu.mapper.EconoBenefit4Mapper;
import com.whu.pojo.EconoBenefit4;
import com.whu.service.EconoBenefit4Service;

@Service
public class EconoBenefit4ServiceImpl implements EconoBenefit4Service {
    @Autowired
    EconoBenefit4Mapper econoBenefit4Mapper;

    @Override
    public EconoBenefit4 queryScoreByProjectIdAndState(Long projectId, int state, Long expertId) {
        return econoBenefit4Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
    }

    @Override
    public List<EconoBenefit4> queryScoresByProjectIdAndState(Long projectId, int state) {
        return econoBenefit4Mapper.queryScoresByProjectIdAndState(projectId, state);
    }

    @Override
    public int insertScore(EconoBenefit4 econoBenefit4) {
        return econoBenefit4Mapper.insertScore(econoBenefit4);
    }
}
