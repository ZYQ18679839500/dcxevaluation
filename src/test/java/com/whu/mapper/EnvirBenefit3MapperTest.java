/*package com.whu.mapper;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit1;
import com.whu.pojo.EnvirBenefit3;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EnvirBenefit3MapperTest extends FcxevaluationApplicationTests
{
    @Autowired
    EnvirBenefit3Mapper envirBenefit3Mapper;

    @Test
    public void queryScoreByProjectId()
    {
        EnvirBenefit3 envirBenefit3 = envirBenefit3Mapper.queryScoreByProjectIdAndState(1L,1, -1L);
        log.info("EnvirBenefit3MapperTest.queryScoreByProjectIdAndState: " + envirBenefit3);
    }

    @Test
    public void queryScoresByProjectId()
    {
        List<EnvirBenefit3> econoBenefits = envirBenefit3Mapper.queryScoresByProjectIdAndState(26L,1);
        log.info("EnvirBenefit3MapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
    }

    @Test
    public void insertScore()
    {
        EnvirBenefit3 envirBenefit3 = new EnvirBenefit3();
        envirBenefit3.setExpertId(38L);
        envirBenefit3.setProjectId(7L);
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
        int code = envirBenefit3Mapper.insertScore(envirBenefit3);
        log.info("EnvirBenefit3MapperTest.insertScore: " + code);
    }
    
    @Test
    public void updateScore()
    {
    	EnvirBenefit3 envirBenefit3 = envirBenefit3Mapper.queryScoreByProjectIdAndState(7L,1, 38L);
    	
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
        envirBenefit3.setS_Envir_System(8);
        envirBenefit3.setState(1);
        int code = envirBenefit3Mapper.updateScore(envirBenefit3);
        log.info("EnvirBenefit3MapperTest.insertScore: " + code);
    }
}*/
