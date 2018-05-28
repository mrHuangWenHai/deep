package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.FactoryRequest;
import com.deep.api.response.FactoryResponse;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;

import com.deep.domain.model.FactoryModel;
import com.deep.domain.service.FactoryService;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/factory")
public class FactoryResouce {

    private final Logger logger = LoggerFactory.getLogger(FactoryResouce.class);

    @Resource
    private FactoryService factoryService;

    /**
     * 查看所有羊场(超级管理员可见)
     * @return response相关信息
     */
    @Permit(authorities = "increase_customer")
    @GetMapping(value = "/{id}")
    public Response factoryLists(
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "10") String size,
            @PathVariable("id") String id, HttpServletRequest request
    ) {
        logger.info("invoke factoryLists, url is factory/");
        Long uid = StringToLongUtil.stringToLong(id);
        Long upage = StringToLongUtil.stringToLong(page);
        Byte usize = StringToLongUtil.stringToByte(size);
        Byte which = StringToLongUtil.stringToByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
        if (uid < 0 || upage < 0 || usize < 0) {
            return Responses.errorResponse("错误");
        }
        if (which == 2) {
            // 这是客户
            return Responses.errorResponse("error, have no permit");
        } else if (which == 0) {
            // 这是代理
            Long start = upage*usize;
            List<FactoryResponse> factoryModelList = factoryService.getAllFactoryOfOneAgentPage(uid, start, usize);
            if (factoryModelList == null) {
                return Responses.errorResponse("暂无羊场信息");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List", factoryModelList);
            data.put("size", factoryModelList.size());
            response.setData(data);
            return response;
        } else {
            Map<Long, List<Long>> factories = AgentUtil.getAllSubordinateFactory(id);
            if (factories == null) {
                return Responses.errorResponse("error request!");
            }
            List<FactoryResponse> models = new ArrayList<>();
            // direct people
            List<Long> directFactories = factories.get((long) -1);
            List<FactoryResponse> theOther = new ArrayList<>();
            if (directFactories != null) {
                for (Long directFactory : directFactories) {
                    System.out.println("directFactory = " + directFactory);
                    theOther.add(factoryService.getOneFactoryAgent(Long.parseLong(directFactory.toString())));
                }
            }
            // un direct people
            List<Long> undirectFacotries = factories.get((long) 0);
            List<FactoryResponse> other = new ArrayList<>();
            if (undirectFacotries != null) {
                for (Long undirectFactory : undirectFacotries) {
                    other.add(factoryService.getOneFactoryAgent(Long.parseLong(undirectFactory.toString())));
                }
            }
            models.addAll(theOther);
            models.addAll(other);
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List", models);
            data.put("size", theOther.size());
            response.setData(data);
            return response;
        }
    }

    /**
     * 获取某一个代理的直属羊场
     * @param id
     * @return
     */
    @Permit(authorities = "customer_inquiry")
    @GetMapping(value = "/agent/{id}")
    public Response factoryByAgent(
            @PathVariable("id") String id,
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "10") String size,
            @RequestParam(value = "flag", defaultValue = "2") Byte flag
    ) {
        logger.info("invoke factoryByAgent {}, url is factory/agent/{id}", id);
//        if (flag  == 2 || flag == 0) {
//            return Responses.errorResponse("无权限, 您不能查看不属于您管辖的羊场");
//        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Long upage = StringToLongUtil.stringToLong(page);
        Byte usize = StringToLongUtil.stringToByte(size);
        Long start = upage*usize;
        if (upage < 0 || usize < 0) {
            return Responses.errorResponse("错误");
        }
        List<FactoryResponse> lists = factoryService.getAllFactoryOfOneAgentPage(uid, start, usize);
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
     * 根据羊场的地理位置查询(模糊查询)
     * @param location 羊场地理位置信息
     * @return response 相关信息
     */
    @Permit(authorities = "customer_inquiry")
    @GetMapping(value = "/location")
    public Response factoryByBreadLocation(@RequestParam(value = "location", defaultValue = "") String location) {
        logger.info("invoke getFactory {}, url is factory/location", location);
        List<FactoryModel> factoryModels = factoryService.getAgentByBreadLocation("%" + location + "%");
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
    @GetMapping(value = "/find/{id}")
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
        data.put("model", factoryModel);
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
     * @param factoryRequest 羊场实体
     * @param id 羊场主键
     * @param bindingResult 错误对象
     * @return Response响应
     */
    @Permit(authorities = "modify_customer")
    @PutMapping(value = "/{id}")
    public Response factoryUpdate(@Valid @RequestBody FactoryRequest factoryRequest, @PathVariable("id") String id , BindingResult bindingResult) {
        logger.info("invoke factoryUpdate{}", factoryRequest, id);
        long uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("羊场修改失败, 数据校验失败");
        } else {

            FactoryModel factoryModel = new FactoryModel();

            factoryModel.setId(uid);
            factoryModel.setGmtCreate(factoryService.getOneFactory(uid).getGmtCreate());
            factoryModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            factoryModel.setAgent(factoryRequest.getFactoryNum());
            factoryModel.setBreedLocation(factoryRequest.getBreedLocation());
            factoryModel.setBreedLocationDetail(factoryRequest.getBreedLocationDetail());
            factoryModel.setBreedName(factoryRequest.getBreedName());
//            factoryModel.setDisinfectP(factoryRequest.getDisinfectP());

            factoryModel.setDisinfectP("123");

            factoryModel.setResponsiblePersonId(factoryRequest.getResponsibleId());
            factoryModel.setResponsiblePersonName(factoryRequest.getResponsiblePersonId());

            factoryModel.setPkNumber(factoryRequest.getPkNumber());
            factoryModel.setCreateTime(factoryRequest.getCreateTime());
            factoryModel.setRemark(factoryRequest.getRemark());

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
     * @param factoryRequest factoryRequest
     * @param bindingResult bindingResult
     * @return response
     */
    @Permit(authorities = "increase_customer")
    @PostMapping(value = "")
    public Response addFactory(@Valid @RequestBody FactoryRequest factoryRequest, BindingResult bindingResult) {
        logger.info("invoke addFactory{}, url is factory/add", factoryRequest);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("参数错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            FactoryModel factoryModel = new FactoryModel();

            factoryModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            factoryModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            factoryModel.setAgent(factoryRequest.getFactoryNum());
            factoryModel.setBreedLocation(factoryRequest.getBreedLocation());
            factoryModel.setBreedLocationDetail(factoryRequest.getBreedLocationDetail());
            factoryModel.setBreedName(factoryRequest.getBreedName());
//            factoryModel.setDisinfectP(factoryRequest.getDisinfectP());
            factoryModel.setDisinfectP("123");

            factoryModel.setResponsiblePersonId(factoryRequest.getResponsibleId());
            factoryModel.setResponsiblePersonName(factoryRequest.getResponsiblePersonId());

            factoryModel.setPkNumber(factoryRequest.getPkNumber());
            factoryModel.setCreateTime(factoryRequest.getCreateTime());
            factoryModel.setRemark(factoryRequest.getRemark());

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

    /**
     * get no responsibleFactory
     * @return response
     */
    @Permit(authorities = "customer_inquiry")
    @GetMapping(value = "/fr")
    public Response findAllNoResponsibleFactory(HttpServletRequest request) {
        logger.info("fhaohfaf");

        Long userId = TokenAnalysis.getUserId(request.getHeader(Constants.AUTHORIZATION));
        if (userId == null) {
            return Responses.errorResponse("Error!");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        List<FactoryModel> factoryModels = factoryService.findAllNoResponsibleFactory(userId);
        data.put("List", factoryModels);
        data.put("size", factoryModels.size());
        response.setData(data);
        return response;
    }
}
