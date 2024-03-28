package com.whu.impl;

import com.whu.mapper.*;
import com.whu.pojo.*;
import com.whu.service.Type3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
public class Type3ServiceImpl implements Type3Service {
    @Autowired
    EnvirBenefit3Mapper envirBenefit3Mapper;

    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Autowired
    EconoBenefit3Mapper econoBenefit3Mapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectAssignmentMapper projectAssignmentMapper;

    /**
     * 返回提交成绩的结果
     *
     * @param envirBenefit3
     * @param socialBenefit
     * @param econoBenefit
     * @return 1：成功； 0：未知错误； -1：项目已经评审
     */
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int insertType3Score(EnvirBenefit3 envirBenefit3, SocialBenefit socialBenefit, EconoBenefit3 econoBenefit3,
                                float grade, String evaluation, float impression) {
        /**
         * 从前端获取到projectId和state
         * 如果表中已经存在记录说明该项目已经被评分过
         */
        Long projectId = econoBenefit3.getProjectId();
        Long expertId = econoBenefit3.getExpertId();
        int state = econoBenefit3.getState();

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
            envirBenefit3Mapper.insertScore(envirBenefit3);
            socialBenefitMapper.insertScore(socialBenefit);
            econoBenefit3Mapper.insertScore(econoBenefit3);

            projectAssignment.setEvaluation(evaluation);
            projectAssignment.setImpression(impression);
            projectAssignment.setGrade(grade);
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
            EconoBenefit3 econoBenefit3 = econoBenefit3Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            EnvirBenefit3 envirBenefit3 = envirBenefit3Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            result.put("econoBenefit3", econoBenefit3);
            result.put("socialBenefit", socialBenefit);
            result.put("envirBenefit3", envirBenefit3);
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

            List<EconoBenefit3> econoBenefits = econoBenefit3Mapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            List<SocialBenefit> socialBenefits = socialBenefitMapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            List<EnvirBenefit3> envirBenefit3s = envirBenefit3Mapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            int size = econoBenefits.size();

            //社会效益各项
            float F_Social = 0.0f;
            float S_Social_Develop = 0.0f;
            float S_Social_People = 0.0f;
            float S_Social_Green = 0.0f;

            float F_Work = 0.0f;
            float F_Work_Brand = 0.0f;
            float F_Work_Develop = 0.0f;

            //环境效益
            float F_Function = 0.0f;
            float S_Function_Use = 0.0f;
            float S_Function_Service = 0.0f;
            float S_Function_Arrivable = 0.0f;
            float S_Function_Open = 0.0f;
            float S_Function_Safe = 0.0f;

            float F_Tech = 0.0f;
            float S_Tech_Operate = 0.0f;
            float S_Tech_Curing = 0.0f;
            float S_Tech_Material = 0.0f;

            float F_Human = 0.0f;
            float S_Human_Design = 0.0f;
            float S_Human_Explore = 0.0f;

            float F_Envir = 0.0f;
            float S_Envir_EnegrySave = 0.0f;
            float S_Envir_WaterSave = 0.0f;
            float S_Envir_Res = 0.0f;
            float S_Envir_System = 0.0f;

            //经济效益
            float F_AssetManager = 0.0f;
            float S_AssetManager_Early = 0.0f;
            float S_AssetManager_Service = 0.0f;
            float S_AssetManager_MoreAsset = 0.0f;

            float F_ProjectManager = 0.0f;
            float S_ProjectManager_Cost = 0.0f;
            float S_ProjectManager_Method = 0.0f;

            float impression = 0.0f;

            for (int i = 0; i < size; ++i) {
                EnvirBenefit3 envirBenefit3 = envirBenefit3s.get(i);
                EconoBenefit3 econoBenefit3 = econoBenefits.get(i);
                SocialBenefit socialBenefit = socialBenefits.get(i);
                ProjectAssignment projectAssignment = projectAssignments.get(i);
                /*
                if(size > 2 &&
                        ((econoBenefit.getExpertId().equals(maxGradeExpertId))
                                || (econoBenefit.getExpertId().equals(minGradeExpertId))))
                    continue;*/ //去掉最高分最低分

                F_Social += socialBenefit.getF_Social();
                S_Social_Develop += socialBenefit.getS_Social_Develop();
                S_Social_People += socialBenefit.getS_Social_People();
                S_Social_Green += socialBenefit.getS_Social_Green();
                F_Work += socialBenefit.getF_Work();
                F_Work_Brand += socialBenefit.getF_Work_Brand();
                F_Work_Develop += socialBenefit.getF_Work_Develop();

                F_Function += envirBenefit3.getF_Function();
                S_Function_Use += envirBenefit3.getS_Function_Use();
                S_Function_Service += envirBenefit3.getS_Function_Service();
                S_Function_Arrivable += envirBenefit3.getS_Function_Arrivable();
                S_Function_Open += envirBenefit3.getS_Function_Open();
                S_Function_Safe += envirBenefit3.getS_Function_Safe();
                F_Tech += envirBenefit3.getF_Tech();
                S_Tech_Operate += envirBenefit3.getS_Tech_Operate();
                S_Tech_Curing += envirBenefit3.getS_Tech_Curing();
                S_Tech_Material += envirBenefit3.getS_Tech_Material();
                F_Human += envirBenefit3.getF_Human();
                S_Human_Design += envirBenefit3.getS_Human_Design();
                S_Human_Explore += envirBenefit3.getS_Human_Explore();
                F_Envir += envirBenefit3.getF_Envir();
                S_Envir_EnegrySave += envirBenefit3.getS_Envir_EnegrySave();
                S_Envir_WaterSave += envirBenefit3.getS_Envir_WaterSave();
                S_Envir_Res += envirBenefit3.getS_Envir_Res();
                S_Envir_System += envirBenefit3.getS_Envir_System();

                F_AssetManager += econoBenefit3.getF_AssetManager();
                S_AssetManager_Early += econoBenefit3.getS_AssetManager_Early();
                S_AssetManager_Service += econoBenefit3.getS_AssetManager_Service();
                S_AssetManager_MoreAsset += econoBenefit3.getS_AssetManager_MoreAsset();
                F_ProjectManager += econoBenefit3.getF_ProjectManager();
                S_ProjectManager_Cost += econoBenefit3.getS_ProjectManager_Cost();
                S_ProjectManager_Method += econoBenefit3.getS_ProjectManager_Method();

                impression += projectAssignment.getImpression();

            }
            //size = size > 2? (size-2): size; 用于去掉最高分最低分的
            result.put("F_Social", (float) (Math.round(F_Social / size * 100)) / 100);
            result.put("S_Social_Develop", (float) (Math.round(S_Social_Develop / size * 100)) / 100);
            result.put("S_Social_People", (float) (Math.round(S_Social_People / size * 100)) / 100);
            result.put("S_Social_Green", (float) (Math.round(S_Social_Green / size * 100)) / 100);
            result.put("F_Work", (float) (Math.round(F_Work / size * 100)) / 100);
            result.put("F_Work_Brand", (float) (Math.round(F_Work_Brand / size * 100)) / 100);
            result.put("F_Work_Develop", (float) (Math.round(F_Work_Develop / size * 100)) / 100);

            result.put("F_Function", (float) (Math.round(F_Function / size * 100)) / 100);
            result.put("S_Function_Use", (float) (Math.round(S_Function_Use / size * 100)) / 100);
            result.put("S_Function_Service", (float) (Math.round(S_Function_Service / size * 100)) / 100);
            result.put("S_Function_Arrivable", (float) (Math.round(S_Function_Arrivable / size * 100)) / 100);
            result.put("S_Function_Open", (float) (Math.round(S_Function_Open / size * 100)) / 100);
            result.put("S_Function_Safe", (float) (Math.round(S_Function_Safe / size * 100)) / 100);
            result.put("F_Tech", (float) (Math.round(F_Tech / size * 100)) / 100);
            result.put("S_Tech_Operate", (float) (Math.round(S_Tech_Operate / size * 100)) / 100);
            result.put("S_Tech_Curing", (float) (Math.round(S_Tech_Curing / size * 100)) / 100);
            result.put("S_Tech_Material", (float) (Math.round(S_Tech_Material / size * 100)) / 100);
            result.put("F_Human", (float) (Math.round(F_Human / size * 100)) / 100);
            result.put("S_Human_Design", (float) (Math.round(S_Human_Design / size * 100)) / 100);
            result.put("S_Human_Explore", (float) (Math.round(S_Human_Explore / size * 100)) / 100);
            result.put("F_Envir", (float) (Math.round(F_Envir / size * 100)) / 100);
            result.put("S_Envir_EnegrySave", (float) (Math.round(S_Envir_EnegrySave / size * 100)) / 100);
            result.put("S_Envir_WaterSave", (float) (Math.round(S_Envir_WaterSave / size * 100)) / 100);
            result.put("S_Envir_Res", (float) (Math.round(S_Envir_Res / size * 100)) / 100);
            result.put("S_Envir_System", (float) (Math.round(S_Envir_System / size * 100)) / 100);

            result.put("F_AssetManager", (float) (Math.round(F_AssetManager / size * 100)) / 100);
            result.put("S_AssetManager_Early", (float) (Math.round(S_AssetManager_Early / size * 100)) / 100);
            result.put("S_AssetManager_Service", (float) (Math.round(S_AssetManager_Service / size * 100)) / 100);
            result.put("S_AssetManager_MoreAsset", (float) (Math.round(S_AssetManager_MoreAsset / size * 100)) / 100);
            result.put("F_ProjectManager", (float) (Math.round(F_ProjectManager / size * 100)) / 100);
            result.put("S_ProjectManager_Cost", (float) (Math.round(S_ProjectManager_Cost / size * 100)) / 100);
            result.put("S_ProjectManager_Method", (float) (Math.round(S_ProjectManager_Method / size * 100)) / 100);

            result.put("impression", ((float) (Math.round(impression / size * 100))) / 100);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int alterType3Score(EnvirBenefit3 envirBenefit3, SocialBenefit socialBenefit, EconoBenefit3 econoBenefit3,
                               float grade) {
        try {
            envirBenefit3Mapper.updateScore(envirBenefit3);
            socialBenefitMapper.updateScore(socialBenefit);
            econoBenefit3Mapper.updateScore(econoBenefit3);
            Long projectId = econoBenefit3.getProjectId();
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
    public int updateType3Score(EnvirBenefit3 envirBenefit3, SocialBenefit socialBenefit, EconoBenefit3 econoBenefit3,
                                float grade, Long expertId, int state, String evaluation, float impression) {
        try {
            envirBenefit3Mapper.updateScore(envirBenefit3);
            socialBenefitMapper.updateScore(socialBenefit);
            econoBenefit3Mapper.updateScore(econoBenefit3);
            Long projectId = econoBenefit3.getProjectId();
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
