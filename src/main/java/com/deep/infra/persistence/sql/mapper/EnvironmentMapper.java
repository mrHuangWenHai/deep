package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.EnvironmentModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * create by zhongrui on 18-4-17.
 */
@Mapper
public interface EnvironmentMapper {
    void setEnvironmentModel(@Param("environmentModel") EnvironmentModel environmentModel);
}
