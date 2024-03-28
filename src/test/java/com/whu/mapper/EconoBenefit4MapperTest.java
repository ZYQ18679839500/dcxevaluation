/*package com.whu.mapper;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EconoBenefit4;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EconoBenefit4MapperTest extends FcxevaluationApplicationTests
{
    @Autowired
    EconoBenefit4Mapper econoBenefit4Mapper;

    @Test
    public void queryScoreByProjectId()
    {
        EconoBenefit4 econoBenefit4 = econoBenefit4Mapper.queryScoreByProjectIdAndState(10L,1, 38L);
        log.info("EconoBenefitMapperTest.queryScoreByProjectId: " +  econoBenefit4.toString());
    }

    @Test
    public void queryScoresByProjectId()
    {
        List<EconoBenefit4> econoBenefits = econoBenefit4Mapper.queryScoresByProjectIdAndState(10L,1);
        log.info("EconoBenefitMapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
    }

    /*@Test
    public void updateScore()
    {
    	EconoBenefit4 econoBenefit4 = econoBenefit4Mapper.queryScoreByProjectIdAndState(10L,1, 38L);
    	//EconoBenefit4 econoBenefit4=new EconoBenefit4(); 
        econoBenefit4.setExpertId(38L);
        econoBenefit4.setProjectId(10L);
        econoBenefit4.setState(1);
        econoBenefit4.setF_AssetPlan(1);
        econoBenefit4.setS_AssetPlan_Para(1);
        econoBenefit4.setF_AssetManagerment(1);
        econoBenefit4.setS_AssetManagerment_Early(1);
        econoBenefit4.setS_Managerment_Rent(1);
        econoBenefit4.setS_Managerment_Increment(1);
        econoBenefit4.setS_Managerment_Manager(1);
        econoBenefit4.setF_Financial(1);
        econoBenefit4.setS_Financial_Managerment(1);
        econoBenefit4.setS_Financial_Cash(1);
        econoBenefit4.setS_Financial_Operate(1);
        econoBenefit4.setS_Financial_Increment(9);                    
        int code = econoBenefit4Mapper.updateScore(econoBenefit4);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}*/
