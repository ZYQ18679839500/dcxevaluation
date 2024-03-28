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

import com.whu.pojo.EconoBenefit3;

@Mapper
public interface EconoBenefit3Mapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    /**
     * 根据项目的id和项目的评分进度返回项目评分情况
     * @param projectId 项目id
     * @param state 初评或者会评
     * @return 返回的评分情况
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "expert_id", property = "expertId"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "state", property = "state"),
            @Result(column = "F_AssetManager", property = "F_AssetManager"),
            @Result(column = "S_AssetManager_Early", property = "S_AssetManager_Early"),
            @Result(column = "S_AssetManager_Service", property = "S_AssetManager_Service"),
            @Result(column = "S_AssetManager_MoreAsset", property = "S_AssetManager_MoreAsset"),
            @Result(column = "F_ProjectManager", property = "F_ProjectManager"),
            @Result(column = "S_ProjectManager_Cost", property = "S_ProjectManager_Cost"),
            @Result(column = "S_ProjectManager_Method", property = "S_ProjectManager_Method")
    })
    @Select("SELECT * FROM t_econo_benefit_3 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "AND expert_id = #{expertId}")
    EconoBenefit3 queryScoreByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_AssetManager", property = "F_AssetManager"),
            @Result(column = "S_AssetManager_Early", property = "S_AssetManager_Early"),
            @Result(column = "S_AssetManager_Service", property = "S_AssetManager_Service"),
            @Result(column = "S_AssetManager_MoreAsset", property = "S_AssetManager_MoreAsset"),
            @Result(column = "F_ProjectManager", property = "F_ProjectManager"),
            @Result(column = "S_ProjectManager_Cost", property = "S_ProjectManager_Cost"),
            @Result(column = "S_ProjectManager_Method", property = "S_ProjectManager_Method")
    })
    @Select("SELECT * FROM t_econo_benefit_3 WHERE project_id = #{projectId} " +
            "AND state = #{state}")
    List<EconoBenefit3> queryScoresByProjectIdAndState(@Param("projectId") Long projectId,
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
            @Result(column = "F_AssetManager", property = "F_AssetManager"),
            @Result(column = "S_AssetManager_Early", property = "S_AssetManager_Early"),
            @Result(column = "S_AssetManager_Service", property = "S_AssetManager_Service"),
            @Result(column = "S_AssetManager_MoreAsset", property = "S_AssetManager_MoreAsset"),
            @Result(column = "F_ProjectManager", property = "F_ProjectManager"),
            @Result(column = "S_ProjectManager_Cost", property = "S_ProjectManager_Cost"),
            @Result(column = "S_ProjectManager_Method", property = "S_ProjectManager_Method")
    })
    @Select("SELECT * FROM t_econo_benefit_3 WHERE project_id = #{projectId} " +
            "AND state = #{state} " +
            "ORDER BY expert_id")
    List<EconoBenefit3> queryScoresByProjectIdAndStateOrderByExpertId(@Param("projectId") Long projectId,
                                                                      @Param("state") int state);

    /**
     * 插入项目分数
     *
     * @param econoBenefit 插入的项目分数信息
     * @return 事务操作返回值
     */
    @Insert("INSERT INTO t_econo_benefit_3(project_id, expert_id, F_AssetManager,S_AssetManager_Early,S_AssetManager_Service,S_AssetManager_MoreAsset,F_ProjectManager,S_ProjectManager_Cost,S_ProjectManager_Method,state) " +
            "VALUES(#{projectId}, #{expertId}, #{F_AssetManager},#{S_AssetManager_Early},#{S_AssetManager_Service},#{S_AssetManager_MoreAsset},#{F_ProjectManager},#{S_ProjectManager_Cost},#{S_ProjectManager_Method},#{state})")
    int insertScore(EconoBenefit3 econoBenefit3);

    /**
     * 更新分数，只用于终评
     *
     * @param econoBenefit 更新的分数
     * @return
     */
    @Update("UPDATE t_econo_benefit_3 SET F_AssetManager = #{F_AssetManager}, " +
            "S_AssetManager_Early=#{S_AssetManager_Early}, " +
            "S_AssetManager_Service=#{S_AssetManager_Service}, " +
            "S_AssetManager_MoreAsset=#{S_AssetManager_MoreAsset}, " +
            "F_ProjectManager=#{F_ProjectManager}, " +
            "S_ProjectManager_Cost=#{S_ProjectManager_Cost}, " +
            "S_ProjectManager_Method=#{S_ProjectManager_Method} " +
            "WHERE id = #{id}")
    int updateScore(EconoBenefit3 econoBenefit3);

    /**
     * 删除评分,只用于终评
     *
     * @param econoBenefit
     * @return
     */
    @Delete("DELETE FROM t_econo_benefit_3 WHERE id = #{id}")
    int deleteScore(EconoBenefit3 econoBenefit3);
}
