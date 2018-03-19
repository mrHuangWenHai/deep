package com.deep.domain.service;

import com.deep.domain.model.DisinfectFilesModel;
import com.deep.infra.persistence.sql.mapper.DisinfectFilesMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class DisinfectFilesService {
    @Resource
    private DisinfectFilesMapper disinfectFilesMapper;

    public void setDisinfectFilesModel(DisinfectFilesModel disinfectFilesModel){
        this.disinfectFilesMapper.setDisinfectFilesModel(disinfectFilesModel);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModel(@RequestParam("factoryNum") BigInteger factoryNum,
                                                            @RequestParam("disinfectTimeStart") String disinfectTimeStart,
                                                            @RequestParam("disinfectTimeEnd") String disinfectTimeEnd,
                                                            @RequestParam("disinfectName") String disinfectName,
                                                            @RequestParam("disinfectQuality") String disinfectQuality,
                                                            @RequestParam("disinfectWay") String disinfectWay,
                                                            @RequestParam("operator") String operator,
                                                            @RequestParam("professor") String professor,
                                                            @RequestParam("supervisor") String supervisor,
                                                            @RequestParam("remark") String remark,
                                                            @RequestParam("isPass1") String  isPass1,
                                                            @RequestParam("unpassReason1") String unpassReason1,
                                                            @RequestParam("isPass2") String  isPass2,
                                                            @RequestParam("unpassReason2") String unpassReason2){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModel(factoryNum,
                disinfectTimeStart,disinfectTimeEnd, disinfectName, disinfectQuality, disinfectWay, operator,
                professor, supervisor, remark, isPass1,unpassReason1,isPass2,unpassReason2);
        return disinfectFilesModels;
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNum(BigInteger factoryNum){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNum(factoryNum);
        return disinfectFilesModels;
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelBydisinfectTime(Date disinfectTime){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelBydisinfectTime(disinfectTime);
        return disinfectFilesModels;
    }
    public DisinfectFilesModel getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(BigInteger factoryNum, String disinfectTime, String disinfectName){
        DisinfectFilesModel disinfectFilesModel = this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(factoryNum,disinfectTime,disinfectName);
        return disinfectFilesModel;
    }
    public int deleteDisinfectFilesModelByfactoryNum(BigInteger factoryNum){
        int row = this.disinfectFilesMapper.deleteDisinfectFilesModelByfactoryNum(factoryNum);
        return row;
    }
    public int deleteDisinfectFilesModelByfactoryNumAnddisinfectTime(BigInteger factoryNum, String disinfectTime){
        int row = this.disinfectFilesMapper.deleteDisinfectFilesModelByfactoryNumAnddisinfectTime(factoryNum,disinfectTime);
        return row;
    }

}
