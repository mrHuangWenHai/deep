package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.OperationCoditionRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.OperationFile;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.service.OperationFileService;
import com.deep.domain.util.JudgeUtil;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangwenhai on 2018/4/17.
 */

@RequestMapping("/of")
@RestController
public class OperationFileResource {
  private Logger logger = LoggerFactory.getLogger(OperationFileResource.class);
  @Resource
  private OperationFileService operationFileService;

  @Permit(authorities = "increase_health_and_animal_welfare")
  @PostMapping(value = "")
  Response addOperationFile(@Valid @RequestBody OperationFile operationFile,
                            BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      Response response = Responses.errorResponse("param is error");
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("param",bindingResult.getAllErrors());
      response.setData(map);
      return response;
    }

    logger.info("invoke Post /of {}",operationFile);

    try {
      int isSuccess  = operationFileService.addOperationFile(operationFile);
      if (isSuccess == 1) {
        Response response = Responses.successResponse();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id",operationFile.getId());
        response.setData(data);
        return response;
      } else {
        return Responses.errorResponse("add fail");
      }
    } catch (Exception e) {
      return Responses.errorResponse(e.getMessage());
    }
  }

  @Permit(authorities = "inquiries_on_hygiene_and_animal_welfare")
  @GetMapping(value = "/{id}")
  Response getOperationFile(@PathVariable(value = "id")long id,
                            OperationCoditionRequest operationCoditionRequest,
                            HttpServletRequest httpServletRequest) {

    try {
      Map<Long, List<Long>> factoryMap = null;
      String roleString = TokenAnalysis.getFlag(httpServletRequest.getHeader(Constants.AUTHORIZATION));
      if (roleString == null) {
        return Responses.errorResponse("认证信息错误");
      }
      Byte role = Byte.parseByte(roleString);
      if (role == 0) {
        operationCoditionRequest.setFactoryNum(id);
      } else if (role == 1) {
        factoryMap = AgentUtil.getAllSubordinateFactory(String.valueOf(id));
        List<Long> factoryList = new ArrayList<>();
        factoryList.addAll(factoryMap.get(new Long(-1)));
        factoryList.addAll(factoryMap.get(new Long(0)));
        operationCoditionRequest.setFactoryList(factoryList);
      } else {
        return Responses.errorResponse("你没有权限");
      }
      logger.info("invoke Get /of {}",operationCoditionRequest);
      List<OperationFile> totalList = operationFileService.getOperationFile(operationCoditionRequest);
      int size = totalList.size();
      int page = operationCoditionRequest.getPage();
      int pageSize = operationCoditionRequest.getPageSize();
      int destIndex = (page+1) * pageSize + 1  > size ? size : (page+1) * pageSize + 1;
      List<OperationFile> list = totalList.subList(page * pageSize, destIndex);

      if (role == 1) {
        Map<String,Object> data = new HashMap<>();
        List<OperationFile> factorylist = new ArrayList<>();
        List<OperationFile> direct = new ArrayList<>();
        List<OperationFile> others = new ArrayList<>();
        List<Long> directId = factoryMap.get((long) -1);
        for (OperationFile operationFile : list) {
          if (directId.contains(operationFile.getFactoryNum())) {
            direct.add(operationFile);
          } else {
            others.add(operationFile);
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
        Map<String, Object> data = new HashMap<>();
        data.put("size",size);
        data.put("List",list);
        Response response = Responses.successResponse();
        response.setData(data);
        return response;
      }

    } catch (Exception e) {
      return Responses.errorResponse(e.getMessage());
    }
  }

  @Permit(authorities = "supervise_and_audit_health_and_animal_welfare")
  @PatchMapping(value = "s/{id}")
  Response setCheckStatus(@PathVariable(value = "id")int id,
                          @RequestBody Map<String, String> json) {
    if (!json.containsKey("ispassSup")) {
      return Responses.errorResponse("lock param ispassSup");
    }

//    short checkStatus = json.get("ispassSup").shortValue();
    short checkStatus = Short.valueOf(json.get("ispassSup"));
    if (id < 0 || checkStatus < 0 || checkStatus > 2) {
      return Responses.errorResponse("param is invalid");
    }
    logger.info("/of/s/{} {}",id,checkStatus);

    try {
      int isSuccess = operationFileService.updateSupStatus(id, checkStatus);
      if (isSuccess == 1) {
          return Responses.successResponse();
      } else {
        return Responses.errorResponse("update fail");
      }

    } catch (Exception e) {
      return Responses.errorResponse(e.getMessage());
    }
  }

  @Permit(authorities = "experts_review_health_and_animal_welfare")
  @PatchMapping(value = "p/{id}")
  Response setSupStatus(@PathVariable(value = "id")int id,
                        @RequestBody Map<String, String> json) {
    if (!json.containsKey("ispassCheck")) {
      return Responses.errorResponse("lock param ispassCheck");
    }

//    short supStatus = json.get("ispassCheck").shortValue();
    short supStatus = Short.valueOf(json.get("ispassCheck"));
    if (id < 0 || supStatus < 0 || supStatus > 2) {
      return Responses.errorResponse("param is invalid");
    }
    logger.info("/of/p/{} {}",id,supStatus);
    try {
      int isSuccess = operationFileService.updateCheckStatus(id, supStatus);
      if (isSuccess == 1) {
        return Responses.successResponse();
      } else {
        return Responses.errorResponse("update fail");
      }

    } catch (Exception e) {
      return Responses.errorResponse(e.getMessage());
    }
  }

  @Permit(authorities = "modify_health_and_animal_welfare")
  @PutMapping(value = "/{id}")
  public Response update(@RequestBody OperationFile operationFile,
                         @PathVariable(value = "id") int id) {
    operationFile.setId(id);
    int isSuccess = operationFileService.updateOperationFileByOperationFile(operationFile);
    if (isSuccess == 1) {
      return Responses.successResponse();
    } else {
      return Responses.errorResponse("add error");
    }
  }

  @Permit(authorities = "inquiries_on_hygiene_and_animal_welfare")
  @GetMapping(value = "/find/{id}")
  public Response getOperationFileById(@PathVariable(value = "id") int id) {
    OperationFile operationFile = operationFileService.getOperationFileById(id);
    Response response = Responses.successResponse();
    Map<String, Object> data = new HashMap<>();
    data.put("model", operationFile);
    response.setData(data);
    return response;
  }

  @Permit(authorities = "remove_health_and_animal_welfare")
  @DeleteMapping(value = "/{id}")
  public Response deleteOperationFile(@PathVariable("id") int id) {
    int isSuccess = operationFileService.deleteOperationFile(id);
    if (isSuccess == 1) {
      return Responses.successResponse();
    } else {
      return Responses.errorResponse("删除错误");
    }
  }
}
