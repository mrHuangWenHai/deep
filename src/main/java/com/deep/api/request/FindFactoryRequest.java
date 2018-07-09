package com.deep.api.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class FindFactoryRequest {
    @NotBlank(message = "羊场号不能为空")
    private String factoryNum;
    @Min(value = 0)
    private int page;
    @Min(value = 5)
    private int size;

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
