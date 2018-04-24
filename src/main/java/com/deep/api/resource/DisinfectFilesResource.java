package com.deep.api.resource;

import com.deep.api.request.DisinfectRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.api.response.ValidResponse;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.service.DisinfectFilesService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.DownloadUtil;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.domain.util.UploadUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/df",method = RequestMethod.GET)
public class DisinfectFilesResource {

    private final Logger logger = LoggerFactory.getLogger(DisinfectFilesResource.class);

    @Resource
    private DisinfectFilesService disinfectFilesService;

    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;

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
            //仅允许上传.txt .rtf(日记本格式) .doc .docx
            /*String Header = FileUtil.getFileHeader(repellentEartagFile);
            if ( !"75736167".equals(Header) && !"7B5C727466".equals(Header) &&
                    !"D0CF11E0".equals(Header) && !"504B0304".equals(Header)) {
                return Responses.errorResponse("Wrong file form");
            }*/

            DisinfectFilesModel disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(disinfectFilesModel.getFactoryNum(), disinfectFilesModel.getDisinfectTime(), disinfectFilesModel.getDisinfectName());
            if (disinfectFilesModel1 == null) {

                try {

                    String fileName = disinfectEartagFile.getOriginalFilename();
                    //目的路径
                    String filePath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/disinfectEartag/";
                    String fileAddress = "";
                    fileAddress = UploadUtil.uploadFile(disinfectEartagFile.getBytes(),filePath,fileName,fileAddress);

                    //System.out.println("save before");
                    //数据插入数据库
                    //System.out.println("mysql执行前");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    disinfectFilesModel.setDisinfectEartag(fileAddress);
                    disinfectFilesModel.setIsPass("0");
                    disinfectFilesModel.setIsPass1("0");
                    disinfectFilesModel.setGmtCreate(dateFormat.format(new Timestamp(System.currentTimeMillis())));

                    disinfectFilesService.setDisinfectFilesModel(disinfectFilesModel);

                    //System.out.println("pppppppppppppppppppppppppppp  "+ disinfectFilesModel.getId());
                    //数据插入redis
                    String professorKey = disinfectFilesModel.getFactoryNum().toString() + "_disinfectFiles_professor";
                    String supervisorKey = disinfectFilesModel.getFactoryNum().toString() + "_disinfectFiles_supervisor";
                    String testSendProfessor = disinfectFilesModel.getFactoryNum().toString() + "_disinfectFiles_professor_AlreadySend";
                    String testSendSupervisor = disinfectFilesModel.getFactoryNum().toString() + "_disinfectFiles_supervisor_AlreadySend";

                    String professorWorkInRedis = disinfectFilesModel.getId().toString()+ "_disinfectFiles_professor_worked";
                    String supervisorWorkInRedis = disinfectFilesModel.getId().toString()+ "_disinfectFiles_supervisor_worked";

                    //System.out.println("professorWorkInRedis:"+professorWorkInRedis);
                    //System.out.println("supervisorWorkInRedis:"+supervisorWorkInRedis);


                    JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                    JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                    JedisUtil.setCertainKeyValue(professorWorkInRedis,"0");
                    JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"0");
                    System.out.println("插入后,审核前");
                    System.out.println("pk+"+professorKey+" "+"pv:"+JedisUtil.getCertainKeyValue(professorKey));
                    System.out.println("sk+"+supervisorKey+" "+"sv:"+JedisUtil.getCertainKeyValue(supervisorKey));
                    System.out.println("tpk+"+testSendProfessor+" "+"tpv:"+JedisUtil.getCertainKeyValue(testSendProfessor));
                    System.out.println("tsk+"+testSendSupervisor+" "+"tsv:"+JedisUtil.getCertainKeyValue(testSendSupervisor));
                    System.out.println("pkir+"+professorWorkInRedis+" "+"pvir:"+JedisUtil.getCertainKeyValue(professorWorkInRedis));
                    System.out.println("skir+"+supervisorWorkInRedis+" "+"svir:"+JedisUtil.getCertainKeyValue(supervisorWorkInRedis));

                    //System.out.println("Have sent:"+jedisUtil.getCertainKeyValue(testSendProfessor));

                    //发送短信后 testSendProfessor存放在redis中 过期时间为ExpireTime
                    if( !("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                        //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                        if( JedisUtil.redisJudgeTime(professorKey) ) {

                            System.out.println("in redis:");

                            List<String> phone = userService.getUserTelephoneByfactoryNum(disinfectFilesModel.getFactoryNum());

                            //需完成:userModels.getTelephone()赋值给String
                            // 获得StringBuffer手机号
                            StringBuffer phoneList = new StringBuffer("");

                            for (String aPhone : phone) {
                                phoneList = phoneList.append(aPhone).append(",");
                            }

                            if ("".equals(phoneList.toString())){
                                System.out.println("here");

                              //
                            } else {
                                //发送成功 更新redis中字段
                                if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                                }
                            }

                        }


                    } else {
                            System.out.println("professor:3天内已发送");
                    }


