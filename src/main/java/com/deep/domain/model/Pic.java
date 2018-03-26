package com.deep.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Pic implements Serializable {
    private Long id;

    private String address;

    @NotNull(message = "商标耳牌不能为空")
    @Size(min =15, max =15, message = "商标耳牌长度错误")
    private String brand;

    @NotNull(message = "日期不能为空")
    private Date udate;

    @NotNull(message = "专家不能为空")
    private String expert;

    @NotNull(message = "性别不能为空")
    private String sex;

    @NotNull(message = "解决方案不能为空")
    private String solution;

    @NotNull(message = "症状不能为空")
    private String symptom;

    @NotNull(message = "上传人不能为空")
    private String uploader;

    @NotNull(message = "检疫耳牌不能为空")
    @Size(min =15, max =15, message = "检疫耳牌长度错误")
    private String vaccine;

    @NotNull(message = "文件名不能为空")
    private String filename;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert == null ? null : expert.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom == null ? null : symptom.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine == null ? null : vaccine.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}