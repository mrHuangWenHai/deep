package com.deep.api.resource;

import com.deep.api.request.OperationCoditionRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.OperationFile;
import com.deep.domain.service.OperationFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
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

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  Response addOperationFile(@Valid @RequestBody OperationFile operationFile,
                            BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return Responses.errorResponse("param is invalid");
    }

    logger.info("/of/add {}",operationFile);
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

  @RequestMapping(value = "/find", method = RequestMethod.POST)
  Response getOperationFile(@RequestBody OperationCoditionRequest operationCoditionRequest) {
    logger.info("/of/find {}",operationCoditionRequest);
    try {
      List<OperationFile> list = operationFileService.getOperationFile(operationCoditionRequest);
      Map<String, Object> data = new HashMap<>();
      data.put("count",data.size());
      data.put("data",list);
      Response response = Responses.successResponse();
      response.setData(data);
      return response;
    } catch (Exception e) {
      return Responses.errorResponse(e.getMessage());
    }
  }

  @RequestMapping(value = "/set", method = RequestMethod.PATCH)
  Response setCheckStatus(@RequestBody Map<String, Integer> json) {

    if (!json.containsKey("id") || !json.containsKey("checkStatus")) {
      return Responses.errorResponse("lock param");
    }
    int id = json.get("id").intValue();
    short checkStatus = json.get("checkStatus").shortValue();
    if (id < 0 || checkStatus < 0 || checkStatus > 2) {
      return Responses.errorResponse("param is invalid");
    }
    logger.info("/of/set {}",id,checkStatus);

    try {
      int isSuccess = operationFileService.updateOperationFile(id, checkStatus);
      if (isSuccess == 1) {
          return Responses.successResponse();
      } else {
        return Responses.errorResponse("update fail");
      }

    } catch (Exception e) {
      return Responses.errorResponse(e.getMessage());
    }

  }
}
