package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.*;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.BreedingPlanAnotherModel;
import com.deep.domain.service.BreedingPlanAnotherService;
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
@RequestMapping(value = "/b")
public class BreedingAnotherResource {
    private final Logger logger = LoggerFactory.getLogger(BreedingAnotherResource.class);
    @Resource
    private BreedingPlanAnotherService breedingPlanAnotherService;

    /**
     * 查询某个羊场或者某个代理下面的所有记录（包括子代理）
     * @param page 页号
     * @param size 每页记录条数
     * @param factoryName 羊场name
     * @param ispassCheck pass
     * @param id 羊场号或者代理号，需要进行判断
     * @param request Request请求
     * @return Response响应
     */
    @Permit(authorities = "search_for_breeding_child_files")
    @GetMapping(value = "/{id}")
    public Response getAllRecords(@RequestParam(value = "page", defaultValue = "0") String page,
                                  @RequestParam(value = "size", defaultValue = "10") String size,
                                  @RequestParam(value = "factoryName", defaultValue = "") String factoryName,
                                  @RequestParam(value = "ispassCheck", defaultValue = "-1") String ispassCheck,
                                  @PathVariable("id") String id, HttpServletRequest request) {
        logger.info("invoke getAllRecords, url is /b {}", id, page, size, factoryName, ispassCheck);
        Long uid = StringToLongUtil.stringToLong(id);
        Integer upage = StringToLongUtil.stringToInt(page);
        Byte usize = StringToLongUtil.stringToByte(size);
        Byte flag = Byte.valueOf(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
        if (uid < 0 || upage < 0 || usize < 0) {
            return Responses.errorResponse("错误");
        }
        HashMap<String, Object> data = new HashMap<>();
        if (flag == 0) {
            // 如果是羊场
            List<BreedingPlanAnotherModel> models = breedingPlanAnotherService.findAllRecords(uid);
            List<BreedingPlanAnotherModel> result = new ArrayList<>();
            if (models != null) {
                for (int i = upage*usize; i < models.size() && i < upage*usize + usize; i++) {
                    result.add(models.get(i));
                }
            }
            data.put("List", result);
            data.put("size", breedingPlanAnotherService.queryCount(uid));
        } else if (flag == 1) {
            // 如果是代理，查询下级所有的羊场
            Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(String.valueOf(uid));
            if (factories == null) {
                return Responses.errorResponse("没有数据");
            } else {
                List<BreedingPlanAnotherModel> models = new ArrayList<>();
                long directCount = 0, undirectCount = 0;         // record number
                // direct factories
                List<Long> directFactories = factories.get((long)-1);
                List<BreedingPlanAnotherModel> directModels = new ArrayList<>();
                if (directFactories != null) {
                    for (Long directFactory : directFactories) {
                        directModels.addAll(breedingPlanAnotherService.findAllRecords(directFactory));
                        directCount += breedingPlanAnotherService.queryCount(directFactory);
                    }
                }

                // undirect factories
                List<Long> undirectFactories = factories.get((long)0);
                List<BreedingPlanAnotherModel> undirectModels = new ArrayList<>();
                if (undirectFactories != null) {
                    for (Long undirectFactory : undirectFactories) {
                        undirectModels.addAll(breedingPlanAnotherService.findAllRecords(undirectFactory));
                        undirectCount += breedingPlanAnotherService.queryCount(undirectFactory);
                    }
                }
                models.addAll(directModels);
                models.addAll(undirectModels);
                List<BreedingPlanAnotherModel> result = new ArrayList<>();
                for (int i = upage*usize; i < models.size() && i < upage*usize + usize; i++) {
                    result.add(models.get(i));
                }
                data.put("List", result);
                data.put("size", directCount + undirectCount);
                data.put("directSize", directCount);
            }
        } else {
            return Responses.errorResponse("没有权限查询相关信息");
        }
        return Responses.successResponse(data);
    }

    /**
     * 查询一条记录
     * @param id 记录主键
     * @return Response相关信息
     */
    @Permit(authorities = "search_for_breeding_child_files")
    @GetMapping(value = "/find/{id}")
    public Response findARecord(@PathVariable("id") String id) {
        logger.info("invoke findARecord {}, url is b/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null) {
            return Responses.errorResponse("错误！");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", model);
        response.setData(data);
        return response;
    }

    /**
     * 操作员添加一条记录
     * @param breedingRequest 添加对象模型
     * @param bindingResult 验证信息
     * @return response 响应
     */
    @Permit(authorities = "increase_breeding_maternity_file")
    @PostMapping(value = "")
    public Response addARecord(@Valid @RequestBody BreedingRequest breedingRequest, BindingResult bindingResult) {
        logger.info("invoke addARecord {}", breedingRequest.toString());
        HashMap<String, Object> data = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加失败！");
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        breedingRequest.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        breedingRequest.setGmtModify(new Timestamp(System.currentTimeMillis()));
        breedingRequest.setOperatorTime(new Timestamp(System.currentTimeMillis()));
        Long success = breedingPlanAnotherService.addARecordByOperator(breedingRequest);
        if (success > 0) {
            data.put("success", success);
            return Responses.successResponse(data);
        }
        return Responses.errorResponse("添加失败");
    }

    /**
     * 操作员修改信息
     * @param id 记录主键
     * @param breedingModifyRequest 添加对象模型
     * @param bindingResult 验证信息
     * @return response 响应
     */
    @Permit(authorities = "modify_breeding_seed_file")
    @PatchMapping(value = "/{id}")
    public Response addARecordFirst(@PathVariable("id")String id,  @Valid @RequestBody BreedingModifyRequest breedingModifyRequest, BindingResult bindingResult) {
        logger.info("invoke addARecordFirst {}", breedingModifyRequest.toString());
        HashMap<String, Object> data = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("操作员修改信息失败！");
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        Long uid = StringToLongUtil.stringToLong(id);
        if (uid <= 0) {
            return Responses.errorResponse("修改第一阶段信息失败！");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null || model.getIspassCheck() == 1 || model.getIspassSup() == 1) {
            return Responses.errorResponse("not find this record or the record has been checked!");
        }
        breedingModifyRequest.setGmtModify(new Timestamp(System.currentTimeMillis()));
        breedingModifyRequest.setOperatorTime(new Timestamp(System.currentTimeMillis()));
        Long success = breedingPlanAnotherService.updateARecordFirstByOperator(breedingModifyRequest, uid);
        if (success > 0) {
            data.put("success", success);
            return Responses.successResponse(data);
        }
        return Responses.errorResponse("修改第一阶段信息失败");
    }

    /**
     * 操作员删除一条记录
     * @param id 记录主键
     * @return response响应
     */
    @Permit(authorities = "delete_breeding_child_files")
    @DeleteMapping(value = "/{id}")
    public Response deleteARecord(@PathVariable("id") String id){
        logger.info("invoke deleteARecord {}, url is b/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("错误");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null || model.getIspassCheck() == 1 || model.getIspassSup() == 1) {
            return Responses.errorResponse("the record has been checked, can not be deleted!");
        }
        long success = breedingPlanAnotherService.deleteARecord(uid);
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
     * 监督员审核一条记录
     * @param id 修改记录的主键
     * @return response相应信息
     */
    @Permit(authorities = "supervise_auditing_breeding_child_files")
    @PatchMapping(value = "/s/{id}")
    public Response supervisorARecordFirst(@PathVariable("id")String id, @Valid @RequestBody SupervisorRequest supervisorRequest, BindingResult bindingResult) {
        logger.info("invoke supervisorARecordFirst {}, url is b/s/{id}", id, supervisorRequest.toString(), bindingResult.toString());
        if (bindingResult.hasErrors()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            Response response = Responses.errorResponse("监督员审核错误！");
            response.setData(data);
            return response;
        }
        Long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("监督员审核错误!");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null || model.getIspassSup() != 2) {
            return Responses.errorResponse("this record has been checked!");
        }
        Long success = breedingPlanAnotherService.updateARecordBySupervisor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), supervisorRequest.getIspassSup(), supervisorRequest.getSupervisor(), supervisorRequest.getName());
        if (success <= 0) {
            return Responses.errorResponse("监督员审核错误！");
        } else {
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", "监督员审核成功！");
            return Responses.successResponse(data);
        }
    }


    /**
     * 技术员审核一条记录
     * @param id 修改记录的主键
     * @return response相应信息
     */
    @Permit(authorities = "experts_review_mating_and_childbirth_files")
    @PatchMapping(value = "/p/{id}")
    public Response professorARecordFirst(@PathVariable("id")String id, @Valid @RequestBody ProfessorRequest professorRequest, BindingResult bindingResult) {
        logger.info("invoke professorARecordFirst {}, url is b/p/{id}", id, professorRequest.toString(), bindingResult.toString());
        if (bindingResult.hasErrors()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            Response response = Responses.errorResponse("技术员审核错误！");
            response.setData(data);
            return response;
        }
        Long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("error!");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null || model.getIspassCheck() != 2) {
            return Responses.errorResponse("this record has been checked!");
        }
        Long success = breedingPlanAnotherService.updateARecordByProfessor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), professorRequest.getIspassCheck(), professorRequest.getProfessor(), professorRequest.getName(), professorRequest.getUpassReason());
        if (success <= 0) {
            return Responses.errorResponse("技术员审核错误！");
        } else {
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", "技术员审核成功！");
            return Responses.successResponse(data);
        }
    }
}
