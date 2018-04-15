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
    void setRepellentPlanModel(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);


    List<RepellentPlanModel> getRepellentPlanModel(@Param("repellentPlanModel") RepellentRequest repellentPlanModel,

                                                   RowBounds bounds);

    RepellentPlanModel getRepellentPlanModelByfactoryNumAndcrowdNumAndrepellentTimeAndrepellentName(@Param("factoryNum")BigInteger factoryNum,
                                                                                                    @Param("crowdNum")String crowdNum,
                                                                                                    @Param("repellentTime")String repellentTime,
                                                                                                    @Param("repellentName")String repellentName);


    List<RepellentPlanModel> getRepellentPlanModelByProfessor(@Param("isPass") Integer isPass,
                                                              RowBounds bounds);
    List<RepellentPlanModel> getRepellentPlanModelBySupervisor(@Param("isPass1") Integer isPass1,
                                                               RowBounds bounds);

    RepellentPlanModel getRepellentPlanModelByid(@Param("id") Long id);


    int deleteRepellentPlanModelByid(@Param("id") Long id);

    int updateRepellentPlanModelByProfessor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

    int updateRepellentPlanModelBySupervisor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

    int updateRepellentPlanModelByOperator(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

}
