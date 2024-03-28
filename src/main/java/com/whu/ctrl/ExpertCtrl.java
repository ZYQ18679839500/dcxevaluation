package com.whu.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.whu.mapper.EconoBenefit1Mapper;
import com.whu.mapper.EconoBenefit2Mapper;
import com.whu.mapper.EconoBenefit3Mapper;
import com.whu.mapper.EconoBenefit4Mapper;
import com.whu.mapper.EnvirBenefit1Mapper;
import com.whu.mapper.EnvirBenefit2Mapper;
import com.whu.mapper.EnvirBenefit3Mapper;
import com.whu.mapper.EnvirBenefit4Mapper;
import com.whu.mapper.ProjectAssignmentMapper;
import com.whu.mapper.SocialBenefitMapper;
import com.whu.pojo.Benefit;
import com.whu.pojo.EconoBenefit1;
import com.whu.pojo.EconoBenefit2;
import com.whu.pojo.EconoBenefit3;
import com.whu.pojo.EconoBenefit4;
import com.whu.pojo.EnvirBenefit1;
import com.whu.pojo.EnvirBenefit2;
import com.whu.pojo.EnvirBenefit3;
import com.whu.pojo.EnvirBenefit4;
import com.whu.pojo.Expert;
import com.whu.pojo.FinalGrade;
import com.whu.pojo.Portion;
import com.whu.pojo.Prize;
import com.whu.pojo.Project;
import com.whu.pojo.ProjectAssignment;
import com.whu.pojo.SocialBenefit;
import com.whu.service.EnvirBenefit4Service;
import com.whu.service.ExpertService;
import com.whu.service.FinalGradeService;
import com.whu.service.PortionService;
import com.whu.service.ProjectService;
import com.whu.service.Type1Service;
import com.whu.service.Type2Service;
import com.whu.service.Type3Service;
import com.whu.service.Type4Service;
import com.whu.utils.JsonUtil;


@Controller
@RequestMapping("/expert")
public class ExpertCtrl {
    @Autowired
    ExpertService expertService;

    @Autowired
    ProjectService projectService;

    @Autowired
    PortionService portionService;

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
    FinalGradeService finalGradeService;

    @Autowired
    EnvirBenefit1Mapper envirBenefit1Mapper;

    @Autowired
    EnvirBenefit2Mapper envirBenefit2Mapper;

    @Autowired
    EnvirBenefit3Mapper envirBenefit3Mapper;

    @Autowired
    EnvirBenefit4Mapper envirBenefit4Mapper;

    @Autowired
    SocialBenefitMapper socialBenefitMapper;

    @Autowired
    EconoBenefit1Mapper econoBenefit1Mapper;

    @Autowired
    EconoBenefit2Mapper econoBenefit2Mapper;

    @Autowired
    EconoBenefit3Mapper econoBenefit3Mapper;

    @Autowired
    EconoBenefit4Mapper econoBenefit4Mapper;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Expert expert;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            String username = parameters.getString("username");
            String password = parameters.getString("password");

            expert = expertService.queryExpertByUsername(username);

            if (expert == null) //用户不存在
            {
                result.put("success", false);
                result.put("errMsg", 1);
            } else if (!expert.getPassword().equals(password)) //密码错误
            {
                result.put("success", false);
                result.put("errMsg", 0);
            } else  //认证成功
            {
                result.put("success", 1);
                result.put("errMsg", "");
                result.put("name", expert.getName());
                result.put("id", expert.getId());
                result.put("completeInfo", expert.getCompleteInfo());
                result.put("type", expert.getType());
                if (expert.getCompleteInfo() == false) {
                    result.put("department", expert.getDepartment());
                    result.put("title", expert.getTitle());
                    result.put("designation", "");
                    result.put("telephone", "");
                    result.put("email", "");
                    expert.setCompleteInfo(true);
                    expertService.updateExpert(expert);
                }
                HttpSession session = (HttpSession) request.getSession();
                session.setAttribute("currentExpert", expert);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", "Unknow error!");
        }

