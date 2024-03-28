package com.whu.service;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.EconoBenefit2;
import com.whu.pojo.EconoBenefit3;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class EconoBenefit3ServiceTest extends FcxevaluationApplicationTests {
    @Autowired
    EconoBenefit3Service econoBenefit3Service;

    @Test
    public void queryScoreByProjectId() {
        EconoBenefit3 econoBenefit3 = econoBenefit3Service.queryScoreByProjectIdAndState(1L, 1, -1L);
        log.info("EconoBenefitServiceTest.queryScoreByProjectId: " + econoBenefit3.toString());
    }

    @Test
    public void insertScore() {
        EconoBenefit3 econoBenefit3 = new EconoBenefit3();
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
        int code = econoBenefit3Service.insertScore(econoBenefit3);
        log.info("SocialBenefitMapperTest.insertScore: " + code);
    }
}

