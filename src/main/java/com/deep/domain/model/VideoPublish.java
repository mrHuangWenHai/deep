package com.deep.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by huangwenhai on 2018/5/8.
 */
public class VideoPublish {

  private int id;
  @NotBlank
  private String professorName;
  @NotNull
  private Long professorId;
  private String fileName;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String gmtCreate;

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

  @Override
  public String toString() {
    return "VideoPublish{" +
        "id=" + id +
        ", professorName='" + professorName + '\'' +
        ", professorId=" + professorId +
        ", fileName='" + fileName + '\'' +
        ", gmtCreate='" + gmtCreate + '\'' +
        '}';
  }
}

