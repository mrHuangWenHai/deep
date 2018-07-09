package com.deep.infra.service.liveBroadcast;

import com.deep.api.request.LiveStatRequest;
import com.deep.infra.service.utils.LiveBroadcastUtil;
import okhttp3.Response;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import java.util.*;
import com.alibaba.fastjson.*;

/**
 * Created by huangwenhai on 2018/3/11.
 */

@Service
public class LiveBroadcastService {

  public static final String LiveManagerServiceUrl = "http://fcgi.video.qcloud.com/common_access?";
  public static final String liveChannelSetStatusInterfaceName = "Live_Channel_SetStatus";
  public static final String liveChannelGetStatusInterfaceName = "Live_Channel_GetStatus";
  public static final String getLivePushStatUrl = "http://statcgi.video.qcloud.com/common_access?";
  public static final String pushKey = "4282008f9841bcbdffb3751a95479714";
  public static final String liveKey = "bbd3dacfd84a02a7a85bf58d852323c9";
  public static final String bizid = "21502";
  public static final String appid = "1252233888";
  public static final String pushUrlSub = ".livepush.myqcloud.com/live/";
  public static final String playUrlSub = ".liveplay.myqcloud.com/live/";

  public ChannelStatusResponse setLiveChannel(String status, String channelId) {

    OkHttpClient client = new OkHttpClient();
    ChannelStatusResponse response = new ChannelStatusResponse();

    try {

      long t = LiveBroadcastUtil.getUnixTime(Calendar.MINUTE, 2);
      String input = new StringBuilder().
          append(liveKey).
          append(String.valueOf(t)).toString();
      String sign = LiveBroadcastUtil.md5Secret(input);

      String targetUrl = new StringBuilder().
          append(LiveManagerServiceUrl).
          append("appid=").
          append(appid).
          append("&interface=").
          append(liveChannelSetStatusInterfaceName).
          append("&Param.s.channel_id=").
          append(channelId).
          append("&Param.n.status=").
          append(status).
          append("&t=").
          append(String.valueOf(t)).
          append("&sign=").
          append(sign).toString();

      Request request = new Request.Builder()
          .url(targetUrl)
          .build();
      Response clientResponse = client.newCall(request).execute();

      try {
        String jsonResponse = clientResponse.body().string();
        JSONObject jsonObject = JSON.parseObject(jsonResponse);
        response.setRet(jsonObject.getInteger("ret"));
        response.setMessage(jsonObject.getString("message"));

      } catch (Exception ex) {
        response.setRet(-1);
        response.setMessage(ex.getMessage());
      }

    } catch (Exception ex) {
      response.setRet(-1);
      response.setMessage(ex.getMessage());
    }

    return response;
  }

  public ChannelStatusResponse getLiveChannel(String channelId) {

    OkHttpClient client = new OkHttpClient();
    ChannelStatusResponse response = null;

    try {

      long t = LiveBroadcastUtil.getUnixTime(Calendar.MINUTE, 2);
      String input = new StringBuilder()
          .append(liveKey)
          .append(String.valueOf(t)).toString();
      String sign = LiveBroadcastUtil.md5Secret(input);

      String targetUrl = new StringBuilder()
          .append(LiveManagerServiceUrl)
          .append("appid=")
          .append(appid)
          .append("&interface=")
          .append(liveChannelGetStatusInterfaceName)
          .append("&Param.s.channel_id=")
          .append(channelId)
          .append("&t=")
          .append(String.valueOf(t))
          .append("&sign=")
          .append(sign).toString();

      Request request = new Request.Builder()
          .url(targetUrl)
          .build();
      Response clientResponse = client.newCall(request).execute();

      try {
        String jsonResponse = clientResponse.body().string();
        response = JSON.parseObject(jsonResponse, new TypeReference<ChannelStatusResponse>() {});
      } catch (Exception ex) {
        response = new ChannelStatusResponse();
        response.setRet(-1);
        response.setMessage(ex.getMessage());
      }


    } catch (Exception e) {
      response = new ChannelStatusResponse();
      response.setRet(-1);
      response.setMessage(e.getMessage());
    }

    return response;

  }

  public ChannelStatusResponse getLiveState(String interfaceName, LiveStatRequest statRequest) {

    OkHttpClient client = new OkHttpClient();
    ChannelStatusResponse response = null;

    try {

      long t = LiveBroadcastUtil.getUnixTime(Calendar.MINUTE, 2);
      String input = new StringBuilder()
          .append(liveKey)
          .append(String.valueOf(t)).toString();
      String sign = LiveBroadcastUtil.md5Secret(input);

      StringBuilder targetUrlBuild = new StringBuilder()
          .append(LiveManagerServiceUrl)
          .append("appid=")
          .append(appid)
          .append("&interface=")
          .append(interfaceName)
          .append("&t=")
          .append(t)
          .append("&sign=")
          .append(sign)
          .append("&Param.n.status=1")
          .append("&Param.n.page_no=")
          .append(statRequest.getPageNo())
          .append("&Param.n.page_size=")
          .append(statRequest.getPageSize());
      if (statRequest.getUserId() != null) {
        targetUrlBuild.append("&Param.s.stream_id=").append(bizid+"_"+statRequest.getUserId());
      }
      if (statRequest.getPulldomain() != null) {
        targetUrlBuild.append("&Param.s.pull_domain=").append(statRequest.getPulldomain());
      }

      String targetUrl = targetUrlBuild.toString();
      Request request = new Request.Builder()
          .url(targetUrl)
          .build();
      Response clientResponse = client.newCall(request).execute();
      System.out.println(clientResponse.toString());

      try {
        String jsonResponse = clientResponse.body().string();
        System.out.println(jsonResponse);
        response = JSON.parseObject(jsonResponse, new TypeReference<ChannelStatusResponse>() {});
      } catch (Exception ex) {
        response = new ChannelStatusResponse();
        response.setRet(-1);
        response.setMessage(ex.getMessage());
      }

    } catch (Exception e) {
      response = new ChannelStatusResponse();
      response.setRet(-1);
      response.setMessage(e.getMessage());
    }

    return response;

  }

