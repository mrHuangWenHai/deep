//package com.deep.api.resource;
//
//import com.deep.api.Utils.AgentUtil;
//import com.deep.api.Utils.StringToLongUtil;
//import com.deep.api.Utils.TokenAnalysis;
//import com.deep.api.authorization.tools.Constants;
//import com.deep.api.request.BreedingOperatorFirst;
//import com.deep.api.request.BreedingOperatorSecond;
//import com.deep.api.request.ProfessorRequest;
//import com.deep.api.request.SupervisorRequest;
//import com.deep.api.response.Response;
//import com.deep.api.response.Responses;
//import com.deep.domain.model.BreedingPlanAnotherModel;
//import com.deep.domain.service.BreedingPlanAnotherService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping(value = "/b")
//public class BreedingAnotherResource {
//    private final Logger logger = LoggerFactory.getLogger(BreedingAnotherResource.class);
//    @Resource
//    private BreedingPlanAnotherService breedingPlanAnotherService;
//
//    /**
//     * 查询某个羊场或者某个代理下面的所有记录（包括子代理）
//     * @param page 页号
//     * @param size 每页记录条数
//     * @param stage 所处的阶段
//     * @param id 羊场号或者代理号，需要进行判断
//     * @param request Request请求
//     * @return Response响应
//     */
//    @GetMapping(value = "/{id}")
//    public Response getAllRecords(@RequestParam(value = "page", defaultValue = "0") String page,
//                                  @RequestParam(value = "size", defaultValue = "10") String size,
//                                  @RequestParam(value = "stage", defaultValue = "0") Byte stage,
//                                  @PathVariable("id") String id, HttpServletRequest request) {
//        logger.info("invoke getAllRecords, url is /b {}", id, page, size);
//        Long uid = StringToLongUtil.stringToLong(id);
//        Long upage = StringToLongUtil.stringToLong(page);
//        Byte usize = StringToLongUtil.stringToByte(size);
//        Byte flag = Byte.valueOf(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
//        if (uid < 0 || upage < 0 || usize < 0) {
//            return Responses.errorResponse("错误");
//        }
//        HashMap<String, Object> data = new HashMap<>();
//        if (flag == 0) {
//            // 如果是羊场
//            List<BreedingPlanAnotherModel> models = breedingPlanAnotherService.findAllRecords(uid, stage, upage*usize, usize);
//            if (models == null) {
//                data.put("List", null);
//                data.put("size", 0);
//            } else {
//                data.put("List", models);
//                data.put("size", breedingPlanAnotherService.queryCount(stage));
//            }
//        } else if (flag == 1) {
//            // 如果是代理，查询下级所有的羊场
//            Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(String.valueOf(uid));
//            if (factories == null) {
//                return Responses.errorResponse("没有数据");
//            } else {
//                List<BreedingPlanAnotherModel> models = new ArrayList<>();
//                // direct factories
//                List<Long> directFactories = factories.get((long)-1);
//                List<BreedingPlanAnotherModel> directModels = new ArrayList<>();
//                if (directFactories != null) {
//                    for (Long directFactory : directFactories) {
//                        directModels.addAll(breedingPlanAnotherService.findAllRecords(directFactory, stage, upage*usize, usize));
//                    }
//                }
//
//                // undirect factories
//                List<Long> undirectFactories = factories.get((long)0);
//                List<BreedingPlanAnotherModel> undirectModels = new ArrayList<>();
//                if (undirectFactories != null) {
//                    for (Long undirectFactory : undirectFactories) {
//                        undirectModels.addAll(breedingPlanAnotherService.findAllRecords(undirectFactory, stage, usize*upage, usize));
//                    }
//                }
//                models.addAll(directModels);
//                models.addAll(undirectModels);
//                data.put("List", models);
//                data.put("size", models.size());
//                data.put("directSize", breedingPlanAnotherService.queryCount(stage));
//            }
//        } else {
//            return Responses.errorResponse("没有权限查询相关信息");
//        }
//        return Responses.successResponse(data);
//    }
//
//    /**
//     * 查询一条记录
//     * @param id 记录主键
//     * @return Response相关信息
//     */
//    @GetMapping(value = "/find/{id}")
//    public Response findARecord(@PathVariable("id") String id) {
//        logger.info("invoke findARecord {}, url is b/{id}", id);
//        long uid = StringToLongUtil.stringToLong(id);
//        if (uid == -1) {
//            return Responses.errorResponse("查询错误");
//        }
//        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
//        if (model == null) {
//            return Responses.errorResponse("错误！");
//        }
//        Response response = Responses.successResponse();
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("model", model);
//        response.setData(data);
//        return response;
//    }
//
//    /**
//     * 操作员在第一阶段添加一条记录
//     * @param breedingOperatorFirst 添加对象模型
//     * @param bindingResult 验证信息
//     * @return response 响应
//     */
//    @PostMapping(value = "")
//    public Response addARecord(@Valid @RequestBody BreedingOperatorFirst breedingOperatorFirst, BindingResult bindingResult) {
//        logger.info("invoke addARecord {}", breedingOperatorFirst.toString());
//        HashMap<String, Object> data = new HashMap<>();
//        if (bindingResult.hasErrors()) {
//            Response response = Responses.errorResponse("添加失败！");
//            data.put("errorMessage", bindingResult.getAllErrors());
//            response.setData(data);
//            return response;
//        }
//        breedingOperatorFirst.setGmtCreate(new Timestamp(System.currentTimeMillis()));
//        breedingOperatorFirst.setGmtModify(new Timestamp(System.currentTimeMillis()));
//        Long success = breedingPlanAnotherService.addARecordByOperator(breedingOperatorFirst);
//        if (success > 0) {
//            data.put("success", success);
//            return Responses.successResponse(data);
//        }
//        return Responses.errorResponse("添加失败");
//    }
//
//    /**
//     * 操作员修改第一阶段信息
//     * @param id 记录主键
//     * @param breedingOperatorFirst 添加对象模型
//     * @param bindingResult 验证信息
//     * @return response 响应
//     */
//    @PatchMapping(value = "/f/{id}")
//    public Response addARecordFirst(@PathVariable("id")String id,  @Valid @RequestBody BreedingOperatorFirst breedingOperatorFirst, BindingResult bindingResult) {
//        logger.info("invoke addARecordFirst {}", breedingOperatorFirst.toString());
//        HashMap<String, Object> data = new HashMap<>();
//        if (bindingResult.hasErrors()) {
//            Response response = Responses.errorResponse("操作员修改第一阶段信息失败！");
//            data.put("errorMessage", bindingResult.getAllErrors());
//            response.setData(data);
//            return response;
//        }
//        Long uid = StringToLongUtil.stringToLong(id);
//        if (uid <= 0) {
//            return Responses.errorResponse("修改第一阶段信息失败！");
//        }
//        // 首先查询是否符合修改信息的条件
//        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
//        if (model == null || model.getStageFlag() == 1 || model.getIsPassCheckFirst() <= 1 || model.getIsPassSupFirst() <= 1) {
//            return Responses.errorResponse("error!");
//        }
//        breedingOperatorFirst.setGmtModify(new Timestamp(System.currentTimeMillis()));
//        Long success = breedingPlanAnotherService.updateARecordFirstByOperator(breedingOperatorFirst, uid);
//        if (success > 0) {
//            data.put("success", success);
//            return Responses.successResponse(data);
//        }
//        return Responses.errorResponse("修改第一阶段信息失败");
//    }
//
//    /**
//     * 操作员增加第二阶段信息
//     * @param id 记录主键
//     * @param breedingOperatorSecond 添加对象模型
//     * @param bindingResult 验证信息
//     * @return response 响应
//     */
//    @PatchMapping(value = "/s/{id}")
//    public Response addARecordSecond(@PathVariable("id")String id,  @Valid @RequestBody BreedingOperatorSecond breedingOperatorSecond, BindingResult bindingResult) {
//        logger.info("invoke addARecordSecond {}", breedingOperatorSecond.toString());
//        HashMap<String, Object> data = new HashMap<>();
//        if (bindingResult.hasErrors()) {
//            Response response = Responses.errorResponse("操作员修改第二阶段信息失败！");
//            data.put("errorMessage", bindingResult.getAllErrors());
//            response.setData(data);
//            return response;
//        }
//        Long uid = StringToLongUtil.stringToLong(id);
//        if (uid <= 0) {
//            return Responses.errorResponse("增加第二阶段信息失败！");
//        }
//        // 首先查询是否符合修改信息的条件
//        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
//        if (model == null || model.getStageFlag() == 0 || model.getIsPassCheckSecond() <= 1 || model.getIsPassSupSecond() <= 1) {
//            return Responses.errorResponse("error!");
//        }
//        breedingOperatorSecond.setId(uid);
//        breedingOperatorSecond.setGmtModify(new Timestamp(System.currentTimeMillis()));
//        Long success = breedingPlanAnotherService.updateARecordByOperator(breedingOperatorSecond);
//        if (success > 0) {
//            data.put("success", success);
//            return Responses.successResponse(data);
//        }
//        return Responses.errorResponse("添加第二阶段信息失败");
//    }
//
//    /**
//     * 操作员在第一阶段删除一条记录
//     * @param id 记录主键
//     * @return response响应
//     */
//    @DeleteMapping(value = "/{id}")
//    public Response deleteARecord(@PathVariable("id") String id){
//        logger.info("invoke deleteARecord {}, url is b/{id}", id);
//        long uid = StringToLongUtil.stringToLong(id);
//        if (uid == -1) {
//            return Responses.errorResponse("错误");
//        }
//        long success = breedingPlanAnotherService.deleteARecord(uid);
//        if (success <= 0) {
//            return Responses.errorResponse("删除失败");
//        }
//        Response response = Responses.successResponse();
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("success", success);
//        response.setData(data);
//        return response;
//    }
//
//    /**
//     * 监督员在第一阶段审核一条记录
//     * @param id 修改记录的主键
//     * @return response相应信息
//     */
//    @PatchMapping(value = "/s/f/{id}")
//    public Response supervisorARecordFirst(@PathVariable("id")String id, @Valid @RequestBody SupervisorRequest supervisorRequest, BindingResult bindingResult) {
//        logger.info("invoke supervisorARecordFirst {}, url is b/s/f/{id}", id, supervisorRequest.toString(), bindingResult.toString());
//        if (bindingResult.hasErrors()) {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("errorMessage", bindingResult.getAllErrors());
//            Response response = Responses.errorResponse("监督员第一阶段审核错误！");
//            response.setData(data);
//            return response;
//        }
//        Long uid = StringToLongUtil.stringToLong(id);
//        if (uid < 0) {
//            return Responses.errorResponse("error!");
//        }
//        Long success = breedingPlanAnotherService.updateARecordFirstBySupervisor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), supervisorRequest.getIspassSup(), supervisorRequest.getSupervisorId(), supervisorRequest.getSupervisorName());
//        if (success <= 0) {
//            return Responses.errorResponse("监督员第一阶段审核错误！");
//        } else {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("success", "监督员第一阶段审核成功！");
//            return Responses.successResponse(data);
//        }
//    }
//
//    /**
//     * 监督员在第二阶段审核一条记录
//     * @param id 修改记录的主键
//     * @return response相应信息
//     */
//    @PatchMapping(value = "/s/s/{id}")
//    public Response supervisorARecordSecond(@PathVariable("id")String id, @Valid @RequestBody SupervisorRequest supervisorRequest, BindingResult bindingResult) {
//        logger.info("invoke supervisorARecordSecond {}, url is b/s/s/{id}", id, supervisorRequest.toString(), bindingResult.toString());
//        if (bindingResult.hasErrors()) {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("errorMessage", bindingResult.getAllErrors());
//            Response response = Responses.errorResponse("监督员第二阶段参数错误！");
//            response.setData(data);
//            return response;
//        }
//        Long uid = StringToLongUtil.stringToLong(id);
//        if (uid < 0) {
//            return Responses.errorResponse("error!");
//        }
//        Long success = breedingPlanAnotherService.updateARecordSecondBySupervisor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), supervisorRequest.getIspassSup(), supervisorRequest.getSupervisorId(), supervisorRequest.getSupervisorName());
//        if (success <= 0) {
//            return Responses.errorResponse("监督员第二阶段审核错误！");
//        } else {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("success", "监督员第二阶段审核成功！");
//            return Responses.successResponse(data);
//        }
//    }
//
//    /**
//     * 技术员在第一阶段审核一条记录
//     * @param id 修改记录的主键
//     * @return response相应信息
//     */
//    @PatchMapping(value = "/p/f/{id}")
//    public Response professorARecordFirst(@PathVariable("id")String id, @Valid @RequestBody ProfessorRequest professorRequest, BindingResult bindingResult) {
//        logger.info("invoke professorARecordFirst {}, url is b/p/f/{id}", id, professorRequest.toString(), bindingResult.toString());
//        if (bindingResult.hasErrors()) {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("errorMessage", bindingResult.getAllErrors());
//            Response response = Responses.errorResponse("技术员第一阶段审核错误！");
//            response.setData(data);
//            return response;
//        }
//        Long uid = StringToLongUtil.stringToLong(id);
//        if (uid < 0) {
//            return Responses.errorResponse("error!");
//        }
//        Long success = breedingPlanAnotherService.updateARecordFirstByProfessor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), professorRequest.getIspassCheck(), professorRequest.getProfessorId(), professorRequest.getProfessorName());
//        if (success <= 0) {
//            return Responses.errorResponse("技术员第一阶段审核错误！");
//        } else {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("success", "技术员第一阶段审核成功！");
//            return Responses.successResponse(data);
//        }
//    }
//
//    /**
//     * 技术员在第二阶段审核一条记录
//     * @param id 修改记录的主键
//     * @return response相应信息
//     */
//    @PatchMapping(value = "/p/s/{id}")
//    public Response professorARecordSecond(@PathVariable("id")String id, @Valid @RequestBody ProfessorRequest professorRequest, BindingResult bindingResult) {
//        logger.info("invoke professorARecordSecond {}, url is b/p/s/{id}", id, professorRequest.toString(), bindingResult.toString());
//        if (bindingResult.hasErrors()) {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("errorMessage", bindingResult.getAllErrors());
//            Response response = Responses.errorResponse("技术员第二阶段审核参数错误！");
//            response.setData(data);
//            return response;
//        }
//        Long uid = StringToLongUtil.stringToLong(id);
//        if (uid < 0) {
//            return Responses.errorResponse("error!");
//        }
//        Long success = breedingPlanAnotherService.updateARecordSecondByProfessor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), professorRequest.getIspassCheck(), professorRequest.getProfessorId(), professorRequest.getProfessorName());
//        if (success <= 0) {
//            return Responses.errorResponse("技术员第二阶段审核错误！");
//        } else {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("success", "技术员第二阶段审核成功！");
//            return Responses.successResponse(data);
//        }
//    }
//}
