/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EconoBenefit2;
import com.whu.pojo.EconoBenefit3;
import com.whu.pojo.EconoBenefit4;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class EconoBenefit4ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    EconoBenefit4Service econoBenefit4Service;

    @Test
    public void queryScoreByProjectId()
    {
        EconoBenefit4 econoBenefit4 = econoBenefit4Service.queryScoreByProjectIdAndState(1L,1, -1L);
        log.info("EconoBenefitServiceTest.queryScoreByProjectId: " +  econoBenefit4.toString());
    }

    /*@Test
    public void insertScore()
    {
        EconoBenefit4  econoBenefit4 = new EconoBenefit4();
        econoBenefit4.setExpertId(-1L);
        econoBenefit4.setProjectId(1L);
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
        econoBenefit4.setS_Financial_Increment(1);                     
        int code = econoBenefit4Service.insertScore(econoBenefit4);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}*/

