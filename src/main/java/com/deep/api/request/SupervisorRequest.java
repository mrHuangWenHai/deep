package com.deep.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * supervisor check
 */
public class SupervisorRequest {
    // 监督员的ID
    private Integer supervisor;

//    @NotBlank(message = "supervisor can not be null")
    private String name;

    private Long factoryNum;

    @Max(value = 1)
    @Min(value = 0)
    private Byte ispassSup;

    public Long getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Long factoryNum) {
        this.factoryNum = factoryNum;
    }

    public Integer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIspassSup() {
        return ispassSup;
    }

    public void setIspassSup(Byte ispassSup) {
        this.ispassSup = ispassSup;
    }

    @Override
    public String toString() {
        return "SupervisorRequest{" +
                "supervisor=" + supervisor +
                ", name='" + name + '\'' +
                ", factoryNum=" + factoryNum +
                ", ispassSup=" + ispassSup +
                '}';
    }
}
