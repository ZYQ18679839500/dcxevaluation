package com.whu.impl;

import com.whu.mapper.*;
import com.whu.pojo.*;
import com.whu.service.Type4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
public class Type4ServiceImpl implements Type4Service {
    @Autowired
    EnvirBenefit4Mapper envirBenefit4Mapper;

    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Autowired
    EconoBenefit4Mapper econoBenefit4Mapper;

    @Autowired
    ProjectMapper projectMapper;


    @Autowired
    ProjectAssignmentMapper projectAssignmentMapper;

    /**
     * 返回提交成绩的结果
     *
     * @param envirBenefit4
     * @param socialBenefit
     * @param econoBenefit
     * @return 1：成功； 0：未知错误； -1：项目已经评审
     */
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int insertType4Score(EnvirBenefit4 envirBenefit4, SocialBenefit socialBenefit, EconoBenefit4 econoBenefit4,
                                float grade, String evaluation, float impression) {
        /**
         * 从前端获取到projectId和state
         * 如果表中已经存在记录说明该项目已经被评分过
         */
        Long projectId = econoBenefit4.getProjectId();
        Long expertId = econoBenefit4.getExpertId();
        int state = econoBenefit4.getState();

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
            envirBenefit4Mapper.insertScore(envirBenefit4);
            socialBenefitMapper.insertScore(socialBenefit);
            econoBenefit4Mapper.insertScore(econoBenefit4);

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
            EconoBenefit4 econoBenefit4 = econoBenefit4Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            EnvirBenefit4 envirBenefit4 = envirBenefit4Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            result.put("econoBenefit4", econoBenefit4);
            result.put("socialBenefit", socialBenefit);
            result.put("envirBenefit4", envirBenefit4);
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


            List<EconoBenefit4> econoBenefit4s = econoBenefit4Mapper.queryScoresByProjectIdAndState(projectId, state);
            List<SocialBenefit> socialBenefits = socialBenefitMapper.queryScoresByProjectIdAndState(projectId, state);
            List<EnvirBenefit4> envirBenefit4s = envirBenefit4Mapper.queryScoresByProjectIdAndState(projectId, state);
            int size = econoBenefit4s.size();

            //社会效益各项
            float F_Social = 0.0f;
            float S_Social_Develop = 0.0f;
            float S_Social_People = 0.0f;
            float S_Social_Green = 0.0f;

            float F_Work = 0.0f;
            float F_Work_Brand = 0.0f;
            float F_Work_Develop = 0.0f;

            //环境效益
            float F_PhysicalEnvir = 0.0f;
            float S_PhysicalEnvir_Sound = 0.0f;
            float S_PhysicalEnvir_Light = 0.0f;
            float S_PhysicalEnvir_Hot = 0.0f;
            float S_PhysicalEnvir_Wind = 0.0f;

            float F_HumanEnvir = 0.0f;
            float S_HumanEnvir_Function = 0.0f;
            float S_HumanEnvir_Beauty = 0.0f;
            float S_HumanEnvir_Explore = 0.0f;

            float F_Decorate = 0.0f;
            float S_DecorateDetails = 0.0f;
            float S_DecorateMaterial = 0.0f;

            float F_Tech = 0.0f;
            float S_Tech_Envir = 0.0f;
            float S_Tech_Res = 0.0f;
            float S_Tech_Progress = 0.0f;

            //经济效益
            float F_AssetPlan = 0.0f;//资产规划
            float S_AssetPlan_Para = 0.0f;

            float F_AssetManagerment = 0.0f;//资产运营
            float S_AssetManagerment_Early = 0.0f;
            float S_Managerment_Rent = 0.0f;
            float S_Managerment_Increment = 0.0f;
            float S_Managerment_Manager = 0.0f;

            float F_Financial = 0.0f;//财务表现
            float S_Financial_Managerment = 0.0f;
            float S_Financial_Cash = 0.0f;
            float S_Financial_Operate = 0.0f;
            float S_Financial_Increment = 0.0f;

            float impression = 0.0f;
            for (int i = 0; i < size; ++i) {
                EnvirBenefit4 envirBenefit4 = envirBenefit4s.get(i);
                EconoBenefit4 econoBenefit4 = econoBenefit4s.get(i);
                SocialBenefit socialBenefit = socialBenefits.get(i);
                ProjectAssignment projectAssignment = projectAssignments.get(i);
                /*
                if(size > 2 &&
                        ((econoBenefit.getExpertId().equals(maxGradeExpertId))
                                || (econoBenefit.getExpertId().equals(minGradeExpertId))))
                    continue;*/ //去掉最高分最低分

                F_PhysicalEnvir += envirBenefit4.getF_PhysicalEnvir();
                S_PhysicalEnvir_Sound += envirBenefit4.getS_PhysicalEnvir_Sound();
                S_PhysicalEnvir_Light += envirBenefit4.getS_PhysicalEnvir_Light();
                S_PhysicalEnvir_Hot += envirBenefit4.getS_PhysicalEnvir_Hot();
                S_PhysicalEnvir_Wind += envirBenefit4.getS_PhysicalEnvir_Wind();
                F_HumanEnvir += envirBenefit4.getF_HumanEnvir();
                S_HumanEnvir_Function += envirBenefit4.getS_HumanEnvir_Function();
                S_HumanEnvir_Beauty += envirBenefit4.getS_HumanEnvir_Beauty();
                S_HumanEnvir_Explore += envirBenefit4.getS_HumanEnvir_Explore();
                F_Decorate += envirBenefit4.getF_Decorate();
                S_DecorateDetails += envirBenefit4.getS_DecorateDetails();
                S_DecorateMaterial += envirBenefit4.getS_DecorateMaterial();
                F_Tech += envirBenefit4.getF_Tech();
                S_Tech_Envir += envirBenefit4.getS_Tech_Envir();
                S_Tech_Res += envirBenefit4.getS_Tech_Res();
                S_Tech_Progress += envirBenefit4.getS_Tech_Progress();

                F_Social += socialBenefit.getF_Social();
                S_Social_Develop += socialBenefit.getS_Social_Develop();
                S_Social_People += socialBenefit.getS_Social_People();
                S_Social_Green += socialBenefit.getS_Social_Green();
                F_Work += socialBenefit.getF_Work();
                F_Work_Brand += socialBenefit.getF_Work_Brand();
                F_Work_Develop += socialBenefit.getF_Work_Develop();

                F_AssetPlan += econoBenefit4.getF_AssetPlan();
                S_AssetPlan_Para += econoBenefit4.getS_AssetPlan_Para();
                F_AssetManagerment += econoBenefit4.getF_AssetManagerment();
                S_AssetManagerment_Early += econoBenefit4.getS_AssetManagerment_Early();
                S_Managerment_Rent += econoBenefit4.getS_Managerment_Rent();
                S_Managerment_Increment += econoBenefit4.getS_Managerment_Increment();
                S_Managerment_Manager += econoBenefit4.getS_Managerment_Manager();
                F_Financial += econoBenefit4.getF_Financial();
                S_Financial_Managerment += econoBenefit4.getS_Financial_Managerment();
                S_Financial_Cash += econoBenefit4.getS_Financial_Cash();
                S_Financial_Operate += econoBenefit4.getS_Financial_Operate();
                S_Financial_Increment += econoBenefit4.getS_Financial_Increment();

                impression += projectAssignment.getImpression();
            }
            //size = size > 2? (size-2): size; 用于去掉最高分最低分的

            result.put("F_PhysicalEnvir", ((float) (Math.round(F_PhysicalEnvir / size * 100))) / 100);
            result.put("S_PhysicalEnvir_Sound", ((float) (Math.round(S_PhysicalEnvir_Sound / size * 100))) / 100);
            result.put("S_PhysicalEnvir_Light", ((float) (Math.round(S_PhysicalEnvir_Light / size * 100))) / 100);
            result.put("S_PhysicalEnvir_Hot", ((float) (Math.round(S_PhysicalEnvir_Hot / size * 100))) / 100);
            result.put("S_PhysicalEnvir_Wind", ((float) (Math.round(S_PhysicalEnvir_Wind / size * 100))) / 100);
            result.put("F_HumanEnvir", ((float) (Math.round(F_HumanEnvir / size * 100))) / 100);
            result.put("S_HumanEnvir_Function", ((float) (Math.round(S_HumanEnvir_Function / size * 100))) / 100);
            result.put("S_HumanEnvir_Beauty", ((float) (Math.round(S_HumanEnvir_Beauty / size * 100))) / 100);
            result.put("S_HumanEnvir_Explore", ((float) (Math.round(S_HumanEnvir_Explore / size * 100))) / 100);
            result.put("F_Decorate", ((float) (Math.round(F_Decorate / size * 100))) / 100);
            result.put("S_DecorateDetails", ((float) (Math.round(S_DecorateDetails / size * 100))) / 100);
            result.put("S_DecorateMaterial", ((float) (Math.round(S_DecorateMaterial / size * 100))) / 100);
            result.put("F_Tech", ((float) (Math.round(F_Tech / size * 100))) / 100);
            result.put("S_Tech_Envir", ((float) (Math.round(S_Tech_Envir / size * 100))) / 100);
            result.put("S_Tech_Res", ((float) (Math.round(S_Tech_Res / size * 100))) / 100);
            result.put("S_Tech_Progress", ((float) (Math.round(S_Tech_Progress / size * 100))) / 100);

            result.put("F_AssetPlan", ((float) (Math.round(F_AssetPlan / size * 100))) / 100);
            result.put("S_AssetPlan_Para", ((float) (Math.round(S_AssetPlan_Para / size * 100))) / 100);
            result.put("F_AssetManagerment", ((float) (Math.round(F_AssetManagerment / size * 100))) / 100);
            result.put("S_AssetManagerment_Early", ((float) (Math.round(S_AssetManagerment_Early / size * 100))) / 100);
            result.put("S_Managerment_Rent", ((float) (Math.round(S_Managerment_Rent / size * 100))) / 100);
            result.put("S_Managerment_Increment", ((float) (Math.round(S_Managerment_Increment / size * 100))) / 100);
            result.put("S_Managerment_Manager", ((float) (Math.round(S_Managerment_Manager / size * 100))) / 100);
            result.put("F_Financial", ((float) (Math.round(F_Financial / size * 100))) / 100);
            result.put("S_Financial_Managerment", ((float) (Math.round(S_Financial_Managerment / size * 100))) / 100);
            result.put("S_Financial_Cash", ((float) (Math.round(S_Financial_Cash / size * 100))) / 100);
            result.put("S_Financial_Operate", ((float) (Math.round(S_Financial_Operate / size * 100))) / 100);
            result.put("S_Financial_Increment", ((float) (Math.round(S_Financial_Increment / size * 100))) / 100);

            result.put("F_Social", ((float) (Math.round(F_Social / size * 100))) / 100);
            result.put("S_Social_Develop", ((float) (Math.round(S_Social_Develop / size * 100))) / 100);
            result.put("S_Social_People", ((float) (Math.round(S_Social_People / size * 100))) / 100);
            result.put("S_Social_Green", ((float) (Math.round(S_Social_Green / size * 100))) / 100);
            result.put("F_Work", ((float) (Math.round(F_Work / size * 100))) / 100);
            result.put("F_Work_Brand", ((float) (Math.round(F_Work_Brand / size * 100))) / 100);
            result.put("F_Work_Develop", ((float) (Math.round(F_Work_Develop / size * 100))) / 100);

            result.put("impression", ((float) (Math.round(impression / size * 100))) / 100);

        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int alterType4Score(EnvirBenefit4 envirBenefit4, SocialBenefit socialBenefit, EconoBenefit4 econoBenefit4,
                               float grade) {
        try {
            envirBenefit4Mapper.updateScore(envirBenefit4);
            socialBenefitMapper.updateScore(socialBenefit);
            econoBenefit4Mapper.updateScore(econoBenefit4);
            Long projectId = econoBenefit4.getProjectId();
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
    public int updateType4Score(EnvirBenefit4 envirBenefit4, SocialBenefit socialBenefit, EconoBenefit4 econoBenefit4,
                                float grade, Long expertId, int state, String evaluation, float impression) {
        try {
            envirBenefit4Mapper.updateScore(envirBenefit4);
            socialBenefitMapper.updateScore(socialBenefit);
            econoBenefit4Mapper.updateScore(econoBenefit4);
            Long projectId = econoBenefit4.getProjectId();
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
