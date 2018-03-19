package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.RoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface RoleMapper {
    void setRoleModel(@Param("roleModel")RoleModel roleModel);

    RoleModel getRoleModelByid(@Param("id")BigInteger id);
    List<RoleModel> getAllRoleModel();

    int deleteRoleModelByid(@Param("id") BigInteger id);
}
