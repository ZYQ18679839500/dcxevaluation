/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit1;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvirBenefit1ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    EnvirBenefit1Service envirBenefit1Service;

    @Test
    public void querySoceByProjectId()
    {
        EnvirBenefit1 envirBenefit1 = envirBenefit1Service.queryScoreByProjectIdAndState(1L,1, -1L);
        log.info(" EnvirBenefit1ServiceTest.querySoceByProjectIdAndState: " + envirBenefit1.toString());
    }

    @Test
    public void insertScore()
    {
        EnvirBenefit1 envirBenefit1 = new EnvirBenefit1();
        envirBenefit1.setExpertId(-1L);
        envirBenefit1.setProjectId(1L);
        envirBenefit1.setState(1);
        envirBenefit1.setF_SaveLands(1);
        envirBenefit1.setS_SaveLands_LandUse(1);
        envirBenefit1.setS_SaveLands_Outdoors(1);
        envirBenefit1.setS_SaveLands_Transport(1);
        envirBenefit1.setS_SaveLands_SiteDesign(1);
        envirBenefit1.setF_SaveEnergy(1);
        envirBenefit1.setS_SaveEnergy_Build(1);
        envirBenefit1.setS_SaveEnergy_Condition(1);                    
        envirBenefit1.setS_SaveEnergy_Light(1);
        envirBenefit1.setS_SaveEnergy_EnergyUse(1);
        envirBenefit1.setF_SaveRes(1);
        envirBenefit1.setS_SaveRes_SaveRes(1);
        envirBenefit1.setS_SaveRes_Choose(1);
        envirBenefit1.setF_Indoor(1);
        envirBenefit1.setS_Indoor_Sound(1);
        envirBenefit1.setS_Indoor_Light(1);
        envirBenefit1.setS_Indoor_Hot(1);
        envirBenefit1.setS_Indoor_Atmo(1);
        envirBenefit1.setF_Construction(1);
        envirBenefit1.setS_Construction_Envir(1);
        envirBenefit1.setS_Construction_Res(1);
        envirBenefit1.setS_Construction_Progress(1);
        envirBenefit1.setF_Operation(1);;
        envirBenefit1.setS_Operation_Management(1);
        envirBenefit1.setS_Operation_Tech(1);
        envirBenefit1.setS_Operation_Envir(1);
        envirBenefit1.setF_Humanity(1);
        envirBenefit1.setS_Humanity_People(1);
        envirBenefit1.setS_Humanity_GreenLive(1);
        envirBenefit1.setS_Humanity_GreenEdu(1);
        envirBenefit1.setS_Humanity_History(1);
        envirBenefit1.setF_Art(1);
        envirBenefit1.setS_Art_Design(1);
        int code = envirBenefit1Service.insertScore(envirBenefit1);
        log.info(" EnvirBenefit1ServiceTest.insertScore: " + code);
    }
}*/
