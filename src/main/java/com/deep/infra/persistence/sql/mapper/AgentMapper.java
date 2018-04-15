package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.AgentModel;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AgentMapper {
    /**
     * 列出代理列表
     * @return
     */
    @Select("select * from agent_factory")
    @Results ({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "agentName", column = "agent_name"),
            @Result(property = "agentArea", column = "agent_area"),
            @Result(property = "agentFather", column = "agent_father")
    })
    List<AgentModel> queryAllAgent();

    /**
     * 获取所有的子代理
     * @param id
     * @return
     */
    @Select("select * from agent_factory where agent_father = #{id}")
    @Results ({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "agentName", column = "agent_name"),
            @Result(property = "agentArea", column = "agent_area"),
            @Result(property = "agentFather", column = "agent_father")
    })
    List<AgentModel> getSons(int id);



    /**

     * 根据ID获取单个代理
     * @param id
     * @return
     */
    @Select("select * from agent_factory where id = #{id}")
    @Results ({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "agentName", column = "agent_name"),
            @Result(property = "agentArea", column = "agent_area"),
            @Result(property = "agentFather", column = "agent_father")
    })
    AgentModel queryAgentByID(Long id);

    /**
     * 插入一个代理
     * @param agentModel
     * @return
     */
    @Insert("insert into agent_factory(" +
            "gmt_create, " +
            "gmt_modified, " +
            "agent_rank, " +
            "agent_area, " +
            "agent_name, " +
            "agent_father" +
            ") values(" +
            "#{gmtCreate}, " +
            "#{gmtModified}, " +
            "#{agentRank}," +
            "#{agentArea}, " +
            "#{agentName}, " +
            "#{agentFather}" +
            ")")
    Long insertAgent(AgentModel agentModel);

    /**
     * 修改一个代理
     * @param agentModel
     * @return
     */
    @Update("update agent_factory set " +
            "gmt_create = #{gmtCreate}, " +
            "gmt_modified = #{gmtModified}, " +
            "agent_rank = #{agentRank}, " +
            "agent_area = #{agentArea}, " +
            "agent_name = #{agentName}, " +
            "agent_father = #{agentFather}" +
            " where id = #{id}")
    Long updateAgent(AgentModel agentModel);

    /**
     * 删除一个代理
     * @param permitID
     * @return
     */
    @Delete("delete from agent_factory where id = #{id}")
    Long deleteAgent(Long permitID);
}
