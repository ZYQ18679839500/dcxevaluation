package com.whu.mapper;

import com.whu.pojo.EnvirBenefit4;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EnvirBenefit4Mapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    /**
     * 根据项目的id和项目的评分进度返回项目评分情况
     * @param projectId 项目id
     * @param state 初评或者会评
     * @param expertId 专家id
     * @return 返回的评分情况
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "state", property = "state"),
            @Result(column = "F_PhysicalEnvir", property = "F_PhysicalEnvir"),
            @Result(column = "S_PhysicalEnvir_Sound", property = "S_PhysicalEnvir_Sound"),
            @Result(column = "S_PhysicalEnvir_Light", property = "S_PhysicalEnvir_Light"),
            @Result(column = "S_PhysicalEnvir_Hot", property = "S_PhysicalEnvir_Hot"),
            @Result(column = "S_PhysicalEnvir_Wind", property = "S_PhysicalEnvir_Wind"),
            @Result(column = "F_HumanEnvir", property = "F_HumanEnvir"),
            @Result(column = "S_HumanEnvir_Function", property = "S_HumanEnvir_Function"),
            @Result(column = "S_HumanEnvir_Beauty", property = "S_HumanEnvir_Beauty"),
            @Result(column = "S_HumanEnvir_Explore", property = "S_HumanEnvir_Explore"),
            @Result(column = "F_Decorate", property = "F_Decorate"),
            @Result(column = "S_DecorateDetails", property = "S_DecorateDetails"),
            @Result(column = "S_DecorateMaterial", property = "S_DecorateMaterial"),
            @Result(column = "F_Tech", property = "F_Tech"),
            @Result(column = "S_Tech_Envir", property = "S_Tech_Envir"),
            @Result(column = "S_Tech_Res", property = "S_Tech_Res"),
            @Result(column = "S_Tech_Progress", property = "S_Tech_Progress")
    })
    @Select("SELECT * FROM t_envir_benefit_4 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "AND expert_id = #{expertId}")
    EnvirBenefit4 queryScoreByProjectIdAndState(@Param("projectId") Long projectId,
                                                @Param("state") int state,
                                                @Param("expertId") Long expertId);

    /**
     * 根据项目的id和项目的评分进度返回项目评分情况
     *
     * @param projectId 项目id
     * @param state     初评或者会评
     * @return 返回的评分情况
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "state", property = "state"),
            @Result(column = "F_PhysicalEnvir", property = "F_PhysicalEnvir"),
            @Result(column = "S_PhysicalEnvir_Sound", property = "S_PhysicalEnvir_Sound"),
            @Result(column = "S_PhysicalEnvir_Light", property = "S_PhysicalEnvir_Light"),
            @Result(column = "S_PhysicalEnvir_Hot", property = "S_PhysicalEnvir_Hot"),
            @Result(column = "S_PhysicalEnvir_Wind", property = "S_PhysicalEnvir_Wind"),
            @Result(column = "F_HumanEnvir", property = "F_HumanEnvir"),
            @Result(column = "S_HumanEnvir_Function", property = "S_HumanEnvir_Function"),
            @Result(column = "S_HumanEnvir_Beauty", property = "S_HumanEnvir_Beauty"),
            @Result(column = "S_HumanEnvir_Explore", property = "S_HumanEnvir_Explore"),
            @Result(column = "F_Decorate", property = "F_Decorate"),
            @Result(column = "S_DecorateDetails", property = "S_DecorateDetails"),
            @Result(column = "S_DecorateMaterial", property = "S_DecorateMaterial"),
            @Result(column = "F_Tech", property = "F_Tech"),
            @Result(column = "S_Tech_Envir", property = "S_Tech_Envir"),
            @Result(column = "S_Tech_Res", property = "S_Tech_Res"),
            @Result(column = "S_Tech_Progress", property = "S_Tech_Progress")
    })
    @Select("SELECT * FROM t_envir_benefit_4 WHERE project_id = #{projectId} " +
            "AND state = #{state}")
    List<EnvirBenefit4> queryScoresByProjectIdAndState(@Param("projectId") Long projectId,
                                                       @Param("state") int state);

    /**
     * 根据项目的id和项目的评分进度返回项目评分情况
     *
     * @param projectId 项目id
     * @param state     初评或者会评
     * @return 返回的评分情况
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "state", property = "state"),
            @Result(column = "F_PhysicalEnvir", property = "F_PhysicalEnvir"),
            @Result(column = "S_PhysicalEnvir_Sound", property = "S_PhysicalEnvir_Sound"),
            @Result(column = "S_PhysicalEnvir_Light", property = "S_PhysicalEnvir_Light"),
            @Result(column = "S_PhysicalEnvir_Hot", property = "S_PhysicalEnvir_Hot"),
            @Result(column = "S_PhysicalEnvir_Wind", property = "S_PhysicalEnvir_Wind"),
            @Result(column = "F_HumanEnvir", property = "F_HumanEnvir"),
            @Result(column = "S_HumanEnvir_Function", property = "S_HumanEnvir_Function"),
            @Result(column = "S_HumanEnvir_Beauty", property = "S_HumanEnvir_Beauty"),
            @Result(column = "S_HumanEnvir_Explore", property = "S_HumanEnvir_Explore"),
            @Result(column = "F_Decorate", property = "F_Decorate"),
            @Result(column = "S_DecorateDetails", property = "S_DecorateDetails"),
            @Result(column = "S_DecorateMaterial", property = "S_DecorateMaterial"),
            @Result(column = "F_Tech", property = "F_Tech"),
            @Result(column = "S_Tech_Envir", property = "S_Tech_Envir"),
            @Result(column = "S_Tech_Res", property = "S_Tech_Res"),
            @Result(column = "S_Tech_Progress", property = "S_Tech_Progress")
    })
    @Select("SELECT * FROM t_envir_benefit_4 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "ORDER BY expert_id")
    List<EnvirBenefit4> queryScoresByProjectIdAndStateOrderByExpertId(@Param("projectId") Long projectId,
                                                                      @Param("state") int state);

    /**
     * 插入项目分数
     *
     * @param envirBenefit4 插入的项目分数信息
     * @return 事务操作返回值
     */
    @Insert("INSERT INTO t_envir_benefit_4(project_id, expert_id, F_PhysicalEnvir, S_PhysicalEnvir_Sound, " +
            "S_PhysicalEnvir_Light, S_PhysicalEnvir_Hot,S_PhysicalEnvir_Wind,F_HumanEnvir,S_HumanEnvir_Function,S_HumanEnvir_Beauty,S_HumanEnvir_Explore,F_Decorate,S_DecorateDetails,S_DecorateMaterial,F_Tech,S_Tech_Envir,S_Tech_Res,S_Tech_Progress, state) " +
            "VALUES(#{projectId}, #{expertId}, #{F_PhysicalEnvir}, #{S_PhysicalEnvir_Sound}, " +
            "#{S_PhysicalEnvir_Light}, #{S_PhysicalEnvir_Hot},#{S_PhysicalEnvir_Wind},#{F_HumanEnvir},#{S_HumanEnvir_Function},#{S_HumanEnvir_Beauty},#{S_HumanEnvir_Explore},#{F_Decorate},#{S_DecorateDetails},#{S_DecorateMaterial},#{F_Tech},#{S_Tech_Envir},#{S_Tech_Res},#{S_Tech_Progress},#{state})")
    int insertScore(EnvirBenefit4 envirBenefit4);

    /**
     * 更新分数，只用于终评
     *
     * @param envirBenefit4 更新的分数
     * @return
     */
    @Update("UPDATE t_envir_benefit_4 SET " +
            "F_PhysicalEnvir = #{F_PhysicalEnvir}, " +
            "S_PhysicalEnvir_Sound = #{S_PhysicalEnvir_Sound}, " +
            "S_PhysicalEnvir_Light = #{S_PhysicalEnvir_Light}, " +
            "S_PhysicalEnvir_Hot = #{S_PhysicalEnvir_Hot}, " +
            "S_PhysicalEnvir_Wind = #{S_PhysicalEnvir_Wind}, " +
            "F_HumanEnvir = #{F_HumanEnvir}, " +
            "S_HumanEnvir_Function = #{S_HumanEnvir_Function}, " +
            "S_HumanEnvir_Beauty = #{S_HumanEnvir_Beauty}, " +
            "S_HumanEnvir_Explore = #{S_HumanEnvir_Explore}, " +
            "F_Decorate = #{F_Decorate}, " +
            "S_DecorateDetails = #{S_DecorateDetails}, " +
            "S_DecorateMaterial = #{S_DecorateMaterial}, " +
            "F_Tech = #{F_Tech}, " +
            "S_Tech_Envir = #{S_Tech_Envir}, " +
            "S_Tech_Res = #{S_Tech_Res}, " +
            "S_Tech_Progress = #{S_Tech_Progress} " +
            "WHERE id = #{id}")
    int updateScore(EnvirBenefit4 envirBenefit4);

    /**
     * 删除评分,只用于终评
     *
     * @param envirBenefit4
     * @return
     */
    @Delete("DELETE FROM t_envir_benefit_4 WHERE id = #{id}")
    int deleteScore(EnvirBenefit4 envirBenefit4);
}
