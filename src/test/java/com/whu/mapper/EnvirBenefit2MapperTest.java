/*package com.whu.mapper;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit1;
import com.whu.pojo.EnvirBenefit2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EnvirBenefit2MapperTest extends FcxevaluationApplicationTests
{
    @Autowired
    EnvirBenefit2Mapper envirBenefit2Mapper;

    @Test
    public void queryScoreByProjectId()
    {
        EnvirBenefit2 envirBenefit2 = envirBenefit2Mapper.queryScoreByProjectIdAndState(1L,1, -1L);
        log.info("EnvirBenefit2MapperTest.queryScoreByProjectIdAndState: " + envirBenefit2);
    }

    @Test
    public void queryScoresByProjectId()
    {
        List<EnvirBenefit2> econoBenefits = envirBenefit2Mapper.queryScoresByProjectIdAndState(26L,1);
        log.info("EnvirBenefit2MapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
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
        envirBenefit2.setF_InfoManager(1);
        envirBenefit2.setS_InfoManager_Urban(1);
        envirBenefit2.setS_InfoManager_Service(1);
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
        int code = envirBenefit2Mapper.insertScore(envirBenefit2);
        log.info("EnvirBenefit2MapperTest.insertScore: " + code);
    }
    @Test
    public void insertScore()
    {
    	EnvirBenefit2 envirBenefit2 = envirBenefit2Mapper.queryScoreByProjectIdAndState(1L,1, -1L);
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
        envirBenefit2.setF_InfoManager(1);
        envirBenefit2.setS_InfoManager_Urban(1);
        envirBenefit2.setS_InfoManager_Service(1);
        envirBenefit2.setF_Innovation(1);
        envirBenefit2.setS_Innovation(1);
        envirBenefit2.setF_Humanity(1);
        envirBenefit2.setS_Humanity_People(1);
        envirBenefit2.setS_Humanity_GreenLive(1);
        envirBenefit2.setS_Humanity_GreenEdu(1);
        envirBenefit2.setS_Humanity_History(1);
        envirBenefit2.setF_Art(1);
        envirBenefit2.setS_Art_Bulid(1);
        envirBenefit2.setS_Art_Envir(8);
        
        envirBenefit2.setState(1);
        int code = envirBenefit2Mapper.updateScore(envirBenefit2);
        log.info("EnvirBenefit2MapperTest.insertScore: " + code);
    }
}*/
