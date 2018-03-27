package com.deep.api.resource;

import com.deep.api.response.RespMeta;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.service.GenealogicalFilesService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/gf")
public class GenealogicalFilesResource {

    //替代注册bean
    @Resource
    private GenealogicalFilesService genealogicalFilesService;

    //localhost:9090/allfunction/gf/function

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/function",method = RequestMethod.POST)
    public String GenealogicalFilesFunctionChoice(){
        return "GenealogicalFilesHTML/GenealogicalFilesFunctionChoiceForm";
    }

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String Save(){
        /*Jedis jedis = new Jedis("localhost");
        jedis.get("userId");
        jedis.get("token");
        System.out.println(jedis.get("userId")+ jedis.get("token"));
        System.out.println("查看userId的剩余生存时间："+jedis.ttl("userId"));*/
        return "GenealogicalFilesHTML/GenealogicalFilesSaveForm";
    }

    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * METHOD:POST
     * @param genealogicalFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@RequestBody GenealogicalFilesModel genealogicalFilesModel) {
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
    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public String Find(){
        return "GenealogicalFilesHTML/GenealogicalFilesFindForm";
    }


    /**
     * 用于条件查找
     * RowBounds为必传参数
     * METHOD:GET
     * @param genealogicalFilesModel
     * @return
     */
    //bound为必传参数
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.GET)
    public Response FindResult(@RequestBody GenealogicalFilesModel genealogicalFilesModel){
        //System.out.println("EartagOfFather:"+genealogicalFilesModel.getEartagOfFather());

        List<GenealogicalFilesModel> genealogicalFilesModels = genealogicalFilesService.getGenealogicalFilesModel(genealogicalFilesModel.getSelfEartag(),
                genealogicalFilesModel.getImmuneEartag(),genealogicalFilesModel.getTradeMarkEartag(),genealogicalFilesModel.getBreedingSheepBase(),
                genealogicalFilesModel.getBirthTimeStart(),genealogicalFilesModel.getBirthTimeEnd(),genealogicalFilesModel.getBirthWeightStart(),
                genealogicalFilesModel.getBirthWeightEnd(),genealogicalFilesModel.getColor(),genealogicalFilesModel.getSex(),genealogicalFilesModel.getEartagOfFather(),
                genealogicalFilesModel.getEartagOfMother(),genealogicalFilesModel.getEartagOfFathersFather(),genealogicalFilesModel.getEartagOfFathersMother(),
                genealogicalFilesModel.getEartagOfMothersFather(),genealogicalFilesModel.getEartagOfMothersMother(),new RowBounds(0,20));
        System.out.println("ModelSize:"+genealogicalFilesModels.size());
        System.out.println("ModelId:"+genealogicalFilesModels.get(0).getId());

        return new Response().addData("Success",genealogicalFilesModels);
    }

    /**
     * 查询出满足若干条件后的结果
     * 可由id进行操作
     * METHOD:GET
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshowbyid",method = RequestMethod.GET)
    public Response FindIdShow(@RequestParam("id") BigInteger id ) {
        System.out.println("id:"+id);
        GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesService.getGenealogicalFilesModelByid(id);
        //System.out.println(genealogicalFilesModel.getSelfEartag());

        if (genealogicalFilesModel == null){
            Responses.successResponse();
        }
        return null;

    }

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String Delete(){
        return "GenealogicalFilesHTML/GenealogicalFilesDeleteForm";
    }

    /**
     * 返回删除内容行号
     * 以json格式返回前端
     * METHOD:DELETE
     * @param selfEartag
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/deleteshow",method = RequestMethod.DELETE)
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


    //update
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PATCH)
    public Response Update(@RequestBody GenealogicalFilesModel genealogicalFilesModel){
        int row = genealogicalFilesService.updateGenealogicalFilesModel(genealogicalFilesModel);
        return null;
    }

}
