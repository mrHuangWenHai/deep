package com.deep.api.resource.SheepInfo;

import com.deep.api.request.BCRequest;
import com.deep.api.response.BuildingColResponse;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.sheepInfo.BuildingColumnModel;
import com.deep.domain.service.SheepInfo.BuildingFactoryService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/bc")
public class BuildingFactoryResource {
    @Resource
    private BuildingFactoryService buildingFactoryService;

    /**
     * 创建一个栏栋信息
     * @param buildingColumnModel 栏栋模型
     * @param bindingResult 相应结果
     * @return 返回信息
     */
    @PostMapping(value = "/batchCreateBC")
    public Response insertBuildingAndColumn(@Valid @RequestBody BuildingColumnModel buildingColumnModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加栏栋失败, 羊场号，栏号，栋号不能为空!");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        Long insert = buildingFactoryService.insertBuildingAndColumn(buildingColumnModel);
        if (insert > 0) {
            return Responses.successResponse();
        } else {
            return Responses.errorResponse("添加栏栋错误！");
        }
    }

    /**
     * 修改栏栋信息
     * @param buildingColumnModel 修改栏栋信息模型
     * @param bindingResult 相应结果
     * @return 返回信息
     */
    @PostMapping(value = "/batchUpdateBC")
    public Response updateBuildingAndColumn(@Valid @RequestBody BuildingColumnModel buildingColumnModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("修改栏栋失败, 羊场号，栏号，栋号不能为空!");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        if (buildingFactoryService.updateBuildingAndColumn(buildingColumnModel)) {
            return Responses.successResponse();
        } else {
            return Responses.errorResponse("修改栏栋错误，必须大于当前栋数目！");
        }
    }

    /**
     * 获取所有栏栋的信息，包括羊
     * @param factory 羊场号
     * @return 返回信息
     */
    @GetMapping(value = "/getSheepBase/{factory}")
    public Response getSheepBase(@PathVariable("factory") Long factory) {
        HashMap<String, Object> map = new HashMap<>();
        List<BuildingColResponse> columnBase = buildingFactoryService.getBuildingAndColumn(factory);
        map.put("columnBase", columnBase);
        List<BuildingColumnModel> buildingBase = buildingFactoryService.getAllBuildingAndColumn(factory);
        map.put("buildingBase", buildingBase);
        return Responses.successResponse(map);
    }

    /**
     * 羊只搬迁
     * @param bcRequest 搬迁信息
     * @param id 羊id
     * @param bindingResult 相应结果
     * @return 响应
     */
    @PostMapping(value = "/changeBC/{id}")
    public Response changeBC(@Valid @RequestBody BCRequest bcRequest, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("羊场号, 栏号，栋号不能为空!");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        Long update = buildingFactoryService.moveSheep(bcRequest, id);
        if (update == null) {
            return Responses.errorResponse("错误，此栏栋不存在！");
        } else if (update < 0) {
            return Responses.errorResponse("栏栋搬迁失败！");
        } else {
            return Responses.successResponse();
        }
    }

    @GetMapping(value = "/b/{factory}")
    public Response getBuildings(@PathVariable("factory") Long factory) {
        Set<Integer> data = buildingFactoryService.getBuildings(factory);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", data);
        return Responses.successResponse(map);
    }

    @GetMapping(value = "/b/{factory}/{building}")
    public Response getCols(@PathVariable("factory") Long factory, @PathVariable("building") Integer building) {
        List<Integer> data = buildingFactoryService.getCols(factory, building);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", data);
        return Responses.successResponse(map);
    }
}
