package com.whu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whu.pojo.EconoBenefit2;

@Mapper
public interface EconoBenefit2Mapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    /**
     * 根据项目的id和项目的评分进度返回项目评分情况
     * @param projectId 项目id
     * @param state 初评或者会评
     * @return 返回的评分情况
     */
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "state", property = "state"),
            @Result(column = "F_AssetPlan", property = "F_AssetPlan"),
            @Result(column = "S_AssetPlan_Para", property = "S_AssetPlan_Para"),
            @Result(column = "F_Industry", property = "F_Industry"),
            @Result(column = "S_Industry_Early", property = "S_Industry_Early"),
            @Result(column = "S_Industry_Rent", property = "S_Industry_Rent"),
            @Result(column = "S_Industry_Increment", property = "S_Industry_Increment"),
            @Result(column = "S_Industry_Manager", property = "S_Industry_Manager"),
            @Result(column = "F_Financial", property = "F_Financial"),
            @Result(column = "S_Financial_Managerment", property = "S_Financial_Managerment"),
            @Result(column = "S_Financial_Cash", property = "S_Financial_Cash"),
            @Result(column = "S_Financial_Operate", property = "S_Financial_Operate"),
            @Result(column = "S_Financial_Increment", property = "S_Financial_Increment")
    })
    @Select("SELECT * FROM t_econo_benefit_2 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "AND expert_id = #{expertId}")
    EconoBenefit2 queryScoreByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "state", property = "state"),
            @Result(column = "F_AssetPlan", property = "F_AssetPlan"),
            @Result(column = "S_AssetPlan_Para", property = "S_AssetPlan_Para"),
            @Result(column = "F_Industry", property = "F_Industry"),
            @Result(column = "S_Industry_Early", property = "S_Industry_Early"),
            @Result(column = "S_Industry_Rent", property = "S_Industry_Rent"),
            @Result(column = "S_Industry_Increment", property = "S_Industry_Increment"),
            @Result(column = "S_Industry_Manager", property = "S_Industry_Manager"),
            @Result(column = "F_Financial", property = "F_Financial"),
            @Result(column = "S_Financial_Managerment", property = "S_Financial_Managerment"),
            @Result(column = "S_Financial_Cash", property = "S_Financial_Cash"),
            @Result(column = "S_Financial_Operate", property = "S_Financial_Operate"),
            @Result(column = "S_Financial_Increment", property = "S_Financial_Increment")
    })
    @Select("SELECT * FROM t_econo_benefit_2 WHERE project_id = #{projectId} " +
            "AND state = #{state}")
    List<EconoBenefit2> queryScoresByProjectIdAndState(@Param("projectId") Long projectId,
                                                       @Param("state") int state);

    /**
     * 根据项目的id和项目的评分进度返回项目评分情况
     *
     * @param projectId 项目id
     * @param state     初评或者会评
     * @return 返回的评分情况
     */
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "state", property = "state"),
            @Result(column = "F_AssetPlan", property = "F_AssetPlan"),
            @Result(column = "S_AssetPlan_Para", property = "S_AssetPlan_Para"),
            @Result(column = "F_Industry", property = "F_Industry"),
            @Result(column = "S_Industry_Early", property = "S_Industry_Early"),
            @Result(column = "S_Industry_Rent", property = "S_Industry_Rent"),
            @Result(column = "S_Industry_Increment", property = "S_Industry_Increment"),
            @Result(column = "S_Industry_Manager", property = "S_Industry_Manager"),
            @Result(column = "F_Financial", property = "F_Financial"),
            @Result(column = "S_Financial_Managerment", property = "S_Financial_Managerment"),
            @Result(column = "S_Financial_Cash", property = "S_Financial_Cash"),
            @Result(column = "S_Financial_Operate", property = "S_Financial_Operate"),
            @Result(column = "S_Financial_Increment", property = "S_Financial_Increment")
    })
    @Select("SELECT * FROM t_econo_benefit_2 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "ORDER BY expert_id")
    List<EconoBenefit2> queryScoresByProjectIdAndStateOrderByExpertId(@Param("projectId") Long projectId,
                                                                      @Param("state") int state);


    /**
     * 插入项目分数
     *
     * @param econoBenefit 插入的项目分数信息
     * @return 事务操作返回值
     */
    @Insert("INSERT INTO t_econo_benefit_2(project_id, expert_id, F_AssetPlan,S_AssetPlan_Para,F_Industry,S_Industry_Early,S_Industry_Rent,S_Industry_Increment,S_Industry_Manager,F_Financial,S_Financial_Managerment,S_Financial_Cash,S_Financial_Operate,S_Financial_Increment, state) " +
            "VALUES(#{projectId}, #{expertId}, #{F_AssetPlan},#{S_AssetPlan_Para},#{F_Industry},#{S_Industry_Early},#{S_Industry_Rent},#{S_Industry_Increment},#{S_Industry_Manager},#{F_Financial},#{S_Financial_Managerment},#{S_Financial_Cash},#{S_Financial_Operate},#{S_Financial_Increment}, #{state})")
    int insertScore(EconoBenefit2 econoBenefit2);

    /**
     * 更新分数，只用于终评
     *
     * @param econoBenefit 更新的分数
     * @return
     */
    @Update("UPDATE t_econo_benefit_2 SET F_AssetPlan = #{F_AssetPlan}, " +
            "S_AssetPlan_Para=#{S_AssetPlan_Para}, " +
            "F_Industry=#{F_Industry}, " +
            "S_Industry_Early=#{S_Industry_Early}, " +
            "S_Industry_Rent=#{S_Industry_Rent}, " +
            "S_Industry_Increment=#{S_Industry_Increment}, " +
            "S_Industry_Manager=#{S_Industry_Manager}, " +
            "F_Financial=#{F_Financial}, " +
            "S_Financial_Managerment=#{S_Financial_Managerment}, " +
            "S_Financial_Cash=#{S_Financial_Cash}, " +
            "S_Financial_Operate=#{S_Financial_Operate}, " +
            "S_Financial_Increment=#{S_Financial_Increment} " +
            "WHERE id = #{id}")
    int updateScore(EconoBenefit2 econoBenefit2);

    /**
     * 删除评分,只用于终评
     *
     * @param econoBenefit
     * @return
     */
    @Delete("DELETE FROM t_econo_benefit_2 WHERE id = #{id}")
    int deleteScore(EconoBenefit2 econoBenefit2);
}
