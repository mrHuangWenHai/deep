package com.deep.infra.persistence.sql.mapper;

import com.deep.api.response.Professor;
import com.deep.api.response.UserResponse;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.UserService;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 列出用户列表
     * @return 用户列表
     */
    @Select("select * from user_manage where user_role >= #{roleID}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "msn", column = "msn"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "familyPhone", column = "family_phone"),
            @Result(property = "factoryName", column = "factory_name")
    })
    List<UserModel> queryAllUser(long roleID);

    @Select("select pk_userid from user_manage")
    @Results({
            @Result(property = "pkUserid", column = "pk_userid"),
    })
    List<String> queryAllUsernameNoCondition();

    @Select("select * from user_manage where id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "msn", column = "msn"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "familyPhone", column = "family_phone"),
            @Result(property = "factoryName", column = "factory_name")
    })
    UserModel queryUserById(Long userId);


    /**
     * 根据ID获取单个用户
     * @param userId 用户ID
     * @return 单个用户的信息
     */
    @Select("select user_manage.factory_name, user_manage.pk_userid, user_manage.user_realname, user_manage.user_role, " +
            "user_manage.user_telephone, user_manage.official_phone, user_manage.qq, user_manage.msn, user_manage.is_factory, " +
            "user_manage.user_factory, role_user.type_name from user_manage, role_user where user_manage.id = #{id} and user_manage.user_role = role_user.id")
    @Results({
            @Result(property = "factoryName", column = "factory_name"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "roleName", column = "type_name"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "msn", column = "msn"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "userFactory", column = "user_factory"),
    })
    UserResponse queryUserResponseById(Long userId);


    /**
     * 查询某一个用户的角色
     * @param agentID 羊场号或者代理号
     * @param roleID 角色ID
     * @return 用户角色信息
     */
    @Select("select id, pk_userid, user_telephone, user_role, user_email, qq, official_phone from user_manage where is_factory = 1 and user_factory = #{agentID} and user_role = #{roleID}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "officialPhone", column = "official_phone")
    })
    UserService.UserRole queryByAgentAndRole(short agentID, short roleID);


    /**
     * 查询上级代理专家的手机号
     * @param agentID 代理号
     * @return 某一级别代理的手机号码
     */
    @Select("select user_telephone from user_manage where is_factory = 1 and user_factory = #{agentID} and user_role in (4, 8, 12, 16)")
    @Results({
            @Result(property = "userTelephone", column = "user_telephone"),
    })
    List<String> queryTelephoneByAgentAndRole(short agentID);

    /**
     * 获取羊场监督者的所有信息
     * @param agentID 羊场ID或者代理ID
     * @return 相关信息
     */
    @Select("select user_telephone from user_manage where is_factory = 0 and user_factory = #{agentID} and user_role = 20")
    @Results({
            @Result(property = "userTelephone", column = "user_telephone"),
    })
    List<String> querySuperiorTelephoneByAgentAndRole(short agentID);


    /**
     * 根据用户名获取单个用户
     * @param pkUserid 用户名
     * @return 用户信息
     */
    @Select("select * from user_manage where pk_userid = #{pkUserid}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "msn", column = "msn"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "familyPhone", column = "family_phone"),
            @Result(property = "factoryName", column = "factory_name")
    })
    UserModel queryUserByPkuserID(String pkUserid);

    /**
     * 根据用户名进行模糊查询获取单个用户的信息
     * @param userRealname 用户真实姓名
     * @return 相关信息
     */
    @Select("select * from user_manage where user_realname = #{userRealname}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "msn", column = "msn"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "familyPhone", column = "family_phone"),
            @Result(property = "factoryName", column = "factory_name")
    })
    UserModel queryUserByRealnameLike(String userRealname);

    /**
     * 插入一个用户
     * @param userModel 用户对象模型
     * @return 是否插入成功的标志
     */
    @Insert("insert into user_manage(" +
            "gmt_create," +
            "gmt_modified," +
            "pk_userid," +
            "user_pwd," +
            "user_num," +
            "user_pic," +
            "user_realname," +
            "user_location," +
            "user_telephone," +
            "user_remark," +
            "user_factory," +
            "user_role," +
            "user_permit," +
            "is_extended," +
            "is_factory," +
            "question_1," +
            "question_2," +
            "question_3, " +
            "answer_1," +
            "answer_2," +
            "answer_3," +
            "user_email," +
            "msn," +
            "qq," +
            "official_phone," +
            "family_phone," +
            "factory_name)" +
            "values(" +
            "#{gmtCreate}," +
            "#{gmtModified}," +
            "#{pkUserid}," +
            "#{userPwd}," +
            "#{userNum}," +
            "#{userPic}," +
            "#{userRealname}," +
            "#{userLocation}," +
            "#{userTelephone}," +
            "#{userRemark}," +
            "#{userFactory}," +
            "#{userRole}," +
            "#{userPermit}," +
            "#{isExtended}," +
            "#{isFactory}," +
            "#{question_1}," +
            "#{question_2}," +
            "#{question_3}," +
            "#{answer_1}," +
            "#{answer_2}," +
            "#{answer_3}," +
            "#{userEmail}," +
            "#{msn}," +
            "#{qq}," +
            "#{officialPhone}," +
            "#{familyPhone}," +
            "#{factoryName}" +
            ")")
    Long insertUser(UserModel userModel);

    /**
     * 修改用户信息
     * @param userModel 用户对象模型
     * @return 是否修改成功的标志
     */
    @Update("update user_manage set " +
            "gmt_create = #{gmtCreate}," +
            "gmt_modified = #{gmtModified}," +
            "pk_userid = #{pkUserid}," +
            "user_pwd = #{userPwd}," +
            "user_num = #{userNum}," +
            "user_pic = #{userPic}," +
            "user_realname = #{userRealname}," +
            "user_location = #{userLocation}," +
            "user_telephone = #{userTelephone}," +
            "user_remark = #{userRemark}," +
            "user_factory = #{userFactory}," +
            "user_role = #{userRole}," +
            "user_permit = #{userPermit}," +
            "is_extended = #{isExtended}," +
            "is_factory = #{isFactory}," +
            "question_1 = #{question_1}, " +
            "question_2 = #{question_2}, " +
            "question_3 = #{question_3}, " +
            "answer_1 = #{answer_1}, " +
            "answer_2 = #{answer_2}, " +
            "answer_3 = #{answer_3}," +
            "user_email = #{userEmail}," +
            "msn = #{msn}," +
            "qq = #{qq}," +
            "official_phone = #{officialPhone}," +
            "family_phone = #{familyPhone}," +
            "factory_name = #{factoryName}" +
            " where id=#{id}")
    Long updateUser(UserModel userModel);

    /**
     * 删除用户信息
     * @param userID 用户的ID
     * @return 是否成功的标志
     */
    @Delete("delete from user_manage where id = #{id}")
    Long deleteUser(Long userID);

    /**
     * 根据羊场名称或者代理名称删除用户
     */
    @Delete("delete from user_manage where user_factory = #{factoryNumber} and is_factory = #{isFactory}")
    void deleteUserByFactoryNumber(@Param("factoryNumber") Long factoryNumber, @Param("isFactory") Byte isFactory);

    /**
     * 修改用户密码
     * @param userPwd 新密码
     * @param username 用户名
     * @return 是否修改
     */
    @Update("update user_manage set user_pwd = #{userPwd} where username = #{username}")
    Long updateUserPwd(String userPwd, String username);

    /**
     * 获取某一类专家
     * @param roleID 角色的ID
     * @return 专家信息
     */
    @Select("select id, pk_userid, user_telephone, user_role, user_email, official_phone, qq from user_manage where user_role = #{roleID}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "qq", column = "qq")
    })
    List<Professor> getOneRoles(long roleID);

    @Select("select id, pk_userid, user_telephone, user_role, user_email, official_phone, qq, user_realname from user_manage where user_role in (4, 8, 12, 16) and user_factory = #{agentID} and is_factory = 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "userRealname", column = "user_realname")
    })
    List<Professor> getProfessor(Long agentID);

    /**
     * 查找某一个羊场或者一个代理的所有用户
     * @return 对象模型列表
     */
    @Select("select * from user_manage where user_factory = #{factory} and is_factory = #{which} limit #{page}, #{size}")
    @Results({
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "msn", column = "msn"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "familyPhone", column = "family_phone"),
            @Result(property = "factoryName", column = "factory_name")
    })
    List<UserModel> getAllUsersOfOneFactoryOrOneAgent(@Param("factory") Long factory, @Param("which") Byte which, @Param("page") Long page, @Param("size") Byte size);


    /**
     * 根据ID获取某个羊场下的用户
     * @return 单个用户的信息
     */
    @Select("select user_manage.id, user_manage.factory_name, user_manage.pk_userid, user_manage.user_realname, user_manage.user_role, " +
            "user_manage.user_telephone, user_manage.official_phone, user_manage.qq, user_manage.msn, user_manage.is_factory, " +
            "user_manage.user_factory, role_user.type_name from user_manage, role_user where user_manage.user_role = role_user.id" +
            " and user_manage.user_factory = #{factory} and user_manage.is_factory = #{which} limit #{page}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "factoryName", column = "factory_name"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "roleName", column = "type_name"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "officialPhone", column = "official_phone"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "msn", column = "msn"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "userFactory", column = "user_factory"),
    })
    List<UserResponse> queryUserResponsesById(@Param("factory") Long factory, @Param("which") Byte which, @Param("page") Long page, @Param("size") Byte size);

    /**
     * 查找某一个羊场或者一个代理的所有用户
     * @return 返回总的记录条数
     */
    @Select("select count(*) from user_manage where user_factory = #{factory} and is_factory = #{which}")
    Long getCountsOfOneFactoryOrOneAgent(@Param("factory") Long factory, @Param("which") Byte which);
}
