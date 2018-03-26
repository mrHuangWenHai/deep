package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.service.RepellentPlanService;
import com.deep.domain.util.UploadUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Timestamp;
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
     * @param repellentPlanModel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@RequestBody RepellentPlanModel repellentPlanModel,
                             //@RequestParam("factoryNum") BigInteger factoryNum,
                             //@RequestParam("crowdNum") String crowdNum,
                             //@RequestParam("repellentEartag") MultipartFile repellentEartag,
                             //@RequestParam("repellentTime") String repellentTime,
                             //@RequestParam("repellentName") String repellentName,
                             //@RequestParam("repellentWay") String repellentWay,
                             //@RequestParam("repellentQuality") String repellentQuality,
                             //@RequestParam("operator") String operator,
                             //@RequestParam("professor") String professor, 审核时自动插入
                             //@RequestParam("supervisor") String supervisor, 审核时自动插入
                             //@RequestParam("remark") String remark,
                             //@RequestParam("isPass") String isPass,  默认 未审核
                             //@RequestParam("unpassReason") String unpassReason, 默认 无
                             //@RequestParam("gmtCreate") Timestamp gmtCreate; 插入时自动生成
                             // 下同
                             HttpServletRequest request
    ) {
        if ("".equals(repellentPlanModel.getFactoryNum().toString()) ||
                "".equals(repellentPlanModel.getCrowdNum()) ||
                repellentPlanModel.getRepellentEartag().isEmpty() ||
                "".equals(repellentPlanModel.getRepellentTime()) ||
                "".equals(repellentPlanModel.getRepellentName()) ||
                "".equals(repellentPlanModel.getRepellentWay()) ||
                "".equals(repellentPlanModel.getRepellentQuality()) ||
                "".equals(repellentPlanModel.getOperator()) ||
                "".equals(repellentPlanModel.getRemark())) {
            return new Response().addData("Error","Lack Item");
        } else {
            try{
                RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(repellentPlanModel.getFactoryNum(),repellentPlanModel.getRepellentTime(),repellentPlanModel.getRepellentName());
                if (repellentPlanModel1 == null){
                    //上传文件
                    UploadUtil uploadUtil = new UploadUtil();
                    try{
                        String filepath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/repellentEartag/"+"/";
                        try {
                            uploadUtil.uploadFile(repellentPlanModel.getRepellentEartag().getBytes(), filepath);
                            //System.out.println("saving file");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String ispass = "0";

                        repellentPlanService.setRepellentPlanModel(new RepellentPlanModel(repellentPlanModel.getFactoryNum(),repellentPlanModel.getCrowdNum(),uploadUtil.getFilename(),
                                repellentPlanModel.getRepellentTime(),repellentPlanModel.getRepellentName(),repellentPlanModel.getRepellentWay(),repellentPlanModel.getRepellentQuality(),
                                repellentPlanModel.getOperator(),repellentPlanModel.getRemark(),ispass,ispass,timestamp));
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
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@RequestBody RepellentPlanModel repellentPlanModel){
        //
        RowBounds bounds = new RowBounds(0,10);

        List<RepellentPlanModel> repellentPlanModels =repellentPlanService.getRepellentPlanModel(repellentPlanModel.getFactoryNum(),
                repellentPlanModel.getCrowdNum(),repellentPlanModel.getRepellentEartag(), repellentPlanModel.getRepellentTimeStart() ,
                repellentPlanModel.getRepellentTimeEnd(), repellentPlanModel.getRepellentName(), repellentPlanModel.getRepellentWay(),
                repellentPlanModel.getRepellentQuality(), repellentPlanModel.getOperator(),repellentPlanModel.getProfessor(),
                repellentPlanModel.getSupervisor(), repellentPlanModel.getRemark(),repellentPlanModel.getIsPass1(),
                repellentPlanModel.getUnpassReason1(), repellentPlanModel.getIsPass2(),repellentPlanModel.getUnpassReason2(),bounds);

        return new Response().addData("List<repellentPlanModels>",repellentPlanModels);

    }




    //////
}
