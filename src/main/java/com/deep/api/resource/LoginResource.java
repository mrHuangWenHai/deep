package com.deep.api.resource;

import com.deep.api.Utils.MD5Util;
import com.deep.api.Utils.MobileAnnouncementUtil;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.RoleService;
import com.deep.domain.service.ServiceConfiguration;
import com.deep.domain.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class LoginResource {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MobileAnnouncementUtil mobileAnnouncementModel;

    @Resource
    private UserModel myuserModel;

    /**
     * 用户登录验证并且返回结果
     * @param userModelTest 用户登录加的模型
     * @return0
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response LoginResult(@RequestBody UserModel userModelTest){
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
     * @return
     */
    @RequestMapping(value = "/phonefind")
    public Response PhoneFind(@RequestParam("usernameP") String usernameP){
        UserModel userModel = userService.getUserByPkuserID(usernameP);
        mobileAnnouncementModel = new MobileAnnouncementUtil(userModel.getUserTelephone());

        String httpResponse =  mobileAnnouncementModel.testSend();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "验证码发送成功!");
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
        if(verifyCode.equals(mobileAnnouncementModel.getIdentityCode())){
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "valid success");
        }else{
            Response response = Responses.errorResponse("logout failed!");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "something error");
        }
        return null;
    }

    /**
     * 通过问题找回密码的数据验证方法
     * @param answer_1  答案一
     * @param answer_2  答案二
     * @param answer_3  答案三
     * @return
     */
    @RequestMapping(value = "/ensurequestion")
    public Response EnsureQuestion(@RequestParam("answer_1") String answer_1,
                                 @RequestParam("answer_2") String answer_2,
                                 @RequestParam("answer_3") String answer_3){
        if ( MD5Util.encode(answer_1).equals(myuserModel.getAnswer_1()) &&
                MD5Util.encode(answer_2).equals(myuserModel.getAnswer_2()) &&
                MD5Util.encode(answer_3).equals(myuserModel.getAnswer_3())){
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "valid success");
        }else {
            Response response = Responses.errorResponse("valid error");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "valid error");
        }
        return null;
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
