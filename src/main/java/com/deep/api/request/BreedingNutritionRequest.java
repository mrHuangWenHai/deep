package com.deep.api.request;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class BreedingNutritionRequest {
    @NotNull(message = "查询时间不能为空")
    private Timestamp time;
    @NotNull(message = "羊场编号不能为空")
    private Integer factoryNumber;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
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
                "time=" + time +
                ", factoryNumber=" + factoryNumber +
                '}';
    }
}
