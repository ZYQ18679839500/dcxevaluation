/*package com.whu.mapper;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EconoBenefit1;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EconoBenefit1MapperTest extends FcxevaluationApplicationTests
{
    @Autowired
    EconoBenefit1Mapper econoBenefit1Mapper;

    @Test
    public void queryScoreByProjectId()
    {
        EconoBenefit1 econoBenefit1 = econoBenefit1Mapper.queryScoreByProjectIdAndState(1L,1,-1L);
        log.info("EconoBenefitMapperTest.queryScoreByProjectId: " +  econoBenefit1);
     }

    @Test
    public void queryScoresByProjectId()
    {
        List<EconoBenefit1> econoBenefits = econoBenefit1Mapper.queryScoresByProjectIdAndState(26L,1);
        log.info("EconoBenefitMapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
    }

    @Test
    public void insertScore()
    {
        EconoBenefit1  econoBenefit1 = new EconoBenefit1();
        econoBenefit1.setExpertId(-1L);
        econoBenefit1.setProjectId(1L);
        econoBenefit1.setState(1);
        econoBenefit1.setF_AssetPlan(1);
        econoBenefit1.setS_AssetPlan_Para(1);
        econoBenefit1.setF_AssetManagerment(1);
        econoBenefit1.setS_AssetManagerment_Early(1);
        econoBenefit1.setS_Managerment_Rent(1);
        econoBenefit1.setS_Managerment_Increment(1);
        econoBenefit1.setS_Managerment_Manager(1);
        econoBenefit1.setF_Financial(1);
        econoBenefit1.setS_Financial_Managerment(1);
        econoBenefit1.setS_Financial_Cash(1);
        econoBenefit1.setS_Financial_Operate(1);
        econoBenefit1.setS_Financial_Increment(1);                    
        int code = econoBenefit1Mapper.insertScore(econoBenefit1);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
    @Test
    public void updateScore()
    {
    	EconoBenefit1 econoBenefit1 = econoBenefit1Mapper.queryScoreByProjectIdAndState(1L,1,-1L);
    	
        econoBenefit1.setExpertId(-1L);
        econoBenefit1.setProjectId(1L);
        econoBenefit1.setState(1);
        econoBenefit1.setF_AssetPlan(1);
        econoBenefit1.setS_AssetPlan_Para(1);
        econoBenefit1.setF_AssetManagerment(1);
        econoBenefit1.setS_AssetManagerment_Early(1);
        econoBenefit1.setS_Managerment_Rent(1);
        econoBenefit1.setS_Managerment_Increment(1);
        econoBenefit1.setS_Managerment_Manager(1);
        econoBenefit1.setF_Financial(1);
        econoBenefit1.setS_Financial_Managerment(1);
        econoBenefit1.setS_Financial_Cash(1);
        econoBenefit1.setS_Financial_Operate(1);
        econoBenefit1.setS_Financial_Increment(8);                    
        int code = econoBenefit1Mapper.updateScore(econoBenefit1);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}*/
