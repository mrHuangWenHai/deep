package com.deep.api.response;

/**
 * Created by huangwenhai on 2018/2/1.
 */
public class RespMeta {
  private int code = -1;
  private String errorMsg;

  public int getCode() {
    return code;
  }

  void setCode(int code) {
    this.code = code;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
