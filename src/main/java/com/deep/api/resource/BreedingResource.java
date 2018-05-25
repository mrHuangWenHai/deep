//package com.deep.api.resource;
//
//import com.deep.api.Utils.AgentUtil;
//import com.deep.api.Utils.StringToLongUtil;
//import com.deep.api.Utils.TokenAnalysis;
//import com.deep.api.authorization.annotation.Permit;
//import com.deep.api.authorization.tools.Constants;
//import com.deep.api.request.BreedingPlanModel;
//import com.deep.api.request.ProfessorRequest;
//import com.deep.api.request.SupervisorRequest;
//import com.deep.api.response.Response;
//import com.deep.api.response.Responses;
//import com.deep.domain.model.BreedingPlan;
//import com.deep.domain.model.BreedingPlanExample;
//import com.deep.domain.service.BreedingPlanService;
//import org.apache.ibatis.session.RowBounds;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.text.ParseException;
//import java.util.*;
//
///**
// * author: Created  By  Caojiawei
// * date: 2018/2/2  12:52
// */
//@RestController
//@RequestMapping(value = "/breeding")
//public class BreedingResource {
//    @Resource
//    private BreedingPlanService breedingPlanService;
//
//    private final Logger logger = LoggerFactory.getLogger(BreedingResource.class);
//
//    /**
//     * 添加一条记录信息
//     * @param planModel
//     * @param bindingResult
//     * 接收参数：整个表单信息（所有参数必填）
//     * 参数类型为：Long factoryNum;String building;String mEtI;String mEtB;String fEtI;String fEtB;Date breedingT; Date gestationT;Date prenatalIT;Date cubT;Integer quantity;String operator;String remark；
//     * @return
//     * @throws ParseException
//     */
//    @Permit(authorities = "increase_breeding_maternity_file")
//    @PostMapping(value = "")
//    public Response addPlan(@RequestBody @Valid BreedingPlanModel planModel, BindingResult bindingResult) throws ParseException {
//        logger.info("invoke addPlan {}, url = /breeding", planModel);
//        if (bindingResult.hasErrors()) {
//            return Responses.errorResponse("育种实施档案录入失败");
//        }else {
//            BreedingPlan insert = new BreedingPlan();
//            insert.setId(planModel.getId());
//            insert.setGmtModified(planModel.getGmtModified());
//
//            insert.setFactoryNum(planModel.getFactoryNum());
//            insert.setFactoryName(planModel.getFactoryName());
//            insert.setBuilding(planModel.getBuilding());
//
//            insert.setmEtI(planModel.getmEtI());
//            insert.setmEtB(planModel.getmEtB());
//            insert.setfEtI(planModel.getfEtI());
//            insert.setfEtB(planModel.getfEtB());
//
//            insert.setBreedingT(planModel.getBreedingT());
//            insert.setGestationT(planModel.getGestationT());
//            insert.setPrenatalIT(planModel.getPrenatalIT());
//            insert.setCubT(planModel.getCubT());
//            insert.setQuantity(planModel.getQuantity());
//
//            insert.setOperatorId(planModel.getOperatorId());
//            insert.setOperatorName(planModel.getOperatorName());
//            insert.setProfessorId(planModel.getProfessorId());
//            insert.setProfessorName(planModel.getProfessorName());
//            insert.setSupervisorName(planModel.getSupervisorName());
//            insert.setSupervisorId(planModel.getSupervisorId());
//
//            insert.setRemark(planModel.getRemark());
//            insert.setUpassReason(planModel.getUpassReason());
//
//            Byte flag = 2;
//
//            insert.setGmtCreate(new Date());
//            insert.setIsPassCheck(flag);
//            insert.setIsPassSup(flag);
//
//            int success = breedingPlanService.addPlan(insert);
//            if (success <= 0) {
//                return Responses.errorResponse("error!");
//            }
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("success",success);
//            response.setData(data);
//            return response;
//        }
//    }
//
//    /**
//     * 操作员按照主键进行修改
//     * @param planModel
//     * @param bindingResult
//     * 操作员使用接收参数：整个表单信息（整型id必填，各参数选填）
//     * @return
//     * @throws ParseException
//     */
//    @Permit(authorities = "modify_breeding_seed_file")
//    @PutMapping(value = "/{id}")
//    public Response changePlanByOperator(@RequestBody @Valid BreedingPlanModel planModel, @PathVariable("id") Integer id, BindingResult bindingResult) throws ParseException {
//        logger.info("invoke changePlanByOperator {}, url is breeding/operator", planModel);
//        if (bindingResult.hasErrors()) {
//            return Responses.errorResponse("育种实施档案(操作员页面)修改失败");
//        }else{
//            if (planModel.getIsPassCheck() != null || planModel.getIsPassSup() != null) {
//                return Responses.errorResponse("错误!");
//            }
//            //将planModel部分变量拆分传递给对象operator
//            BreedingPlan operator = new BreedingPlan();
//            operator.setId(id);
//            operator.setGmtCreate(planModel.getGmtCreate());
//            operator.setGmtModified(planModel.getGmtModified());
//            operator.setFactoryNum(planModel.getFactoryNum());
//            operator.setBuilding(planModel.getBuilding());
//
//            operator.setmEtI(planModel.getmEtI());
//            operator.setmEtB(planModel.getmEtB());
//            operator.setfEtI(planModel.getfEtI());
//            operator.setfEtB(planModel.getfEtB());
//
//            operator.setBreedingT(planModel.getBreedingT());
//            operator.setGestationT(planModel.getGestationT());
//            operator.setPrenatalIT(planModel.getPrenatalIT());
//            operator.setCubT(planModel.getCubT());
//
//            operator.setQuantity(planModel.getQuantity());
//            operator.setOperatorName(planModel.getOperatorName());
////            operator.setProfessorName(planModel.getProfessorName());
////            operator.setSupervisorName(planModel.getSupervisorName());
//            operator.setOperatorId(planModel.getOperatorId());
////            operator.setSupervisorId(planModel.getSupervisorId());
////            operator.setProfessorId(planModel.getProfessorId());
//            operator.setRemark(planModel.getRemark());
////            operator.setIsPassCheck(planModel.getIsPassCheck());
////            operator.setUpassReason(planModel.getUpassReason());
////            operator.setIsPassSup(planModel.getIsPassSup());
//
//            operator.setFactoryName(planModel.getFactoryName());
//
//            int updateID = breedingPlanService.changePlanSelective(operator);
//            if (updateID <= 0) {
//                return Responses.errorResponse("修改失败");
//            }
//
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("breeding_plan", updateID);
//            response.setData(data);
//            return response;
//        }
//    }
//
//    /**
//     * 按照主键删除一条记录
//     * @return
//     */
//    @Permit(authorities = "delete_breeding_child_files")
//    @DeleteMapping(value = "/{id}")
//    public Response dropPlan(@PathVariable("id") String id){
//        logger.info("invoke dropPlan {}, url is breeding/{id}", id);
//        int uid = StringToLongUtil.stringToInt(id);
//        System.out.println("uid is " + uid);
//        if (uid == -1) {
//            return Responses.errorResponse("查询错误");
//        } else {
//            int deleteID = breedingPlanService.dropPlan(uid);
//            if (deleteID <= 0) {
//                return Responses.errorResponse("删除失败");
//            }
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("breeding_plan",deleteID);
//            response.setData(data);
//            return response;
//        }
//    }
//
//    /**
//     * 获取该羊场的所有数据
//     * @param size
//     * @param page
//     * @return
//     */
//    @Permit(authorities = "search_for_breeding_child_files")
//    @GetMapping(value = "/{id}")
//    public Response findAllOfOneFactory(@PathVariable("id") String id,
//                                        @RequestParam(value = "size", defaultValue = "10") String size,
//                                        @RequestParam(value = "page", defaultValue = "0") String page,
//                                        @RequestParam(value = "factoryName", defaultValue = "") String factoryName,
//                                        @RequestParam(value = "ispassCheck", defaultValue = "-1") String ispassCheck, HttpServletRequest request) {
//        logger.info("invoke findAllOfOneFactory , url is breeding/{id} {}", id, size, page, factoryName, ispassCheck, request);
//        if (size == null || page == null) {
//            return Responses.errorResponse("失败");
//        }
//        int usize = StringToLongUtil.stringToInt(size);
//        int upage = StringToLongUtil.stringToInt(page);
//        byte pass = StringToLongUtil.stringToByte(ispassCheck);
//        Long factoryOrAgentID = StringToLongUtil.stringToLong(id);
//        Byte which = StringToLongUtil.stringToByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
//        System.out.println("which = " + which);
//        if (which == 0) {
//            // user is a factory user
//            BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
//            BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();
//            criteria.andFactoryNumEqualTo(factoryOrAgentID);
//            if (pass != -1) {
//                criteria.andIspassCheckEqualTo(pass);
//            }
//            List<BreedingPlan> select = breedingPlanService.findPlanSelective(breedingPlanExample,new RowBounds(upage, usize));
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("List",select);
//            data.put("size", select.size());
//            response.setData(data);
//            return response;
//        } else if (which == 2) {
//            // user is a ordinary user
//            return Responses.errorResponse("permit error, user can not get factory's message");
//        } else if (which == 1) {
//            // user is a agent user
//            BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
//            BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();
//            BreedingPlanExample breedingPlanExample1 = new BreedingPlanExample();
//            BreedingPlanExample.Criteria criteria1 = breedingPlanExample1.createCriteria();
//            if (pass != -1) {
//                criteria.andIspassCheckEqualTo(pass);
//                criteria1.andIspassCheckEqualTo(pass);
//            }
//            // It's agent message
//            // find his all subordinate factory
//            Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(String.valueOf(factoryOrAgentID));
//            if (factories == null) {
//                return Responses.errorResponse("request error!");
//            }
//            List<BreedingPlan> plans = new ArrayList<>();
//            // direct people
//            List<Long> directFactories = factories.get((long) -1);
//            List<BreedingPlan> theOther = new ArrayList<>();
//            if (directFactories != null) {
//                for (Long directFactory : directFactories) {
//                    criteria1.andFactoryNumEqualTo(directFactory);
//                    theOther.addAll(breedingPlanService.findPlanSelective(breedingPlanExample1, new RowBounds(upage, usize)));
//                }
//            }
//
//            // not direct people
//            List<Long> allFactories = factories.get((long) 0);
//            List<BreedingPlan> theOne = new ArrayList<>();
//            if (allFactories != null) {
//                for (Long allFactory : allFactories) {
//                    criteria.andFactoryNumEqualTo(allFactory);
//                    theOne.addAll(breedingPlanService.findPlanSelective(breedingPlanExample, new RowBounds(upage, usize)));
//                }
//            }
//
//            plans.addAll(theOther);
//            plans.addAll(theOne);
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("List",plans);
//            data.put("size", plans.size());
//            data.put("directSize", theOther.size());
//            response.setData(data);
//            return response;
//        }
//        return Responses.errorResponse("request error!");
//    }
//
//    /**
//     * 专家按照主键进行审核
//     * 专家使用接收参数：整个表单信息（整型id必填，各参数选填）
//     * @param professorRequest
//     * @param bindingResult
//     * @return
//     */
//    @Permit(authorities = "experts_review_mating_and_childbirth_files")
//    @PatchMapping(value = "/p/{id}")
//    public Response changePlanByProfessor(@RequestBody @Valid ProfessorRequest professorRequest, @PathVariable("id") String id, BindingResult bindingResult) {
//        logger.info("invoke changePlanByProfessor {}, url is breeding/professor", professorRequest);
//        if (bindingResult.hasErrors()) {
//            return Responses.errorResponse("育种实施档案(专家页面)修改失败");
//        }else{
//            int uid = StringToLongUtil.stringToInt(id);
//            if (uid == -1) {
//                return Responses.errorResponse("error!");
//            }
//            BreedingPlan professor = breedingPlanService.findPlanById(uid);
//            // modify the modified time
//            professor.setId(uid);
//            professor.setGmtModified(new Date());
//            professor.setIsPassCheck(professorRequest.getIspassCheck());
//            professor.setUpassReason(professorRequest.getUpassReason());
////            professor.setProfessorId(professorRequest.getProfessor());
//            professor.setProfessorName(professorRequest.getProfessorName());
//
//            int success = breedingPlanService.changePlanSelective(professor);
//            if (success <= 0) {
//                return Responses.errorResponse("错误");
//            }
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("success", success);
//            response.setData(data);
//            return response;
//        }
//    }
//
//    /**
//     * 监督员按照主键审核
//     * @param supervisorRequest
//     * @param bindingResult
//     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
//     * @return
//     */
//    @Permit(authorities = "supervise_auditing_breeding_child_files")
//    @PatchMapping(value = "/s/{id}")
//    public Response changePlanBySupervisor(@RequestBody @Valid SupervisorRequest supervisorRequest, @PathVariable("id") String id, BindingResult bindingResult) {
//        logger.info("invoke changePlanBySupervisor {}, url is breeding/supervisor", supervisorRequest);
//        if (bindingResult.hasErrors()) {
//            return Responses.errorResponse("育种实施档案(监督页面)修改失败");
//        }else{
//            int uid = StringToLongUtil.stringToInt(id);
//            if (uid == -1) {
//                return Responses.errorResponse("error!");
//            }
//            BreedingPlan supervisor = breedingPlanService.findPlanById(uid);
//            // modify the supervisor time
//            supervisor.setId(uid);
//            supervisor.setGmtSupervised(new Date());
//            supervisor.setSupervisorId(supervisorRequest.getSupervisorId());
//            supervisor.setSupervisorName(supervisorRequest.getSupervisorName());
//            supervisor.setIsPassSup(supervisorRequest.getIspassSup());
//
//            int success = breedingPlanService.changePlanSelective(supervisor);
//            if (success <= 0) {
//                return Responses.errorResponse("审核失败");
//            }
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("success",success);
//            response.setData(data);
//            return response;
//        }
//    }
//
//
//    /**
//     * 按照主键查询
//     * 接收参数：整型的主键号（保留接口查询，前端不调用此接口）
//     * @return
//     */
//    @Permit(authorities = "search_for_breeding_child_files")
//    @GetMapping(value = "/find/{id}")
//    public Response findPlanById(@PathVariable("id") String id){
//        logger.info("invoke findPlanById {}, url is breeding/{id}", id);
//        int uid = StringToLongUtil.stringToInt(id);
//        System.out.println("uid is " + uid);
//        if (uid == -1) {
//            return Responses.errorResponse("查询错误");
//        } else {
//            //查询语句的写法：一定要在声明对象时把值直接赋进去
//            BreedingPlan selectById = breedingPlanService.findPlanById(uid);
//            if (selectById == null) {
//                return Responses.errorResponse("查询错误");
//            }
//            Response response = Responses.successResponse();
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("model",selectById);
//            response.setData(data);
//            return response;
//        }
//    }
//
////    /**
////     * 按照条件查询
////     * @param planModel
////     * @param bindingResult
////     * 接收的参数：前端的各参数，以及八个("s_breedingT1")("s_breedingT2")("s_gestationT1")("s_gestationT2")("s_prenatalIT1")("s_prenatalIT2")("s_cubT1")("s_cubT2")时间字符串（所有参数可以选填）
////     * @ret urn
////     * @throws ParseException
////     */
////    @PostMapping(value = "/select")
////    public Response findPlanSelective(@RequestBody @Valid BreedingPlanModel planModel, BindingResult bindingResult) throws ParseException {
////
////        logger.info("invoke findPlanSelective {}, url is breeding/select", planModel);
////
////        if (bindingResult.hasErrors()) {
////            return Responses.errorResponse("育种实施档案(根据条件)查询失败");
////        }else {
////            //将planModel部分变量拆分传递给对象breedingPlan
////            BreedingPlan breedingPlan = new BreedingPlan();
////            breedingPlan.setId(planModel.getId());
////            breedingPlan.setGmtCreate(planModel.getGmtCreate());
////            breedingPlan.setGmtModified(planModel.getGmtModified());
////            breedingPlan.setSupervisorName(planModel.getSupervisorName());
////            breedingPlan.setFactoryNum(planModel.getFactoryNum());
////            breedingPlan.setBuilding(planModel.getBuilding());
////            breedingPlan.setmEtI(planModel.getmEtI());
////            breedingPlan.setmEtB(planModel.getmEtB());
////            breedingPlan.setfEtI(planModel.getfEtI());
////            breedingPlan.setfEtB(planModel.getfEtB());
////            breedingPlan.setBreedingT(planModel.getBreedingT());
////            breedingPlan.setGestationT(planModel.getGestationT());
////            breedingPlan.setPrenatalIT(planModel.getPrenatalIT());
////            breedingPlan.setCubT(planModel.getCubT());
////            breedingPlan.setQuantity(planModel.getQuantity());
////            breedingPlan.setOperatorName(planModel.getOperatorName());
////            breedingPlan.setProfessorName(planModel.getProfessorName());
////            breedingPlan.setSupervisorName(planModel.getSupervisorName());
////            breedingPlan.setRemark(planModel.getRemark());
////            breedingPlan.setIsPassCheck(planModel.getIsPassCheck());
////            breedingPlan.setUpassReason(planModel.getUpassReason());
////            breedingPlan.setIsPassSup(planModel.getIsPassSup());
////            //将planModel部分变量拆分传递给对象otherTime
////            OtherTime otherTime = new OtherTime();
////            otherTime.setSearch_string(planModel.getSearch_string());
////            otherTime.setS_breedingT(planModel.getS_breedingT());
////            otherTime.setS_gestationT(planModel.getS_gestationT());
////            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
////            otherTime.setS_cubT(planModel.getS_cubT());
////            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
////            otherTime.setS_nutritionT(planModel.getS_nutritionT());
////            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
////            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
////            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
////            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
////            otherTime.setS_breedingT1(planModel.getS_breedingT1());
////            otherTime.setS_breedingT2(planModel.getS_breedingT2());
////            System.out.println(otherTime.getS_breedingT1()+"---"+otherTime.getS_breedingT2());
////            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
////            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
////            otherTime.setS_gestationT1(planModel.getS_gestationT1());
////            otherTime.setS_gestationT2(planModel.getS_gestationT2());
////            otherTime.setS_cubT1(planModel.getS_cubT1());
////            otherTime.setS_cubT2(planModel.getS_cubT2());
////            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
////            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
////            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
////            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
////            otherTime.setDownloadPath(planModel.getDownloadPath());
////            otherTime.setPage(planModel.getPage());
////            otherTime.setSize(planModel.getSize());
////
////            BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
////            BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();
////            Date breedingT1 = null;
////            Date breedingT2 = null;
////            Date gestationT1 = null;
////            Date gestationT2 = null;
////            Date prenatalIT1 = null;
////            Date prenatalIT2 = null;
////            Date cubT1 = null;
////            Date cubT2 = null;
////            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
////            if (otherTime.getS_breedingT1() != null && !otherTime.getS_breedingT1().isEmpty() && otherTime.getS_breedingT2() != null && !otherTime.getS_breedingT2().isEmpty()){
////                breedingT1 =  formatter.parse(otherTime.getS_breedingT1());
////                breedingT2 =  formatter.parse(otherTime.getS_breedingT2());
////            }
////            if (otherTime.getS_gestationT1() != null && !otherTime.getS_gestationT1().isEmpty() && otherTime.getS_gestationT2() != null && !otherTime.getS_gestationT2().isEmpty()){
////                gestationT1 =  formatter.parse(otherTime.getS_gestationT1());
////                gestationT2 =  formatter.parse(otherTime.getS_gestationT2());
////            }
////            if (otherTime.getS_prenatalIT1() != null && !otherTime.getS_prenatalIT1().isEmpty() && otherTime.getS_prenatalIT2() != null && !otherTime.getS_prenatalIT2().isEmpty()){
////                prenatalIT1 =  formatter.parse(otherTime.getS_prenatalIT1());
////                prenatalIT2 =  formatter.parse(otherTime.getS_prenatalIT2());
////            }
////            if (otherTime.getS_cubT1() != null && !otherTime.getS_cubT1().isEmpty() && otherTime.getS_cubT2() != null && !otherTime.getS_cubT2().isEmpty()){
////                cubT1 =  formatter.parse(otherTime.getS_cubT1());
////                cubT2 =  formatter.parse(otherTime.getS_cubT2());
////            }
////            if(breedingT1 != null && breedingT2 != null ){
////                criteria.andBreedingTBetween(breedingT1,breedingT2);
////            }
////            if(gestationT1 != null && gestationT2 != null ){
////                criteria.andGestationTBetween(gestationT1,gestationT2);
////            }
////            if(prenatalIT1 != null && breedingT2 != null ){
////                criteria.andPrenatalITBetween(prenatalIT1,prenatalIT2);
////            }
////            if(cubT1 != null && cubT2 != null ){
////                criteria.andCubTBetween(cubT1,cubT2);
////            }
////            if(breedingPlan.getId() != null && !breedingPlan.getId().toString().isEmpty()){
////                criteria.andIdEqualTo(breedingPlan.getId());
////            }
////            if(breedingPlan.getFactoryNum() != null && !breedingPlan.getFactoryNum().toString().isEmpty()){
////                criteria.andFactoryNumEqualTo(breedingPlan.getFactoryNum());
////            }
////            if(breedingPlan.getBuilding() != null && !breedingPlan.getBuilding().isEmpty()){
////                criteria.andBuildingEqualTo(breedingPlan.getBuilding());
////            }
////            if(breedingPlan.getmEtI() != null && !breedingPlan.getmEtI().isEmpty()){
////                criteria.andMEtIEqualTo(breedingPlan.getmEtI());
////            }
////            if(breedingPlan.getmEtB() != null && !breedingPlan.getmEtB().isEmpty()){
////                criteria.andMEtBEqualTo(breedingPlan.getmEtB());
////            }
////            if(breedingPlan.getfEtI() != null && !breedingPlan.getfEtI().isEmpty()){
////                criteria.andFEtIEqualTo(breedingPlan.getfEtI());
////            }
////            if(breedingPlan.getfEtB() != null && !breedingPlan.getfEtB().isEmpty()){
////                criteria.andFEtBEqualTo(breedingPlan.getfEtB());
////            }
////            if(breedingPlan.getQuantity() != null && !breedingPlan.getQuantity().toString().isEmpty()){
////                criteria.andQuantityEqualTo(breedingPlan.getQuantity());
////            }
////            if(breedingPlan.getOperatorName() != null && !breedingPlan.getOperatorName().isEmpty()){
////                criteria.andOperatorNameEqualTo(breedingPlan.getOperatorName());
////            }
////            if(breedingPlan.getProfessorName() != null && !breedingPlan.getProfessorName().isEmpty()){
////                criteria.andProfessorNameEqualTo(breedingPlan.getProfessorName());
////            }
////            if(breedingPlan.getSupervisorName() != null && !breedingPlan.getSupervisorName().isEmpty()){
////                criteria.andSupervisorNameEqualTo(breedingPlan.getSupervisorName());
////            }
////            if(breedingPlan.getIsPassCheck() != null && !breedingPlan.getIsPassCheck().toString().isEmpty()){
////                criteria.andIspassCheckEqualTo(breedingPlan.getIsPassCheck());
////            }
////            if(breedingPlan.getUpassReason() != null && !breedingPlan.getUpassReason().isEmpty()){
////                criteria.andUpassReasonLike(breedingPlan.getUpassReason());
////            }
////            if(breedingPlan.getIsPassSup() != null && !breedingPlan.getIsPassSup().toString().isEmpty()){
////                criteria.andIspassSupEqualTo(breedingPlan.getIsPassSup());
////            }
////            System.out.println(otherTime.getPage());
////            System.out.println(otherTime.getSize());
////            List<BreedingPlan> selective = breedingPlanService.findPlanSelective(breedingPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
////            Response response = Responses.successResponse();
////            HashMap<String, Object> data = new HashMap<>();
////            data.put("List",selective);
////            response.setData(data);
////            return response;
////        }
////    }
//}
//
