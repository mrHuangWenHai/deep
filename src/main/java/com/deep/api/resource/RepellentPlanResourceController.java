package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.service.RepellentPlanService;
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

@RequestMapping(value = "/allfunction/rp")
@Controller
public class RepellentPlanResourceController {

    @Resource
    private RepellentPlanService repellentPlanService;

    @RequestMapping(value = "/function")
    public String RepellentPlanFunctionChoice(){
        return "RepellentPlanHTML/RepellentPlanFunctionChoiceForm";
    }

    @RequestMapping(value = "/save")
    public String Save(){
        return "RepellentPlanHTML/RepellentPlanSaveForm";
    }

    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@RequestParam("factoryNum") BigInteger factoryNum,
                             @RequestParam("crowdNum") String crowdNum,
                             @RequestParam("repellentEartag") String repellentEartag,
                             @RequestParam("repellentTime") String repellentTime,
                             @RequestParam("repellentName") String repellentName,
                             @RequestParam("repellentWay") String repellentWay,
                             @RequestParam("repellentQuality") String repellentQuality,
                             @RequestParam("operator") String operator,
                             //@RequestParam("professor") String professor, 审核时自动插入
                             //@RequestParam("supervisor") String supervisor, 审核时自动插入
                             @RequestParam("remark") String remark
                             //@RequestParam("isPass") String isPass,  默认 未审核
                             //@RequestParam("unpassReason") String unpassReason, 默认 无
                             //@RequestParam("gmtCreate") Timestamp gmtCreate; 插入时自动生成
                             // 下同
                           ) {
        if ("".equals(factoryNum.toString()) ||
                "".equals(crowdNum) ||
                "".equals(repellentEartag) ||
                "".equals(repellentTime) ||
                "".equals(repellentName) ||
                "".equals(repellentWay) ||
                "".equals(repellentQuality) ||
                "".equals(operator) ||
                "".equals(remark)) {
            return new Response().addData("Error","Lack Item");
        } else {
            try{
                RepellentPlanModel repellentPlanModel = repellentPlanService.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(factoryNum,repellentTime,repellentName);
                if (repellentPlanModel == null){
                    //上传文件

                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String ispass = "0";
                    repellentPlanService.setRepellentPlanModel(new RepellentPlanModel(factoryNum,crowdNum,repellentEartag,
                                                                        repellentTime,repellentName,repellentWay,repellentQuality,
                                                                        operator,remark,ispass,ispass,timestamp));
                    //RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(factoryNum, repellentTime, repellentName);
                    //System.out.println("save before:"+ispass);
                    return new Response().addData("Success","");
                }else{
                    return new Response().addData("Error","Already Exist");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/find")
    public String Find(){
        return "RepellentPlanHTML/RepellentPlanFindForm";
    }

    @ResponseBody
    @RequestMapping(value = "/findshow")
    public Response FindShow(@Valid RepellentPlanModel repellentPlanModel,
                                 @RequestParam("repellentTimeStart") String repellentTimeStart,
                                 @RequestParam("repellentTimeEnd") String repellentTimeEnd,
                                 @RequestParam(defaultValue = "1") int pageNum){
        PageHelper.startPage(pageNum,2);
        List<RepellentPlanModel> repellentPlanModels =repellentPlanService.getRepellentPlanModel(repellentPlanModel.getFactoryNum(),
                                                            repellentPlanModel.getCrowdNum(),repellentPlanModel.getRepellentEartag(),
                                                            repellentTimeStart ,repellentTimeEnd,repellentPlanModel.getRepellentName(),
                                                            repellentPlanModel.getRepellentWay(),repellentPlanModel.getRepellentQuality(),
                                                            repellentPlanModel.getOperator(),repellentPlanModel.getProfessor(),repellentPlanModel.getSupervisor(),
                                                            repellentPlanModel.getRemark(),repellentPlanModel.getIsPass1(),repellentPlanModel.getUnpassReason1(),
                                                            repellentPlanModel.getIsPass2(),repellentPlanModel.getUnpassReason2());

        System.out.println(repellentPlanModels.get(0).getIsPass1()+repellentPlanModels.get(0).getIsPass2());
        return new Response().addData("List<repellentPlanModels>",repellentPlanModels);

    }




    //////
}
