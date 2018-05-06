package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.EnvironmentTraceModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

/**
 * create by zhongrui on 18-4-17.
 */
@Mapper
public interface EnvironmentTraceMapper {

    EnvironmentTraceModel getEnvironmentTraceModelLatestByFactoryNum(@Param("factoryNum")BigInteger factoryNum);

    List<EnvironmentTraceModel> getEnvironmentTraceModelByFactoryNum(@Param("factoryNum")BigInteger factoryNum);

    int deleteEnvironmentTraceModel();
}
