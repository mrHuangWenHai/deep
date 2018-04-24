package com.deep.api.resource;

import com.deep.api.request.GenealogicalRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.api.response.ValidResponse;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.model.TypeBriefModel;
import com.deep.domain.service.GenealogicalFilesService;
import com.deep.domain.service.TypeBriefService;
import com.deep.domain.util.JudgeUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/gf")
public class GenealogicalFilesResource {

    private Logger logger = LoggerFactory.getLogger(GenealogicalFilesResource.class);

    //替代注册bean
    @Resource
    private GenealogicalFilesService genealogicalFilesService;

    @Resource
    private TypeBriefService typeBriefService;


    /**
     * 用于存放羊品种以及简介
     * @param typeBriefModel  品种简介类
     * @param bindingResult   异常抛出类
     * @return  插入/更新结果
     */
    @RequestMapping(value = "/add/type",method = RequestMethod.POST)
    public Response type(@RequestBody @Validated TypeBriefModel typeBriefModel,

                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return ValidResponse.bindExceptionHandler();
        } else {
            int success = this.typeBriefService.setTypeBrief(typeBriefModel);
            if (success == 1) {
                return Responses.successResponse();
            } else {
                return Responses.errorResponse("add error");
            }
        }

    }

    /**
     * 查询之前 需要返回给前端的数据
     * 山羊品种对应的特征
     * @return 种类
     */

    @RequestMapping(value = "/types",method = RequestMethod.GET)
    public Response beforeSave() {
        logger.info("/gf/types");
        return JudgeUtil.JudgeSuccess("type",this.typeBriefService.getAllType());
    }

    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     *
     * color:棕色 0  暗红 1  杂色 2
     * sex:公 0 母 1
     * METHOD:POST
     * @param genealogicalFilesModel 系谱类
     * @return 插入结果
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Response saveShow(@RequestBody @Validated GenealogicalFilesModel genealogicalFilesModel) {

      logger.info("invoke save {}",genealogicalFilesModel);
      //查看数据库中是否有这种羊的品种信息

      List<TypeBriefModel> list = this.typeBriefService.getAllType();
      int i = 0;
      for (TypeBriefModel tempType : list) {
          if (tempType.getTypename().equals(genealogicalFilesModel.getTypeName())) {

              i = 1;
              break;
          }
      }

      if (i == 0) {
          return Responses.errorResponse("No this type before");
      }

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String time = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
      genealogicalFilesModel.setGmtCreate(time);
      genealogicalFilesModel.setGmtModified(time);

      try {
          int id = genealogicalFilesService.insertGenealogicalFilesModel(genealogicalFilesModel);
          if (id == 0) {
              return Responses.errorResponse("add data error");
          } else {
              HashMap<String,Object> data = new HashMap<>();
              data.put("id",genealogicalFilesModel.getId());
              return Responses.successResponse(data);
          }
      } catch (Exception e) {
          e.printStackTrace();
          return Responses.errorResponse("data already exist");
      }

    }

    /**
     * 用于条件查找
     * METHOD:POST
     * @param genealogicalRequest 系谱请求类
     * @return 查询结果/查询结果条数
     */
    //bound为必传参数
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response findShow(@RequestBody GenealogicalRequest genealogicalRequest) {

        logger.info("invoke findShow {}",genealogicalRequest);

        if ( genealogicalRequest.getPage() == 0 ) {
            genealogicalRequest.setPage(0);
        }

        if ( genealogicalRequest.getSize() == 0 ) {
            genealogicalRequest.setSize(10);
        }

        List<GenealogicalFilesModel> genealogicalFilesModels = genealogicalFilesService.getGenealogicalFilesModel(genealogicalRequest,new RowBounds(genealogicalRequest.getPage(),genealogicalRequest.getSize()));

        for(int i = 0 ; i < genealogicalFilesModels.size() ; i ++) {
            String brief = this.typeBriefService.getTypeBrief(genealogicalFilesModels.get(i).getTypeName()).getBrief();
            genealogicalFilesModels.get(i).setBrief(brief);

        }
        return JudgeUtil.JudgeFind(genealogicalFilesModels,genealogicalFilesModels.size());
    }

    /**
     * 用于条件查找
     * RowBounds为必传参数
     * @param
     * @return
     */
     @RequestMapping(value = "find/{id}")
     public Response findGenealogicalFiles(@NotNull @PathVariable(value = "id") long factoryNum,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
         if (page < 0 || size < 0) {
             return Responses.errorResponse("param is invaild");
         }
         logger.info("invoke find/{} {}",factoryNum, page, size);
         RowBounds rowBounds = new RowBounds(page, size);
         int total = genealogicalFilesService.allGenealogicalFilesCounts();
         List<GenealogicalFilesModel> genealogicalFilesModels = genealogicalFilesService.getGenealogicalFilesModelByFactoryNum(factoryNum, rowBounds);
         Response response = Responses.successResponse();
         HashMap<String,Object> data = new HashMap<>();
         data.put("List",genealogicalFilesModels);
         data.put("size",size);
         data.put("total",total);
         response.setData(data);
         return response;
     }

    /**
     * 查询出满足若干条件后的结果
     * 可由id进行操作
     * METHOD:GET
     * @param id id
     * @return 查询结果
     */
    @ResponseBody

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public Response findShowById(@NotNull @PathVariable("id") int id ) {

        logger.info("invoke showById {}", id);

        GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesService.getGenealogicalFilesModelByid(id);
        return JudgeUtil.JudgeFind(genealogicalFilesModel);
    }

    //update

    /**
     * 更新操作 输入数据替代原数据
     * METHOD:PATCH
     * @param genealogicalFilesModel 系谱类
     * @return  更新结果
     */
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PATCH)
    public Response update(@Validated @RequestBody GenealogicalFilesModel genealogicalFilesModel,
                           @NotNull @PathVariable(value = "id") int id) {

        logger.info("invoke update/ {}", genealogicalFilesModel);
        if (id < 0) {
            return Responses.errorResponse("path is invalid");
        }
        genealogicalFilesModel.setId(id);
        int row = genealogicalFilesService.updateGenealogicalFilesModel(genealogicalFilesModel);
        return JudgeUtil.JudgeUpdate(row);

    }

    /**
     * 返回删除内容行号
     * METHOD:DELETE
     * @param id id
     * @return  删除结果
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Response delete(@NotNull @PathVariable(value = "id") int id) {
        logger.info("invoke delete {}", id);
        int row = genealogicalFilesService.deleteGenealogicalFilesModel(id);
        return JudgeUtil.JudgeDelete(row);
    }

}
