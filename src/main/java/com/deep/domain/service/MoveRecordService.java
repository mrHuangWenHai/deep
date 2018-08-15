package com.deep.domain.service;

import com.deep.domain.model.MoveRecord;
import com.deep.infra.persistence.sql.mapper.MoveRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class MoveRecordService {
    @Resource
    private MoveRecordMapper mapper;

    public Integer insert(MoveRecord moveRecord){
        return mapper.insert(moveRecord);
    }
}
