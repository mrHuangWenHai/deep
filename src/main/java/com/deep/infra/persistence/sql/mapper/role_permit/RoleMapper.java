package com.deep.infra.persistence.sql.mapper.role_permit;

import com.deep.domain.model.RoleModel;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select count(*) from role_user where id > #{rank}")
    Long queryCount(Byte rank);

    @Select("select count(*) from role_user where (id > #{rank} and factoryID = 0) or factoryID = #{factoryID}")
    Long queryAnotherCount(@Param("rank")Byte rank, @Param("factoryID") Long factoryID);

    /**
     * 列出角色列表
     * @return 所有角色的集合
     */
    @Select("select * from role_user where (id > #{rank} and factoryID = 0) or factoryID = #{factoryID} limit #{start}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkTypeid", column = "pk_typeid"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "roleDescription", column = "role_description"),
            @Result(property = "defaultPermit", column = "default_permit"),
            @Result(property = "factoryID", column = "factoryID")
    })
    List<RoleModel> queryAllRole(@Param("start") Long start, @Param("size") Byte size, @Param("rank")Byte rank, @Param("factoryID") Long factoryID);

    @Select("select * from role_user where id > #{rank} limit #{start}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkTypeid", column = "pk_typeid"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "roleDescription", column = "role_description"),
            @Result(property = "defaultPermit", column = "default_permit"),
            @Result(property = "factoryID", column = "factoryID")
    })
    List<RoleModel> queryAnotherAllRole(@Param("start") Long start, @Param("size") Byte size, @Param("rank")Byte rank);


    /**
     * 根据ID获取单个角色
     * @param roleId 角色的ID
     * @return 单个角色的信息
     */
    @Select("select * from role_user where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkTypeid", column = "pk_typeid"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "roleDescription", column = "role_description"),
            @Result(property = "defaultPermit", column = "default_permit"),
            @Result(property = "factoryID", column = "factoryID")
    })
    RoleModel queryRoleById(Long roleId);

    /**
     * 根据权限的标识获取单个角色
     * @param pkTypeId
     * @return
     */
    @Select("select * from role_user where pk_typeid = #{pkTypeid}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkTypeid", column = "pk_typeid"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "roleDescription", column = "role_description"),
            @Result(property = "defaultPermit", column = "default_permit"),
            @Result(property = "factoryID", column = "factoryID")
    })
    RoleModel queryRoleByPkTypeId(Long pkTypeId);


    @Select("select max(id) from role_user")
    Long getTheBigId();

    /**
     * 插入一个角色
     * @param roleModel
     * @return
     */
    @Insert("insert into role_user(" +
            "gmt_create," +
            "gmt_modified," +
            "pk_typeid," +
            "type_name," +
            "role_description," +
            "default_permit," +
            "factoryID)" +
            "values(" +
            "#{gmtCreate}," +
            "#{gmtModified}," +
            "#{pkTypeid}," +
            "#{typeName}," +
            "#{roleDescription}," +
            "#{defaultPermit}," +
            "#{factoryID}" +
            ")")
    Long insertRole(RoleModel roleModel);

    /**
     * 修改一个角色
     * @param roleModel 用户角色
     * @return 是否成功的标志
     */
    @Update("update role_user set " +
            "gmt_create = #{gmtCreate}, " +
            "gmt_modified = #{gmtModified}, " +
            "pk_typeid = #{pkTypeid}, " +
            "type_name = #{typeName}, " +
            "role_description = #{roleDescription}, " +
            "default_permit = #{defaultPermit} " +
            "where id = #{id}")
    Long updateRole(RoleModel roleModel);

    /**
     * 删除角色信息
     * @param roleID 角色的ID
     * @return 是否删除成功的标志
     */
    @Delete("delete from role_user where id = #{id}")
    Long deleteRole(Long roleID);

}
