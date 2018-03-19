package com.deep.domain.service;

import com.deep.domain.model.RoleModel;
import com.deep.infra.persistence.sql.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    public void setRoleModel(RoleModel roleModel){
        this.roleMapper.setRoleModel(roleModel);
    }

    public RoleModel getRoleModelByid(BigInteger id){
        RoleModel roleModel = this.roleMapper.getRoleModelByid(id);
        return roleModel;
    }
    public List<RoleModel> getAllRoleModel(){
        List<RoleModel> roleModels = this.roleMapper.getAllRoleModel();
        return roleModels;
    }

    public int deleteRoleModelByid(BigInteger id){
        int row = this.roleMapper.deleteRoleModelByid(id);
        return row;
    }

}
