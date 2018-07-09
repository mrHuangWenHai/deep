package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.Expression;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "expressionMapper")
public interface ExpressionMapper {
    @Insert("insert into useful_expressions(expert_id, expression) values (#{expert_id}, #{expression})")
    int expression_record(@Param("expert_id") Long expert_id, @Param("expression") String expression);

    @Select("select * from useful_expressions where expert_id = #{expert_id}")
    List<Expression> getExpression(@Param("expert_id") Long expert_id);
}
