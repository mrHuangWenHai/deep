package com.deep.domain.model;

/**
 * Created by 10742 on 2018/2/9.
 */
public class MsgBean {
  private String talk_id;
  private String message;
  private String user_id;
  private Long[] to;
  private String name;
  private String isExpert;
  private int mode;

  public String getTalk_id() {
    return talk_id;
  }

  public void setTalk_id(String talk_id) {
    this.talk_id = talk_id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  public Long[] getTo() {
    return to;
  }

  public void setTo(Long[] to) {
    this.to = to;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIsExpert() {
    return isExpert;
  }

  public void setIsExpert(String isExpert) {
    this.isExpert = isExpert;
  }

  public int getMode() {
    return mode;
  }

  public void setMode(int mode) {
    this.mode = mode;
  }
}

