package com.deep.domain.service;

import com.deep.domain.model.EnvironmentTraceModel;
import com.deep.infra.persistence.sql.mapper.EnvironmentTraceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-18.
 */
@Service
public class EnvironmentTraceService {

    @Resource
    private EnvironmentTraceMapper environmentTraceMapper;

    public EnvironmentTraceModel getEnvironmentTraceModelLatestByFactoryNum(BigInteger factoryNum){
        return this.environmentTraceMapper.getEnvironmentTraceModelLatestByFactoryNum(factoryNum);
    }

}
