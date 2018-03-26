package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.DisinfectFilesService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.UploadUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

import java.util.List;


@Controller
@RequestMapping(value = "/allfunction/df",method = RequestMethod.GET)
public class DisinfectFilesResourceController {

    @Resource
    private DisinfectFilesService disinfectFilesService;
    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/function",method = RequestMethod.POST)
    public String DisinfectFilesFunctionChoice(){
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DisinfectFilesModel disinfectFilesModel = disinfectFilesService.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(new BigInteger("2011"),"2018-03-01","消毒液");
        System.out.println(disinfectFilesModel.getDisinfectTime()+"   "+simpleDateFormat.format(disinfectFilesModel.getGmtCreate()));
        System.out.println(disinfectFilesModel.getDisinfectTime().compareTo(simpleDateFormat.format(disinfectFilesModel.getGmtCreate())));*/
        return "DisinfectFilesHTML/DisinfectFilesFunctionChoiceForm";
    }

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String Save(){
        return "DisinfectFilesHTML/DisinfectFilesSaveForm";
    }

    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * METHOD:POST
     * @param disinfectFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@RequestBody DisinfectFilesModel disinfectFilesModel
                             //@RequestParam("professor") String professor,    //可空 技术审核时自动生成
                             //@RequestParam("supervisor") String supervisor,   //可空 监督员审核时自动生成
                             //@RequestParam("isPass") String isPass,    //可空 由技术审核填写 默认值 未审核
                             //@RequestParam("unpassReason") String unpassReason,   //可空 由技术审核填写 默认值 无
                             //表单提交自动生成
                             //@RequestParam("gmtCreate") Timestamp gmtCreate,
                             //@RequestParam("gmtModified") Timestamp gmtModified,
                             //@RequestParam("gmtProfessor") Timestamp gmtProfessor,   //可空 技术审核填写后自动生成
                             //@RequestParam("gmtSupervise") Timestamp gmtSupervise    //可空 监督员审核后自动生成
                            ){
        if("".equals(disinfectFilesModel.getFactoryNum().toString())||
                "".equals(disinfectFilesModel.getDisinfectTime())||
                "".equals(disinfectFilesModel.getDisinfectName())||
                "".equals(disinfectFilesModel.getDisinfectQuality())||
                "".equals(disinfectFilesModel.getDisinfectWay())||
                "".equals(disinfectFilesModel.getOperator())||
                "".equals(disinfectFilesModel.getRemark())){
            return new Response().addData("Error","Lack Item");
        }else {
            DisinfectFilesModel disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(disinfectFilesModel.getFactoryNum(), disinfectFilesModel.getDisinfectTime(), disinfectFilesModel.getDisinfectName());
            if (disinfectFilesModel1 == null) {
                try{
                    //System.out.println("save before");
                    //数据插入数据库
                    //System.out.println("mysql执行前");
                    String ispass = "0";
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    disinfectFilesService.setDisinfectFilesModel(new DisinfectFilesModel(disinfectFilesModel.getFactoryNum(), disinfectFilesModel.getDisinfectTime(),
                            disinfectFilesModel.getDisinfectName(), disinfectFilesModel.getDisinfectQuality(),
                            disinfectFilesModel.getDisinfectWay(), disinfectFilesModel.getOperator(),
                            disinfectFilesModel.getRemark(), ispass, ispass, timestamp));

                    //数据插入redis
                    JedisUtil jedisUtil = new JedisUtil();
                    String professorKey = disinfectFilesModel.getFactoryNum() + "_immunePlan_professor";
                    String supervisorKey = disinfectFilesModel.getFactoryNum() + "_immunePlan_supervisor";
                    String testSendProfessor = disinfectFilesModel.getFactoryNum() + "_immunePlan_professor_AlreadySend";
                    String testSendSupervisor = disinfectFilesModel.getFactoryNum() + "_immunePlan_supervisor_AlreadySend";

                    jedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                    jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                    //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                    //System.out.println("judge equal:"+"1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)));

                    //若redis中 若干天未发送短信
                    //若未完成超过50条
                    if(!("1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)))){
                        System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                        if(jedisUtil.redisJudgeTime(professorKey)){

                            System.out.println("in redis:");
                            //获取redis中存储的设定过期时间
                            int expireTime = Integer.parseInt(jedisUtil.getCertainKeyValue("ExpireTime"));
                            List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(disinfectFilesModel.getFactoryNum());

                            //需完成:userModels.getTelephone()赋值给String
                            //获得StringBuffer手机号
                            StringBuffer phoneList = new StringBuffer("");
                            for(int i = 0; i < userModels.size(); i++){
                                phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                            }

                            //发送成功 更新redis中字段
                            if(jedisUtil.redisSendMessage(phoneList.toString(),jedisUtil.getCertainKeyValue("Message"))){
                                jedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor,"1",expireTime*24*60*60);
                            }

                                //System.out.println(phoneList);
                        } }else {
                            //System.out.println("professor:3天内已发送");
                        }

                        if(!("1".equals(jedisUtil.getCertainKeyValue(testSendSupervisor)))){
                        if(jedisUtil.redisJudgeTime(supervisorKey)){
                            int expireTime = Integer.parseInt(jedisUtil.getCertainKeyValue("ExpireTime"));
                            List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(disinfectFilesModel.getFactoryNum());

                            StringBuffer phoneList = new StringBuffer("");
                            for(int i = 0; i < userModels.size(); i++){
                                phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                            }
                            if(jedisUtil.redisSendMessage(phoneList.toString(),jedisUtil.getCertainKeyValue("Message"))){
                                //System.out.println("发送成功！");
                                jedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",expireTime*24*60*60);
                                return new Response().addData("Success","Send ok");
                            }
                        }
                    }else {
                        System.out.println("supervisor:3天内已发送");
                    }
                    return new Response().addData("Success","Uneccessary send");
                    //jedisUtil.redisSaveProfessorSupervisorWorks(professorKey,factoryNum);
                    //jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey,factoryNum);
                }catch (Exception e){

                    e.printStackTrace();
                }

            }else {
                return new Response().addData("Error", "Already exist");
            }
        }
            return new Response().addData("Error", "IO Exception");

    }





    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/find")
    public String Find(){
        return "DisinfectFilesHTML/DisinfectFilesFindForm";
    }

    /**
     * 返回查询结果
     * 以json格式返回前端
     * 分页查询
     * METHOD:POST
     * @param disinfectFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@RequestBody DisinfectFilesModel disinfectFilesModel){

        RowBounds bounds = new RowBounds(0,2);

        List<DisinfectFilesModel> disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModel(disinfectFilesModel.getFactoryNum(),
                disinfectFilesModel.getDisinfectTimeStart(),disinfectFilesModel.getDisinfectTimeEnd(),disinfectFilesModel.getDisinfectName(),
                disinfectFilesModel.getDisinfectQuality(),disinfectFilesModel.getDisinfectWay(),disinfectFilesModel.getOperator(),disinfectFilesModel.getProfessor(),
                disinfectFilesModel.getSupervisor(),disinfectFilesModel.getRemark(),disinfectFilesModel.getIsPass1(),disinfectFilesModel.getUnpassReason1(),
                disinfectFilesModel.getIsPass2(),disinfectFilesModel.getUnpassReason2(),bounds);
        return new Response().addData("List<DisinfectionFilesModel>",disinfectFilesModel1);
    }

    //更新接口
    //权限仅为专家和监督员

    //////删除数据在查询中再修改


}
