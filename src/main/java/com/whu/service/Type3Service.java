package com.whu.service;

import com.whu.pojo.*;

import java.util.Map;

public interface Type3Service {
    int insertType3Score(EnvirBenefit3 envirBenefit3, SocialBenefit socialBenefit, EconoBenefit3 econoBenefit3,
                         float grade, String evaluation, float impression);

    Map<String, Benefit> queryScoreByProjectIdAndState(Long projectId, int state, Long expertId);

    Map<String, Float> queryScoresByProjectIdAndState(Long projectId, int state);

    int alterType3Score(EnvirBenefit3 envirBenefit3, SocialBenefit socialBenefit, EconoBenefit3 econoBenefit3,
                        float grade);

    int updateType3Score(EnvirBenefit3 envirBenefit3, SocialBenefit socialBenefit, EconoBenefit3 econoBenefit3,
                         float grade, Long expertId, int state, String evaluation, float impression);

}
