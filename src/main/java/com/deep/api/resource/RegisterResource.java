package com.deep.api.resource;

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
    public Response register(@RequestBody @Valid UserModel userModel, BindingResult bindingResult) {
        logger.info("invoke register{}, url is /register", userModel, bindingResult);

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

            userModel.setIsFactory((byte)0);
            if (userModel.getUserPermit() == null || userModel.getUserPermit().equals("")) {
                userModel.setUserPermit("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            }
            userModel.setIsExtended((byte)0);
            userModel.setUserRole(0);

            Long success = userService.addUser(userModel);
            if (success <= 0) {
                return Responses.errorResponse("用户信息增加失败,请检查网络后重试");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);
            response.setData(data);
            return response;
        }
    }
}
