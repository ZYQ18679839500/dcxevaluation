package com.whu.service;

import com.whu.pojo.*;

import java.util.Map;

public interface Type2Service {
    int insertType2Score(EnvirBenefit2 envirBenefit2, SocialBenefit socialBenefit, EconoBenefit2 econoBenefit2,
                         float grade, String evaluation, float impression);

    Map<String, Benefit> queryScoreByProjectIdAndState(Long projectId, int state, Long expertId);

    Map<String, Float> queryScoresByProjectIdAndState(Long projectId, int state);

    /**
     * 改变建筑类分数，只适用于终评
     *
     * @param envirBenefit2
     * @param socialBenefit
     * @param econoBenefit
     * @param grade
     * @return
     */
    int alterType2Score(EnvirBenefit2 envirBenefit2, SocialBenefit socialBenefit, EconoBenefit2 econoBenefit2,
                        float grade);

    int updateType2Score(EnvirBenefit2 envirBenefit2, SocialBenefit socialBenefit, EconoBenefit2 econoBenefit2,
                         float grade, Long expertId, int state, String evaluation, float impression);

}
