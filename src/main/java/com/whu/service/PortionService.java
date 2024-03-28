package com.whu.service;

import java.util.List;

import com.whu.pojo.Portion;

public interface PortionService {

    /**
     * 根据expert_id查询各项占比
     *
     * @param id 专家的id
     * @return 返回查询结果
     */
    int setPortion(Long expert_id, Long type, int envir_benefit, int econo_benefit, int social_benefit);

    List<Portion> queryAllPortionByID(Long expert_id);

    void updatePortion(Long expert_id, Long type, int envir_benefit, int econo_benefit, int social_benefit);

}
