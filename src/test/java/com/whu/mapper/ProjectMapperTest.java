package com.whu.mapper;

import com.alibaba.fastjson.JSONObject;
import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.Expert;
import com.whu.pojo.ExpertType;
import com.whu.pojo.Prize;
import com.whu.pojo.Project;
import com.whu.service.AdminService;
import com.whu.service.ExpertService;
import com.whu.service.ProjectAssignmentService;
import com.whu.service.ProjectService;
import com.whu.service.Type1Service;
import com.whu.service.Type2Service;
import com.whu.service.Type3Service;
import com.whu.service.Type4Service;
import com.whu.utils.JsonUtil;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ProjectMapperTest extends FcxevaluationApplicationTests {
    @Autowired
    PrizeMapper prizeMapper;

    @Autowired
    ExpertMapper expertMapper;


    @Autowired
    AdminService adminService;

    @Autowired
    ExpertService expertService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectAssignmentService projectAssignmentService;

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

    @Autowired
    ProjectAssignmentMapper projectAssignmentMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    Type1Service type1Service;

    @Autowired
    Type2Service type2Service;

    @Autowired
    Type3Service type3Service;

    @Autowired
    Type4Service type4Service;
    
    /*@Test
    public void queryProjectById()
    {
        Project project = projectMapper.queryProjectById(1L);
        log.info("ProjectMapperTest.queryProjectById: " + project.toString());
    }



    @Test
    public void updateProject()
    {
        Project project = projectMapper.queryProjectById(1L);
        project.setAssessmentState(20);
        int code = projectMapper.updateProject(project);
        log.info("ProjectMapperTest.updateProject: " + code);
    }

    @Test
    public void queryProjectsByProjectIds()
    {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        List<Project> projects = projectMapper.queryProjectsByProjectIds(list, 45L,1);
        log.info("ProjectMapperTest.queryProjectsByProjectIds: " + projects);
    }

    @Test
    public void queryAllProjects()
    {
        List<Project> projects = projectMapper.queryAllProjects();
        log.info("ProjectMapperTest.queryAllProjects: " + projects);
    }

    @Test
    public void queryProjectsByState()
    {
        List<Project> projects = projectMapper.queryProjectsByState(1);
        log.info("ProjectMapperTest.queryProjectsByState: " + projects);
    }

    @Test
    public void queryUnassignedProjectsF()
    {
        List<Project> projects = projectMapper.queryUnassignedProjectsF();
        log.info("ProjectMapperTest.queryUnassignedProjectsF: " + projects);
    }

    @Test
    public void queryUnassignedProjectsL()
    {
        List<Project> projects = projectMapper.queryUnassignedProjectsL();
        log.info("ProjectMapperTest.queryUnassignedProjectsL: " + projects);
    }*/

   /* @Test
    public void fileTest()
    {
        File xml = new File("D:\\Code software\\Code\\Spring\\dcxevaluation\\src\\test\\java\\com\\whu\\mapper\\project.xlsx");
        if(!xml.exists())
            log.info("ProjectMapperTest.fileTest: " + "file not exists");
        else
        {
            try
            {
                Workbook workbook = new XSSFWorkbook(xml);
                Sheet sheet = workbook.getSheet("Sheet1");
                int firstRowIndex = sheet.getFirstRowNum() + 2;
                int lastRowIndex = sheet.getLastRowNum();
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                for(int i = firstRowIndex; i<= lastRowIndex ;i++)
                {
                    Project project = new Project();
                    Prize prize = new Prize();
                    Row row = sheet.getRow(i);
                    Cell id = row.getCell(0);
                    Cell fTypeString = row.getCell(2);
                    int fType = 0;
                    switch (fTypeString.toString())
                    {
                        case "城更产城类":
                            fType = 1;
                            break;
                        case "教育类":
                            fType = 2;
                            break;
                        case "居住类":
                            fType = 3;
                            break;
                        case "旅游类":
                            fType = 4;
                            break;
                        case "商办类":
                            fType = 5;
                            break;
                        case "文化类":
                            fType = 6;
                            break;
                        case "医养类":
                            fType = 7;

                    }
                    Cell sTypeString = row.getCell(1);
                    int sType = 0;
                    switch (sTypeString.toString())
                    {
                        case "建筑":
                            sType = 1;
                            break;
                        case "规划":
                            sType = 2;
                            break;
                        case "景观":
                            sType = 3;
                            break;
                        case "室内":
                            sType = 4;
                            break;
                    }
                    Cell tTypeString = row.getCell(3);
                    int tType = 0;
                    switch (tTypeString.toString())
                    {
                        case "建成":
                            tType = 1;
                            break;
                        case "在建":
                            tType = 2;
                            break;
                        case "未来设计/方案":
                            tType = 3;
                            break;
                    }
                    Long prizeId = prizeMapper.queryPrizeIdByType(fType,sType,tType);
                    prize.setfType(fType);prize.setsType(sType);prize.settType(tType);prize.setId(prizeId);
                    Cell total=row.getCell(4);
                    Cell name = row.getCell(5);
                    Cell time = row.getCell(6);
                    Cell index=row.getCell(7);
                    Cell other=row.getCell(10);
                    Cell addition=row.getCell(9);
                    Long otherId=(Long.valueOf(other.toString().split("\\.")[0]));
                    project.setOtherId(otherId);
                    ts = Timestamp.valueOf(time.toString());
                    Date date = new Date(ts.getTime());
                    project.setId(Long.valueOf(id.toString().split("\\.")[0]));                    
                    project.setPrize(prize);
                    project.setName(name.toString());
                    Long index_number=(long) (100*(Double.valueOf(index.toString().split("\\.")[0])/Double.valueOf(total.toString().split("\\.")[0])));
                    Long addition_number=(long) (100*(Double.valueOf(addition.toString().split("\\.")[0])/Double.valueOf(total.toString().split("\\.")[0])));
                    project.setIndexNumber(index_number);
                    project.setAdditionNumber(addition_number);
                    //Date date = new Date(DateUtil.getJavaDate(Double.valueOf(time.toString())).getTime());
                    project.setApplicationTime(date);
                    project.setAssessmentState(1);
                    project.setPrizeClass(5);
                    projectMapper.insertProject(project,prizeId);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }*/
    
    
    /*@Test
    public void fileTest()
    {
        File xml = new File("D:\\Code software\\Code\\Spring\\dcxevaluation\\src\\test\\java\\com\\whu\\mapper\\expert6.xlsx");
        if(!xml.exists())
            log.info("ProjectMapperTest.fileTest: " + "file not exists");
        else
        {
            try
            {
                Workbook workbook = new XSSFWorkbook(xml);
                Sheet sheet = workbook.getSheet("Sheet1");
                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();
                for(int i = firstRowIndex; i<= lastRowIndex ;i++)
                {
                    Row row = sheet.getRow(i);
                    Cell name = row.getCell(0);
                    Expert expert=expertMapper.queryExpertByName(name.toString());
                    Long expertId=expert.getId();
                    int fType=7;
                    int sType=4;
                    for(int n=1;n<5;n++) {
                    	for(int m=1;m<4;m++) {
                            Long prizeId = prizeMapper.queryPrizeIdByType(fType,n,m);
                            expertTypeMapper.insertExpertType(expertId, prizeId, 1);
                    	}
                    }

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
   }*/
      
    /*@Test
    public void fileTest()
    {
        File xml = new File("D:\\Code software\\Code\\Spring\\dcxevaluation\\src\\test\\java\\com\\whu\\mapper\\finalproject.xlsx");
        if(!xml.exists())
            log.info("ProjectMapperTest.fileTest: " + "file not exists");
        else
        {
            try
            {
            	Workbook workbook = new XSSFWorkbook(xml);
            	for(int n=1;n<23;n++) {
            		String sheetName="Sheet"+n;
                    Sheet sheet = workbook.getSheet(sheetName);
                    int firstRowIndex = sheet.getFirstRowNum();
                    int lastRowIndex = sheet.getLastRowNum();
                    for(int i = firstRowIndex+1; i<= lastRowIndex ;i++)
                    {
                        Row row = sheet.getRow(i);
                        Cell name = row.getCell(0);
                        if(name!=null) {
                            Project project=projectMapper.queryProjectByName(name.toString());
                            	project.setAssessmentState(3);
                                project.setPrizeType(n);
                                projectMapper.updateProject(project);
                        }else
                        	continue;
                    }
            	}

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
   }*/

    /* @Test
     public void fileTest()
     {
         File xml = new File("D:\\Code software\\Code\\Spring\\dcxevaluation\\src\\test\\java\\com\\whu\\mapper\\lexpert.xlsx");
         if(!xml.exists())
             log.info("ProjectMapperTest.fileTest: " + "file not exists");
         else
         {
             try
             {
                 Workbook workbook = new XSSFWorkbook(xml);
                 for(Long n=1L;n<4;n++) {
                     String sheetName="Sheet"+n;
                     Sheet sheet = workbook.getSheet(sheetName);
                     int firstRowIndex = sheet.getFirstRowNum();
                     int lastRowIndex = sheet.getLastRowNum();
                     for(int i = firstRowIndex; i<= lastRowIndex ;i++)
                     {
                         Row row = sheet.getRow(i);
                         Cell name = row.getCell(0);
                         Expert expert=expertMapper.queryExpertByName(name.toString());
                         expert.setType(n);
                         expertMapper.updateExpert(expert);
                     }
                 }
 
             }
             catch (Exception e)
             {
                 e.printStackTrace();
             }
         }
    }*/
    @Test
    public void fileTest() throws FileNotFoundException {
        FileInputStream gradeFileInputStream = new FileInputStream("D:\\Code software\\Code\\Spring\\dcxevaluation\\src\\test\\java\\com\\whu\\mapper\\grade.xlsx");
        FileInputStream evaluationFileInputStream = new FileInputStream("D:\\Code software\\Code\\Spring\\dcxevaluation\\src\\test\\java\\com\\whu\\mapper\\evaluation.xlsx");
        if (false)
            log.info("ProjectMapperTest.fileTest: " + "file not exists");
        else {

            try {
                Workbook workbook1 = new XSSFWorkbook(gradeFileInputStream);
                Workbook workbook2 = new XSSFWorkbook(evaluationFileInputStream);
                gradeFileInputStream.close();
                evaluationFileInputStream.close();

                Sheet sheetGrade = workbook1.getSheet("Sheet1");
                int firstRowIndex1 = sheetGrade.getFirstRowNum();
                int lastRowIndex1 = sheetGrade.getLastRowNum();

                Sheet sheetEvaluation = workbook2.getSheet("Sheet1");
                int firstRowIndex2 = sheetEvaluation.getFirstRowNum();
                int lastRowIndex2 = sheetEvaluation.getLastRowNum();

                //log.info("ProjectMapperTest.updateProject: " + lastRowIndex1);

                for (int i = firstRowIndex2; i <= lastRowIndex2; i++) {
                    Row row2 = sheetEvaluation.getRow(i);
                    Cell projectId2 = row2.getCell(1);
                    Cell evaluation = row2.getCell(2);
                    if (evaluation == null) {
                        log.info("ProjectMapperTest.updateProject: " + i);
                    }
                    for (int j = firstRowIndex1; j <= lastRowIndex1; j++) {
                        Row row1 = sheetGrade.getRow(j);
                        Cell projectId1 = row1.getCell(0);
                        if (projectId1.toString().equals(projectId2.toString())) {

                            for (int k = 0; k < 16; k++) {
                                Cell text = row1.getCell(k);

                                if (text.toString().trim().equals("a")) {
                                    text.setCellValue(evaluation.toString());
                                    break;
                                }
                            }
                        }
                    }
                }
                FileOutputStream gradeFileOutPutStream = new FileOutputStream("D:\\Code software\\Code\\Spring\\dcxevaluation\\src\\test\\java\\com\\whu\\mapper\\grade2.xlsx");//写数据到这个路径上
                workbook1.write(gradeFileOutPutStream);
                gradeFileOutPutStream.flush();
                gradeFileOutPutStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /*@Test
    public void updateGrade() {
    	
			
	        Float envir_benefit1 = 45.4f;
	        Float econo_benefit1 = 27f;
	        Float social_benefit1 = 27.6f;
	        
	        Float envir_benefit2 = 45.05f;
	        Float econo_benefit2 = 25.37f;
	        Float social_benefit2 = 29.58f;
	        
	        Float envir_benefit3 = 45.52f;
	        Float econo_benefit3 = 22.91f;
	        Float social_benefit3 = 31.57f;
	        
	        Float envir_benefit4 = 40.94f;
	        Float econo_benefit4 = 27.88f;
	        Float social_benefit4 = 31.18f;
	        	        	     	        
	        for(Long i=0L;i<1160;i++) {
	        	Project project=projectService.queryProjectById(i);
	        	if(project==null) {
	        		
	        	}else {
	        		Prize prize=project.getPrize();
	        		int sType=prize.getsType();//1 建筑 2规划  3 景观 4 室内
	        		int tType=prize.gettType();
	        		if(sType==1) {//建筑类型项目
	        			if(tType==1) {
	        				//只有建成类项目才需要修改分数
	        				Map<String, Float> avgScore1 = type1Service.queryScoresByProjectIdAndState(i, 1);
	        				
	        				
	        				//27.61
	                        Float F_AssetPlan= avgScore1.get("F_AssetPlan");//0.053
	                        Float F_AssetManagerment=avgScore1.get("F_AssetManagerment");//0.589
	                        Float F_Financial=avgScore1.get("F_Financial");//0.358                   
	                        
	                        //27.86
	                        Float F_Social=avgScore1.get("F_Social");//0.5
	                        Float F_Work=avgScore1.get("F_Work");//0.5
	                        
	                        //44.53
	                        Float F_SaveLands=avgScore1.get("F_SaveLands");//0.11
	                        Float F_SaveEnergy=avgScore1.get("F_SaveEnergy");//0.11
	                        Float F_SaveWater=avgScore1.get("F_SaveWater");//0.11
	                        Float F_SaveRes=avgScore1.get("F_SaveRes");//0.11
	                        Float F_Indoor=avgScore1.get("F_Indoor");//0.11
	                        Float F_Construction=avgScore1.get("F_Construction");//0.11
	                        Float F_Operation=avgScore1.get("F_Operation");//0.11
	                        Float F_Innovation=avgScore1.get("F_Innovation");//0.018
	                        Float F_Humanity=avgScore1.get("F_Humanity");//0.102
	                        Float F_Art=avgScore1.get("F_Art");//0.11
	                        
	                        Float impression=avgScore1.get("impression");
	                        
	                        Float envirBenefitGrade1=((float)(Math.round((F_SaveLands*0.11f+F_SaveEnergy*0.11f+F_SaveWater*0.11f+F_SaveRes*0.11f+F_Indoor*0.11f+F_Construction*0.11f+F_Operation*0.11f+F_Innovation*0.018f+F_Humanity*0.102f+F_Art*0.11f)*envir_benefit1/5.0f*100))/100);
	                        Float socialBenefitGrade1=((float)(Math.round((F_Social*0.5f+F_Work*0.5f)*social_benefit1/5.0f*100))/100);
	                        Float econoBenefitGrade1=((float)(Math.round((F_AssetPlan*0.053f+F_AssetManagerment*0.589f+F_Financial*0.358f)*econo_benefit1/5.0f*100))/100);	      
	                        
	                        Float trueGrade=(envirBenefitGrade1+socialBenefitGrade1+econoBenefitGrade1)*0.8f+impression*0.2f;
	                        
	                        project.setfGrade(trueGrade);
	                        project.setImpression(impression);
	                        project.setlGrade(envirBenefitGrade1+socialBenefitGrade1+econoBenefitGrade1);
	                        projectMapper.updateProject(project);
	                        	                        	                 
	        			}else {
	        				Map<String, Float> avgScore1 = type1Service.queryScoresByProjectIdAndState(i, 1);
	        				                        
	                        Float impression=avgScore1.get("impression");
	                        	                        
	                        Float trueGrade=project.getfGrade()*0.8f+impression*0.2f;
	                        project.setlGrade(project.getfGrade());
	                        project.setfGrade(trueGrade);	
	                        project.setImpression(impression);
	                        projectMapper.updateProject(project);
	                        
	        			}	        				        			
	        		}else if(sType==2) {
	        			if(tType==1) {
	        				//只有建成类项目才需要修改分数
	        				Map<String, Float> avgScore2 = type2Service.queryScoresByProjectIdAndState(i, 1);
	        				
	        				//29.84
	        				Float F_Social=avgScore2.get("F_Social");//0.5
	                        Float F_Work=avgScore2.get("F_Work");//0.5
	                        
	                        //25.91
	                        Float F_AssetPlan= avgScore2.get("F_AssetPlan");//0.053
	                        Float F_Industry= avgScore2.get("F_Industry");//0.589
	                        Float F_Financial= avgScore2.get("F_Financial");//0.358
	                        
	                        //44.25
	                        Float F_LandPlan= avgScore2.get("F_LandPlan");//0.127
	                        Float F_Eco= avgScore2.get("F_Eco");//0.127
	                        Float F_GreenBulid= avgScore2.get("F_GreenBulid");//0.127
	                        Float F_Res= avgScore2.get("F_Res");//0.131
	                        Float F_GreenTransport= avgScore2.get("F_GreenTransport");//0.108
	                        Float F_InfoManager=avgScore2.get("F_InfoManager");//0.127
	                        Float F_Innovation= avgScore2.get("F_Innovation");//0.009
	                        Float F_Humanity= avgScore2.get("F_Humanity");//0.118
	                        Float F_Art= avgScore2.get("F_Art");//0.126
	                        
	                        Float impression=avgScore2.get("impression");
	                        
	                        Float envirBenefitGrade2=((float)(Math.round((F_LandPlan*0.127f+F_Eco*0.127f+F_GreenBulid*0.127f+F_Res*0.131f+F_GreenTransport*0.108f+F_InfoManager*0.127f+F_Innovation*0.009f+F_Humanity*0.118f+F_Art*0.126f)*envir_benefit2/5.0f*100))/100);
	                        Float socialBenefitGrade2=((float)(Math.round((F_Social*0.5f+F_Work*0.5f)*social_benefit2/5.0f*100))/100);
	                        Float econoBenefitGrade2=((float)(Math.round((F_AssetPlan*0.053+F_Industry*0.589+F_Financial*0.358)*econo_benefit2/5.0f*100))/100);	                        
	                        Float trueGrade=(envirBenefitGrade2+socialBenefitGrade2+econoBenefitGrade2)*0.8f+impression*0.2f;
	                        	                       	                        
	                        project.setfGrade(trueGrade);
	                        project.setImpression(impression);
	                        project.setlGrade(envirBenefitGrade2+socialBenefitGrade2+econoBenefitGrade2);
	                        projectMapper.updateProject(project);
	                        	                        
	        			}else {
	        				Map<String, Float> avgScore2 = type2Service.queryScoresByProjectIdAndState(i, 1);
	        				                        
	                        Float impression=avgScore2.get("impression");
	                        	                        
	                        Float trueGrade=project.getfGrade()*0.8f+impression*0.2f;
	                        project.setlGrade(project.getfGrade());
	                        project.setfGrade(trueGrade);	     
	                        project.setImpression(impression);
	                        projectMapper.updateProject(project);
	                        
	        			}
	        		}else if(sType==3) {
	        			if(tType==1) {
	        				//只有建成类项目才需要修改分数
	        				Map<String, Float> avgScore3 = type3Service.queryScoresByProjectIdAndState(i, 1);
	        				
	        				//31.58
	        				Float F_Social=avgScore3.get("F_Social");//0.5
	                        Float F_Work=avgScore3.get("F_Work");//0.5
	                        
	                        //23.37
	                        Float F_AssetManager= avgScore3.get("F_AssetManager");//0.6
	                        Float F_ProjectManager= avgScore3.get("F_ProjectManager");//0.4
	                        
	                        //45.05
	                        Float F_Function= avgScore3.get("F_Function");///0.207
	                        Float F_Tech= avgScore3.get("F_Tech");//0.183
	                        Float F_Human= avgScore3.get("F_Human");//0.244
	                        Float F_Envir= avgScore3.get("F_Envir");//0.366
	                        
	                        Float impression=avgScore3.get("impression");
	                        
	                        Float envirBenefitGrade3=((float)(Math.round((F_Envir*0.207+F_Human*0.244+F_Tech*0.183+F_Function*0.207)*envir_benefit3/5.0f*100))/100);
	                        Float socialBenefitGrade3=((float)(Math.round((F_Social*0.5+F_Work*0.5)*social_benefit3/5.0f*100))/100);
	                        Float econoBenefitGrade3=((float)(Math.round((F_ProjectManager*0.4+F_AssetManager*0.6)*econo_benefit3/5.0f*100))/100);	                        
	                        Float trueGrade=(envirBenefitGrade3+socialBenefitGrade3+econoBenefitGrade3)*0.8f+impression*0.2f;
	                        
	                        project.setfGrade(trueGrade);
	                        project.setlGrade(envirBenefitGrade3+socialBenefitGrade3+econoBenefitGrade3);
	                        project.setImpression(impression);
	                        projectMapper.updateProject(project);
	        			}else {
	        				Map<String, Float> avgScore3 = type3Service.queryScoresByProjectIdAndState(i, 1);
	        				                        
	                        Float impression=avgScore3.get("impression");
	                        	                        
	                        Float trueGrade=project.getfGrade()*0.8f+impression*0.2f;
	                        project.setlGrade(project.getfGrade());
	                        project.setImpression(impression);
	                        project.setfGrade(trueGrade);	                        	                        
	                        projectMapper.updateProject(project);
	                        
	        			}
	        		}else {
	        			if(tType==1) {
	        				//只有建成类项目才需要修改分数
	        				Map<String, Float> avgScore4 = type4Service.queryScoresByProjectIdAndState(i, 1);
	        				
	        				//31.12
	        				Float F_Social=avgScore4.get("F_Social");//0.5
	                        Float F_Work=avgScore4.get("F_Work");//0.5
	                        
	                        //28.41
	                        //Float F_AssetPlan= avgScore4.get("F_AssetPlan");
	                        Float F_AssetManagerment=avgScore4.get("F_AssetManagerment");//0.622
	                        Float F_Financial=avgScore4.get("F_Financial");//0.378
	                        
	                        //40.47
	                        Float F_PhysicalEnvir= avgScore4.get("F_PhysicalEnvir");//0.27
	                        Float F_HumanEnvir= avgScore4.get("F_HumanEnvir");//0.285
	                        Float F_Decorate= avgScore4.get("F_Decorate");//0.26
	                        Float F_Tech= avgScore4.get("F_Tech");              //0.285        

	                        Float impression=avgScore4.get("impression");
	                        
	                        Float envirBenefitGrade4=((float)(Math.round((F_Tech*0.285+F_Decorate*0.26+F_HumanEnvir*0.285+F_PhysicalEnvir*0.27)*envir_benefit4/5.0f*100)/100));
	                        Float socialBenefitGrade4=((float)(Math.round((F_Social*0.5+F_Work*0.5)*social_benefit4/5.0f*100)/100));
	                        Float econoBenefitGrade4=((float)(Math.round((F_AssetManagerment*0.622+F_Financial*0.378)*econo_benefit4/5.0f*100)/100));	                        
	                        Float trueGrade=(envirBenefitGrade4+socialBenefitGrade4+econoBenefitGrade4)*0.8f+impression*0.2f;
	                        
	                        //System.out.println("envir:"+envirBenefitGrade4+" social:"+socialBenefitGrade4+" econo:"+econoBenefitGrade4+" impresss:"+impression +" grade:"+trueGrade);
	                        project.setlGrade(envirBenefitGrade4+socialBenefitGrade4+econoBenefitGrade4);
	                        project.setImpression(impression);
	                        project.setfGrade(trueGrade);
	                        projectMapper.updateProject(project);
	                        
	        			}else {
	        				Map<String, Float> avgScore4 = type4Service.queryScoresByProjectIdAndState(i, 1);
	        				                        
	                        Float impression=avgScore4.get("impression");
	                        	                        
	                        Float trueGrade=project.getfGrade()*0.8f+impression*0.2f;
	                        project.setImpression(impression);
	                        project.setlGrade(project.getfGrade());
	                        project.setfGrade(trueGrade);	                        	                        
	                        projectMapper.updateProject(project);
	                        
	        			}
	        		}
	        		
	        	}
	        		
	        }        
    }*/
}