        return result;
    }

    @RequestMapping(value = "/change_updateRight", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeUpdateRight(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Expert expert;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long id = parameters.getLong("id");

            expert = expertService.queryExpertById(id);

            if (expert == null) //用户不存在
            {
                result.put("success", false);
                result.put("errMsg", 1);
            } else  //修改成功
            {
                expert.setSaveGrade(1);

                expertService.updateExpert(expert);
                result.put("success", 1);
                result.put("errMsg", "");

                HttpSession session = (HttpSession) request.getSession();
                session.setAttribute("currentExpert", expert);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", -1);
        }

        return result;
    }

    @RequestMapping(value = "/alter_infomation", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> alterInfomation(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Expert expert;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long id = parameters.getLong("id");
            String name = parameters.getString("name");
            String department = parameters.getString("department");
            String title = parameters.getString("title");
            String designation = parameters.getString("designation");
            String telephone = parameters.getString("telephone");
            String email = parameters.getString("email");

            expert = expertService.queryExpertById(id);

            if (expert == null) //用户不存在
            {
                result.put("success", false);
                result.put("errMsg", 1);
            } else  //修改成功
            {
                expert.setName(name);
                expert.setDepartment(department);
                expert.setTitle(title);
                expert.setDesignation(designation);
                expert.setTelephone(telephone);
                expert.setEmail(email);

                expertService.updateExpert(expert);
                result.put("success", 1);
                result.put("errMsg", "");

                HttpSession session = (HttpSession) request.getSession();
                session.setAttribute("currentExpert", expert);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", -1);
        }

        return result;
    }

    @RequestMapping(value = "/check_updateRight", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> CheckUpdateRight(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Expert expert;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long id = parameters.getLong("id");
            expert = expertService.queryExpertById(id);

            if (expert == null) //用户不存在
            {
                result.put("success", false);
                result.put("errMsg", 1);
            } else {
                result.put("saveGrade", expert.getSaveGrade());
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", -1);
        }

        return result;
    }

    @RequestMapping(value = "/query_infomation", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryInfomation(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Expert expert;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long id = parameters.getLong("id");
            expert = expertService.queryExpertById(id);

            if (expert == null) //用户不存在
            {
                result.put("success", false);
                result.put("errMsg", 1);
            } else {
                result.put("name", expert.getName());
                result.put("department", expert.getDepartment());
                result.put("title", expert.getTitle());
                result.put("designation", expert.getDesignation());
                result.put("telephone", expert.getTelephone());
                result.put("email", expert.getEmail());
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", -1);
        }

        return result;
    }

    @RequestMapping(value = "/setPortion", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> setPortion(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        List<Portion> portionList;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long expert_id = parameters.getLong("id");

            int envir_benefit1 = parameters.getInteger("envir_benefit1");
            int econo_benefit1 = parameters.getInteger("econo_benefit1");
            int social_benefit1 = parameters.getInteger("social_benefit1");

            int envir_benefit2 = parameters.getInteger("envir_benefit2");
            int econo_benefit2 = parameters.getInteger("econo_benefit2");
            int social_benefit2 = parameters.getInteger("social_benefit2");

            int envir_benefit3 = parameters.getInteger("envir_benefit3");
            int econo_benefit3 = parameters.getInteger("econo_benefit3");
            int social_benefit3 = parameters.getInteger("social_benefit3");

            int envir_benefit4 = parameters.getInteger("envir_benefit4");
            int econo_benefit4 = parameters.getInteger("econo_benefit4");
            int social_benefit4 = parameters.getInteger("social_benefit4");

            portionList = portionService.queryAllPortionByID(expert_id);
            if (portionList.size() > 1) {
                portionService.updatePortion(expert_id, 1L, envir_benefit1, econo_benefit1, social_benefit1);
                portionService.updatePortion(expert_id, 2L, envir_benefit2, econo_benefit2, social_benefit2);
                portionService.updatePortion(expert_id, 3L, envir_benefit3, econo_benefit3, social_benefit3);
                portionService.updatePortion(expert_id, 4L, envir_benefit4, econo_benefit4, social_benefit4);
                result.put("success", true);
            } else {
                portionService.setPortion(expert_id, 1L, envir_benefit1, econo_benefit1, social_benefit1);
                portionService.setPortion(expert_id, 2L, envir_benefit2, econo_benefit2, social_benefit2);
                portionService.setPortion(expert_id, 3L, envir_benefit3, econo_benefit3, social_benefit3);
                portionService.setPortion(expert_id, 4L, envir_benefit4, econo_benefit4, social_benefit4);
                result.put("success", true);
            }

        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", -1);
        }

        return result;
    }

    @RequestMapping(value = "/queryPortion", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryPortion(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        List<Portion> portionList;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long expert_id = parameters.getLong("id");
            portionList = portionService.queryAllPortionByID(expert_id);
            if (portionList.size() > 2) {
                result.put("success", true);
                result.put("set", true);
                result.put("portions", portionList);
            } else {
                result.put("success", true);
                result.put("set", false);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", -1);
        }

        return result;
    }

    @RequestMapping(value = "/alter_password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> alterPassword(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Expert expert;
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long id = parameters.getLong("id");
            String newPassword = parameters.getString("newPassword");
            String password = parameters.getString("password");

            expert = expertService.queryExpertById(id);

            if (expert == null) //用户不存在
            {
                result.put("success", false);
                result.put("errMsg", 1);
            } else if (!expert.getPassword().equals(password)) //旧密码错误
            {
                result.put("success", false);
                result.put("errMsg", 0);
            } else  //修改成功
            {
                expert.setPassword(newPassword);
                expertService.updateExpert(expert);
                result.put("success", 1);
                result.put("errMsg", "");

                HttpSession session = (HttpSession) request.getSession();
                session.setAttribute("currentExpert", expert);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", -1);
        }

        return result;
    }

    @RequestMapping(value = "/my_projects/{id}/{state}")
    @ResponseBody
    public Map<String, Object> queryProjetsByExpertId(@PathVariable("id") Long id, @PathVariable("state") int state) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Project> projects = projectService.queryProjectsByExpertIdAndState(id, state);
            result.put("success", 1);
            result.put("errMsg", "");
            result.put("projects", projects);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("errMsg", "-1");
        }
        return result;
    }

    @RequestMapping(value = "/my_types/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryPrizesByExpertId(@PathVariable("id") Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Expert expert = expertService.queryExpertById(id);
            result.put("success", 1);
            result.put("errMsg", "");
            result.put("prizeList", expert.getPrizeList());
        } catch (NullPointerException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("errMsg", "-2");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("errMsg", "-1");
        }
        return result;
    }
    
    /*@RequestMapping(value = "/my_types/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryPrizesByExpertId(@PathVariable("id") Long id)
    {
        Map<String, Object> result = new HashMap<>();
        try
        {        	
            Expert expert = expertService.queryExpertById(id);
            result.put("success", 1);
            result.put("errMsg", "");
            //result.put("prizeList", expert.getPrizeList());
            List<Prize> prizeList=new ArrayList<>();
            List<Long> fProjectIdList=expert.getfProjectIdList();
            for(Long projectId:fProjectIdList) {
            	Project project=projectService.queryProjectById(projectId);
            	prizeList.add(project.getPrize());
            }            
            result.put("prizeList", prizeList);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            result.put("success", false);
            result.put("errMsg", "-2");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("success", false);
            result.put("errMsg", "-1");
        }
        return result;
    }*/

    @RequestMapping(value = "/assess_type1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> assessType1(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");


            //环境效益
            float F_SaveLands = parameters.getFloatValue("F_SaveLands");//节地与室外环境
            float S_SaveLands_LandUse = parameters.getFloatValue("S_SaveLands_LandUse");
            float S_SaveLands_Outdoors = parameters.getFloatValue("S_SaveLands_Outdoors");
            float S_SaveLands_Transport = parameters.getFloatValue("S_SaveLands_Transport");
            float S_SaveLands_SiteDesign = parameters.getFloatValue("S_SaveLands_SiteDesign");
            float F_SaveEnergy = parameters.getFloatValue("F_SaveEnergy");//节能与能源利用
            float S_SaveEnergy_Build = parameters.getFloatValue("S_SaveEnergy_Build");
            float S_SaveEnergy_Condition = parameters.getFloatValue("S_SaveEnergy_Condition");
            float S_SaveEnergy_Light = parameters.getFloatValue("S_SaveEnergy_Light");
            float S_SaveEnergy_EnergyUse = parameters.getFloatValue("S_SaveEnergy_EnergyUse");
            float F_SaveWater = parameters.getFloatValue("F_SaveWater");//节水与水资源利用
            float S_SaveWater_System = parameters.getFloatValue("S_SaveWater_System");
            float S_SaveWater_Tool = parameters.getFloatValue("S_SaveWater_Tool");
            float S_SaveWater_Use = parameters.getFloatValue("S_SaveWater_Use");
            float F_SaveRes = parameters.getFloatValue("F_SaveRes");//节材与材料资源利用
            float S_SaveRes_SaveRes = parameters.getFloatValue("S_SaveRes_SaveRes");
            float S_SaveRes_Choose = parameters.getFloatValue("S_SaveRes_Choose");
            float F_Indoor = parameters.getFloatValue("F_Indoor");//室内环境质量
            float S_Indoor_Sound = parameters.getFloatValue("S_Indoor_Sound");
            float S_Indoor_Light = parameters.getFloatValue("S_Indoor_Light");
            float S_Indoor_Hot = parameters.getFloatValue("S_Indoor_Hot");
            float S_Indoor_Atmo = parameters.getFloatValue("S_Indoor_Atmo");
            float F_Construction = parameters.getFloatValue("F_Construction");//施工管理
            float S_Construction_Envir = parameters.getFloatValue("S_Construction_Envir");
            float S_Construction_Res = parameters.getFloatValue("S_Construction_Res");
            float S_Construction_Progress = parameters.getFloatValue("S_Construction_Progress");
            float F_Operation = parameters.getFloatValue("F_Operation");//运行管理
            float S_Operation_Management = parameters.getFloatValue("S_Operation_Management");
            float S_Operation_Tech = parameters.getFloatValue("S_Operation_Tech");
            float S_Operation_Envir = parameters.getFloatValue("S_Operation_Envir");
            float F_Innovation = parameters.getFloatValue("F_Innovation");//创新项评价
            float S_Innovation_Structure = parameters.getFloatValue("S_Innovation_Structure");
            float S_Innovation_Management = parameters.getFloatValue("S_Innovation_Management");
            float S_Innovation_Tech = parameters.getFloatValue("S_Innovation_Tech");
            float F_Humanity = parameters.getFloatValue("F_Humanity");//人文
            float S_Humanity_People = parameters.getFloatValue("S_Humanity_People");
            float S_Humanity_GreenLive = parameters.getFloatValue("S_Humanity_GreenLive");
            float S_Humanity_GreenEdu = parameters.getFloatValue("S_Humanity_GreenEdu");
            float S_Humanity_History = parameters.getFloatValue("S_Humanity_History");
            float F_Art = parameters.getFloatValue("F_Art");//艺术
            float S_Art_Design = parameters.getFloatValue("S_Art_Design");

            //经济效益
            float F_AssetPlan = parameters.getFloatValue("F_AssetPlan");//资产规划
            float S_AssetPlan_Para = parameters.getFloatValue("S_AssetPlan_Para");
            float F_AssetManagerment = parameters.getFloatValue("F_AssetManagerment");//资产运营
            float S_AssetManagerment_Early = parameters.getFloatValue("S_AssetManagerment_Early");
            float S_Managerment_Rent = parameters.getFloatValue("S_Managerment_Rent");
            float S_Managerment_Increment = parameters.getFloatValue("S_Managerment_Increment");
            float S_Managerment_Manager = parameters.getFloatValue("S_Managerment_Manager");
            float F_Financial = parameters.getFloatValue("F_Financial");//财务表现
            float S_Financial_Managerment = parameters.getFloatValue("S_Financial_Managerment");
            float S_Financial_Cash = parameters.getFloatValue("S_Financial_Cash");
            float S_Financial_Operate = parameters.getFloatValue("S_Financial_Operate");
            float S_Financial_Increment = parameters.getFloatValue("S_Financial_Increment");

            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            float grade = parameters.getFloatValue("grade");

            EnvirBenefit1 envirBenefit1 = new EnvirBenefit1();
            envirBenefit1.setExpertId(expertId);
            envirBenefit1.setProjectId(projectId);
            envirBenefit1.setF_SaveLands(F_SaveLands);
            envirBenefit1.setS_SaveLands_LandUse(S_SaveLands_LandUse);
            envirBenefit1.setS_SaveLands_Outdoors(S_SaveLands_Outdoors);
            envirBenefit1.setS_SaveLands_Transport(S_SaveLands_Transport);
            envirBenefit1.setS_SaveLands_SiteDesign(S_SaveLands_SiteDesign);
            envirBenefit1.setF_SaveEnergy(F_SaveEnergy);
            envirBenefit1.setS_SaveEnergy_Build(S_SaveEnergy_Build);
            envirBenefit1.setS_SaveEnergy_Condition(S_SaveEnergy_Condition);
            envirBenefit1.setS_SaveEnergy_Light(S_SaveEnergy_Light);
            envirBenefit1.setS_SaveEnergy_EnergyUse(S_SaveEnergy_EnergyUse);
            envirBenefit1.setF_SaveWater(F_SaveWater);
            envirBenefit1.setS_SaveWater_System(S_SaveWater_System);
            envirBenefit1.setS_SaveWater_Tool(S_SaveWater_Tool);
            envirBenefit1.setS_SaveWater_Use(S_SaveWater_Use);
            envirBenefit1.setF_SaveRes(F_SaveRes);
            envirBenefit1.setS_SaveRes_SaveRes(S_SaveRes_SaveRes);
            envirBenefit1.setS_SaveRes_Choose(S_SaveRes_Choose);
            envirBenefit1.setF_Indoor(F_Indoor);
            envirBenefit1.setS_Indoor_Sound(S_Indoor_Sound);
            envirBenefit1.setS_Indoor_Light(S_Indoor_Light);
            envirBenefit1.setS_Indoor_Hot(S_Indoor_Hot);
            envirBenefit1.setS_Indoor_Atmo(S_Indoor_Atmo);
            envirBenefit1.setF_Construction(F_Construction);
            envirBenefit1.setS_Construction_Envir(S_Construction_Envir);
            envirBenefit1.setS_Construction_Res(S_Construction_Res);
            envirBenefit1.setS_Construction_Progress(S_Construction_Progress);
            envirBenefit1.setF_Operation(F_Operation);
            envirBenefit1.setS_Operation_Management(S_Operation_Management);
            envirBenefit1.setS_Operation_Tech(S_Operation_Tech);
            envirBenefit1.setS_Operation_Envir(S_Operation_Envir);
            envirBenefit1.setF_Innovation(F_Innovation);
            envirBenefit1.setS_Innovation_Structure(S_Innovation_Structure);
            envirBenefit1.setS_Innovation_Management(S_Innovation_Management);
            envirBenefit1.setS_Innovation_Tech(S_Innovation_Tech);
            envirBenefit1.setF_Humanity(F_Humanity);
            envirBenefit1.setS_Humanity_People(S_Humanity_People);
            envirBenefit1.setS_Humanity_GreenLive(S_Humanity_GreenLive);
            envirBenefit1.setS_Humanity_GreenEdu(S_Humanity_GreenEdu);
            envirBenefit1.setS_Humanity_History(S_Humanity_History);
            envirBenefit1.setF_Art(F_Art);
            envirBenefit1.setS_Art_Design(S_Art_Design);
            envirBenefit1.setState(state);

            SocialBenefit socialBenefit = new SocialBenefit();
            socialBenefit.setExpertId(expertId);
            socialBenefit.setProjectId(projectId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);
            socialBenefit.setState(state);

            EconoBenefit1 econoBenefit1 = new EconoBenefit1();
            econoBenefit1.setExpertId(expertId);
            econoBenefit1.setProjectId(projectId);
            econoBenefit1.setF_AssetPlan(F_AssetPlan);
            econoBenefit1.setS_AssetPlan_Para(S_AssetPlan_Para);
            econoBenefit1.setF_AssetManagerment(F_AssetManagerment);
            econoBenefit1.setS_AssetManagerment_Early(S_AssetManagerment_Early);
            econoBenefit1.setS_Managerment_Rent(S_Managerment_Rent);
            econoBenefit1.setS_Managerment_Increment(S_Managerment_Increment);
            econoBenefit1.setS_Managerment_Manager(S_Managerment_Manager);
            econoBenefit1.setF_Financial(F_Financial);
            econoBenefit1.setS_Financial_Managerment(S_Financial_Managerment);
            econoBenefit1.setS_Financial_Cash(S_Financial_Cash);
            econoBenefit1.setS_Financial_Operate(S_Financial_Operate);
            econoBenefit1.setS_Financial_Increment(S_Financial_Increment);
            econoBenefit1.setState(state);

            int code = type1Service.insertType1Score(envirBenefit1, socialBenefit, econoBenefit1, grade, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else if (code == -2)  // no right to assess
            {
                result.put("success", false);
                result.put("errMsg", -2);
            } else if (code == -1) //project has been assessed already
            {
                result.put("success", false);
                result.put("errMsg", -1);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);
        }
        return result;
    }

    @RequestMapping(value = "/update_type1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateType1(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");

            //环境效益
            float F_SaveLands = parameters.getFloatValue("F_SaveLands");//节地与室外环境
            float S_SaveLands_LandUse = parameters.getFloatValue("S_SaveLands_LandUse");
            float S_SaveLands_Outdoors = parameters.getFloatValue("S_SaveLands_Outdoors");
            float S_SaveLands_Transport = parameters.getFloatValue("S_SaveLands_Transport");
            float S_SaveLands_SiteDesign = parameters.getFloatValue("S_SaveLands_SiteDesign");
            float F_SaveEnergy = parameters.getFloatValue("F_SaveEnergy");//节能与能源利用
            float S_SaveEnergy_Build = parameters.getFloatValue("S_SaveEnergy_Build");
            float S_SaveEnergy_Condition = parameters.getFloatValue("S_SaveEnergy_Condition");
            float S_SaveEnergy_Light = parameters.getFloatValue("S_SaveEnergy_Light");
            float S_SaveEnergy_EnergyUse = parameters.getFloatValue("S_SaveEnergy_EnergyUse");
            float F_SaveWater = parameters.getFloatValue("F_SaveWater");//节水与水资源利用
            float S_SaveWater_System = parameters.getFloatValue("S_SaveWater_System");
            float S_SaveWater_Tool = parameters.getFloatValue("S_SaveWater_Tool");
            float S_SaveWater_Use = parameters.getFloatValue("S_SaveWater_Use");
            float F_SaveRes = parameters.getFloatValue("F_SaveRes");//节材与材料资源利用
            float S_SaveRes_SaveRes = parameters.getFloatValue("S_SaveRes_SaveRes");
            float S_SaveRes_Choose = parameters.getFloatValue("S_SaveRes_Choose");
            float F_Indoor = parameters.getFloatValue("F_Indoor");//室内环境质量
            float S_Indoor_Sound = parameters.getFloatValue("S_Indoor_Sound");
            float S_Indoor_Light = parameters.getFloatValue("S_Indoor_Light");
            float S_Indoor_Hot = parameters.getFloatValue("S_Indoor_Hot");
            float S_Indoor_Atmo = parameters.getFloatValue("S_Indoor_Atmo");
            float F_Construction = parameters.getFloatValue("F_Construction");//施工管理
            float S_Construction_Envir = parameters.getFloatValue("S_Construction_Envir");
            float S_Construction_Res = parameters.getFloatValue("S_Construction_Res");
            float S_Construction_Progress = parameters.getFloatValue("S_Construction_Progress");
            float F_Operation = parameters.getFloatValue("F_Operation");//运行管理
            float S_Operation_Management = parameters.getFloatValue("S_Operation_Management");
            float S_Operation_Tech = parameters.getFloatValue("S_Operation_Tech");
            float S_Operation_Envir = parameters.getFloatValue("S_Operation_Envir");
            float F_Innovation = parameters.getFloatValue("F_Innovation");//创新项评价
            float S_Innovation_Structure = parameters.getFloatValue("S_Innovation_Structure");
            float S_Innovation_Management = parameters.getFloatValue("S_Innovation_Management");
            float S_Innovation_Tech = parameters.getFloatValue("S_Innovation_Tech");
            float F_Humanity = parameters.getFloatValue("F_Humanity");//人文
            float S_Humanity_People = parameters.getFloatValue("S_Humanity_People");
            float S_Humanity_GreenLive = parameters.getFloatValue("S_Humanity_GreenLive");
            float S_Humanity_GreenEdu = parameters.getFloatValue("S_Humanity_GreenEdu");
            float S_Humanity_History = parameters.getFloatValue("S_Humanity_History");
            float F_Art = parameters.getFloatValue("F_Art");//艺术
            float S_Art_Design = parameters.getFloatValue("S_Art_Design");

            //经济效益
            float F_AssetPlan = parameters.getFloatValue("F_AssetPlan");//资产规划
            float S_AssetPlan_Para = parameters.getFloatValue("S_AssetPlan_Para");
            float F_AssetManagerment = parameters.getFloatValue("F_AssetManagerment");//资产运营
            float S_AssetManagerment_Early = parameters.getFloatValue("S_AssetManagerment_Early");
            float S_Managerment_Rent = parameters.getFloatValue("S_Managerment_Rent");
            float S_Managerment_Increment = parameters.getFloatValue("S_Managerment_Increment");
            float S_Managerment_Manager = parameters.getFloatValue("S_Managerment_Manager");
            float F_Financial = parameters.getFloatValue("F_Financial");//财务表现
            float S_Financial_Managerment = parameters.getFloatValue("S_Financial_Managerment");
            float S_Financial_Cash = parameters.getFloatValue("S_Financial_Cash");
            float S_Financial_Operate = parameters.getFloatValue("S_Financial_Operate");
            float S_Financial_Increment = parameters.getFloatValue("S_Financial_Increment");

            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            float grade = parameters.getFloatValue("grade");

            EnvirBenefit1 envirBenefit1 = envirBenefit1Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            envirBenefit1.setF_SaveLands(F_SaveLands);
            envirBenefit1.setS_SaveLands_LandUse(S_SaveLands_LandUse);
            envirBenefit1.setS_SaveLands_Outdoors(S_SaveLands_Outdoors);
            envirBenefit1.setS_SaveLands_Transport(S_SaveLands_Transport);
            envirBenefit1.setS_SaveLands_SiteDesign(S_SaveLands_SiteDesign);
            envirBenefit1.setF_SaveEnergy(F_SaveEnergy);
            envirBenefit1.setS_SaveEnergy_Build(S_SaveEnergy_Build);
            envirBenefit1.setS_SaveEnergy_Condition(S_SaveEnergy_Condition);
            envirBenefit1.setS_SaveEnergy_Light(S_SaveEnergy_Light);
            envirBenefit1.setS_SaveEnergy_EnergyUse(S_SaveEnergy_EnergyUse);
            envirBenefit1.setF_SaveWater(F_SaveWater);
            envirBenefit1.setS_SaveWater_System(S_SaveWater_System);
            envirBenefit1.setS_SaveWater_Tool(S_SaveWater_Tool);
            envirBenefit1.setS_SaveWater_Use(S_SaveWater_Use);
            envirBenefit1.setF_SaveRes(F_SaveRes);
            envirBenefit1.setS_SaveRes_SaveRes(S_SaveRes_SaveRes);
            envirBenefit1.setS_SaveRes_Choose(S_SaveRes_Choose);
            envirBenefit1.setF_Indoor(F_Indoor);
            envirBenefit1.setS_Indoor_Sound(S_Indoor_Sound);
            envirBenefit1.setS_Indoor_Light(S_Indoor_Light);
            envirBenefit1.setS_Indoor_Hot(S_Indoor_Hot);
            envirBenefit1.setS_Indoor_Atmo(S_Indoor_Atmo);
            envirBenefit1.setF_Construction(F_Construction);
            envirBenefit1.setS_Construction_Envir(S_Construction_Envir);
            envirBenefit1.setS_Construction_Res(S_Construction_Res);
            envirBenefit1.setS_Construction_Progress(S_Construction_Progress);
            envirBenefit1.setF_Operation(F_Operation);
            envirBenefit1.setS_Operation_Management(S_Operation_Management);
            envirBenefit1.setS_Operation_Tech(S_Operation_Tech);
            envirBenefit1.setS_Operation_Envir(S_Operation_Envir);
            envirBenefit1.setF_Innovation(F_Innovation);
            envirBenefit1.setS_Innovation_Structure(S_Innovation_Structure);
            envirBenefit1.setS_Innovation_Management(S_Innovation_Management);
            envirBenefit1.setS_Innovation_Tech(S_Innovation_Tech);
            envirBenefit1.setF_Humanity(F_Humanity);
            envirBenefit1.setS_Humanity_People(S_Humanity_People);
            envirBenefit1.setS_Humanity_GreenLive(S_Humanity_GreenLive);
            envirBenefit1.setS_Humanity_GreenEdu(S_Humanity_GreenEdu);
            envirBenefit1.setS_Humanity_History(S_Humanity_History);
            envirBenefit1.setF_Art(F_Art);
            envirBenefit1.setS_Art_Design(S_Art_Design);

            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);

            EconoBenefit1 econoBenefit1 = econoBenefit1Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            econoBenefit1.setF_AssetPlan(F_AssetPlan);
            econoBenefit1.setS_AssetPlan_Para(S_AssetPlan_Para);
            econoBenefit1.setF_AssetManagerment(F_AssetManagerment);
            econoBenefit1.setS_AssetManagerment_Early(S_AssetManagerment_Early);
            econoBenefit1.setS_Managerment_Rent(S_Managerment_Rent);
            econoBenefit1.setS_Managerment_Increment(S_Managerment_Increment);
            econoBenefit1.setS_Managerment_Manager(S_Managerment_Manager);
            econoBenefit1.setF_Financial(F_Financial);
            econoBenefit1.setS_Financial_Managerment(S_Financial_Managerment);
            econoBenefit1.setS_Financial_Cash(S_Financial_Cash);
            econoBenefit1.setS_Financial_Operate(S_Financial_Operate);
            econoBenefit1.setS_Financial_Increment(S_Financial_Increment);

            int code = type1Service.updateType1Score(envirBenefit1, socialBenefit, econoBenefit1, grade, expertId, state, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);
        }
        return result;
    }

    @RequestMapping(value = "/assess_type2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> assessType2(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");

            //环境效益
            float F_LandPlan = parameters.getFloatValue("F_LandPlan");
            float S_LandPlan_LandUse = parameters.getFloatValue("S_LandPlan_LandUse");
            float S_LandPlan_LayOut = parameters.getFloatValue("S_LandPlan_LayOut");
            float F_Eco = parameters.getFloatValue("F_Eco");
            float S_Eco_Nature = parameters.getFloatValue("S_Eco_Nature");
            float S_Eco_Envir = parameters.getFloatValue("S_Eco_Envir");
            float F_GreenBulid = parameters.getFloatValue("F_GreenBulid");
            float S_GreenBuild_GreenBulid = parameters.getFloatValue("S_GreenBuild_GreenBulid");
            float F_Res = parameters.getFloatValue("F_Res");
            float S_Res_Energy = parameters.getFloatValue("S_Res_Energy");
            float S_Res_Water = parameters.getFloatValue("S_Res_Water");
            float S_Res_Res = parameters.getFloatValue("S_Res_Res");
            float S_Res_Carbon = parameters.getFloatValue("S_Res_Carbon");
            float F_GreenTransport = parameters.getFloatValue("F_GreenTransport");
            float S_GreenTransport_Trans = parameters.getFloatValue("S_GreenTransport_Trans");
            float S_GreenTransport_Road = parameters.getFloatValue("S_GreenTransport_Road");
            float S_GreenTransport_Static = parameters.getFloatValue("S_GreenTransport_Static");
            float F_InfoManager = parameters.getFloatValue("F_InfoManager");
            float S_InfoManager_Urban = parameters.getFloatValue("S_InfoManager_Urban");
            float S_InfoManager_Service = parameters.getFloatValue("S_InfoManager_Service");
            float F_Innovation = parameters.getFloatValue("F_Innovation");//创新项评价
            float S_Innovation = parameters.getFloatValue("S_Innovation");
            float F_Humanity = parameters.getFloatValue("F_Humanity");//人文
            float S_Humanity_People = parameters.getFloatValue("S_Humanity_People");
            float S_Humanity_GreenLive = parameters.getFloatValue("S_Humanity_GreenLive");
            float S_Humanity_GreenEdu = parameters.getFloatValue("S_Humanity_GreenEdu");
            float S_Humanity_History = parameters.getFloatValue("S_Humanity_History");
            float F_Art = parameters.getFloatValue("F_Art");
            float S_Art_Bulid = parameters.getFloatValue("S_Art_Bulid");
            float S_Art_Envir = parameters.getFloatValue("S_Art_Envir");
            //经济效益
            float F_AssetPlan = parameters.getFloatValue("F_AssetPlan");
            float S_AssetPlan_Para = parameters.getFloatValue("S_AssetPlan_Para");
            float F_Industry = parameters.getFloatValue("F_Industry");//产业与经济
            float S_Industry_Early = parameters.getFloatValue("S_Industry_Early");
            float S_Industry_Rent = parameters.getFloatValue("S_Industry_Rent");
            float S_Industry_Increment = parameters.getFloatValue("S_Industry_Increment");
            float S_Industry_Manager = parameters.getFloatValue("S_Industry_Manager");
            float F_Financial = parameters.getFloatValue("F_Financial");//财务表现
            float S_Financial_Managerment = parameters.getFloatValue("S_Financial_Managerment");
            float S_Financial_Cash = parameters.getFloatValue("S_Financial_Cash");
            float S_Financial_Operate = parameters.getFloatValue("S_Financial_Operate");
            float S_Financial_Increment = parameters.getFloatValue("S_Financial_Increment");
            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            float grade = parameters.getFloat("grade");

            EnvirBenefit2 envirBenefit2 = new EnvirBenefit2();
            envirBenefit2.setExpertId(expertId);
            envirBenefit2.setProjectId(projectId);
            envirBenefit2.setF_LandPlan(F_LandPlan);
            envirBenefit2.setS_LandPlan_LandUse(S_LandPlan_LandUse);
            envirBenefit2.setS_LandPlan_LayOut(S_LandPlan_LayOut);
            envirBenefit2.setF_Eco(F_Eco);
            envirBenefit2.setS_Eco_Nature(S_Eco_Nature);
            envirBenefit2.setS_Eco_Envir(S_Eco_Envir);
            envirBenefit2.setF_GreenBulid(F_GreenBulid);
            envirBenefit2.setS_GreenBuild_GreenBulid(S_GreenBuild_GreenBulid);
            envirBenefit2.setF_Res(F_Res);
            envirBenefit2.setS_Res_Energy(S_Res_Energy);
            envirBenefit2.setS_Res_Water(S_Res_Water);
            envirBenefit2.setS_Res_Res(S_Res_Res);
            envirBenefit2.setS_Res_Carbon(S_Res_Carbon);
            envirBenefit2.setF_GreenTransport(F_GreenTransport);
            envirBenefit2.setS_GreenTransport_Trans(S_GreenTransport_Trans);
            envirBenefit2.setS_GreenTransport_Road(S_GreenTransport_Road);
            envirBenefit2.setS_GreenTransport_Static(S_GreenTransport_Static);
            envirBenefit2.setF_InfoManager(F_InfoManager);
            envirBenefit2.setS_InfoManager_Urban(S_InfoManager_Urban);
            envirBenefit2.setS_InfoManager_Service(S_InfoManager_Service);
            envirBenefit2.setF_Innovation(F_Innovation);
            envirBenefit2.setS_Innovation(S_Innovation);
            envirBenefit2.setF_Humanity(F_Humanity);
            envirBenefit2.setS_Humanity_People(S_Humanity_People);
            envirBenefit2.setS_Humanity_GreenLive(S_Humanity_GreenLive);
            envirBenefit2.setS_Humanity_GreenEdu(S_Humanity_GreenEdu);
            envirBenefit2.setS_Humanity_History(S_Humanity_History);
            envirBenefit2.setF_Art(F_Art);
            envirBenefit2.setS_Art_Bulid(S_Art_Bulid);
            envirBenefit2.setS_Art_Envir(S_Art_Envir);
            envirBenefit2.setState(state);

            SocialBenefit socialBenefit = new SocialBenefit();
            socialBenefit.setExpertId(expertId);
            socialBenefit.setProjectId(projectId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);
            socialBenefit.setState(state);

            EconoBenefit2 econoBenefit2 = new EconoBenefit2();
            econoBenefit2.setExpertId(expertId);
            econoBenefit2.setProjectId(projectId);
            econoBenefit2.setF_AssetPlan(F_AssetPlan);
            econoBenefit2.setS_AssetPlan_Para(S_AssetPlan_Para);
            econoBenefit2.setF_Industry(F_Industry);
            econoBenefit2.setS_Industry_Early(S_Industry_Early);
            econoBenefit2.setS_Industry_Rent(S_Industry_Rent);
            econoBenefit2.setS_Industry_Increment(S_Industry_Increment);
            econoBenefit2.setS_Industry_Manager(S_Industry_Manager);
            econoBenefit2.setF_Financial(F_Financial);
            econoBenefit2.setS_Financial_Managerment(S_Financial_Managerment);
            econoBenefit2.setS_Financial_Cash(S_Financial_Cash);
            econoBenefit2.setS_Financial_Operate(S_Financial_Operate);
            econoBenefit2.setS_Financial_Increment(S_Financial_Increment);
            econoBenefit2.setState(state);

            int code = type2Service.insertType2Score(envirBenefit2, socialBenefit, econoBenefit2, grade, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else if (code == -2)  // no right to assess
            {
                result.put("success", false);
                result.put("errMsg", -2);
            } else if (code == -1) //project has been assessed already
            {
                result.put("success", false);
                result.put("errMsg", -1);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);
            return result;
        }
    }

    @RequestMapping(value = "/update_type2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateType2(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");

            //环境效益
            float F_LandPlan = parameters.getFloatValue("F_LandPlan");
            float S_LandPlan_LandUse = parameters.getFloatValue("S_LandPlan_LandUse");
            float S_LandPlan_LayOut = parameters.getFloatValue("S_LandPlan_LayOut");
            float F_Eco = parameters.getFloatValue("F_Eco");
            float S_Eco_Nature = parameters.getFloatValue("S_Eco_Nature");
            float S_Eco_Envir = parameters.getFloatValue("S_Eco_Envir");
            float F_GreenBulid = parameters.getFloatValue("F_GreenBulid");
            float S_GreenBuild_GreenBulid = parameters.getFloatValue("S_GreenBuild_GreenBulid");
            float F_Res = parameters.getFloatValue("F_Res");
            float S_Res_Energy = parameters.getFloatValue("S_Res_Energy");
            float S_Res_Water = parameters.getFloatValue("S_Res_Water");
            float S_Res_Res = parameters.getFloatValue("S_Res_Res");
            float S_Res_Carbon = parameters.getFloatValue("S_Res_Carbon");
            float F_GreenTransport = parameters.getFloatValue("F_GreenTransport");
            float S_GreenTransport_Trans = parameters.getFloatValue("S_GreenTransport_Trans");
            float S_GreenTransport_Road = parameters.getFloatValue("S_GreenTransport_Road");
            float S_GreenTransport_Static = parameters.getFloatValue("S_GreenTransport_Static");
            float F_InfoManager = parameters.getFloatValue("F_InfoManager");
            float S_InfoManager_Urban = parameters.getFloatValue("S_InfoManager_Urban");
            float S_InfoManager_Service = parameters.getFloatValue("S_InfoManager_Service");
            float F_Innovation = parameters.getFloatValue("F_Innovation");//创新项评价
            float S_Innovation = parameters.getFloatValue("S_Innovation");
            float F_Humanity = parameters.getFloatValue("F_Humanity");//人文
            float S_Humanity_People = parameters.getFloatValue("S_Humanity_People");
            float S_Humanity_GreenLive = parameters.getFloatValue("S_Humanity_GreenLive");
            float S_Humanity_GreenEdu = parameters.getFloatValue("S_Humanity_GreenEdu");
            float S_Humanity_History = parameters.getFloatValue("S_Humanity_History");
            float F_Art = parameters.getFloatValue("F_Art");
            float S_Art_Bulid = parameters.getFloatValue("S_Art_Bulid");
            float S_Art_Envir = parameters.getFloatValue("S_Art_Envir");
            //经济效益
            float F_AssetPlan = parameters.getFloatValue("F_AssetPlan");
            float S_AssetPlan_Para = parameters.getFloatValue("S_AssetPlan_Para");
            float F_Industry = parameters.getFloatValue("F_Industry");//产业与经济
            float S_Industry_Early = parameters.getFloatValue("S_Industry_Early");
            float S_Industry_Rent = parameters.getFloatValue("S_Industry_Rent");
            float S_Industry_Increment = parameters.getFloatValue("S_Industry_Increment");
            float S_Industry_Manager = parameters.getFloatValue("S_Industry_Manager");
            float F_Financial = parameters.getFloatValue("F_Financial");//财务表现
            float S_Financial_Managerment = parameters.getFloatValue("S_Financial_Managerment");
            float S_Financial_Cash = parameters.getFloatValue("S_Financial_Cash");
            float S_Financial_Operate = parameters.getFloatValue("S_Financial_Operate");
            float S_Financial_Increment = parameters.getFloatValue("S_Financial_Increment");
            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            float grade = parameters.getFloat("grade");

            EnvirBenefit2 envirBenefit2 = envirBenefit2Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            envirBenefit2.setF_LandPlan(F_LandPlan);
            envirBenefit2.setS_LandPlan_LandUse(S_LandPlan_LandUse);
            envirBenefit2.setS_LandPlan_LayOut(S_LandPlan_LayOut);
            envirBenefit2.setF_Eco(F_Eco);
            envirBenefit2.setS_Eco_Nature(S_Eco_Nature);
            envirBenefit2.setS_Eco_Envir(S_Eco_Envir);
            envirBenefit2.setF_GreenBulid(F_GreenBulid);
            envirBenefit2.setS_GreenBuild_GreenBulid(S_GreenBuild_GreenBulid);
            envirBenefit2.setF_Res(F_Res);
            envirBenefit2.setS_Res_Energy(S_Res_Energy);
            envirBenefit2.setS_Res_Water(S_Res_Water);
            envirBenefit2.setS_Res_Res(S_Res_Res);
            envirBenefit2.setS_Res_Carbon(S_Res_Carbon);
            envirBenefit2.setF_GreenTransport(F_GreenTransport);
            envirBenefit2.setS_GreenTransport_Trans(S_GreenTransport_Trans);
            envirBenefit2.setS_GreenTransport_Road(S_GreenTransport_Road);
            envirBenefit2.setS_GreenTransport_Static(S_GreenTransport_Static);
            envirBenefit2.setF_InfoManager(F_InfoManager);
            envirBenefit2.setS_InfoManager_Urban(S_InfoManager_Urban);
            envirBenefit2.setS_InfoManager_Service(S_InfoManager_Service);
            envirBenefit2.setF_Innovation(F_Innovation);
            envirBenefit2.setS_Innovation(S_Innovation);
            envirBenefit2.setF_Humanity(F_Humanity);
            envirBenefit2.setS_Humanity_People(S_Humanity_People);
            envirBenefit2.setS_Humanity_GreenLive(S_Humanity_GreenLive);
            envirBenefit2.setS_Humanity_GreenEdu(S_Humanity_GreenEdu);
            envirBenefit2.setS_Humanity_History(S_Humanity_History);
            envirBenefit2.setF_Art(F_Art);
            envirBenefit2.setS_Art_Bulid(S_Art_Bulid);
            envirBenefit2.setS_Art_Envir(S_Art_Envir);

            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);

            EconoBenefit2 econoBenefit2 = econoBenefit2Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            econoBenefit2.setF_AssetPlan(F_AssetPlan);
            econoBenefit2.setS_AssetPlan_Para(S_AssetPlan_Para);
            econoBenefit2.setF_Industry(F_Industry);
            econoBenefit2.setS_Industry_Early(S_Industry_Early);
            econoBenefit2.setS_Industry_Rent(S_Industry_Rent);
            econoBenefit2.setS_Industry_Increment(S_Industry_Increment);
            econoBenefit2.setS_Industry_Manager(S_Industry_Manager);
            econoBenefit2.setF_Financial(F_Financial);
            econoBenefit2.setS_Financial_Managerment(S_Financial_Managerment);
            econoBenefit2.setS_Financial_Cash(S_Financial_Cash);
            econoBenefit2.setS_Financial_Operate(S_Financial_Operate);
            econoBenefit2.setS_Financial_Increment(S_Financial_Increment);

            int code = type2Service.updateType2Score(envirBenefit2, socialBenefit, econoBenefit2, grade, expertId, state, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);
            return result;
        }
    }


    @RequestMapping(value = "/assess_type3", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> assessType3(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");

            //环境效益
            float F_Function = parameters.getFloatValue("F_Function");
            float S_Function_Use = parameters.getFloatValue("S_Function_Use");
            float S_Function_Service = parameters.getFloatValue("S_Function_Service");
            float S_Function_Arrivable = parameters.getFloatValue("S_Function_Arrivable");
            float S_Function_Open = parameters.getFloatValue("S_Function_Open");
            float S_Function_Safe = parameters.getFloatValue("S_Function_Safe");
            float F_Tech = parameters.getFloatValue("F_Tech");
            float S_Tech_Operate = parameters.getFloatValue("S_Tech_Operate");
            float S_Tech_Curing = parameters.getFloatValue("S_Tech_Curing");
            float S_Tech_Material = parameters.getFloatValue("S_Tech_Material");
            float F_Human = parameters.getFloatValue("F_Human");
            float S_Human_Design = parameters.getFloatValue("S_Human_Design");
            float S_Human_Explore = parameters.getFloatValue("S_Human_Explore");
            float F_Envir = parameters.getFloatValue("F_Envir");
            float S_Envir_EnegrySave = parameters.getFloatValue("S_Envir_EnegrySave");
            float S_Envir_WaterSave = parameters.getFloatValue("S_Envir_WaterSave");
            float S_Envir_Res = parameters.getFloatValue("S_Envir_Res");
            float S_Envir_System = parameters.getFloatValue("S_Envir_System");

            //经济效益
            float F_AssetManager = parameters.getFloatValue("F_AssetManager");
            float S_AssetManager_Early = parameters.getFloatValue("S_AssetManager_Early");
            float S_AssetManager_Service = parameters.getFloatValue("S_AssetManager_Service");
            float S_AssetManager_MoreAsset = parameters.getFloatValue("S_AssetManager_MoreAsset");
            float F_ProjectManager = parameters.getFloatValue("F_ProjectManager");
            float S_ProjectManager_Cost = parameters.getFloatValue("S_ProjectManager_Cost");
            float S_ProjectManager_Method = parameters.getFloatValue("S_ProjectManager_Method");

            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            float grade = parameters.getFloat("grade");

            EnvirBenefit3 envirBenefit3 = new EnvirBenefit3();
            envirBenefit3.setExpertId(expertId);
            envirBenefit3.setProjectId(projectId);
            envirBenefit3.setF_Function(F_Function);
            envirBenefit3.setS_Function_Use(S_Function_Use);
            envirBenefit3.setS_Function_Service(S_Function_Service);
            envirBenefit3.setS_Function_Arrivable(S_Function_Arrivable);
            envirBenefit3.setS_Function_Open(S_Function_Open);
            envirBenefit3.setS_Function_Safe(S_Function_Safe);
            envirBenefit3.setF_Tech(F_Tech);
            envirBenefit3.setS_Tech_Operate(S_Tech_Operate);
            envirBenefit3.setS_Tech_Curing(S_Tech_Curing);
            envirBenefit3.setS_Tech_Material(S_Tech_Material);
            envirBenefit3.setF_Human(F_Human);
            envirBenefit3.setS_Human_Design(S_Human_Design);
            envirBenefit3.setS_Human_Explore(S_Human_Explore);
            envirBenefit3.setF_Envir(F_Envir);
            envirBenefit3.setS_Envir_EnegrySave(S_Envir_EnegrySave);
            envirBenefit3.setS_Envir_WaterSave(S_Envir_WaterSave);
            envirBenefit3.setS_Envir_Res(S_Envir_Res);
            envirBenefit3.setS_Envir_System(S_Envir_System);
            envirBenefit3.setState(state);

            SocialBenefit socialBenefit = new SocialBenefit();
            socialBenefit.setExpertId(expertId);
            socialBenefit.setProjectId(projectId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);
            socialBenefit.setState(state);

            EconoBenefit3 econoBenefit3 = new EconoBenefit3();
            econoBenefit3.setExpertId(expertId);
            econoBenefit3.setProjectId(projectId);
            econoBenefit3.setF_AssetManager(F_AssetManager);
            econoBenefit3.setS_AssetManager_Early(S_AssetManager_Early);
            econoBenefit3.setS_AssetManager_Service(S_AssetManager_Service);
            econoBenefit3.setS_AssetManager_MoreAsset(S_AssetManager_MoreAsset);
            econoBenefit3.setF_ProjectManager(F_ProjectManager);
            econoBenefit3.setS_ProjectManager_Cost(S_ProjectManager_Cost);
            econoBenefit3.setS_ProjectManager_Method(S_ProjectManager_Method);
            econoBenefit3.setState(state);

            int code = type3Service.insertType3Score(envirBenefit3, socialBenefit, econoBenefit3, grade, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else if (code == -2)  // no right to assess
            {
                result.put("success", false);
                result.put("errMsg", -2);
            } else if (code == -1) //project has been assessed already
            {
                result.put("success", false);
                result.put("errMsg", -1);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);

        }
        return result;
    }

    @RequestMapping(value = "/update_type3", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateType3(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");

            //环境效益
            float F_Function = parameters.getFloatValue("F_Function");
            float S_Function_Use = parameters.getFloatValue("S_Function_Use");
            float S_Function_Service = parameters.getFloatValue("S_Function_Service");
            float S_Function_Arrivable = parameters.getFloatValue("S_Function_Arrivable");
            float S_Function_Open = parameters.getFloatValue("S_Function_Open");
            float S_Function_Safe = parameters.getFloatValue("S_Function_Safe");
            float F_Tech = parameters.getFloatValue("F_Tech");
            float S_Tech_Operate = parameters.getFloatValue("S_Tech_Operate");
            float S_Tech_Curing = parameters.getFloatValue("S_Tech_Curing");
            float S_Tech_Material = parameters.getFloatValue("S_Tech_Material");
            float F_Human = parameters.getFloatValue("F_Human");
            float S_Human_Design = parameters.getFloatValue("S_Human_Design");
            float S_Human_Explore = parameters.getFloatValue("S_Human_Explore");
            float F_Envir = parameters.getFloatValue("F_Envir");
            float S_Envir_EnegrySave = parameters.getFloatValue("S_Envir_EnegrySave");
            float S_Envir_WaterSave = parameters.getFloatValue("S_Envir_WaterSave");
            float S_Envir_Res = parameters.getFloatValue("S_Envir_Res");
            float S_Envir_System = parameters.getFloatValue("S_Envir_System");

            //经济效益
            float F_AssetManager = parameters.getFloatValue("F_AssetManager");
            float S_AssetManager_Early = parameters.getFloatValue("S_AssetManager_Early");
            float S_AssetManager_Service = parameters.getFloatValue("S_AssetManager_Service");
            float S_AssetManager_MoreAsset = parameters.getFloatValue("S_AssetManager_MoreAsset");
            float F_ProjectManager = parameters.getFloatValue("F_ProjectManager");
            float S_ProjectManager_Cost = parameters.getFloatValue("S_ProjectManager_Cost");
            float S_ProjectManager_Method = parameters.getFloatValue("S_ProjectManager_Method");

            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            float grade = parameters.getFloat("grade");

            EnvirBenefit3 envirBenefit3 = envirBenefit3Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            envirBenefit3.setF_Function(F_Function);
            envirBenefit3.setS_Function_Use(S_Function_Use);
            envirBenefit3.setS_Function_Service(S_Function_Service);
            envirBenefit3.setS_Function_Arrivable(S_Function_Arrivable);
            envirBenefit3.setS_Function_Open(S_Function_Open);
            envirBenefit3.setS_Function_Safe(S_Function_Safe);
            envirBenefit3.setF_Tech(F_Tech);
            envirBenefit3.setS_Tech_Operate(S_Tech_Operate);
            envirBenefit3.setS_Tech_Curing(S_Tech_Curing);
            envirBenefit3.setS_Tech_Material(S_Tech_Material);
            envirBenefit3.setF_Human(F_Human);
            envirBenefit3.setS_Human_Design(S_Human_Design);
            envirBenefit3.setS_Human_Explore(S_Human_Explore);
            envirBenefit3.setF_Envir(F_Envir);
            envirBenefit3.setS_Envir_EnegrySave(S_Envir_EnegrySave);
            envirBenefit3.setS_Envir_WaterSave(S_Envir_WaterSave);
            envirBenefit3.setS_Envir_Res(S_Envir_Res);
            envirBenefit3.setS_Envir_System(S_Envir_System);

            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);

            EconoBenefit3 econoBenefit3 = econoBenefit3Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            econoBenefit3.setF_AssetManager(F_AssetManager);
            econoBenefit3.setS_AssetManager_Early(S_AssetManager_Early);
            econoBenefit3.setS_AssetManager_Service(S_AssetManager_Service);
            econoBenefit3.setS_AssetManager_MoreAsset(S_AssetManager_MoreAsset);
            econoBenefit3.setF_ProjectManager(F_ProjectManager);
            econoBenefit3.setS_ProjectManager_Cost(S_ProjectManager_Cost);
            econoBenefit3.setS_ProjectManager_Method(S_ProjectManager_Method);

            int code = type3Service.updateType3Score(envirBenefit3, socialBenefit, econoBenefit3, grade, expertId, state, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);

        }
        return result;
    }

    @RequestMapping(value = "/assess_type4", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> assessType4(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");

            //经济效益
            float F_AssetPlan = parameters.getFloatValue("F_AssetPlan");//资产规划
            float S_AssetPlan_Para = parameters.getFloatValue("S_AssetPlan_Para");
            float F_AssetManagerment = parameters.getFloatValue("F_AssetManagerment");//资产运营
            float S_AssetManagerment_Early = parameters.getFloatValue("S_AssetManagerment_Early");
            float S_Managerment_Rent = parameters.getFloatValue("S_Managerment_Rent");
            float S_Managerment_Increment = parameters.getFloatValue("S_Managerment_Increment");
            float S_Managerment_Manager = parameters.getFloatValue("S_Managerment_Manager");
            float F_Financial = parameters.getFloatValue("F_Financial");//财务表现
            float S_Financial_Managerment = parameters.getFloatValue("S_Financial_Managerment");
            float S_Financial_Cash = parameters.getFloatValue("S_Financial_Cash");
            float S_Financial_Operate = parameters.getFloatValue("S_Financial_Operate");
            float S_Financial_Increment = parameters.getFloatValue("S_Financial_Increment");

            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            //环境效益
            float F_PhysicalEnvir = parameters.getFloatValue("F_PhysicalEnvir");
            float S_PhysicalEnvir_Sound = parameters.getFloatValue("S_PhysicalEnvir_Sound");
            float S_PhysicalEnvir_Light = parameters.getFloatValue("S_PhysicalEnvir_Light");
            float S_PhysicalEnvir_Hot = parameters.getFloatValue("S_PhysicalEnvir_Hot");
            float S_PhysicalEnvir_Wind = parameters.getFloatValue("S_PhysicalEnvir_Wind");
            float F_HumanEnvir = parameters.getFloatValue("F_HumanEnvir");
            float S_HumanEnvir_Function = parameters.getFloatValue("S_HumanEnvir_Function");
            float S_HumanEnvir_Beauty = parameters.getFloatValue("S_HumanEnvir_Beauty");
            float S_HumanEnvir_Explore = parameters.getFloatValue("S_HumanEnvir_Explore");
            float F_Decorate = parameters.getFloatValue("F_Decorate");
            float S_DecorateDetails = parameters.getFloatValue("S_DecorateDetails");
            float S_DecorateMaterial = parameters.getFloatValue("S_DecorateMaterial");
            float F_Tech = parameters.getFloatValue("F_Tech");
            float S_Tech_Envir = parameters.getFloatValue("S_Tech_Envir");
            float S_Tech_Res = parameters.getFloatValue("S_Tech_Res");
            float S_Tech_Progress = parameters.getFloatValue("S_Tech_Progress");

            float grade = parameters.getFloat("grade");

            EnvirBenefit4 envirBenefit4 = new EnvirBenefit4();
            envirBenefit4.setExpertId(expertId);
            envirBenefit4.setProjectId(projectId);
            envirBenefit4.setF_PhysicalEnvir(F_PhysicalEnvir);
            envirBenefit4.setS_PhysicalEnvir_Sound(S_PhysicalEnvir_Sound);
            envirBenefit4.setS_PhysicalEnvir_Light(S_PhysicalEnvir_Light);
            envirBenefit4.setS_PhysicalEnvir_Hot(S_PhysicalEnvir_Hot);
            envirBenefit4.setS_PhysicalEnvir_Wind(S_PhysicalEnvir_Wind);
            envirBenefit4.setF_HumanEnvir(F_HumanEnvir);
            envirBenefit4.setS_HumanEnvir_Function(S_HumanEnvir_Function);
            envirBenefit4.setS_HumanEnvir_Beauty(S_HumanEnvir_Beauty);
            envirBenefit4.setS_HumanEnvir_Explore(S_HumanEnvir_Explore);
            envirBenefit4.setF_Decorate(F_Decorate);
            envirBenefit4.setS_DecorateDetails(S_DecorateDetails);
            envirBenefit4.setS_DecorateMaterial(S_DecorateMaterial);
            envirBenefit4.setF_Tech(F_Tech);
            envirBenefit4.setS_Tech_Envir(S_Tech_Envir);
            envirBenefit4.setS_Tech_Res(S_Tech_Res);
            envirBenefit4.setS_Tech_Progress(S_Tech_Progress);
            envirBenefit4.setState(state);

            SocialBenefit socialBenefit = new SocialBenefit();
            socialBenefit.setExpertId(expertId);
            socialBenefit.setProjectId(projectId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);
            socialBenefit.setState(state);

            EconoBenefit4 econoBenefit4 = new EconoBenefit4();
            econoBenefit4.setExpertId(expertId);
            econoBenefit4.setProjectId(projectId);
            econoBenefit4.setF_AssetPlan(F_AssetPlan);
            econoBenefit4.setS_AssetPlan_Para(S_AssetPlan_Para);
            econoBenefit4.setF_AssetManagerment(F_AssetManagerment);
            econoBenefit4.setS_AssetManagerment_Early(S_AssetManagerment_Early);
            econoBenefit4.setS_Managerment_Rent(S_Managerment_Rent);
            econoBenefit4.setS_Managerment_Increment(S_Managerment_Increment);
            econoBenefit4.setS_Managerment_Manager(S_Managerment_Manager);
            econoBenefit4.setF_Financial(F_Financial);
            econoBenefit4.setS_Financial_Managerment(S_Financial_Managerment);
            econoBenefit4.setS_Financial_Cash(S_Financial_Cash);
            econoBenefit4.setS_Financial_Operate(S_Financial_Operate);
            econoBenefit4.setS_Financial_Increment(S_Financial_Increment);
            econoBenefit4.setState(state);

            int code = type4Service.insertType4Score(envirBenefit4, socialBenefit, econoBenefit4, grade, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else if (code == -2)  // no right to assess
            {
                result.put("success", false);
                result.put("errMsg", -2);
            } else if (code == -1) //project has been assessed already
            {
                result.put("success", false);
                result.put("errMsg", -1);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);
            return result;
        }
    }

    @RequestMapping(value = "/update_type4", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateType4(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject parameters = JsonUtil.getRequestJsonObject(request);
            Long projectId = parameters.getLong("projectId");
            Long expertId = parameters.getLong("expertId");
            int state = parameters.getIntValue("state");
            String evaluation = parameters.getString("evaluation");
            float impression = parameters.getFloatValue("impression");

            //经济效益
            float F_AssetPlan = parameters.getFloatValue("F_AssetPlan");//资产规划
            float S_AssetPlan_Para = parameters.getFloatValue("S_AssetPlan_Para");
            float F_AssetManagerment = parameters.getFloatValue("F_AssetManagerment");//资产运营
            float S_AssetManagerment_Early = parameters.getFloatValue("S_AssetManagerment_Early");
            float S_Managerment_Rent = parameters.getFloatValue("S_Managerment_Rent");
            float S_Managerment_Increment = parameters.getFloatValue("S_Managerment_Increment");
            float S_Managerment_Manager = parameters.getFloatValue("S_Managerment_Manager");
            float F_Financial = parameters.getFloatValue("F_Financial");//财务表现
            float S_Financial_Managerment = parameters.getFloatValue("S_Financial_Managerment");
            float S_Financial_Cash = parameters.getFloatValue("S_Financial_Cash");
            float S_Financial_Operate = parameters.getFloatValue("S_Financial_Operate");
            float S_Financial_Increment = parameters.getFloatValue("S_Financial_Increment");

            //社会效益
            float F_Social = parameters.getFloatValue("F_Social");
            float S_Social_Develop = parameters.getFloatValue("S_Social_Develop");
            float S_Social_People = parameters.getFloatValue("S_Social_People");
            float S_Social_Green = parameters.getFloatValue("S_Social_Green");
            float F_Work = parameters.getFloatValue("F_Work");
            float F_Work_Brand = parameters.getFloatValue("F_Work_Brand");
            float F_Work_Develop = parameters.getFloatValue("F_Work_Develop");

            //环境效益
            float F_PhysicalEnvir = parameters.getFloatValue("F_PhysicalEnvir");
            float S_PhysicalEnvir_Sound = parameters.getFloatValue("S_PhysicalEnvir_Sound");
            float S_PhysicalEnvir_Light = parameters.getFloatValue("S_PhysicalEnvir_Light");
            float S_PhysicalEnvir_Hot = parameters.getFloatValue("S_PhysicalEnvir_Hot");
            float S_PhysicalEnvir_Wind = parameters.getFloatValue("S_PhysicalEnvir_Wind");
            float F_HumanEnvir = parameters.getFloatValue("F_HumanEnvir");
            float S_HumanEnvir_Function = parameters.getFloatValue("S_HumanEnvir_Function");
            float S_HumanEnvir_Beauty = parameters.getFloatValue("S_HumanEnvir_Beauty");
            float S_HumanEnvir_Explore = parameters.getFloatValue("S_HumanEnvir_Explore");
            float F_Decorate = parameters.getFloatValue("F_Decorate");
            float S_DecorateDetails = parameters.getFloatValue("S_DecorateDetails");
            float S_DecorateMaterial = parameters.getFloatValue("S_DecorateMaterial");
            float F_Tech = parameters.getFloatValue("F_Tech");
            float S_Tech_Envir = parameters.getFloatValue("S_Tech_Envir");
            float S_Tech_Res = parameters.getFloatValue("S_Tech_Res");
            float S_Tech_Progress = parameters.getFloatValue("S_Tech_Progress");

            float grade = parameters.getFloat("grade");

            EnvirBenefit4 envirBenefit4 = envirBenefit4Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            envirBenefit4.setF_PhysicalEnvir(F_PhysicalEnvir);
            envirBenefit4.setS_PhysicalEnvir_Sound(S_PhysicalEnvir_Sound);
            envirBenefit4.setS_PhysicalEnvir_Light(S_PhysicalEnvir_Light);
            envirBenefit4.setS_PhysicalEnvir_Hot(S_PhysicalEnvir_Hot);
            envirBenefit4.setS_PhysicalEnvir_Wind(S_PhysicalEnvir_Wind);
            envirBenefit4.setF_HumanEnvir(F_HumanEnvir);
            envirBenefit4.setS_HumanEnvir_Function(S_HumanEnvir_Function);
            envirBenefit4.setS_HumanEnvir_Beauty(S_HumanEnvir_Beauty);
            envirBenefit4.setS_HumanEnvir_Explore(S_HumanEnvir_Explore);
            envirBenefit4.setF_Decorate(F_Decorate);
            envirBenefit4.setS_DecorateDetails(S_DecorateDetails);
            envirBenefit4.setS_DecorateMaterial(S_DecorateMaterial);
            envirBenefit4.setF_Tech(F_Tech);
            envirBenefit4.setS_Tech_Envir(S_Tech_Envir);
            envirBenefit4.setS_Tech_Res(S_Tech_Res);
            envirBenefit4.setS_Tech_Progress(S_Tech_Progress);

            SocialBenefit socialBenefit = socialBenefitMapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            socialBenefit.setF_Social(F_Social);
            socialBenefit.setS_Social_Develop(S_Social_Develop);
            socialBenefit.setS_Social_People(S_Social_People);
            socialBenefit.setS_Social_Green(S_Social_Green);
            socialBenefit.setF_Work(F_Work);
            socialBenefit.setF_Work_Brand(F_Work_Brand);
            socialBenefit.setF_Work_Develop(F_Work_Develop);

            EconoBenefit4 econoBenefit4 = econoBenefit4Mapper.queryScoreByProjectIdAndState(projectId, state, expertId);
            econoBenefit4.setF_AssetPlan(F_AssetPlan);
            econoBenefit4.setS_AssetPlan_Para(S_AssetPlan_Para);
            econoBenefit4.setF_AssetManagerment(F_AssetManagerment);
            econoBenefit4.setS_AssetManagerment_Early(S_AssetManagerment_Early);
            econoBenefit4.setS_Managerment_Rent(S_Managerment_Rent);
            econoBenefit4.setS_Managerment_Increment(S_Managerment_Increment);
            econoBenefit4.setS_Managerment_Manager(S_Managerment_Manager);
            econoBenefit4.setF_Financial(F_Financial);
            econoBenefit4.setS_Financial_Managerment(S_Financial_Managerment);
            econoBenefit4.setS_Financial_Cash(S_Financial_Cash);
            econoBenefit4.setS_Financial_Operate(S_Financial_Operate);
            econoBenefit4.setS_Financial_Increment(S_Financial_Increment);

            int code = type4Service.updateType4Score(envirBenefit4, socialBenefit, econoBenefit4, grade, expertId, state, evaluation, impression);
            if (code == 1) {
                result.put("success", 1);
                result.put("errMsg", null);
            } else {
                result.put("success", false);
                result.put("errMsg", 1);
            }
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", 1);
            return result;
        }
    }

    @RequestMapping(value = "/get_project_grade/{type}/{projectId}/{state}/{expertId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryScoreByTypeAndProjectIdAndState(@PathVariable("type") int type,
                                                                    @PathVariable("projectId") Long projectId,
                                                                    @PathVariable("state") int state,
                                                                    @PathVariable("expertId") Long expertId) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Benefit> score;
        ProjectAssignment projectAssignment =
                projectAssignmentMapper.queryAssignmentByProjectIdAndExpertIdAndState(projectId, expertId, state);
        switch (type) {
            case 1:
                score = type1Service.queryScoreByProjectIdAndState(projectId, state, expertId);

                if (score != null) {
                    EnvirBenefit1 envirBenefit1 = (EnvirBenefit1) score.get("envirBenefit1");
                    if (envirBenefit1 == null) {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    } else {
                        result.put("success", 1);
                        result.put("errMsg", null);
                        result.put("envirBenefit1", envirBenefit1);
                        result.put("socialBenefit", (SocialBenefit) score.get("socialBenefit"));
                        result.put("econoBenefit1", (EconoBenefit1) score.get("econoBenefit1"));
                    }
                } else {

                    result.put("success", 0);
                    result.put("errMsg", "1");
                }
                break;
            case 2:
                score = type2Service.queryScoreByProjectIdAndState(projectId, state, expertId);
                if (score != null) {
                    EnvirBenefit2 envirBenefit2 = (EnvirBenefit2) score.get("envirBenefit2");
                    if (envirBenefit2 == null) {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    } else {
                        result.put("success", 1);
                        result.put("errMsg", null);
                        result.put("envirBenefit2", envirBenefit2);
                        result.put("socialBenefit", (SocialBenefit) score.get("socialBenefit"));
                        result.put("econoBenefit2", (EconoBenefit2) score.get("econoBenefit2"));
                    }
                } else {
                    result.put("success", 0);
                    result.put("errMsg", "1");
                }
                break;
            case 3:
                score = type3Service.queryScoreByProjectIdAndState(projectId, state, expertId);
                if (score != null) {
                    EnvirBenefit3 envirBenefit3 = (EnvirBenefit3) score.get("envirBenefit3");
                    if (envirBenefit3 == null) {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    } else {
                        result.put("success", 1);
                        result.put("errMsg", null);
                        result.put("envirBenefit3", envirBenefit3);
                        result.put("socialBenefit", (SocialBenefit) score.get("socialBenefit"));
                        result.put("econoBenefit3", (EconoBenefit3) score.get("econoBenefit3"));
                    }
                } else {
                    result.put("success", 0);
                    result.put("errMsg", "1");
                }
                break;
            case 4:
                score = type4Service.queryScoreByProjectIdAndState(projectId, state, expertId);
                if (score != null) {
                    EnvirBenefit4 envirBenefit4 = (EnvirBenefit4) score.get("envirBenefit4");
                    if (envirBenefit4 == null) {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    } else {
                        result.put("success", 1);
                        result.put("errMsg", null);
                        result.put("envirBenefit4", envirBenefit4);
                        result.put("socialBenefit", (SocialBenefit) score.get("socialBenefit"));
                        result.put("econoBenefit4", (EconoBenefit4) score.get("econoBenefit4"));
                    }
                } else {
                    result.put("success", 0);
                    result.put("errMsg", "1");
                }
                break;
        }
        if (projectAssignment != null && projectAssignment.getFinish() == 1)
            result.put("grade", projectAssignment.getGrade());
        result.put("evaluation", projectAssignment.getEvaluation());
        result.put("impression", projectAssignment.getImpression());
        return result;
    }


    @RequestMapping(value = "/get_project_avg_grade/{type}/{projectId}/{state}/{expertId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryAvgScoreByTypeAndProjectIdAndState(@PathVariable("type") int type,
                                                                       @PathVariable("projectId") Long projectId,
                                                                       @PathVariable("state") int state,
                                                                       @PathVariable("expertId") Long expertId)
        /*type：项目类型
         * projectId：项目ID
         * state：评审阶段，传1
         * expertId：专家ID*/ {
        Map<String, Object> result = new HashMap<>();
        FinalGrade finalGrade = finalGradeService.queryFinalGradeByProjectId(expertId, projectId);
        switch (type) {
            case 1:
                try {
                    Map<String, Float> avgScore1 = type1Service.queryScoresByProjectIdAndState(projectId, state);
                    Project project1 = projectService.queryProjectById(projectId);

                    if (avgScore1 != null) {

                        result.put("success", 1);
                        result.put("errMsg", null);

                        result.put("F_AssetPlan", avgScore1.get("F_AssetPlan"));//资产规划
                        result.put("F_AssetManagerment", avgScore1.get("F_AssetManagerment"));//资产运营
                        result.put("F_Financial", avgScore1.get("F_Financial"));//财务表现

                        result.put("F_Social", avgScore1.get("F_Social"));//社会影响
                        result.put("F_Work", avgScore1.get("F_Work"));//行业影响

                        result.put("F_SaveLands", avgScore1.get("F_SaveLands"));//节地与室外环境
                        result.put("F_SaveEnergy", avgScore1.get("F_SaveEnergy"));//节能与能源利用
                        result.put("F_SaveWater", avgScore1.get("F_SaveWater"));//节水与水资源利用
                        result.put("F_SaveRes", avgScore1.get("F_SaveRes"));//节材与材料资源利用
                        result.put("F_Indoor", avgScore1.get("F_Indoor"));//室内环境质量
                        result.put("F_Construction", avgScore1.get("F_Construction"));//施工管理
                        result.put("F_Operation", avgScore1.get("F_Operation"));//运行管理
                        result.put("F_Innovation", avgScore1.get("F_Innovation"));//创新项评价
                        result.put("F_Humanity", avgScore1.get("F_Humanity"));//人文
                        result.put("F_Art", avgScore1.get("F_Art"));//艺术
                        
                        /*result.put("F_AssetPlan", avgScore1.get("F_AssetPlan"));
                        result.put("S_AssetPlan_Para",avgScore1.get("S_AssetPlan_Para"));
                        result.put("F_AssetManagerment",avgScore1.get("F_AssetManagerment"));
                        result.put("S_AssetManagerment_Early",avgScore1.get("S_AssetManagerment_Early"));
                        result.put("S_Managerment_Rent",avgScore1.get("S_Managerment_Rent"));
                        result.put("S_Managerment_Increment",avgScore1.get("S_Managerment_Increment"));
                        result.put("S_Managerment_Manager",avgScore1.get("S_Managerment_Manager"));
                        result.put("F_Financial",avgScore1.get("F_Financial"));
                        result.put("S_Financial_Managerment",avgScore1.get("S_Financial_Managerment"));
                        result.put("S_Financial_Cash",avgScore1.get("S_Financial_Cash"));
                        result.put("S_Financial_Operate",avgScore1.get("S_Financial_Operate"));
                        result.put("S_Financial_Increment",avgScore1.get("S_Financial_Increment"));  
                        result.put("F_Art",avgScore1.get("F_Art"));
                        
                        result.put("F_Social",avgScore1.get("F_Social"));
                        result.put("S_Social_Develop",avgScore1.get("S_Social_Develop"));
                        result.put("S_Social_People",avgScore1.get("S_Social_People"));
                        result.put("S_Social_Green",avgScore1.get("S_Social_Green"));
                        result.put("F_Work",avgScore1.get("F_Work"));                        
                        result.put("F_Work_Brand",avgScore1.get("F_Work_Brand"));
                        result.put("F_Work_Develop",avgScore1.get("F_Work_Develop"));
                                           
                        result.put("F_SaveLands",avgScore1.get("F_SaveLands"));
                        result.put("S_SaveLands_LandUse",avgScore1.get("S_SaveLands_LandUse"));
                        result.put("S_SaveLands_Outdoors",avgScore1.get("S_SaveLands_Outdoors"));
                        result.put("S_SaveLands_Transport",avgScore1.get("S_SaveLands_Transport"));
                        result.put("S_SaveLands_SiteDesign",avgScore1.get("S_SaveLands_SiteDesign"));
                        result.put("F_SaveEnergy",avgScore1.get("F_SaveEnergy"));
                        result.put("S_SaveEnergy_Build",avgScore1.get("S_SaveEnergy_Build"));
                        result.put("S_SaveEnergy_Condition",avgScore1.get("S_SaveEnergy_Condition"));                    
                        result.put("S_SaveEnergy_Light",avgScore1.get("S_SaveEnergy_Light"));
                        result.put("S_SaveEnergy_EnergyUse",avgScore1.get("S_SaveEnergy_EnergyUse"));                        
                        result.put("F_SaveWater", avgScore1.get("F_SaveWater"));
                        result.put("S_SaveWater_System", avgScore1.get("S_SaveWater_System"));
                        result.put("S_SaveWater_Tool",avgScore1.get("S_SaveWater_Tool"));
                        result.put("S_SaveWater_Use", avgScore1.get("S_SaveWater_Use"));                        
                        result.put("F_SaveRes",avgScore1.get("F_SaveRes"));                        
                        result.put("S_SaveRes_SaveRes",avgScore1.get("S_SaveRes_SaveRes"));
                        result.put("S_SaveRes_Choose",avgScore1.get("S_SaveRes_Choose"));
                        result.put("F_Indoor",avgScore1.get("F_Indoor"));
                        result.put("S_Indoor_Sound",avgScore1.get("S_Indoor_Sound"));
                        result.put("S_Indoor_Light",avgScore1.get("S_Indoor_Light"));
                        result.put("S_Indoor_Hot",avgScore1.get("S_Indoor_Hot"));
                        result.put("S_Indoor_Atmo",avgScore1.get("S_Indoor_Atmo"));
                        result.put("F_Construction",avgScore1.get("F_Construction"));
                        result.put("S_Construction_Envir",avgScore1.get("S_Construction_Envir"));
                        result.put("S_Construction_Res",avgScore1.get("S_Construction_Res"));
                        result.put("S_Construction_Progress",avgScore1.get("S_Construction_Progress"));
                        result.put("F_Operation",avgScore1.get("F_Operation"));
                        result.put("S_Operation_Management",avgScore1.get("S_Operation_Management"));
                        result.put("S_Operation_Tech",avgScore1.get("S_Operation_Tech"));
                        result.put("S_Operation_Envir",avgScore1.get("S_Operation_Envir"));                        
                        result.put("F_Innovation", avgScore1.get("F_Innovation"));
                        result.put("S_Innovation_Structure", avgScore1.get("S_Innovation_Structure"));
                        result.put("S_Innovation_Management", avgScore1.get("S_Innovation_Management"));
                        result.put("S_Innovation_Tech", avgScore1.get("S_Innovation_Tech"));                      
                        result.put("F_Humanity",avgScore1.get("F_Humanity"));
                        result.put("S_Humanity_People",avgScore1.get("S_Humanity_People"));
                        result.put("S_Humanity_GreenLive",avgScore1.get("S_Humanity_GreenLive"));
                        result.put("S_Humanity_GreenEdu",avgScore1.get("S_Humanity_GreenEdu"));
                        result.put("S_Humanity_History",avgScore1.get("S_Humanity_History"));
                        result.put("F_Art",avgScore1.get("F_Art"));
                        result.put("S_Art_Design",avgScore1.get("S_Art_Design"));*/

                        if (state == 1)
                            result.put("grade", project1.getfGrade());
                        else if (state == 2)
                            result.put("grade", project1.getlGrade());
                        else
                            result.put("grade", project1.getFinalGrade());
                    } else {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", 0);
                    result.put("errMsg", "1");
                }
                break;
            case 2:
                try {
                    Map<String, Float> avgScore2 = type2Service.queryScoresByProjectIdAndState(projectId, state);
                    Project project2 = projectService.queryProjectById(projectId);
                    if (avgScore2 != null) {

                        result.put("success", 1);
                        result.put("errMsg", null);

                        result.put("F_Social", avgScore2.get("F_Social"));//社会影响
                        result.put("F_Work", avgScore2.get("F_Work"));//行业影响

                        result.put("F_AssetPlan", avgScore2.get("F_AssetPlan"));//资产规划
                        result.put("F_Industry", avgScore2.get("F_Industry"));//产业与经济
                        result.put("F_Financial", avgScore2.get("F_Financial"));//技术创新

                        result.put("F_LandPlan", avgScore2.get("F_LandPlan"));//用地规划
                        result.put("F_Eco", avgScore2.get("F_Eco"));//生态环境
                        result.put("F_GreenBulid", avgScore2.get("F_GreenBulid"));//绿色建筑
                        result.put("F_Res", avgScore2.get("F_Res"));//资源与碳排放
                        result.put("F_GreenTransport", avgScore2.get("F_GreenTransport"));//绿色交通
                        result.put("F_InfoManager", avgScore2.get("F_InfoManager"));//信息化管理
                        result.put("F_Innovation", avgScore2.get("F_Innovation"));//技术创新
                        result.put("F_Humanity", avgScore2.get("F_Humanity"));//人文
                        result.put("F_Art", avgScore2.get("F_Art"));//艺术
                        
                        /*result.put("F_Social",avgScore2.get("F_Social"));
                        result.put("S_Social_Develop",avgScore2.get("S_Social_Develop"));
                        result.put("S_Social_People",avgScore2.get("S_Social_People"));
                        result.put("S_Social_Green",avgScore2.get("S_Social_Green"));
                        result.put("F_Work",avgScore2.get("F_Work"));
                        result.put("F_Work_Brand",avgScore2.get("F_Work_Brand"));
                        result.put("F_Work_Develop",avgScore2.get("F_Work_Develop"));
                        
                        result.put("F_AssetPlan", avgScore2.get("F_AssetPlan"));
                        result.put("S_AssetPlan_Para", avgScore2.get("S_AssetPlan_Para"));
                        result.put("F_Industry", avgScore2.get("F_Industry"));
                        result.put("S_Industry_Early", avgScore2.get("S_Industry_Early"));
                        result.put("S_Industry_Rent", avgScore2.get("S_Industry_Rent"));
                        result.put("S_Industry_Increment", avgScore2.get("S_Industry_Increment"));
                        result.put("S_Industry_Manager", avgScore2.get("S_Industry_Manager"));
                        result.put("F_Financial", avgScore2.get("F_Financial"));
                        result.put("S_Financial_Managerment", avgScore2.get("S_Financial_Managerment"));
                        result.put("S_Financial_Cash", avgScore2.get("S_Financial_Cash"));
                        result.put("S_Financial_Operate", avgScore2.get("S_Financial_Operate"));
                        result.put("S_Financial_Increment", avgScore2.get("S_Financial_Increment"));
                        
                        result.put("F_LandPlan", avgScore2.get("F_LandPlan"));
                        result.put("S_LandPlan_LandUse", avgScore2.get("S_LandPlan_LandUse"));
                        result.put("S_LandPlan_LayOut", avgScore2.get("S_LandPlan_LayOut"));
                        result.put("F_Eco", avgScore2.get("F_Eco"));
                        result.put("S_Eco_Nature", avgScore2.get("S_Eco_Nature"));
                        result.put("S_Eco_Envir", avgScore2.get("S_Eco_Envir"));
                        result.put("F_GreenBulid", avgScore2.get("F_GreenBulid"));
                        result.put("S_GreenBuild_GreenBulid", avgScore2.get("S_GreenBuild_GreenBulid"));
                        result.put("F_Res", avgScore2.get("F_Res"));
                        result.put("S_Res_Energy", avgScore2.get("S_Res_Energy"));
                        result.put("S_Res_Water", avgScore2.get("S_Res_Water"));
                        result.put("S_Res_Res", avgScore2.get("S_Res_Res"));
                        result.put("S_Res_Carbon", avgScore2.get("S_Res_Carbon"));
                        result.put("F_GreenTransport", avgScore2.get("F_GreenTransport"));
                        result.put("S_GreenTransport_Trans", avgScore2.get("S_GreenTransport_Trans"));
                        result.put("S_GreenTransport_Road", avgScore2.get("S_GreenTransport_Road"));
                        result.put("S_GreenTransport_Static", avgScore2.get("S_GreenTransport_Static"));
						result.put("F_InfoManager", avgScore2.get("F_InfoManager"));
						result.put("S_InfoManager_Urban", avgScore2.get("S_InfoManager_Urban"));
						result.put("S_InfoManager_Service", avgScore2.get("S_InfoManager_Service"));
                        result.put("F_Innovation", avgScore2.get("F_Innovation"));
                        result.put("S_Innovation", avgScore2.get("S_Innovation"));
                        result.put("F_Humanity", avgScore2.get("F_Humanity"));
                        result.put("S_Humanity_People", avgScore2.get("S_Humanity_People"));
                        result.put("S_Humanity_GreenLive", avgScore2.get("S_Humanity_GreenLive"));
                        result.put("S_Humanity_GreenEdu", avgScore2.get("S_Humanity_GreenEdu"));
                        result.put("S_Humanity_History", avgScore2.get("S_Humanity_History"));
                        result.put("F_Art", avgScore2.get("F_Art"));
                        result.put("S_Art_Bulid", avgScore2.get("S_Art_Bulid"));
                        result.put("S_Art_Envir", avgScore2.get("S_Art_Envir"));*/


                        if (state == 1)
                            result.put("grade", project2.getfGrade());
                        else if (state == 2)
                            result.put("grade", project2.getlGrade());
                        else
                            result.put("grade", project2.getFinalGrade());
                    } else {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", 0);
                    result.put("errMsg", "1");
                }
                break;
            case 3:
                try {
                    Map<String, Float> avgScore3 = type3Service.queryScoresByProjectIdAndState(projectId, state);
                    Project project3 = projectService.queryProjectById(projectId);
                    if (avgScore3 != null) {

                        result.put("success", 1);
                        result.put("errMsg", null);

                        result.put("F_Social", avgScore3.get("F_Social"));//社会影响
                        result.put("F_Work", avgScore3.get("F_Work"));//行业影响

                        result.put("F_AssetManager", avgScore3.get("F_AssetManager"));//资产运营管理
                        result.put("F_ProjectManager", avgScore3.get("F_ProjectManager"));//项目运营管理

                        result.put("F_Function", avgScore3.get("F_Function"));//项目功能性
                        result.put("F_Tech", avgScore3.get("F_Tech"));//项目技术性
                        result.put("F_Human", avgScore3.get("F_Human"));//人文艺术性
                        result.put("F_Envir", avgScore3.get("F_Envir"));//环境友好性
                        
                        /*result.put("F_Social",avgScore3.get("F_Social"));
                        result.put("S_Social_Develop",avgScore3.get("S_Social_Develop"));
                        result.put("S_Social_People",avgScore3.get("S_Social_People"));
                        result.put("S_Social_Green",avgScore3.get("S_Social_Green"));
                        result.put("F_Work",avgScore3.get("F_Work"));
                        result.put("F_Work_Brand",avgScore3.get("F_Work_Brand"));
                        result.put("F_Work_Develop",avgScore3.get("F_Work_Develop"));
                        
                        result.put("F_AssetManager", avgScore3.get("F_AssetManager"));
                        result.put("S_AssetManager_Early", avgScore3.get("S_AssetManager_Early"));
                        result.put("S_AssetManager_Service", avgScore3.get("S_AssetManager_Service"));
                        result.put("S_AssetManager_MoreAsset", avgScore3.get("S_AssetManager_MoreAsset"));
                        result.put("F_ProjectManager", avgScore3.get("F_ProjectManager"));
                        result.put("S_ProjectManager_Cost", avgScore3.get("S_ProjectManager_Cost"));
                        result.put("S_ProjectManager_Method", avgScore3.get("S_ProjectManager_Method"));
                        
                        result.put("F_Function", avgScore3.get("F_Function"));
                        result.put("S_Function_Use", avgScore3.get("S_Function_Use"));
                        result.put("S_Function_Service", avgScore3.get("S_Function_Service"));
                        result.put("S_Function_Arrivable", avgScore3.get("S_Function_Arrivable"));
                        result.put("S_Function_Open", avgScore3.get("S_Function_Open"));
                        result.put("S_Function_Safe", avgScore3.get("S_Function_Safe"));
                        result.put("F_Tech", avgScore3.get("F_Tech"));
                        result.put("S_Tech_Operate", avgScore3.get("S_Tech_Operate"));
                        result.put("S_Tech_Curing", avgScore3.get("S_Tech_Curing"));
                        result.put("S_Tech_Material", avgScore3.get("S_Tech_Material"));
                        result.put("F_Human", avgScore3.get("F_Human"));
                        result.put("S_Human_Design", avgScore3.get("S_Human_Design"));
                        result.put("S_Human_Explore", avgScore3.get("S_Human_Explore"));
                        result.put("F_Envir", avgScore3.get("F_Envir"));
                        result.put("S_Envir_EnegrySave", avgScore3.get("S_Envir_EnegrySave"));
                        result.put("S_Envir_WaterSave", avgScore3.get("S_Envir_WaterSave"));
                        result.put("S_Envir_Res", avgScore3.get("S_Envir_Res"));
                        result.put("S_Envir_System", avgScore3.get("S_Envir_System"));*/


                        if (state == 1)
                            result.put("grade", project3.getfGrade());
                        else if (state == 2)
                            result.put("grade", project3.getlGrade());
                        else
                            result.put("grade", project3.getFinalGrade());
                    } else {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", 0);
                    result.put("errMsg", "1");
                }
                break;
            case 4:
                try {
                    Map<String, Float> avgScore4 = type4Service.queryScoresByProjectIdAndState(projectId, state);
                    Project project4 = projectService.queryProjectById(projectId);
                    if (avgScore4 != null) {

                        result.put("success", 1);
                        result.put("errMsg", null);

                        result.put("F_Social", avgScore4.get("F_Social"));//社会影响
                        result.put("F_Work", avgScore4.get("F_Work"));//行业影响

                        result.put("F_AssetManagerment", avgScore4.get("F_AssetManagerment"));//资产运营
                        result.put("F_Financial", avgScore4.get("F_Financial"));//财务表现

                        result.put("F_PhysicalEnvir", avgScore4.get("F_PhysicalEnvir"));//室内物理环境营造
                        result.put("F_HumanEnvir", avgScore4.get("F_HumanEnvir"));//室内人文环境营造
                        result.put("F_Decorate", avgScore4.get("F_Decorate"));//室内装饰装修材料
                        result.put("F_Tech1", avgScore4.get("F_Tech"));//室内装饰装修技术与工程施工
                        
                        /*result.put("F_Social",avgScore4.get("F_Social"));
                        result.put("S_Social_Develop",avgScore4.get("S_Social_Develop"));
                        result.put("S_Social_People",avgScore4.get("S_Social_People"));
                        result.put("S_Social_Green",avgScore4.get("S_Social_Green"));
                        result.put("F_Work",avgScore4.get("F_Work"));
                        result.put("S_Work_Brand",avgScore4.get("F_Work_Brand"));
                        result.put("S_Work_Develop",avgScore4.get("F_Work_Develop"));
                        
                        result.put("F_AssetPlan", avgScore4.get("F_AssetPlan"));
                        result.put("S_AssetPlan_Para",avgScore4.get("S_AssetPlan_Para"));
                        result.put("F_AssetManagerment",avgScore4.get("F_AssetManagerment"));
                        result.put("S_AssetManagerment_Early",avgScore4.get("S_AssetManagerment_Early"));
                        result.put("S_Managerment_Rent",avgScore4.get("S_Managerment_Rent"));
                        result.put("S_Managerment_Increment",avgScore4.get("S_Managerment_Increment"));
                        result.put("S_Managerment_Manager",avgScore4.get("S_Managerment_Manager"));
                        result.put("F_Financial",avgScore4.get("F_Financial"));
                        result.put("S_Financial_Managerment",avgScore4.get("S_Financial_Managerment"));
                        result.put("S_Financial_Cash",avgScore4.get("S_Financial_Cash"));
                        result.put("S_Financial_Operate",avgScore4.get("S_Financial_Operate"));
                        result.put("S_Financial_Increment",avgScore4.get("S_Financial_Increment"));  
                        
                        result.put("F_PhysicalEnvir", avgScore4.get("F_PhysicalEnvir"));
                        result.put("S_PhysicalEnvir_Sound", avgScore4.get("S_PhysicalEnvir_Sound"));
                        result.put("S_PhysicalEnvir_Light", avgScore4.get("S_PhysicalEnvir_Light"));
                        result.put("S_PhysicalEnvir_Hot", avgScore4.get("S_PhysicalEnvir_Hot"));
                        result.put("S_PhysicalEnvir_Wind", avgScore4.get("S_PhysicalEnvir_Wind"));
                        result.put("F_HumanEnvir", avgScore4.get("F_HumanEnvir"));
                        result.put("S_HumanEnvir_Function", avgScore4.get("S_HumanEnvir_Function"));
                        result.put("S_HumanEnvir_Beauty", avgScore4.get("S_HumanEnvir_Beauty"));
                        result.put("S_HumanEnvir_Explore", avgScore4.get("S_HumanEnvir_Explore"));
                        result.put("F_Decorate", avgScore4.get("F_Decorate"));
                        result.put("S_DecorateDetails", avgScore4.get("S_DecorateDetails"));
                        result.put("S_DecorateMaterial", avgScore4.get("S_DecorateMaterial"));
                        result.put("F_Tech1", avgScore4.get("F_Tech"));
                        result.put("S_Tech1_Envir", avgScore4.get("S_Tech_Envir"));
                        result.put("S_Tech1_Res", avgScore4.get("S_Tech_Res"));
                        result.put("S_Tech1_Progress", avgScore4.get("S_Tech_Progress"));*/


                        if (state == 1)
                            result.put("grade", project4.getfGrade());
                        else if (state == 2)
                            result.put("grade", project4.getlGrade());
                        else
                            result.put("grade", project4.getFinalGrade());
                    } else {
                        result.put("success", 0);
                        result.put("errMsg", "-1");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", 0);
                    result.put("errMsg", "1");
                }

                break;
        }
        if (finalGrade != null) {
            result.put("evaluation", finalGrade.getEvaluation());
        } else
            result.put("evaluation", null);

        return result;
    }

    @RequestMapping(value = "/setFinalGrade", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> setFinalGrade(HttpServletRequest request) {

        JSONObject parameters;
        Map<String, Object> result = new HashMap<>();
        try {
            parameters = JsonUtil.getRequestJsonObject(request);
            Long expertId = parameters.getLong("expertId");
            Long projectId1 = parameters.getLong("projectId1");
            Long projectId2 = parameters.getLong("projectId2");
            Long projectId3 = parameters.getLong("projectId3");
            Long projectId4 = parameters.getLong("projectId4");
            Long projectId5 = parameters.getLong("projectId5");
            int prizeType = parameters.getIntValue("prizeType");
            int grade = parameters.getIntValue("grade");
            String evaluation = parameters.getString("evaluation");

            switch (grade) {

                case 0:
                    FinalGrade finalGrade0 = finalGradeService.queryFinalGradeByProjectId(expertId, projectId1);
                    if (finalGrade0 == null) {//该专家未曾对该项目评分
                        finalGradeService.setFinalGrade(expertId, projectId1, prizeType, grade, evaluation);

                        result.put("success", 1);//打分成功
                        result.put("errMsg", null);
                        break;
                    } else {//该专家已经对该项目评分，所以是更新
                        finalGradeService.updateFinalGradeEvaluation(expertId, projectId1, evaluation);
                        result.put("success", 2);//更新成功
                        result.put("errMsg", null);
                        break;
                    }
                case 1:
                    if (projectService.queryProjectsByStateAndPrizeType(3, prizeType).size() < 10) {
                        //医养，教育
                        FinalGrade finalGrade = finalGradeService.queryFinalGradeByProjectId(expertId, projectId1);
                        if (finalGrade != null) {
                            FinalGrade finalGrade1 = finalGradeService.queryVotedProject(expertId, prizeType);
                            if (finalGrade1 == null) {//该专家对该类型项目未投票
                                finalGradeService.updateFinalGradeWithoutEvaluation(expertId, projectId1, grade);
                                Project project = projectService.queryProjectById(projectId1);
                                float oldGrade = project.getFinalGrade();
                                project.setFinalGrade(++oldGrade);
                                projectService.updateProject(project);

                                result.put("success", 3);//投票成功
                                result.put("errMsg", null);
                                break;
                            } else {
                                Project oldProject = projectService.queryProjectById(finalGrade1.getProjectId());//之前投票项目
                                finalGradeService.updateFinalGradeWithoutEvaluation(expertId, finalGrade1.getProjectId(), 0);//取消原先投票
                                finalGradeService.updateFinalGradeWithoutEvaluation(expertId, projectId1, grade);//投票新项目
                                float grade1 = oldProject.getFinalGrade();
                                oldProject.setFinalGrade(--grade1);
                                projectService.updateProject(oldProject);
                                Project project = projectService.queryProjectById(projectId1);//当前投票项目
                                float grade2 = project.getFinalGrade();
                                project.setFinalGrade(++grade2);
                                projectService.updateProject(project);//修改成绩

                                result.put("success", 4);//修改投票成功
                                result.put("errMsg", null);
                                break;
                            }
                        } else {
                            result.put("success", "false");//不可投票未评语项目
                            result.put("errMsg", null);
                            break;
                        }
                    } else {//医养，教育之外的类
                        List<Long> newProjectIds = new ArrayList<>();
                        newProjectIds.add(projectId1);
                        newProjectIds.add(projectId2);
                        newProjectIds.add(projectId3);
                        newProjectIds.add(projectId4);
                        newProjectIds.add(projectId5);
                        Boolean flag = true;
                        for (Long newProjectId : newProjectIds) {
                            FinalGrade finalGrade = finalGradeService.queryFinalGradeByProjectId(expertId, newProjectId);
                            if (finalGrade == null) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            List<Long> oldProjectIds = finalGradeService.queryVotedProjectIds(expertId, prizeType);
                            if (oldProjectIds.size() < 5) {
                                for (Long newProjectId : newProjectIds) {
                                    Project newProject = projectService.queryProjectById(newProjectId);
                                    finalGradeService.updateFinalGradeWithoutEvaluation(expertId, newProjectId, 1);//投票
                                    float grade1 = newProject.getFinalGrade();
                                    newProject.setFinalGrade(++grade1);
                                    projectService.updateProject(newProject);
                                }
                                result.put("success", 3);
                                result.put("errMsg", null);
                                break;
                            } else {
                                for (Long oldProjectId : oldProjectIds) {
                                    Project oldProject = projectService.queryProjectById(oldProjectId);//之前投票项目
                                    finalGradeService.updateFinalGradeWithoutEvaluation(expertId, oldProjectId, 0);//取消原先投票
                                    float grade1 = oldProject.getFinalGrade();
                                    oldProject.setFinalGrade(--grade1);
                                    projectService.updateProject(oldProject);
                                }
                                for (Long newProjectId : newProjectIds) {
                                    Project newProject = projectService.queryProjectById(newProjectId);
                                    finalGradeService.updateFinalGradeWithoutEvaluation(expertId, newProjectId, 1);//投票
                                    float grade1 = newProject.getFinalGrade();
                                    newProject.setFinalGrade(++grade1);
                                    projectService.updateProject(newProject);
                                }
                                result.put("success", 4);
                                result.put("errMsg", null);
                                break;
                            }
                        } else {
                            result.put("success", "false");//不可投票未评语项目
                            result.put("errMsg", null);
                            break;
                        }
                    }
                default:
                    result.put("success", 5);
                    result.put("errMsg", "grade error");
            }
        } catch (IOException e) {

            result.put("success", 0);
            result.put("errMsg", "1");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getFProjects", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getFProjects(HttpServletRequest request) {

        JSONObject parameters;
        List<Long> finalGrade = new ArrayList<>();
        ;
        Map<String, Object> result = new HashMap<>();
        try {
            parameters = JsonUtil.getRequestJsonObject(request);
            Long expertId = parameters.getLong("expertId");
            int prizeType = parameters.getIntValue("prizeType");
            int state = parameters.getIntValue("state");
            List<Project> projects = projectService.queryProjectsByStateAndPrizeType(state, prizeType);

            finalGrade = finalGradeService.queryVotedProjectIds(expertId, prizeType);
            if (finalGrade.size() > 0) {//专家已经打分，返回项目列表已经投票的项目ID
                if (finalGrade.size() == 1) {
                    result.put("voteProjectId1", finalGrade.get(0));
                    result.put("projects", projects);
                    result.put("success", 1);
                    result.put("errMsg", null);
                } else {
                    for (int i = 1; i < 6; i++) {
                        result.put("voteProjectId" + i, finalGrade.get(i - 1));
                    }
                    result.put("projects", projects);
                    result.put("success", 3);
                    result.put("errMsg", null);
                }

            } else {//专家未打分，直接返回项目列表
                result.put("projects", projects);
                result.put("success", 2);
                result.put("errMsg", null);
            }
        } catch (IOException e) {
            result.put("success", 0);
            result.put("errMsg", "1");
            e.printStackTrace();
        }
        return result;
    }
}
