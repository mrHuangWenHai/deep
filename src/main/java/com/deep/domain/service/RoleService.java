package com.deep.domain.service;

import com.deep.domain.model.RoleModel;
import com.deep.infra.persistence.sql.mapper.PermitMapper;
import com.deep.infra.persistence.sql.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermitMapper permitMapper;
    /**
     * 获取所有的角色信息
     * @return
     */
    public List<RoleModel> getAll() {
        return roleMapper.queryAllRole();
    }

    /**
     * 根据角色主键次查询单个角色信息
     * @param id
     * @return
     */
    public RoleModel getOneRole(Long id) {
        System.out.println(id);
        System.out.println(roleMapper.queryRoleById(id).getDefaultPermit());
        System.out.println(roleMapper.queryRoleById(id).getTypeName());
        return roleMapper.queryRoleById(id);
    }

    /**
     * 根据pktypeID获取相应的单个角色的信息
     * @param id
     * @return
     */
    public RoleModel getOneRoleByRolePkTypeID(Long id) {
        System.out.println(id);
        return roleMapper.queryRoleByPkTypeId(id);
    }

    /**
     * 插入一个新的角色
     * @param roleModel
     * @return
     */
    public Long addRole(RoleModel roleModel) {
        return roleMapper.insertRole(roleModel);
    }

    /**
     * 修改单个角色
     * @param roleModel
     * @return
     */
    public Long updateRole(RoleModel roleModel) {
        return roleMapper.updateRole(roleModel);
    }

    /**
     * 删除单个角色
     * @param id
     * @return
     */
    public Long deleteRole(Long id) {
        return roleMapper.deleteRole(id);
    }

    /**
     * 获取角色的固定权限, 通过位运算操作
     * @return
     */
    public Map findRolePermits(long id) {
        System.out.println(id);
        long permit = roleMapper.queryRoleByPkTypeId(id).getDefaultPermit();
        Map map = new HashMap();
        // 位运算基础
        long basic = 1;
        long result = 0;
        result = basic & permit;
        if (result == 0) {

        } else {
            map.put("1", permitMapper.queryPermitById(1).getPermitName());
        }

        for (int i = 2; i <= 64; i++) {
            basic <<= 1;
            result = basic & permit;
            if (Math.pow(2, i-1) == result) {
                map.put(i, permitMapper.queryPermitById(i).getPermitName());
            } else {

            }
        }

        return map;
    }

    /**
     * 查询用户的拓展权限并返回
     * @param map
     * @param permit
     * @return
     */
    public Map findExtendPermit(Map map, long permit) {
        // 位运算基础
        long basic = 1;
        long result = 0;
        result = basic & permit;
        if (result == 0) {

        } else {
            map.put("1", permitMapper.queryPermitById(1).getPermitName());
        }

        for (int i = 2; i <= 64; i++) {
            basic <<= 1;
            result = basic & permit;
            if (Math.pow(2, i-1) == result) {
                map.put(i, permitMapper.queryPermitById(i).getPermitName());
            } else {

            }
        }

        return map;
    }
}
