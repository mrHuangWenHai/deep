package com.deep.api.request;

import com.deep.domain.model.DisinfectionArchives;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangwenhai on 2018/4/16.
 */
public class DisinfectionArchivesRequest implements Serializable {

  List<DisinfectionArchives> list;

  public List<DisinfectionArchives> getList() {
    return list;
  }

  public void setList(List<DisinfectionArchives> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "DisinfectionArchivesRequest{" +
        "list=" + list +
        '}';
  }
}
