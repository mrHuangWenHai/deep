package com.deep.api.resource;

import com.deep.api.Utils.MobileAnnouncementUtil;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.authorization.tools.RoleAndPermit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.ServiceConfiguration;
import com.deep.domain.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class LoginResource {
    @Resource
    private UserService userService;

    @Resource
    private MobileAnnouncementUtil mobileAnnouncementModel;

    @Resource
    private UserModel myuserModel;

    /**
     * 用户登录验证并且返回结果
     * @param userModelTest 用户登录加的模型
     * {
     *                      "pkUserid": "00004",
     *                      "userPwd": "123456"
     * }
     * @return0
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response LoginResult(@RequestBody UserModel userModelTest, HttpServletResponse httpServletResponse){
        String username = userModelTest.getPkUserid();
        String password = userModelTest.getUserPwd();
        UserModel userModel = userService.getUserByPkuserID(username);
        if(userModel == null){
            //数据库中未查到用户名
            Response response = Responses.errorResponse("没有找到用户名");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "username error");
            response.setData(data);
            return response;
        }else {
            // 验证密码信息, 忽略大小写
            if(userModel.getUserPwd().equalsIgnoreCase(password)){
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "登录成功!");
                response.setData(data);

                RoleAndPermit userRoleAndPermit = userService.findRoleByUserID(userModel.getId());
                Long roleInt = userRoleAndPermit.getRole();
                TokenModel tokenModel = new TokenModel(userModel.getId(), String.valueOf(roleInt));

                Jedis jedis = new Jedis(ServiceConfiguration.redisServer, ServiceConfiguration.port);
                jedis.set(String.valueOf(userModel.getId()),tokenModel.getToken());
                jedis.expire(String.valueOf(userModel.getId()),10*60);
                System.out.println("login token:" + tokenModel.getToken());
                System.out.println("login redis token" + jedis.get(String.valueOf(userModel.getId())));
                httpServletResponse.setHeader("Authorization", userModel.getId() + ":" + tokenModel.getToken());
                return response;
            }else {
                Response response = Responses.errorResponse("密码错误");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "password error");
                response.setData(data);
                return response;
            }
        }
    }

    /**
     *  通过电话号码找回并且返回相关的数据
     * @param usernameP 用户名
     *                  "usernameP": "00004"
     * @return
     */
    @RequestMapping(value = "/phonefind")
    public Response PhoneFind(@RequestParam("usernameP") String usernameP){
        UserModel userModel = userService.getUserByPkuserID(usernameP);
        if (userModel == null) {
            return Responses.errorResponse("用户不存在");
        }
        mobileAnnouncementModel = new MobileAnnouncementUtil(userModel.getUserTelephone());
        if (mobileAnnouncementModel == null){
            Response response = Responses.errorResponse("发送失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("successMessage", "验证码发送成功!");
            response.setData(data);
            return response;
        }
        String httpResponse =  mobileAnnouncementModel.testSend();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("fail", "未查找到用户名");
                response.setData(data);
                return response;
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
                Response response = Responses.errorResponse("发送消息失败");
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "Send message failed,code is "+error_code+",msg is "+error_msg);
                response.setData(data);
                return response;

            }
        } catch (JSONException ex) {
            Logger.getLogger(MobileAnnouncementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        httpResponse =  mobileAnnouncementModel.testStatus();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            if( error_code == 0 ){
                int deposit = jsonObj.getInt("deposit");
                System.out.println("Fetch deposit success :"+deposit);
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "验证码发送成功!");
                response.setData(data);
                return response;
            }else{
                String error_msg = jsonObj.getString("msg");
                System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
                Response response = Responses.errorResponse("发送消息失败");
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
                response.setData(data);
                return response;
            }
        } catch (JSONException ex) {
            Logger.getLogger(MobileAnnouncementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 验证短信验证码信息
     * @param verifyCode 验证码
     * @return
     */
    @RequestMapping(value = "/ensureverify")
    public Response EnsureVerify(@RequestParam("verifyCode") String verifyCode){
        Response response;
        if(verifyCode.equals(mobileAnnouncementModel.getIdentityCode())){
            response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "valid success");
        }else{
            response = Responses.errorResponse("valid failed!");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "something error");
        }
        return response;
    }

    @GetMapping(value = "/questionAndAnswer")
    public Response requestQuestion(@RequestParam("id") Long id) {
        myuserModel = userService.getOneUser(id);
        if (myuserModel == null) {
            return Responses.errorResponse("请求失败");
        } else {
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("answer_1", myuserModel.getQuestion_1());
            data.put("answer_2", myuserModel.getQuestion_2());
            data.put("answer_3", myuserModel.getQuestion_3());
            response.setData(data);
            return response;
        }
    }

    /**
     * 通过问题找回密码的数据验证方法
     * @return
     */
    @RequestMapping(value = "/ensurequestion")
    public Response EnsureQuestion(@RequestBody UserModel userModel){
        Response response;
        if (userModel.getAnswer_1().equals(myuserModel.getAnswer_1()) &&
                userModel.getAnswer_2().equals(myuserModel.getAnswer_2()) &&
                userModel.getAnswer_3().equals(myuserModel.getAnswer_3())){
            response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "valid success");
        }else {
            response = Responses.errorResponse("valid error");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "valid error");
        }
        return response;
    }

    /**
     * user logout for himself
     * @param id 用户名
     * @return
     */
    @DeleteMapping
    public Response logout(Long id) {
        Jedis jedis = new Jedis(ServiceConfiguration.redisServer);
        jedis.del(String.valueOf(id));
        Response response = Responses.errorResponse("logout failed!");
        HashMap<String, Object> data = new HashMap<>();
        data.put("errorMessage", "something error");
        response.setData(data);
        return response;
    }
}
