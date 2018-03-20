package com.deep.api.resource;

import com.deep.api.Utils.MD5Util;
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
import java.util.regex.Pattern;

@RestController
//@RequestMapping(value = "user")
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
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("allUser", userService.getAll());
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
    @GetMapping(value = "user/{id:\\d+}")
    public Response getUserOne(@PathVariable("id")Long id) {
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userService.findOneUser(id));
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
    @GetMapping(value = "user/detail/{id:\\d+}")
    public Response getUserOneDetail(@PathVariable("id")Long id) {
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userService.getOneUser(id));
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
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userService.getUserByUserRealnameLike(realname));
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
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userService.getUserByPkuserID(pkUserid));
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
    @PostMapping("/userAdd")
    public Response addUser(@RequestBody @Valid UserModel userModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加用户信息失败");
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

            // 待添加的用户信息
            userModel.setPkUserid(userModel.getPkUserid());

            //
            System.out.println(userModel.getUserPwd());
            // user's password validator
            if (!Pattern.matches("^[0-9a-z]+$", userModel.getUserPwd())) {
                Response response = Responses.errorResponse("log in error");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "user's password error");
                response.setData(data);
                return response;
            }
            if (userModel.getUserPwd().length() < 6 || userModel.getUserPwd().length() > 12) {
                Response response = Responses.errorResponse("log in error");

                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "user's password error");
                response.setData(data);
                return response;
            }

            userModel.setUserPwd(MD5Util.encode(userModel.getUserPwd()));

            userModel.setUserNum(userModel.getUserNum());
            userModel.setUserPic(userModel.getUserPic());
            userModel.setUserRealname(userModel.getUserRealname());
            userModel.setUserLocation(userModel.getUserLocation());
            userModel.setUserTelephone(userModel.getUserTelephone());
            userModel.setUserRemark(userModel.getUserRemark());
            userModel.setUserFactory(userModel.getUserFactory());
            userModel.setUserRole(userModel.getUserRole());
            userModel.setUserPermit(userModel.getUserPermit());
            userModel.setIsExtended(userModel.getIsExtended());

            userModel.setQuestion_1(userModel.getQuestion_1());
            userModel.setQuestion_2(userModel.getQuestion_2());
            userModel.setQuestion_3(userModel.getQuestion_3());
            userModel.setAnswer_1(MD5Util.encode(userModel.getAnswer_1()));
            userModel.setAnswer_2(MD5Util.encode(userModel.getAnswer_2()));
            userModel.setAnswer_3(MD5Util.encode(userModel.getAnswer_3()));
            // 基本信息
            userModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            userModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("success", userService.addUser(userModel));
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
    @PutMapping(value = "user/{id:\\d+}")
    public Response modifyUser(@RequestBody @Valid UserModel userModel, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("修改用户信息失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());

            response.setData(data);
            return response;
        }

        if (!userService.verifyOnlyOnePkUserid(userModel.getPkUserid())) {
            Response response = Responses.errorResponse("修改用户信息失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "用户名已经被使用过");
            response.setData(data);
            return response;
        }

        userModel.setId(id);
        //用户名不可以更改
        userModel.setPkUserid(userService.getOneUser(id).getPkUserid());
//        userModel.setPkUserid(userModel.getPkUserid());

        userModel.setUserPwd(userModel.getUserPwd());
        userModel.setUserNum(userModel.getUserNum());
        userModel.setUserPic(userModel.getUserPic());
        userModel.setUserRealname(userModel.getUserRealname());
        userModel.setUserLocation(userModel.getUserLocation());
        userModel.setUserTelephone(userModel.getUserTelephone());
        userModel.setUserRemark(userModel.getUserRemark());
        userModel.setUserFactory(userModel.getUserFactory());
        userModel.setUserRole(userModel.getUserRole());
        userModel.setUserPermit(userModel.getUserPermit());
        userModel.setIsExtended(userModel.getIsExtended());

        userModel.setQuestion_1(userModel.getQuestion_1());
        userModel.setQuestion_2(userModel.getQuestion_2());
        userModel.setQuestion_3(userModel.getQuestion_3());
        userModel.setAnswer_1(MD5Util.encode(userModel.getAnswer_1()));
        userModel.setAnswer_2(MD5Util.encode(userModel.getAnswer_2()));
        userModel.setAnswer_3(MD5Util.encode(userModel.getAnswer_3()));

        userModel.setGmtCreate(userService.getOneUser(id).getGmtCreate());
        userModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userService.updateUser(userModel));
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
    @DeleteMapping("user/{id:\\d+}")
    public Response deleteUser(@PathVariable("id") Long id) {
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", userService.deleteUser(id));
        response.setData(data);
        return response;
    }
}
