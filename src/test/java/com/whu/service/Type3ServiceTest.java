/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.Benefit;
import com.whu.pojo.EconoBenefit3;
import com.whu.pojo.EnvirBenefit3;
import com.whu.pojo.SocialBenefit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class Type3ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    Type3Service type3Service;

    @Test
    public void insertType3Score()
    {
    	 EnvirBenefit3 envirBenefit3 = new EnvirBenefit3();
         envirBenefit3.setExpertId(-1L);
         envirBenefit3.setProjectId(1L);
         envirBenefit3.setF_Function(1);
         envirBenefit3.setS_Function_Use(1);
         envirBenefit3.setS_Function_Service(1);
         envirBenefit3.setS_Function_Arrivable(1);
         envirBenefit3.setS_Function_Open(1);
         envirBenefit3.setS_Function_Safe(1);
         envirBenefit3.setF_Tech(1);
         envirBenefit3.setS_Tech_Operate(1);
         envirBenefit3.setS_Tech_Curing(1);
         envirBenefit3.setS_Tech_Material(1);
         envirBenefit3.setF_Human(1);
         envirBenefit3.setS_Human_Design(1);
         envirBenefit3.setS_Human_Explore(1);
         envirBenefit3.setF_Envir(1);
         envirBenefit3.setS_Envir_EnegrySave(1);
         envirBenefit3.setS_Envir_WaterSave(1);
         envirBenefit3.setS_Envir_Res(1);
         envirBenefit3.setS_Envir_System(1);
         envirBenefit3.setState(1);

        EconoBenefit3  econoBenefit3 = new EconoBenefit3();
        econoBenefit3.setExpertId(-1L);
        econoBenefit3.setProjectId(1L);
        econoBenefit3.setState(1);
        econoBenefit3.setF_AssetManager(1);
        econoBenefit3.setS_AssetManager_Early(1);
        econoBenefit3.setS_AssetManager_Service(1);
        econoBenefit3.setS_AssetManager_MoreAsset(1);
        econoBenefit3.setF_ProjectManager(1);
        econoBenefit3.setS_ProjectManager_Cost(1);
        econoBenefit3.setS_ProjectManager_Method(1);    

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
        int code = type3Service.insertType3Score(envirBenefit3,socialBenefit,econoBenefit3,grade);
        log.info("Type3ServiceTest.insertType3Score: " + code);
    }

    @Test
    public void queryScoreByProjetIdAndState()
    {
        Map<String, Benefit> score = type3Service.queryScoreByProjectIdAndState(26L,1, 8L);
        if(score != null)
        {
            log.info("Type3ServiceTest.queryScoreByProjetIdAndState: " + 1);
        }
    }
}*/

