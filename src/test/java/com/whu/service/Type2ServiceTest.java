package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.mapper.EconoBenefit1Mapper;
import com.whu.mapper.EconoBenefit2Mapper;
import com.whu.mapper.EnvirBenefit1Mapper;
import com.whu.mapper.EnvirBenefit2Mapper;
import com.whu.mapper.SocialBenefitMapper;
import com.whu.pojo.Benefit;
import com.whu.pojo.EconoBenefit2;
import com.whu.pojo.EnvirBenefit2;
import com.whu.pojo.SocialBenefit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class Type2ServiceTest extends FcxevaluationApplicationTests {
    @Autowired
    Type2Service type2Service;

    @Autowired
    EconoBenefit2Mapper econoBenefit2Mapper;

    @Autowired
    EnvirBenefit2Mapper envirBenefit2Mapper;

    @Autowired
    SocialBenefitMapper socialBenefitMapper;
    
    /*@Test
    public void insertType2Score()
    {
    	EnvirBenefit2 envirBenefit2 = new EnvirBenefit2();
        envirBenefit2.setExpertId(-1L);
        envirBenefit2.setProjectId(883L);
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

        EconoBenefit2  econoBenefit2 = new EconoBenefit2();
        econoBenefit2.setExpertId(-1L);
        econoBenefit2.setProjectId(883L);
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

        SocialBenefit socialBenefit = new SocialBenefit();
        socialBenefit.setExpertId(-1L);
        socialBenefit.setProjectId(883L);
    	socialBenefit.setF_Social(1);
    	socialBenefit.setS_Social_Develop(1);
    	socialBenefit.setS_Social_People(1);
    	socialBenefit.setS_Social_Green(1);
    	socialBenefit.setF_Work(1);
    	socialBenefit.setF_Work_Brand(1);
    	socialBenefit.setF_Work_Develop(1);
        socialBenefit.setState(1);
        
		float grade=(float) 99.999;
		String evaluation="嘿嘿";
		float impression=100;
        
        int code = type2Service.insertType2Score(envirBenefit2,socialBenefit,econoBenefit2,grade,evaluation,impression);
        log.info("Type2ServiceTest.insertType2Score: " + code);
    }*/

    @Test
    public void updateType2() {
        EconoBenefit2 econoBenefit2 = econoBenefit2Mapper.queryScoreByProjectIdAndState(4L, 1, 38L);
        EnvirBenefit2 envirBenefit2 = envirBenefit2Mapper.queryScoreByProjectIdAndState(4L, 1, 38L);
        SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(4L, 1, 38L);

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

        socialBenefit.setF_Social(1);
        socialBenefit.setS_Social_Develop(1);
        socialBenefit.setS_Social_People(1);
        socialBenefit.setS_Social_Green(1);
        socialBenefit.setF_Work(1);
        socialBenefit.setF_Work_Brand(1);
        socialBenefit.setF_Work_Develop(1);
        socialBenefit.setState(1);

        float grade = (float) 99.999;
        String evaluation = "嘿嘿";
        float impression = 100;

        int code = type2Service.updateType2Score(envirBenefit2, socialBenefit, econoBenefit2, grade, -1L, 1, evaluation, impression);
        log.info("Type2ServiceTest.insertType2Score: " + code);
    }

    @Test
    public void queryScoreByProjetIdAndState() {
        Map<String, Benefit> score = type2Service.queryScoreByProjectIdAndState(4L, 1, 38L);
        if (score == null) {
            log.info("Type2ServiceTest.queryScoreByProjetIdAndState: " + 1);
        }
    }
}
