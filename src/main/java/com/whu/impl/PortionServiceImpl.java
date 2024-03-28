package com.whu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whu.mapper.PortionMapper;
import com.whu.pojo.Portion;
import com.whu.service.PortionService;

@Service
public class PortionServiceImpl implements PortionService {

    @Autowired
    PortionMapper portionMapper;

    @Override
    public int setPortion(Long expert_id, Long type, int envir_benefit, int econo_benefit, int social_benefit) {

        return portionMapper.setPortion(expert_id, type, envir_benefit, econo_benefit, social_benefit);
    }

    @Override
    public List<Portion> queryAllPortionByID(Long expert_id) {

        return portionMapper.queryPortionByID(expert_id);
    }

    @Override
    public void updatePortion(Long expert_id, Long type, int envir_benefit, int econo_benefit, int social_benefit) {
        portionMapper.updatePortion(expert_id, type, envir_benefit, econo_benefit, social_benefit);
    }

}
