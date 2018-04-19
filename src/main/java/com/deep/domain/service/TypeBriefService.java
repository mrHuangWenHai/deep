package com.deep.domain.service;

import com.deep.domain.model.TypeBriefModel;
import com.deep.infra.persistence.sql.mapper.TypeBriefMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by zhongrui on 18-4-17.
 */
@Service
public class TypeBriefService {
    @Resource
    private TypeBriefMapper typeBriefMapper;

    public void setTypeBrief(TypeBriefModel typeBriefModel){
        this.typeBriefMapper.setTypeBrief(typeBriefModel);
    }
    public List<String> getAllType(){
        return this.typeBriefMapper.getAllType();
    }

    public TypeBriefModel getTypeBrief(String type){
        return this.typeBriefMapper.getTypeBrief(type);
    }
    public int updateTypeBrief( TypeBriefModel typeBriefModel){
        return this.typeBriefMapper.updateTypeBrief(typeBriefModel);
    }
}
