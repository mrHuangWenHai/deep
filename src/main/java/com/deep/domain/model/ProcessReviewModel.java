package com.deep.domain.model;

import java.io.Serializable;
import java.util.Date;

public class ProcessReviewModel implements Serializable {
    private Integer id;

    private String companyName;

    private Integer companyId;

    private Integer healthRecord;

    private Integer disinfectionRecord;

    private Integer immunizationRecord;

    private Integer dewormRecord;

    private Integer nutritionRecord;

    private Integer breedingRecord;

    private Integer preventionRecord;

    private Integer comprehensiveResult;

    private String reviewer;

    private Date uploadTime;

    private String eartagAddress;


    private Integer reviewId;

    private Integer pageNumb = 0;

    private Integer limit = 10;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(Integer healthRecord) {
        this.healthRecord = healthRecord;
    }

    public Integer getDisinfectionRecord() {
        return disinfectionRecord;
    }

    public void setDisinfectionRecord(Integer disinfectionRecord) {
        this.disinfectionRecord = disinfectionRecord;
    }

    public Integer getImmunizationRecord() {
        return immunizationRecord;
    }

    public void setImmunizationRecord(Integer immunizationRecord) {
        this.immunizationRecord = immunizationRecord;
    }

    public Integer getDewormRecord() {
        return dewormRecord;
    }

    public void setDewormRecord(Integer dewormRecord) {
        this.dewormRecord = dewormRecord;
    }

    public Integer getNutritionRecord() {
        return nutritionRecord;
    }

    public void setNutritionRecord(Integer nutritionRecord) {
        this.nutritionRecord = nutritionRecord;
    }

    public Integer getBreedingRecord() {
        return breedingRecord;
    }

    public void setBreedingRecord(Integer breedingRecord) {
        this.breedingRecord = breedingRecord;
    }

    public Integer getPreventionRecord() {
        return preventionRecord;
    }

    public void setPreventionRecord(Integer preventionRecord) {
        this.preventionRecord = preventionRecord;
    }

    public Integer getComprehensiveResult() {
        return comprehensiveResult;
    }

    public void setComprehensiveResult(Integer comprehensiveResult) {
        this.comprehensiveResult = comprehensiveResult;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
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
    public String toString(){
        return "ProcessReview{" +
                "id = " + id +
                ", companyName='" + companyName + '\'' +
                ", companyId='" + companyId + '\'' +
                ", healthRecord='" + healthRecord + '\'' +
                ", disinfectionRecord='" + disinfectionRecord + '\'' +
                ", immunizationRecord='" + immunizationRecord + '\'' +
                ", dewormRecord='" + dewormRecord + '\'' +
                ", nutritionRecord='" + nutritionRecord + '\'' +
                ", breedingRecord='" + breedingRecord + '\'' +
                ", preventionRecord='" + preventionRecord + '\'' +
                ", comprehensiveResult='" + comprehensiveResult + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", eartagAddress=" + eartagAddress +
                '}';

    }
}