                    if( !("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                        if(JedisUtil.redisJudgeTime(supervisorKey)) {
                            List<String> phone = userService.getUserTelephoneByfactoryNum(disinfectFilesModel.getFactoryNum());

                            StringBuffer phoneList = new StringBuffer("");


                            for (String aPhone : phone) {
                                phoneList = phoneList.append(aPhone).append(",");

                            }
                            if ("".equals(phoneList.toString())) {

                                System.out.println(phoneList);
                                return Responses.errorResponse("Not found telephone");
                            } else {
                                if( JedisUtil.redisSendMessage(phoneList.toString(),JedisUtil.getCertainKeyValue("Message"))) {
                                    //System.out.println("发送成功！");

                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime"))*24*60*60);
                                    return JudgeUtil.JudgeSuccess("successMessage","Message Sent");
                                }
                            }
                        }

                    } else {
                        System.out.println("supervisor:3天内已发送");
                    }

                    return JudgeUtil.JudgeSuccess("id",disinfectFilesModel.getId());


                    //jedisUtil.redisSaveProfessorSupervisorWorks(professorKey,factoryNum);
                    //jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey,factoryNum);
                } catch (Exception e) {

                    e.printStackTrace();
                }

            } else {
                return Responses.errorResponse("Already Exist");
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
        List<DisinfectFilesModel> disinfectFilesModels = disinfectFilesService.getDisinfectFilesModel(disinfectRequest,
                new RowBounds(disinfectRequest.getPage(),disinfectRequest.getSize()));

        return JudgeUtil.JudgeFind(disinfectFilesModels,disinfectFilesModels.size());
    }

    /**
     * 下载文件 并保存到自定义路径
     * @param response HttpServletResponse
     * @throws Exception 下载文件异常
     */
    @RequestMapping(value = "/down",method = RequestMethod.GET)
    public Response download(HttpServletResponse response,
                         @Param("file") String file,
                         @Param("locate") String locate)throws Exception{
        logger.info("invoke download {}", response, file, locate);
        String filePath = "../EartagDocument/disinfectEartag/";
        if (DownloadUtil.downloadFile(response , file, filePath, locate)){
            return JudgeUtil.JudgeSuccess("download","Success");
        }else {
            return Responses.errorResponse("download Error");
        }
    }




    //更新接口
    //权限仅为专家和监督员
    /**
     * 专家入口 查看isPass = 0或者isPass = 1的数据
     * METHOD:GET
     * @param isPass 审核标志位
     * @param page  页号
     * @param size  条数
     * @return 查询结果/查询结果条数

     */
    @RequestMapping(value = "/pfind",method = RequestMethod.POST)
    public Response professorFind(@RequestBody Map<String, Integer> json) {

        Integer isPass;
        if (!json.containsKey("isPass")) {
            return Responses.errorResponse("lack param isPass");
        }
        isPass = json.get("isPass");

        int page = 0;
        if (json.containsKey("page")) {
            page = json.get("page");
        }

        int size = 10;
        if (json.containsKey("size")) {
            size = json.get("size");
        }

        logger.info("invoke professorFind {}", isPass, page, size);
        if ("2".equals(isPass.toString())) {
            return Responses.errorResponse("Wrong Pass num");
        }
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesService.getDisinfectFilesModelByProfessor(isPass,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(disinfectFilesModels,disinfectFilesModels.size());
    }

    /**
     * 审核入口 审核isPass = 0的数据
     * METHOD:PATCH
     * @param disinfectFilesModel 消毒类
     * @return 更新结果
     */
    @RequestMapping(value = "/pupdate",method = RequestMethod.PATCH)
    public Response professorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel) {

        logger.info("invoke professorUpdate {}", disinfectFilesModel);

        if (disinfectFilesModel.getId() == null ||
                disinfectFilesModel.getProfessor() == null ||
                disinfectFilesModel.getIsPass() == null) {

            return Responses.errorResponse("Lack Item");

        } else {

            DisinfectFilesModel disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModelByid(disinfectFilesModel.getId());
            String professorWorkInRedis = disinfectFilesModel1.getId().toString() + "_disinfectFiles_professor_worked";

            if (disinfectFilesModel1.getIsPass().equals("1") ) {

                return Responses.errorResponse("Already update");
            } else {

                if ("1".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))) {

                    //生成更新当前时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    disinfectFilesModel.setGmtProfessor(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

                    int row = this.disinfectFilesService.updateDisinfectFilesModelByProfessor(disinfectFilesModel);
                    return JudgeUtil.JudgeUpdate(row);
                } else {

                    int row = this.disinfectFilesService.updateDisinfectFilesModelByProfessor(disinfectFilesModel);

                    if (row == 1) {
                        return JudgeUtil.JudgeUpdate(row);
                    } else {


                        //??? 这是做什么的啊？？？？？？
                        //删除成功 redis数据库种对应数据-1
                        String professorKey = disinfectFilesModel1.getFactoryNum().toString() + "_disinfectFiles_professor";

                        //key->value-1 返回true
                        if (JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
                            return JudgeUtil.JudgeUpdate(row);
                        } else {
                            //此时数据库出现较大问题
                            //未完成工作实际数量与redis记录不一样
                            return Responses.errorResponse("Inner Error");
                        }
                    }
                }
            }
        }

    }

    /**
     * 审核入口 展示所有isPass1 = 0或者isPass1 = 1的数据
     * @param isPass1 审核标志位
     * @param page   页码
     * @param size   条数
     * METHOD:GET
     * @return 查询结果
     */
    @RequestMapping(value = "/sfind",method = RequestMethod.GET)
    public Response supervisorFind(@RequestParam(value = "isPass1",defaultValue = "2") Integer isPass1,
                                   @RequestParam(value = "page",defaultValue = "0") int page,
                                   @RequestParam(value = "size",defaultValue = "10") int size) {

        logger.info("invoke supervisorFind {}", isPass1, page, size);
        if ("2".equals(isPass1.toString())) {
            return Responses.errorResponse("Wrong Pass Num");
        }
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesService.getDisinfectFilesModelBySupervisor(isPass1,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(disinfectFilesModels,disinfectFilesModels.size());
    }



    /**
     * 监督员入口 审核isPass1 = 0的数据
     * 审核要求:审核时要求条例写完整 审核后 isPass = 1时 无权限再修改
     * @param disinfectFilesModel 消毒类
     * METHOD:PATCH
     * @return 审核结果
     */
    @RequestMapping(value = "/supdate",method = RequestMethod.PATCH)
    public Response supervisorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel) {

        logger.info("invoke supervisorUpdate {}", disinfectFilesModel);

        if (disinfectFilesModel.getId() == null ||
                disinfectFilesModel.getSupervisor() == null ||
                disinfectFilesModel.getIsPass1() == null ) {

            return Responses.errorResponse("Lack Item");

        } else {

            DisinfectFilesModel disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModelByid(disinfectFilesModel.getId());
            String supervisorWorkInRedis = disinfectFilesModel1.getId().toString() + "_disinfectFiles_supervisor_worked";


            if (disinfectFilesModel1.getIsPass1().equals("1") ) {

                return Responses.errorResponse("Already update");
            }else {
                //生成更新当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

                if ("1".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))) {

                    disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = this.disinfectFilesService.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);
                    return JudgeUtil.JudgeUpdate(row);

                }else {

                    disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = this.disinfectFilesService.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);


                    if (row == 0) {

                        return JudgeUtil.JudgeUpdate(row);

                    } else {

                        //删除成功 redis数据库种对应数据-1
                        String supervisorKey = disinfectFilesModel1.getFactoryNum().toString() + "_disinfectFiles_supervisor";
                        disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

                        JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"1");
                        //key->value-1 返回true
                        if (JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)) {
                            return JudgeUtil.JudgeUpdate(row);
                        } else {
                            //此时数据库出现较大问题
                            //未完成工作实际数量与redis记录不一样
                            return Responses.errorResponse("Inner Error");
                        }

                    }
                }
            }
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
    @RequestMapping(value = "/oupdate",method = RequestMethod.PATCH)
    public Response operatorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel) {


        logger.info("invoke operatorUpdate {}", disinfectFilesModel);

        if (disinfectFilesModel.getId() == null) {

            return Responses.errorResponse("Operate wrong");
        } else if("1".equals(disinfectFilesModel.getIsPass()) && "1".equals(disinfectFilesModel.getIsPass1() )){
            return Responses.errorResponse("Already update");

        } else{
            DisinfectFilesModel disinfectFilesModel1 = this.disinfectFilesService.getDisinfectFilesModelByid(disinfectFilesModel.getId());

            String professorWorkInRedis = disinfectFilesModel1.getId().toString()+ "_immunePlan_professor_worked";
            String supervisorWorkInRedis = disinfectFilesModel1.getId().toString()+ "_immunePlan_supervisor_worked";

            //即专家未审核 操作员需要修改
            if("0".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))) {
                //无需修改 在下个语句修改
                System.out.println("running");

            }else {

                //专家已审核 退回后的数据
                String professorKey = disinfectFilesModel1.getFactoryNum().toString()+ "_disinfectFiles_professor";

                //置0 专家可操作
                JedisUtil.setCertainKeyValue(professorWorkInRedis,"0");

                //redis数据库中对应字段+1
                JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
            }

            //监督员未审核 操作员需要修改
            if ("0".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))) {
                //System.out.println("No redis");

                int row = this.disinfectFilesService.updateDisinfectFilesModelByOperator(disinfectFilesModel);
                return JudgeUtil.JudgeUpdate(row);

            } else {

                //专家已审核 退回后的数据
                int row = this.disinfectFilesService.updateDisinfectFilesModelByOperator(disinfectFilesModel);
                String supervisorKey = disinfectFilesModel1.getFactoryNum().toString()+ "_disinfectFiles_supervisor";

                JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"0");
                //redis数据库中对应字段+1
                JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                return JudgeUtil.JudgeUpdate(row);
            }

        }
    }

    /**
     * &#x5220;&#x9664;id = certain &#x7684;&#x6570;&#x636e;
     * &#x6743;&#x9650;&#x8bbe;&#x7f6e;
     * @param id id
     * @return &#x5220;&#x9664;&#x7ed3;&#x679c;
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)

    public Response delete(@RequestParam(value = "id",defaultValue = "0") Long id) {
        logger.info("invoke delete {}", id);
        if ("0".equals(id.toString())) {
            return Responses.errorResponse("Wrong id");
        }
        int row = this.disinfectFilesService.deleteDisinfectFilesModelByid(id);
        return JudgeUtil.JudgeDelete(row);
    }

}
