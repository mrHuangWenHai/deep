package com.deep.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * professor check
 */
public class ProfessorRequest {
    private Integer professorId;

    @NotBlank(message = "professor can not be null")
    private String professorName;
    @Max(value = 1)
    @Min(value = 0)
    private Byte ispassCheck;

    private String upassReason;

    public String getUpassReason() {
        return upassReason;
    }

    public void setUpassReason(String upassReason) {
        this.upassReason = upassReason;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Byte getIspassCheck() {
        return ispassCheck;
    }

    public void setIspassCheck(Byte ispassCheck) {
        this.ispassCheck = ispassCheck;
    }
}
