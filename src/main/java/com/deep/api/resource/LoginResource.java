package com.deep.api.resource;


import com.deep.api.Utils.JedisUtil;
import com.deep.api.Utils.MobileAnnouncementUtil;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.request.LoginRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.AgentModel;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.AgentService;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.RoleService;
import com.deep.domain.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    @Resource
    private MobileAnnouncementUtil mobileAnnouncementModel;

    @Resource
    private UserModel myuserModel;


    /**
     * 用户登录验证并且返回结果, 登录有效期为3600s, 1小时
     * @param loginRequest 用户登录加的模型
     * @return response
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response LoginResult(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        logger.info("invoke LoginResult{}, url is /login", loginRequest);
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
        } else {
            // 验证密码信息, 忽略CASE OR case
            if (userModel.getUserPwd().equalsIgnoreCase(password)) {
                System.out.println(userModel);
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("successMessage", "登录成功!");
                data.put("id", userModel.getId());
                data.put("username", userModel.getPkUserid());
                data.put("roleId", userModel.getUserRole());
              if (userModel.getIsFactory() == 0) {
                  data.put("flag", 0);
                // 如果是羊场
                FactoryModel factoryModel = factoryService.getOneFactory(userModel.getUserFactory());
                data.put("factoryId", userModel.getUserFactory());
                data.put("agentId", factoryModel.getAgent());
                data.put("departmentName", factoryModel.getBreedName());
                // 如果是羊场, 代理等级默认为-1
                data.put("agentRank", -1);
              } else if (userModel.getIsFactory() == 1) {
                  data.put("flag", 1);
                  // 如果是代理
                  AgentModel agentModel = agentService.getOneAgent(userModel.getUserFactory());
//                data.put("agent_id", userModel.getUserFactory());
                  data.put("factoryId", userModel.getUserFactory());
                  data.put("agentId", agentModel.getAgentFather());
                  data.put("departmentName", agentModel.getAgentName());
                  data.put("agentRank", agentModel.getAgentRank());
//                  data.put("agent_father_id", agentModel.getAgentFather());
              } else {
                  data.put("flag", 2);
                  data.put("factoryId", null);
                  data.put("agentId", null);
                  data.put("departmentName", null);
                  data.put("agentRank", null);
              }
              response.setData(data);
              Long roleInt = userModel.getUserRole();
              String defaultPermit = roleService.findRoleDefaultPermits(userModel.getUserRole());

              defaultPermit = roleService.findExtendPermit(defaultPermit, userModel.getUserPermit());

              TokenModel tokenModel = new TokenModel(userModel.getId(), String.valueOf(roleInt), String.valueOf(userModel.getIsFactory()));
              logger.info("login is success token = {}",tokenModel);

              JedisUtil.setValue(String.valueOf(userModel.getId()), tokenModel.getToken());
                //    JedisUtil.doExpire(String.valueOf(userModel.getId()));
              JedisUtil.setValue("defaultPermit" + userModel.getId(), defaultPermit);

          //    JedisUtil.doExpire("defaultPermit" + userModel.getId());

              httpServletResponse.setHeader("Authorization", userModel.getId() + ":" + tokenModel.getToken());
              return response;
            } else {
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

     * @param usernameP 用户名
     * @return response
     */
    @RequestMapping(value = "/phonefind")
    public Response PhoneFind(@RequestParam("usernameP") String usernameP){
        logger.info("invoke PhoneFind {}, url is /phonefind", usernameP);
        UserModel userModel = userService.getUserByPkuserID(usernameP);
        if (userModel == null) {
            return Responses.errorResponse("用户不存在");
        }
        mobileAnnouncementModel = new MobileAnnouncementUtil(userModel.getUserTelephone());
        String httpResponse =  mobileAnnouncementModel.testSend();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if (error_code == 0) {
                System.out.println("Send message success.");
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("fail", "未查找到用户名");
                response.setData(data);
                return response;
            } else {
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
                Response response = Responses.errorResponse("发送消息失败");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "Send message failed,code is "+error_code+",msg is "+error_msg);
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
                data.put("phone", userModel.getUserTelephone());
                response.setData(data);
                JedisUtil.setValue(usernameP+userModel.getUserTelephone(),mobileAnnouncementModel.getIdentityCode());
                JedisUtil.doExpire(usernameP+userModel.getUserTelephone());
                return response;
            }else{
                String error_msg = jsonObj.getString("msg");
                System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
                Response response = Responses.errorResponse("发送消息失败");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
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
     * @return response
     */
    @GetMapping(value = "/ensureverify/{verifyCode}")
    public Response EnsureVerify(@PathVariable("verifyCode") String verifyCode, UserModel userModel){
        logger.info("invoke EnsureVerify{}, url is /ensureverify/{vefiyCode}", verifyCode);
        if (verifyCode == null || userModel == null || userModel.getUserTelephone().equals("") || userModel.getPkUserid().equals("")) {
            return Responses.errorResponse("error!");
        }
        Response response;
        if(verifyCode.equals(JedisUtil.getValue(userModel.getPkUserid()+userModel.getUserTelephone()))) {
            response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "valid success");
            response.setData(data);

        }else{
            response = Responses.errorResponse("valid failed!");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", "something error");
            response.setData(data);
        }
        return response;
    }


    /**
     * 找回密码接口
     * @param name 用户名:pkUserId
     * @return response
     */

    @GetMapping(value = "/question")
    public Response requestQuestion(@RequestParam("name") String name) {
        logger.info("invoke requestQuestion{}, url is requestQuestion", name);
        if (name == null) {
            return Responses.errorResponse("error!");
        }
        myuserModel = userService.getUserByPkuserID(name);
        if (myuserModel == null) {

            return Responses.errorResponse("请求失败");
        } else {
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();

            data.put("question_1", myuserModel.getQuestion_1());
            data.put("question_2", myuserModel.getQuestion_2());
            data.put("question_3", myuserModel.getQuestion_3());

            response.setData(data);
            return response;
        }
    }

    /**
     * 通过问题找回密码的数据验证方法
     * @return
     */
    @PostMapping(value = "/ensurequestion")
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

     * user logout for himself
     * @param id 用户名
     * @return response

     */
    @GetMapping(value = "/logout/{id}")
    public Response logout(@PathVariable("id") String id) {
        logger.info("invoke logout{}, url is /logout/{id}", id);
        if (id == null) {
            return Responses.errorResponse("error!");
        }
        System.out.println("this is id = " + id);
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
