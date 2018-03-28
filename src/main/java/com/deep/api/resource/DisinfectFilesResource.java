package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.DisinfectFilesService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.domain.util.UploadUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping(value = "/df",method = RequestMethod.GET)
public class DisinfectFilesResource {

    @Resource
    private DisinfectFilesService disinfectFilesService;
    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;



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
            return Responses.errorResponse("Lack Item");

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

                    if(!("1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)))){
                        //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
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
                            System.out.println("professor:3天内已发送");
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

                                    HashMap<String,Object> data = new HashMap<>();
                                    data.put("successMessage","Message Sent");
                                    return Responses.successResponse(data);
                                }
                            }
                        }else {
                            System.out.println("supervisor:3天内已发送");
                        }
                    HashMap<String,Object> data = new HashMap<>();
                    data.put("successMessage","have sent message in 3 days");
                    return Responses.successResponse(data);

                    //jedisUtil.redisSaveProfessorSupervisorWorks(professorKey,factoryNum);
                    //jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey,factoryNum);
                }catch (Exception e){

                    e.printStackTrace();
                }

            }else {
                return Responses.errorResponse("Already Exist");
            }
        }
        return Responses.errorResponse("IOException");

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

        List<DisinfectFilesModel> disinfectFilesModels = disinfectFilesService.getDisinfectFilesModel(disinfectFilesModel,
                new RowBounds(disinfectFilesModel.getPage(),disinfectFilesModel.getSize()));

        return JudgeUtil.JudgeFind(disinfectFilesModels);
    }

    //更新接口


    //权限仅为专家和监督员
    /**
     * 专家入口 查看isPass1 = 0或者isPass1 = 1的数据
     * METHOD:GET
     * @param isPass1
     * @param page
     * @param size
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/pfind",method = RequestMethod.GET)
    public Response ProfessorFind(@RequestParam("isPass1") Integer isPass1,
                                  @RequestParam("page") int page,
                                  @RequestParam("size") int size){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesService.getDisinfectFilesModelByProfessor(isPass1,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(disinfectFilesModels);
    }


    /**
     * 审核入口 展示所有isPass2 = 0或者isPass2 = 1的数据
     * METHOD:PATCH
     * @param disinfectFilesModel
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/pupdate",method = RequestMethod.PATCH)
    public Response ProfessorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel){
        int row = this.disinfectFilesService.updateDisinfectFilesModelByProfessor(disinfectFilesModel);

        return JudgeUtil.JudgeUpdate(row);
    }



    @ResponseBody
    @RequestMapping(value = "/sfind",method = RequestMethod.GET)
    public Response SupervisorFind(@RequestParam("isPass2") Integer isPass2,
                                   @RequestParam("page") int page,
                                   @RequestParam("size") int size){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesService.getDisinfectFilesModelBySupervisor(isPass2,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(disinfectFilesModels);
    }



    @ResponseBody
    @RequestMapping(value = "/supdate",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel){
        int row = this.disinfectFilesService.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);

        return JudgeUtil.JudgeUpdate(row);
    }

    //////删除数据在查询中再修改

    /**
     * 删除id = certain 的数据
     * 权限设置
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Response Delete(@RequestParam("id") BigInteger id){
        int row = this.disinfectFilesService.deleteDisinfectFilesModelByid(id);

        return JudgeUtil.JudgeDelete(row);
    }

}
