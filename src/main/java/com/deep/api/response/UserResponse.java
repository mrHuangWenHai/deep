package com.deep.api.response;

public class UserResponse {
    private Long id;

    private String factoryName;

    private String pkUserid;

    private String userRealname;

    private String roleName;

    private Long userRole;

    private String userTelephone;

    private String officialPhone;

    private String qq;

    private String msn;

    private Byte isFactory;

    private Long userFactory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getPkUserid() {
        return pkUserid;
    }

    public void setPkUserid(String pkUserid) {
        this.pkUserid = pkUserid;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getUserRole() {
        return userRole;
    }

    public void setUserRole(Long userRole) {
        this.userRole = userRole;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getOfficialPhone() {
        return officialPhone;
    }

    public void setOfficialPhone(String officialPhone) {
        this.officialPhone = officialPhone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public Byte getIsFactory() {
        return isFactory;
    }

    public void setIsFactory(Byte isFactory) {
        this.isFactory = isFactory;
    }

    public Long getUserFactory() {
        return userFactory;
    }

    public void setUserFactory(Long userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", factoryName='" + factoryName + '\'' +
                ", pkUserid='" + pkUserid + '\'' +
                ", userRealname='" + userRealname + '\'' +
                ", roleName='" + roleName + '\'' +
                ", userRole=" + userRole +
                ", userTelephone='" + userTelephone + '\'' +
                ", officialPhone='" + officialPhone + '\'' +
                ", qq='" + qq + '\'' +
                ", msn='" + msn + '\'' +
                ", isFactory=" + isFactory +
                ", userFactory=" + userFactory +
                '}';
    }
}
