package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.NutritionRequest;
import com.deep.domain.model.NutritionPlan;
import com.deep.domain.model.NutritionPlanExample;
import com.deep.domain.model.NutritionPlanWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

public interface NutritionPlanMapper {
    int countByExample(NutritionPlanExample example);

    int deleteByExample(NutritionPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NutritionPlanWithBLOBs record);

    int insertSelective(NutritionPlanWithBLOBs record);

    List<NutritionPlanWithBLOBs> selectByExampleWithBLOBsWithRowbounds(NutritionPlanExample example, RowBounds rowBounds);

    List<NutritionPlanWithBLOBs> selectByExampleWithBLOBs(NutritionPlanExample example);

    List<NutritionPlan> selectByExampleWithRowbounds(NutritionPlanExample example, RowBounds rowBounds);

    List<NutritionPlan> selectByExample(NutritionPlanExample example);

    NutritionPlanWithBLOBs selectByPrimaryKey(Integer id);

    int getNutritionPlanModelCount(@Param("factoryNum") Long factoryNum);

    Long getImmunePlanModelOperator(@Param("id") Long id);

    int updateByExampleSelective(@Param("record") NutritionPlanWithBLOBs record, @Param("example") NutritionPlanExample example);

    int updateByExampleWithBLOBs(@Param("record") NutritionPlanWithBLOBs record, @Param("example") NutritionPlanExample example);

    int updateByExample(@Param("record") NutritionPlan record, @Param("example") NutritionPlanExample example);

    int updateByPrimaryKeySelective(@Param("record") NutritionPlanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NutritionPlanWithBLOBs record);

    int updateByPrimaryKey(NutritionPlan record);

    List<NutritionPlanWithBLOBs> selectByTwoDate(@Param("date1") Date date1, @Param("date2") Date date2, @Param("number") Integer number);

//    @Param("factory") Long factory, @Param("pass") Byte pass, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("factoryName") String factoryName, @Param("earTag") String earTag
    List<NutritionPlanWithBLOBs> findAllRecords(@Param("nutritionRequest")NutritionRequest nutritionRequest);

    List<NutritionPlanWithBLOBs> getAll(@Param("nutritionRequest")NutritionRequest nutritionRequest);
}