package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.EnvironmentTraceModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-17.
 */
@Mapper
public interface EnvironmentTraceMapper {
    EnvironmentTraceModel getEnvironmentTraceModelLatestByFactoryNum(@Param("factoryNum")BigInteger fatoryNum);
}
