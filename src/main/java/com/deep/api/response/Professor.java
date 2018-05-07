package com.deep.api.response;

/**
 * 获取上级专家所带的类
 */
public class Professor {
    private long id;

    private String pkUserid;

    private String userTelephone;

    private long userRole;

    private String userEmail;

    private String qq;

    private String officialPhone;

    private String userRealname;

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPkUserid() {
        return pkUserid;
    }

    public void setPkUserid(String pkUserid) {
        this.pkUserid = pkUserid;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public long getUserRole() {
        return userRole;
    }

    public void setUserRole(long userRole) {
        this.userRole = userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getOfficialPhone() {
        return officialPhone;
    }

    public void setOfficialPhone(String officialPhone) {
        this.officialPhone = officialPhone;
    }
}
