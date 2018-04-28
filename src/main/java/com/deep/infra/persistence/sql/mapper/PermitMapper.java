package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.PermitModel;
import com.deep.domain.model.RoleModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermitMapper {
    /**
     * 列出权限列表
     * @return
     */
    @Select("select * from permit_manage")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "permitId", column = "permit_id"),
            @Result(property = "permitName", column = "permit_name")
    })
    List<PermitModel> queryAllPermit();

    /**
     * 根据ID获取单个权限
     * @param permitId
     * @return
     */
    @Select("select * from permit_manage where permit_id = #{permitId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "permitId", column = "permit_id"),
            @Result(property = "permitName", column = "permit_name")
    })
    PermitModel queryPermitById(int permitId);


    /**
     * 插入一个权限
     * @param permitModel
     * @return
     */
    @Insert("insert into permit_manage(" +
            "gmt_create," +
            "gmt_modified," +
            "permit_id," +
            "permit_name)" +
            "values(" +
            "#{id}," +
            "#{gmtCreate}," +
            "#{gmtModified}," +
            "#{permitId}," +
            "#{permitName}" +
            ")")
    Long insertPermit(PermitModel permitModel);

    /**
     * 修改一个权限
     * @param permitModel
     * @return
     */
    @Update("update permit_manage set " +
            "gmt_create = #{gmtCreate}, " +
            "gmt_modified = #{gmtModified}, " +
            "permit_id = #{permitId}, " +
            "permit_name = #{permitName} " +
            "where id = #{id}")
    Long updatePermit(PermitModel permitModel);

    /**
     * 删除权限信息
     * @param permitId
     * @return
     */
    @Delete("delete from permit_manage where id = #{id}")
    Long deletePermit(Long permitId);
}
