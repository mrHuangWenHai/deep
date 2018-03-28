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
            return Responses.errorResponse("Lack Item");
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


                HashMap<String,Object> data = new HashMap<>();
                data.put("successMessage","save");
                return Responses.successResponse(data);

            }else{

                return Responses.errorResponse("Already Exist");
            }
        }

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
                genealogicalFilesModel.getEartagOfMothersFather(),genealogicalFilesModel.getEartagOfMothersMother(),new RowBounds(genealogicalFilesModel.getPage(),genealogicalFilesModel.getSize()));
        //System.out.println("ModelSize:"+genealogicalFilesModels.size());
        //System.out.println("ModelId:"+genealogicalFilesModels.get(0).getId());

        HashMap<String,Object> data  = new HashMap<>();
        data.put("List",genealogicalFilesModels);
        return Responses.successResponse(data);
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

        HashMap<String,Object> data = new HashMap<>();
        data.put("find by id",genealogicalFilesModel);
        return Responses.successResponse(data);

    }

    //update
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PATCH)
    public Response Update(@RequestBody GenealogicalFilesModel genealogicalFilesModel){
        int row = genealogicalFilesService.updateGenealogicalFilesModel(genealogicalFilesModel);
        HashMap<String,Object> data = new HashMap<>();
        data.put("update row",row);
        return Responses.successResponse(data);
    }


    /**
     * 返回删除内容行号
     * 以json格式返回前端
     * METHOD:DELETE
     * @param genealogicalFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Response Delete(@RequestBody GenealogicalFilesModel genealogicalFilesModel){
        int row = genealogicalFilesService.deleteGenealogicalFilesModel(genealogicalFilesModel.getId());
        HashMap<String,Object> data = new HashMap<>();
        data.put("delete row",row);
        return Responses.successResponse(data);
    }

}
