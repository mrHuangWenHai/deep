package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;

import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.GenealogicalRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.model.TypeBriefModel;
import com.deep.domain.service.GenealogicalFilesService;
import com.deep.domain.service.TypeBriefService;
import com.deep.domain.util.JudgeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/gf")
public class GenealogicalFilesResource {

    private Logger logger = LoggerFactory.getLogger(GenealogicalFilesResource.class);

    //替代注册bean
    @Resource
    private GenealogicalFilesService genealogicalFilesService;

    @Resource
    private TypeBriefService typeBriefService;

//    @Permit(authorities = "delete_sheep_type")
//    @Permit(authorities = "select_sheep_type")
//    @Permit(authorities = "modify_sheep_type")


    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * color:棕色 0  暗红 1  杂色 2
     * sex:公 0 母 1
     * METHOD:POST
     * @param genealogicalFilesModel 系谱类
     * @return 插入结果
     */
    @Permit(authorities = "add_pedigree_files")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Response saveShow(@RequestBody @Validated GenealogicalFilesModel genealogicalFilesModel) {

      logger.info("invoke save {}",genealogicalFilesModel);

      List<GenealogicalFilesModel> model = this.genealogicalFilesService.getGenealogicalFilesModelByTradeMarkEarTag(genealogicalFilesModel.getTradeMarkEartag());
      if (model.size() == 0) {
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String time = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
          genealogicalFilesModel.setGmtCreate(time);
          genealogicalFilesModel.setGmtModified(time);

          int id = genealogicalFilesService.insertGenealogicalFilesModel(genealogicalFilesModel);
          if (id == 0) {
              return Responses.errorResponse("添加数据失败");
          } else {
              HashMap<String, Object> data = new HashMap<>();
              data.put("id", genealogicalFilesModel.getId());
              return Responses.successResponse(data);
              }
      } else {
          if (model.get(0).getEartagOfMother().equals(genealogicalFilesModel.getEartagOfMother()) &&
                  model.get(0).getEartagOfFather().equals(genealogicalFilesModel.getEartagOfFather()) &&
                  model.get(0).getEartagOfMothersFather().equals(genealogicalFilesModel.getEartagOfMothersFather()) &&
                  model.get(0).getEartagOfMothersMother().equals(genealogicalFilesModel.getEartagOfMothersMother()) &&
                  model.get(0).getEartagOfFathersMother().equals(genealogicalFilesModel.getEartagOfFathersMother()) &&
                  model.get(0).getEartagOfFathersFather().equals(genealogicalFilesModel.getEartagOfFathersFather())) {
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String time = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
              genealogicalFilesModel.setGmtCreate(time);
              genealogicalFilesModel.setGmtModified(time);

              int id = genealogicalFilesService.insertGenealogicalFilesModel(genealogicalFilesModel);
              if (id == 0) {
                  return Responses.errorResponse("添加数据失败");
              } else {
                  HashMap<String, Object> data = new HashMap<>();
                  data.put("id", genealogicalFilesModel.getId());
                  return Responses.successResponse(data);
              }
          } else {
              return Responses.errorResponse("与原数据冲突,请修改插入数据！");
          }
      }
    }


    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public Response findInTree(@RequestParam("tradeMarkEartag") String tradeMarkEartag) {
        GenealogicalFilesModel model = this.genealogicalFilesService.getGenealogicalFilesModelByTradeMarkEartag(tradeMarkEartag);
        if (model == null) {
            return Responses.errorResponse("无效的耳牌号！");
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("self", tradeMarkEartag);
            map.put("mother", model.getEartagOfMother());
            map.put("father", model.getEartagOfFather());
            map.put("mothersMother", model.getEartagOfMothersMother());
            map.put("mothersFather", model.getEartagOfMothersFather());
            map.put("fathersFather", model.getEartagOfFathersFather());
            map.put("fathersMother", model.getEartagOfFathersMother());
            return Responses.successResponse(map);
        }
    }
    /**
     * 用于条件查找
     * METHOD:POST
     * @param genealogicalRequest 系谱请求类
     * @return 查询结果/查询结果条数
     */
    //bound为必传参数
    @Permit(authorities = "query_pedigree_files")
    @GetMapping(value = "/{id}")
    public Response findShow(@PathVariable(value = "id")long id,
                             GenealogicalRequest genealogicalRequest,
                             HttpServletRequest httpServletRequest) {

      Map<Long, List<Long>> factoryMap = null;
      String roleString = TokenAnalysis.getFlag(httpServletRequest.getHeader(Constants.AUTHORIZATION));
      if (roleString == null) {
        return Responses.errorResponse("认证信息错误");
      }
      Byte role = Byte.parseByte(roleString);
      if (role == 0) {
        genealogicalRequest.setFactoryNum(id);
      } else if (role == 1) {
        factoryMap = AgentUtil.getAllSubordinateFactory(String.valueOf(id));
        List<Long> factoryList = new ArrayList<>();
        if (factoryMap == null) {
            return Responses.errorResponse("错误！");
        }
        factoryList.addAll(factoryMap.get((long) -1));
        factoryList.addAll(factoryMap.get(0L));
        genealogicalRequest.setFactoryList(factoryList);
      } else {
        return Responses.errorResponse("你没有权限");
      }
        logger.info("invoke findShow {}",genealogicalRequest);
        List<GenealogicalFilesModel> totalList = genealogicalFilesService.getGenealogicalFilesModel(genealogicalRequest);
        int size = totalList.size();
        int page = genealogicalRequest.getPage();
        int pageSize = genealogicalRequest.getSize();
        int destIndex = (page+1) * pageSize > size + 1 ? size  : (page+1) * pageSize + 1;
        List<GenealogicalFilesModel> genealogicalFilesModels = totalList.subList(page * pageSize, destIndex);
        for (GenealogicalFilesModel genealogicalFilesModel : genealogicalFilesModels) {
            String brief = "";
            if (this.typeBriefService.getTypeBrief(genealogicalFilesModel.getTypeName()) != null) {
                brief = this.typeBriefService.getTypeBrief(genealogicalFilesModel.getTypeName()).getBrief();
            }
            genealogicalFilesModel.setBrief(brief);
        }

      if (role == 1) {
        Map<String,Object> data = new HashMap<>();
        List<GenealogicalFilesModel> factorylist = new ArrayList<>();
        List<GenealogicalFilesModel> direct = new ArrayList<>();
        List<GenealogicalFilesModel> others = new ArrayList<>();
        List<Long> directId = factoryMap.get((long) -1);
        for (GenealogicalFilesModel genealogicalFilesModel : genealogicalFilesModels) {
          if (directId.contains(genealogicalFilesModel.getFactoryNum())) {
            direct.add(genealogicalFilesModel);
          } else {
            others.add(genealogicalFilesModel);
          }
        }
        factorylist.addAll(direct);
        factorylist.addAll(others);
        data.put("List", factorylist);
        data.put("size", size);
        data.put("directSize",direct.size());
        Response response = Responses.successResponse();
        response.setData(data);
        return response;

      } else {
        return JudgeUtil.JudgeFind(genealogicalFilesModels, size);
      }

    }

//    /**
//     * 用于条件查找
//     * @param factoryNum 工厂号
//     * @param page  页号
//     * @param size  条数
//     * @return  查询结果
//     */
//     @RequestMapping(value = "find",method = RequestMethod.GET)
//     public Response findGenealogicalFiles(@NotNull @RequestParam(value = "factoryNum") long factoryNum,
//
//                                           @RequestParam(value = "page", defaultValue = "0") int page,
//                                           @RequestParam(value = "size", defaultValue = "10") int size) {
//         if (page < 0 || size < 0) {
//             return Responses.errorResponse("param is invaild");
//         }
//         logger.info("invoke find/{} {}",factoryNum, page, size);
//         RowBounds rowBounds = new RowBounds(page * size, size);
//         int total = genealogicalFilesService.allGenealogicalFilesCounts();
//         List<GenealogicalFilesModel> genealogicalFilesModels = genealogicalFilesService.getGenealogicalFilesModelByFactoryNum(factoryNum, rowBounds);
//         Response response = Responses.successResponse();
//         HashMap<String,Object> data = new HashMap<>();
//         data.put("List",genealogicalFilesModels);
//         data.put("size",size);
//         data.put("total",total);
//         response.setData(data);
//         return response;
//     }

