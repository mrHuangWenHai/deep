package com.deep.infra.persistence.sql.mapper;


import com.deep.api.request.ImmuneRequest;
import com.deep.domain.model.ImmunePlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ImmunePlanMapper {
    int setImmunePlanModel(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);


    List<ImmunePlanModel> getImmunePlanModel(@Param("immunePlanModel") ImmuneRequest immunePlanModel);

    List<ImmunePlanModel> getImmunePlanModelByTradeMarkEarTag(@Param("immuneEartag")List<String[]> immuneEartag,
                                                              RowBounds bounds);

    ImmunePlanModel getImmunePlanModelById(@Param("id") Long id);

    List<ImmunePlanModel> getImmunePlanModelByFactoryNum(@Param("factoryNum")BigInteger factoryNum);

    List<ImmunePlanModel> getImmunePlanModelByFactoryNumAndIsPassCheck(@Param("factoryNum")BigInteger factoryNum ,
                                                                       @Param("ispassCheck")String ispassCheck,
                                                                       RowBounds bounds);

    List<ImmunePlanModel> getImmunePlanModelByFactoryNumAndIsPassSup(@Param("factoryNum")BigInteger factoryNum ,
                                                                     @Param("ispassSup")String ispassSup,
                                                                     RowBounds bounds);

    int updateImmunePlanModelByProfessor(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);

    int updateImmunePlanModelBySupervisor(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);

    int updateImmunePlanModelByOperator(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);

    int deleteImmunePlanModelById(@Param("id") Long id);



}
