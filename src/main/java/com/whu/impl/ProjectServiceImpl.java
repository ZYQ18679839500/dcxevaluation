package com.whu.impl;

import com.whu.mapper.*;
import com.whu.pojo.*;
import com.whu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    PrizeMapper prizeMapper;

    @Autowired
    ProjectAssignmentMapper projectAssignmentMapper;

    @Autowired
    Type1Service type1Service;

    @Autowired
    Type2Service type2Service;

    @Autowired
    Type3Service type3Service;

    @Autowired
    Type4Service type4Service;

    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Autowired
    EnvirBenefit1Mapper envirBenefit1Mapper;

    @Autowired
    EnvirBenefit2Mapper envirBenefit2Mapper;

    @Autowired
    EnvirBenefit3Mapper envirBenefit3Mapper;

    @Autowired
    EnvirBenefit4Mapper envirBenefit4Mapper;

    @Autowired
    EconoBenefit1Mapper econoBenefit1Mapper;

    @Autowired
    EconoBenefit2Mapper econoBenefit2Mapper;

    @Autowired
    EconoBenefit3Mapper econoBenefit3Mapper;

    @Autowired
    EconoBenefit4Mapper econoBenefit4Mapper;


    @Override
    public Project queryProjectById(Long id) {
        return projectMapper.queryProjectById(id);
    }


    @Override
    public int updateProject(Project project) {
        return projectMapper.updateProject(project);
    }


    @Override
    public List<Project> queryProjectsByExpertIdAndState(Long expertId, int state) {
        List<Long> projectIdList = projectAssignmentMapper.queryProjectIdsByExpertIdAndState(expertId, state);
        if (projectIdList.size() == 0)
            return null;
        else
            return projectMapper.queryProjectsByProjectIds(projectIdList, expertId, state);
    }

    @Override
    public List<Project> queryAllProjects() {
        return projectMapper.queryAllProjects();
    }

    @Override
    public List<Project> queryProjectsByState(int state) {
        return projectMapper.queryProjectsByState(state);
    }

    @Override
    public List<Project> queryUnassignedProjectsF() {
        return projectMapper.queryUnassignedProjectsF();
    }

    @Override
    public List<Project> queryUnassignedProjectsL() {
        return projectMapper.queryUnassignedProjectsL();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Map<String, Object> selectProjectsAtFirstAssessment(List<Long> projectIdListToNext,
                                                               List<Long> projectIdListToRemain) {
        Map<String, Object> result = new HashMap<>();
        //遗留到初评的项目，默认被淘汰
        for (Long id : projectIdListToRemain) {
            Project project = projectMapper.queryProjectById(id);
            int state = project.getAssessmentState();
            if (state == 1) {
                continue;
            } else if (state == 2) {
                List<ProjectAssignment> projectAssignments =
                        projectAssignmentMapper.queryAssignmentsByProjectIdAndState(id, state);
                if (projectAssignments == null)  //还未分配
                {
                    project.setAssessmentState(1);
                    projectMapper.updateProject(project);
                } else     //项目已经分配
                {
                    for (ProjectAssignment assignment : projectAssignments) {
                        if (assignment.getFinish() == 1)   //存在已经评分的专家
                        {
                            result.put("code", -4);
                            result.put("errProjectName", project.getName());
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return result;
                        }
                    }
                    for (ProjectAssignment assignment : projectAssignments)   //都没有评分，直接撤回
                        projectAssignmentMapper.
                                deleteAssignment(assignment.getProjectId(), assignment.getExpertId(), state);
                }

            }
        }
        //进入会评的项目
        for (Long id : projectIdListToNext) {
            Project project = projectMapper.queryProjectById(id);
            int state = project.getAssessmentState();
            if (state == 1 /*&& project.getfGrade() >= -1*/) {
                project.setAssessmentState(2);
                projectMapper.updateProject(project);
            } else if (state == 2) {
                continue;
            }
        }
        result.put("code", 1);
        return result;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int selectProjectsAtLastAssessment(List<Long> projectIdListToNext,
                                              List<Long> projectIdListToRemain) {
        for (Long projectId : projectIdListToNext) {
            Project project = projectMapper.queryProjectById(projectId);
            float finalGrade = project.getlGrade();
            int state = project.getAssessmentState();
            if (state == 2) {
                project.setAssessmentState(3);
                project.setFinalGrade(finalGrade);
                int type = project.getPrize().getsType();
                ProjectAssignment projectAssignment = new ProjectAssignment();
                //EconoBenefit econoBenefit = new EconoBenefit();
                SocialBenefit socialBenefit = new SocialBenefit();
                if (type == 1) {
                    EconoBenefit1 econoBenefit1 = new EconoBenefit1();
                    EnvirBenefit1 envirBenefit1 = new EnvirBenefit1();
                    Map<String, Float> avgScore1 =
                            type1Service.queryScoresByProjectIdAndState(projectId, state);

                    econoBenefit1.setF_AssetPlan(avgScore1.get("F_AssetPlan"));
                    econoBenefit1.setS_AssetPlan_Para(avgScore1.get("S_AssetPlan_Para"));
                    econoBenefit1.setF_AssetManagerment(avgScore1.get("F_AssetManagerment"));
                    econoBenefit1.setS_AssetManagerment_Early(avgScore1.get("S_AssetManagerment_Early"));
                    econoBenefit1.setS_Managerment_Rent(avgScore1.get("S_Managerment_Rent"));
                    econoBenefit1.setS_Managerment_Increment(avgScore1.get("S_Managerment_Increment"));
                    econoBenefit1.setS_Managerment_Manager(avgScore1.get("S_Managerment_Manager"));
                    econoBenefit1.setF_Financial(avgScore1.get("F_Financial"));
                    econoBenefit1.setS_Financial_Managerment(avgScore1.get("S_Financial_Managerment"));
                    econoBenefit1.setS_Financial_Cash(avgScore1.get("S_Financial_Cash"));
                    econoBenefit1.setS_Financial_Operate(avgScore1.get("S_Financial_Operate"));
                    econoBenefit1.setS_Financial_Increment(avgScore1.get("S_Financial_Increment"));
                    econoBenefit1.setExpertId(-1L);
                    econoBenefit1.setProjectId(projectId);
                    econoBenefit1.setState(3);

                    socialBenefit.setF_Social(avgScore1.get("F_Social"));
                    socialBenefit.setS_Social_Develop(avgScore1.get("S_Social_Develop"));
                    socialBenefit.setS_Social_People(avgScore1.get("S_Social_People"));
                    socialBenefit.setS_Social_Green(avgScore1.get("S_Social_Green"));
                    socialBenefit.setF_Work(avgScore1.get("F_Work"));
                    socialBenefit.setF_Work_Brand(avgScore1.get("F_Work_Brand"));
                    socialBenefit.setF_Work_Develop(avgScore1.get("F_Work_Develop"));
                    socialBenefit.setExpertId(-1L);
                    socialBenefit.setProjectId(projectId);
                    socialBenefit.setState(3);

                    envirBenefit1.setF_SaveLands(avgScore1.get("F_SaveLands"));
                    envirBenefit1.setS_SaveLands_LandUse(avgScore1.get("S_SaveLands_LandUse"));
                    envirBenefit1.setS_SaveLands_Outdoors(avgScore1.get("S_SaveLands_Outdoors"));
                    envirBenefit1.setS_SaveLands_Transport(avgScore1.get("S_SaveLands_Transport"));
                    envirBenefit1.setS_SaveLands_SiteDesign(avgScore1.get("S_SaveLands_SiteDesign"));
                    envirBenefit1.setF_SaveEnergy(avgScore1.get("F_SaveEnergy"));
                    envirBenefit1.setS_SaveEnergy_Build(avgScore1.get("S_SaveEnergy_Build"));
                    envirBenefit1.setS_SaveEnergy_Condition(avgScore1.get("S_SaveEnergy_Condition"));
                    envirBenefit1.setS_SaveEnergy_Light(avgScore1.get("S_SaveEnergy_Light"));
                    envirBenefit1.setS_SaveEnergy_EnergyUse(avgScore1.get("S_SaveEnergy_EnergyUse"));
                    envirBenefit1.setF_SaveWater(avgScore1.get("F_SaveWater"));
                    envirBenefit1.setS_SaveWater_System(avgScore1.get("S_SaveWater_System"));
                    envirBenefit1.setS_SaveWater_Tool(avgScore1.get("S_SaveWater_Tool"));
                    envirBenefit1.setS_SaveWater_Use(avgScore1.get("S_SaveWater_Use"));
                    envirBenefit1.setF_SaveRes(avgScore1.get("F_SaveRes"));
                    envirBenefit1.setS_SaveRes_SaveRes(avgScore1.get("S_SaveRes_SaveRes"));
                    envirBenefit1.setS_SaveRes_Choose(avgScore1.get("S_SaveRes_Choose"));
                    envirBenefit1.setF_Indoor(avgScore1.get("F_Indoor"));
                    envirBenefit1.setS_Indoor_Sound(avgScore1.get("S_Indoor_Sound"));
                    envirBenefit1.setS_Indoor_Light(avgScore1.get("S_Indoor_Light"));
                    envirBenefit1.setS_Indoor_Hot(avgScore1.get("S_Indoor_Hot"));
                    envirBenefit1.setS_Indoor_Atmo(avgScore1.get("S_Indoor_Atmo"));
                    envirBenefit1.setF_Construction(avgScore1.get("F_Construction"));
                    envirBenefit1.setS_Construction_Envir(avgScore1.get("S_Construction_Envir"));
                    envirBenefit1.setS_Construction_Res(avgScore1.get("S_Construction_Res"));
                    envirBenefit1.setS_Construction_Progress(avgScore1.get("S_Construction_Progress"));
                    envirBenefit1.setF_Operation(avgScore1.get("F_Operation"));
                    ;
                    envirBenefit1.setS_Operation_Management(avgScore1.get("S_Operation_Management"));
                    envirBenefit1.setS_Operation_Tech(avgScore1.get("S_Operation_Tech"));
                    envirBenefit1.setS_Operation_Envir(avgScore1.get("S_Operation_Envir"));
                    envirBenefit1.setF_Innovation(avgScore1.get("F_Innovation"));
                    envirBenefit1.setS_Innovation_Structure(avgScore1.get("S_Innovation_Structure"));
                    envirBenefit1.setS_Innovation_Management(avgScore1.get("S_Innovation_Management"));
                    envirBenefit1.setS_Innovation_Tech(avgScore1.get("S_Innovation_Tech"));
                    envirBenefit1.setF_Humanity(avgScore1.get("F_Humanity"));
                    envirBenefit1.setS_Humanity_People(avgScore1.get("S_Humanity_People"));
                    envirBenefit1.setS_Humanity_GreenLive(avgScore1.get("S_Humanity_GreenLive"));
                    envirBenefit1.setS_Humanity_GreenEdu(avgScore1.get("S_Humanity_GreenEdu"));
                    envirBenefit1.setS_Humanity_History(avgScore1.get("S_Humanity_History"));
                    envirBenefit1.setF_Art(avgScore1.get("F_Art"));
                    envirBenefit1.setS_Art_Design(avgScore1.get("S_Art_Design"));
                    envirBenefit1.setExpertId(-1L);
                    envirBenefit1.setProjectId(projectId);
                    envirBenefit1.setState(3);

                    projectAssignment.setExpertId(-1L);
                    projectAssignment.setProjectId(projectId);
                    projectAssignment.setState(3);
                    projectAssignment.setGrade(finalGrade);
                    projectAssignment.setFinish(1);

                    envirBenefit1Mapper.insertScore(envirBenefit1);
                    socialBenefitMapper.insertScore(socialBenefit);
                    econoBenefit1Mapper.insertScore(econoBenefit1);
                    projectAssignmentMapper.insertProjectAssignment(projectAssignment);

                } else if (type == 2) {
                    EnvirBenefit2 envirBenefit2 = new EnvirBenefit2();
                    EconoBenefit2 econoBenefit2 = new EconoBenefit2();
                    Map<String, Float> avgScore2 =
                            type2Service.queryScoresByProjectIdAndState(projectId, state);

                    econoBenefit2.setF_AssetPlan(avgScore2.get("F_AssetPlan"));
                    econoBenefit2.setS_AssetPlan_Para(avgScore2.get("S_AssetPlan_Para"));
                    econoBenefit2.setF_Industry(avgScore2.get("F_Industry"));
                    econoBenefit2.setS_Industry_Early(avgScore2.get("S_Industry_Early"));
                    econoBenefit2.setS_Industry_Rent(avgScore2.get("S_Industry_Rent"));
                    econoBenefit2.setS_Industry_Increment(avgScore2.get("S_Industry_Increment"));
                    econoBenefit2.setS_Industry_Manager(avgScore2.get("S_Industry_Manager"));
                    econoBenefit2.setF_Financial(avgScore2.get("F_Financial"));
                    econoBenefit2.setS_Financial_Managerment(avgScore2.get("S_Financial_Managerment"));
                    econoBenefit2.setS_Financial_Cash(avgScore2.get("S_Financial_Cash"));
                    econoBenefit2.setS_Financial_Operate(avgScore2.get("S_Financial_Operate"));
                    econoBenefit2.setS_Financial_Increment(avgScore2.get("S_Financial_Increment"));
                    econoBenefit2.setExpertId(-1L);
                    econoBenefit2.setProjectId(projectId);
                    econoBenefit2.setState(3);

                    socialBenefit.setF_Social(avgScore2.get("F_Social"));
                    socialBenefit.setS_Social_Develop(avgScore2.get("S_Social_Develop"));
                    socialBenefit.setS_Social_People(avgScore2.get("S_Social_People"));
                    socialBenefit.setS_Social_Green(avgScore2.get("S_Social_Green"));
                    socialBenefit.setF_Work(avgScore2.get("F_Work"));
                    socialBenefit.setF_Work_Brand(avgScore2.get("F_Work_Brand"));
                    socialBenefit.setF_Work_Develop(avgScore2.get("F_Work_Develop"));
                    socialBenefit.setExpertId(-1L);
                    socialBenefit.setProjectId(projectId);
                    socialBenefit.setState(3);

                    envirBenefit2.setF_LandPlan(avgScore2.get("F_LandPlan"));
                    envirBenefit2.setS_LandPlan_LandUse(avgScore2.get("S_LandPlan_LandUse"));
                    envirBenefit2.setS_LandPlan_LayOut(avgScore2.get("S_LandPlan_LayOut"));
                    envirBenefit2.setF_Eco(avgScore2.get("F_Eco"));
                    envirBenefit2.setS_Eco_Nature(avgScore2.get("S_Eco_Nature"));
                    envirBenefit2.setS_Eco_Envir(avgScore2.get("S_Eco_Envir"));
                    envirBenefit2.setF_GreenBulid(avgScore2.get("F_GreenBulid"));
                    envirBenefit2.setS_GreenBuild_GreenBulid(avgScore2.get("S_GreenBuild_GreenBulid"));
                    envirBenefit2.setF_Res(avgScore2.get("F_Res"));
                    envirBenefit2.setS_Res_Energy(avgScore2.get("S_Res_Energy"));
                    envirBenefit2.setS_Res_Water(avgScore2.get("S_Res_Water"));
                    envirBenefit2.setS_Res_Res(avgScore2.get("S_Res_Res"));
                    envirBenefit2.setS_Res_Carbon(avgScore2.get("S_Res_Carbon"));
                    envirBenefit2.setF_GreenTransport(avgScore2.get("F_GreenTransport"));
                    envirBenefit2.setS_GreenTransport_Trans(avgScore2.get("S_GreenTransport_Trans"));
                    envirBenefit2.setS_GreenTransport_Road(avgScore2.get("S_GreenTransport_Road"));
                    envirBenefit2.setS_GreenTransport_Static(avgScore2.get("S_GreenTransport_Static"));
                    envirBenefit2.setF_InfoManager(avgScore2.get("F_InfoManager"));
                    envirBenefit2.setS_InfoManager_Urban(avgScore2.get("S_InfoManager_Urban"));
                    envirBenefit2.setS_InfoManager_Service(avgScore2.get("S_InfoManager_Service"));
                    envirBenefit2.setF_Innovation(avgScore2.get("F_Innovation"));
                    envirBenefit2.setS_Innovation(avgScore2.get("S_Innovation"));
                    envirBenefit2.setF_Humanity(avgScore2.get("F_Humanity"));
                    envirBenefit2.setS_Humanity_People(avgScore2.get("S_Humanity_People"));
                    envirBenefit2.setS_Humanity_GreenLive(avgScore2.get("S_Humanity_GreenLive"));
                    envirBenefit2.setS_Humanity_GreenEdu(avgScore2.get("S_Humanity_GreenEdu"));
                    envirBenefit2.setS_Humanity_History(avgScore2.get("S_Humanity_History"));
                    envirBenefit2.setF_Art(avgScore2.get("F_Art"));
                    envirBenefit2.setS_Art_Bulid(avgScore2.get("S_Art_Bulid"));
                    envirBenefit2.setS_Art_Envir(avgScore2.get("S_Art_Envir"));
                    envirBenefit2.setExpertId(-1L);
                    envirBenefit2.setProjectId(projectId);
                    envirBenefit2.setState(3);

                    projectAssignment.setExpertId(-1L);
                    projectAssignment.setProjectId(projectId);
                    projectAssignment.setState(3);
                    projectAssignment.setGrade(finalGrade);
                    projectAssignment.setFinish(1);

                    envirBenefit2Mapper.insertScore(envirBenefit2);
                    socialBenefitMapper.insertScore(socialBenefit);
                    econoBenefit2Mapper.insertScore(econoBenefit2);
                    projectAssignmentMapper.insertProjectAssignment(projectAssignment);
                } else if (type == 3) {
                    EnvirBenefit3 envirBenefit3 = new EnvirBenefit3();
                    EconoBenefit3 econoBenefit3 = new EconoBenefit3();
                    Map<String, Float> avgScore3 =
                            type3Service.queryScoresByProjectIdAndState(projectId, state);

                    econoBenefit3.setF_AssetManager(avgScore3.get("F_AssetManager"));
                    econoBenefit3.setS_AssetManager_Early(avgScore3.get("S_AssetManager_Early"));
                    econoBenefit3.setS_AssetManager_Service(avgScore3.get("S_AssetManager_Service"));
                    econoBenefit3.setS_AssetManager_MoreAsset(avgScore3.get("S_AssetManager_MoreAsset"));
                    econoBenefit3.setF_ProjectManager(avgScore3.get("F_ProjectManager"));
                    econoBenefit3.setS_ProjectManager_Cost(avgScore3.get("S_ProjectManager_Cost"));
                    econoBenefit3.setS_ProjectManager_Method(avgScore3.get("S_ProjectManager_Method"));
                    econoBenefit3.setExpertId(-1L);
                    econoBenefit3.setProjectId(projectId);
                    econoBenefit3.setState(3);

                    socialBenefit.setF_Social(avgScore3.get("F_Social"));
                    socialBenefit.setS_Social_Develop(avgScore3.get("S_Social_Develop"));
                    socialBenefit.setS_Social_People(avgScore3.get("S_Social_People"));
                    socialBenefit.setS_Social_Green(avgScore3.get("S_Social_Green"));
                    socialBenefit.setF_Work(avgScore3.get("F_Work"));
                    socialBenefit.setF_Work_Brand(avgScore3.get("F_Work_Brand"));
                    socialBenefit.setF_Work_Develop(avgScore3.get("F_Work_Develop"));
                    socialBenefit.setExpertId(-1L);
                    socialBenefit.setProjectId(projectId);
                    socialBenefit.setState(3);

                    envirBenefit3.setF_Function(avgScore3.get("F_Function"));
                    envirBenefit3.setS_Function_Use(avgScore3.get("S_Function_Use"));
                    envirBenefit3.setS_Function_Service(avgScore3.get("S_Function_Service"));
                    envirBenefit3.setS_Function_Arrivable(avgScore3.get("S_Function_Arrivable"));
                    envirBenefit3.setS_Function_Open(avgScore3.get("S_Function_Open"));
                    envirBenefit3.setS_Function_Safe(avgScore3.get("S_Function_Safe"));
                    envirBenefit3.setF_Tech(avgScore3.get("F_Tech"));
                    envirBenefit3.setS_Tech_Operate(avgScore3.get("S_Tech_Operate"));
                    envirBenefit3.setS_Tech_Curing(avgScore3.get("S_Tech_Curing"));
                    envirBenefit3.setS_Tech_Material(avgScore3.get("S_Tech_Material"));
                    envirBenefit3.setF_Human(avgScore3.get("F_Human"));
                    envirBenefit3.setS_Human_Design(avgScore3.get("S_Human_Design"));
                    envirBenefit3.setS_Human_Explore(avgScore3.get("S_Human_Explore"));
                    envirBenefit3.setF_Envir(avgScore3.get("F_Envir"));
                    envirBenefit3.setS_Envir_EnegrySave(avgScore3.get("S_Envir_EnegrySave"));
                    envirBenefit3.setS_Envir_WaterSave(avgScore3.get("S_Envir_WaterSave"));
                    envirBenefit3.setS_Envir_Res(avgScore3.get("S_Envir_Res"));
                    envirBenefit3.setS_Envir_System(avgScore3.get("S_Envir_System"));
                    envirBenefit3.setExpertId(-1L);
                    envirBenefit3.setProjectId(projectId);
                    envirBenefit3.setState(3);

                    projectAssignment.setExpertId(-1L);
                    projectAssignment.setProjectId(projectId);
                    projectAssignment.setState(3);
                    projectAssignment.setGrade(finalGrade);
                    projectAssignment.setFinish(1);

                    envirBenefit3Mapper.insertScore(envirBenefit3);
                    socialBenefitMapper.insertScore(socialBenefit);
                    econoBenefit3Mapper.insertScore(econoBenefit3);
                    projectAssignmentMapper.insertProjectAssignment(projectAssignment);
                } else {
                    EnvirBenefit4 envirBenefit4 = new EnvirBenefit4();
                    EconoBenefit4 econoBenefit4 = new EconoBenefit4();
                    Map<String, Float> avgScore4 =
                            type4Service.queryScoresByProjectIdAndState(projectId, state);

                    econoBenefit4.setF_AssetPlan(avgScore4.get("F_AssetPlan"));
                    econoBenefit4.setS_AssetPlan_Para(avgScore4.get("S_AssetPlan_Para"));
                    econoBenefit4.setF_AssetManagerment(avgScore4.get("F_AssetManagerment"));
                    econoBenefit4.setS_AssetManagerment_Early(avgScore4.get("S_AssetManagerment_Early"));
                    econoBenefit4.setS_Managerment_Rent(avgScore4.get("S_Managerment_Rent"));
                    econoBenefit4.setS_Managerment_Increment(avgScore4.get("S_Managerment_Increment"));
                    econoBenefit4.setS_Managerment_Manager(avgScore4.get("S_Managerment_Manager"));
                    econoBenefit4.setF_Financial(avgScore4.get("F_Financial"));
                    econoBenefit4.setS_Financial_Managerment(avgScore4.get("S_Financial_Managerment"));
                    econoBenefit4.setS_Financial_Cash(avgScore4.get("S_Financial_Cash"));
                    econoBenefit4.setS_Financial_Operate(avgScore4.get("S_Financial_Operate"));
                    econoBenefit4.setS_Financial_Increment(avgScore4.get("S_Financial_Increment"));
                    econoBenefit4.setExpertId(-1L);
                    econoBenefit4.setProjectId(projectId);
                    econoBenefit4.setState(3);

                    socialBenefit.setF_Social(avgScore4.get("F_Social"));
                    socialBenefit.setS_Social_Develop(avgScore4.get("S_Social_Develop"));
                    socialBenefit.setS_Social_People(avgScore4.get("S_Social_People"));
                    socialBenefit.setS_Social_Green(avgScore4.get("S_Social_Green"));
                    socialBenefit.setF_Work(avgScore4.get("F_Work"));
                    socialBenefit.setF_Work_Brand(avgScore4.get("F_Work_Brand"));
                    socialBenefit.setF_Work_Develop(avgScore4.get("F_Work_Develop"));
                    socialBenefit.setExpertId(-1L);
                    socialBenefit.setProjectId(projectId);
                    socialBenefit.setState(3);

                    envirBenefit4.setF_PhysicalEnvir(avgScore4.get("F_PhysicalEnvir"));
                    envirBenefit4.setS_PhysicalEnvir_Sound(avgScore4.get("S_PhysicalEnvir_Sound"));
                    envirBenefit4.setS_PhysicalEnvir_Light(avgScore4.get("S_PhysicalEnvir_Light"));
                    envirBenefit4.setS_PhysicalEnvir_Hot(avgScore4.get("S_PhysicalEnvir_Hot"));
                    envirBenefit4.setS_PhysicalEnvir_Wind(avgScore4.get("S_PhysicalEnvir_Wind"));
                    envirBenefit4.setF_HumanEnvir(avgScore4.get("F_HumanEnvir"));
                    envirBenefit4.setS_HumanEnvir_Function(avgScore4.get("S_HumanEnvir_Function"));
                    envirBenefit4.setS_HumanEnvir_Beauty(avgScore4.get("S_HumanEnvir_Beauty"));
                    envirBenefit4.setS_HumanEnvir_Explore(avgScore4.get("S_HumanEnvir_Explore"));
                    envirBenefit4.setF_Decorate(avgScore4.get("F_Decorate"));
                    envirBenefit4.setS_DecorateDetails(avgScore4.get("S_DecorateDetails"));
                    envirBenefit4.setS_DecorateMaterial(avgScore4.get("S_DecorateMaterial"));
                    envirBenefit4.setF_Tech(avgScore4.get("F_Tech"));
                    envirBenefit4.setS_Tech_Envir(avgScore4.get("S_Tech_Envir"));
                    envirBenefit4.setS_Tech_Res(avgScore4.get("S_Tech_Res"));
                    envirBenefit4.setS_Tech_Progress(avgScore4.get("S_Tech_Progress"));
                    envirBenefit4.setExpertId(-1L);
                    envirBenefit4.setProjectId(projectId);
                    envirBenefit4.setState(3);

                    projectAssignment.setExpertId(-1L);
                    projectAssignment.setProjectId(projectId);
                    projectAssignment.setState(3);
                    projectAssignment.setGrade(finalGrade);
                    projectAssignment.setFinish(1);

                    envirBenefit4Mapper.insertScore(envirBenefit4);
                    socialBenefitMapper.insertScore(socialBenefit);
                    econoBenefit4Mapper.insertScore(econoBenefit4);
                    projectAssignmentMapper.insertProjectAssignment(projectAssignment);
                }

                projectMapper.updateProject(project);
            } else if (state == 3) {
                continue;
            }
        }
        //遗留的项目，默认被淘汰
        for (Long id : projectIdListToRemain) {
            Project project = projectMapper.queryProjectById(id);
            int state = project.getAssessmentState();
            int type = project.getPrize().getsType();
            if (state == 3) {
                SocialBenefit socialBenefit = socialBenefitMapper.
                        queryScoreByProjectIdAndState(id, state, -1L);
                if (type == 1) {
                    EconoBenefit1 econoBenefit1 = econoBenefit1Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    econoBenefit1Mapper.deleteScore(econoBenefit1);
                    EnvirBenefit1 envirBenefit1 = envirBenefit1Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    envirBenefit1Mapper.deleteScore(envirBenefit1);

                } else if (type == 2) {
                    EconoBenefit2 econoBenefit2 = econoBenefit2Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    econoBenefit2Mapper.deleteScore(econoBenefit2);
                    EnvirBenefit2 envirBenefit2 = envirBenefit2Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    envirBenefit2Mapper.deleteScore(envirBenefit2);
                } else if (type == 3) {
                    EconoBenefit3 econoBenefit3 = econoBenefit3Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    econoBenefit3Mapper.deleteScore(econoBenefit3);
                    EnvirBenefit3 envirBenefit3 = envirBenefit3Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    envirBenefit3Mapper.deleteScore(envirBenefit3);
                } else {
                    EconoBenefit4 econoBenefit4 = econoBenefit4Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    econoBenefit4Mapper.deleteScore(econoBenefit4);
                    EnvirBenefit4 envirBenefit4 = envirBenefit4Mapper.
                            queryScoreByProjectIdAndState(id, state, -1L);
                    envirBenefit4Mapper.deleteScore(envirBenefit4);
                }
                socialBenefitMapper.deleteScore(socialBenefit);
                projectAssignmentMapper.deleteAssignment(id, -1L, state);
                project.setAssessmentState(2);
                project.setFinalGrade(-1.0f);
                project.setPrizeClass(5);
                projectMapper.updateProject(project);
            } else if (state == 2) {
                continue;
            }
        }
        return 1;
    }


    @Override
    public List<Project> queryProjectsByStateAndPrizeType(int state, int prizeType) {
        return projectMapper.queryProjectsByStateAndPrizeType(state, prizeType);
    }
}
