package com.whu.service;

import com.whu.pojo.*;

import java.util.Map;

public interface Type4Service {
    int insertType4Score(EnvirBenefit4 envirBenefit4, SocialBenefit socialBenefit, EconoBenefit4 econoBenefit4,
                         float grade, String evaluation, float impression);

    Map<String, Benefit> queryScoreByProjectIdAndState(Long projectId, int state, Long expertId);

    Map<String, Float> queryScoresByProjectIdAndState(Long projectId, int state);

    /**
     * 改变建筑类分数，只适用于终评
     *
     * @param envirBenefit4
     * @param socialBenefit
     * @param econoBenefit
     * @param grade
     * @return
     */
    int alterType4Score(EnvirBenefit4 envirBenefit4, SocialBenefit socialBenefit, EconoBenefit4 econoBenefit4,
                        float grade);

    int updateType4Score(EnvirBenefit4 envirBenefit4, SocialBenefit socialBenefit, EconoBenefit4 econoBenefit4,
                         float grade, Long expertId, int state, String evaluation, float impression);
}
