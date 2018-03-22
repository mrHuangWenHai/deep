package com.deep.domain.service;

import com.deep.domain.model.UserModel;
import com.deep.infra.persistence.sql.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    public void setUserModel(UserModel userModel){
        this.userMapper.setUserModel(userModel);
    }

    public UserModel getUserModelByusername(String username){
        UserModel userModel = this.userMapper.getUserModelByusername(username);
        return userModel;
    }
    public List<UserModel> getAllUserModel(){
        List<UserModel> userModels = this.userMapper.getAllUserModel();
        return userModels;
    }
    public List<UserModel> getUserTelephoneByfactoryNum(BigInteger factoryNum){
        List<UserModel> userModels = this.userMapper.getUserTelephoneByfactoryNum(factoryNum);
        return userModels;
    }

    public int deleteUserModelByid(Long id){
        int row = this.userMapper.deleteUserModelByid(id);
        return row;
    }

}
