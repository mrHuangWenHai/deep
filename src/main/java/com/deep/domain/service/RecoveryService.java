package com.deep.domain.service;


import com.deep.domain.model.RecoveryModel;
import com.deep.infra.persistence.sql.mapper.RecoveryModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecoveryService {

    @Resource
    private RecoveryModelMapper recoveryModelMapper;

    public int insertRecord(RecoveryModel recoveryModel){

        int flag = recoveryModelMapper.insert(recoveryModel);

        return flag;

    }

    public int updateRecord(RecoveryModel recoveryModel){
        int flag = recoveryModelMapper.updateByPrimaryKeySelective(recoveryModel);

        return flag;
    }



}
