package com.deep.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public class AgentRequest {
    private int id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    @NotBlank(message = "代理等级不能为空")
    private String agentRank;

    @NotBlank(message = "代理名称不能为空")
    private String agentName;

    @NotEmpty(message = "代理区域不能为空")
    private String agentArea;

    private int factoryNum;

    private long responsibleId;

    private String responsibleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getAgentRank() {
        return agentRank;
    }

    public void setAgentRank(String agentRank) {
        this.agentRank = agentRank;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentArea() {
        return agentArea;
    }

    public void setAgentArea(String agentArea) {
        this.agentArea = agentArea;
    }

    public int getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(int factoryNum) {
        this.factoryNum = factoryNum;
    }

    public long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(long responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }
}
