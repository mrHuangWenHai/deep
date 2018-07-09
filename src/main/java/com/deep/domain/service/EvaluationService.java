package com.deep.domain.service;

import com.deep.domain.model.Evaluation;
import com.deep.infra.persistence.sql.mapper.EvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    EvaluationMapper mapper;

    public int evaluation_record(Long expert_id, String expert_name, int evaluation, String description, int year, int season, int month) {
        return mapper.evaluation_record(expert_id, expert_name, evaluation, description, year, season, month);
    }

    public List<Evaluation> getEvaluation(Long expert_id){
        return mapper.getEvaluation(expert_id);
    }

    public List<Evaluation> getAllEvaluation(){
        return mapper.getAllEvaluation();
    }
}
