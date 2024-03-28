package com.whu.impl;

import com.whu.mapper.*;
import com.whu.pojo.*;
import com.whu.service.Type2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
public class Type2ServiceImpl implements Type2Service {
    @Autowired
    EnvirBenefit2Mapper envirBenefit2Mapper;

    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Autowired
    EconoBenefit2Mapper econoBenefit2Mapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectAssignmentMapper projectAssignmentMapper;

    /**
     * 返回提交成绩的结果
     *
     * @param envirBenefit2
     * @param socialBenefit
     * @param econoBenefit
     * @return 1：成功； 0：未知错误； -1：项目已经评审
     */
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int insertType2Score(EnvirBenefit2 envirBenefit2, SocialBenefit socialBenefit, EconoBenefit2 econoBenefit2,
                                float grade, String evaluation, float impression) {
        /**
         * 从前端获取到projectId和state
         * 如果表中已经存在记录说明该项目已经被评分过
         */
        Long projectId = econoBenefit2.getProjectId();
        Long expertId = econoBenefit2.getExpertId();
        int state = econoBenefit2.getState();

        //判断是否有权限
        ProjectAssignment projectAssignment =
                projectAssignmentMapper.queryAssignmentByProjectIdAndExpertIdAndState(projectId, expertId, state);
        if (projectAssignment == null) //说明管理员撤销分配给该专家该项目
            return -2;

        //判断是否已评分
        /*
        Map<String, Benefit> score = queryScoreByProjectIdAndState(projectId,state);
        EnvirBenefit1 eb = (EnvirBenefit1)score.get("envirBenefit1");
        SocialBenefit sb = (SocialBenefit)score.get("socialBenefit");
        EconoBenefit  ecb = (EconoBenefit)score.get("econoBenefit");

        if((eb != null) || (sb != null) || (ecb != null) )
            return -1;  //已评分返回-1*/
        if (projectAssignment.getFinish() == 1)
            return -1;

        Project project = projectMapper.queryProjectById(projectId);

        try {
            envirBenefit2Mapper.insertScore(envirBenefit2);
            socialBenefitMapper.insertScore(socialBenefit);
            econoBenefit2Mapper.insertScore(econoBenefit2);

            projectAssignment.setGrade(grade);
            projectAssignment.setEvaluation(evaluation);
            projectAssignment.setImpression(impression);
            projectAssignment.setFinish(1);
            projectAssignmentMapper.updateAssignment(projectAssignment);

            List<ProjectAssignment> projectAssignments = //查看该项目的分配到的专家是否已经全部评审完毕
                    projectAssignmentMapper.queryAssignmentsByProjectIdAndState(projectId, state);
            List<Float> gradeList = new ArrayList<>();
            for (ProjectAssignment assignment : projectAssignments) {
                if (assignment.getFinish() == 1)  //当前专家是否评分
                    gradeList.add(assignment.getGrade());
                else
                    break;                //如果存在一个没有评分的专家就直接跳出循环
            }
            int gradeNum = gradeList.size();
            if (projectAssignments.size() == gradeNum)  //数目相等说明该项目已经评审完毕
            {
                Collections.sort(gradeList);   //成绩升序排列
                float total = 0.0f;
                for (int i = 0; i < gradeNum; i++) {
                    total += gradeList.get(i);
                }
                if (state == 1)
                    /*if(gradeNum > 2)
                        project.setfGrade((total-gradeList.get(0)-gradeList.get(gradeNum-1)) / (gradeNum - 2 ));
                    else
                        project.setfGrade(total / (gradeNum));*/ //去掉最高分最低分的版本
                    project.setfGrade(total / (gradeNum)); //不去掉最高分最低分的版本
                else
                    /*if(gradeNum > 2)
                        project.setlGrade((total-gradeList.get(0)-gradeList.get(gradeNum-1)) / (gradeNum - 2 ));
                    else
                        project.setlGrade(total / (gradeNum));*/ //去掉最高分最低分的版本
                    project.setlGrade(total / (gradeNum)); //不去掉最高分最低分的版本
                projectMapper.updateProject(project);
            }
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }


    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public Map<String, Benefit> queryScoreByProjectIdAndState(Long projectId, int state, Long expertId) {
        Map<String, Benefit> result = new HashMap<>();
        try {
            EconoBenefit2 econoBenefit2 = econoBenefit2Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            EnvirBenefit2 envirBenefit2 = envirBenefit2Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            result.put("econoBenefit2", econoBenefit2);
            result.put("socialBenefit", socialBenefit);
            result.put("envirBenefit2", envirBenefit2);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public Map<String, Float> queryScoresByProjectIdAndState(Long projectId, int state) {
        Map<String, Float> result = new HashMap<>();
        try {
            List<ProjectAssignment> projectAssignments = projectAssignmentMapper.
                    queryAssignmentsByProjectIdAndStateOrderByExpertId(projectId, state);

            //Long maxGradeExpertId = projectAssignments.get(projectAssignments.size() - 1).getExpertId();
            //Long minGradeExpertId = projectAssignments.get(0).getExpertId();去掉最高分最低分的版本


            List<EconoBenefit2> econoBenefits = econoBenefit2Mapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            List<SocialBenefit> socialBenefits = socialBenefitMapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            List<EnvirBenefit2> envirBenefit2s = envirBenefit2Mapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);

            int size = econoBenefits.size();


            //经济效益各项
            float F_AssetPlan = 0.0f;//资产规划
            float S_AssetPlan_Para = 0.0f;

            float F_Industry = 0.0f;//资产运营
            float S_Industry_Early = 0.0f;
            float S_Industry_Rent = 0.0f;
            float S_Industry_Increment = 0.0f;
            float S_Industry_Manager = 0.0f;

            float F_Financial = 0.0f;//财务表现
            float S_Financial_Managerment = 0.0f;
            float S_Financial_Cash = 0.0f;
            float S_Financial_Operate = 0.0f;
            float S_Financial_Increment = 0.0f;

            //社会效益各项
            float F_Social = 0.0f;
            float S_Social_Develop = 0.0f;
            float S_Social_People = 0.0f;
            float S_Social_Green = 0.0f;

            float F_Work = 0.0f;
            float F_Work_Brand = 0.0f;
            float F_Work_Develop = 0.0f;

            //环境效益各项
            float F_LandPlan = 0.0f;
            float S_LandPlan_LandUse = 0.0f;
            float S_LandPlan_LayOut = 0.0f;

            float F_Eco = 0.0f;
            float S_Eco_Nature = 0.0f;
            float S_Eco_Envir = 0.0f;

            float F_GreenBulid = 0.0f;
            float S_GreenBuild_GreenBulid = 0.0f;

            float F_Res = 0.0f;
            float S_Res_Energy = 0.0f;
            float S_Res_Water = 0.0f;
            float S_Res_Res = 0.0f;
            float S_Res_Carbon = 0.0f;

            float F_GreenTransport = 0.0f;
            float S_GreenTransport_Trans = 0.0f;
            float S_GreenTransport_Road = 0.0f;
            float S_GreenTransport_Static = 0.0f;

            float F_InfoManager = 0.0f;
            float S_InfoManager_Urban = 0.0f;
            float S_InfoManager_Service = 0.0f;

            float F_Innovation = 0.0f;//创新项评价
            float S_Innovation = 0.0f;

            float F_Humanity = 0.0f;//人文
            float S_Humanity_People = 0.0f;
            float S_Humanity_GreenLive = 0.0f;
            float S_Humanity_GreenEdu = 0.0f;
            float S_Humanity_History = 0.0f;

            float F_Art = 0.0f;
            float S_Art_Bulid = 0.0f;
            float S_Art_Envir = 0.0f;

            float impression = 0.0f;

            for (int i = 0; i < size; ++i) {
                EnvirBenefit2 envirBenefit2 = envirBenefit2s.get(i);
                EconoBenefit2 econoBenefit2 = econoBenefits.get(i);
                SocialBenefit socialBenefit = socialBenefits.get(i);
                ProjectAssignment projectAssignment = projectAssignments.get(i);
                /*
                if(size > 2 &&
                        ((econoBenefit.getExpertId().equals(maxGradeExpertId))
                                || (econoBenefit.getExpertId().equals(minGradeExpertId))))
                    continue;*/ //去掉最高分最低分

                F_LandPlan += envirBenefit2.getF_LandPlan();
                S_LandPlan_LandUse += envirBenefit2.getS_LandPlan_LandUse();
                S_LandPlan_LayOut += envirBenefit2.getS_LandPlan_LayOut();
                F_Eco += envirBenefit2.getF_Eco();
                S_Eco_Nature += envirBenefit2.getS_Eco_Nature();
                S_Eco_Envir += envirBenefit2.getS_Eco_Envir();
                F_GreenBulid += envirBenefit2.getF_GreenBulid();
                S_GreenBuild_GreenBulid += envirBenefit2.getS_GreenBuild_GreenBulid();
                F_Res += envirBenefit2.getF_Res();
                S_Res_Energy += envirBenefit2.getS_Res_Energy();
                S_Res_Water += envirBenefit2.getS_Res_Water();
                S_Res_Res += envirBenefit2.getS_Res_Res();
                S_Res_Carbon += envirBenefit2.getS_Res_Carbon();
                F_GreenTransport += envirBenefit2.getF_GreenTransport();
                S_GreenTransport_Trans += envirBenefit2.getS_GreenTransport_Trans();
                S_GreenTransport_Road += envirBenefit2.getS_GreenTransport_Road();
                S_GreenTransport_Static += envirBenefit2.getS_GreenTransport_Static();
                F_InfoManager += envirBenefit2.getF_InfoManager();
                S_InfoManager_Urban += envirBenefit2.getS_InfoManager_Urban();
                S_InfoManager_Service += envirBenefit2.getS_InfoManager_Service();
                F_Innovation += envirBenefit2.getF_Innovation();
                S_Innovation += envirBenefit2.getS_Innovation();
                F_Humanity += envirBenefit2.getF_Humanity();
                S_Humanity_People += envirBenefit2.getS_Humanity_People();
                S_Humanity_GreenLive += envirBenefit2.getS_Humanity_GreenLive();
                S_Humanity_GreenEdu += envirBenefit2.getS_Humanity_GreenEdu();
                S_Humanity_History += envirBenefit2.getS_Humanity_History();
                F_Art += envirBenefit2.getF_Art();
                S_Art_Bulid += envirBenefit2.getS_Art_Bulid();
                S_Art_Envir += envirBenefit2.getS_Art_Envir();

                F_AssetPlan += econoBenefit2.getF_AssetPlan();
                S_AssetPlan_Para += econoBenefit2.getS_AssetPlan_Para();
                F_Industry += econoBenefit2.getF_Industry();
                S_Industry_Early += econoBenefit2.getS_Industry_Early();
                S_Industry_Rent += econoBenefit2.getS_Industry_Rent();
                S_Industry_Increment += econoBenefit2.getS_Industry_Increment();
                S_Industry_Manager += econoBenefit2.getS_Industry_Manager();
                F_Financial += econoBenefit2.getF_Financial();
                S_Financial_Managerment += econoBenefit2.getS_Financial_Managerment();
                S_Financial_Cash += econoBenefit2.getS_Financial_Cash();
                S_Financial_Operate += econoBenefit2.getS_Financial_Operate();
                S_Financial_Increment += econoBenefit2.getS_Financial_Increment();

                F_Social += socialBenefit.getF_Social();
                S_Social_Develop += socialBenefit.getS_Social_Develop();
                S_Social_People += socialBenefit.getS_Social_People();
                S_Social_Green += socialBenefit.getS_Social_Green();
                F_Work += socialBenefit.getF_Work();
                F_Work_Brand += socialBenefit.getF_Work_Brand();
                F_Work_Develop += socialBenefit.getF_Work_Develop();

                impression += projectAssignment.getImpression();
            }

            //size = size > 2? (size-2): size; 用于去掉最高分最低分的
            result.put("F_LandPlan", (float) (Math.round(F_LandPlan / size * 100)) / 100);
            result.put("S_LandPlan_LandUse", (float) (Math.round(S_LandPlan_LandUse / size * 100)) / 100);
            result.put("S_LandPlan_LayOut", (float) (Math.round(S_LandPlan_LayOut / size * 100)) / 100);
            result.put("F_Eco", (float) (Math.round(F_Eco / size * 100)) / 100);
            result.put("S_Eco_Nature", (float) (Math.round(S_Eco_Nature / size * 100)) / 100);
            result.put("S_Eco_Envir", (float) (Math.round(S_Eco_Envir / size * 100)) / 100);
            result.put("F_GreenBulid", (float) (Math.round(F_GreenBulid / size * 100)) / 100);
            result.put("S_GreenBuild_GreenBulid", (float) (Math.round(S_GreenBuild_GreenBulid / size * 100)) / 100);
            result.put("F_Res", (float) (Math.round(F_Res / size * 100)) / 100);
            result.put("S_Res_Energy", (float) (Math.round(S_Res_Energy / size * 100)) / 100);
            result.put("S_Res_Water", (float) (Math.round(S_Res_Water / size * 100)) / 100);
            result.put("S_Res_Res", (float) (Math.round(S_Res_Res / size * 100)) / 100);
            result.put("S_Res_Carbon", (float) (Math.round(S_Res_Carbon / size * 100)) / 100);
            result.put("F_GreenTransport", (float) (Math.round(F_GreenTransport / size * 100)) / 100);
            result.put("S_GreenTransport_Trans", (float) (Math.round(S_GreenTransport_Trans / size * 100)) / 100);
            result.put("S_GreenTransport_Road", (float) (Math.round(S_GreenTransport_Road / size * 100)) / 100);
            result.put("S_GreenTransport_Static", (float) (Math.round(S_GreenTransport_Static / size * 100)) / 100);
            result.put("F_InfoManager", (float) (Math.round(F_InfoManager / size * 100)) / 100);
            result.put("S_InfoManager_Urban", (float) (Math.round(S_InfoManager_Urban / size * 100)) / 100);
            result.put("S_InfoManager_Service", (float) (Math.round(S_InfoManager_Service / size * 100)) / 100);
            result.put("F_Innovation", (float) (Math.round(F_Innovation / size * 100)) / 100);
            result.put("S_Innovation", (float) (Math.round(S_Innovation / size * 100)) / 100);
            result.put("F_Humanity", (float) (Math.round(F_Humanity / size * 100)) / 100);
            result.put("S_Humanity_People", (float) (Math.round(S_Humanity_People / size * 100)) / 100);
            result.put("S_Humanity_GreenLive", (float) (Math.round(S_Humanity_GreenLive / size * 100)) / 100);
            result.put("S_Humanity_GreenEdu", (float) (Math.round(S_Humanity_GreenEdu / size * 100)) / 100);
            result.put("S_Humanity_History", (float) (Math.round(S_Humanity_History / size * 100)) / 100);
            result.put("F_Art", (float) (Math.round(F_Art / size * 100)) / 100);
            result.put("S_Art_Bulid", (float) (Math.round(S_Art_Bulid / size * 100)) / 100);
            result.put("S_Art_Envir", (float) (Math.round(S_Art_Envir / size * 100)) / 100);

            result.put("F_AssetPlan", (float) (Math.round(F_AssetPlan / size * 100)) / 100);
            result.put("S_AssetPlan_Para", (float) (Math.round(S_AssetPlan_Para / size * 100)) / 100);
            result.put("F_Industry", (float) (Math.round(F_Industry / size * 100)) / 100);
            result.put("S_Industry_Early", (float) (Math.round(S_Industry_Early / size * 100)) / 100);
            result.put("S_Industry_Rent", (float) (Math.round(S_Industry_Rent / size * 100)) / 100);
            result.put("S_Industry_Increment", (float) (Math.round(S_Industry_Increment / size * 100)) / 100);
            result.put("S_Industry_Manager", (float) (Math.round(S_Industry_Manager / size * 100)) / 100);
            result.put("F_Financial", (float) (Math.round(F_Financial / size * 100)) / 100);
            result.put("S_Financial_Managerment", (float) (Math.round(S_Financial_Managerment / size * 100)) / 100);
            result.put("S_Financial_Cash", (float) (Math.round(S_Financial_Cash / size * 100)) / 100);
            result.put("S_Financial_Operate", (float) (Math.round(S_Financial_Operate / size * 100)) / 100);
            result.put("S_Financial_Increment", (float) (Math.round(S_Financial_Increment / size * 100)) / 100);

            result.put("F_Social", (float) (Math.round(F_Social / size * 100)) / 100);
            result.put("S_Social_Develop", (float) (Math.round(S_Social_Develop / size * 100)) / 100);
            result.put("S_Social_People", (float) (Math.round(S_Social_People / size * 100)) / 100);
            result.put("S_Social_Green", (float) (Math.round(S_Social_Green / size * 100)) / 100);
            result.put("F_Work", (float) (Math.round(F_Work / size * 100)) / 100);
            result.put("F_Work_Brand", (float) (Math.round(F_Work_Brand / size * 100)) / 100);
            result.put("F_Work_Develop", (float) (Math.round(F_Work_Develop / size * 100)) / 100);

            result.put("impression", ((float) (Math.round(impression / size * 100))) / 100);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int alterType2Score(EnvirBenefit2 envirBenefit2, SocialBenefit socialBenefit, EconoBenefit2 econoBenefit2,
                               float grade) {
        try {
            envirBenefit2Mapper.updateScore(envirBenefit2);
            socialBenefitMapper.updateScore(socialBenefit);
            econoBenefit2Mapper.updateScore(econoBenefit2);
            Long projectId = econoBenefit2.getProjectId();
            ProjectAssignment projectAssignment = projectAssignmentMapper.
                    queryAssignmentByProjectIdAndExpertIdAndState(projectId, -1L, 3);
            projectAssignment.setGrade(grade);
            projectAssignmentMapper.updateAssignment(projectAssignment);
            Project project = projectMapper.queryProjectById(projectId);
            project.setFinalGrade(grade);
            projectMapper.updateProject(project);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
        return 1;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int updateType2Score(EnvirBenefit2 envirBenefit2, SocialBenefit socialBenefit, EconoBenefit2 econoBenefit2,
                                float grade, Long expertId, int state, String evaluation, float impression) {
        try {
            envirBenefit2Mapper.updateScore(envirBenefit2);
            socialBenefitMapper.updateScore(socialBenefit);
            econoBenefit2Mapper.updateScore(econoBenefit2);
            Long projectId = econoBenefit2.getProjectId();
            ProjectAssignment projectAssignment = projectAssignmentMapper.
                    queryAssignmentByProjectIdAndExpertIdAndState(projectId, expertId, state);
            projectAssignment.setEvaluation(evaluation);
            projectAssignment.setImpression(impression);
            projectAssignment.setGrade(grade);
            projectAssignmentMapper.updateAssignment(projectAssignment);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
        return 1;
    }
}
