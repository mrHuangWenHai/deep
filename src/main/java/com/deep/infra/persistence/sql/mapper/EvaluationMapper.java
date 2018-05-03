package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.Evaluation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "evaluationMapper")
public interface EvaluationMapper {
    @Insert("insert into client_evaluation(expert_id, expert_name, evaluation, description, year, season, month) values (#{expert_id}, #{expert_name}, #{evaluation}, #{description}, #{year}, #{season}, #{month})")
    int evaluation_record(@Param("expert_id") Long expert_id, @Param("expert_name") String expert_name, @Param("evaluation") int evaluation, @Param("description") String description, @Param("year") int year, @Param("season") int season, @Param("month") int month);

    @Select("select * from client_evaluation where expert_id = #{expert_id}")
    List<Evaluation> getEvaluation(@Param("expert_id") Long expert_id);

    @Select("select * from client_evaluation")
    List<Evaluation> getAllEvaluation();

    @Select("select evaluation,count(evaluation) from client_evaluation where year = #{year} and expert_id = #{expert_id}")
    List<Evaluation> getYearEvaluation(@Param("expert_id") Long expert_id, @Param("year") int year);

    @Select("select evaluation,count(evaluation) from client_evaluation where season = #{season} and expert_id = #{expert_id}")
    List<Evaluation> getSeasonEvaluation(@Param("expert_id") Long expert_id, @Param("season") int season);

    @Select("select evaluation,count(evaluation) from client_evaluation where month = #{month} and expert_id = #{expert_id}")
    List<Evaluation> getMonthEvaluation(@Param("expert_id") Long expert_id, @Param("season") int month);
}
