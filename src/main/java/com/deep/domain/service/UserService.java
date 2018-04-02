package com.deep.domain.service;

import com.deep.api.authorization.tools.RoleAndPermit;
import com.deep.domain.model.AgentModel;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.UserModel;
import com.deep.infra.persistence.sql.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private FactoryService factoryService;

    @Resource
    private AgentService agentService;

    @Resource
    private RoleService roleService;

    // 保存用户的登录信息
    private UserModel user;
    /**
     * 用户登录在用户表中的可见信息
     */
    public class UserLogin {
        private long id;;                      // 用户表的主键
        private String pkUserid;               // 用户名
        private String userPwd;                // 密码
        private String userPic;                // 用户照片存储路径
        private Map userRole;                  // 用户角色
        private Map userPermit;                // 用户所具有的权限
        private Map userFactory;               // 用户所属羊场

        public Map getUserFactory() {
            return userFactory;
        }

        public void setUserFactory(Map userFactory) {
            this.userFactory = userFactory;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPkUserid() {
            return pkUserid;
        }

        public void setPkUserid(String pkUserid) {
            this.pkUserid = pkUserid;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public Map getUserRole() {
            return userRole;
        }

        public void setUserRole(Map userRole) {
            this.userRole = userRole;
        }

        public Map getUserPermit() {
            return userPermit;
        }

        public void setUserPermit(Map userPermit) {
            this.userPermit = userPermit;
        }
    }

    /**
     * 获取所有的用户信息
     * @return
     */
    public List<UserModel> getAll() {
        return userMapper.queryAllUser();
    }

    /**
     * 根据主键获取单个用户的信息
     */
    public UserModel getOneUser(Long id) {
        return userMapper.queryUserById(id);
    }

    /**
     * 根据用户名获取单个用户的信息
     * @param pkUserid  用户名
     * @return 单个用户信息的模型
     */
    public UserModel getUserByPkuserID(String pkUserid) {
        return userMapper.queryUserByPkuserID(pkUserid);
    }

    /**
     * 根据用户的真实姓名获取单个用户的信息
     */
    public UserModel getUserByUserRealnameLike(String userRealname) {
        return userMapper.queryUserByRealnameLike(userRealname);
    }

    /**
     * 插入一个新的用户
     * @param userModel
     * @return
     */
    public Long addUser(UserModel userModel) {
        return userMapper.insertUser(userModel);
    }

    /**
     * 更新单个用户的信息
     * @param userModel
     * @return
     */
    public Long updateUser(UserModel userModel) {
        return userMapper.updateUser(userModel);
    }

    /**
     * 删除单个用户
     * @param id
     * @return
     */
    public Long deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }

    /**
     * 返回用户的角色和权限信息
     * @param id
     * @return
     */
    public RoleAndPermit findRoleByUserID(long id) {
        RoleAndPermit roleAndPermit = new RoleAndPermit();
        UserModel userModel = userMapper.queryUserById(id);
        if (userModel == null) {
            return null;
        }
        roleAndPermit.setRole(userModel.getUserRole());
        roleAndPermit.setExtended(userModel.getIsExtended());
        roleAndPermit.setExtendedPermit(userModel.getUserPermit());

        return roleAndPermit;
    }
    /**
     ***********************************************************************
     */
    /**
     * 验证用户登录的信息
     * @param userModel 用户登录提交的实体
     * @return list.size()表示列表的大小,如果>0,则说明有该用户反之则没有该用户
     */
    public boolean verifyLogin(UserModel userModel) {
        // 根据用户的输入信息到数据库中进行查询,由于注册时候的限制,只能有一个相同用户名的用户,所以user的size的值一定为1
        user = userMapper.queryUserByPkuserID(userModel.getPkUserid());
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证用户名是否已经存在, 如果存在, 则返回false, 如果不存在,则返回true
     * @param id
     * @return
     */
    public boolean verifyOnlyOnePkUserid(String id) {
        UserModel userModel = userMapper.queryUserByPkuserID(id);
        if (userModel != null) {
            // 此用户名被注册过
            return false;
        } else {
            // 此用户名没有被注册过
            return true;
        }
    }

    /**
     * 根据用户的ID获取用户返回到前端可见的权限, 登录之后的信息
     * @param id 用户的ID
     * @return
     */
    public UserLogin findOneUser(long id) {
        UserModel userModel = userMapper.queryUserById(id);
        if (userModel == null) {
            return null;
        }
        UserLogin userLogin = new UserLogin();
        // 用户角色信息
        Map roleMapper = new HashMap();
        // 用户权限信息
        Map permitMapper = new HashMap();
        // 用户代理羊场信息
        Map factoryOrAgentMapper = new HashMap();
        userLogin.setId(userModel.getId());
        userLogin.setPkUserid(userModel.getPkUserid());
        userLogin.setUserPic(userModel.getUserPic());
        userLogin.setUserPwd(userModel.getUserPwd());
        // 角色名称
        String roleName = roleService.getOneRoleByRolePkTypeID(userModel.getUserRole()).getTypeName();
        roleMapper.put("roleName", roleName);
        roleMapper.put("roleNum", userModel.getUserRole());
        userLogin.setUserRole(roleMapper);

        permitMapper = roleService.findRolePermits(userModel.getUserRole());
        // 判断是否有拓展权限
        if (userModel.getIsExtended() == 0) {
            // 没有拓展权限
        } else {
            permitMapper = roleService.findExtendPermit(permitMapper, userModel.getUserPermit());
        }
        userLogin.setUserPermit(permitMapper);

        int isFactory = userModel.getIsFactory();
        long factoryId = userModel.getUserFactory();

        // 判断客户的类型(代理(总公司)\羊场\游客)
        if (isFactory == 0) {
            // 代表羊场
            FactoryModel factoryModel = factoryService.getOneFactory(factoryId);
            if (factoryModel != null) {
                factoryOrAgentMapper.put("factoryNum", factoryModel.getId());
                factoryOrAgentMapper.put("factoryName",factoryModel.getBreadName());
            }
        } else if (isFactory == 1){
            // 代表代理
            AgentModel agentModel = agentService.getOneAgent(factoryId);
            if (agentModel != null) {
                factoryOrAgentMapper.put("agentNum", agentModel.getId());
                factoryOrAgentMapper.put("agentName", agentModel.getAgentName());
                factoryOrAgentMapper.put("agentArea", agentModel.getAgentArea());
            }
        }
        userLogin.setUserFactory(factoryOrAgentMapper);
        return userLogin;
    }

    /**
     * 修改用户密码
     * @param userPwd
     * @return
     */
    public Long updateUserPwd(String userPwd, String username) {
        return userMapper.updateUserPwd(userPwd, username);
    }
}
