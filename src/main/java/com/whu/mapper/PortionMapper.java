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

import com.whu.pojo.Portion;

@Mapper
public interface PortionMapper {

    @Options(useGeneratedKeys = false, keyProperty = "id")
    /**
     * 插入各项权重
     */
    @Insert("INSERT INTO t_portion(expert_id,type,envir_benefit, econo_benefit, social_benefit) " +
            "VALUES(#{expert_id}, #{type},#{envir_benefit}, #{econo_benefit}, #{social_benefit})")
    int setPortion(@Param("expert_id") Long expert_id,
                   @Param("type") Long type,
                   @Param("envir_benefit") int envir_benefit,
                   @Param("econo_benefit") int econo_benefit,
                   @Param("social_benefit") int social_benefit);

    /*
     * 查询该专家是否已经设置权重
     */
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "expert_id", property = "expert_id"),
            @Result(column = "type", property = "type"),
            @Result(column = "envir_benefit", property = "envir_benefit"),
            @Result(column = "econo_benefit", property = "econo_benefit"),
            @Result(column = "social_benefit", property = "social_benefit")
    })
    @Select("SELECT * FROM t_portion WHERE expert_id = #{expert_id}")
    List<Portion> queryPortionByID(@Param("expert_id") Long expert_id);


    /*
     * 修改权重
     */
    @Update("UPDATE t_portion SET " +
            "envir_benefit = #{envir_benefit}, " +
            "econo_benefit = #{econo_benefit}, " +
            "social_benefit = #{social_benefit} " +
            "WHERE expert_id = #{expert_id} AND type=#{type}")
    void updatePortion(@Param("expert_id") Long expert_id,
                       @Param("type") Long type,
                       @Param("envir_benefit") int envir_benefit,
                       @Param("econo_benefit") int econo_benefit,
                       @Param("social_benefit") int social_benefit);

}
