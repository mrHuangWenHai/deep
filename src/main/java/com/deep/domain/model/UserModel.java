package com.deep.domain.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.sql.Timestamp;
@Component
public class UserModel {
        private long id;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        @NotBlank(message = "用户名不能为空!")
        @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "用户名只能由a-z以及数字组成")
        @Size(min = 4, max = 20, message = "用户名最小长度为4, 最大长度为20")
        private String pkUserid;

        private String userPwd;

//        @NotBlank(message = "用户编号不能为含有空格的串")
        private String userNum;

        private String userPic;

//        @NotBlank(message = "不能带有空格")
        private String userRealname;

//        @NotBlank(message = "不能带有空格")
        private String userLocation;

        @Pattern(regexp = "(^$|(^[1][3-9][0-9]{9}$)|(^[1-9]{1}[0-9]{5,8}))", message = "号码格式错误")
        private String userTelephone;

        private String userRemark;

        private Long userFactory;

        private Long userRole;

        private String userPermit;

//        @Max(1)
//        @Min(0)
        private byte isExtended;

//        @Min(0)
//        @Max(1)
        private byte isFactory;

   //     @NotBlank(message = "不能为空")
        private String factoryName;

//        @NotNull(message = "必须设置密保问题1")
        private String question_1;    //找回密码问题1
//        @NotNull(message = "必须设置密保问题1的答案")
        private String answer_1;     //找回密码答案1

//        @NotNull(message = "必须设置密保问题2")
        private String question_2;
//        @NotNull(message = "必须设置密保问题2的答案")
        private String answer_2;

 //       @NotNull(message = "必须设置密保问题3")
        private String question_3;

