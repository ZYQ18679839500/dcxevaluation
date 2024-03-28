package com.whu.mapper;

import com.whu.pojo.EnvirBenefit2;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnvirBenefit2Mapper {
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
            @Result(column = "F_LandPlan", property = "F_LandPlan"),
            @Result(column = "S_LandPlan_LandUse", property = "S_LandPlan_LandUse"),
            @Result(column = "S_LandPlan_LayOut", property = "S_LandPlan_LayOut"),
            @Result(column = "F_Eco", property = "F_Eco"),
            @Result(column = "S_Eco_Nature", property = "S_Eco_Nature"),
            @Result(column = "S_Eco_Envir", property = "S_Eco_Envir"),
            @Result(column = "F_GreenBulid", property = "F_GreenBulid"),
            @Result(column = "S_GreenBuild_GreenBulid", property = "S_GreenBuild_GreenBulid"),
            @Result(column = "F_Res", property = "F_Res"),
            @Result(column = "S_Res_Energy", property = "S_Res_Energy"),
            @Result(column = "S_Res_Water", property = "S_Res_Water"),
            @Result(column = "S_Res_Res", property = "S_Res_Res"),
            @Result(column = "S_Res_Carbon", property = "S_Res_Carbon"),
            @Result(column = "F_GreenTransport", property = "F_GreenTransport"),
            @Result(column = "S_GreenTransport_Trans", property = "S_GreenTransport_Trans"),
            @Result(column = "S_GreenTransport_Road", property = "S_GreenTransport_Road"),
            @Result(column = "S_GreenTransport_Static", property = "S_GreenTransport_Static"),
            @Result(column = "F_InfoManager", property = "F_InfoManager"),
            @Result(column = "S_InfoManager_Urban", property = "S_InfoManager_Urban"),
            @Result(column = "S_InfoManager_Service", property = "S_InfoManager_Service"),
            @Result(column = "F_Innovation", property = "F_Innovation"),
            @Result(column = "S_Innovation", property = "S_Innovation"),
            @Result(column = "F_Humanity", property = "F_Humanity"),
            @Result(column = "S_Humanity_People", property = "S_Humanity_People"),
            @Result(column = "S_Humanity_GreenLive", property = "S_Humanity_GreenLive"),
            @Result(column = "S_Humanity_GreenEdu", property = "S_Humanity_GreenEdu"),
            @Result(column = "S_Humanity_History", property = "S_Humanity_History"),
            @Result(column = "F_Art", property = "F_Art"),
            @Result(column = "S_Art_Bulid", property = "S_Art_Bulid"),
            @Result(column = "S_Art_Envir", property = "S_Art_Envir")
    })
    @Select("SELECT * FROM t_envir_benefit_2 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "And expert_id = #{expertId}")
    EnvirBenefit2 queryScoreByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_LandPlan", property = "F_LandPlan"),
            @Result(column = "S_LandPlan_LandUse", property = "S_LandPlan_LandUse"),
            @Result(column = "S_LandPlan_LayOut", property = "S_LandPlan_LayOut"),
            @Result(column = "F_Eco", property = "F_Eco"),
            @Result(column = "S_Eco_Nature", property = "S_Eco_Nature"),
            @Result(column = "S_Eco_Envir", property = "S_Eco_Envir"),
            @Result(column = "F_GreenBulid", property = "F_GreenBulid"),
            @Result(column = "S_GreenBuild_GreenBulid", property = "S_GreenBuild_GreenBulid"),
            @Result(column = "F_Res", property = "F_Res"),
            @Result(column = "S_Res_Energy", property = "S_Res_Energy"),
            @Result(column = "S_Res_Water", property = "S_Res_Water"),
            @Result(column = "S_Res_Res", property = "S_Res_Res"),
            @Result(column = "S_Res_Carbon", property = "S_Res_Carbon"),
            @Result(column = "F_GreenTransport", property = "F_GreenTransport"),
            @Result(column = "S_GreenTransport_Trans", property = "S_GreenTransport_Trans"),
            @Result(column = "S_GreenTransport_Road", property = "S_GreenTransport_Road"),
            @Result(column = "S_GreenTransport_Static", property = "S_GreenTransport_Static"),
            @Result(column = "F_InfoManager", property = "F_InfoManager"),
            @Result(column = "S_InfoManager_Urban", property = "S_InfoManager_Urban"),
            @Result(column = "S_InfoManager_Service", property = "S_InfoManager_Service"),
            @Result(column = "F_Innovation", property = "F_Innovation"),
            @Result(column = "S_Innovation", property = "S_Innovation"),
            @Result(column = "F_Humanity", property = "F_Humanity"),
            @Result(column = "S_Humanity_People", property = "S_Humanity_People"),
            @Result(column = "S_Humanity_GreenLive", property = "S_Humanity_GreenLive"),
            @Result(column = "S_Humanity_GreenEdu", property = "S_Humanity_GreenEdu"),
            @Result(column = "S_Humanity_History", property = "S_Humanity_History"),
            @Result(column = "F_Art", property = "F_Art"),
            @Result(column = "S_Art_Bulid", property = "S_Art_Bulid"),
            @Result(column = "S_Art_Envir", property = "S_Art_Envir")
    })
    @Select("SELECT * FROM t_envir_benefit_2 WHERE project_id = #{projectId} " +
            "AND state = #{state}")
    List<EnvirBenefit2> queryScoresByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_LandPlan", property = "F_LandPlan"),
            @Result(column = "S_LandPlan_LandUse", property = "S_LandPlan_LandUse"),
            @Result(column = "S_LandPlan_LayOut", property = "S_LandPlan_LayOut"),
            @Result(column = "F_Eco", property = "F_Eco"),
            @Result(column = "S_Eco_Nature", property = "S_Eco_Nature"),
            @Result(column = "S_Eco_Envir", property = "S_Eco_Envir"),
            @Result(column = "F_GreenBulid", property = "F_GreenBulid"),
            @Result(column = "S_GreenBuild_GreenBulid", property = "S_GreenBuild_GreenBulid"),
            @Result(column = "F_Res", property = "F_Res"),
            @Result(column = "S_Res_Energy", property = "S_Res_Energy"),
            @Result(column = "S_Res_Water", property = "S_Res_Water"),
            @Result(column = "S_Res_Res", property = "S_Res_Res"),
            @Result(column = "S_Res_Carbon", property = "S_Res_Carbon"),
            @Result(column = "F_GreenTransport", property = "F_GreenTransport"),
            @Result(column = "S_GreenTransport_Trans", property = "S_GreenTransport_Trans"),
            @Result(column = "S_GreenTransport_Road", property = "S_GreenTransport_Road"),
            @Result(column = "S_GreenTransport_Static", property = "S_GreenTransport_Static"),
            @Result(column = "F_InfoManager", property = "F_InfoManager"),
            @Result(column = "S_InfoManager_Urban", property = "S_InfoManager_Urban"),
            @Result(column = "S_InfoManager_Service", property = "S_InfoManager_Service"),
            @Result(column = "F_Innovation", property = "F_Innovation"),
            @Result(column = "S_Innovation", property = "S_Innovation"),
            @Result(column = "F_Humanity", property = "F_Humanity"),
            @Result(column = "S_Humanity_People", property = "S_Humanity_People"),
            @Result(column = "S_Humanity_GreenLive", property = "S_Humanity_GreenLive"),
            @Result(column = "S_Humanity_GreenEdu", property = "S_Humanity_GreenEdu"),
            @Result(column = "S_Humanity_History", property = "S_Humanity_History"),
            @Result(column = "F_Art", property = "F_Art"),
            @Result(column = "S_Art_Bulid", property = "S_Art_Bulid"),
            @Result(column = "S_Art_Envir", property = "S_Art_Envir")
    })
    @Select("SELECT * FROM t_envir_benefit_2 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "ORDER BY expert_id")
    List<EnvirBenefit2> queryScoresByProjectIdAndStateOrderByExpertId(@Param("projectId") Long projectId,
                                                                      @Param("state") int state);

    /**
     * 插入项目分数
     *
     * @param envirBenefit2 插入的项目分数信息
     * @return 事务操作返回值
     */
    @Insert("INSERT INTO t_envir_benefit_2(project_id, expert_id," +
            "F_LandPlan, S_LandPlan_LandUse, S_LandPlan_LayOut, " +
            "F_Eco,S_Eco_Nature,S_Eco_Envir," +
            "F_GreenBulid,S_GreenBuild_GreenBulid," +
            "F_Res,S_Res_Energy,S_Res_Water,S_Res_Res,S_Res_Carbon," +
            "F_GreenTransport,S_GreenTransport_Trans,S_GreenTransport_Road,S_GreenTransport_Static," +
            "F_InfoManager,S_InfoManager_Urban,S_InfoManager_Service," +
            "F_Innovation,S_Innovation," +
            "F_Humanity,S_Humanity_People,S_Humanity_GreenLive,S_Humanity_GreenEdu,S_Humanity_History," +
            "F_Art,S_Art_Bulid,S_Art_Envir," +
            "state) " +
            "VALUES(#{projectId}, #{expertId}," +
            "#{F_LandPlan}, #{S_LandPlan_LandUse}, #{S_LandPlan_LayOut}," +
            "#{F_Eco}, #{S_Eco_Nature}, #{S_Eco_Envir}," +
            "#{F_GreenBulid}, #{S_GreenBuild_GreenBulid}," +
            "#{F_Res}, #{S_Res_Energy}, #{S_Res_Water}, #{S_Res_Res},#{S_Res_Carbon}, " +
            "#{F_GreenTransport}, #{S_GreenTransport_Trans}, #{S_GreenTransport_Road}, #{S_GreenTransport_Static}, " +
            "#{F_InfoManager}, #{S_InfoManager_Urban}, #{S_InfoManager_Service}, " +
            "#{F_Innovation}, #{F_Innovation}," +
            "#{F_Humanity}, #{S_Humanity_People}, #{S_Humanity_GreenLive}, #{S_Humanity_GreenEdu},#{S_Humanity_History}, " +
            "#{F_Art}, #{S_Art_Bulid}, #{S_Art_Envir}," +
            " #{state})")
    int insertScore(EnvirBenefit2 envirBenefit2);

    /**
     * 更新分数，只用于终评
     *
     * @param envirBenefit2 更新的分数
     * @return
     */
    @Update("UPDATE t_envir_benefit_2 SET " +
            " F_LandPlan= #{F_LandPlan}, " +
            " S_LandPlan_LandUse= #{S_LandPlan_LandUse}, " +
            " S_LandPlan_LayOut= #{S_LandPlan_LayOut}, " +
            " F_Eco= #{F_Eco}, " +
            " S_Eco_Nature= #{S_Eco_Nature}, " +
            " S_Eco_Envir= #{S_Eco_Envir}, " +
            " F_GreenBulid= #{F_GreenBulid}, " +
            " S_GreenBuild_GreenBulid= #{S_GreenBuild_GreenBulid}, " +
            " F_Res= #{F_Res}, " +
            " S_Res_Energy= #{S_Res_Energy}, " +
            " S_Res_Water= #{S_Res_Water}, " +
            " S_Res_Res= #{S_Res_Res}, " +
            " S_Res_Carbon= #{S_Res_Carbon}, " +
            " F_GreenTransport= #{F_GreenTransport}, " +
            " S_GreenTransport_Trans= #{S_GreenTransport_Trans}, " +
            " S_GreenTransport_Road= #{S_GreenTransport_Road}, " +
            " S_GreenTransport_Static= #{S_GreenTransport_Static}, " +
            " F_InfoManager= #{F_InfoManager}, " +
            " S_InfoManager_Urban= #{S_InfoManager_Urban}, " +
            " S_InfoManager_Service= #{S_InfoManager_Service}, " +
            " F_Innovation= #{F_Innovation}, " +
            " S_Innovation= #{S_Innovation}, " +
            " F_Humanity= #{F_Humanity}, " +
            " S_Humanity_People= #{S_Humanity_People}, " +
            " S_Humanity_GreenLive= #{S_Humanity_GreenLive}, " +
            " S_Humanity_GreenEdu= #{S_Humanity_GreenEdu}, " +
            " S_Humanity_History= #{S_Humanity_History}, " +
            " F_Art= #{F_Art}, " +
            " S_Art_Bulid= #{S_Art_Bulid}, " +
            " S_Art_Envir= #{S_Art_Envir} " +
            "WHERE id = #{id}")
    int updateScore(EnvirBenefit2 envirBenefit2);

    /**
     * 删除评分,只用于终评
     *
     * @param envirBenefit2
     * @return
     */
    @Delete("DELETE FROM t_envir_benefit_2 WHERE id = #{id}")
    int deleteScore(EnvirBenefit2 envirBenefit2);
}
