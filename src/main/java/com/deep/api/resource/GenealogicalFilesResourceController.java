package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.service.GenealogicalFilesService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
@RequestMapping(value = "/allfunction/gf")
public class GenealogicalFilesResourceController {

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
    /**
     * METHOD:POST
     * @return
     */
    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public String Find(){
        return "GenealogicalFilesHTML/GenealogicalFilesFindForm";
    }


    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindResult(@RequestBody GenealogicalFilesModel genealogicalFilesModel){
        System.out.println("running:"+genealogicalFilesModel.getColor());
        return new Response().addData("Success",genealogicalFilesModel.getColor());
    }

    /**
     * 查询出满足若干条件后的结果
     * 可由id进行操作
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshowbyid",method = RequestMethod.POST)
    public Response FindIdShow(@RequestParam("id") int id) {

        RowBounds bounds = new RowBounds(0,2);
        GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesService.getGenealogicalFilesModelByid(id);
        return new Response().addData("GenealogicalFilesModel",genealogicalFilesModel);

    }

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
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

}
