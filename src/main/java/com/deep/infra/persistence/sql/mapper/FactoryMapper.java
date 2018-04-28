package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.FactoryModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FactoryMapper {
    /**
     * 列出羊场列表
     * @return
     */
    @Select("select * from factory_manage")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent")
    })
    List<FactoryModel> queryAllFactory();

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
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
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
    @Select("select * from factory_manage where bread_location = #{location}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "pk_number"),
            @Result(property = "breedName", column = "breed_name"),
            @Result(property = "breedLocation", column = "breed_location"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
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
    @Results({
            @Result(property = "agent", column = "agent")
    })
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
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonId", column = "responsible_person_id"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disinfectP", column = "disinfect_p"),
            @Result(property = "agent", column = "agent")
    })
    FactoryModel queryFactoryByID(Long id);

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
            "create_time," +
            "responsible_person_id," +
            "remark," +
            "disinfect_p," +
            "agent" +
            ") values(" +
            "#{gmtCreate}, " +
            "#{gmtModified}, " +
            "#{pkNumber}, " +
            "#{breedName}, " +
            "#{breedLocation}, " +
            "#{createTime}, " +
            "#{responsiblePersonId}, " +
            "#{remark}, " +
            "#{disinfectP}, " +
            "#{agent})")
    Long insertFactory(FactoryModel factoryModel);

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
            "breed_location = #{breedLocation}, " +
            "create_time = #{createTime}, " +
            "responsible_person_id = #{responsiblePersonId}, " +
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
}
