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
import java.util.Map;

@RestController
@RequestMapping(value = "/factory")
public class FactoryResouce {

    private final Logger logger = LoggerFactory.getLogger(FactoryResouce.class);

    @Resource
    private FactoryService factoryService;

    /**
     * 查看所有羊场(超级管理员可见)
     * @return
     */
    @Permit(authorities = "customer_inquiry")

    @GetMapping(value = "")

    public Response factoryLists() {
        logger.info("invoke factoryLists, url is factory/");
        List<FactoryModel> factoryModelList = factoryService.getAll();
        if (factoryModelList == null) {
            return Responses.errorResponse("暂无羊场信息");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();

        data.put("List", factoryModelList);
        data.put("size", factoryModelList.size());

        response.setData(data);
        return response;
    }

    /**
     * 获取属于某一个代理的所有羊场
     * @param id
     * @return
     */
    @GetMapping(value = "/agent/{id}")
    public Response factoryByAgent(@PathVariable("id") String id) {
        logger.info("invoke factoryByAgent {}, url is factory/agent/{id}", id);
        long uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        List<FactoryModel> lists = factoryService.getAllFactoryOfOneAgent(uid);
        if (lists == null) {
            return Responses.errorResponse("没有相应直属羊场");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();

        data.put("List", lists);

        data.put("size", lists.size());
        response.setData(data);
        return response;
    }

    /**
     * 根据羊场的地理位置查询
     * @param location
     * @return
     */
    @Permit(authorities = "customer_inquiry")
    @PostMapping(value = "/location")
    public Response factoryByBreadLocation(@RequestBody Map<String, String> location, BindingResult bindingResult) {
        logger.info("invoke getFactory {}, url is factory/location", location);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("请求错误!");
        }
        List<FactoryModel> factoryModels = factoryService.getAgentByBreadLocation(location.get("location"));
        if (factoryModels == null) {
            return Responses.errorResponse("无该区域的羊场");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();

        data.put("List", factoryModels);

        data.put("size", factoryModels.size());
        response.setData(data);
        return response;
    }

    /**
     * 根据羊场的主键查询羊场
     */
    @Permit(authorities = "customer_inquiry")
    @GetMapping(value = "/{id}")
    public Response getFactoryOne(@PathVariable("id") String id) {
        logger.info("invoke getFactoryOne {}, url is factory/{id}", id);
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

        data.put("List", factoryModel);

        response.setData(data);
        return response;
    }

    /**
     * 根据工厂的主键删除一个羊场

     * @param id 羊场主键

     */
    @Permit(authorities = "delete_customer")
    @DeleteMapping(value = "/{id}")
    public Response deleteFactory(@PathVariable("id") String id) {

        logger.info("invoke deleteFactory{}, url is factory/{id}", id);

        long uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Long deleteID = factoryService.deleteFactory(uid);
        if (deleteID <= 0) {
            return Responses.errorResponse("删除央行信息失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();

        data.put("List", deleteID);

        response.setData(data);
        return response;
    }

    /**
     * 修改一个羊场

     * @param factoryModel 羊场实体
     * @param id 羊场主键
     * @param bindingResult 错误对象
     * @return Response响应

     */
    @Permit(authorities = "modify_customer")
    @PutMapping(value = "/{id}")
    public Response factoryUpdate(@Valid @RequestBody FactoryModel factoryModel, @PathVariable("id") String id , BindingResult bindingResult) {

        logger.info("invoke factoryUpdate{}", factoryModel, id);

        long uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        if (bindingResult.hasErrors()) {

            return Responses.errorResponse("羊场修改失败, 数据校验失败");

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

            data.put("List", updateID);

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
    @Permit(authorities = "increase_customer")

    @PostMapping(value = "")
    public Response addFactory(@Valid @RequestBody FactoryModel factoryModel, BindingResult bindingResult) {

        logger.info("invoke addFactory{}, url is factory/add", factoryModel);

        if (bindingResult.hasErrors()) {

            return Responses.errorResponse(bindingResult.getFieldError().toString());
        } else {
            factoryModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            factoryModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            int issuccess = factoryService.addFactory(factoryModel);
            if (issuccess == 0) {
                return Responses.errorResponse("添加失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("issuccess", issuccess);

            response.setData(data);
            return response;
        }
    }
}