    /**
     * 根据查询id进行操作
     * METHOD:GET
     * @param id id
     * @return 查询结果
     */
    @Permit(authorities = "query_pedigree_files")
    @RequestMapping(value = "/find/{id}",method = RequestMethod.GET)
    public Response find(@NotNull @PathVariable("id") long id ) {

        logger.info("invoke find{id} {}", id);

        GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesService.getGenealogicalFilesModelById(id);

        return JudgeUtil.JudgeFind(genealogicalFilesModel);
    }

    //update
    /**
     * 更新操作 输入数据替代原数据
     * METHOD:PUT
     * @param genealogicalFilesModel 系谱类
     * @return  更新结果
     */
    @Permit(authorities = "modify_pedigree_files")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Response update(@Validated @RequestBody GenealogicalFilesModel genealogicalFilesModel,
                           @NotNull @PathVariable(value = "id") int id) {
        if (id < 0) {
            return Responses.errorResponse("path is invalid");
        }
        genealogicalFilesModel.setId(id);
        logger.info("invoke Put /gf/{} {}",id, genealogicalFilesModel);
        int row = genealogicalFilesService.updateGenealogicalFilesModel(genealogicalFilesModel);
        return JudgeUtil.JudgeUpdate(row);
    }

    /**
     * 返回删除内容行号
     * METHOD:DELETE
     * @param id id
     * @return  删除结果
     */
    @Permit(authorities = "delete_pedigree_files")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Response delete(@NotNull @PathVariable(value = "id") long id) {
        logger.info("invoke delete {}", id);
        int row = genealogicalFilesService.deleteGenealogicalFilesModelById(id);
        return JudgeUtil.JudgeDelete(row);
    }

}
