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
     * 获取角色的固定权限, 返回本身格式String
     * @param id
     * @return
     */
    public String findRoleDefaultPermits(long id) {
        RoleModel roleModel = roleMapper.queryRoleByPkTypeId(id);
        if (roleModel == null) {
            return "";
        } else {
            return roleModel.getDefaultPermit();
        }
    }
    /**
     * 获取角色的固定权限返回map前端使用, 通过位运算操作
     * @return
     */
    public Map findRolePermits(long id) {
        System.out.println(id);
        String permit = roleMapper.queryRoleByPkTypeId(id).getDefaultPermit();
        Map map = new HashMap();
        for (int i = 0; i < permit.length(); i++) {
            if (permit.charAt(i) == '1') {
                map.put(i, permitMapper.queryPermitById(i+1).getPermitName());
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
    public Map findExtendPermit(Map map, String permit) {
        // 位运算基础
        for (int i = 0; i < permit.length(); i++) {
            if (permit.charAt(i) == '1') {
                map.put(i, permitMapper.queryPermitById(i+1).getPermitName());
            }
        }
        return map;
    }
}
