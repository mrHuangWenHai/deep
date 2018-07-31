package com.deep.api.resource;

import com.deep.api.Utils.PermitUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.token.TokenManagerRealization;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.RoleRequest;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RoleModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.RoleService;

import com.deep.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "role")
public class RoleResource {
    private final Logger logger = LoggerFactory.getLogger(RoleResource.class);

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    /**
     * 查看所有的角色
     * @return json数据返回所有角色
     */
    @Permit(authorities = "query_role")
    @GetMapping(value = "/{id}")
    public Response roleLists(
            @RequestParam(value = "size", defaultValue = "10") String size,
            @RequestParam(value = "page", defaultValue = "0") String page,
            @PathVariable("id") String agentRank, HttpServletRequest httpServletRequest
    ) {
        logger.info("invoke roleLists, url is role");

        // 从header中获取token
        String authorization = httpServletRequest.getHeader(Constants.AUTHORIZATION);
        System.out.println(authorization);
        TokenManagerRealization tokenManagerRealization = new TokenManagerRealization();
        // 从authorization中获取用户名以及token
        Long userID = tokenManagerRealization.getUserID(authorization);

        System.out.println("size = [" + size + "], page = [" + page + "], agentRank = [" + agentRank + "], httpServletRequest = [" + httpServletRequest + "]");
        System.out.println("userID = " + userID);

        // 根据userID查找用户表
        UserModel userModel = userService.getOneUser(userID);
        Long factoryID = 0L;

        if (userModel != null) {
            factoryID = userModel.getUserFactory();
        }

        Long upage = StringToLongUtil.stringToLong(page);
        Byte usize = StringToLongUtil.stringToByte(size);
        Byte rank;
        switch (StringToLongUtil.stringToByte(agentRank)) {
            case -1:
                rank = 18;
                break;
            case 0:
                rank = 1;
                break;
            case 1:
                rank = 6;
                break;
            case 2:
                rank = 10;
                break;
            case 3:
                rank = 14;
                break;
            default:
                rank = 21;
        }
        if (usize < 0 || upage < 0) {
            return Responses.errorResponse("参数错误!");
        }
        List<RoleModel> roleModels;
        Long counts = (long)0;
        if (rank == 18) {
            roleModels = roleService.getAll(usize*upage, usize, rank, factoryID);
            counts = roleService.findAnotherTheCount(rank, factoryID);
        } else {
            roleModels = roleService.getAnotherAll(usize*upage, usize, rank);
            counts = roleService.findAllTheCount(rank);
        }

        if (roleModels == null) {
            return Responses.errorResponse("获取角色信息失败");
        }

        List<RoleRequest> roleRequests = new ArrayList<>();
        for (RoleModel roleModel : roleModels) {
            RoleRequest request = new RoleRequest();
            request.setRoleDescription(roleModel.getRoleDescription());
            request.setRolePermit(PermitUtil.stringToStringArray(roleModel.getDefaultPermit()));
            request.setTypeName(roleModel.getTypeName());
            request.setId(roleModel.getId());
            roleRequests.add(request);
        }

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List", roleRequests);
        data.put("size", counts);
        response.setData(data);
        return response;
    }

    /**
     * 添加一个角色
     * @param bindingResult error message
     * @return response
     */
    @Permit(authorities = "add_role")
    @PostMapping(value = "")
    public Response addRole(@Valid @RequestBody RoleRequest roleRequest, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        logger.info("invoke addRole{}, url is role", roleRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            Response response =  Responses.errorResponse("数据校验失败, 请检查输入格式是否错误");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            if (roleRequest.getRolePermit() == null) {
                System.out.println("权限为空!!!");
                return Responses.errorResponse("失败!");
            }

            // 从header中获取token
            String authorization = httpServletRequest.getHeader(Constants.AUTHORIZATION);
            System.out.println(authorization);
            TokenManagerRealization tokenManagerRealization = new TokenManagerRealization();
            // 从authorization中获取用户名以及token
            Long userID = tokenManagerRealization.getUserID(authorization);
            // 根据userID查找用户表
            UserModel userModel = userService.getOneUser(userID);
            Long factoryID = 0L;

            if (userModel != null) {
                factoryID = userModel.getUserFactory();
            }

            RoleModel roleModel = new RoleModel();
            roleModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            roleModel.setPkTypeid(String.valueOf(0));
            roleModel.setRoleDescription(roleRequest.getRoleDescription());
            roleModel.setTypeName(roleRequest.getTypeName());
            roleModel.setDefaultPermit(PermitUtil.stringArrayToString(roleRequest.getRolePermit()));
            roleModel.setFactoryID(factoryID);
            Long success = roleService.addRole(roleModel);
            if (success <= 0) {
                return Responses.errorResponse("添加失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("model", roleService.getTheBigId());
            response.setData(data);
            return response;
        }
    }

    /**
     * 根据权限的主键查找一个角色
     * @param id 主键
     * @return response
     */
    @Permit(authorities = "query_role")
    @GetMapping(value = "/find/{id}")
    public Response findRole(@PathVariable("id")String id) {
        logger.info("invoke findRole{}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        RoleModel roleModel = roleService.getOneRole(uid);
        if (roleModel == null) {
            return Responses.errorResponse("查询失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();

        RoleRequest request = new RoleRequest();
        request.setId(roleModel.getId());
        request.setTypeName(roleModel.getTypeName());
        request.setRolePermit(PermitUtil.stringToStringArray(roleModel.getDefaultPermit()));
        request.setRoleDescription(roleModel.getRoleDescription());

        data.put("model", request);
        response.setData(data);
        return response;
    }

    /**
     * 删除一个角色
     * @param id ID
     */
    @Permit(authorities = "remove_role")
    @DeleteMapping(value = "/{id}")
    public Response deleteRole(@PathVariable("id")String id) {

        logger.info("invoke deleteRole{}, url is role/{id}", id);

        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1 || uid <= 21) {
            return Responses.errorResponse("查询错误");
        }
        Long success = roleService.deleteRole(uid);
        if (success <= 0) {
            return Responses.errorResponse("删除失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("success", success);
        response.setData(data);
        return response;
    }

    /**
     * 修改权限方法
     * @param roleRequest roleRequest
     * @return response
     */
    @Permit(authorities = "modify_role")
    @PutMapping(value = "/{id}")
    public Response roleUpdate(@RequestBody @Valid RoleRequest roleRequest, @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke roleUpdate{}, url is role/{id}", roleRequest, id, bindingResult);
        if (bindingResult.hasErrors()) {
            Response response =  Responses.errorResponse("error, 校验错误");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        RoleModel model = roleService.getOneRole(uid);
        if (model == null) {
            return Responses.errorResponse("修改失败");
        }

        RoleModel roleModel = new RoleModel();
        roleModel.setId(uid);
        roleModel.setGmtCreate(model.getGmtCreate());
        roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
        roleModel.setDefaultPermit(PermitUtil.stringArrayToString(roleRequest.getRolePermit()));
        roleModel.setRoleDescription(roleRequest.getRoleDescription());
        roleModel.setTypeName(roleRequest.getTypeName());
        roleModel.setPkTypeid(String.valueOf(1));

        Long success = roleService.updateRole(roleModel);
        if (success <= 0) {
            return Responses.errorResponse("修改失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("success", success);
        response.setData(data);
        return response;
    }
}
