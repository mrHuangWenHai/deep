package com.deep.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * professor check
 */
public class ProfessorRequest {

    private Integer professor;          // 专家的ID

//    @NotBlank(message = "professor can not be null")
    private String name;                // 专家姓名
    @Max(value = 1)
    @Min(value = 0)
    private Byte ispassCheck;           // 是否审核通过

    private String upassReason;

    private Long factoryNum;

    public Long getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Long factoryNum) {
        this.factoryNum = factoryNum;
    }

    public Integer getProfessor() {
        return professor;
    }

    public void setProfessor(Integer professor) {
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpassReason() {
        return upassReason;
    }

    public void setUpassReason(String upassReason) {
        this.upassReason = upassReason;
    }

    public Byte getIspassCheck() {
        return ispassCheck;
    }

    public void setIspassCheck(Byte ispassCheck) {
        this.ispassCheck = ispassCheck;
    }

    @Override
    public String toString() {
        return "ProfessorRequest{" +
                "professor=" + professor +
                ", name='" + name + '\'' +
                ", ispassCheck=" + ispassCheck +
                ", upassReason='" + upassReason + '\'' +
                ", factoryNum=" + factoryNum +
                '}';
    }
}
