package com.deep.api.resource;

import com.deep.api.request.DisinfectRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.service.DisinfectFilesService;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.*;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;


@RestController
@RequestMapping(value = "/df",method = RequestMethod.GET)
public class DisinfectFilesResource {

    private final Logger logger = LoggerFactory.getLogger(DisinfectFilesResource.class);
    private final String pathPre = "../EartagDocument/";

    @Resource
    private DisinfectFilesService disinfectFilesService;

    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;

    //用于查询羊厂代理id
    @Resource
    private FactoryService factoryService;
    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     *
     * 同时 若累计未审核超过50条（自定义）
     * 3天内未通知对应专家/审核员
     * 则通知 不然返回已发送
     * METHOD:POST
     * @param disinfectFilesModel 消毒类
     * @return 插入结果
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Response saveShow(@Valid DisinfectFilesModel disinfectFilesModel,
                             BindingResult bindingResult,
                             @RequestParam(value = "disinfectEartagFile") MultipartFile disinfectEartagFile,
                             HttpServletRequest request
                            ) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse(bindingResult.toString());
        }
        logger.info("invoke save {}", disinfectFilesModel, disinfectEartagFile, request);
        if( disinfectEartagFile.isEmpty() ) {
            return Responses.errorResponse("Lack Item");
        } else {
                try {
                    //System.out.println("running");
                    String fileName = disinfectEartagFile.getOriginalFilename();
                    //目的路径
                    String filePath = pathPre + disinfectFilesModel.getFactoryNum().toString() + "/disinfectEartag/";
                    fileName = UploadUtil.uploadFile(disinfectEartagFile.getBytes(),filePath,fileName);
                    //System.out.println("save before");

                    //数据插入数据库
                    //System.out.println("mysql执行前");

                    disinfectFilesModel.setDisinfectEartag(fileName);

                    disinfectFilesService.setDisinfectFilesModel(disinfectFilesModel);

                    //System.out.println("pppppppppppppppppppppppppppp  "+ disinfectFilesModel.getId());
                    //数据插入redis


                    //professor字段为 代理ID + _professor
                    //supervisor字段为 工厂号 + _supervisor
                    short agentID = this.factoryService.getAgentIDByFactoryNumber(disinfectFilesModel.getFactoryNum().toString());
                    String professorKey = agentID + "_professor";
                    String supervisorKey = disinfectFilesModel.getFactoryNum().toString() + "_supervisor";

                    String testSendProfessor = agentID + "_professor_AlreadySend";
                    String testSendSupervisor = disinfectFilesModel.getFactoryNum().toString() + "_supervisor_AlreadySend";

                    JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                    JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);



                    System.out.println("插入后,审核前");
                    System.out.println("pk+"+professorKey+" "+"pv:"+JedisUtil.getCertainKeyValue(professorKey));
                    System.out.println("sk+"+supervisorKey+" "+"sv:"+JedisUtil.getCertainKeyValue(supervisorKey));
                    System.out.println("tpk+"+testSendProfessor+" "+"tpv:"+JedisUtil.getCertainKeyValue(testSendProfessor));
                    System.out.println("tsk+"+testSendSupervisor+" "+"tsv:"+JedisUtil.getCertainKeyValue(testSendSupervisor));

                    //发送短信后 testSendProfessor存放在redis中 过期时间为ExpireTime
                    if( !("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                        //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                        if( JedisUtil.redisJudgeTime(professorKey) ) {

                            System.out.println("in redis:");

                            List<String> phone = userService.getProfessorTelephoneByFactoryNum(disinfectFilesModel.getFactoryNum());

                            //需完成:userModels.getTelephone()赋值给String
                            // 获得StringBuffer手机号
                            StringBuffer phoneList = new StringBuffer("");

                            for (String aPhone : phone) {
                                phoneList = phoneList.append(aPhone).append(",");
                            }

                            if (phoneList.length() != 0) {
                                if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                                }
                            }


                        }
                    }

                    if( !("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                        if(JedisUtil.redisJudgeTime(supervisorKey)) {
                            List<String> phone = userService.getSupervisorTelephoneByFactoryNum(disinfectFilesModel.getFactoryNum());

                            StringBuffer phoneList = new StringBuffer("");

                            for (String aPhone : phone) {
                                phoneList = phoneList.append(aPhone).append(",");
                            }

                            if (phoneList.length() != 0) {

                                if( JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime"))*24*60*60);
                                }
                            }
                        }

                    }
                    return JudgeUtil.JudgeSuccess("id",disinfectFilesModel.getId());

                } catch (Exception e) {

                    e.printStackTrace();
                }

        }
        return Responses.errorResponse("Exception");

    }

    /**
     * 返回查询结果
     * 以json格式返回前端
     * 分页查询
     * METHOD:POST
     * @param disinfectRequest 消毒请求类
     * @return 查询结果/查询结果条数
     */
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response findShow(@RequestBody DisinfectRequest disinfectRequest) {
        logger.info("invoke findShow {}",disinfectRequest);

        if( disinfectRequest.getSize() == 0) {
            disinfectRequest.setSize(10);
        }
        System.out.println(disinfectRequest.getDisinfectTimeStart() + "  " + disinfectRequest.getDisinfectName());
        List<DisinfectFilesModel> disinfectFilesModels = disinfectFilesService.getDisinfectFilesModel(disinfectRequest,
                new RowBounds(disinfectRequest.getPage() * disinfectRequest.getSize(),disinfectRequest.getSize()));

        return JudgeUtil.JudgeFind(disinfectFilesModels,disinfectFilesModels.size());
    }

    /**
     * 用于id查询
     * @param id id
     * @return 查询结果
     */
    @RequestMapping(value = "/find/{id}",method = RequestMethod.GET)
    public Response find(@PathVariable("id") long id){

        logger.info(" invoke find{id} {}" , id);
        DisinfectFilesModel disinfectFilesModel = this.disinfectFilesService.getDisinfectFilesModelById(id);
        return JudgeUtil.JudgeFind(disinfectFilesModel);
    }


    /**
     * 查看某专家/监督员负责的工厂
     * 前端需要传代理ID
     * @param id 代理ID
     * @return 查询结果
     */
    @RequestMapping(value = "/psfind",method = RequestMethod.GET)
    public Response psFind(@RequestParam("id") Long id,
                           @RequestParam(value = "factoryNum",required = false)BigInteger factoryNum,
                           @RequestParam(value = "page" , defaultValue = "0") int page,
                           @RequestParam(value = "size" , defaultValue = "10") int size){
        logger.info(" invoke psFind{id} {}" , id);

        long[] factoryIDs = this.factoryService.queryFactoryIDByAgentID(id);
        List<DisinfectFilesModel> list = new ArrayList<>();
        if (factoryNum != null ) {
            list.addAll(disinfectFilesService.getDisinfectFilesModelByFactoryNum(factoryNum, new RowBounds(page * size , size)));
        } else {
            for (long factoryID : factoryIDs) {
                list.addAll(disinfectFilesService.getDisinfectFilesModelByFactoryNum(BigInteger.valueOf(factoryID) , new RowBounds(page * size , size)));
            }
        }
        return JudgeUtil.JudgeFind(list , list.size());
    }


    /**
     * 下载文件 并保存到自定义路径
     * @param response  HttpServletResponse
     * @param factoryNum  下载文件所属工厂号
     * @param file  文件名
     * @param locate  目的地址
     * @return  下载结果
     */
    @RequestMapping(value = "/down/{num}/{file}/{locate}",method = RequestMethod.GET)
    public Response download(HttpServletResponse response,
                         @PathVariable("num") String factoryNum,
                         @PathVariable("file") String file,
                         @PathVariable("locate") String locate){
        logger.info("invoke download {}", response, file, locate);
        String filePath = "../EartagDocument" + factoryNum + "/disinfectEartag/";
        if (DownloadUtil.downloadFile(response , file, filePath, locate)) {
            return JudgeUtil.JudgeSuccess("download","Success");
        } else {
            return Responses.errorResponse("download Error");
        }
    }




    /**
     * 审核入口 审核isPass = 0的数据
     * METHOD:PATCH
     * @param disinfectFilesModel 消毒类
     * @return 更新结果
     */
    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response professorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel) {

        logger.info("invoke professorUpdate {}", disinfectFilesModel);
        if(disinfectFilesModel.getId() == null ||
                disinfectFilesModel.getFactoryNum() == null ||
                disinfectFilesModel.getIspassCheck() == null ||
                disinfectFilesModel.getUnpassReason() == null) {
            return Responses.errorResponse("Lack Item");
        } else {
            int row = disinfectFilesService.updateDisinfectFilesModelByProfessor(disinfectFilesModel);
            if (row == 1) {
                String professorKey = this.factoryService.getAgentIDByFactoryNumber(disinfectFilesModel.getFactoryNum().toString()) + "_professor";
                if (!JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
                    return Responses.errorResponse("cancel error");
                }
            }
            return JudgeUtil.JudgeUpdate(row);
        }

    }


    /**
     * 监督员入口 审核isPass1 = 0的数据
     * 审核要求:审核时要求条例写完整 审核后 isPass = 1时 无权限再修改
     * @param disinfectFilesModel 消毒类
     * METHOD:PATCH
     * @return 审核结果
     */
    @RequestMapping(value = "supdate",method = RequestMethod.PATCH)
    public Response supervisorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel){
        logger.info("invoke supervisorUpdate {}", disinfectFilesModel);
        if(disinfectFilesModel.getId() == null ||
                disinfectFilesModel.getFactoryNum() == null ||
                disinfectFilesModel.getSupervisor() == null ||
                disinfectFilesModel.getIspassSup() == null){
            return Responses.errorResponse("Lack Item");

        } else {
          int row = disinfectFilesService.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);
          if (row == 1) {
            String supervisorKey = disinfectFilesModel.getFactoryNum().toString() + "_supervisor";
            if (!JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)){
                return Responses.errorResponse("cancel error");
            }
          }
          return JudgeUtil.JudgeUpdate(row);
        }
    }

    /**
     * 操作员在审核前想修改数据的接口
     * 或处理被退回操作的接口
     *
     * 行为1 与redis数据库无关
     * 行为2 redis对应数据字段+1
     * @param disinfectFilesModel 消毒类
     * @return 更新结果
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Response operatorUpdate(@Validated DisinfectFilesModel disinfectFilesModel,
                                   BindingResult bindingResult,
                                   @RequestParam(value = "disinfectEartagFile", required = false) MultipartFile disinfectEartagFile) {

        if (bindingResult.hasErrors()) {
            Response response = Responses.successResponse();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("error",bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        logger.info("invoke operatorUpdate {}", disinfectFilesModel);

        if (disinfectFilesModel.getId() == null) {
            return Responses.errorResponse("Operate wrong");
        }

        if (disinfectEartagFile != null) {

            String filePath = pathPre + disinfectFilesModel.getFactoryNum().toString() + "/disinfectEartag/";
            String fileName = disinfectEartagFile.getOriginalFilename();
            try {
                fileName =  UploadUtil.uploadFile(disinfectEartagFile.getBytes(),filePath,fileName);
            } catch (Exception e) {
                return Responses.errorResponse("update file error");
            }

            String oldPath = filePath + disinfectFilesModel.getDisinfectEartag();
            disinfectFilesModel.setDisinfectEartag(fileName);
            int row = this.disinfectFilesService.updateDisinfectFilesModelByOperatorName(disinfectFilesModel);
            if (row == 1) {
                File file = new File(oldPath);
                file.delete();
            } else {
                String newPath = filePath + fileName;
                File file = new File(newPath);
                file.delete();
            }
            return JudgeUtil.JudgeUpdate(row);
        } else {
            int row = this.disinfectFilesService.updateDisinfectFilesModelByOperatorName(disinfectFilesModel);
            return JudgeUtil.JudgeUpdate(row);
        }
    }

    /**
     * 删除对应ID数据
     * @param id id
     * @return 是否成功
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Response delete(@Min(0) @PathVariable(value = "id") Long id) {
        logger.info("invoke delete {}", id);
        if ("0".equals(id.toString())) {
            return Responses.errorResponse("Wrong id");
        }
        DisinfectFilesModel disinfectFilesModel = this.disinfectFilesService.getDisinfectFilesModelById(id);
        String filePath = pathPre + disinfectFilesModel.getFactoryNum() + "/disinfectEartag/"+disinfectFilesModel.getDisinfectEartag();
        int row = this.disinfectFilesService.deleteDisinfectFilesModelById(id);
        if (FileUtil.deleteFile(filePath) && row == 1){
            return JudgeUtil.JudgeDelete(row);
        } else {
            return Responses.errorResponse("delete wrong");
        }

    }

}
