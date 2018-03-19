package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.service.GenealogicalFilesService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "/allfunction/gf")
public class GenealogicalFilesResourceController {

    //替代注册bean
    @Resource
    private GenealogicalFilesService genealogicalFilesService;

    //localhost:9090/allfunction/gf/function
    @RequestMapping(value = "/function")
    public String GenealogicalFilesFunctionChoice(){

        return "GenealogicalFilesHTML/GenealogicalFilesFunctionChoiceForm";
    }
    @RequestMapping(value = "/save")
    public String Save(){
        /*Jedis jedis = new Jedis("localhost");
        jedis.get("userId");
        jedis.get("token");
        System.out.println(jedis.get("userId")+ jedis.get("token"));
        System.out.println("查看userId的剩余生存时间："+jedis.ttl("userId"));*/
        return "GenealogicalFilesHTML/GenealogicalFilesSaveForm";
    }
    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@Valid GenealogicalFilesModel genealogicalFilesModel) {
        //System.out.println("传参数成功");
        if("".equals(genealogicalFilesModel.getSelfEartag())||
                "".equals(genealogicalFilesModel.getImmuneEartag())||
                "".equals(genealogicalFilesModel.getTradeMarkEartag())||
                "".equals(genealogicalFilesModel.getBreedingSheepBase())||
                "".equals(genealogicalFilesModel.getBirthTime())||
                0 == genealogicalFilesModel.getBirthWeight()||
                "".equals(genealogicalFilesModel.getColor())||
                "".equals(genealogicalFilesModel.getSex())||
                "".equals(genealogicalFilesModel.getEartagOfFather())||
                "".equals(genealogicalFilesModel.getEartagOfMother())||
                "".equals(genealogicalFilesModel.getEartagOfFathersFather())||
                "".equals(genealogicalFilesModel.getEartagOfFathersMother())||
                "".equals(genealogicalFilesModel.getEartagOfMothersFather())||
                "".equals(genealogicalFilesModel.getEartagOfMothersMother())||
                "".equals(genealogicalFilesModel.getRemark())){
            return new Response().addData("Error","Error Lack Item");
        }else{
            //System.out.println(SelfEartag);
            GenealogicalFilesModel genealogicalFilesModelByimmuneEartag = genealogicalFilesService.getGenealogicalFilesModelByimmuneEartag(genealogicalFilesModel.getImmuneEartag());
            GenealogicalFilesModel genealogicalFilesModelBytradeMarkEartag = genealogicalFilesService.getGenealogicalFilesModelBytradeMarkEartag(genealogicalFilesModel.getTradeMarkEartag());
            //System.out.println(genealogicalFilesModelByboth.getSelfEartag());
            if( genealogicalFilesModelByimmuneEartag == null && genealogicalFilesModelBytradeMarkEartag == null){

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                //System.out.println(timestamp);
                genealogicalFilesService.setGenealogicalFilesModel(new GenealogicalFilesModel(genealogicalFilesModel.getSelfEartag(),genealogicalFilesModel.getImmuneEartag(),
                        genealogicalFilesModel.getTradeMarkEartag(),genealogicalFilesModel.getBreedingSheepBase(),genealogicalFilesModel.getBirthTime(),genealogicalFilesModel.getBirthWeight(),
                        genealogicalFilesModel.getColor(),genealogicalFilesModel.getSex(), genealogicalFilesModel.getEartagOfFather(),genealogicalFilesModel.getEartagOfMother(),
                        genealogicalFilesModel.getEartagOfFathersFather(),genealogicalFilesModel.getEartagOfFathersMother(),genealogicalFilesModel.getEartagOfMothersFather(),
                        genealogicalFilesModel.getEartagOfMothersMother(),genealogicalFilesModel.getRemark(),timestamp));

                Response response = new Response();
                response.addData("Success","");

                return response;
            }else{
                return new Response().addData("Error","Error Already Exist");
            }
        }

    }
    //localhost:8080/allfunction/gf/find
    @RequestMapping(value = "/find")
    public String Find(){
        return "GenealogicalFilesHTML/GenealogicalFilesFindForm";
    }

    @ResponseBody
    @RequestMapping(value = "/findshow")
    public Response FindSelfEartagShow(@Valid GenealogicalFilesModel genealogicalFilesModel,
                                       @RequestParam("immuneEartagStart") String immuneEartagStart,
                                       @RequestParam("immuneEartagEnd") String immuneEartagEnd,
                                       @RequestParam("birthTimeStart") String birthTimeStart,
                                       @RequestParam("birthTimeEnd") String birthTimeEnd,
                                       @RequestParam("birthWeightStart") String birthWeightStart,
                                       @RequestParam("birthWeightEnd") String birthWeightEnd,
                                       @RequestParam(defaultValue = "1") int pageNum) {
        //if ("".equals(selfEartag) || selfEartag == null) selfEartag = "";
        //System.out.println("selfeartag："+selfEartag);eartag_of_fathersfather
        PageHelper.startPage(pageNum,2);
        List<GenealogicalFilesModel> genealogicalFilesModels = genealogicalFilesService.getGenealogicalFilesModel(genealogicalFilesModel.getSelfEartag(),immuneEartagStart,immuneEartagEnd,
                                                                                                        genealogicalFilesModel.getTradeMarkEartag(),genealogicalFilesModel.getBreedingSheepBase(),
                                                                                                        birthTimeStart,birthTimeEnd,birthWeightStart,birthWeightEnd,genealogicalFilesModel.getColor(),
                                                                                                        genealogicalFilesModel.getSex(),genealogicalFilesModel.getEartagOfFather(),genealogicalFilesModel.getEartagOfMother(),
                                                                                                        genealogicalFilesModel.getEartagOfFathersFather(),genealogicalFilesModel.getEartagOfFathersMother(),
                                                                                                        genealogicalFilesModel.getEartagOfMothersFather(), genealogicalFilesModel.getEartagOfMothersMother());
        return new Response().addData("List<GenealogicalFilesModel>",genealogicalFilesModels);

        //System.out.println(genealogicalFilesModel.getImmuneEartag());
        //System.out.println(genealogicalFilesModel.getTradeMarkEartag());
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String Delete(){
        return "GenealogicalFilesHTML/GenealogicalFilesDeleteForm";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteshow",method = RequestMethod.POST)
    public Response DeleteShow(@RequestParam("selfEartag") String selfEartag)throws Exception{
        try {
            int row = genealogicalFilesService.deleteGenealogicalFilesModel(selfEartag);
            //System.out.println(row);
            if(row != 0){ return new Response().addData("Delete Success! Detele row:",row); }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response().addData("Delete Fail!","Error");
    }


    private class Jedis {
    }
}
