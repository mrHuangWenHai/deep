package com.deep.domain.model;

import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

public class Pic implements Serializable {
    private Long id;

    private String address;

    @NotBlank(message = "商标耳牌不能为空")
    @Size(min =15, max =15, message = "商标耳牌长度错误")
    private String brand;


    private Date udate;

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    private Date sdate;

    private Date edate;

    @NotBlank(message = "专家不能为空")
    private String expert;

//    @NotBlank(message = "性别不能为空")
    private String sex;

    @NotBlank(message = "解决方案不能为空")
    private String solution;

    @NotBlank(message = "症状不能为空")
    private String symptom;

    @NotBlank(message = "上传人不能为空")
    private String uploader;

    @NotBlank(message = "检疫耳牌不能为空")
    @Size(min =8, max =8, message = "检疫耳牌长度错误")
    private String vaccine;

    private String filename;

    private Integer returnId;

    private Integer pageNumb = 1;

    private Integer limit = 10;

    private Integer size;

    private Integer filetype;

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }



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

    public static boolean isDate(String date) {
        String regex = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
        return Pattern.matches(regex, date);
    }

    @Override
    public String toString() {
        return "Pic{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", brand='" + brand + '\'' +
                ", udate=" + udate +
                ", sdate=" + sdate +
                ", edate=" + edate +
                ", expert='" + expert + '\'' +
                ", sex='" + sex + '\'' +
                ", solution='" + solution + '\'' +
                ", symptom='" + symptom + '\'' +
                ", uploader='" + uploader + '\'' +
                ", vaccine='" + vaccine + '\'' +
                ", filename='" + filename + '\'' +
                ", returnId=" + returnId +
                ", pageNumb=" + pageNumb +
                ", limit=" + limit +
                ", size=" + size +
                ", filetype=" + filetype +
                '}';
    }
}