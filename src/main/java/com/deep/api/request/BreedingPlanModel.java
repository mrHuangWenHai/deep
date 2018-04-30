package com.deep.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * author: Created  By  Caojiawei
 * date: 2018/4/12  18:33
 */
public class BreedingPlanModel implements Serializable {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Date gmtSupervised;

    private Long factoryNum;

    private String factoryName;

    private String building;

    private String mEtI;

    private String mEtB;

    private String fEtI;

    private String fEtB;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date breedingT;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date gestationT;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date prenatalIT;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date cubT;

    private Integer quantity;

    private String operatorName;

    private String professorName;

    private String supervisorName;

    private Integer operatorId;

    private Integer professorId;

    private Integer supervisorId;

    private String remark;

    private Byte ispassCheck;

    private String upassReason;

    private Byte ispassSup;

    private String search_string;

    private String s_breedingT;

    private String s_gestationT;

    private String s_prenatalIT;

    private String s_cubT;

    private String s_diagnosisT;

    private String s_nutritionT;

    private String s_gmtCreate1;

    private String s_gmtCreate2;

    private String s_gmtModified1;

    private String s_gmtModified2;

    private String s_breedingT1;

    private String s_breedingT2;

    private String s_gestationT1;

    private String s_gestationT2;

    private String s_prenatalIT1;

    private String s_prenatalIT2;

    private String s_cubT1;

    private String s_cubT2;

    private String s_diagnosisT1;

    private String s_diagnosisT2;

    private String s_nutritionT1;

    private String s_nutritionT2;

    private String downloadPath;

    private int size;

    private int page;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtSupervised() {
        return gmtSupervised;
    }

    public void setGmtSupervised(Date gmtSupervised) {
        this.gmtSupervised = gmtSupervised;
    }

    public Long getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Long factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getmEtI() {
        return mEtI;
    }

    public void setmEtI(String mEtI) {
        this.mEtI = mEtI;
    }

    public String getmEtB() {
        return mEtB;
    }

    public void setmEtB(String mEtB) {
        this.mEtB = mEtB;
    }

    public String getfEtI() {
        return fEtI;
    }

    public void setfEtI(String fEtI) {
        this.fEtI = fEtI;
    }

    public String getfEtB() {
        return fEtB;
    }

    public void setfEtB(String fEtB) {
        this.fEtB = fEtB;
    }

    public Date getBreedingT() {
        return breedingT;
    }

    public void setBreedingT(Date breedingT) {
        this.breedingT = breedingT;
    }

    public Date getGestationT() {
        return gestationT;
    }

    public void setGestationT(Date gestationT) {
        this.gestationT = gestationT;
    }

    public Date getPrenatalIT() {
        return prenatalIT;
    }

    public void setPrenatalIT(Date prenatalIT) {
        this.prenatalIT = prenatalIT;
    }

    public Date getCubT() {
        return cubT;
    }

    public void setCubT(Date cubT) {
        this.cubT = cubT;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpassReason() {
        return upassReason;
    }

    public void setUpassReason(String upassReason) {
        this.upassReason = upassReason;
    }

    public Byte getIsPassCheck() {
        return ispassCheck;
    }

    public void setIsPassCheck(Byte ispassCheck) {
        this.ispassCheck = ispassCheck;
    }

    public Byte getIsPassSup() {
        return ispassSup;
    }

    public void setIsPassSup(Byte ispassSup) {
        this.ispassSup = ispassSup;
    }

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }

    public String getS_breedingT() {
        return s_breedingT;
    }

    public void setS_breedingT(String s_breedingT) {
        this.s_breedingT = s_breedingT;
    }

    public String getS_gestationT() {
        return s_gestationT;
    }

    public void setS_gestationT(String s_gestationT) {
        this.s_gestationT = s_gestationT;
    }

    public String getS_prenatalIT() {
        return s_prenatalIT;
    }

