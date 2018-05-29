package com.deep.domain.service;

import com.deep.api.Utils.JedisUtil;
import com.deep.api.authorization.tools.RoleAndPermit;
import com.deep.api.response.Professor;
import com.deep.api.response.UserResponse;
import com.deep.domain.model.AgentModel;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.UserModel;
import com.deep.infra.persistence.sql.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

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
        private Map userRole;                  // 用户角色
        private String userPermit;                // 用户所具有的权限
        private Map userFactory;               // 用户所属羊场
        private long agentFather;            // 用户所在的ＡＧＥＮＴ，如果是羊场，则不为空，否则为空

        public UserLogin() {
        }

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

        public long getAgentFather() {
            return agentFather;
        }

        public void setAgentFather(long agentFather) {
            this.agentFather = agentFather;
        }

        public Map getUserRole() {
            return userRole;
        }

        public void setUserRole(Map userRole) {
            this.userRole = userRole;
        }

        public String getUserPermit() {
            return userPermit;
        }

        public void setUserPermit(String userPermit) {
            this.userPermit = userPermit;
        }
    }

    public class UserRole {
        private long id;;                      // 用户表的主键
        private String pkUserid;               // 用户名
        private long userRole;
        private String userEmail;
        private String QQ;
        private String officialPhone;
        private String userTelephone;

        public String getUserTelephone() {
            return userTelephone;
        }

        public void setUserTelephone(String userTelephone) {
            this.userTelephone = userTelephone;
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

        public long getUserRole() {
            return userRole;
        }

        public void setUserRole(long userRole) {
            this.userRole = userRole;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getOfficialPhone() {
            return officialPhone;
        }

        public void setOfficialPhone(String officialPhone) {
            this.officialPhone = officialPhone;
        }

        public UserRole(long id, String pkUserid, long userRole, String userEmail, String QQ, String officialPhone, String userTelephone) {
            this.id = id;
            this.pkUserid = pkUserid;
            this.userRole = userRole;
            this.userEmail = userEmail;
            this.QQ = QQ;
            this.officialPhone = officialPhone;
            this.userTelephone = userTelephone;
        }
    }

    /**
     * 获取所有的用户信息
     * @return
     */
    public List<UserModel> getAll(long roleID) {
        return userMapper.queryAllUser(roleID);
    }

    /**
     * 获取所有的用户信息
     * @return
     */
    public List<String> getAllWithNoCondition() {
        return userMapper.queryAllUsernameNoCondition();
    }

    /**
     * 根据主键获取单个用户的信息
     */
    public UserResponse getOneUserResponse(Long id) {
        UserResponse userResponse = userMapper.queryUserResponseById(id);
        if (userResponse != null) {
            System.out.println(userResponse.toString());
        } else {
            System.out.println("userResponse is null");
        }
        return userResponse;
    }

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
        // 用户代理羊场信息
        Map factoryOrAgentMapper = new HashMap();
        userLogin.setId(userModel.getId());
        // 角色名称
        String roleName = roleService.getOneRole(userModel.getUserRole()).getTypeName();
        roleMapper.put("roleName", roleName);
        roleMapper.put("roleNum", userModel.getUserRole());
        userLogin.setUserRole(roleMapper);
        String allPermits = roleService.findRolePermits(userModel.getUserRole());
        // 判断是否有拓展权限
        if (userModel.getIsExtended() == 1) {
            allPermits = roleService.findExtendPermit(allPermits, userModel.getUserPermit());
        }
        userLogin.setUserPermit(allPermits);
        int isFactory = userModel.getIsFactory();
        long factoryId = userModel.getUserFactory();
        // 判断客户的类型(代理(总公司)\羊场\游客)
        if (isFactory == 0) {
            // 代表羊场
            FactoryModel factoryModel = factoryService.getOneFactory(factoryId);
            Logger logger = LoggerFactory.getLogger(UserService.class);
            logger.info("isFactory", factoryModel);
            if (factoryModel != null) {
                factoryOrAgentMapper.put("factoryNum", factoryModel.getId());
                factoryOrAgentMapper.put("factoryName",factoryModel.getBreedName());
            }
            userLogin.setAgentFather(userModel.getUserFactory());
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
     * 获取同类的角色
     * @param roleID    角色ID
     * @return  人员列表
     */
    public List<Professor> getRoles(long roleID) {
        return userMapper.getOneRoles(roleID);
    }

    /**
     * 修改用户密码
     * @param userPwd   用户密码
     * @return  影响行数
     */
    public Long updateUserPwd(String userPwd, String username) {
        return userMapper.updateUserPwd(userPwd, username);
    }


    /**
     * 获取羊场上级代理的专家信息(测试, only 测试)
     * @param factoryNumber 羊场编号
     * @return  羊场对应专家的电话号码集合
     */
    public List<String> getProfessorTelephoneByFactoryNum(BigInteger factoryNumber) {
        Short agentID = factoryService.getAgentIDByFactoryNumber(factoryNumber.toString());
        return agentID == null ? null : userMapper.queryTelephoneByAgentAndRole(agentID);
    }

    /**
     * 根据羊场获取监督者的信息
     * @param factoryNum 羊场号码
     * @return 手机号字符串
     */
    public List<String> getSuperiorTelephoneByFactoryNum(BigInteger factoryNum) {
        Short agentID = factoryService.getAgentIDByFactoryNumber(factoryNum.toString());
        return agentID == null ? null : userMapper.querySuperiorTelephoneByAgentAndRole(agentID);
    }

    /**
     * 查询某个羊场下的所有用户
     * @param factory  羊场或者代理的ID
     * @param  which  which
     * @return 手机号
     */
    public List<UserResponse> getAllUserOfFactoryOrAgent(Long factory, Byte which, Long page, Byte size) {
        return userMapper.queryUserResponsesById(factory, which, page, size);
    }

    /**
     * 获取所有的专家信息, 包括在线和没有在线的专家
     * @param agents    代理的集合
     * @return
     */
    public Map<String, Object> getProfessor(List<AgentModel> agents) {
        Map<String, Object> allProfessor = new HashMap<>();
        for(AgentModel attribute : agents) {
            System.out.println(attribute.getId());
            allProfessor.put("上级代理" + attribute.getAgentRank(), userMapper.getProfessor((long)attribute.getId()));
        }
        return allProfessor;
    }

    /**
     * 获取上级专家的在线信息
     * @param agentID   代理主键ID
     * @return 数据的类型, 之后要将其改成Request的模板类
     */
    public Professor getFatherProfessors(long agentID) {
        List<Professor> models = new ArrayList<>();
        List<Professor> users = new ArrayList<>();
        long fatherID = agentID;
        //　首先查询上级代理有木有在线的专家
        models = userMapper.getProfessor(agentID);
        for (Professor model : models) {
            if (JedisUtil.getValue(String.valueOf(model.getId())) != null) {
                users.add(model);
            }
        }
        while (users.size() <= 0) {
            // 找到所有在线的上级
            for (Professor model : models) {
                if (JedisUtil.getValue(String.valueOf(model.getId())) != null) {
                    users.add(model);
                }
            }
            if (users.size() > 0) {
                break;
            } else {
//                fatherID = agentService.getFather(agentID).getAgentFather();
                System.out.println(fatherID);
                if (fatherID == 0) {
                    return null;
                }
                AgentModel temp = agentService.getFather(fatherID);
                if (temp == null) {
                    return null;
                }
                fatherID = temp.getId();
                // 如果没有上级, 即最高级, fatherID 为0
                models = userMapper.getProfessor(fatherID);
            }
        }
        int random = (int) (Math.random()*users.size());
        return users.get(random);
    }

    public Long getCountsOfOneFactoryOrOneAgent(Long factory, Byte which) {
        return userMapper.getCountsOfOneFactoryOrOneAgent(factory, which);
    }

    /**
     * 获取代理的等级
     * @return 代理的等级
     */
    public Byte getAgentRank(Long id) {
        AgentModel agentModel = agentService.getOneAgent(id);
        if (agentModel != null) {
            return agentModel.getAgentRank();
        } else {
            return null;
        }
    }

    public Long getFatherAgent(Long userFactory, Byte flag) {
        if (flag == 0) {
            FactoryModel factoryModel = factoryService.getOneFactory(userFactory);
            return Long.valueOf(String.valueOf(factoryModel.getAgent()));
        } else if (flag == 1) {
            AgentModel agentModel = agentService.getOneAgent(userFactory);
            return (long) agentModel.getAgentFather();
        } else {
            return (long)1;
        }
    }

    /**
     * 根据羊场编号或者代理编号删除该羊场或者代理下面的全部信息
     * @param factoryNumber 羊场编号
     * @param isFactory 羊场或者代理标记
     */
    public void deleteUserByFactoryNumber(Long factoryNumber, Byte isFactory) {
        userMapper.deleteUserByFactoryNumber(factoryNumber, isFactory);
    }
}
