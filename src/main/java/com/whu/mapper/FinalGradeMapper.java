package com.whu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whu.pojo.FinalGrade;

@Mapper
public interface FinalGradeMapper {

    @Options(useGeneratedKeys = false, keyProperty = "id")

    @Insert("INSERT INTO t_finalgrade(expert_id,project_id,prize_type,grade,evaluation) " +
            "VALUES(#{expert_id},#{project_id},#{prize_type},#{grade},#{evaluation})")
    int setFinalGrade(@Param("expert_id") Long expert_id,
                      @Param("project_id") Long project_id,
                      @Param("prize_type") int prizeType,
                      @Param("grade") int grade,
                      @Param("evaluation") String evaluation);

    @Results({
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "prize_type", property = "prizeType"),
            @Result(column = "grade", property = "grade"),
            @Result(column = "evaluation", property = "evaluation"),
    })
    @Select("SELECT * FROM t_finalgrade WHERE expert_id = #{expert_id} AND prize_type=#{prize_type} AND grade=1")
    FinalGrade queryVotedProject(@Param("expert_id") Long expertId,
                                 @Param("prize_type") int prizeType);

    @Select("SELECT project_id FROM t_finalgrade WHERE expert_id = #{expert_id} AND prize_type=#{prize_type} AND grade=1")
    List<Long> queryVotedProjects(@Param("expert_id") Long expertId,
                                  @Param("prize_type") int prizeType);

    @Results({
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "prize_type", property = "prizeType"),
            @Result(column = "grade", property = "grade"),
            @Result(column = "evaluation", property = "evaluation"),
    })
    @Select("SELECT * FROM t_finalgrade WHERE expert_id = #{expert_id} AND project_id=#{project_id}")
    FinalGrade queryFinalGradeByProjectId(@Param("expert_id") Long expertId,
                                          @Param("project_id") Long projectId);

    @Select("SELECT COUNT(*) FROM t_finalgrade WHERE expert_id = #{expert_id} AND prize_type=#{prize_type}")
    int queryFinalGradesByExpertIdAndPrizeType(@Param("expert_id") Long expertId,
                                               @Param("prize_type") int prizeType);

    @Update("UPDATE t_finalgrade SET " +
            "grade = #{grade}," +
            " evaluation=#{evaluation}" +
            " WHERE expert_id = #{expert_id} AND project_id=#{project_id}")
    void updateFinalGrade(@Param("expert_id") Long expertId,
                          @Param("project_id") Long projectId,
                          @Param("grade") int grade,
                          @Param("evaluation") String evaluation);

    @Update("UPDATE t_finalgrade SET " +
            "grade = #{grade}" +
            " WHERE expert_id = #{expert_id} AND project_id=#{project_id}")
    void updateFinalGradeWithoutEvaluation(@Param("expert_id") Long expertId,
                                           @Param("project_id") Long projectId,
                                           @Param("grade") int grade);

    @Update("UPDATE t_finalgrade SET " +
            "evaluation = #{evaluation}" +
            " WHERE expert_id = #{expert_id} AND project_id=#{project_id}")
    void updateFinalGradeEvaluation(@Param("expert_id") Long expertId,
                                    @Param("project_id") Long projectId,
                                    @Param("evaluation") String evaluation);
}

