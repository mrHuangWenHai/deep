package com.deep.domain.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Expression {
    @JSONField(name = "expert_id")
    private String expert_id;
    @JSONField(name = "expression")
    private String expression;

    public String getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(String expert_id) {
        this.expert_id = expert_id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
