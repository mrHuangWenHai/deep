package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RoleModel;
import com.deep.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.awt.print.Pageable;
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
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("allRole", roleService.getAll());
        response.setData(data);

        return response;
    }

    /**
     * 角色管理
     * @return
     */
//    @Permit(modules = "role")
//    @GetMapping("role")
//    public ModelAndView showAddRole() {
//        List<RoleUserEntity> roleUserEntities = roleRepository.findAll();
//
//        String viewName = "addRole";
//        ModelAndView modelAndView = new ModelAndView(viewName);
//        modelAndView.addObject("allRole", roleUserEntities);
//        return modelAndView;
//    }

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
            // 各个角色的名称,不能重复
            roleModel.setTypeName(roleModel.getTypeName());
            // 各个角色的代码,不能重复
            roleModel.setPkTypeid(roleModel.getPkTypeid());
            // 各个角色的默认权限
            roleModel.setDefaultPermit(roleModel.getDefaultPermit());
            roleModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("oneAgent", roleService.addRole(roleModel));
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
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", roleService.getOneRole(id));
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
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", roleService.deleteRole(id));
        response.setData(data);
        return response;
    }

    /**
     * 修改权限方法
     * @param id
     * @param pkTypeid
     * @param typeName
     * @param defaultPermit
     * @return
     */
    @Permit(modules = "role")
    @PutMapping(value = "/{id:\\d+}")
    public Response roleUpdate(
            @PathVariable("id")Long id,
            @PathVariable("pkTypeid")Long pkTypeid,
            @PathVariable("typeName")String typeName,
            @PathVariable("defaultPermit")Long defaultPermit, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("修改角色失败");
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setDefaultPermit(defaultPermit);
        roleModel.setTypeName(typeName);
        roleModel.setPkTypeid(pkTypeid);
        roleModel.setId(id);
        roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", roleService.updateRole(roleModel));
        response.setData(data);
        return response;
    }
}
