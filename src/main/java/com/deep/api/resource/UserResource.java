package com.deep.api.resource;

import com.deep.api.Utils.ExcelData;
import com.deep.api.Utils.ExportExcelUtil;
import com.deep.api.Utils.JedisUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.authorization.annotation.Permit;
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

    /**
     * 查找下级的所有用户列表
     * @return 返回所有的用户信息
     */
    @Permit(authorities = "query_user")
    @GetMapping(value = "user/subordinate/{roleID}")
    public Response userList(@PathVariable("roleID") long roleID) {
        logger.info("invoke userList, url is user/");
        List<UserModel> userLists = userService.getAll(roleID);
        if (userLists == null) {
            return Responses.errorResponse("系统中暂时没有下级用户");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("allUser", userLists);
        data.put("userNumber", userLists.size());
        response.setData(data);
        return response;
    }

    /**
     * 通过用户的主键查找单个用户
     * @param id 获取用户的信息(简略信息)
     * @return
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
        data.put("oneUser", userModel);
        response.setData(data);
        return response;
    }

    /**
     * 通过用户的主键查找单个用户
     * @param id 获取用户的信息(简略信息)
     * @return
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "user/detail/{id}")
    public Response getUserOneDetail(@PathVariable("id") String id) {
        logger.info("invoke getUserOneDetail{}, url is user/detail/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        UserModel userModel = userService.getOneUser(uid);
        if (userModel == null) {
            return Responses.errorResponse("系统中该用户不存在");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userModel);
        response.setData(data);
        return response;
    }

    /**
     * 根据用户的真实姓名查找单个用户
     * @param realname
     * @return
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @PostMapping(value = "user/name")
    public Response getUserByUserRealname(@RequestBody Map<String, String> realname) {
        logger.info("invoke getUserByUserRealname{}, url is user/name", realname);
        System.out.println(realname.get("realname"));
        UserModel userModel = userService.getUserByUserRealnameLike(realname.get("realname"));
        if (realname.equals("")) {
            return Responses.errorResponse("用户名格式错误");
        }
        if (userModel == null) {
            return Responses.errorResponse("用户不存在");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userModel);
        response.setData(data);
        return response;
    }

    /**
     * 根据用户名获取单个用户的信息
     * @param pkUserid
     * @return
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
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userModel);
        response.setData(data);
        return response;
    }

    /**
     * 增加一个用户
     * @param userModel
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public Response addUser(@RequestBody @Valid UserModel userModel,  BindingResult bindingResult) {
        logger.info("invoke addUser{}, url is register", userModel, bindingResult);

        if (bindingResult.hasErrors()) {
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

            userModel.setIsFactory((byte)0);
            if (userModel.getUserPermit().equals("")) {
                userModel.setUserPermit("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            }
            userModel.setIsExtended((byte)0);
            userModel.setUserRole(0);

            Long addID = userService.addUser(userModel);
            if (addID <= 0) {
                return Responses.errorResponse("用户信息增加失败,请检查网络后重试");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("addID", addID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 修改一个用户
     * @param userModel
     * @param id
     * @param bindingResult
     * @return
     */
    @Permit(authorities = {"modify_user", "modify_expert", "modify_technician", "modify_administrator"})
    @PutMapping(value = "user/{id}")
    public Response modifyUser(@RequestBody @Valid UserModel userModel, @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke modifyUser{}, url is user/{id}", userModel, id, bindingResult);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Response response;
        if (bindingResult.hasErrors()) {
            response = Responses.errorResponse("验证失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        userModel.setId(uid);
        UserModel user = userService.getOneUser(uid);
        //用户名不可以更改
        userModel.setPkUserid(user.getPkUserid());
        userModel.setGmtCreate(user.getGmtCreate());
        userModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
        Long updateID = userService.updateUser(userModel);
        if (updateID <= 0) {
            return Responses.errorResponse("用户信息修改失败,请检查网络后重试");
        }
        response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("updateID", updateID);
        response.setData(data);
        return response;
    }

    /**
     * 删除单个用户
     * @param id
     * @return
     */
    @Permit(authorities = {"delete_users", "delete_expert", "remove_technician", "remove_administrator"})
    @DeleteMapping("user/{id}")
    public Response deleteUser(@PathVariable("id") String id) {
        logger.info("invoke deleteUser{}, url is user/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Long deleteId = userService.deleteUser(uid);
        if (deleteId <= 0) {
            return Responses.errorResponse("用户信息删除失败,请检查网络后重试");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deleteID", deleteId);
        response.setData(data);
        return response;
    }

    /**
     * 导出Excel表格
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @Permit(authorities = {"query_user", "query_expert", "query_technician", "query_administrator"})
    @GetMapping(value = "/user/excel/{roleID}")
    public Response exportExcel(@PathVariable("roleID") long roleID, HttpServletResponse httpServletResponse) throws Exception{
        logger.info("invoke exportExcel{}, url is /user/excel", httpServletResponse);
        ExcelData data = new ExcelData();
        data.setName("user");
        List<UserModel> userModels = userService.getAll(roleID);
        UserModel userModel;
        List<List<Object>> rows = new ArrayList();
        List<String> titles = new ArrayList();
        for(int i = 0 ; i < userModels.size(); i++) {
            List<Object> row = new ArrayList();
            userModel = userModels.get(i);
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
     * @param id
     * @return
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
     * @param id
     * @return
     */
    @Permit(authorities = "query_expert")
    @GetMapping(value = "/user/test/{id}")
    public Response getTest(@PathVariable("id") String id) {
        logger.info("invoke getRolesOfProfessor {}, url is /user/high/{id}", id);
        Response response = Responses.successResponse();
        Map data = new HashMap<>();
        data.put("data", userService.getUserTelephoneByfactoryNum(new BigInteger(id)));
        response.setData(data);
        return response;
    }

    /**
     * 获取某个羊场或者某个代理的所有用户
     * @param id
     * @return
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
                data.put("data", userLists);
                data.put("size", userLists.size());
                response.setData(data);
                return response;
            }
        }
    }

    /**
     * 判断专家是否在线
     * @param id
     * @return
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
     * @param id
     * @return
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
