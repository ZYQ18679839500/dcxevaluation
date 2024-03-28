/*package com.whu.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EconoBenefit2;
import com.whu.pojo.EconoBenefit3;

public class EconoBenefit3MapperTest extends FcxevaluationApplicationTests{
	@Autowired
    EconoBenefit3Mapper econoBenefit3Mapper;

    @Test
    public void queryScoreByProjectId()
    {
        EconoBenefit3 econoBenefit3 = econoBenefit3Mapper.queryScoreByProjectIdAndState(1L,1, -1L);
        log.info("EconoBenefitMapperTest.queryScoreByProjectId: " +  econoBenefit3.toString());
    }

    @Test
    public void queryScoresByProjectId()
    {
        List<EconoBenefit3> econoBenefits = econoBenefit3Mapper.queryScoresByProjectIdAndState(26L,1);
        log.info("EconoBenefitMapperTest.queryScoresByProjectId: " +  econoBenefits.toString());
    }

    @Test
    public void insertScore()
    {
        EconoBenefit3  econoBenefit3 = new EconoBenefit3();
        econoBenefit3.setExpertId(38L);
        econoBenefit3.setProjectId(7L);
        econoBenefit3.setState(1);
        econoBenefit3.setF_AssetManager(1);
        econoBenefit3.setS_AssetManager_Early(1);
        econoBenefit3.setS_AssetManager_Service(1);
        econoBenefit3.setS_AssetManager_MoreAsset(1);
        econoBenefit3.setF_ProjectManager(1);
        econoBenefit3.setS_ProjectManager_Cost(1);
        econoBenefit3.setS_ProjectManager_Method(1);    
        int code = econoBenefit3Mapper.insertScore(econoBenefit3);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
    
    @Test
    public void updateScore()
    {
    	EconoBenefit3 econoBenefit3 = econoBenefit3Mapper.queryScoreByProjectIdAndState(7L,1, 38L);
        econoBenefit3.setExpertId(-1L);
        econoBenefit3.setProjectId(1L);
        econoBenefit3.setState(1);
        econoBenefit3.setF_AssetManager(1);
        econoBenefit3.setS_AssetManager_Early(1);
        econoBenefit3.setS_AssetManager_Service(1);
        econoBenefit3.setS_AssetManager_MoreAsset(1);
        econoBenefit3.setF_ProjectManager(1);
        econoBenefit3.setS_ProjectManager_Cost(1);
        econoBenefit3.setS_ProjectManager_Method(8);    
        int code = econoBenefit3Mapper.updateScore(econoBenefit3);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}*/