  public LiveBroadcastResp getLiveBroadCastLiveUrl(String userid) {

    String streamId = bizid+"_"+userid;
    LiveBroadcastResp resp = new LiveBroadcastResp();
    Map<String, Object> data = new HashMap<String, Object>();

    String playUrl = new StringBuilder().
        append(bizid).
        append(playUrlSub).
        append(streamId).toString();

    data.put("playUrl", playUrl);
    resp.setData(data);
    resp.setRet("SUCCESS");
    return resp;

  }


  public LiveBroadcastResp getLiveBroadCastPushUrl(String userid) {

    String streamId = bizid+"_"+userid;
    long txTime = LiveBroadcastUtil.getUnixTime(Calendar.DATE,1);
    LiveBroadcastResp resp = new LiveBroadcastResp();
    String safeUrl = LiveBroadcastUtil.getSafeUrl(pushKey, streamId, txTime);
    Map<String, Object> data = new HashMap<String, Object>();
    if (safeUrl == null) {
      resp.setRet("FAIL");
      resp.setRet("服务器错误");
      data.put("error_code", "-1");
      resp.setData(data);
      return resp;
    }

    ChannelStatusResponse startChannel = setLiveChannel("1",streamId);

    if (startChannel.getRet() != 0) {
      data.put("error_code", String.valueOf(startChannel.getRet()));
      data.put("errorMessage", startChannel.getMessage());
      System.out.println(startChannel.getMessage());
      resp.setRet("FAIL");
      resp.setData(data);
      return resp;
    }

    String pushUrl = new StringBuilder().
        append("rtmp://").
        append(bizid).
        append(pushUrlSub).
        append(streamId).
        append("?bizid=").
        append(bizid).
        append(safeUrl).toString();

    data.put("pushUrl", pushUrl);
    resp.setData(data);
    resp.setRet("SUCCESS");
    return resp;

  }

  public LiveBroadcastResp setLiveBroadcastStatus(String userid,String status) {

    String channelId = bizid+"_"+userid;
    ChannelStatusResponse response = setLiveChannel(status, channelId);
    Map<String, Object>data = new HashMap<String, Object>();
    LiveBroadcastResp resp = new LiveBroadcastResp();

    if (response.getRet() != 0) {
      data.put("error_code", String.valueOf(response.getRet()));
      data.put("errorMessage", response.getMessage());
      System.out.println(response.getMessage());
      resp.setRet("FAIL");
      resp.setData(data);
      return resp;
    }

    data.put("error_code", String.valueOf(response.getRet()));
    resp.setRet("SUCCESS");
    resp.setData(data);
    return resp;
  }

  public LiveBroadcastResp getLiveChannelPushStatus(String userid) {
    String channelId = bizid+"_"+userid;
    ChannelStatusResponse response = getLiveChannel(channelId);
    Map<String, Object>data = new HashMap<String, Object>();
    LiveBroadcastResp resp = new LiveBroadcastResp();

    if (response.getRet() != 0) {
      data.put("error_code", String.valueOf(response.getRet()));
      data.put("errorMessage", response.getMessage());
      System.out.println(response.getMessage());
      resp.setRet("FAIL");
      resp.setData(data);
      return resp;
    }

    data.put("error_code", String.valueOf(response.getRet()));
    data.put("output", response.getOutput());
    resp.setRet("SUCCESS");
    resp.setData(data);
    return resp;
  }

  public LiveBroadcastResp getLiveStatMessage(LiveStatRequest statRequest) {

    ChannelStatusResponse response = getLiveState("Live_Channel_GetChannelList", statRequest);
    LiveBroadcastResp resp = new LiveBroadcastResp();
    Map<String, Object>data = new HashMap<String, Object>();

    if (response.getRet() != 0) {
      data.put("error_code", String.valueOf(response.getRet()));
      data.put("errorMessage", response.getMessage());
      System.out.println(response.getMessage());
      resp.setRet("FAIL");
      resp.setData(data);
      return resp;
    }

    data.put("error_code", String.valueOf(response.getRet()));
    data.put("output", response.getOutput());
    resp.setRet("SUCCESS");
    resp.setData(data);
    return resp;
  }

  private static class ChannelStatusResponse {

    private int ret;
    private String message;
    private String errmsg;
    private List<Object> output;
    private int retcode;

    public String getErrmsg() {
      return errmsg;
    }

    public void setErrmsg(String errmsg) {
      this.errmsg = errmsg;
    }

    public int getRetcode() {
      return retcode;
    }

    public void setRetcode(int retcode) {
      this.retcode = retcode;
    }

    public int getRet() {
      return ret;
    }

    public void setRet(int ret) {
      this.ret = ret;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message)
    {
      this.message = message;
    }

    public List<Object> getOutput() {
      return output;
    }

    public void setOutput(List<Object> output) {
      this.output = output;
    }
  }




}
