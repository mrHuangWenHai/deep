package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.service.GenealogicalFilesService;
import com.deep.domain.util.JudgeUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
     *
     * color:棕色 0  暗红 1  杂色 2
     * sex:公 0 母 1
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

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //System.out.println(timestamp);
                genealogicalFilesService.setGenealogicalFilesModel(new GenealogicalFilesModel(genealogicalFilesModel.getSelfEartag(),genealogicalFilesModel.getImmuneEartag(),
                        genealogicalFilesModel.getTradeMarkEartag(),genealogicalFilesModel.getBreedingSheepBase(),genealogicalFilesModel.getBirthTime(),genealogicalFilesModel.getBirthWeight(),
                        genealogicalFilesModel.getColor(),genealogicalFilesModel.getSex(), genealogicalFilesModel.getEartagOfFather(),genealogicalFilesModel.getEartagOfMother(),
                        genealogicalFilesModel.getEartagOfFathersFather(),genealogicalFilesModel.getEartagOfFathersMother(),genealogicalFilesModel.getEartagOfMothersFather(),
                        genealogicalFilesModel.getEartagOfMothersMother(),genealogicalFilesModel.getRemark(),simpleDateFormat.format(new Timestamp(System.currentTimeMillis()))));


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
     * METHOD:POST
     * @param genealogicalFilesModel
     * @return
     */
    //bound为必传参数
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindResult(@RequestBody GenealogicalFilesModel genealogicalFilesModel){
        //System.out.println("selfEartag:"+genealogicalFilesModel.getSelfEartag());
        if (genealogicalFilesModel.getPage() == 0){
            genealogicalFilesModel.setPage(0);
        }

        if (genealogicalFilesModel.getSize() == 0){
            genealogicalFilesModel.setSize(10);
        }
        List<GenealogicalFilesModel> genealogicalFilesModels = genealogicalFilesService.getGenealogicalFilesModel(genealogicalFilesModel,new RowBounds(genealogicalFilesModel.getPage(),genealogicalFilesModel.getSize()));
        //System.out.println("ModelSize:"+genealogicalFilesModels.size());
        //System.out.println("ModelId:"+genealogicalFilesModels.get(0).getId());

        //System.out.println(""+genealogicalFilesModels.get(0).getId());
        return JudgeUtil.JudgeFind(genealogicalFilesModels,genealogicalFilesModels.size());
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
    public Response FindIdShow(@RequestParam("id") Long id ) {
        System.out.println("id:"+id);
        GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesService.getGenealogicalFilesModelByid(id);
        //System.out.println(genealogicalFilesModel.getSelfEartag());

        return JudgeUtil.JudgeFind(genealogicalFilesModel);

    }

    //update

    /**
     * 更新操作 输入数据替代原数据
     * METHOD:PATCH
     * @param genealogicalFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PATCH)
    public Response Update(@RequestBody GenealogicalFilesModel genealogicalFilesModel){
        if (genealogicalFilesService.getGenealogicalFilesModelByselfEartag(genealogicalFilesModel.getSelfEartag()) == null&&
                genealogicalFilesService.getGenealogicalFilesModelByimmuneEartag(genealogicalFilesModel.getImmuneEartag()) == null&&
                genealogicalFilesService.getGenealogicalFilesModelBytradeMarkEartag(genealogicalFilesModel.getTradeMarkEartag()) == null){

            int row = genealogicalFilesService.updateGenealogicalFilesModel(genealogicalFilesModel);
            return JudgeUtil.JudgeUpdate(row);
        }else {
            return Responses.errorResponse("conflict with existing datas");
        }

    }


    /**
     * 返回删除内容行号
     * METHOD:DELETE
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Response Delete(@RequestParam("id") Long id){
        int row = genealogicalFilesService.deleteGenealogicalFilesModel(id);
        return JudgeUtil.JudgeDelete(row);
    }

}
