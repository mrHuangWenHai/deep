package com.deep.api.resource;

import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.service.FactoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/factory")
public class FactoryResouce {

    private final Logger logger = LoggerFactory.getLogger(FactoryResouce.class);

    @Resource
    private FactoryService factoryService;

    /**
     * 查看所有羊场
     * @return
     */
    @Permit(modules = "factory", authorities = "select_factory")
    @GetMapping(value = "/")
    public Response factoryLists() {

        logger.info("invoke factoryLists, url is factory/");

        List<FactoryModel> factoryModelList = factoryService.getAll();
        if (factoryModelList.size() <= 0) {
            return Responses.errorResponse("暂无羊场信息");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("allFactory", factoryModelList);
        data.put("number", factoryModelList.size());
        response.setData(data);
        return response;
    }

    /**
     * 根据羊场的主键查询羊场
     */
    @Permit(modules = "factory", authorities = "select_factory")
    @GetMapping(value = "/{id}")
    public Response getFactoryOne(@PathVariable("id") String id) {

        logger.info("invoke getFactoryOne{}, url is factory/{id}", id);

        long uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        FactoryModel factoryModel = factoryService.getOneFactory(uid);
        if (factoryModel == null) {
            return Responses.errorResponse("系统中暂无该羊场信息");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneFactory", factoryModel);
        response.setData(data);
        return response;
    }

    /**
     * 根据工厂的主键删除一个羊场
     * @param id
     */
    @Permit(modules = "factory")
    @DeleteMapping(value = "/{id}")
    public Response deleteFactory(@PathVariable("id") String id) {

        logger.info("invoke deleteFactory{}, url is factory/{id}", id);

        long uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Long deleteID = factoryService.deleteFatory(uid);
        if (deleteID <= 0) {
            return Responses.errorResponse("删除央行信息失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", deleteID);
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
    @PutMapping(value = "/{id}")
    public Response factoryUpdate(@Valid @RequestBody FactoryModel factoryModel, @PathVariable("id") String id , BindingResult bindingResult) {

        logger.info("invoke factoryUpdate{}", factoryModel, id);

        long uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("羊场修改失败, 数据校验失败");
            return response;
        } else {
            factoryModel.setId(uid);
            factoryModel.setGmtCreate(factoryService.getOneFactory(uid).getGmtCreate());
            factoryModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            Long updateID = factoryService.updateFactory(factoryModel);
            if (updateID <= 0) {
                return Responses.errorResponse("修改失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("oneUser", updateID);
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
    public Response addFactory(@Valid @RequestBody FactoryModel factoryModel, BindingResult bindingResult) {

        logger.info("invoke addFactory{}, url is factory/add", factoryModel);

        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("羊场添加失败");
            return response;
        } else {
            factoryModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            factoryModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            Long addID = factoryService.updateFactory(factoryModel);
            if (addID <= 0) {
                return Responses.errorResponse("添加失败");
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("oneUser", addID);
            response.setData(data);
            return response;
        }
    }
}
