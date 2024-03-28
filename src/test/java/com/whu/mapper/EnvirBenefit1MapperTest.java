/*package com.whu.mapper;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EnvirBenefit1;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EnvirBenefit1MapperTest extends FcxevaluationApplicationTests
{
    @Autowired
    EnvirBenefit1Mapper envirBenefit1Mapper;

    @Test
    public void queryScoreByProjectId()
    {
        EnvirBenefit1 envirBenefit1 = envirBenefit1Mapper.queryScoreByProjectIdAndState(1L,1, -1L);
        log.info("EnvirBenefit1MapperTest.queryScoreByProjectIdAndState: " + envirBenefit1);
    }

    @Test
    public void queryScoresByProjectId()
    {
        List<EnvirBenefit1> econoBenefits = envirBenefit1Mapper.queryScoresByProjectIdAndState(26L,1);
        log.info("EnvirBenefit1MapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
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
        
        envirBenefit1.setF_SaveWater(1);
        envirBenefit1.setS_SaveWater_System(1);
        envirBenefit1.setS_SaveWater_Tool(1);
        envirBenefit1.setS_SaveWater_Use(1);
        
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
        
        envirBenefit1.setF_Innovation(1);
        envirBenefit1.setS_Innovation_Structure(1);
        envirBenefit1.setS_Innovation_Management(1);
        envirBenefit1.setS_Innovation_Tech(1);
        
        envirBenefit1.setF_Humanity(1);
        envirBenefit1.setS_Humanity_People(1);
        envirBenefit1.setS_Humanity_GreenLive(1);
        envirBenefit1.setS_Humanity_GreenEdu(1);
        envirBenefit1.setS_Humanity_History(1);
        
        envirBenefit1.setF_Art(1);
        envirBenefit1.setS_Art_Design(1);
        int code = envirBenefit1Mapper.insertScore(envirBenefit1);
        log.info("EnvirBenefit1MapperTest.insertScore: " + code);
    }
    
    /*@Test
    public void updateScore()
    {
        EnvirBenefit1 envirBenefit1 = envirBenefit1Mapper.queryScoreByProjectIdAndState(1L, 1, -1L);
        
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
        
        envirBenefit1.setF_SaveWater(1);
        envirBenefit1.setS_SaveWater_System(1);
        envirBenefit1.setS_SaveWater_Tool(1);
        envirBenefit1.setS_SaveWater_Use(1);
        
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
        
        envirBenefit1.setF_Innovation(1);
        envirBenefit1.setS_Innovation_Structure(1);
        envirBenefit1.setS_Innovation_Management(1);
        envirBenefit1.setS_Innovation_Tech(1);
        
        envirBenefit1.setF_Humanity(1);
        envirBenefit1.setS_Humanity_People(1);
        envirBenefit1.setS_Humanity_GreenLive(1);
        envirBenefit1.setS_Humanity_GreenEdu(1);
        envirBenefit1.setS_Humanity_History(1);
        
        envirBenefit1.setF_Art(10);
        envirBenefit1.setS_Art_Design(5);
        int code = envirBenefit1Mapper.updateScore(envirBenefit1);
        log.info("EnvirBenefit1MapperTest.updateScore: " + code);
    }
}*/
