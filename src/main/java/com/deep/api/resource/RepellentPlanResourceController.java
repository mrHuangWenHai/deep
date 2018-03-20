package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.service.RepellentPlanService;
import com.deep.domain.util.UploadUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * METHOD:GET
     */
    @RequestMapping(value = "/function",method = RequestMethod.POST)
    public String RepellentPlanFunctionChoice(){
        return "RepellentPlanHTML/RepellentPlanFunctionChoiceForm";
    }

    /**
     * METHOD:GET
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String Save(){
        return "RepellentPlanHTML/RepellentPlanSaveForm";
    }

    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * METHOD:POST
     * @param factoryNum
     * @param crowdNum
     * @param repellentEartag
     * @param repellentTime
     * @param repellentName
     * @param repellentWay
     * @param repellentQuality
     * @param operator
     * @param remark
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@RequestParam("factoryNum") BigInteger factoryNum,
                             @RequestParam("crowdNum") String crowdNum,
                             @RequestParam("repellentEartag") MultipartFile repellentEartag,
                             @RequestParam("repellentTime") String repellentTime,
                             @RequestParam("repellentName") String repellentName,
                             @RequestParam("repellentWay") String repellentWay,
                             @RequestParam("repellentQuality") String repellentQuality,
                             @RequestParam("operator") String operator,
                             //@RequestParam("professor") String professor, 审核时自动插入
                             //@RequestParam("supervisor") String supervisor, 审核时自动插入
                             @RequestParam("remark") String remark,
                             //@RequestParam("isPass") String isPass,  默认 未审核
                             //@RequestParam("unpassReason") String unpassReason, 默认 无
                             //@RequestParam("gmtCreate") Timestamp gmtCreate; 插入时自动生成
                             // 下同
                             HttpServletRequest request
    ) {
        if ("".equals(factoryNum.toString()) ||
                "".equals(crowdNum) ||
                repellentEartag.isEmpty() ||
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
                    UploadUtil uploadUtil = new UploadUtil();
                    try{
                        String filepath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/repellentEartag/"+"/";
                        try {
                            uploadUtil.uploadFile(repellentEartag.getBytes(), filepath);
                            //System.out.println("saving file");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String ispass = "0";
                        repellentPlanService.setRepellentPlanModel(new RepellentPlanModel(factoryNum,crowdNum,uploadUtil.getFilename(),
                                                                        repellentTime,repellentName,repellentWay,repellentQuality,
                                                                        operator,remark,ispass,ispass,timestamp));
                        //RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(factoryNum, repellentTime, repellentName);
                        //System.out.println("save before:"+ispass);
                        return new Response().addData("Success","");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    return new Response().addData("Error","Already Exist");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new Response().addData("Error","Null Pointer");
    }

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public String Find(){
        return "RepellentPlanHTML/RepellentPlanFindForm";
    }

    /**
     * 返回查询结果
     * 以json格式返回前端
     * METHOD:POST
     * @param repellentPlanModel
     * @param repellentTimeStart
     * @param repellentTimeEnd
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
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
