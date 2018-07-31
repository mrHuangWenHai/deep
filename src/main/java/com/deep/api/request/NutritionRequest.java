package com.deep.api.request;

public class NutritionRequest {
    private String startTime;
    private String endTime;
    private String factoryName;
    private String earTag;

    private Byte pass;
    private Long factory;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        if (!"".equals(startTime)) this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if (!"".equals(endTime)) this.endTime = endTime;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        if (!"".equals(factoryName)) this.factoryName = factoryName;
    }

    public String getEarTag() {
        return earTag;
    }

    public void setEarTag(String earTag) {
        if (!"".equals(earTag)) this.earTag = earTag;
    }

    public Byte getPass() {
        return pass;
    }

    public void setPass(Byte pass) {
        this.pass = pass;
    }

    public Long getFactory() {
        return factory;
    }

    public void setFactory(Long factory) {
        this.factory = factory;
    }
}
