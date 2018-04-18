package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.service.GenealogicalFilesService;
import com.deep.domain.util.JudgeUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/gf")
public class GenealogicalFilesResource {

    private Logger logger = LoggerFactory.getLogger(GenealogicalFilesResource.class);
    //替代注册bean
    @Resource
    private GenealogicalFilesService genealogicalFilesService;

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
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response saveShow(@RequestBody @Validated GenealogicalFilesModel genealogicalFilesModel) {
      logger.info("invoke saveShow {}",genealogicalFilesModel);
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String time = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
      genealogicalFilesModel.setGmtCreate(time);
      genealogicalFilesModel.setGmtModified(time);
      int id = genealogicalFilesService.insertGenealogicalFilesModel(genealogicalFilesModel);
      if (id == 0) {
        return Responses.errorResponse("add data error");
      } else {
        HashMap<String,Object> data = new HashMap<>();
        data.put("id",genealogicalFilesModel.getId());
        return Responses.successResponse(data);
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
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response findShow(@RequestBody GenealogicalFilesModel genealogicalFilesModel){

        logger.info("invoke findShow {}",genealogicalFilesModel);

        if ( genealogicalFilesModel.getPage() == 0 ) {
            genealogicalFilesModel.setPage(0);
        }

        if ( genealogicalFilesModel.getSize() == 0 ) {
            genealogicalFilesModel.setSize(10);
        }

        List<GenealogicalFilesModel> genealogicalFilesModels = null;
        genealogicalFilesModels = genealogicalFilesService.getGenealogicalFilesModel(genealogicalFilesModel,new RowBounds(genealogicalFilesModel.getPage(),genealogicalFilesModel.getSize()));
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
    public Response findShowById(@RequestParam("id") int id ) {

        logger.info("invoke findShowById {}", id);

        GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesService.getGenealogicalFilesModelByid(id);

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
    public Response update(@RequestBody GenealogicalFilesModel genealogicalFilesModel) {

        logger.info("invoke update {}", genealogicalFilesModel);

        if (genealogicalFilesService.getGenealogicalFilesModelByNativeEartag(genealogicalFilesModel.getNativeEartag()) == null
            && genealogicalFilesService.getGenealogicalFilesModelByimmuneEartag(genealogicalFilesModel.getImmuneEartag()) == null
            && genealogicalFilesService.getGenealogicalFilesModelBytradeMarkEartag(genealogicalFilesModel.getTradeMarkEartag()) == null) {
            int row = genealogicalFilesService.updateGenealogicalFilesModel(genealogicalFilesModel);
            return JudgeUtil.JudgeUpdate(row);
        } else {
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
    public Response delete(@RequestParam("id") int id) {
        logger.info("invoke delete {}", id);
        int row = genealogicalFilesService.deleteGenealogicalFilesModel(id);
        return JudgeUtil.JudgeDelete(row);
    }

}
