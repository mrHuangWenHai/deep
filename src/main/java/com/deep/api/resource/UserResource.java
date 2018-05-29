package com.deep.api.resource;

import com.deep.api.Utils.*;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.token.TokenManagerRealization;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.PasswordRequest;
import com.deep.api.request.RolePermitRequest;
import com.deep.api.request.UserRequest;

import com.deep.api.response.Professor;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.api.response.UserResponse;
import com.deep.domain.model.AgentModel;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.AgentService;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class UserResource {

    private final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Resource
    private UserService userService;

    @Resource
    private AgentService agentService;

    @Resource
    private FactoryService factoryService;

    @Resource
    private TokenManagerRealization tokenManagerRealization;

    public static Map<Long, String> map = new HashMap<>();

    static {
        map.put((long) 4, "东翔总公司专家");
        map.put((long) 8, "省专家");
        map.put((long) 12, "市专家");
        map.put((long) 16, "县专家");
    }

    /**
     * 查找下级的所有用户列表
     * @return 返回所有的用户信息
     */
    @Permit(authorities = "query_user")
    @GetMapping(value = "user/{id}")
    public Response userList(
            @PathVariable("id") String id,
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "10") String size, HttpServletRequest request
    ) {
        logger.info("invoke userList, url is user/{id} {}", id);
        Long uid = StringToLongUtil.stringToLong(id);
        Long upage = StringToLongUtil.stringToLong(page);
        Byte usize = StringToLongUtil.stringToByte(size);
        Byte which = StringToLongUtil.stringToByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
        if (uid < 0 || upage < 0 || usize < 0) {
            return Responses.errorResponse("错误");
        }
        if (which == 0 || which == 2) {
            List<UserResponse> userLists = userService.getAllUserOfFactoryOrAgent(uid, which, upage, usize);
            if (userLists == null) {
                return Responses.errorResponse("error");
            } else {
                Response response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("List", userLists);
                data.put("size", userService.getCountsOfOneFactoryOrOneAgent(uid, which));
                response.setData(data);
                return response;
            }
        } else if (which == 1) {
            System.out.println("this is agent");
            // 所有的子代理
            Map<Long, List<Integer> > agents = AgentUtil.getAllSubordinateAgent(id);
            // 所有的子羊场
            Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(id);
            List<UserResponse> models = new ArrayList<>(userService.getAllUserOfFactoryOrAgent(uid, which, upage*usize, usize));
            if (agents != null && factories != null) {
                // direct agent people
                List<Integer> directAgents = agents.get((long) -1);
                if (directAgents != null) {
                    for (Integer directFactory : directAgents) {
                        System.out.println("directFactory = " + directFactory);
                        models.addAll(userService.getAllUserOfFactoryOrAgent(Long.parseLong(directFactory.toString()), which, upage*usize, usize));
                    }
                }

                // direct factory people
                List<Long> directFactories = factories.get((long)-1);
                if (directFactories != null) {
                    for (Long directFactory : directFactories) {
                        models.addAll(userService.getAllUserOfFactoryOrAgent(Long.parseLong(directFactory.toString()), (byte)0, upage*usize, usize));
                    }
                }

                // un direct agent people
                List<Integer> undirectAgents = agents.get((long) 0);
                if (undirectAgents != null) {
                    for (Integer undirectFactory : undirectAgents) {
                        models.addAll(userService.getAllUserOfFactoryOrAgent(Long.parseLong(undirectFactory.toString()), which, upage*usize, usize));
                    }
                }

                // undirect factory people
                List<Long> undirectFactories = factories.get((long)0);
                if (directFactories != null) {
                    for (Long undirectFactory : undirectFactories) {
                        models.addAll(userService.getAllUserOfFactoryOrAgent(Long.parseLong(undirectFactory.toString()), (byte)0, upage*usize, usize));
                    }
                }
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List", models);
            data.put("size", models.size());
            response.setData(data);
            return response;
        } else {
            return Responses.errorResponse("error");
        }
    }

    /**
     * 通过用户的主键查找单个用户
     * @param id 获取用户的信息(简略信息)
     * @return response
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "user/find/{id}")
    public Response getUserOne(@PathVariable("id")String id) {
        logger.info("invoke getUserOne{}, url is user/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        UserService.UserLogin userModel = userService.findOneUser(uid);
        if (userModel == null) {
            return Responses.errorResponse("用户不存在");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", userModel);
        response.setData(data);
        return response;
    }

    /**
     * 通过用户的主键查找单个用户
     * @param id 获取用户的信息(简略信息)
     * @return response
     */
    @GetMapping(value = "user/detail/{username}")
    public Response getUserOneDetail(@PathVariable("username") String id, HttpServletRequest request) {
        logger.info("invoke getUserOneDetail{}, url is user/detail/{}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("错误");
        }
        Byte which = StringToLongUtil.stringToByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
        if (which == 0) {
            // 这时候是羊场
            which = -1;
        }
        UserResponse userModel = userService.getOneUserResponse(uid);
        logger.info("===========  {}",userModel);
        if (userModel == null) {
            return Responses.errorResponse("系统中该用户不存在");
        }
        if (userModel.getIsFactory() == 0) {
            // 如果是羊场
            which = -1;

        } else if(userModel.getIsFactory() == 1) {
            // 如果是代理
            which = userService.getAgentRank(userModel.getUserFactory());
        }

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", userModel);
        data.put("agentRank", which);
        data.put("agent", userService.getFatherAgent(userModel.getUserFactory(), userModel.getIsFactory()));
        data.put("role", userModel.getIsFactory());
        response.setData(data);
        return response;
    }

    /**
     * 根据用户的真实姓名查找单个用户
     * @param realname realname
     * @return response
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @PostMapping(value = "user/name")
    public Response getUserByUserRealname(@RequestBody Map<String, String> realname) {
        logger.info("invoke getUserByUserRealname{}, url is user/name", realname);
        System.out.println(realname.get("realname"));
        UserModel userModel = userService.getUserByUserRealnameLike(realname.get("realname"));
        if (realname.get("realname") == null) {
            return Responses.errorResponse("error!");
        }
        if (realname.get("realname").equals("")) {
            return Responses.errorResponse("用户名格式错误");
        }
        if (userModel == null) {
            return Responses.errorResponse("用户不存在");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", userModel);
        response.setData(data);
        return response;
    }

    /**
     * 根据用户名获取单个用户的信息
     * @param pkUserid username
     * @return response
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "user/id/{pkUserid}")
    public Response getUserByUserID(@PathVariable("pkUserid") String pkUserid) {

        logger.info("invoke getUserByUserID{}, url is user/id/{pkUserid}", pkUserid);

        // 检查用户名输入的是否合法
        if (!Pattern.matches("^[0-9a-z]+$", pkUserid)) {
            return Responses.errorResponse("请输入正确的用户名");
        }
        UserModel userModel = userService.getUserByPkuserID(pkUserid);
        if (userModel == null) {
            return Responses.errorResponse("系统中不存在该用户");
        }

        // 前端不需要的字段
        userModel.setUserPermit("0");
        userModel.setUserPwd("0");
        userModel.setUserRole(0);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", userModel);
        response.setData(data);
        return response;
    }

    /**
     * 增加一个用户
     * @param userRequest request
     * @param bindingResult bindingResult
     * @return response
     */
    @Permit(authorities = "add_user")
    @PostMapping("user")
    public Response addUser(@RequestBody @Valid UserRequest userRequest, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("invoke addUser{}, url is register", userRequest, bindingResult);
        Byte which = StringToLongUtil.stringToByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
        if (bindingResult.hasErrors() || userRequest.getUsername() == null) {
            Response response = Responses.errorResponse("验证失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            // 添加用户的校验信息
            if (!userService.verifyOnlyOnePkUserid(userRequest.getUsername())) {
                Response response = Responses.errorResponse("添加用户信息失败");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "用户名已经被使用过");
                response.setData(data);
                return response;
            }
            UserModel userModel = new UserModel();
            userModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            userModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            if (userRequest.getFlag() == 2) {
                userModel.setIsFactory((byte) -2);
            }
            userModel.setUserFactory(userRequest.getFactoryId());
            userModel.setIsFactory(userRequest.getFlag());

            if (userRequest.getFlag() == 0) {
                // 这表示羊场
                userModel.setUserRole(18);
                // 找到羊场， 赋值id
                FactoryModel factoryModel = factoryService.getOneFactory(userRequest.getFactoryId());
                factoryModel.setResponsiblePersonId((long)2);
                Long success = factoryService.updateFactory(factoryModel);
                if (success == null) {
                    return Responses.errorResponse("error");
                }
            } else if (userRequest.getFlag() == 1) {
                // 这个表示代理
                // 首先查询代理等级
                AgentModel agentModel = agentService.getOneAgent(userRequest.getFactoryId());
                if (agentModel == null) {
                    return Responses.errorResponse("error!");
                }
                switch (agentModel.getAgentRank()) {
                    case 0:
                        userModel.setUserRole(3);
                        break;
                    case 1:
                        userModel.setUserRole(6);
                        break;
                    case 2:
                        userModel.setUserRole(10);
                        break;
                    case 3:
                        userModel.setUserRole(14);
                        break;
                    default:
                        userModel.setUserRole(0);
                }
                AgentModel agentModel1 = agentService.getOneAgent(userRequest.getFactoryId());
                agentModel1.setResponsibleId((long)2);
                Long success = agentService.updateAgent(agentModel1);
                if (success == null) {
                    return Responses.errorResponse("error");
                }
            } else if (userRequest.getFlag() == 3) {
                userModel.setIsFactory(which);
            }

            userModel.setPkUserid(userRequest.getUsername());
            userModel.setUserPwd(userRequest.getPassword());
            userModel.setUserTelephone(userRequest.getTelephone());
            userModel.setFactoryName(userRequest.getFactoryName());
            userModel.setUserRealname(userRequest.getRealname());

            if (userModel.getUserPermit() == null || userModel.getUserPermit().equals("")) {
                userModel.setUserPermit("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            }
            userModel.setIsExtended((byte)0);
            userModel.setUserRole(0);
            Long success = userService.addUser(userModel);
            if (success <= 0) {
                return Responses.errorResponse("用户信息增加失败,请检查网络后重试");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);
            response.setData(data);
            return response;
        }
    }

    /**
     * 修改一个用户
     * @param userModel userModel
     * @param id ID
     * @param bindingResult bindingResult
     * @return response
     */
    @Permit(authorities = {"modify_user", "modify_expert", "modify_technician", "modify_administrator"})
    @PutMapping(value = "user/{id}")
    public Response modifyUser(@RequestBody @Valid UserModel userModel, @PathVariable("id") String id, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("invoke modifyUser{}, url is user/{username}", userModel, id, bindingResult);
        Response response;
        if (bindingResult.hasErrors()) {
            response = Responses.errorResponse("验证失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }

        long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("错误");
        }

        UserModel user = userService.getOneUser(uid);
        //用户名不可以更改
        userModel.setId(uid);
        userModel.setPkUserid(user.getPkUserid());
        userModel.setUserPermit(user.getUserPermit());
        userModel.setUserPwd(user.getUserPwd());
        userModel.setUserRole(user.getUserRole());
        userModel.setGmtCreate(user.getGmtCreate());
        userModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

        Long success = userService.updateUser(userModel);
        if (success <= 0) {
            return Responses.errorResponse("用户信息修改失败,请检查网络后重试");
        }
        response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("success", success);
        response.setData(data);
        System.out.println(response);
        return response;
    }

    @Permit(authorities = "modify_user")
    @PatchMapping(value = "/user/password/{id}")
    public Response modifyUserPassword(@PathVariable("id") String id, @RequestBody @Valid PasswordRequest passwordRequest) {
        long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("错误");
        } else {
            UserModel oldUser = userService.getOneUser(uid);
            if (oldUser == null) {
                return Responses.errorResponse("错误");
            }
            String oldPassword = oldUser.getUserPwd();
            if (oldPassword.equals(passwordRequest.getOldPassword())) {
                oldUser.setUserPwd(passwordRequest.getNewPassword());
                Long success = userService.updateUser(oldUser);
                if (success == null) {
                    return Responses.errorResponse("修改失败");
                }
                return Responses.successResponse();
            } else {
                return Responses.errorResponse("修改失败");
            }
        }
    }

    /**
     * 删除单个用户
     * @param id userID
     * @return response
     */
    @Permit(authorities = {"delete_users", "delete_expert", "remove_technician", "remove_administrator"})
    @DeleteMapping("user/{id}")
    public Response deleteUser(@PathVariable("id") String id, HttpServletRequest request) {
        logger.info("invoke deleteUser{}, url is user/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);

        // 首先判断所登录用户的ID是否是要删除自己
        Long which = TokenAnalysis.getUserId(request.getHeader(Constants.AUTHORIZATION));

        System.out.println("which = " + which);

        if (which == uid) {
            return Responses.errorResponse("您不能删除自己的账户");
        }

        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Long success = userService.deleteUser(uid);
        if (success <= 0) {
            return Responses.errorResponse("用户信息删除失败,请检查网络后重试");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("success", success);
        response.setData(data);
        return response;
    }

    /**
     * 导出Excel表格
     * @param httpServletResponse httpServletResponse
     * @return response
     * @throws Exception exception
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "/user/excel/{roleID}")
    public Response exportExcel(@PathVariable("roleID") long roleID, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("invoke exportExcel{}, url is /user/excel", httpServletResponse);
        ExcelData data = new ExcelData();
        data.setName("user");
        List<UserModel> userModels = userService.getAll(roleID);
        UserModel userModel;
        ArrayList<List<Object>> rows = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<String>();
        for (UserModel userModel1 : userModels) {
            ArrayList<Object> row = new ArrayList<>();
            userModel = userModel1;
            for (Field field : userModel.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                row.add(field.get(userModel));
                titles.add(field.getName());
            }
            rows.add(row);
        }
        data.setTitles(titles);
        data.setRows(rows);
        // TODO 应该继续封装模板
        ExportExcelUtil.exportExcel(httpServletResponse,"user.xlsx",data);
        return Responses.successResponse();
    }

    /**
     * 获取某一类专家接口
     * @param id id
     * @return response
     */
    @Permit(authorities = "query_expert")
    @GetMapping(value = "/user/high/{id}")
    public Response getRolesOfProfessor(@PathVariable("id") String id) {
        logger.info("invoke getRolesOfProfessor{}, url is /user/high/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("error!");
        }
        List<Professor> userModels = userService.getRoles(uid);
        if (userModels == null) {
            return Responses.errorResponse("error!");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", userModels);
        data.put("size", userModels.size());
        response.setData(data);
        return response;
    }

    /**
     * 测试类, 获取其上级专家的手机号
     * @param id id
     * @return response
     */
    @Permit(authorities = "query_expert")
    @GetMapping(value = "/user/test/{id}")
    public Response getTest(@PathVariable("id") String id) {
        logger.info("invoke getRolesOfProfessor {}, url is /user/high/{id}", id);
        Response response = Responses.successResponse();
        Map<String, Object> data = new HashMap<>();

        data.put("data", userService.getProfessorTelephoneByFactoryNum(new BigInteger(id)));

        response.setData(data);
        return response;
    }

//    /**
//     * 获取某个羊场或者某个代理的所有用户
//     * @param id
//     * @return
//     */
//    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
//    @GetMapping(value = "/user/factory/lists/{factoryAgentID}")
//    public Response getFactoryLists(@PathVariable("factoryAgentID") String id) {
//        logger.info("invoke getFactoryLists {}, url is /user/factory/lists/{factoryAgentID}", id);
//        long uid = StringToLongUtil.stringToLong(id);
//        if (uid == -1) {
//            return Responses.errorResponse("error");
//        } else {
//            Response response;
//            List<UserModel> userLists = userService.getAllUserOfFactoryOrAgent(uid);
//            if (userLists == null) {
//                return Responses.errorResponse("error");
//            } else {
//                response = Responses.successResponse();
//                Map<String, Object> data = new HashMap<>();
//                data.put("List", userLists);
//                data.put("size", userLists.size());
//                response.setData(data);
//                return response;
//            }
//        }
//    }

    /**
     * 判断专家是否在线
     * @param id id
     * @return response
     */
    @Permit(authorities = "query_expert")
    @GetMapping(value = "/user/online/{id}")
    public Response getOnline(@PathVariable("id") String id) {
        logger.info("invoke getOnline {}", id);
        if(JedisUtil.getValue(id) == null) {
            return Responses.errorResponse("not on line");
        } else {
            return Responses.successResponse();
        }
    }

    /**
     * 获取已发展羊场的直属上级所有在线的专家, 如果没有, 则返回直属上级的上级的专家
     * @param id id
     * @return 查询结果
     */
    @Permit(authorities = "query_expert")
    @GetMapping(value = "getExpert/{agent_id}")
    public Response getOnlineAncestors(@PathVariable("agent_id") String id) {
        logger.info("invoke getOnlineAncestors {}", id);
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            Professor agent = userService.getFatherProfessors(agentID);
            System.out.println(agent);
            if (agent != null) {
                Response response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("expert", agent.getPkUserid());
                data.put("expert_id", agent.getId());
                data.put("name", agent.getPkUserid());
                data.put("realName", agent.getUserRealname());
                data.put("email", agent.getUserEmail());
                data.put("phone", agent.getUserTelephone());
                data.put("type", map.get(agent.getUserRole()));
                response.setData(data);
                return response;
            }
            return Responses.successResponse();
        }
    }

    @Permit(authorities = "query_user")
    @GetMapping(value = "check/username/{username}")
    public Integer checkUsername (@PathVariable("username") String username) {
        // find all username
        List<String> allUser = userService.getAllWithNoCondition();
        if (allUser == null) {
            return 0;
        } else if (allUser.indexOf(username) == -1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Permit(authorities = "modify_user")
    @PatchMapping(value = "/user/role/{id}")
    public Response updateUserRolePermit(@RequestBody RolePermitRequest rolePermitRequest, @PathVariable("id") String id) {
        logger.info("invoke updateUserRolePermit {}", rolePermitRequest.getRole(), rolePermitRequest.getId());
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("error!");
        }

        UserModel user = userService.getOneUser(uid);
        //用户名不可以更改
        user.setId(uid);
        user.setUserRole(rolePermitRequest.getRole());
        Long success = userService.updateUser(user);
        if (success < 0) {
            return Responses.errorResponse("Error!");
        } else {
            return Responses.successResponse();
        }
    }
}