    public void setS_prenatalIT(String s_prenatalIT) {
        this.s_prenatalIT = s_prenatalIT;
    }

    public String getS_cubT() {
        return s_cubT;
    }

    public void setS_cubT(String s_cubT) {
        this.s_cubT = s_cubT;
    }

    public String getS_diagnosisT() {
        return s_diagnosisT;
    }

    public void setS_diagnosisT(String s_diagnosisT) {
        this.s_diagnosisT = s_diagnosisT;
    }

    public String getS_nutritionT() {
        return s_nutritionT;
    }

    public void setS_nutritionT(String s_nutritionT) {
        this.s_nutritionT = s_nutritionT;
    }

    public String getS_gmtCreate1() {
        return s_gmtCreate1;
    }

    public void setS_gmtCreate1(String s_gmtCreate1) {
        this.s_gmtCreate1 = s_gmtCreate1;
    }

    public String getS_gmtCreate2() {
        return s_gmtCreate2;
    }

    public void setS_gmtCreate2(String s_gmtCreate2) {
        this.s_gmtCreate2 = s_gmtCreate2;
    }

    public String getS_gmtModified1() {
        return s_gmtModified1;
    }

    public void setS_gmtModified1(String s_gmtModified1) {
        this.s_gmtModified1 = s_gmtModified1;
    }

    public String getS_gmtModified2() {
        return s_gmtModified2;
    }

    public void setS_gmtModified2(String s_gmtModified2) {
        this.s_gmtModified2 = s_gmtModified2;
    }

    public String getS_breedingT1() {
        return s_breedingT1;
    }

    public void setS_breedingT1(String s_breedingT1) {
        this.s_breedingT1 = s_breedingT1;
    }

    public String getS_breedingT2() {
        return s_breedingT2;
    }

    public void setS_breedingT2(String s_breedingT2) {
        this.s_breedingT2 = s_breedingT2;
    }

    public String getS_gestationT1() {
        return s_gestationT1;
    }

    public void setS_gestationT1(String s_gestationT1) {
        this.s_gestationT1 = s_gestationT1;
    }

    public String getS_gestationT2() {
        return s_gestationT2;
    }

    public void setS_gestationT2(String s_gestationT2) {
        this.s_gestationT2 = s_gestationT2;
    }

    public String getS_prenatalIT1() {
        return s_prenatalIT1;
    }

    public void setS_prenatalIT1(String s_prenatalIT1) {
        this.s_prenatalIT1 = s_prenatalIT1;
    }

    public String getS_prenatalIT2() {
        return s_prenatalIT2;
    }

    public void setS_prenatalIT2(String s_prenatalIT2) {
        this.s_prenatalIT2 = s_prenatalIT2;
    }

    public String getS_cubT1() {
        return s_cubT1;
    }

    public void setS_cubT1(String s_cubT1) {
        this.s_cubT1 = s_cubT1;
    }

    public String getS_cubT2() {
        return s_cubT2;
    }

    public void setS_cubT2(String s_cubT2) {
        this.s_cubT2 = s_cubT2;
    }

    public String getS_diagnosisT1() {
        return s_diagnosisT1;
    }

    public void setS_diagnosisT1(String s_diagnosisT1) {
        this.s_diagnosisT1 = s_diagnosisT1;
    }

    public String getS_diagnosisT2() {
        return s_diagnosisT2;
    }

    public void setS_diagnosisT2(String s_diagnosisT2) {
        this.s_diagnosisT2 = s_diagnosisT2;
    }

    public String getS_nutritionT1() {
        return s_nutritionT1;
    }

    public void setS_nutritionT1(String s_nutritionT1) {
        this.s_nutritionT1 = s_nutritionT1;
    }

    public String getS_nutritionT2() {
        return s_nutritionT2;
    }

    public void setS_nutritionT2(String s_nutritionT2) {
        this.s_nutritionT2 = s_nutritionT2;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
