package com.deep.api.resource;


import com.deep.api.Utils.JedisUtil;
import com.deep.api.Utils.MobileAnnouncementUtil;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.request.LoginRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.AgentModel;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.MobileAnnouncementModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.AgentService;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.RoleService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JudgeUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class LoginResource {


    private final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginResource.class);

    @Resource
    private UserService userService;

    @Resource
    private FactoryService factoryService;

    @Resource
    private AgentService agentService;

    @Resource
    private RoleService roleService;


    /**
     * 用户登录验证并且返回结果
     * @param loginRequest 用户登录加的模型
     * @return0
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response LoginResult(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        logger.info("invoke LoginResult{}, url is /login", loginRequest, httpServletResponse);
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        UserModel userModel = userService.getUserByPkuserID(username);
        if(userModel == null) {
            //数据库中未查到用户名
            Response response = Responses.errorResponse("用户名或者密码错误");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "error");
            response.setData(data);
            return response;
        }else {
            // 验证密码信息, 忽略大小写
            if(userModel.getUserPwd().equalsIgnoreCase(password)){
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "登录成功!");
                data.put("id", userModel.getId());
                data.put("role_id", userModel.getUserRole());
                if (userModel.getIsFactory() == 0) {
                    // 如果是羊场
                    FactoryModel factoryModel = factoryService.getOneFactory(userModel.getUserFactory());
                    data.put("factory_id", userModel.getUserFactory());
                    data.put("agent_id", factoryModel.getAgent());
                }else if (userModel.getIsFactory() == 1) {
                    // 如果是代理
                    AgentModel agentModel = agentService.getOneAgent(userModel.getUserFactory());
                    data.put("agent_id", userModel.getUserFactory());
                    data.put("agent_father_id", agentModel.getAgentFather());
                } else {

                }
                response.setData(data);
                Long roleInt = userModel.getUserRole();
                String defaultPermit = roleService.findRoleDefaultPermits(userModel.getUserRole());
                defaultPermit =  roleService.findExtendPermit(defaultPermit, userModel.getUserPermit());

                TokenModel tokenModel = new TokenModel(userModel.getId(), String.valueOf(roleInt));
                JedisUtil.setValue(String.valueOf(userModel.getId()), tokenModel.getToken());
                JedisUtil.doExpire(String.valueOf(userModel.getId()));

                JedisUtil.setValue("defaultPermit" + userModel.getId(), defaultPermit);
                JedisUtil.doExpire("defaultPermit" + userModel.getId());

                httpServletResponse.setHeader("Authorization", userModel.getId() + ":" + tokenModel.getToken());
                return response;
            }else {
                Response response = Responses.errorResponse("用户名或者密码错误");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "error");
                response.setData(data);
                return response;
            }
        }
    }

    /**
     *  通过电话号码找回并且返回相关的数据
     * @param username 用户名
     * @return 返回发送结果
     */
    @RequestMapping(value = "/pfind" , method = RequestMethod.GET)
    public Response PhoneFind(@RequestParam("username") String username){
        logger.info("invoke PhoneFind {}, url is /phonefind", username);
        UserModel userModel = userService.getUserByPkuserID(username);
        if (userModel == null) {
            return Responses.errorResponse("用户不存在");
        } else {
            MobileAnnouncementModel mobileAnnouncementModel = new MobileAnnouncementModel(userModel.getUserTelephone());
            if (com.deep.domain.util.JedisUtil.oneMessageSendResult(mobileAnnouncementModel)){
                //发送后 将验证码存入redis
                //有效期为10分钟
                JedisUtil.setValue(userModel.getPkUserid()+userModel.getUserTelephone(), mobileAnnouncementModel.getIdentityCode());
                JedisUtil.doExpire(userModel.getPkUserid()+userModel.getUserTelephone());
                return JudgeUtil.JudgeSuccess("Send","Success");
            }else {
                return Responses.errorResponse("Send Error");
            }
        }


    }

    /**
     * 需要用户username
     * 需要新密码
     * 验证短信验证码信息
     * @param verifyCode 验证码
     * @return 返回验证结果
     */
    @GetMapping(value = "/pverify/{verifyCode}")
    public Response EnsureVerify(@PathVariable("verifyCode") String verifyCode, UserModel userModel){
        logger.info("invoke EnsureVerify{}, url is /ensureverify/{vefiyCode}", verifyCode);
        if (verifyCode == null || userModel == null || userModel.getUserTelephone().equals("") || userModel.getPkUserid().equals("")) {
            return Responses.errorResponse("error!");
        }
        Response response;
        if(verifyCode.equals(JedisUtil.getValue(userModel.getPkUserid()+userModel.getUserTelephone()))) {


            UserModel user = this.userService.getUserByPkuserID(userModel.getPkUserid());
            user.setUserPwd(userModel.getUserPwd());
            Long updateID = userService.updateUser(user);
            if (updateID <= 0){
                return Responses.errorResponse("update wrong");
            } else {
                response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "valid success");
                response.setData(data);
            }
        }else{
            response = Responses.errorResponse("valid failed!");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "something error");
            response.setData(data);
        }
        return response;
    }

    /**
     * 返回注册时的三个问题
     * @param username 用户名
     * @return 三个问题
     */
    @GetMapping(value = "/qfind")
    public Response requestQuestion(@RequestParam("username") String username) {
        logger.info("invoke requestQuestion{}, url is requestQuestion", username);
        if (username == null) {
            return Responses.errorResponse("error!");
        }
        UserModel userModel = userService.getUserByPkuserID(username);
        if (userModel == null) {
            return Responses.errorResponse("请求失败");
        } else {
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("question_1", userModel.getQuestion_1());
            data.put("question_2", userModel.getQuestion_2());
            data.put("question_3", userModel.getQuestion_3());
            response.setData(data);
            return response;
        }
    }

    /**
     * 新密码
     * 三个问题答案
     * 通过问题找回密码的数据验证方法
     * @param userModel 问题答案
     * @return 修改结果
     */
    @PostMapping(value = "/qverify")
    public Response EnsureQuestion(@RequestBody UserModel userModel) {
        logger.info("invoke ensureQuestion{}, url is /ensurequestion", userModel);
        if (userModel == null) {
            return Responses.errorResponse("error!");
        }
        String username = userModel.getPkUserid();
        UserModel user = userService.getUserByPkuserID(username);
        if (user == null || userModel.getAnswer_3().equals("") || userModel.getAnswer_2().equals("") || userModel.getAnswer_1().equals("")) {
            return Responses.errorResponse("error");
        }
        if (userModel.getAnswer_1().equals(user.getAnswer_1()) &&
                userModel.getAnswer_2().equals(user.getAnswer_2()) &&
                    userModel.getAnswer_3().equals(user.getAnswer_3())) {
            user.setUserPwd(userModel.getUserPwd());
            Long updateID = userService.updateUser(user);
            if (updateID <= 0) {
                return Responses.errorResponse("修改失败!");
            } else {
                return Responses.successResponse();
            }
        }
        return Responses.errorResponse("问题答案错误!");
    }

    /**
     * 用户登出
     * @param id 用户名
     * @return 登出结果
     */
    @GetMapping(value = "/logout/{id}")
    public Response logout(@PathVariable("id") String id) {
        logger.info("invoke logout{}, url is /logout/{id}", id);
        if (id == null) {
            return Responses.errorResponse("error!");
        }
        if (!JedisUtil.doDelete(id)) {
            return Responses.errorResponse("退出登录失败, 请检查网络之后重试");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("successMessage", "退出成功!");
        response.setData(data);
        return response;
    }
}
