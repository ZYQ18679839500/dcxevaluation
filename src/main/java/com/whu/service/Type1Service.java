package com.whu.service;

import com.whu.pojo.Benefit;
import com.whu.pojo.EconoBenefit1;
import com.whu.pojo.EnvirBenefit1;
import com.whu.pojo.SocialBenefit;

import java.util.Map;

public interface Type1Service {
    /**
     * 插入到建筑类的分数，其他几项同理
     *
     * @param envirBenefit1
     * @param socialBenefit
     * @param econoBenefit
     * @param grade
     * @return
     */
    int insertType1Score(EnvirBenefit1 envirBenefit1, SocialBenefit socialBenefit1, EconoBenefit1 econoBenefit1,
                         float grade, String evaluation, float impression);

    /**
     * 改变建筑类分数，只适用于终评
     *
     * @param envirBenefit1
     * @param socialBenefit
     * @param econoBenefit
     * @param grade
     * @return
     */
    int alterType1Score(EnvirBenefit1 envirBenefit1, SocialBenefit socialBenefit1, EconoBenefit1 econoBenefit1,
                        float grade);

    Map<String, Benefit> queryScoreByProjectIdAndState(Long projectId, int state, Long expertId);

    Map<String, Float> queryScoresByProjectIdAndState(Long projectId, int state);

    int updateType1Score(EnvirBenefit1 envirBenefit1, SocialBenefit socialBenefit, EconoBenefit1 econoBenefit1,
                         float grade, Long expertId, int state, String evaluation, float impression);
}
