/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvirBenefit2ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    EnvirBenefit2Service envirBenefit2Service;

    @Test
    public void queryScoreByProjectId()
    {
        EnvirBenefit2 envirBenefit2 = envirBenefit2Service.queryScoreByProjectIdAndState(26L,1, 8L);
        log.info("EnvirBenefit2ServiceTest.queryScoreByProjectIdAndState: " + envirBenefit2);
    }

    @Test
    public void insertScore()
    {
    	EnvirBenefit2 envirBenefit2 = new EnvirBenefit2();
        envirBenefit2.setExpertId(-1L);
        envirBenefit2.setProjectId(1L);
        envirBenefit2.setF_LandPlan(1);
        envirBenefit2.setS_LandPlan_LandUse(1);
        envirBenefit2.setS_LandPlan_LayOut(1);
        envirBenefit2.setF_Eco(1);
        envirBenefit2.setS_Eco_Nature(1);
        envirBenefit2.setS_Eco_Envir(1);
        envirBenefit2.setF_GreenBulid(1);
        envirBenefit2.setS_GreenBuild_GreenBulid(1);
        envirBenefit2.setF_Res(1);
        envirBenefit2.setS_Res_Energy(1);
        envirBenefit2.setS_Res_Water(1);
        envirBenefit2.setS_Res_Res(1);
        envirBenefit2.setS_Res_Carbon(1);
        envirBenefit2.setF_GreenTransport(1);
        envirBenefit2.setS_GreenTransport_Trans(1);
        envirBenefit2.setS_GreenTransport_Road(1);
        envirBenefit2.setS_GreenTransport_Static(1);
        envirBenefit2.setF_Innovation(1);
        envirBenefit2.setS_Innovation(1);
        envirBenefit2.setF_Humanity(1);
        envirBenefit2.setS_Humanity_People(1);
        envirBenefit2.setS_Humanity_GreenLive(1);
        envirBenefit2.setS_Humanity_GreenEdu(1);
        envirBenefit2.setS_Humanity_History(1);
        envirBenefit2.setF_Art(1);
        envirBenefit2.setS_Art_Bulid(1);
        envirBenefit2.setS_Art_Envir(1);
        envirBenefit2.setState(1);
        int code = envirBenefit2Service.insertScore(envirBenefit2);
        log.info("EnvirBenefit2ServiceTest.insertScore: " + code);
    }
}*/
