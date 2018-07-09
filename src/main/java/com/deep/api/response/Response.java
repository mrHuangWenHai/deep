package com.deep.api.response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangwenhai on 2018/2/1.
 */
public class Response {
  private RespMeta meta;
  private Map<String, Object> data;

  /**
   * add data.
   *
   * @param name   data name
   * @param object data object
   * @return this response
   */
  public Response addData(String name, Object object) {
    if (data == null) {
      data = new HashMap<>();
    }
    data.put(name, object);
    return this;
  }

  public RespMeta getMeta() {
    return meta;
  }

  public void setMeta(RespMeta meta) {
    this.meta = meta;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Response{" +
        "meta=" + meta +
        ", data=" + data +
        '}';
  }
}
