package com.deep.api.request;

import javax.validation.constraints.NotBlank;

public class SheepRequest {
    private Long id;
    @NotBlank(message = "商标耳牌不能为空！")
    private String trademarkEarTag;
    private String immuneEarTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrademarkEarTag() {
        return trademarkEarTag;
    }

    public void setTrademarkEarTag(String trademarkEarTag) {
        this.trademarkEarTag = trademarkEarTag;
    }

    public String getImmuneEarTag() {
        return immuneEarTag;
    }

    public void setImmuneEarTag(String immuneEarTag) {
        this.immuneEarTag = immuneEarTag;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "id=" + id +
                ", trademarkEarTag='" + trademarkEarTag + '\'' +
                ", immuneEarTag='" + immuneEarTag + '\'' +
                '}';
    }
}
