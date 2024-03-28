package com.whu.mapper;

import com.whu.pojo.EnvirBenefit3;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EnvirBenefit3Mapper {
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
            @Result(column = "F_Function", property = "F_Function"),
            @Result(column = "S_Function_Use", property = "S_Function_Use"),
            @Result(column = "S_Function_Service", property = "S_Function_Service"),
            @Result(column = "S_Function_Arrivable", property = "S_Function_Arrivable"),
            @Result(column = "S_Function_Open", property = "S_Function_Open"),
            @Result(column = "S_Function_Safe", property = "S_Function_Safe"),
            @Result(column = "F_Tech", property = "F_Tech"),
            @Result(column = "S_Tech_Operate", property = "S_Tech_Operate"),
            @Result(column = "S_Tech_Curing", property = "S_Tech_Curing"),
            @Result(column = "S_Tech_Material", property = "S_Tech_Material"),
            @Result(column = "F_Human", property = "F_Human"),
            @Result(column = "S_Human_Design", property = "S_Human_Design"),
            @Result(column = "S_Human_Explore", property = "S_Human_Explore"),
            @Result(column = "F_Envir", property = "F_Envir"),
            @Result(column = "S_Envir_EnegrySave", property = "S_Envir_EnegrySave"),
            @Result(column = "S_Envir_WaterSave", property = "S_Envir_WaterSave"),
            @Result(column = "S_Envir_Res", property = "S_Envir_Res"),
            @Result(column = "S_Envir_System", property = "S_Envir_System")
    })
    @Select("SELECT * FROM t_envir_benefit_3 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "And expert_id = #{expertId}")
    EnvirBenefit3 queryScoreByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_Function", property = "F_Function"),
            @Result(column = "S_Function_Use", property = "S_Function_Use"),
            @Result(column = "S_Function_Service", property = "S_Function_Service"),
            @Result(column = "S_Function_Arrivable", property = "S_Function_Arrivable"),
            @Result(column = "S_Function_Open", property = "S_Function_Open"),
            @Result(column = "S_Function_Safe", property = "S_Function_Safe"),
            @Result(column = "F_Tech", property = "F_Tech"),
            @Result(column = "S_Tech_Operate", property = "S_Tech_Operate"),
            @Result(column = "S_Tech_Curing", property = "S_Tech_Curing"),
            @Result(column = "S_Tech_Material", property = "S_Tech_Material"),
            @Result(column = "F_Human", property = "F_Human"),
            @Result(column = "S_Human_Design", property = "S_Human_Design"),
            @Result(column = "S_Human_Explore", property = "S_Human_Explore"),
            @Result(column = "F_Envir", property = "F_Envir"),
            @Result(column = "S_Envir_EnegrySave", property = "S_Envir_EnegrySave"),
            @Result(column = "S_Envir_WaterSave", property = "S_Envir_WaterSave"),
            @Result(column = "S_Envir_Res", property = "S_Envir_Res"),
            @Result(column = "S_Envir_System", property = "S_Envir_System")
    })
    @Select("SELECT * FROM t_envir_benefit_3 WHERE project_id = #{projectId} " +
            "AND state = #{state}")
    List<EnvirBenefit3> queryScoresByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_Function", property = "F_Function"),
            @Result(column = "S_Function_Use", property = "S_Function_Use"),
            @Result(column = "S_Function_Service", property = "S_Function_Service"),
            @Result(column = "S_Function_Arrivable", property = "S_Function_Arrivable"),
            @Result(column = "S_Function_Open", property = "S_Function_Open"),
            @Result(column = "S_Function_Safe", property = "S_Function_Safe"),
            @Result(column = "F_Tech", property = "F_Tech"),
            @Result(column = "S_Tech_Operate", property = "S_Tech_Operate"),
            @Result(column = "S_Tech_Curing", property = "S_Tech_Curing"),
            @Result(column = "S_Tech_Material", property = "S_Tech_Material"),
            @Result(column = "F_Human", property = "F_Human"),
            @Result(column = "S_Human_Design", property = "S_Human_Design"),
            @Result(column = "S_Human_Explore", property = "S_Human_Explore"),
            @Result(column = "F_Envir", property = "F_Envir"),
            @Result(column = "S_Envir_EnegrySave", property = "S_Envir_EnegrySave"),
            @Result(column = "S_Envir_WaterSave", property = "S_Envir_WaterSave"),
            @Result(column = "S_Envir_Res", property = "S_Envir_Res"),
            @Result(column = "S_Envir_System", property = "S_Envir_System")
    })
    @Select("SELECT * FROM t_envir_benefit_3 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "ORDER BY expert_id")
    List<EnvirBenefit3> queryScoresByProjectIdAndStateOrderByExpertId(@Param("projectId") Long projectId,
                                                                      @Param("state") int state);

    /**
     * 插入项目分数
     *
     * @param envirBenefit3 插入的项目分数信息
     * @return 事务操作返回值
     */
    @Insert("INSERT INTO t_envir_benefit_3(project_id, expert_id, F_Function, S_Function_Use, S_Function_Service,S_Function_Arrivable , S_Function_Open,S_Function_Safe , F_Tech, S_Tech_Operate, S_Tech_Curing,S_Tech_Material ,F_Human ,S_Human_Design , S_Human_Explore, F_Envir,S_Envir_EnegrySave ,S_Envir_WaterSave ,S_Envir_Res ,S_Envir_System, " +
            "state) " +
            "VALUES(#{projectId}, #{expertId}, #{F_Function}, #{S_Function_Use}, #{S_Function_Service},#{S_Function_Arrivable},#{S_Function_Open},#{S_Function_Safe},#{F_Tech},#{S_Tech_Operate},#{S_Tech_Curing},#{S_Tech_Material},#{F_Human},#{S_Human_Design},#{S_Human_Explore},#{F_Envir},#{S_Envir_EnegrySave},#{S_Envir_WaterSave},#{S_Envir_Res},#{S_Envir_System}," +
            "#{state})")
    int insertScore(EnvirBenefit3 envirBenefit3);

    /**
     * 更新分数，只用于终评
     *
     * @param envirBenefit3 更新的分数
     * @return
     */
    @Update("UPDATE t_envir_benefit_3 SET " +
            "F_Function = #{F_Function}, " +
            "S_Function_Use = #{S_Function_Use}, " +
            "S_Function_Service = #{S_Function_Service}, " +
            "S_Function_Arrivable = #{S_Function_Arrivable}, " +
            "S_Function_Open = #{S_Function_Open}, " +
            "S_Function_Safe = #{S_Function_Safe}, " +
            "F_Tech = #{F_Tech}, " +
            "S_Tech_Operate = #{S_Tech_Operate}, " +
            "S_Tech_Curing = #{S_Tech_Curing}, " +
            "S_Tech_Material = #{S_Tech_Material}, " +
            "F_Human = #{F_Human}, " +
            "S_Human_Design = #{S_Human_Design}, " +
            "S_Human_Explore = #{S_Human_Explore}, " +
            "F_Envir = #{F_Envir}, " +
            "S_Envir_EnegrySave = #{S_Envir_EnegrySave}, " +
            "S_Envir_WaterSave = #{S_Envir_WaterSave}, " +
            "S_Envir_Res = #{S_Envir_Res}, " +
            "S_Envir_System = #{S_Envir_System} " +
            " WHERE id = #{id}")
    int updateScore(EnvirBenefit3 envirBenefit3);

    /**
     * 删除评分,只用于终评
     *
     * @param envirBenefit3
     * @return
     */
    @Delete("DELETE FROM t_envir_benefit_3 WHERE id = #{id}")
    int deleteScore(EnvirBenefit3 envirBenefit3);
}
