package com.deep.infra.persistence.sql.mapper;


import com.deep.api.request.RepellentRequest;
import com.deep.domain.model.RepellentPlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface RepellentPlanMapper {

    int setRepellentPlanModel(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);


    List<RepellentPlanModel> getRepellentPlanModel(@Param("repellentPlanModel") RepellentRequest repellentPlanModel);

    List<RepellentPlanModel> getRepellentPlanModelByTradeMarkEarTag(@Param("repellentEartag") List<String[]> repellentEartag,
                                                                    RowBounds bounds);

    RepellentPlanModel getRepellentPlanModelById(@Param("id") Long id);

    List<RepellentPlanModel> getRepellentPlanModelByFactoryNum(@Param("factoryNum")BigInteger factoryNum );

    List<RepellentPlanModel> getRepellentPlanModelByFactoryNumAndIsPassCheck(@Param("factoryNum")BigInteger factoryNum ,
                                                                             @Param("ispassCheck")String ispassCheck,
                                                                             RowBounds bounds);

    List<RepellentPlanModel> getRepellentPlanModelByFactoryNumAndIsPassSup(@Param("factoryNum")BigInteger factoryNum ,
                                                                           @Param("ispassSup")String ispassSup,
                                                                           RowBounds bounds);

    int updateRepellentPlanModelByProfessor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

    int updateRepellentPlanModelBySupervisor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

    int deleteRepellentPlanModelByid(@Param("id") Long id);

    int updateRepellentPlanModelByOperator(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

}
