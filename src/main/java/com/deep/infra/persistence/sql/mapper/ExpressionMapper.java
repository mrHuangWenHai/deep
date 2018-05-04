package com.deep.infra.persistence.sql.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "expressionMapper")
public interface ExpressionMapper {
    @Insert("insert into useful_expressions(expert_id, expression) values (#{expert_id}, #{expression})")
    int expression_record(@Param("expert_id") Long expert_id, @Param("expression") String expression);
}
