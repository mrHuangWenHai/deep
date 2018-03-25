package com.deep.domain.service;

import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.infra.persistence.sql.mapper.GenealogicalFilesMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by zhongrui on 2018/2/1
 */
@Service
public class  GenealogicalFilesService {

    @Resource
    private GenealogicalFilesMapper genealogicalFilesMapper;


    public void setGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {
        this.genealogicalFilesMapper.setGenealogicalFilesModel(genealogicalFilesModel);
    }

    public List<GenealogicalFilesModel> getGenealogicalFilesModel(String selfEartag,
                                                                  String immuneEartag,
                                                                  String tradeMarkEartag,
                                                                  String breedingSheepBase,
                                                                  String birthTimeStart,
                                                                  String birthTimeEnd,
                                                                  String birthWeightStart,
                                                                  String birthWeightEnd,
                                                                  String color,
                                                                  String sex,
                                                                  String eartagOfFather,
                                                                  String eartagOfMother,
                                                                  String eartagOfFathersFather,
                                                                  String eartagOfFathersMother,
                                                                  String eartagOfMothersFather,
                                                                  String eartagOfMothersMother,
                                                                  RowBounds bounds) {
        List<GenealogicalFilesModel> model = this.genealogicalFilesMapper.getGenealogicalFilesModel(selfEartag,immuneEartag,tradeMarkEartag,
                                                                                        breedingSheepBase,birthTimeStart,birthTimeEnd,
                                                                                        birthWeightStart,birthWeightEnd,color,
                                                                                        sex,eartagOfFather,eartagOfMother,
                                                                                        eartagOfFathersFather,eartagOfFathersMother,
                                                                                        eartagOfMothersFather, eartagOfMothersMother,bounds);
        return model;
    }



    public GenealogicalFilesModel getGenealogicalFilesModelByid(int id){
        GenealogicalFilesModel model = this.genealogicalFilesMapper.getGenealogicalFilesModelByid(id);
        return model;
    }

    public GenealogicalFilesModel getGenealogicalFilesModelByimmuneEartag(String immuneEartag) {
        GenealogicalFilesModel model = this.genealogicalFilesMapper.getGenealogicalFilesModelByimmuneEartag(immuneEartag);
        return model;
    }
    public GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(String tradeMarkEartag){
        GenealogicalFilesModel model = this.genealogicalFilesMapper.getGenealogicalFilesModelBytradeMarkEartag(tradeMarkEartag);
        return model;
    }


    public int deleteGenealogicalFilesModel(String selfEartag){
        int row=this.genealogicalFilesMapper.deleteGenealogicalFilesModel(selfEartag);
        return row;
    }
}