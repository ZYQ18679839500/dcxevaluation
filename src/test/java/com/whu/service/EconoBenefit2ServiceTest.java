/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EconoBenefit2;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class EconoBenefit2ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    EconoBenefit2Service econoBenefit2Service;

    @Test
    public void queryScoreByProjectId()
    {
        EconoBenefit2 econoBenefit2 = econoBenefit2Service.queryScoreByProjectIdAndState(26L,1, 8L);
        log.info("EconoBenefitServiceTest.queryScoreByProjectId: " +  econoBenefit2.toString());
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
        int code = econoBenefit2Service.insertScore(econoBenefit2);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}*/
