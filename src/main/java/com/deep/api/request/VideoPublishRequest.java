package com.deep.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by huangwenhai on 2018/5/9.
 */
public class VideoPublishRequest {
  private int id;
  @NotBlank
  private String professorName;
  @NotNull
  private Long professorId;
  private String fileName;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String gmtCreate;

  int size;

  private int page = 0;
  private int pageSize = 10;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProfessorName() {
    return professorName;
  }

  public void setProfessorName(String professorName) {
    this.professorName = professorName;
  }

  public Long getProfessorId() {
    return professorId;
  }

  public void setProfessorId(Long professorId) {
    this.professorId = professorId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(String gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "VideoPublishRequest{" +
        "id=" + id +
        ", professorName='" + professorName + '\'' +
        ", professorId=" + professorId +
        ", fileName='" + fileName + '\'' +
        ", gmtCreate='" + gmtCreate + '\'' +
        ", page=" + page +
        ", pageSize=" + pageSize +
        '}';
  }
}
