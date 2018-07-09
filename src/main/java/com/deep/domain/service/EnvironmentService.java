package com.deep.domain.service;

import com.deep.domain.model.EnvironmentModel;
import com.deep.infra.persistence.sql.mapper.EnvironmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by zhongrui on 18-4-18.
 */
@Service
public class EnvironmentService {

    @Resource
    private EnvironmentMapper environmentMapper;

    public void setEnvironmentModel(EnvironmentModel environmentModel){
        this.environmentMapper.setEnvironmentModel(environmentModel);
    }

}
