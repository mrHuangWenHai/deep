package com.deep.api.resource;

import com.deep.domain.model.MobileAnnouncementModel;
import com.deep.domain.model.RoleModel;
import com.deep.domain.model.TokenModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.util.MD5Util;
import com.deep.domain.service.RoleService;
import com.deep.domain.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class LoginResourceController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MD5Util md5Util;

    private MobileAnnouncementModel mobileAnnouncementModel;

    private UserModel myuserModel;

    @RequestMapping(value = "/login")
    public String Login(){
        //System.out.println("loginform");
        return "LoginHTML/Login";
    }

    @RequestMapping(value = "/loginresult", method = RequestMethod.POST)
    public String LoginResult(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        //System.out.println(username);
        //System.out.println(password);
        UserModel userModel = userService.getUserModelByusername(username);
        if(userModel == null){
            //////数据库中未查到用户名
            System.out.println("no username");
            return null;
        }else {
            if(userModel.getPassword().equals(md5Util.encode(password))){
                TokenModel tokenModel = new TokenModel(userModel.getUserId());

                /*System.out.println("运行到该模块：");
                System.out.println(username);
                System.out.println(password);*/
                //tokenModel存入redis
                //10分钟后过期 需要重新登陆
                Jedis jedis = new Jedis("localhost");
                //key:userId value:token
                jedis.set("userId",tokenModel.getUserId().toString());
                jedis.expire(tokenModel.getUserId().toString(),10*60);
                jedis.set("token",tokenModel.getToken());
                jedis.expire(tokenModel.getToken(),10*60);
                //System.out.println("in login"+" userId: "+tokenModel.getUserId()+"  token: "+tokenModel.getToken());
                //System.out.println(md5Util.encode(password));
                return "AllFunctionChoiceForm";
            }else {
                System.out.println("wrong password");
                return null;
            }

        }
    }

    @RequestMapping(value = "/register")
    public String Register(){
        return "LoginHTML/Register";
    }

    @RequestMapping(value = "/registerresult",method = RequestMethod.POST)
    public String RegiterResult(@RequestParam("username") String username,
                                @RequestParam("passwordFirst") String passwordFirst,
                                @RequestParam("passwordSecond") String passwordSecond,
                                @RequestParam("name") String name,
                                @RequestParam("telephone") String telephone,
                                @RequestParam("question_1") String question_1,
                                @RequestParam("answer_1") String answer_1,
                                @RequestParam("question_2") String question_2,
                                @RequestParam("answer_2") String answer_2,
                                @RequestParam("question_3") String question_3,
                                @RequestParam("answer_3") String answer_3){
        if(!passwordFirst.equals(passwordSecond)){
            ////第一次和第二次输入密码不一致
            return null;
        }else {
            UserModel userModel = userService.getUserModelByusername(username);
            if (userModel == null){
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                UserModel userModel1 = new UserModel(username,md5Util.encode(passwordFirst),name,telephone,
                        question_1,md5Util.encode(answer_1),question_2,md5Util.encode(answer_2),
                        question_3,md5Util.encode(answer_3),timestamp);
                userService.setUserModel(userModel1);
                roleService.setRoleModel(new RoleModel(userModel1.getUserId(),"未分配权限",timestamp,timestamp));
                return "LoginHTML/Login";
            }else {
                ////用户名已存在
                return null;
            }
        }

    }

    @RequestMapping(value = "/findpassword")
    public String FindPassword(){
        return "LoginHTML/FindPassword";
    }

    @RequestMapping(value = "/phonefind")
    public String PhoneFind(@RequestParam("usernameP") String usernameP){
        UserModel userModel = userService.getUserModelByusername(usernameP);
        mobileAnnouncementModel = new MobileAnnouncementModel(userModel.getTelephone());

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
            Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Send Error");
        //发送失败
        return null;
    }

    @RequestMapping(value = "/ensureverify")
    public String EnsureVerify(@RequestParam("verifyCode") String verifyCode){
        if(verifyCode.equals(mobileAnnouncementModel.getIdentityCode())){
            System.out.println("验证成功");
        }else{
            System.out.println("验证失败");
        }
        return null;
    }

    @RequestMapping(value = "/questionfind")
    public ModelAndView QuestionFind(@RequestParam("usernameQ") String usernameQ){
        myuserModel = userService.getUserModelByusername(usernameQ);
        ModelAndView modelAndView = new ModelAndView("/LoginHTML/LoginQuestion");
        modelAndView.addObject("mylist",myuserModel);
        return modelAndView;
    }

    @RequestMapping(value = "/ensurequestion")
    public String EnsureQuestion(@RequestParam("answer_1") String answer_1,
                                 @RequestParam("answer_2") String answer_2,
                                 @RequestParam("answer_3") String answer_3){
        if ( md5Util.encode(answer_1).equals(myuserModel.getAnswer_1()) &&
                md5Util.encode(answer_2).equals(myuserModel.getAnswer_2()) &&
                md5Util.encode(answer_3).equals(myuserModel.getAnswer_3())){
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }
        return null;
    }

}
