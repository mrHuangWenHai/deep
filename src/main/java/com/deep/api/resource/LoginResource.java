package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.MobileAnnouncementModel;
import com.deep.domain.model.RoleModel;
import com.deep.domain.model.TokenModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.util.JedisUtil;
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
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class LoginResource {

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
                //key:userId value:token
                JedisUtil.setCertainKeyValueWithExpireTime("userId",tokenModel.getUserId().toString(),10*60);
                JedisUtil.setCertainKeyValueWithExpireTime("token",tokenModel.getToken(),10*60);

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
    public Response RegiterResult(@RequestParam("username") String username,
                                @RequestParam("passwordFirst") String passwordFirst,
                                @RequestParam("passwordSecond") String passwordSecond,
                                @RequestParam("name") String name,
                                @RequestParam("factoryNum") BigInteger factoryNum,
                                @RequestParam("telephone") String telephone,
                                @RequestParam("question_1") String question_1,
                                @RequestParam("answer_1") String answer_1,
                                @RequestParam("question_2") String question_2,
                                @RequestParam("answer_2") String answer_2,
                                @RequestParam("question_3") String question_3,
                                @RequestParam("answer_3") String answer_3){
        if(!passwordFirst.equals(passwordSecond)){
            ////第一次和第二次输入密码不一致
            return new Response().addData("Error","Password is different");
        }else {

            /*List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(factoryNum);
            System.out.println(userModels.get(0).getTelephone()+"长度:"+userModels.size());*/
            UserModel userModel = userService.getUserModelByusername(username);
            if (userModel == null){
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                UserModel userModel1 = new UserModel(username,md5Util.encode(passwordFirst),name,factoryNum,telephone,
                        question_1,md5Util.encode(answer_1),question_2,md5Util.encode(answer_2),
                        question_3,md5Util.encode(answer_3),timestamp);
                userService.setUserModel(userModel1);
                roleService.setRoleModel(new RoleModel("未分配权限",timestamp,timestamp));
                return new Response().addData("Success","");
            }else {
                ////用户名已存在
                return new Response().addData("Error","Username already exist");
            }
        }

    }

    @RequestMapping(value = "/findpassword")
    public String FindPassword(){
        return "LoginHTML/FindPassword";
    }

    @RequestMapping(value = "/phonefind")
    public Response PhoneFind(@RequestParam("usernameP") String usernameP){
        UserModel userModel = userService.getUserModelByusername(usernameP);
        mobileAnnouncementModel = new MobileAnnouncementModel(userModel.getTelephone());

        return JedisUtil.oneMessageSendResult(mobileAnnouncementModel);
    }

    @RequestMapping(value = "/ensureverify")
    public Response EnsureVerify(@RequestParam("verifyCode") String verifyCode){
        if(verifyCode.equals(mobileAnnouncementModel.getIdentityCode())){
            System.out.println("验证成功");
            TokenModel tokenModel = new TokenModel("Identify","Success");
            //记录找回名密码成功状态 时间：5分钟
            JedisUtil.setCertainKeyValueWithExpireTime (tokenModel.getIdentify(),tokenModel.getToken(),5*60);
            return new Response().addData("Success","Continue input new password");
        }else {
            return new Response().addData("Error","Answer wrong");
        }
    }

    @RequestMapping(value = "/questionfind")
    public ModelAndView QuestionFind(@RequestParam("usernameQ") String usernameQ){
        myuserModel = userService.getUserModelByusername(usernameQ);
        ModelAndView modelAndView = new ModelAndView("/LoginHTML/LoginQuestion");
        modelAndView.addObject("mylist",myuserModel);
        return modelAndView;
    }

    @RequestMapping(value = "/ensurequestion")
    public Response EnsureQuestion(@RequestParam("answer_1") String answer_1,
                                   @RequestParam("answer_2") String answer_2,
                                   @RequestParam("answer_3") String answer_3){
        if ( answer_1.equals(myuserModel.getAnswer_1()) &&
                answer_2.equals(myuserModel.getAnswer_2()) &&
                answer_3.equals(myuserModel.getAnswer_3())){
            TokenModel tokenModel = new TokenModel("Identify","Success");
            //记录找回名密码成功状态 时间：5分钟
            JedisUtil jedisUtil = new JedisUtil(tokenModel.getIdentify(),tokenModel.getToken(),5*60);
            return new Response().addData("Success","Continue input new password");
        }else {
            return new Response().addData("Error","Answer wrong");
        }
    }

}
