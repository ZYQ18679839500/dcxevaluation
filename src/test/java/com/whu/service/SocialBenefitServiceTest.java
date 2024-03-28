/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.SocialBenefit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SocialBenefitServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    SocialBenefitService socialBenefitService;

    @Test
    public void queryScoreByProjectIdAndState()
    {
        SocialBenefit socialBenefit =  socialBenefitService.queryScoreByProjectIdAndState(1L,1, -1L);
        if(socialBenefit != null)
        	log.info("SocialBenefitServiceTest.queryScoreByProjectIdAndState: " + socialBenefit.toString());
    }

    @Test
    public void insertScore()
    {
    	SocialBenefit socialBenefit = new SocialBenefit();
        socialBenefit.setExpertId(-1L);
        socialBenefit.setProjectId(1L);
    	socialBenefit.setF_Social(1);
    	socialBenefit.setS_Social_Develop(1);
    	socialBenefit.setS_Social_People(1);
    	socialBenefit.setS_Social_Green(1);
    	socialBenefit.setF_Work(1);
    	socialBenefit.setF_Work_Brand(1);
    	socialBenefit.setF_Work_Develop(1);
        socialBenefit.setState(1);
        int code = socialBenefitService.insertScore(socialBenefit);
        log.info("SocialBenefitServiceTest.insertScore: " + code);
    }
}*/
