package com.deep.infra.persistence.sql.mapper;


import com.deep.api.request.RepellentRequest;
import com.deep.domain.model.RepellentPlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface RepellentPlanMapper {

    int setRepellentPlanModel(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);


    List<RepellentPlanModel> getRepellentPlanModel(@Param("repellentPlanModel") RepellentRequest repellentPlanModel,
                                                   RowBounds bounds);

    RepellentPlanModel getRepellentPlanModelById(@Param("id") long id);

    int deleteRepellentPlanModelByid(@Param("id") long id);

    int updateRepellentPlanModelByProfessor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

    int updateRepellentPlanModelBySupervisor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

    int updateRepellentPlanModelByOperator(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

}
