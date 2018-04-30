package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.domain.service.TrackBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

/**
 * Created by huangwenhai on 2018/4/10.
 */

@RequestMapping("/trackBack")
@RestController
public class TrackBackResource {

  private final Logger logger = LoggerFactory.getLogger(TrackBackResource.class);

  @Resource
  TrackBackService trackBackService;

  @RequestMapping("/immune")

  Response trackBackSheepByImmuneEarTag(@RequestParam("immuneEarTag") @NotNull String immuneEarTag) {

    logger.info("/trackBack/immune  immuneTag = {}",immuneEarTag);
    if (immuneEarTag.length() == 0) {
      return Responses.errorResponse("immuneTag is empty");
    }


    GenealogicalFilesModel genealogicalFilesModel = trackBackService.getGenealogicalFilesModelByimmuneEartag(immuneEarTag);

    Map<String, Object> data = new  HashMap<String, Object>();
    data.put("GenealogicalFilesModel", genealogicalFilesModel);
    Response response = Responses.successResponse();
    response.setData(data);
    return response;
  }

  @RequestMapping("/trademark")

  Response trackBackSheepByTrademarkEarTag(@RequestParam("trademarkEarTag") @NotNull String trademarkEarTag) {

    logger.info("/trackBack/trademark TrademarkEarTag = {}",trademarkEarTag);
    if (trademarkEarTag.length() == 0) {
      return Responses.errorResponse("trademarkEarTag is empty");
    }

    GenealogicalFilesModel genealogicalFilesModel = trackBackService.getGenealogicalFilesModelBytradeMarkEartag(trademarkEarTag);

    Map<String, Object> data = new  HashMap<String, Object>();
    data.put("GenealogicalFilesModel", genealogicalFilesModel);
    Response response = Responses.successResponse();
    response.setData(data);
    return response;
  }

  @RequestMapping("/native")

  Response trackBackSheepByNativeEarTag(@RequestParam("nativeEarTag") @NotNull String nativeEarTag) {
    logger.info("/trackBack/trademark nativeEarTag = {}",nativeEarTag);
    if (nativeEarTag.length() == 0) {
      return Responses.errorResponse("trademarkEarTag is empty");
    }
    GenealogicalFilesModel genealogicalFilesModel = trackBackService.getGenealogicalFilesModelByNativeEartag(nativeEarTag);
    Map<String, Object> data = new  HashMap<String, Object>();
    data.put("GenealogicalFilesModel", genealogicalFilesModel);
    Response response = Responses.successResponse();
    response.setData(data);
    return response;
  }

}
