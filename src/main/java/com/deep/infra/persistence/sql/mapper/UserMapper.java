package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface UserMapper {
    void setUserModel(@Param("userModel")UserModel userModel);

    UserModel getUserModelByusername(@Param("username") String username);
    List<UserModel> getAllUserModel();

    int deleteUserModelByid(@Param("id")Long id);
}
