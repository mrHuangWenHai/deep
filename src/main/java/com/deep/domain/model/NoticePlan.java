package com.deep.domain.model;


import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

public class NoticePlan implements Serializable {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;


    @NotBlank(message = "不能为空")
    private String operatorName;

    private Integer operatorId;

    private String type;

    @NotBlank(message = "不能为空")
    private String title;

    @NotBlank(message = "不能为空")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

}