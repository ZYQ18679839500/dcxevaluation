package com.whu.service;

import java.util.List;

import com.whu.pojo.FinalGrade;

public interface FinalGradeService {

    void setFinalGrade(Long expertId, Long projectId, int prizeType, int grade, String evaluation);

    FinalGrade queryVotedProject(Long expertId, int prizeType);

    List<Long> queryVotedProjectIds(Long expertId, int prizeType);

    FinalGrade queryFinalGradeByProjectId(Long expertId, Long projectId);

    void updateFinalGrade(Long expertId, Long projectId, int grade, String evaluation);

    void updateFinalGradeWithoutEvaluation(Long expertId, Long projectId, int grade);

    int queryFinalGradesByExpertIdAndPrizeType(Long expertId, int prizeType);

    void updateFinalGradeEvaluation(Long expertId, Long projectId, String evaluation);
}
