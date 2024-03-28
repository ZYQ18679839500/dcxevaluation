package com.whu.impl;

import com.whu.mapper.*;
import com.whu.service.*;
import com.whu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
public class Type1ServiceImpl implements Type1Service {
    @Autowired
    EnvirBenefit1Mapper envirBenefit1Mapper;

    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Autowired
    EconoBenefit1Mapper econoBenefit1Mapper;

    @Autowired
    ProjectService projectMapper;

    @Autowired
    ProjectAssignmentMapper projectAssignmentMapper;

    /**
     * 返回提交成绩的结果
     *
     * @param envirBenefit1
     * @param socialBenefit
     * @param econoBenefit
     * @return 1：成功； 0：未知错误； -1：项目已经评审; -2: 无权评审
     */

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int insertType1Score(EnvirBenefit1 envirBenefit1, SocialBenefit socialBenefit1, EconoBenefit1 econoBenefit1,
                                float grade, String evaluation, float impression) {
        /**
         * 从前端获取到projectId和state
         * 如果表中已经存在记录说明该项目已经被评分过
         */
        Long projectId = econoBenefit1.getProjectId();
        Long expertId = econoBenefit1.getExpertId();
        int state = econoBenefit1.getState();

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

            envirBenefit1Mapper.insertScore(envirBenefit1);
            socialBenefitMapper.insertScore(socialBenefit1);
            econoBenefit1Mapper.insertScore(econoBenefit1);

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
            EconoBenefit1 econoBenefit1 = econoBenefit1Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            EnvirBenefit1 envirBenefit1 = envirBenefit1Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            result.put("econoBenefit1", econoBenefit1);
            result.put("socialBenefit", socialBenefit);
            result.put("envirBenefit1", envirBenefit1);
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

            List<EconoBenefit1> econoBenefits = econoBenefit1Mapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            List<SocialBenefit> socialBenefits = socialBenefitMapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            List<EnvirBenefit1> envirBenefit1s = envirBenefit1Mapper.
                    queryScoresByProjectIdAndStateOrderByExpertId(projectId, state);
            int size = econoBenefits.size();

            //环境变量各项
            float F_SaveLands = 0.0f;//节地与室外环境
            float S_SaveLands_LandUse = 0.0f;
            float S_SaveLands_Outdoors = 0.0f;
            float S_SaveLands_Transport = 0.0f;
            float S_SaveLands_SiteDesign = 0.0f;
            float F_SaveEnergy = 0.0f;//节能与能源利用
            float S_SaveEnergy_Build = 0.0f;
            float S_SaveEnergy_Condition = 0.0f;
            float S_SaveEnergy_Light = 0.0f;
            float S_SaveEnergy_EnergyUse = 0.0f;
            float F_SaveWater = 0.0f;//节水与水资源利用
            float S_SaveWater_System = 0.0f;
            float S_SaveWater_Tool = 0.0f;
            float S_SaveWater_Use = 0.0f;
            float F_SaveRes = 0.0f;//节材与材料资源利用
            float S_SaveRes_SaveRes = 0.0f;
            float S_SaveRes_Choose = 0.0f;
            float F_Indoor = 0.0f;//室内环境质量
            float S_Indoor_Sound = 0.0f;
            float S_Indoor_Light = 0.0f;
            float S_Indoor_Hot = 0.0f;
            float S_Indoor_Atmo = 0.0f;
            float F_Construction = 0.0f;//施工管理
            float S_Construction_Envir = 0.0f;
            float S_Construction_Res = 0.0f;
            float S_Construction_Progress = 0.0f;
            float F_Operation = 0.0f;//运行管理
            float S_Operation_Management = 0.0f;
            float S_Operation_Tech = 0.0f;
            float S_Operation_Envir = 0.0f;
            float F_Innovation = 0.0f;//创新项评价
            float S_Innovation_Structure = 0.0f;
            float S_Innovation_Management = 0.0f;
            float S_Innovation_Tech = 0.0f;
            float F_Humanity = 0.0f;//人文
            float S_Humanity_People = 0.0f;
            float S_Humanity_GreenLive = 0.0f;
            float S_Humanity_GreenEdu = 0.0f;
            float S_Humanity_History = 0.0f;
            float F_Art = 0.0f;//艺术
            float S_Art_Design = 0.0f;

            //经济效益各项
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

            //社会效益各项
            float F_Social = 0.0f;
            float S_Social_Develop = 0.0f;
            float S_Social_People = 0.0f;
            float S_Social_Green = 0.0f;

            float F_Work = 0.0f;
            float F_Work_Brand = 0.0f;
            float F_Work_Develop = 0.0f;

            float impression = 0.0f;

            for (int i = 0; i < size; ++i) {
                EnvirBenefit1 envirBenefit1 = envirBenefit1s.get(i);
                EconoBenefit1 econoBenefit1 = econoBenefits.get(i);
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

                F_AssetPlan += econoBenefit1.getF_AssetPlan();
                S_AssetPlan_Para += econoBenefit1.getS_AssetPlan_Para();
                F_AssetManagerment += econoBenefit1.getF_AssetManagerment();
                S_AssetManagerment_Early += econoBenefit1.getS_AssetManagerment_Early();
                S_Managerment_Rent += econoBenefit1.getS_Managerment_Rent();
                S_Managerment_Increment += econoBenefit1.getS_Managerment_Increment();
                S_Managerment_Manager += econoBenefit1.getS_Managerment_Manager();
                F_Financial += econoBenefit1.getF_Financial();
                S_Financial_Managerment += econoBenefit1.getS_Financial_Managerment();
                S_Financial_Cash += econoBenefit1.getS_Financial_Cash();
                S_Financial_Operate += econoBenefit1.getS_Financial_Operate();
                S_Financial_Increment += econoBenefit1.getS_Financial_Increment();

                F_SaveLands += envirBenefit1.getF_SaveLands();
                S_SaveLands_LandUse += envirBenefit1.getS_SaveLands_LandUse();
                S_SaveLands_Outdoors += envirBenefit1.getS_SaveLands_Outdoors();
                S_SaveLands_Transport += envirBenefit1.getS_SaveLands_Transport();
                S_SaveLands_SiteDesign += envirBenefit1.getS_SaveLands_SiteDesign();
                F_SaveEnergy += envirBenefit1.getF_SaveEnergy();
                S_SaveEnergy_Build += envirBenefit1.getS_SaveEnergy_Build();
                S_SaveEnergy_Condition += envirBenefit1.getS_SaveEnergy_Condition();
                S_SaveEnergy_Light += envirBenefit1.getS_SaveEnergy_Light();
                S_SaveEnergy_EnergyUse += envirBenefit1.getS_SaveEnergy_EnergyUse();
                F_SaveWater += envirBenefit1.getF_SaveWater();
                S_SaveWater_System += envirBenefit1.getS_SaveWater_System();
                S_SaveWater_Tool += envirBenefit1.getS_SaveWater_Tool();
                S_SaveWater_Use += envirBenefit1.getS_SaveWater_Use();
                F_SaveRes += envirBenefit1.getF_SaveRes();
                S_SaveRes_SaveRes += envirBenefit1.getS_SaveRes_SaveRes();
                S_SaveRes_Choose += envirBenefit1.getS_SaveRes_Choose();
                F_Indoor += envirBenefit1.getF_Indoor();
                S_Indoor_Sound += envirBenefit1.getS_Indoor_Sound();
                S_Indoor_Light += envirBenefit1.getS_Indoor_Light();
                S_Indoor_Hot += envirBenefit1.getS_Indoor_Hot();
                S_Indoor_Atmo += envirBenefit1.getS_Indoor_Atmo();
                F_Construction += envirBenefit1.getF_Construction();
                S_Construction_Envir += envirBenefit1.getS_Construction_Envir();
                S_Construction_Res += envirBenefit1.getS_Construction_Res();
                S_Construction_Progress += envirBenefit1.getS_Construction_Progress();
                F_Operation += envirBenefit1.getF_Operation();
                S_Operation_Management += envirBenefit1.getS_Operation_Management();
                S_Operation_Tech += envirBenefit1.getS_Operation_Tech();
                S_Operation_Envir += envirBenefit1.getS_Operation_Envir();
                F_Innovation += envirBenefit1.getF_Innovation();
                S_Innovation_Structure += envirBenefit1.getS_Innovation_Structure();
                S_Innovation_Management += envirBenefit1.getS_Innovation_Management();
                S_Innovation_Tech += envirBenefit1.getS_Innovation_Tech();
                F_Humanity += envirBenefit1.getF_Humanity();
                S_Humanity_People += envirBenefit1.getS_Humanity_People();
                S_Humanity_GreenLive += envirBenefit1.getS_Humanity_GreenLive();
                S_Humanity_GreenEdu += envirBenefit1.getS_Humanity_GreenEdu();
                S_Humanity_History += envirBenefit1.getS_Humanity_History();
                F_Art += envirBenefit1.getF_Art();
                S_Art_Design += envirBenefit1.getS_Art_Design();

                impression += projectAssignment.getImpression();
            }
            //size = size > 2? (size-2): size; 用于去掉最高分最低分的
            result.put("F_AssetPlan", ((float) (Math.round(F_AssetPlan / size * 100))) / 100);
            result.put("S_AssetPlan_Para", ((float) (Math.round(S_AssetPlan_Para / size * 100))) / 100);
            result.put("F_AssetManagerment", ((float) (Math.round(F_AssetManagerment / size * 100))) / 100);
            result.put("S_AssetManagerment_Early", ((float) (Math.round(S_AssetManagerment_Early / size * 100))) / 100);
            result.put("S_Managerment_Rent", ((float) (Math.round(S_Managerment_Rent / size * 100))) / 100);
            result.put("S_Managerment_Increment", ((float) (Math.round(S_Managerment_Increment / size * 100))) / 100);
            result.put("S_Managerment_Manager", ((float) (Math.round(S_Managerment_Manager / size * 100))) / 100);
            result.put("F_Financial", ((float) (Math.round(F_Financial / size * 100)) / 100));
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

            result.put("F_SaveLands", ((float) (Math.round(F_SaveLands / size * 100))) / 100);
            result.put("S_SaveLands_LandUse", ((float) (Math.round(S_SaveLands_LandUse / size * 100))) / 100);
            result.put("S_SaveLands_Outdoors", ((float) (Math.round(S_SaveLands_Outdoors / size * 100))) / 100);
            result.put("S_SaveLands_Transport", ((float) (Math.round(S_SaveLands_Transport / size * 100))) / 100);
            result.put("S_SaveLands_SiteDesign", ((float) (Math.round(S_SaveLands_SiteDesign / size * 100))) / 100);
            result.put("F_SaveEnergy", ((float) (Math.round(F_SaveEnergy / size * 100))) / 100);
            result.put("S_SaveEnergy_Build", ((float) (Math.round(S_SaveEnergy_Build / size * 100))) / 100);
            result.put("S_SaveEnergy_Condition", ((float) (Math.round(S_SaveEnergy_Condition / size * 100))) / 100);
            result.put("S_SaveEnergy_Light", ((float) (Math.round(S_SaveEnergy_Light / size * 100))) / 100);
            result.put("S_SaveEnergy_EnergyUse", ((float) (Math.round(S_SaveEnergy_EnergyUse / size * 100))) / 100);
            result.put("F_SaveWater", ((float) (Math.round(F_SaveWater / size * 100))) / 100);
            result.put("S_SaveWater_System", ((float) (Math.round(S_SaveWater_System / size * 100))) / 100);
            result.put("S_SaveWater_Tool", ((float) (Math.round(S_SaveWater_Tool / size * 100))) / 100);
            result.put("S_SaveWater_Use", ((float) (Math.round(S_SaveWater_Use / size * 100))) / 100);
            result.put("F_SaveRes", ((float) (Math.round(F_SaveRes / size * 100))) / 100);
            result.put("S_SaveRes_SaveRes", ((float) (Math.round(S_SaveRes_SaveRes / size * 100))) / 100);
            result.put("S_SaveRes_Choose", ((float) (Math.round(S_SaveRes_Choose / size * 100))) / 100);
            result.put("F_Indoor", ((float) (Math.round(F_Indoor / size * 100))) / 100);
            result.put("S_Indoor_Sound", ((float) (Math.round(S_Indoor_Sound / size * 100))) / 100);
            result.put("S_Indoor_Light", ((float) (Math.round(S_Indoor_Light / size * 100))) / 100);
            result.put("S_Indoor_Hot", ((float) (Math.round(S_Indoor_Hot / size * 100))) / 100);
            result.put("S_Indoor_Atmo", ((float) (Math.round(S_Indoor_Atmo / size * 100))) / 100);
            result.put("F_Construction", ((float) (Math.round(F_Construction / size * 100))) / 100);
            result.put("S_Construction_Envir", ((float) (Math.round(S_Construction_Envir / size * 100))) / 100);
            result.put("S_Construction_Res", ((float) (Math.round(S_Construction_Res / size * 100))) / 100);
            result.put("S_Construction_Progress", ((float) (Math.round(S_Construction_Progress / size * 100))) / 100);
            result.put("F_Operation", ((float) (Math.round(F_Operation / size * 100))) / 100);
            result.put("S_Operation_Management", ((float) (Math.round(S_Operation_Management / size * 100))) / 100);
            result.put("S_Operation_Tech", ((float) (Math.round(S_Operation_Tech / size * 100))) / 100);
            result.put("S_Operation_Envir", ((float) (Math.round(S_Operation_Envir / size * 100))) / 100);
            result.put("F_Innovation", ((float) (Math.round(F_Innovation / size * 100))) / 100);
            result.put("S_Innovation_Structure", ((float) (Math.round(S_Innovation_Structure / size * 100))) / 100);
            result.put("S_Innovation_Management", ((float) (Math.round(S_Innovation_Management / size * 100))) / 100);
            result.put("S_Innovation_Tech", ((float) (Math.round(S_Innovation_Tech / size * 100))) / 100);
            result.put("F_Humanity", ((float) (Math.round(F_Humanity / size * 100))) / 100);
            result.put("S_Humanity_People", ((float) (Math.round(S_Humanity_People / size * 100))) / 100);
            result.put("S_Humanity_GreenLive", ((float) (Math.round(S_Humanity_GreenLive / size * 100))) / 100);
            result.put("S_Humanity_GreenEdu", ((float) (Math.round(S_Humanity_GreenEdu / size * 100))) / 100);
            result.put("S_Humanity_History", ((float) (Math.round(S_Humanity_History / size * 100))) / 100);
            result.put("F_Art", ((float) (Math.round(F_Art / size * 100))) / 100);
            result.put("S_Art_Design", ((float) (Math.round(S_Art_Design / size * 100))) / 100);

            result.put("impression", ((float) (Math.round(impression / size * 100))) / 100);

        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public int alterType1Score(EnvirBenefit1 envirBenefit1, SocialBenefit socialBenefit, EconoBenefit1 econoBenefit1,
                               float grade) {
        try {
            envirBenefit1Mapper.updateScore(envirBenefit1);
            socialBenefitMapper.updateScore(socialBenefit);
            econoBenefit1Mapper.updateScore(econoBenefit1);
            Long projectId = econoBenefit1.getProjectId();
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
    public int updateType1Score(EnvirBenefit1 envirBenefit1, SocialBenefit socialBenefit, EconoBenefit1 econoBenefit1,
                                float grade, Long expertId, int state, String evaluation, float impression) {
        try {
            socialBenefitMapper.updateScore(socialBenefit);
            envirBenefit1Mapper.updateScore(envirBenefit1);
            econoBenefit1Mapper.updateScore(econoBenefit1);
            Long projectId = econoBenefit1.getProjectId();
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
