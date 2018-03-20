package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.service.FactoryService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/factory")
public class FactoryResouce {
    @Resource
    private FactoryService factoryService;

    /**
     * 查看所有羊场
     * @return
     */
    @Permit(modules = "factory", authorities = "select_factory")
    @GetMapping(value = "/")
    public Response factoryLists() {
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("allFactory", factoryService.getAll());
        response.setData(data);

        return response;
    }

    /**
     * 根据羊场的主键查询一个角色
     */
    @Permit(modules = "factory", authorities = "select_factory")
    @GetMapping(value = "/{id:\\d+}")
    public Response getFactoryOne(@PathVariable("id") Long id) {
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneFactory", factoryService.getOneFactory(id));
        response.setData(data);

        return response;
    }

    /**
     * 根据工厂的主键删除一个角色
     * @param id
     */
    @Permit(modules = "factory")
    @DeleteMapping(value = "/{id:\\d+}")
    public Response deleteFactory(@PathVariable("id") Long id) {
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", factoryService.deleteFatory(id));
        response.setData(data);
        return response;
    }

    /**
     * 修改一个羊场
     * @param factoryModel
     * @param id
     * @param bindingResult
     * @return
     */
    @Permit(modules = "factory")
    @PutMapping(value = "/{id:\\d+}")
    public Response factoryUpdate(@Valid @RequestBody FactoryModel factoryModel, @PathVariable("id") Long id , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("羊场修改失败");
            return response;
        } else {
            factoryModel.setId(id);
            factoryModel.setGmtCreate(factoryService.getOneFactory(id).getGmtCreate());
            factoryModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            factoryModel.setPkNumber(factoryModel.getPkNumber());
            factoryModel.setBreadName(factoryModel.getBreadName());
            factoryModel.setBreadLocation(factoryModel.getBreadLocation());
            factoryModel.setCreateTime(factoryModel.getCreateTime());
            factoryModel.setResponsiblePersonid(factoryModel.getResponsiblePersonid());
            factoryModel.setRemark(factoryModel.getRemark());
            factoryModel.setDisnfectP(factoryModel.getDisnfectP());
            factoryModel.setAgent(factoryModel.getAgent());

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("oneUser", factoryService.updateFactory(factoryModel));
            response.setData(data);

            return response;
        }
    }

    /**
     * 增加一个工厂实体
     * @param factoryModel
     * @param bindingResult
     * @return
     */
    @Permit(modules = "factory")
    @PostMapping(value = "/add")
    public Response addFactory(@Valid FactoryModel factoryModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("羊场添加失败");
            return response;
        } else {
            factoryModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            factoryModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            factoryModel.setPkNumber(factoryModel.getPkNumber());
            factoryModel.setBreadName(factoryModel.getBreadName());
            factoryModel.setBreadLocation(factoryModel.getBreadLocation());
            factoryModel.setCreateTime(factoryModel.getCreateTime());
            factoryModel.setResponsiblePersonid(factoryModel.getResponsiblePersonid());
            factoryModel.setRemark(factoryModel.getRemark());
            factoryModel.setDisnfectP(factoryModel.getDisnfectP());
            factoryModel.setAgent(factoryModel.getAgent());

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("oneUser", factoryService.updateFactory(factoryModel));
            response.setData(data);
            return response;
        }
    }
}
