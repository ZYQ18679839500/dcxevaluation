/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit4;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvirBenefit4ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    EnvirBenefit4Service envirBenefit4Service;

    @Test
    public void queryScoreByProjectId()
    {
        EnvirBenefit4 envirBenefit4 = envirBenefit4Service.queryScoreByProjectIdAndState(26L,1, 8L);
        log.info("EnvirBenefit4ServiceTest.queryScoreByProjectIdAndState: " + envirBenefit4);
    }

    @Test
    public void insertScore()
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
        int code = envirBenefit4Service.insertScore(envirBenefit4);
        log.info("EnvirBenefit4ServiceTest.insertScore: " + code);
    }
}*/
