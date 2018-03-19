package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.DisinfectFilesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Mapper
public interface DisinfectFilesMapper {
    void setDisinfectFilesModel(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);

    List<DisinfectFilesModel> getDisinfectFilesModel(@Param("factoryNum") BigInteger factoryNum,
                                                    @Param("disinfectTimeStart") String disinfectTimeStart,
                                                    @Param("disinfectTimeEnd") String disinfectTimeEnd,
                                                    @Param("disinfectName") String disinfectName,
                                                    @Param("disinfectQuality") String disinfectQuality,
                                                    @Param("disinfectWay") String disinfectWay,
                                                    @Param("operator") String operator,
                                                    @Param("professor") String professor,
                                                    @Param("supervisor") String supervisor,
                                                    @Param("remark") String remark,
                                                    @Param("isPass1") String  isPass1,
                                                    @Param("unpassReason1") String unpassReason1,
                                                    @Param("isPass2") String isPass2,
                                                    @Param("unpassReason2") String unpassReason2);
    List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNum(@Param("factoryNum")BigInteger factoryNum);
    List<DisinfectFilesModel> getDisinfectFilesModelBydisinfectTime(@Param("disinfectTime")Date disinfectTime);
    DisinfectFilesModel getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(@Param("factoryNum") BigInteger factoryNum,
                                                                                        @Param("disinfectTime") String disinfectTime,
                                                                                        @Param("disinfectName") String disinfectName);
    int deleteDisinfectFilesModelByfactoryNum(@Param("factoryNum")BigInteger factoryNum);
    int deleteDisinfectFilesModelByfactoryNumAnddisinfectTime(@Param("factoryNum")BigInteger factoryNum,
                                                              @Param("disinfectTime") String disinfectTime);
}
