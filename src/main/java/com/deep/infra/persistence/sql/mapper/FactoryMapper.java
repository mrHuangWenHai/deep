package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.AgentModel;
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
            @Result(property = "gmtModofied", column = "gmt_modified"),
            @Result(property = "pkNumber", column = "agent_name"),
            @Result(property = "breadName", column = "agent_area"),
            @Result(property = "breadLocation", column = "agent_father"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "responsiblePersonid", column = "responsible_personid"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "disnfectP", column = "disnfect_p"),
            @Result(property = "agent", column = "agent")
    })
    List<FactoryModel> queryAllFactory();

    /**
     * 根据ID获取单个羊场
     * @param id
     * @return
     */
    @Select("select * from factory_manage where id = #{id}")
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
            "bread_name," +
            "bread_location," +
            "create_time," +
            "responsible_personid," +
            "remark," +
            "disnfect_p," +
            "agent," +
            ") values(" +
            "#{gmtCreate}" +
            "#{gmtModofied}" +
            "#{pkNumber}" +
            "#{breadName}" +
            "#{breadLocation}" +
            "#{createTime}" +
            "#{responsiblePersonid}" +
            "#{remark}" +
            "#{disnfectP}" +
            "#{agent})")
    Long insertFactory(FactoryModel factoryModel);

    /**
     * 修改一个羊场的信息
     * @param factoryModel
     * @return
     */
    @Update("update factory_manage set" +
            "gmt_create = #{gmtCreate}, " +
            "gmt_modified = #{gmtModofied}, " +
            "pk_number = #{pkNumber}, " +
            "bread_name = #{breadName}, " +
            "bread_location = #{breadLocation}, " +
            "create_time = #{createTime}, " +
            "responsible_personid = #{responsiblePersonid}, " +
            "remark = #{remark}, " +
            "disnfect_p = #{disnfectP}, " +
            "agent = #{agent}, " +
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
