/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.Benefit;
import com.whu.pojo.EconoBenefit4;
import com.whu.pojo.EnvirBenefit4;
import com.whu.pojo.SocialBenefit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class Type4ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    Type4Service type4Service;

    @Test
    public void insertType4Score()
    {

    	EnvirBenefit4 envirBenefit4= new EnvirBenefit4();
        envirBenefit4.setExpertId(-1L);
        envirBenefit4.setProjectId(1L);
        envirBenefit4.setF_PhysicalEnvir(1);
        envirBenefit4.setS_PhysicalEnvir_Sound(1);
        envirBenefit4.setS_PhysicalEnvir_Light(1);
        envirBenefit4.setS_PhysicalEnvir_Hot(1);
        envirBenefit4.setS_PhysicalEnvir_Wind(1);
        envirBenefit4.setF_HumanEnvir(1);
        envirBenefit4.setS_HumanEnvir_Function(1);
        envirBenefit4.setS_HumanEnvir_Beauty(1);
        envirBenefit4.setS_HumanEnvir_Explore(1);
        envirBenefit4.setF_Decorate(1);
        envirBenefit4.setS_DecorateDetails(1);
        envirBenefit4.setS_DecorateMaterial(1);
        envirBenefit4.setF_Tech(1);
        envirBenefit4.setS_Tech_Envir(1);
        envirBenefit4.setS_Tech_Res(1);
        envirBenefit4.setS_Tech_Progress(1);
        envirBenefit4.setState(1);

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
        float grade = 95.0f;
        int code = type4Service.insertType4Score(envirBenefit4,socialBenefit,econoBenefit4,grade);
        log.info("Type4ServiceTest.insertType4Score: " + code);
    }

    @Test
    public void queryScoreByProjetIdAndState()
    {
        Map<String, Benefit> score = type4Service.queryScoreByProjectIdAndState(26L,1, 8L);
        if(score != null)
        {
            log.info("Type4ServiceTest.queryScoreByProjetIdAndState: " + 1);
        }
    }
}*/
