package com.deep.infra.persistence.sql.mapper;

import com.deep.api.response.FactoryResponse;
import com.deep.domain.model.FactoryModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FactoryMapper {

    @Select("select count(*) from agent_factory where agent_rank != 0")
    Long queryCount();

    @Select("select * from factory_manage where responsible_person_id < 0 and agent = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "breedLocationDetail", column = "breed_location_detail"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "responsiblePersonName", column = "responsible_person_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent")
    })
    List<FactoryModel> getAllNoResponsibleFactory(Long userId);

    /**
     * 列出羊场列表
     * @return
     */
    @Select("select * from factory_manage limit #{start}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "breedLocationDetail", column = "breed_location_detail"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "responsiblePersonName", column = "responsible_person_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent")
    })
    List<FactoryModel> queryAllFactory(@Param("start") Long start, @Param("size") Byte size);

    /**
     * 查找某个代理下的所有羊场
     * @param id
     * @return
     */
    @Select("select factory_manage.id, factory_manage.gmt_create, factory_manage.gmt_modified" +
            ", factory_manage.pk_number, factory_manage.breed_name, factory_manage.breed_location" +
            ", factory_manage.breed_location_detail, factory_manage.create_time, factory_manage.responsible_person_id" +
            ", factory_manage.responsible_person_name, factory_manage.remark, factory_manage.disinfect_p" +
            ", factory_manage.agent, agent_factory.agent_name from factory_manage,agent_factory where factory_manage.agent = #{id} and factory_manage.agent = agent_factory.id limit #{start}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "breedLocationDetail", column = "breed_location_detail"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "responsiblePersonName", column = "responsible_person_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent"),
            @Result(property = "agentName", column = "agent_name")
    })
    List<FactoryResponse> queryFactoryByAgentIDPage(@Param("id") Long id, @Param("start") Long start, @Param("size") Byte size);


    /**
     * 查找某个代理下的所有羊场
     * @param id
     * @return
     */
    @Select("select * from factory_manage where agent = #{agent}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "breedLocationDetail", column = "breed_location_detail"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "responsiblePersonName", column = "responsible_person_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent")
    })
    List<FactoryModel> queryFactoryByAgentID(Long id);

    /**
     * 查找某个代理下的所有羊场的ID
     * @param id
     * @return
     */
    @Select("select id from factory_manage where agent = #{agent}")
    @Results({
            @Result(property = "id", column = "id"),
    })
    long[] queryFactoryIDByAgentID(Long id);


    /**
     * 根据地理位置查找factory
     * @param location
     * @return
     */
    @Select("select * from factory_manage where breed_location like #{location}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "breedLocationDetail", column = "breed_location_detail"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "responsiblePersonName", column = "responsible_person_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent")
    })
    List<FactoryModel> queryFactoryByLocation(String location);

    /**
     * 根据羊场的ID发展羊场的代理agent的ID
     * @param pkNumber
     * @return
     */
    @Select("select agent from factory_manage where pk_number = #{pkNumber}")
//    @Results({
//            @Result(property = "agent", column = "agent")
//    })
    Short queryOneAgentByFactoryID(String pkNumber);

    /**
     * 根据ID获取单个羊场
     * @param id
     * @return
     */
    @Select("select * from factory_manage where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "breedLocationDetail", column = "breed_location_detail"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "responsiblePersonName", column = "responsible_person_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent")
    })
    FactoryModel queryFactoryByID(Long id);

    @Select("select factory_manage.id, factory_manage.gmt_create, factory_manage.gmt_modified" +
            ", factory_manage.pk_number, factory_manage.breed_name, factory_manage.breed_location" +
            ", factory_manage.breed_location_detail, factory_manage.create_time, factory_manage.responsible_person_id" +
            ", factory_manage.responsible_person_name, factory_manage.remark, factory_manage.disinfect_p" +
            ", factory_manage.agent, agent_factory.agent_name from factory_manage,agent_factory where factory_manage.id = #{id} and factory_manage.agent = agent_factory.id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "breedLocationDetail", column = "breed_location_detail"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "responsiblePersonName", column = "responsible_person_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent"),
            @Result(property = "agentName", column = "agent_name")
    })
    FactoryResponse queryFactoryAgentByID(Long id);

    /**
     * 插入一个羊场
     * @param factoryModel
     * @return
     */
    @Insert("insert into factory_manage(" +
            "gmt_create," +
            "gmt_modified," +
            "pk_number," +
            "breed_name," +
            "breed_location," +
            "breed_location_detail," +
            "create_time," +
            "responsible_person_id," +
            "responsible_person_name," +
            "remark," +
            "disinfect_p," +
            "agent" +
            ") values(" +
            "#{gmtCreate}, " +
            "#{gmtModified}, " +
            "#{pkNumber}, " +
            "#{breedName}, " +
            "#{breedLocation}, " +
            "#{breedLocationDetail}, " +
            "#{createTime}, " +
            "#{responsiblePersonId}, " +
            "#{responsiblePersonName}, " +
            "#{remark}, " +
            "#{disinfectP}, " +
            "#{agent})")
    int insertFactory(FactoryModel factoryModel);

    /**
     * 修改一个羊场的信息
     * @param factoryModel
     * @return
     */
    @Update("update factory_manage set " +
            "gmt_create = #{gmtCreate}, " +
            "gmt_modified = #{gmtModified}, " +
            "pk_number = #{pkNumber}, " +
            "breed_name = #{breedName}, " +
            "breed_location = #{breedLocation}," +
            "breed_location_detail = #{breedLocationDetail}, " +
            "create_time = #{createTime}, " +
            "responsible_person_id = #{responsiblePersonId}, " +
            "responsible_person_name = #{responsiblePersonName}, " +
            "remark = #{remark}, " +
            "disinfect_p = #{disinfectP}, " +
            "agent = #{agent} " +
            "where id = #{id}")
    Long updateFactory(FactoryModel factoryModel);

    /**
     * 删除一个羊场
     * @param id
     * @return
     */
    @Delete("delete from factory_manage where id = #{id}")
    Long deleteFactory(Long id);

    /**
     * 根据羊场的ID发展羊场的代理agent的ID
     * @param id
     * @return
     */
    @Select("select agent from factory_manage where id = #{id}")
    @Results({
            @Result(property = "agent", column = "agent")
    })
    Short queryOneAgentByID(Long id);

}
