package com.deep.api.resource;

import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@RestController
public class UserResource {

    @Resource
    private UserService userService;

    /**
     * 查找所有的用户列表
     * @return 返回所有的用户信息
     */
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//    })
    @GetMapping(value = "user/")
    public Response userList() {
        List<UserModel> userLists = userService.getAll();
        if (userLists.size() <= 0) {
            return Responses.errorResponse("系统中暂时没有用户");
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
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//            "dongxiang_factory_expert", "dongxiang_factory_technician", "province_agent_expert",
//            "province_agent_technician", "city_agent_expert", "city_agent_technician",
//            "county_agent_expert", "county_agent_technician", "sheep_farm_operator",
//            "sheep_farm_supervisor", "tourist", "others"
//    })
    @GetMapping(value = "user/{id}")
    public Response getUserOne(@PathVariable("id")String id) {
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
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//            "dongxiang_factory_expert", "dongxiang_factory_technician", "province_agent_expert",
//            "province_agent_technician", "city_agent_expert", "city_agent_technician",
//            "county_agent_expert", "county_agent_technician", "sheep_farm_operator",
//            "sheep_farm_supervisor", "tourist", "others"
//    })
    @GetMapping(value = "user/detail/{id}")
    public Response getUserOneDetail(@PathVariable("id") String id) {
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
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//            "dongxiang_factory_expert", "dongxiang_factory_technician", "province_agent_expert",
//            "province_agent_technician", "city_agent_expert", "city_agent_technician",
//            "county_agent_expert", "county_agent_technician", "sheep_farm_operator",
//            "sheep_farm_supervisor", "tourist", "others"
//    })
    @GetMapping(value = "user/name/{realname}")
    public Response getUserByUserRealname(@PathVariable("realname") String realname) {
        UserModel userModel = userService.getUserByUserRealnameLike(realname);
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
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//            "dongxiang_factory_expert", "dongxiang_factory_technician", "province_agent_expert",
//            "province_agent_technician", "city_agent_expert", "city_agent_technician",
//            "county_agent_expert", "county_agent_technician", "sheep_farm_operator",
//            "sheep_farm_supervisor", "tourist", "others"
//    })
    @GetMapping(value = "user/id/{pkUserid}")
    public Response getUserByUserID(@PathVariable("pkUserid") String pkUserid) {
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
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//            "dongxiang_factory_expert", "dongxiang_factory_technician", "province_agent_expert",
//            "province_agent_technician", "city_agent_expert", "city_agent_technician",
//            "county_agent_expert", "county_agent_technician", "sheep_farm_operator",
//            "sheep_farm_supervisor", "tourist", "others"
//    })
    @PostMapping("/register")
    public Response addUser(@RequestBody @Valid UserModel userModel,  BindingResult bindingResult) {
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
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//            "dongxiang_factory_expert", "dongxiang_factory_technician", "province_agent_expert",
//            "province_agent_technician", "city_agent_expert", "city_agent_technician",
//            "county_agent_expert", "county_agent_technician", "sheep_farm_operator",
//            "sheep_farm_supervisor", "tourist", "others"
//    })
    @PutMapping(value = "user/{id}")
    public Response modifyUser(@RequestBody @Valid UserModel userModel, @PathVariable("id") String id, BindingResult bindingResult) {
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
//    @Permit(modules = {
//            "dongxiang_factory_administrator", "total_system_administrator", "province_agent_total_administrator",
//            "province_agent_administrator", "city_agent_total_administrator", "city_agent_administrator",
//            "county_agent_total_administrator", "county_agent_administrator", "sheep_farm_administrator",
//            "dongxiang_factory_expert", "dongxiang_factory_technician", "province_agent_expert",
//            "province_agent_technician", "city_agent_expert", "city_agent_technician",
//            "county_agent_expert", "county_agent_technician", "sheep_farm_operator",
//            "sheep_farm_supervisor", "tourist", "others"
//    })
    @DeleteMapping("user/{id}")
    public Response deleteUser(@PathVariable("id") String id) {
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
}
