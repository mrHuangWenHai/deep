package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.request.LiveStatRequest;
import com.deep.api.response.Responses;
import com.deep.infra.service.liveBroadcast.LiveBroadcastResp;
import com.deep.infra.service.liveBroadcast.LiveBroadcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.deep.api.response.Response;
import java.util.Map;
import java.util.HashMap;


/**
 * Created by huangwenhai on 2018/3/12.
 */

@RestController
@RequestMapping(value = "/liveBroadcast")
public class LiveBroadcastResource {

  @Resource
  private LiveBroadcastService liveBroadcastService;

  private final Logger logger = LoggerFactory.getLogger(LiveBroadcastResource.class);

  /**
   * 方法功能:获取推流地址
   * @param userid
   * @return response
   */
  @Permit(authorities = "add_live")
  @RequestMapping(value = "/getPushUrl/{id}", method = RequestMethod.GET)
  public Response getLiveBroadCastPushUrl(@PathVariable("id") String userid) {

    if (userid.equals("")) {
      return Responses.errorResponse("userid 不能为空");
    }
    logger.info("invoke /liveBroadcast/getPushUrl/{}",userid);
    LiveBroadcastResp resp = liveBroadcastService.getLiveBroadCastPushUrl(userid);
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

  @RequestMapping(value = "/getLiveUrl/{id}")
  public Response getLiveBroadCastLiveUrl(@PathVariable("id") String  userid) {

    if (userid.equals("")) {
      return Responses.errorResponse("userid 不能为空");
    }
    logger.info("invoke /liveBroadcast/getLiveUrl/{}",userid);
    LiveBroadcastResp resp = liveBroadcastService.getLiveBroadCastLiveUrl(userid);
    Response response;
    Map<String, Object> data = new HashMap<String, Object>();
    if (resp.getRet().equals("SUCCESS")) {
      response = Responses.successResponse();
    } else {
      response = Responses.errorResponse("error");
    }
    data.put("liveBroadcastResp",resp);
    response.setData(data);
    return response;
  }

  @RequestMapping(value = "/setStatus", method = RequestMethod.GET)
  public Response setLiveChannelPushStatus(@RequestParam(value = "userid", required = true)String userid,
                                           @RequestParam(value = "status", required = true)String status) {
    if (userid.equals("") || status.equals("")) {
      return Responses.errorResponse("userid status 不能为空");
    }
    logger.info("invoke /liveBroadcast/getLiveUrl {} {}",userid, status);
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
    logger.info("invoke /liveBroadcast/getStatus/{}",userid);
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

  /**
   * 方法功能:查询现在直播的接口
   * @param statRequest
   * @return response
   */
  @RequestMapping(value = "/getLiveStat")
  public Response getLiveStatMessage(LiveStatRequest statRequest) {
    logger.info("invoke /liveBroadcast/getLiveStat/{}",statRequest);
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
