package com.deep.api.resource;

import com.deep.api.Utils.ExcelData;
import com.deep.api.Utils.ExportExcelUtil;
import com.deep.api.Utils.JedisUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.token.TokenManagerRealization;
import com.deep.api.request.PasswordRequest;
import com.deep.api.response.Professor;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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
    private TokenManagerRealization tokenManagerRealization;

    /**
     * 查找下级的所有用户列表
     * @return 返回所有的用户信息
     */
    @Permit(authorities = "query_user")
//    @GetMapping(value = "user/subordinate/{roleID}")
    @GetMapping(value = "user")
//    @PathVariable("roleID") long roleID
    public Response userList() {
        logger.info("invoke userList, url is user/");
//        List<UserModel> userLists = userService.getAll(roleID);
        List<UserModel> userLists = userService.getAllWithNoCondition();
        if (userLists == null) {
                return Responses.errorResponse("系统中暂时没有下级用户");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List", userLists);
        data.put("size", userLists.size());
        response.setData(data);
        return response;
    }

    /**
     * 通过用户的主键查找单个用户
     * @param id 获取用户的信息(简略信息)
     * @return 查询结果
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "user/{id}")
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
     * @return 查询结果
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "user/detail/{username}")
    public Response getUserOneDetail(@PathVariable("username") String id) {
        logger.info("invoke getUserOneDetail{}, url is user/detail/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("错误");
        }
        UserModel userModel = userService.getOneUser(uid);
        if (userModel == null) {
            return Responses.errorResponse("系统中该用户不存在");
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
     * 根据用户的真实姓名查找单个用户
     * @param realName 真实姓名
     * @return 查询结果
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @PostMapping(value = "user/name")
    public Response getUserByUserRealName(@RequestBody Map<String, String> realName) {
        logger.info("invoke getUserByUserRealName{}, url is user/name", realName);
        System.out.println(realName.get("realName"));
        UserModel userModel = userService.getUserByUserRealnameLike(realName.get("realName"));
        if ("".equals(realName)) {
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
     * @param pkUserId userName
     * @return 查询信息
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "user/id/{pkUserId}")
    public Response getUserByUserID(@PathVariable("pkUserId") String pkUserId) {

        logger.info("invoke getUserByUserID{}, url is user/id/{pkUserId}", pkUserId);

        // 检查用户名输入的是否合法
        if (!Pattern.matches("^[0-9a-z]+$", pkUserId)) {
            return Responses.errorResponse("请输入正确的用户名");
        }
        UserModel userModel = userService.getUserByPkuserID(pkUserId);
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
     * @param userModel 用户类
     * @param bindingResult  异常类型
     * @return 插入结果
     */
    @PostMapping("user/add")
    public Response addUser(@RequestBody @Valid UserModel userModel,  BindingResult bindingResult) {
        logger.info("invoke addUser{}, url is register", userModel, bindingResult);

        if (bindingResult.hasErrors() || userModel.getPkUserid() == null) {
            Response response = Responses.errorResponse("验证失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            // 添加用户的校验信息
            if (!userService.verifyOnlyOnePkUserid(userModel.getPkUserid())) {
                Response response = Responses.errorResponse("添加用户信息失败");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "用户名已经被使用过");
                response.setData(data);
                return response;
            }
            userModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            userModel.setGmtModified(new Timestamp(System.currentTimeMillis()));



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
     * @param userModel 用户类
     * @param id id
     * @param bindingResult 异常类型
     * @return 修改结果
     */
    @Permit(authorities = {"modify_user", "modify_expert", "modify_technician", "modify_administrator"})
    @PutMapping(value = "user/{id}")
    public Response modifyUser(@RequestBody @Valid UserModel userModel, @PathVariable("id") String id, BindingResult bindingResult) {
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
        return response;
    }

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
     * @param id 用户ID
     * @return 删除结果
     */
    @Permit(authorities = {"delete_users", "delete_expert", "remove_technician", "remove_administrator"})
    @DeleteMapping("user/{id}")
    public Response deleteUser(@PathVariable("id") String id) {
        logger.info("invoke deleteUser{}, url is user/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
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
     * @param httpServletResponse  HttpServletResponse
     * @return 到处结果
     * @throws Exception 异常
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "/user/excel/{roleID}")

    public Response exportExcel(@PathVariable("roleID") long roleID, HttpServletResponse httpServletResponse) throws Exception {

        logger.info("invoke exportExcel{}, url is /user/excel", httpServletResponse);
        ExcelData data = new ExcelData();
        data.setName("user");
        List<UserModel> userModels = userService.getAll(roleID);
        UserModel userModel;
        List<List<Object>> rows = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (UserModel userModel1 : userModels) {
            List<Object> row = new ArrayList<>();
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
     * @param id 专家
     * @return 查询结果
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
     * @param id  id
     * @return 手机号
     */
    @Permit(authorities = "query_expert")
    @GetMapping(value = "/user/test/{id}")
    public Response getTest(@PathVariable("id") String id) {
        logger.info("invoke getRolesOfProfessor {}, url is /user/high/{id}", id);
        Response response = Responses.successResponse();
        Map data = new HashMap<>();

        data.put("data", userService.getProfessorTelephoneByFactoryNum(new BigInteger(id)));

        response.setData(data);
        return response;
    }

    /**
     * 获取某个羊场或者某个代理的所有用户
     * @param id id
     * @return 查询结果
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "/user/factory/lists/{factoryAgentID}")
    public Response getFactoryLists(@PathVariable("factoryAgentID") String id) {
        logger.info("invoke getFactoryLists {}, url is /user/factory/lists/{factoryAgentID}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("error");
        } else {
            Response response;
            List<UserModel> userLists = userService.getAllUserOfFactoryOrAgent(uid);
            if (userLists == null) {
                return Responses.errorResponse("error");
            } else {
                response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("List", userLists);
                data.put("size", userLists.size());
                response.setData(data);
                return response;
            }
        }
    }

    /**
     * 判断专家是否在线
     * @param id id
     * @return 是否在线
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
        logger.info("invoke getAncestors {}", id);
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            Professor agent = userService.getFatherProfessors(agentID);
            if (agent != null) {
                Response response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("expert", agent.getPkUserid());
                data.put("expert_id", agent.getId());
                response.setData(data);
                return response;
            }
            return Responses.errorResponse("there is no online father agents");
        }
    }
}
