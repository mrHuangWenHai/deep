package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.model.RepellentPlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ImmunePlanMapper {
    void setImmunePlanModel(@Param("factoryNum") BigInteger factoryNum,
                            @Param("crowdNum") String crowdNum,
                            @Param("immuneEartag") String immuneEartag,
                            @Param("immuneTime") String immuneTime,
                            @Param("immuneKind") String immuneKind,
                            @Param("immuneWay") String immuneWay,
                            @Param("immuneQuality") String immuneQuality,
                            @Param("immuneDuring") String immuneDuring,
                            @Param("operator") String operator,
                            @Param("remark") String remark,
                            @Param("isPass1") String  isPass1,
                            @Param("isPass2") String isPass2,
                            @Param("gmtCreate") Timestamp gmtCreate);

    List<ImmunePlanModel> getImmunePlanModel(@Param("immunePlanModel") ImmunePlanModel immunePlanModel,
                                             RowBounds bounds);

    ImmunePlanModel getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(@Param("factoryNum") BigInteger factoryNum,
                                                                           @Param("crowdNum") String crowdNum,
                                                                           @Param("immuneTime") String immuneTime);

    List<ImmunePlanModel> getImmunePlanModelByProfessor(@Param("isPass1") Integer isPass1,
                                                        RowBounds bounds);
    List<ImmunePlanModel> getImmunePlanModelBySupervisor(@Param("isPass2") Integer isPass2,
                                                         RowBounds bounds);
    ImmunePlanModel getImmunePlanModelByid(@Param("id") BigInteger id);

    int deleteImmunePlanModelByid(@Param("id") BigInteger id);


    int updateImmunePlanModelByProfessor(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);
    int updateImmunePlanModelBySupervisor(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);
    int updateImmunePlanModelByOperator(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);


}
