/*package com.whu.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EconoBenefit2;

public class EconoBenefit2MapperTest extends FcxevaluationApplicationTests{
	@Autowired
    EconoBenefit2Mapper econoBenefit2Mapper;

    @Test
    public void queryScoreByProjectId()
    {
        EconoBenefit2 econoBenefit2 = econoBenefit2Mapper.queryScoreByProjectIdAndState(4L,1, 38L);
        log.info("EconoBenefitMapperTest.queryScoreByProjectId: " +  econoBenefit2.toString());
    }

    @Test
    public void queryScoresByProjectId()
    {
        List<EconoBenefit2> econoBenefits = econoBenefit2Mapper.queryScoresByProjectIdAndState(26L,1);
        log.info("EconoBenefitMapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
    }

    /*@Test
    public void insertScore()
    {
        EconoBenefit2  econoBenefit2 = new EconoBenefit2();
        econoBenefit2.setExpertId(-1L);
        econoBenefit2.setProjectId(1L);
        econoBenefit2.setState(1);
        econoBenefit2.setF_AssetPlan(1);
        econoBenefit2.setS_AssetPlan_Para(1);
        econoBenefit2.setF_Industry(1);
        econoBenefit2.setS_Industry_Early(1);
        econoBenefit2.setS_Industry_Rent(1);
        econoBenefit2.setS_Industry_Increment(1);
        econoBenefit2.setS_Industry_Manager(1);
        econoBenefit2.setF_Financial(1);
        econoBenefit2.setS_Financial_Managerment(1);
        econoBenefit2.setS_Financial_Cash(1);
        econoBenefit2.setS_Financial_Operate(1);
        econoBenefit2.setS_Financial_Increment(1);     
        int code = econoBenefit2Mapper.insertScore(econoBenefit2);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
    
    @Test
    public void insertScore()
    {
    	EconoBenefit2 econoBenefit2 = econoBenefit2Mapper.queryScoreByProjectIdAndState(4L,1, 38L);
        econoBenefit2.setExpertId(-1L);
        econoBenefit2.setProjectId(1L);
        econoBenefit2.setState(1);
        econoBenefit2.setF_AssetPlan(1);
        econoBenefit2.setS_AssetPlan_Para(1);
        econoBenefit2.setF_Industry(1);
        econoBenefit2.setS_Industry_Early(1);
        econoBenefit2.setS_Industry_Rent(1);
        econoBenefit2.setS_Industry_Increment(1);
        econoBenefit2.setS_Industry_Manager(1);
        econoBenefit2.setF_Financial(1);
        econoBenefit2.setS_Financial_Managerment(1);
        econoBenefit2.setS_Financial_Cash(1);
        econoBenefit2.setS_Financial_Operate(1);
        econoBenefit2.setS_Financial_Increment(8);     
        int code = econoBenefit2Mapper.updateScore(econoBenefit2);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}*/
