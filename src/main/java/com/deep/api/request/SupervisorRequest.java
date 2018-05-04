package com.deep.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * supervisor check
 */
public class SupervisorRequest {
    private Integer supervisorId;

    @NotBlank(message = "supervisor can not be null")
    private String supervisorName;

    @Max(value = 1)
    @Min(value = 0)
    private Byte ispassSup;

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Byte getIspassSup() {
        return ispassSup;
    }

    public void setIspassSup(Byte ispassSup) {
        this.ispassSup = ispassSup;
    }
}
