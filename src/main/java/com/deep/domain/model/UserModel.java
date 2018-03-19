package com.deep.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class UserModel {
    private long userId;
    private String username;
    private String password;
    private String name;
    private String telephone;
    private String question_1;
    private String answer_1;
    private String question_2;
    private String answer_2;
    private String question_3;
    private String answer_3;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public UserModel(long userId, String username, String password, String name, String telephone, String question_1, String answer_1, String question_2, String answer_2, String question_3, String answer_3, Timestamp gmtCreate, Timestamp gmtModified) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.question_1 = question_1;
        this.answer_1 = answer_1;
        this.question_2 = question_2;
        this.answer_2 = answer_2;
        this.question_3 = question_3;
        this.answer_3 = answer_3;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
    public UserModel( String username, String password, String name, String telephone, String question_1, String answer_1, String question_2, String answer_2, String question_3, String answer_3, Timestamp gmtCreate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.question_1 = question_1;
        this.answer_1 = answer_1;
        this.question_2 = question_2;
        this.answer_2 = answer_2;
        this.question_3 = question_3;
        this.answer_3 = answer_3;
        this.gmtCreate = gmtCreate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQuestion_1() {
        return question_1;
    }

    public void setQuestion_1(String question_1) {
        this.question_1 = question_1;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(String answer_1) {
        this.answer_1 = answer_1;
    }

    public String getQuestion_2() {
        return question_2;
    }

    public void setQuestion_2(String question_2) {
        this.question_2 = question_2;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(String answer_2) {
        this.answer_2 = answer_2;
    }

    public String getQuestion_3() {
        return question_3;
    }

    public void setQuestion_3(String question_3) {
        this.question_3 = question_3;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(String answer_3) {
        this.answer_3 = answer_3;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    /*private BigInteger id;     //员工编号
    private Timestamp gmtCreate;   //创建时间
    private Timestamp gmtModified;   // 修改时间
    private String userId;   //用户名 对应pk_userid
    private String userPwd;    //密码 对应user_pwd
    private String userNum;     //对应user_num

    private String userRealName;        //真实名字 对应user_realname
    private String userLocation;        //地域 对应user_location
    private String userTelephone;    //电话  对应user_telephone
    private String userRemark;        //备注 对应user_remark
    private BigInteger userFactory;      //员工公司 对应user_factory
    private BigInteger userRole;        //角色 对应user_role
    private BigInteger userPermit;     //权限 对应user_permit
    private String isExtended;     //是否有扩展权限  对应is_extended
    private String isFactory;     //代理/羊厂等    对应is_factory
    private String question_1;    //找回密码问题1
    private String answer_1;     //找回密码答案1
    private String question_2;
    private String answer_2;
    private String question_3;
    private String answer_3;


    public UserModel() {
    }

    //游客注册需要填写的内容
    public UserModel(BigInteger id, Timestamp gmtCreate, String userId, String userPwd, String question_1, String answer_1, String question_2, String answer_2, String question_3, String answer_3) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.userId = userId;
        this.userPwd = userPwd;
        this.question_1 = question_1;
        this.answer_1 = answer_1;
        this.question_2 = question_2;
        this.answer_2 = answer_2;
        this.question_3 = question_3;
        this.answer_3 = answer_3;
    }

    public UserModel(BigInteger id, Timestamp gmtCreate, Timestamp gmtModified, String userId, String userPwd, String userNum, String userRealName, String userLocation, String userTelephone, String userRemark, BigInteger userFactory, BigInteger userRole, BigInteger userPermit, String isExtended, String isFactory, String question_1, String answer_1, String question_2, String answer_2, String question_3, String answer_3) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userNum = userNum;
        this.userRealName = userRealName;
        this.userLocation = userLocation;
        this.userTelephone = userTelephone;
        this.userRemark = userRemark;
        this.userFactory = userFactory;
        this.userRole = userRole;
        this.userPermit = userPermit;
        this.isExtended = isExtended;
        this.isFactory = isFactory;
        this.question_1 = question_1;
        this.answer_1 = answer_1;
        this.question_2 = question_2;
        this.answer_2 = answer_2;
        this.question_3 = question_3;
        this.answer_3 = answer_3;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public BigInteger getUserFactory() {
        return userFactory;
    }

    public void setUserFactory(BigInteger userFactory) {
        this.userFactory = userFactory;
    }

    public BigInteger getUserRole() {
        return userRole;
    }

    public void setUserRole(BigInteger userRole) {
        this.userRole = userRole;
    }

    public BigInteger getUserPermit() {
        return userPermit;
    }

    public void setUserPermit(BigInteger userPermit) {
        this.userPermit = userPermit;
    }

    public String getIsExtended() {
        return isExtended;
    }

    public void setIsExtended(String isExtended) {
        this.isExtended = isExtended;
    }

    public String getIsFactory() {
        return isFactory;
    }

    public void setIsFactory(String isFactory) {
        this.isFactory = isFactory;
    }

    public String getQuestion_1() {
        return question_1;
    }

    public void setQuestion_1(String question_1) {
        this.question_1 = question_1;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(String answer_1) {
        this.answer_1 = answer_1;
    }

    public String getQuestion_2() {
        return question_2;
    }

    public void setQuestion_2(String question_2) {
        this.question_2 = question_2;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(String answer_2) {
        this.answer_2 = answer_2;
    }

    public String getQuestion_3() {
        return question_3;
    }

    public void setQuestion_3(String question_3) {
        this.question_3 = question_3;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(String answer_3) {
        this.answer_3 = answer_3;
    }*/
}
