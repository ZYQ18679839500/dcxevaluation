/*package com.whu.mapper;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit4;
import com.whu.pojo.SocialBenefit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SocialBenefitMapperTest extends FcxevaluationApplicationTests
{
    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Test
    public void queryScoreByProjectId()
    {
        SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(1L,1, -1L);
        log.info("SocialBenefitMapperTest.queryScoreByProjectId: " + socialBenefit.toString());
    }

    @Test
    public void queryScoresByProjectId()
    {
        List<SocialBenefit> econoBenefits = socialBenefitMapper.queryScoresByProjectIdAndState(26L,1);
        log.info("SocialBenefitMapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
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
        int code = socialBenefitMapper.insertScore(socialBenefit);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
    
    @Test
    public void updateScore()
    {
    	SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(1L,1, -1L);
        socialBenefit.setExpertId(-1L);
        socialBenefit.setProjectId(1L);
    	socialBenefit.setF_Social(1);
    	socialBenefit.setS_Social_Develop(1);
    	socialBenefit.setS_Social_People(1);
    	socialBenefit.setS_Social_Green(1);
    	socialBenefit.setF_Work(1);
    	socialBenefit.setF_Work_Brand(1);
    	socialBenefit.setF_Work_Develop(8);
        socialBenefit.setState(1);
        int code = socialBenefitMapper.updateScore(socialBenefit);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}*/
