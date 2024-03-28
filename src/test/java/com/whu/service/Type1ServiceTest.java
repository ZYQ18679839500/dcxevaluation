/*package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.mapper.EconoBenefit1Mapper;
import com.whu.mapper.EnvirBenefit1Mapper;
import com.whu.mapper.SocialBenefitMapper;
import com.whu.pojo.Benefit;
import com.whu.pojo.EconoBenefit1;
import com.whu.pojo.EnvirBenefit1;
import com.whu.pojo.SocialBenefit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class Type1ServiceTest extends FcxevaluationApplicationTests
{
    @Autowired
    Type1Service type1Service;
    
    @Autowired
    EconoBenefit1Mapper econoBenefit1Mapper;

    @Autowired
    EnvirBenefit1Mapper envirBenefit1Mapper;
    
    @Autowired
    SocialBenefitMapper socialBenefitMapper;
    
    @Test
    public void insertType1Score()
    {
        EconoBenefit1 econoBenefit1 = new EconoBenefit1();
        econoBenefit1.setExpertId(-1L);
        econoBenefit1.setProjectId(883L);
        econoBenefit1.setState(1);
        econoBenefit1.setF_AssetPlan(1);
        econoBenefit1.setS_AssetPlan_Para(1);
        econoBenefit1.setF_AssetManagerment(1);
        econoBenefit1.setS_AssetManagerment_Early(1);
        econoBenefit1.setS_Managerment_Rent(1);
        econoBenefit1.setS_Managerment_Increment(1);
        econoBenefit1.setS_Managerment_Manager(1);
        econoBenefit1.setF_Financial(1);
        econoBenefit1.setS_Financial_Managerment(1);
        econoBenefit1.setS_Financial_Cash(1);
        econoBenefit1.setS_Financial_Operate(1);
        econoBenefit1.setS_Financial_Increment(1);    

        EnvirBenefit1 envirBenefit1 = new EnvirBenefit1();
        envirBenefit1.setExpertId(-1L);
        envirBenefit1.setProjectId(883L);
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
        
		String evaluation="还好";
		float impression=100;
        
        float grade = 95.0f;

        int code = type1Service.insertType1Score(envirBenefit1,socialBenefit,econoBenefit1,grade,evaluation,impression);
        log.info("Type1ServiceTest.insertType1Score: " + code);
    }

    @Test
    public void queryScoreByProjetIdAndState()
    {
        Map<String, Benefit> score = type1Service.queryScoreByProjectIdAndState(26L,1, 8L);
        if(score == null)
        {
            log.info("Type1ServiceTest.queryScoreByProjetIdAndState: " + 1);
        }
    }
    
    @Test
    public void updateTyp1(){
		EconoBenefit1 econoBenefit1 = econoBenefit1Mapper.queryScoreByProjectIdAndState(883L,1,-1L);
		EnvirBenefit1 envirBenefit1=envirBenefit1Mapper.queryScoreByProjectIdAndState(883L, 1, -1L);
		SocialBenefit socialBenefit=socialBenefitMapper.queryScoreByProjectIdAndState(883L, 1, -1L);
		
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
        envirBenefit1.setS_Art_Design(50);
		
		
		float grade=(float) 99.999;
		int state=1;
		String evaluation="嘿嘿";
		float impression=100;
		
		type1Service.updateType1Score(envirBenefit1, socialBenefit, econoBenefit1, grade,-1L, state, evaluation, impression);
		
    }
}
*/