package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.service.DisinfectionArchivesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import com.deep.api.request.DisinfectionArchivesRequest;

/**
 * Created by huangwenhai on 2018/4/16.
 */

@RequestMapping("/da")
@RestController
public class DisinfectionArchivesResource {

  private Logger logger = LoggerFactory.getLogger(DisinfectionArchivesResource.class);
  @Resource
  private DisinfectionArchivesService disinfectionArchivesService;

  @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
  public Response addDisinfectionArchives(@Valid @RequestBody DisinfectionArchivesRequest disinfectionArchivesRequest) {

    logger.info("/da/add {}", disinfectionArchivesRequest);
    int insertCount = disinfectionArchivesService.addDisinfectionArchives(disinfectionArchivesRequest.getList());
    if (insertCount != 0) {
      Map<String, Object> data = new HashMap<String, Object>();
      data.put("insertCount", insertCount);
      Response response = Responses.successResponse();
      response.addData("data",data);
      return response;
    } else {
      return Responses.errorResponse("add fail");
    }
  }

//  @RequestMapping(value = "/getAll")
//  public Response getAllDisinfectionArchives(@RequestParam(value = "page", defaultValue = "0") int page,
//                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//    logger.info("/da/getAll = {}",page,pageSize);
//    if (page < 0 || pageSize < 0) {
//      return Responses.errorResponse("param is unvaild");
//    }
//  }
}
