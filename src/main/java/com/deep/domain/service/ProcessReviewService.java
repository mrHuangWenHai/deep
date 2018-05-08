package com.deep.domain.service;

import com.deep.domain.model.ProcessReviewModel;
import com.deep.infra.persistence.sql.mapper.ProcessReviewModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProcessReviewService {

    @Resource
    private ProcessReviewModelMapper processReviewModelMapper;

    public int insertReview(ProcessReviewModel processReviewModel) {
        int flag = processReviewModelMapper.insertSelective(processReviewModel);

        return flag;
    }

    public int updateReview(ProcessReviewModel processReviewModel){
        int flag = processReviewModelMapper.updateByPrimaryKeySelective(processReviewModel);

        return flag;
    }

}