//        @NotNull(message = "必须设置密保问题3的答案")
        private String answer_3;

        @Email(message = "邮箱格式不正确")
        private String userEmail;

        private String msn;

        private String qq;

        @Pattern(regexp = "^$|^(\\d+|\\-){7,}$")
        private String officialPhone;

        @Pattern(regexp = "^$|^(\\d+|\\-){7,}$")
        private String familyPhone;


        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public void setUserFactory(Long userFactory) {
            this.userFactory = userFactory;
        }

        public void setUserRole(Long userRole) {
            this.userRole = userRole;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
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

        public String getFamilyPhone() {
            return familyPhone;
        }

        public void setFamilyPhone(String familyPhone) {
            this.familyPhone = familyPhone;
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

        public byte getIsFactory() {
            return isFactory;
        }

        public void setIsFactory(byte isFactory) {
            this.isFactory = isFactory;
        }

        public String getFactoryName() {
            return factoryName;
        }

        public void setFactoryName(String factoryName) {
            this.factoryName = factoryName;
        }

        @JsonView(UserSimpleView.class)
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @JsonView(UserSimpleView.class)
        public Timestamp getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        @JsonView(UserSimpleView.class)
        public Timestamp getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
        }

        @JsonView(UserSimpleView.class)
        public String getPkUserid() {
            return pkUserid;
        }

        public void setPkUserid(String pkUserid) {
            this.pkUserid = pkUserid;
        }

        @JsonView(UserDetailView.class)
        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        @JsonView(UserSimpleView.class)
        public String getUserNum() {
            return userNum;
        }

        public void setUserNum(String userNum) {
            this.userNum = userNum;
        }

        @JsonView(UserSimpleView.class)
        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        @JsonView(UserSimpleView.class)
        public String getUserRealname() {
            return userRealname;
        }

        public void setUserRealname(String userRealname) {
            this.userRealname = userRealname;
        }

        @JsonView(UserDetailView.class)
        public String getUserLocation() {
            return userLocation;
        }

        public void setUserLocation(String userLocation) {
            this.userLocation = userLocation;
        }

        @JsonView(UserDetailView.class)
        public String getUserTelephone() {
            return userTelephone;
        }

        public void setUserTelephone(String userTelephone) {
            this.userTelephone = userTelephone;
        }

        @JsonView(UserDetailView.class)
        public String getUserRemark() {
            return userRemark;
        }

        public void setUserRemark(String userRemark) {
            this.userRemark = userRemark;
        }

        @JsonView(UserSimpleView.class)
        public long getUserFactory() {
            return userFactory;
        }

        public void setUserFactory(long userFactory) {
            this.userFactory = userFactory;
        }

        @JsonView(UserSimpleView.class)
        public long getUserRole() {
            return userRole;
        }

        public void setUserRole(long userRole) {
            this.userRole = userRole;
        }

        @JsonView(UserSimpleView.class)
        public String getUserPermit() {
            return userPermit;
        }

        public void setUserPermit(String userPermit) {
            this.userPermit = userPermit;
        }

        @JsonView(UserSimpleView.class)
        public byte getIsExtended() {
            return isExtended;
        }

        public void setIsExtended(byte isExtended) {
            this.isExtended = isExtended;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserModel that = (UserModel) o;

            if (id != that.id) return false;
            if (userRole != that.userRole) return false;
            if (userPermit != that.userPermit) return false;
            if (isExtended != that.isExtended) return false;
            if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
            if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
            if (pkUserid != null ? !pkUserid.equals(that.pkUserid) : that.pkUserid != null) return false;
            if (userPwd != null ? !userPwd.equals(that.userPwd) : that.userPwd != null) return false;
            if (userNum != null ? !userNum.equals(that.userNum) : that.userNum != null) return false;
            if (userPic != null ? !userPic.equals(that.userPic) : that.userPic != null) return false;
            if (userRealname != null ? !userRealname.equals(that.userRealname) : that.userRealname != null) return false;
            if (userLocation != null ? !userLocation.equals(that.userLocation) : that.userLocation != null) return false;
            if (userTelephone != null ? !userTelephone.equals(that.userTelephone) : that.userTelephone != null)
                return false;
            if (userRemark != null ? !userRemark.equals(that.userRemark) : that.userRemark != null) return false;
            if (userFactory != that.userFactory) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (id ^ (id >>> 32));
            result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
            result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
            result = 31 * result + (pkUserid != null ? pkUserid.hashCode() : 0);
            result = 31 * result + (userPwd != null ? userPwd.hashCode() : 0);
            result = 31 * result + (userNum != null ? userNum.hashCode() : 0);
            result = 31 * result + (userPic != null ? userPic.hashCode() : 0);
            result = 31 * result + (userRealname != null ? userRealname.hashCode() : 0);
            result = 31 * result + (userLocation != null ? userLocation.hashCode() : 0);
            result = 31 * result + (userTelephone != null ? userTelephone.hashCode() : 0);
            result = 31 * result + (userRemark != null ? userRemark.hashCode() : 0);
            result = 31 * result + (int)(userFactory ^ (userFactory >>> 32));
            result = 31 * result + (int) (userRole ^ (userRole >>> 32));
            result = 31 * result + (userPermit != null ? userPermit.hashCode() : 0);
            result = 31 * result + (int) isExtended;
            return result;
        }

        public interface UserSimpleView {}

        public interface UserDetailView extends UserSimpleView {}


  @Override
  public String toString() {
    return "UserModel{" +
        "id=" + id +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", pkUserid='" + pkUserid + '\'' +
        ", userPwd='" + userPwd + '\'' +
        ", userNum='" + userNum + '\'' +
        ", userPic='" + userPic + '\'' +
        ", userRealname='" + userRealname + '\'' +
        ", userLocation='" + userLocation + '\'' +
        ", userTelephone='" + userTelephone + '\'' +
        ", userRemark='" + userRemark + '\'' +
        ", userFactory=" + userFactory +
        ", userRole=" + userRole +
        ", userPermit='" + userPermit + '\'' +
        ", isExtended=" + isExtended +
        ", isFactory=" + isFactory +
        ", factoryName='" + factoryName + '\'' +
        ", question_1='" + question_1 + '\'' +
        ", answer_1='" + answer_1 + '\'' +
        ", question_2='" + question_2 + '\'' +
        ", answer_2='" + answer_2 + '\'' +
        ", question_3='" + question_3 + '\'' +
        ", answer_3='" + answer_3 + '\'' +
        ", userEmail='" + userEmail + '\'' +
        ", msn='" + msn + '\'' +
        ", qq='" + qq + '\'' +
        ", officialPhone='" + officialPhone + '\'' +
        ", familyPhone='" + familyPhone + '\'' +
        '}';
  }
}
