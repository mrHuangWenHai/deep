package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RoleModel;
import com.deep.domain.service.RoleService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "role")
public class RoleResource {
    @Resource
    private RoleService roleService;

    /**
     * 查看所有的角色
     * @return json数据返回所有角色
     */
    @Permit(modules = "role")
    @GetMapping(value = "/")
    public Response roleLists() {
        List<RoleModel> roleModels = roleService.getAll();
        if (roleModels.size() <= 0) {
            return Responses.errorResponse("获取角色信息失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("allRole", roleModels);
        data.put("number", roleModels.size());
        response.setData(data);
        return response;
    }

    /**
     * 添加一个角色
     * @param roleModel
     * @param bindingResult
     * @return
     */
    @Permit(modules = "role")
    @PostMapping(value = "")
    public Response addRole(@Valid RoleModel roleModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("添加角色出错,请检查网络后重试");
        } else {
            roleModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            Long addId = roleService.addRole(roleModel);
            if (addId <= 0) {
                return Responses.errorResponse("添加失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("oneAgent", addId);
            response.setData(data);
            return response;
        }
    }

    /**
     * 根据权限的主键查找一个角色
     * @param id 主键
     * @return
     */
    @Permit(modules = "role")
    @GetMapping(value = "/{id:\\d+}")
    public Response findRole(@PathVariable("id")Long id) {
        RoleModel roleModel = roleService.getOneRole(id);
        if (roleModel == null) {
            return Responses.errorResponse("查询失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", roleModel);
        response.setData(data);
        return response;
    }

    /**
     * 删除一个角色
     * @param id
     */
    @Permit(modules = "role")
    @DeleteMapping(value = "/{id:\\d+}")
    public Response deleteRole(@PathVariable("id")Long id) {
        Long deleteID = roleService.deleteRole(id);
        if (deleteID <= 0) {
            return Responses.errorResponse("删除失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", deleteID);
        response.setData(data);
        return response;
    }

    /**
     * 修改权限方法
     * @param roleModel
     * @return
     */
    @Permit(modules = "role")
    @PutMapping(value = "/{id:\\d+}")
    public Response roleUpdate(@RequestBody @Valid RoleModel roleModel, @PathVariable("id") Long id, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("修改角色失败");
        }
        RoleModel middle = roleService.getOneRole(id);
        if (middle == null) {
            return Responses.errorResponse("修改失败");
        }
        roleModel.setId(id);
        roleModel.setGmtCreate(middle.getGmtCreate());
        roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
        Long updateId = roleService.updateRole(roleModel);
        if (updateId <= 0) {
            return Responses.errorResponse("修改失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", updateId);
        response.setData(data);
        return response;
    }
}
