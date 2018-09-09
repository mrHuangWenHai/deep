package com.deep.api.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class SalesRecordRequest {
    private List<SheepRequest> sheep;
    private Long startFactory;
    private Long endFactory;
    @NotBlank(message = "初始羊场不能为空！")
    private String startName;
    @NotBlank(message = "目标羊场不能为空！")
    private String endName;

    public List<SheepRequest> getSheep() {
        return sheep;
    }

    public void setSheep(List<SheepRequest> sheep) {
        this.sheep = sheep;
    }

    public Long getStartFactory() {
        return startFactory;
    }

    public void setStartFactory(Long startFactory) {
        this.startFactory = startFactory;
    }

    public Long getEndFactory() {
        return endFactory;
    }

    public void setEndFactory(Long endFactory) {
        this.endFactory = endFactory;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    @Override
    public String toString() {
        return "SalesRecordRequest{" +
                 "sheep=" + sheep.toString() +
                ", startFactory=" + startFactory +
                ", endFactory=" + endFactory +
                ", startName='" + startName + '\'' +
                ", endName='" + endName + '\'' +
                '}';
    }
}
