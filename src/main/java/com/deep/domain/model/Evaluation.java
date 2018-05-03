package com.deep.domain.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Evaluation {
    @JSONField(name = "expert_id")
    private Long expert_id;
    @JSONField(name = "expert_name")
    private String expert_name;
    @JSONField(name = "evaluation")
    private int evaluation;
    @JSONField(name = "description")
    private String description;

    public Long getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(Long expert_id) {
        this.expert_id = expert_id;
    }

    public String getExpert_name() {
        return expert_name;
    }

    public void setExpert_name(String expert_name) {
        this.expert_name = expert_name;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
