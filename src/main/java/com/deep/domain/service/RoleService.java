package com.deep.domain.service;

import com.deep.domain.model.RoleModel;
import com.deep.infra.persistence.sql.mapper.PermitMapper;
import com.deep.infra.persistence.sql.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermitMapper permitMapper;
    /**
     * 获取所有的角色信息
     * @return 所有角色列表
     */
    public List<RoleModel> getAll(Long start, Byte size, Byte rank, Long factoryID) {
        return roleMapper.queryAllRole(start, size, rank, factoryID);
    }

    public List<RoleModel> getAnotherAll(Long start, Byte size, Byte rank) {
        return roleMapper.queryAnotherAllRole(start, size, rank);
    }

    /**
     * 根据角色主键次查询单个角色信息
     * @param id 角色主键
     * @return  单个角色的信息
     */
    public RoleModel getOneRole(Long id) {
        System.out.println(id);
        System.out.println(roleMapper.queryRoleById(id).getDefaultPermit());
        System.out.println(roleMapper.queryRoleById(id).getTypeName());
        return roleMapper.queryRoleById(id);
    }

    /**
     * 根据pktypeID获取相应的单个角色的信息
     * @param id pktypeID
     * @return 单个角色的信息
     */
    public RoleModel getOneRoleByRolePkTypeID(Long id) {
        return roleMapper.queryRoleByPkTypeId(id);
    }

    /**
     * 插入一个新的角色
     * @param roleModel 新的角色的模型类
     * @return 是否插入成功标志
     */
    public Long addRole(RoleModel roleModel) {
        return roleMapper.insertRole(roleModel);
    }

    public Long getTheBigId() {
        return roleMapper.getTheBigId();
    }

    /**
     * 修改单个角色
     * @param roleModel 修改的单个角色的角色信息
     * @return 是否修改成功的标志
     */
    public Long updateRole(RoleModel roleModel) {
        return roleMapper.updateRole(roleModel);
    }

    /**
     * 删除单个角色
     * @param id 单个角色的ID
     * @return 是否成功的标志
     */
    public Long deleteRole(Long id) {
        return roleMapper.deleteRole(id);
    }

    /**
     * 获取角色的固定权限, 返回本身格式String
     * @param id 角色的ID
     * @return 权限组合
     */
    public String findRoleDefaultPermits(long id) {
        RoleModel roleModel = roleMapper.queryRoleById(id);
        if (roleModel == null) {
            return "";
        } else {
            return roleModel.getDefaultPermit();
        }
    }
    /**
     * 获取某个角色固定的权限
     * @return 权限组合
     */
    String findRolePermits(long id) {
        RoleModel roleModel = roleMapper.queryRoleById(id);
        if (roleModel == null) {
            return null;
        }
        return roleModel.getDefaultPermit();
    }

    /**
     * 查询用户的拓展权限并返回
     * @param defaultPermits    默认权限
     * @param permit            拓展权限
     * @return                  综合权限
     */
    public String findExtendPermit(String defaultPermits, String permit) {
        char[] allPermits = new char[192];
        for (int i = 0; i < permit.length(); i++) {
            if (permit.charAt(i) == '1' || defaultPermits.charAt(i) == '1') {
                allPermits[i] = '1';
                continue;
            }
            allPermits[i] = '0';
        }
        return String.valueOf(allPermits);
    }

    /**
     * 获取所有角色的数量
     * @return 所有角色的数量
     */
    public Long findAllTheCount(Byte rank) {
        return roleMapper.queryCount(rank);
    }

    public Long findAnotherTheCount(Byte rank, Long factoryID) {
        return roleMapper.queryAnotherCount(rank, factoryID);
    }
}
