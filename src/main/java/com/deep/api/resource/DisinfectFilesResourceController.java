package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.service.DisinfectFilesService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/allfunction/df")
public class DisinfectFilesResourceController {
    @Resource
    private DisinfectFilesService disinfectFilesService;

    @RequestMapping(value = "/function")
    public String DisinfectFilesFunctionChoice(){
        return "DisinfectFilesHTML/DisinfectFilesFunctionChoiceForm";
    }

    @RequestMapping(value = "/save")
    public String Save(){
        return "DisinfectFilesHTML/DisinfectFilesSaveForm";
    }

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


    @RequestMapping(value = "/find")
    public String Find(){
        return "DisinfectFilesHTML/DisinfectFilesFindForm";
    }

    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@Valid DisinfectFilesModel disinfectFilesModel,
                             @RequestParam("disinfectTimeStart") String disinfectTimeStart,
                             @RequestParam("disinfectTimeEnd") String disinfectTimeEnd,
                             @RequestParam(defaultValue = "1") int pageNum){
        PageHelper.startPage(pageNum,2);
        List<DisinfectFilesModel> disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModel(disinfectFilesModel.getFactoryNum(),
                disinfectTimeStart,disinfectTimeEnd,disinfectFilesModel.getDisinfectName(),disinfectFilesModel.getDisinfectQuality(),
                disinfectFilesModel.getDisinfectWay(),disinfectFilesModel.getOperator(),disinfectFilesModel.getProfessor(),
                disinfectFilesModel.getSupervisor(),disinfectFilesModel.getRemark(),disinfectFilesModel.getIsPass1(),disinfectFilesModel.getUnpassReason1(),
                disinfectFilesModel.getIsPass2(),disinfectFilesModel.getUnpassReason2());
        return new Response().addData("List<DisinfectionFilesModel>",disinfectFilesModel1);
    }


    //////删除数据在查询中再修改


}
