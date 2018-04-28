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

    private List<TypeBriefModel>  typeBriefModelList = null;

    public int setTypeBrief(TypeBriefModel typeBriefModel) {

        int success = this.typeBriefMapper.setTypeBrief(typeBriefModel);
        if (success == 1) {
            typeBriefModelList.add(typeBriefModel);
        }
        return success;
    }

    public List<TypeBriefModel> getAllType() {

        if (this.typeBriefModelList == null) {
            this.typeBriefModelList = this.typeBriefMapper.getAllType();
        }
        return this.typeBriefModelList;
    }

    public TypeBriefModel getTypeBrief(String type) {

        if (this.typeBriefModelList != null) {
            for (TypeBriefModel typeBriefModel : this.typeBriefModelList) {
                if (typeBriefModel.getTypename().equals(type)) {
                    return typeBriefModel;
                }
            }
            return null;
        } else {
            return this.typeBriefMapper.getTypeBrief(type);
        }
    }

    //下面接口还要改
    public int updateTypeBrief( TypeBriefModel typeBriefModel) {
        this.typeBriefModelList = null;

        return this.typeBriefMapper.updateTypeBrief(typeBriefModel);
    }
}
