package com.deep.infra.service.liveBroadcast;
import java.util.Map;

/**
 * Created by huangwenhai on 2018/3/11.
 */
public class LiveBroadcastResp {
  private String ret; //SUCCESS,FAIL
  private Map<String, Object> data;

  public boolean successResp() {
    return "SUCCESS".equalsIgnoreCase(ret);
  }

  public boolean failResp() {
    return "FAIL".equalsIgnoreCase(ret);
  }

  public String getErrorCode() {
    return (String) data.get("error_code");
  }

  public String getRet() {
    return ret;
  }

  public void setRet(String ret) {
    this.ret = ret;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }
}