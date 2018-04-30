package com.deep.infra.persistence.sql.mapper;


import com.deep.api.request.ModuleFindRequest;
import com.deep.api.request.ModuleUpdateRequest;
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


    List<RepellentPlanModel> getRepellentPlanModel(@Param("repellentPlanModel") RepellentRequest repellentPlanModel,
                                                   RowBounds bounds);

    RepellentPlanModel getRepellentPlanModelById(@Param("id") Long id);

    List<RepellentPlanModel> getRepellentPlanModelByFactoryNum(@Param("factoryNum")BigInteger factoryNum , RowBounds bounds);

    List<RepellentPlanModel> getRepellentPlanModelByIsPassCheck(@Param("request") ModuleFindRequest request , RowBounds bounds);

    List<RepellentPlanModel> getRepellentPlanModelByIsPassSup(@Param("request") ModuleFindRequest request , RowBounds bounds);

    int updateRepellentPlanModelByProfessor(@Param("request") ModuleUpdateRequest request);

    int updateRepellentPlanModelBySupervisor(@Param("request") ModuleUpdateRequest request);

    int deleteRepellentPlanModelByid(@Param("id") Long id);

    int updateRepellentPlanModelByOperator(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

}
