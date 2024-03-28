package com.whu.mapper;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit1;
import com.whu.pojo.EnvirBenefit4;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EnvirBenefit4MapperTest extends FcxevaluationApplicationTests {
    @Autowired
    EnvirBenefit4Mapper envirBenefit4Mapper;

    /*@Test
    public void queryScoreByProjectId()
    {
        EnvirBenefit4 envirBenefit4 = envirBenefit4Mapper.queryScoreByProjectIdAndState(10L,1, 38L);
        log.info("EnvirBenefit4MapperTest.queryScoreByProjectIdAndState: " + envirBenefit4.toString());
    }*/

    /*@Test
    public void queryScoresByProjectId()
    {
        List<EnvirBenefit4> econoBenefits = envirBenefit4Mapper.queryScoresByProjectIdAndState(1L,1);
        log.info("EnvirBenefit4MapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
    }*/

    /*@Test
    public void insertScore()
    {
    	EnvirBenefit4 envirBenefit4 = envirBenefit4Mapper.queryScoreByProjectIdAndState(10L,1, 38L);
    	//EnvirBenefit4 envirBenefit4=new EnvirBenefit4();
        envirBenefit4.setExpertId(38L);
        envirBenefit4.setProjectId(10L);
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
        envirBenefit4.setS_Tech_Progress(9);
        envirBenefit4.setState(1);
        int code = envirBenefit4Mapper.updateScore(envirBenefit4);
        log.info("EnvirBenefit4MapperTest.insertScore: " + code);
    }*/
}

