package com.whu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whu.pojo.Expert;

@Mapper
public interface ExpertTypeMapper {
    @Options(useGeneratedKeys = false, keyProperty = "id")

    @Insert("INSERT INTO t_expert_type(expert_id, prize_id, priority) " +
            "VALUES(#{expertId}, #{prizeId}, #{priority})")
    int insertExpertType(@Param(value = "expertId") Long expertId,
                         @Param(value = "prizeId") Long prizeId,
                         @Param(value = "priority") int priority);

    /**
     * 根据专家姓名返回专家信息
     *
     * @param name 专家姓名
     * @return 专家信息
     * @use ProjectAssignmentService:根据项目列表里面的专家姓名返回专家的id
     */
    @Select("SELECT * FROM t_expert WHERE name = #{name}")
    Expert queryExpertByName(String name);

}
