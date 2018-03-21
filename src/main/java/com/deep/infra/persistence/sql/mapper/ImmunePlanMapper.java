package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.ImmunePlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Mapper
public interface ImmunePlanMapper {
    void setImmunePlanModel(@Param("immunePlanModel")ImmunePlanModel immunePlanModel);

    List<ImmunePlanModel> getImmunePlanModel(@Param("factoryNum") BigInteger factoryNum,
                                             @Param("crowdNum") String crowdNum,
                                             @Param("immuneEartagStart") String immuneEartagStart,
                                             @Param("immuneEartagEnd") String immuneEartagEnd,
                                             @Param("immuneTimeStart") String immuneTimeStart,
                                             @Param("immuneTimeEnd") String immuneTimeStartEnd,
                                             @Param("immuneKind") String immuneKind,
                                             @Param("immuneWay") String immuneWay,
                                             @Param("immuneQuality") String immuneQuality,
                                             @Param("immuneDuring") String immuneDuring,
                                             @Param("operator") String operator,
                                             @Param("professor") String professor,
                                             @Param("supervisor") String supervisor,
                                             @Param("remark") String remark,
                                             @Param("isPass1") String  isPass1,
                                             @Param("unpassReason1") String unpassReason1,
                                             @Param("isPass2") String isPass2,
                                             @Param("unpassReason2") String unpassReason2,
                                             RowBounds bounds);

    ImmunePlanModel getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(@Param("factoryNum") BigInteger factoryNum,
                                                                           @Param("crowdNum") String crowdNum,
                                                                           @Param("immuneTime") String immuneTime);

    List<ImmunePlanModel> getImmunePlanModelByProfessor(RowBounds bounds);


    int updateImmunePlanModelByProfessor(@Param("professor") String professor,
                                         @Param("isPass1") String  isPass1,
                                         @Param("unpassReason1") String unpassReason1,
                                         @Param("gmtProfessor") String gmtProfessor);


    int updateImmunePlanModelBySupervisor(@Param("supervisor") String supervisor,
                                         @Param("isPass2") String  isPass2,
                                         @Param("unpassReason2") String unpassReason2,
                                         @Param("gmtSupervise") String gmtSupervise);



    int deleteImmunePlanModelByfactoryNum(@Param("factoryNum") BigInteger factoryNum);
    int deleteImmunePlanModelByfactoryNumAndimmuneTime(@Param("factoryNum") BigInteger factoryNum,
                                                       @Param("immuneTime") String immuneTime);

}
