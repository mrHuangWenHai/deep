package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.service.DisinfectFilesService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;

import java.util.List;


@Controller
@RequestMapping(value = "/allfunction/df",method = RequestMethod.GET)
public class DisinfectFilesResourceController {
    @Resource
    private DisinfectFilesService disinfectFilesService;

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/function",method = RequestMethod.POST)
    public String DisinfectFilesFunctionChoice(){
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
    public Response SaveShow(@Valid DisinfectFilesModel disinfectFilesModel
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
                String ispass = "0";
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                disinfectFilesService.setDisinfectFilesModel(new DisinfectFilesModel(disinfectFilesModel.getFactoryNum(), disinfectFilesModel.getDisinfectTime(),
                        disinfectFilesModel.getDisinfectName(), disinfectFilesModel.getDisinfectQuality(),
                        disinfectFilesModel.getDisinfectWay(), disinfectFilesModel.getOperator(),
                        disinfectFilesModel.getRemark(), ispass, ispass, timestamp));
                //文件上传
                return new Response().addData("Success","");
            }else {
                return new Response().addData("Error","Already Exist");
            }

        }
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
