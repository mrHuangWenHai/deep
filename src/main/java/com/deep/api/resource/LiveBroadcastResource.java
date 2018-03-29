package com.deep.api.resource;

import com.deep.api.request.LiveStatRequest;
import com.deep.api.response.Responses;
import com.deep.infra.service.liveBroadcast.LiveBroadcastResp;
import com.deep.infra.service.liveBroadcast.LiveBroadcastService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import com.deep.api.response.Response;
import java.util.Map;
import java.util.HashMap;
import org.springframework.validation.BindingResult;

/**
 * Created by huangwenhai on 2018/3/12.
 */

@RestController
@RequestMapping(value = "/liveBroadcast")
public class LiveBroadcastResource {

  @Resource
  private LiveBroadcastService liveBroadcastService;

  @RequestMapping(value = "/geturl", method = RequestMethod.POST)
  public Response getLiveBroadCastUrl(@RequestParam(value = "userid", required = true)String userid) {

    if (userid.equals("")) {
      return Responses.errorResponse("userid 不能为空");
    }

    LiveBroadcastResp resp = liveBroadcastService.getLiveBroadcastRespUrl(userid);
    Response response;
    Map<String, Object> data = new HashMap<String, Object>();
    if (resp.getRet().equals("SUCCESS")) {
      response = Responses.successResponse();
    } else {
      response = Responses.errorResponse("error");
    }
    data.put("liveBroadcastResp", resp);
    response.setData(data);
    return response;
  }

  @RequestMapping(value = "/setStatus", method = RequestMethod.POST)
  public Response setLiveChannelPushStatus(@RequestParam(value = "userid", required = true)String userid,
                                           @RequestParam(value = "status", required = true)String status) {
    if (userid.equals("") || status.equals("")) {
      return Responses.errorResponse("userid status 不能为空");
    }

    LiveBroadcastResp resp = liveBroadcastService.setLiveBroadcastStatus(userid,status);
    Response response;
    Map<String, Object> data = new HashMap<String, Object>();
    if (resp.getRet().equals("SUCCESS")) {
      response = Responses.successResponse();
    } else {
      response = Responses.errorResponse("error");
    }
    data.put("liveBroadcastResp", resp);
    response.setData(data);
    return response;
  }

  @RequestMapping(value = "/getStatus")
  public Response getLiveChannelPushStatus(@RequestParam(value = "userid",required = true)String userid) {

    if (userid.equals("")) {
      return Responses.errorResponse("userid 不能为空");
    }
    LiveBroadcastResp resp = liveBroadcastService.getLiveChannelPushStatus(userid);
    Response response;
    Map<String, Object> data = new HashMap<String, Object>();
    if (resp.getRet().equals("SUCCESS")) {
      response = Responses.successResponse();
    } else {
      response = Responses.errorResponse("error");
    }
    data.put("liveChannelResp", resp);
    response.setData(data);
    return response;
  }

  //腾讯处于Beta
  @RequestMapping(value = "/getLiveStat")
  public Response getLiveStatMessage(@Validated LiveStatRequest statRequest) {
    LiveBroadcastResp resp = liveBroadcastService.getLiveStatMessage(statRequest);
    Response response;
    Map<String, Object> data = new HashMap<String, Object>();
    if (resp.getRet().equals("SUCCESS")) {
      response = Responses.successResponse();
    } else {
      response = Responses.errorResponse("error");
    }
    data.put("liveChannelResp", resp);
    response.setData(data);
    return response;
  }

}
