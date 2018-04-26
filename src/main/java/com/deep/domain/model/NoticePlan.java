package com.deep.domain.model;

import java.io.Serializable;
import java.util.Date;

public class NoticePlan implements Serializable {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private String professor;

    private Byte type;

    private String title;

    private String filepath;

    private String suffixname;

    private String content;

//    private String s_gmtCreate1;
//
//    private String s_gmtCreate2;
//
//    private String s_gmtModified1;
//
//    private String s_gmtModified2;

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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor == null ? null : professor.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getSuffixname() {
        return suffixname;
    }

    public void setSuffixname(String suffixname) {
        this.suffixname = suffixname == null ? null : suffixname.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

//    public String getS_gmtCreate1() { return s_gmtCreate1; }
//
//    public void setS_gmtCreate1(String s_gmtCreate1) { this.s_gmtCreate1 = s_gmtCreate1 == null ? null : s_gmtCreate1.trim(); }
//
//    public String getS_gmtCreate2() { return s_gmtCreate2; }
//
//    public void setS_gmtCreate2(String s_gmtCreate2) { this.s_gmtCreate2 = s_gmtCreate2 == null ? null : s_gmtCreate2.trim(); }
//
//    public String getS_gmtModified1() { return s_gmtModified1; }
//
//    public void setS_gmtModified1(String s_gmtModified1) { this.s_gmtModified1 = s_gmtModified1 == null ? null : s_gmtModified1.trim(); }
//
//    public String getS_gmtModified2() { return s_gmtModified2; }
//
//    public void setS_gmtModified2(String s_gmtModified2) { this.s_gmtModified2 = s_gmtModified2 == null ? null : s_gmtModified2.trim(); }
}