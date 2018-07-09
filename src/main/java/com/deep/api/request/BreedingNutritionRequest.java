package com.deep.api.request;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class BreedingNutritionRequest {
    @NotNull(message = "查询时间不能为空")
    private String time;
    @NotNull(message = "羊场编号不能为空")
    private Integer factoryNumber;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(Integer factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    @Override
    public String toString() {
        return "BreedingNutritionRequest{" +
                "time='" + time + '\'' +
                ", factoryNumber=" + factoryNumber +
                '}';
    }
}
