package com.deep.api.resource;

import com.deep.api.request.RegisterRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;

@RestController
public class RegisterResource {
    private final Logger logger = LoggerFactory.getLogger(RegisterResource.class);
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody @Valid RegisterRequest registerRequest, BindingResult bindingResult) {
        logger.info("invoke register{}, url is /register", registerRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("数据验证失败");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            UserModel userModel = new UserModel();
            // 添加用户的校验信息
            if (!userService.verifyOnlyOnePkUserid(registerRequest.getUsername())) {
                Response response = Responses.errorResponse("注册失败");
                HashMap<String, Object> data = new HashMap<>();
                data.put("errorMessage", "用户名已经被使用过");
                response.setData(data);
                return response;
            }

            userModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            userModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            // 进行对应的赋值操作
            userModel.setPkUserid(registerRequest.getUsername());
            userModel.setUserPwd(registerRequest.getPassword());
            userModel.setQq(registerRequest.getQq());
            userModel.setUserEmail(registerRequest.getUserEmail());
            userModel.setUserTelephone(registerRequest.getUserTelephone());
            userModel.setQuestion_1(registerRequest.getQuestion_1());
            userModel.setQuestion_2(registerRequest.getQuestion_2());
            userModel.setQuestion_3(registerRequest.getQuestion_3());
            userModel.setAnswer_1(registerRequest.getAnswer_1());
            userModel.setAnswer_2(registerRequest.getAnswer_2());
            userModel.setAnswer_3(registerRequest.getAnswer_3());

            // 2表示普通注册用户
            userModel.setIsFactory((byte)2);

            userModel.setUserPermit("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            userModel.setIsExtended((byte)0);
            userModel.setUserRole(0);
            userModel.setUserFactory(-2);

            Long success = userService.addUser(userModel);
            if (success <= 0) {
                return Responses.errorResponse("注册用户信息失败,请检查网络后重试");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);
            response.setData(data);
            return response;
        }
    }
}
