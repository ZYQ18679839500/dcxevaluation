package com.whu.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.whu.FcxevaluationApplicationTests;
import com.whu.pojo.FinalGrade;

public class FinalMapperTest extends FcxevaluationApplicationTests {
    @Autowired
    FinalGradeMapper finalGradeMapper;
	
	/*@Test
	public void setFinalGrade() {
		finalGradeMapper.setFinalGrade(1L, 3L, 3, 1,"不错");
	}*/
		
	/*@Test
	public void queryFinalGrade() {
		FinalGrade finalGrade= finalGradeMapper.queryFinalGradeByProjectId(1L, 3L);
		log.info("ProjectMapperTest.queryProjectsByProjectIds: " + finalGrade.toString());
	}*/
	
	/*@Test
	public void queryFinalGrade() {
		FinalGrade finalGrade= finalGradeMapper.queryVotedProject(20L, 22);
		log.info("ProjectMapperTest.queryProjectsByProjectIds: " + finalGrade.toString());
	}*/
	
	/*@Test
	public void updateFinalGrade() {
		finalGradeMapper.updateFinalGrade(1L, 3L, 0, "很好");
	}*/
	
	/*@Test
	public void updateFinalGrade() {
		finalGradeMapper.updateFinalGradeWithoutEvaluation(38L, 1101L, 0);
	}*/
	
	/*@Test
	public void updateFinalGrade() {
		log.info(finalGradeMapper.queryFinalGradesByExpertIdAndPrizeType(38L, 9));
	}*/

    @Test
    public void queryFinalGrade() {
        List<Long> finalGrade = finalGradeMapper.queryVotedProjects(20L, 20);
        log.info("ProjectMapperTest.queryProjectsByProjectIds: " + finalGrade.get(0));
    }
}
