package com.deep.domain.model;

import java.io.Serializable;
import java.util.Date;

public class RecoveryModel implements Serializable {
    private Integer id;

    private Integer companyId;

    private String companyName;

    private Integer sheepType;

    private Integer overall;

    private Integer neck;

    private Integer foreBody;

    private Integer middleBody;

    private Integer hindquarters;

    private Integer limbs;

    private String sex;

    private Integer total;

    private Integer pedigree;

    private Integer brucellosis;

    private Integer antibody;

    private Integer footMouth;

    private Integer antibiotic;

    private Integer hormone;

    private Integer result;

    private Date uploadTime;

    private String eartagAddress;

    private Integer returnId;

    private Integer pageNumb = 1;

    private Integer limit = 10;

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Integer getPageNumb() {
        return pageNumb;
    }

    public void setPageNumb(Integer pageNumb) {
        this.pageNumb = pageNumb;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getSheepType() {
        return sheepType;
    }

    public void setSheepType(Integer sheepType) {
        this.sheepType = sheepType;
    }

    public Integer getOverall() {
        return overall;
    }

    public void setOverall(Integer overall) {
        this.overall = overall;
    }

    public Integer getNeck() {
        return neck;
    }

    public void setNeck(Integer neck) {
        this.neck = neck;
    }

    public Integer getForeBody() {
        return foreBody;
    }

    public void setForeBody(Integer foreBody) {
        this.foreBody = foreBody;
    }

    public Integer getMiddleBody() {
        return middleBody;
    }

    public void setMiddleBody(Integer middleBody) {
        this.middleBody = middleBody;
    }

    public Integer getHindquarters() {
        return hindquarters;
    }

    public void setHindquarters(Integer hindquarters) {
        this.hindquarters = hindquarters;
    }

    public Integer getLimbs() {
        return limbs;
    }

    public void setLimbs(Integer limbs) {
        this.limbs = limbs;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPedigree() {
        return pedigree;
    }

    public void setPedigree(Integer pedigree) {
        this.pedigree = pedigree;
    }

    public Integer getBrucellosis() {
        return brucellosis;
    }

    public void setBrucellosis(Integer brucellosis) {
        this.brucellosis = brucellosis;
    }

    public Integer getAntibody() {
        return antibody;
    }

    public void setAntibody(Integer antibody) {
        this.antibody = antibody;
    }

    public Integer getFootMouth() {
        return footMouth;
    }

    public void setFootMouth(Integer footMouth) {
        this.footMouth = footMouth;
    }

    public Integer getAntibiotic() {
        return antibiotic;
    }

    public void setAntibiotic(Integer antibiotic) {
        this.antibiotic = antibiotic;
    }

    public Integer getHormone() {
        return hormone;
    }

    public void setHormone(Integer hormone) {
        this.hormone = hormone;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getEartagAddress() {
        return eartagAddress;
    }

    public void setEartagAddress(String eartagAddress) {
        this.eartagAddress = eartagAddress == null ? null : eartagAddress.trim();
    }
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sheepType='" + sheepType + '\'' +
                ", overall=" + overall +
                ", neck='" + neck + '\'' +
                ", foreBody='" + foreBody + '\'' +
                ", middleBody='" + middleBody + '\'' +
                ", hindquarters=" + hindquarters +
                ", limbs=" + limbs +
                ", sex=" + sex +
                ", total=" + total +
                ", pedigree=" + pedigree +
                ", brucellosis='" + brucellosis + '\'' +
                ", antibody=" + antibody +
                ", footMouth=" + footMouth +
                ", antiobiotic=" + antibiotic +
                ", hormone=" + hormone +
                ", result=" + result +
                ", uploadTime=" + uploadTime +
                ", eartagAddress=" + eartagAddress +
                '}';
    }
}