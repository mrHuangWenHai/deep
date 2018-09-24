package com.deep.domain.service;

import com.deep.domain.model.EnvironmentTraceModel;
import com.deep.domain.model.EnvironmentTraceReturnModel;
import com.deep.infra.persistence.sql.mapper.EnvironmentTraceMapper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

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

    public List<EnvironmentTraceModel> getEnvironmentTraceModelByFactoryNum(BigInteger factoryNum, Long start, Long size){
        System.out.println("start = " + start + " size = " + size);
        return this.environmentTraceMapper.getEnvironmentTraceModelByFactoryNum(factoryNum, start, size);
    }

    public Long getCount(Long factoryNum) {
        return this.environmentTraceMapper.getCount(factoryNum);
    }

    public List<EnvironmentTraceReturnModel> getReturn(List<EnvironmentTraceModel> list) {
        if (list == null) {
            return null;
        }
        List<EnvironmentTraceReturnModel> models = new ArrayList<>();
        for (EnvironmentTraceModel environmentTraceModel : list) {
            EnvironmentTraceReturnModel model = new EnvironmentTraceReturnModel(environmentTraceModel);
            models.add(model);
        }
        return models;
    }
}
