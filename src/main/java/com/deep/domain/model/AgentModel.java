package com.deep.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public class AgentModel {
    private int id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private byte agentRank;

    @NotBlank(message = "代理名称不能为空")
    private String agentName;

    @NotEmpty(message = "代理区域不能为空")
    private String agentArea;

    private int agentFather;

    private long responsibleId;

    private String responsibleName;


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

    public byte getAgentRank() {
        return agentRank;
    }

    public void setAgentRank(byte agentRank) {
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

    public int getAgentFather() {
        return agentFather;
    }

    public void setAgentFather(int agentFather) {
        this.agentFather = agentFather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgentModel that = (AgentModel) o;

        if (id != that.id) return false;
        if (agentRank != that.agentRank) return false;
        if (agentFather != that.agentFather) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
        if (agentName != null ? !agentName.equals(that.agentName) : that.agentName != null) return false;
        if (agentArea != null ? !agentArea.equals(that.agentArea) : that.agentArea != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        result = 31 * result + (int) agentRank;
        result = 31 * result + (agentName != null ? agentName.hashCode() : 0);
        result = 31 * result + (agentArea != null ? agentArea.hashCode() : 0);
        result = 31 * result + agentFather;
        return result;
    }
}
