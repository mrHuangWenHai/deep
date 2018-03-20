package com.deep.api.resource;

import com.deep.api.Utils.MD5Util;
import com.deep.api.Utils.MobileAnnouncementUtil;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.RoleService;
import com.deep.domain.service.ServiceConfiguration;
import com.deep.domain.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
     * 返回登录页面
     * @return 登录页面的地址
     */
//    @RequestMapping(value = "/login")
//    public String Login(){
//        //System.out.println("loginform");
//        return "LoginHTML/Login";
//    }

    /**
     * 用户登录验证并且返回结果
     * @param userModelTest
     * @return
     */
    @RequestMapping(value = "/loginresult", method = RequestMethod.POST)
    public Response LoginResult(@RequestBody UserModel userModelTest){
        String username = userModelTest.getPkUserid();
        String password = userModelTest.getUserPwd();
        //System.out.println(username);
        //System.out.println(password);
        UserModel userModel = userService.getUserByPkuserID(username);
        if(userModel == null){
            //数据库中未查到用户名
            Response response = Responses.errorResponse("没有找到用户名");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "username error");
            response.setData(data);
            return response;
        }else {
            // 验证密码信息
            if(userModel.getUserPwd().equals(MD5Util.encode(password))){
                // 用户表当中的主键
                TokenModel tokenModel = new TokenModel(userModel.getId());

                //tokenModel存入redis
                //10分钟后过期 需要重新登陆
                // todo 服务器上Redis数据库的地址
                Jedis jedis = new Jedis(ServiceConfiguration.redisServer);
                jedis.set(String.valueOf(userModel.getId()),tokenModel.getToken());
                jedis.expire(String.valueOf(userModel.getId()),10*60);
                //System.out.println("in login"+" userId: "+tokenModel.getUserId()+"  token: "+tokenModel.getToken());
                //System.out.println(md5Util.encode(password));

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
     * 返回注册的页面(后端数据不需要)
     * @return
     */
//    @RequestMapping(value = "/register")
//    public String Register(){
//        return "LoginHTML/Register";
//    }

    /**
     * 返回相关的注册信息, 替换成UserResource中的addUser方法
     * @param username
     * @param passwordFirst
     * @param passwordSecond
     * @param name
     * @param telephone
     * @param question_1
     * @param answer_1
     * @param question_2
     * @param answer_2
     * @param question_3
     * @param answer_3
     * @return
     */
//    @RequestMapping(value = "/registerresult",method = RequestMethod.POST)
//    public String RegiterResult(@RequestParam("username") String username,
//                                @RequestParam("passwordFirst") String passwordFirst,
//                                @RequestParam("passwordSecond") String passwordSecond,
//                                @RequestParam("name") String name,
//                                @RequestParam("telephone") String telephone,
//                                @RequestParam("question_1") String question_1,
//                                @RequestParam("answer_1") String answer_1,
//                                @RequestParam("question_2") String question_2,
//                                @RequestParam("answer_2") String answer_2,
//                                @RequestParam("question_3") String question_3,
//                                @RequestParam("answer_3") String answer_3){
//        if(!passwordFirst.equals(passwordSecond)){
//            ////第一次和第二次输入密码不一致
//            return null;
//        }else {
//            // 查询数据库信息判断是否已经进行注册过
//            UserModel userModel = userService.getUserByPkuserID(username);
//            if (userModel == null){
//                // 该用户没有进行注册过
//                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                UserModel userModel1 = new UserModel(username,md5Util.encode(passwordFirst),name,telephone,
//                        question_1,md5Util.encode(answer_1),question_2,md5Util.encode(answer_2),
//                        question_3,md5Util.encode(answer_3),timestamp);
//                userService.setUserModel(userModel1);
//                roleService.setRoleModel(new RoleModel(userModel1.getUserId(),"未分配权限",timestamp,timestamp));
//                return "LoginHTML/Login";
//            }else {
//                ////用户名已存在
//                return null;
//            }
//        }
//
//    }

    /**
     * 显示找回密码的页面
     * @return
     */
//    @RequestMapping(value = "/findpassword")
//    public String FindPassword(){
//        return "LoginHTML/FindPassword";
//    }

    /**
     *  通过电话号码找回并且返回相关的数据
     * @param usernameP
     * @return
     */
    @RequestMapping(value = "/phonefind")
    public String PhoneFind(@RequestParam("usernameP") String usernameP){
        UserModel userModel = userService.getUserByPkuserID(usernameP);
        mobileAnnouncementModel = new MobileAnnouncementUtil(userModel.getUserTelephone());

        String httpResponse =  mobileAnnouncementModel.testSend();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
                return "LoginHTML/LoginVerifyCode";
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
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
            }else{
                String error_msg = jsonObj.getString("msg");
                System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
            Logger.getLogger(MobileAnnouncementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Send Error");
        //发送失败
        return null;
    }

    /**
     * 验证短信验证码信息
     * @param verifyCode
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
     * 返回找到问题的界面
     * @param usernameQ
     * @return
     */
//    @RequestMapping(value = "/questionfind")
//    public ModelAndView QuestionFind(@RequestParam("usernameQ") String usernameQ){
//        myuserModel = userService.getUserModelByusername(usernameQ);
//        ModelAndView modelAndView = new ModelAndView("/LoginHTML/LoginQuestion");
//        modelAndView.addObject("mylist",myuserModel);
//        return modelAndView;
//    }

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
     * @param id
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
