/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit3;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvirBenefit3ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    EnvirBenefit3Service envirBenefit3Service;

    @Test
    public void queryScoreByProjectId()
    {
        EnvirBenefit3 envirBenefit3 = envirBenefit3Service.queryScoreByProjectIdAndState(26L,1, 8L);
        log.info("EnvirBenefit3ServiceTest.queryScoreByProjectIdAndState: " + envirBenefit3);
    }

    @Test
    public void insertScore()
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
        int code = envirBenefit3Service.insertScore(envirBenefit3);
        log.info("EnvirBenefit3ServiceTest.insertScore: " + code);
    }
}*/
