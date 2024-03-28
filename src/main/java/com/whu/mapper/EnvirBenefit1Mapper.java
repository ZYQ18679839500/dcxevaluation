package com.whu.mapper;

import com.whu.pojo.EnvirBenefit1;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnvirBenefit1Mapper {
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
            @Result(column = "F_SaveLands", property = "F_SaveLands"),
            @Result(column = "S_SaveLands_LandUse", property = "S_SaveLands_LandUse"),
            @Result(column = "S_SaveLands_Outdoors", property = "S_SaveLands_Outdoors"),
            @Result(column = "S_SaveLands_Transport", property = "S_SaveLands_Transport"),
            @Result(column = "S_SaveLands_SiteDesign", property = "S_SaveLands_SiteDesign"),
            @Result(column = "F_SaveEnergy", property = "F_SaveEnergy"),
            @Result(column = "S_SaveEnergy_Build", property = "S_SaveEnergy_Build"),
            @Result(column = "S_SaveEnergy_Condition", property = "S_SaveEnergy_Condition"),
            @Result(column = "S_SaveEnergy_Light", property = "S_SaveEnergy_Light"),
            @Result(column = "S_SaveEnergy_EnergyUse", property = "S_SaveEnergy_EnergyUse"),
            @Result(column = "F_SaveWater", property = "F_SaveWater"),
            @Result(column = "S_SaveWater_System", property = "S_SaveWater_System"),
            @Result(column = "S_SaveWater_Tool", property = "S_SaveWater_Tool"),
            @Result(column = "S_SaveWater_Use", property = "S_SaveWater_Use"),
            @Result(column = "F_SaveRes", property = "F_SaveRes"),
            @Result(column = "S_SaveRes_SaveRes", property = "S_SaveRes_SaveRes"),
            @Result(column = "S_SaveRes_Choose", property = "S_SaveRes_Choose"),
            @Result(column = "F_Indoor", property = "F_Indoor"),
            @Result(column = "S_Indoor_Sound", property = "S_Indoor_Sound"),
            @Result(column = "S_Indoor_Light", property = "S_Indoor_Light"),
            @Result(column = "S_Indoor_Hot", property = "S_Indoor_Hot"),
            @Result(column = "S_Indoor_Atmo", property = "S_Indoor_Atmo"),
            @Result(column = "F_Construction", property = "F_Construction"),
            @Result(column = "S_Construction_Envir", property = "S_Construction_Envir"),
            @Result(column = "S_Construction_Res", property = "S_Construction_Res"),
            @Result(column = "S_Construction_Progress", property = "S_Construction_Progress"),
            @Result(column = "F_Operation", property = "F_Operation"),
            @Result(column = "S_Operation_Management", property = "S_Operation_Management"),
            @Result(column = "S_Operation_Tech", property = "S_Operation_Tech"),
            @Result(column = "S_Operation_Envir", property = "S_Operation_Envir"),
            @Result(column = "F_Innovation", property = "F_Innovation"),
            @Result(column = "S_Innovation_Structure", property = "S_Innovation_Structure"),
            @Result(column = "S_Innovation_Management", property = "S_Innovation_Management"),
            @Result(column = "S_Innovation_Tech", property = "S_Innovation_Tech"),
            @Result(column = "F_Humanity", property = "F_Humanity"),
            @Result(column = "S_Humanity_People", property = "S_Humanity_People"),
            @Result(column = "S_Humanity_GreenLive", property = "S_Humanity_GreenLive"),
            @Result(column = "S_Humanity_GreenEdu", property = "S_Humanity_GreenEdu"),
            @Result(column = "S_Humanity_History", property = "S_Humanity_History"),
            @Result(column = "F_Art", property = "F_Art"),
            @Result(column = "S_Art_Design", property = "S_Art_Design")

    })
    @Select("SELECT * FROM t_envir_benefit_1 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "And expert_id = #{expertId}")
    EnvirBenefit1 queryScoreByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_SaveLands", property = "F_SaveLands"),
            @Result(column = "S_SaveLands_LandUse", property = "S_SaveLands_LandUse"),
            @Result(column = "S_SaveLands_Outdoors", property = "S_SaveLands_Outdoors"),
            @Result(column = "S_SaveLands_Transport", property = "S_SaveLands_Transport"),
            @Result(column = "S_SaveLands_SiteDesign", property = "S_SaveLands_SiteDesign"),
            @Result(column = "F_SaveEnergy", property = "F_SaveEnergy"),
            @Result(column = "S_SaveEnergy_Build", property = "S_SaveEnergy_Build"),
            @Result(column = "S_SaveEnergy_Condition", property = "S_SaveEnergy_Condition"),
            @Result(column = "S_SaveEnergy_Light", property = "S_SaveEnergy_Light"),
            @Result(column = "S_SaveEnergy_EnergyUse", property = "S_SaveEnergy_EnergyUse"),
            @Result(column = "F_SaveWater", property = "F_SaveWater"),
            @Result(column = "S_SaveWater_System", property = "S_SaveWater_System"),
            @Result(column = "S_SaveWater_Tool", property = "S_SaveWater_Tool"),
            @Result(column = "S_SaveWater_Use", property = "S_SaveWater_Use"),
            @Result(column = "F_SaveRes", property = "F_SaveRes"),
            @Result(column = "S_SaveRes_SaveRes", property = "S_SaveRes_SaveRes"),
            @Result(column = "S_SaveRes_Choose", property = "S_SaveRes_Choose"),
            @Result(column = "F_Indoor", property = "F_Indoor"),
            @Result(column = "S_Indoor_Sound", property = "S_Indoor_Sound"),
            @Result(column = "S_Indoor_Light", property = "S_Indoor_Light"),
            @Result(column = "S_Indoor_Hot", property = "S_Indoor_Hot"),
            @Result(column = "S_Indoor_Atmo", property = "S_Indoor_Atmo"),
            @Result(column = "F_Construction", property = "F_Construction"),
            @Result(column = "S_Construction_Envir", property = "S_Construction_Envir"),
            @Result(column = "S_Construction_Res", property = "S_Construction_Res"),
            @Result(column = "S_Construction_Progress", property = "S_Construction_Progress"),
            @Result(column = "F_Operation", property = "F_Operation"),
            @Result(column = "S_Operation_Management", property = "S_Operation_Management"),
            @Result(column = "S_Operation_Tech", property = "S_Operation_Tech"),
            @Result(column = "S_Operation_Envir", property = "S_Operation_Envir"),
            @Result(column = "F_Innovation", property = "F_Innovation"),
            @Result(column = "S_Innovation_Structure", property = "S_Innovation_Structure"),
            @Result(column = "S_Innovation_Management", property = "S_Innovation_Management"),
            @Result(column = "S_Innovation_Tech", property = "S_Innovation_Tech"),
            @Result(column = "F_Humanity", property = "F_Humanity"),
            @Result(column = "S_Humanity_People", property = "S_Humanity_People"),
            @Result(column = "S_Humanity_GreenLive", property = "S_Humanity_GreenLive"),
            @Result(column = "S_Humanity_GreenEdu", property = "S_Humanity_GreenEdu"),
            @Result(column = "S_Humanity_History", property = "S_Humanity_History"),
            @Result(column = "F_Art", property = "F_Art"),
            @Result(column = "S_Art_Design", property = "S_Art_Design")

    })
    @Select("SELECT * FROM t_envir_benefit_1 WHERE project_id = #{projectId} " +
            "AND state = #{state}")
    List<EnvirBenefit1> queryScoresByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_SaveLands", property = "F_SaveLands"),
            @Result(column = "S_SaveLands_LandUse", property = "S_SaveLands_LandUse"),
            @Result(column = "S_SaveLands_Outdoors", property = "S_SaveLands_Outdoors"),
            @Result(column = "S_SaveLands_Transport", property = "S_SaveLands_Transport"),
            @Result(column = "S_SaveLands_SiteDesign", property = "S_SaveLands_SiteDesign"),
            @Result(column = "F_SaveEnergy", property = "F_SaveEnergy"),
            @Result(column = "S_SaveEnergy_Build", property = "S_SaveEnergy_Build"),
            @Result(column = "S_SaveEnergy_Condition", property = "S_SaveEnergy_Condition"),
            @Result(column = "S_SaveEnergy_Light", property = "S_SaveEnergy_Light"),
            @Result(column = "S_SaveEnergy_EnergyUse", property = "S_SaveEnergy_EnergyUse"),
            @Result(column = "F_SaveWater", property = "F_SaveWater"),
            @Result(column = "S_SaveWater_System", property = "S_SaveWater_System"),
            @Result(column = "S_SaveWater_Tool", property = "S_SaveWater_Tool"),
            @Result(column = "S_SaveWater_Use", property = "S_SaveWater_Use"),
            @Result(column = "F_SaveRes", property = "F_SaveRes"),
            @Result(column = "S_SaveRes_SaveRes", property = "S_SaveRes_SaveRes"),
            @Result(column = "S_SaveRes_Choose", property = "S_SaveRes_Choose"),
            @Result(column = "F_Indoor", property = "F_Indoor"),
            @Result(column = "S_Indoor_Sound", property = "S_Indoor_Sound"),
            @Result(column = "S_Indoor_Light", property = "S_Indoor_Light"),
            @Result(column = "S_Indoor_Hot", property = "S_Indoor_Hot"),
            @Result(column = "S_Indoor_Atmo", property = "S_Indoor_Atmo"),
            @Result(column = "F_Construction", property = "F_Construction"),
            @Result(column = "S_Construction_Envir", property = "S_Construction_Envir"),
            @Result(column = "S_Construction_Res", property = "S_Construction_Res"),
            @Result(column = "S_Construction_Progress", property = "S_Construction_Progress"),
            @Result(column = "F_Operation", property = "F_Operation"),
            @Result(column = "S_Operation_Management", property = "S_Operation_Management"),
            @Result(column = "S_Operation_Tech", property = "S_Operation_Tech"),
            @Result(column = "S_Operation_Envir", property = "S_Operation_Envir"),
            @Result(column = "F_Innovation", property = "F_Innovation"),
            @Result(column = "S_Innovation_Structure", property = "S_Innovation_Structure"),
            @Result(column = "S_Innovation_Management", property = "S_Innovation_Management"),
            @Result(column = "S_Innovation_Tech", property = "S_Innovation_Tech"),
            @Result(column = "F_Humanity", property = "F_Humanity"),
            @Result(column = "S_Humanity_People", property = "S_Humanity_People"),
            @Result(column = "S_Humanity_GreenLive", property = "S_Humanity_GreenLive"),
            @Result(column = "S_Humanity_GreenEdu", property = "S_Humanity_GreenEdu"),
            @Result(column = "S_Humanity_History", property = "S_Humanity_History"),
            @Result(column = "F_Art", property = "F_Art"),
            @Result(column = "S_Art_Design", property = "S_Art_Design")

    })
    @Select("SELECT * FROM t_envir_benefit_1 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "ORDER BY expert_id")
    List<EnvirBenefit1> queryScoresByProjectIdAndStateOrderByExpertId(@Param("projectId") Long projectId,
                                                                      @Param("state") int state);

    /**
     * 插入项目分数
     *
     * @param envirBenefit1 插入的项目分数信息
     * @return 事务操作返回值
     */
    @Insert("INSERT INTO t_envir_benefit_1(project_id, expert_id," +
            "F_SaveLands, S_SaveLands_LandUse, S_SaveLands_Outdoors, S_SaveLands_Transport, S_SaveLands_SiteDesign," +
            "F_SaveEnergy,S_SaveEnergy_Build,S_SaveEnergy_Condition,S_SaveEnergy_Light,S_SaveEnergy_EnergyUse," +
            "F_SaveWater,S_SaveWater_System,S_SaveWater_Tool,S_SaveWater_Use," +
            "F_SaveRes,S_SaveRes_SaveRes,S_SaveRes_Choose," +
            "F_Indoor,S_Indoor_Sound,S_Indoor_Light,S_Indoor_Hot,S_Indoor_Atmo," +
            "F_Construction,S_Construction_Envir,S_Construction_Res,S_Construction_Progress," +
            "F_Operation,S_Operation_Management,S_Operation_Tech,S_Operation_Envir," +
            "F_Innovation,S_Innovation_Structure,S_Innovation_Management,S_Innovation_Tech," +
            "F_Humanity,S_Humanity_People,S_Humanity_GreenLive,S_Humanity_GreenEdu,S_Humanity_History," +
            "F_Art,S_Art_Design," +
            " state) " +
            "VALUES(#{projectId}, #{expertId}," +
            "#{F_SaveLands}, #{S_SaveLands_LandUse},#{S_SaveLands_Outdoors},#{S_SaveLands_Transport}, #{S_SaveLands_SiteDesign}," +
            "#{F_SaveEnergy},#{S_SaveEnergy_Build},#{S_SaveEnergy_Condition},#{S_SaveEnergy_Light},#{S_SaveEnergy_EnergyUse}," +
            "#{F_SaveWater},#{S_SaveWater_System},#{S_SaveWater_Tool},#{S_SaveWater_Use}," +
            "#{F_SaveRes},#{S_SaveRes_SaveRes},#{S_SaveRes_Choose}," +
            "#{F_Indoor},#{S_Indoor_Sound},#{S_Indoor_Light},#{S_Indoor_Hot},#{S_Indoor_Atmo}," +
            "#{F_Construction},#{S_Construction_Envir},#{S_Construction_Res},#{S_Construction_Progress}," +
            "#{F_Operation},#{S_Operation_Management},#{S_Operation_Tech},#{S_Operation_Envir}," +
            "#{F_Innovation},#{S_Innovation_Structure},#{S_Innovation_Management},#{S_Innovation_Tech}," +
            "#{F_Humanity},#{S_Humanity_People},#{S_Humanity_GreenLive},#{S_Humanity_GreenEdu},#{S_Humanity_History}," +
            "#{F_Art},#{S_Art_Design}," +
            "#{state})")
    int insertScore(EnvirBenefit1 envirBenefit1);

    /**
     * 更新分数，只用于终评
     *
     * @param envirBenefit1 更新的分数
     * @return
     */
    @Update("UPDATE t_envir_benefit_1 SET " +
            "F_SaveLands = #{F_SaveLands}, " +
            "S_SaveLands_LandUse = #{S_SaveLands_LandUse}, " +
            "S_SaveLands_Outdoors = #{S_SaveLands_Outdoors}, " +
            "S_SaveLands_Transport = #{S_SaveLands_Transport}, " +
            "S_SaveLands_SiteDesign = #{S_SaveLands_SiteDesign}, " +
            "F_SaveEnergy= #{F_SaveEnergy}, " +
            "S_SaveEnergy_Build= #{S_SaveEnergy_Build}, " +
            "S_SaveEnergy_Condition= #{S_SaveEnergy_Condition}, " +
            "S_SaveEnergy_Light= #{S_SaveEnergy_Light}, " +
            "S_SaveEnergy_EnergyUse= #{S_SaveEnergy_EnergyUse}, " +
            "F_SaveWater= #{F_SaveWater}, " +
            "S_SaveWater_System= #{S_SaveWater_System}, " +
            "S_SaveWater_Tool= #{S_SaveWater_Tool}, " +
            "S_SaveWater_Use= #{S_SaveWater_Use}, " +
            "F_SaveRes= #{F_SaveRes}, " +
            "S_SaveRes_SaveRes= #{S_SaveRes_SaveRes}, " +
            "S_SaveRes_Choose= #{S_SaveRes_Choose}, " +
            "F_Indoor= #{F_Indoor}, " +
            "S_Indoor_Sound= #{S_Indoor_Sound}, " +
            "S_Indoor_Light= #{S_Indoor_Light}, " +
            "S_Indoor_Hot = #{S_Indoor_Hot}, " +
            "S_Indoor_Atmo= #{S_Indoor_Atmo}, " +
            "F_Construction= #{F_Construction}, " +
            "S_Construction_Envir= #{S_Construction_Envir}, " +
            "S_Construction_Res= #{S_Construction_Res}, " +
            "S_Construction_Progress= #{S_Construction_Progress}, " +
            "F_Operation= #{F_Operation}, " +
            "S_Operation_Management= #{S_Operation_Management}, " +
            "S_Operation_Tech= #{S_Operation_Tech}, " +
            "S_Operation_Envir= #{S_Operation_Envir}, " +
            "F_Innovation= #{F_Innovation}, " +
            "S_Innovation_Structure= #{S_Innovation_Structure}, " +
            "S_Innovation_Management= #{S_Innovation_Management}, " +
            "S_Innovation_Tech= #{S_Innovation_Tech}, " +
            "F_Humanity= #{F_Humanity}, " +
            "S_Humanity_People= #{S_Humanity_People}, " +
            "S_Humanity_GreenLive= #{S_Humanity_GreenLive}, " +
            "S_Humanity_GreenEdu= #{S_Humanity_GreenEdu}, " +
            "S_Humanity_History= #{S_Humanity_History}, " +
            "F_Art= #{F_Art}, " +
            "S_Art_Design= #{S_Art_Design} " +
            "WHERE id = #{id}")
    int updateScore(EnvirBenefit1 envirBenefit1);

    /**
     * 删除评分,只用于终评
     *
     * @param envirBenefit1
     * @return
     */
    @Delete("DELETE FROM t_envir_benefit_1 WHERE id = #{id}")
    int deleteScore(EnvirBenefit1 envirBenefit1);
}
