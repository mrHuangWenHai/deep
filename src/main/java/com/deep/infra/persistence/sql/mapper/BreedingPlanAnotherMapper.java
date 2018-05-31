package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.BreedingModifyRequest;
import com.deep.api.request.BreedingRequest;
import com.deep.domain.model.BreedingPlanAnotherModel;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BreedingPlanAnotherMapper {
    /**
     * 查找某一个阶段的记录条数
     * @param factoryNumber 阶段标志
     * @return 记录条数
     */
    @Select("select count(*) from breeding_plan_another where factory_number = #{factoryNumber}")
    Long queryCount(Long factoryNumber);

    /**
     * 查找某个阶段，某个羊场的所有记录
     * @param factoryNumber 羊场编号
     * @return 所有记录信息
     */
    @Select("select * from breeding_plan_another where factory_number = #{factoryNumber}")
    @Results ({
        @Result(property = "id", column = "id"),
        @Result(property = "gmtCreate", column = "gmt_create"),
        @Result(property = "gmtModify", column = "gmt_modify"),
        @Result(property = "breedingTime", column = "breeding_time"),
        @Result(property = "buildingAfterBreeding", column = "building_after_breeding"),
        @Result(property = "ramSheepTrademark", column = "ram_sheep_trademark"),
        @Result(property = "eweSheepTrademark", column = "ewe_sheep_trademark"),
        @Result(property = "manageFlag", column = "manage_flag"),
        @Result(property = "manageAverageTime", column = "manage_average_time"),
        @Result(property = "nutritionBeforePregnancy", column = "nutrition_before_pregnancy"),
        @Result(property = "isPregnancy", column = "is_pregnancy"),
        @Result(property = "nutritionAfterPregnancy", column = "nutrition_after_pregnancy"),
        @Result(property = "prenatalImmunityType", column = "prenatal_immunity_type"),
        @Result(property = "prenatalImmunityTime", column = "prenatal_immunity_time"),
        @Result(property = "buildingToBeRelocated", column = "building_to_be_relocated"),
        @Result(property = "nutritionBeforeLambing", column = "nutrition_before_lambing"),
        @Result(property = "lambingTime", column = "lambing_time"),
        @Result(property = "lambingNumber", column = "lambing_number"),
        @Result(property = "nutritionBreastFeeding", column = "nutrition_breast_feeding"),
        @Result(property = "nutritionInsteadBreastFeeding", column = "nutrition_instead_breast_feeding"),
        @Result(property = "nutritionBeforeCutBreastFeeding", column = "nutrition_before_cut_breast_feeding"),
        @Result(property = "nutritionCutBreastFeeding", column = "nutrition_cut_breast_feeding"),
        @Result(property = "operatorTime", column = "operator_time"),
        @Result(property = "operatorId", column = "operator_id"),
        @Result(property = "operatorName", column = "operator_name"),
        @Result(property = "ispassSup", column = "is_pass_up"),
        @Result(property = "supervisorTime", column = "supervisor_time"),
        @Result(property = "supervisorId", column = "supervisor_id"),
        @Result(property = "supervisorName", column = "supervisor_name"),
        @Result(property = "ispassCheck", column = "is_pass_check"),
        @Result(property = "professorTime", column = "professor_time"),
        @Result(property = "professorId", column = "professor_id"),
        @Result(property = "professorName", column = "professor_name"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "factoryNumber", column = "factory_number"),
        @Result(property = "factoryName", column = "factory_name"),
        @Result(property = "professorNotPassReason", column = "professor_not_pass_reason")
    })
    List<BreedingPlanAnotherModel> findAllRecords(@Param("factoryNumber") Long factoryNumber);

    /**
     * 根据ID获取单条记录
     * @param id 记录主键
     * @return 单条信息
     */
    @Select("select * from breeding_plan_another where id = #{id}")
    @Results ({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify"),
            @Result(property = "breedingTime", column = "breeding_time"),
            @Result(property = "buildingAfterBreeding", column = "building_after_breeding"),
            @Result(property = "ramSheepTrademark", column = "ram_sheep_trademark"),
            @Result(property = "eweSheepTrademark", column = "ewe_sheep_trademark"),
            @Result(property = "manageFlag", column = "manage_flag"),
            @Result(property = "manageAverageTime", column = "manage_average_time"),
            @Result(property = "nutritionBeforePregnancy", column = "nutrition_before_pregnancy"),
            @Result(property = "isPregnancy", column = "is_pregnancy"),
            @Result(property = "nutritionAfterPregnancy", column = "nutrition_after_pregnancy"),
            @Result(property = "prenatalImmunityType", column = "prenatal_immunity_type"),
            @Result(property = "prenatalImmunityTime", column = "prenatal_immunity_time"),
            @Result(property = "buildingToBeRelocated", column = "building_to_be_relocated"),
            @Result(property = "nutritionBeforeLambing", column = "nutrition_before_lambing"),
            @Result(property = "lambingTime", column = "lambing_time"),
            @Result(property = "lambingNumber", column = "lambing_number"),
            @Result(property = "nutritionBreastFeeding", column = "nutrition_breast_feeding"),
            @Result(property = "nutritionInsteadBreastFeeding", column = "nutrition_instead_breast_feeding"),
            @Result(property = "nutritionBeforeCutBreastFeeding", column = "nutrition_before_cut_breast_feeding"),
            @Result(property = "nutritionCutBreastFeeding", column = "nutrition_cut_breast_feeding"),
            @Result(property = "operatorTime", column = "operator_time"),
            @Result(property = "operatorId", column = "operator_id"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "ispassSup", column = "is_pass_sup"),
            @Result(property = "supervisorTime", column = "supervisor_time"),
            @Result(property = "supervisorId", column = "supervisor_id"),
            @Result(property = "supervisorName", column = "supervisor_name"),
            @Result(property = "ispassCheck", column = "is_pass_check"),
            @Result(property = "professorTime", column = "professor_time"),
            @Result(property = "professorId", column = "professor_id"),
            @Result(property = "professorName", column = "professor_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "factoryNumber", column = "factory_number"),
            @Result(property = "factoryName", column = "factory_name"),
            @Result(property = "professorNotPassReason", column = "professor_not_pass_reason")
    })
    BreedingPlanAnotherModel findARecords(Long id);

    /**
     * 插入一条记录
     * @param breedingPlanAnotherModel 对象模型
     * @return 返回相关信息
     */
    @Insert("insert into breeding_plan_another(" +
            "gmt_create, " +
            "gmt_modify, " +
            "breeding_time, " +
            "building_after_breeding, " +
            "ram_sheep_trademark, " +
            "ewe_sheep_trademark, " +
            "manage_flag, " +
            "manage_average_time, " +
            "nutrition_before_pregnancy, " +
            "is_pregnancy, " +
            "nutrition_after_pregnancy, " +
            "prenatal_immunity_type, " +
            "prenatal_immunity_time, " +
            "building_to_be_relocated, " +
            "nutrition_before_lambing, " +
            "lambing_time, " +
            "lambing_number, " +
            "nutrition_breast_feeding, " +
            "nutrition_instead_breast_feeding, " +
            "nutrition_before_cut_breast_feeding, " +
            "nutrition_cut_breast_feeding, " +
            "operator_time, " +
            "operator_id, " +
            "operator_name, " +
            "is_pass_up, " +
            "supervisor_time, " +
            "supervisor_id, " +
            "supervisor_name, " +
            "is_pass_check, " +
            "professor_time, " +
            "professor_id, " +
            "professor_name, " +
            "remark " +
            " ) values(" +
            "#{id}," +
            "#{gmtCreate}," +
            "#{gmtModify}," +
            "#{breedingTime}," +
            "#{buildingAfterBreeding}," +
            "#{ramSheepTrademark}," +
            "#{eweSheepTrademark}," +
            "#{manageFlag}," +
            "#{manageAverageTime}," +
            "#{nutritionBeforePregnancy}," +
            "#{isPregnancy}," +
            "#{nutritionAfterPregnancy}," +
            "#{prenatalImmunityType}," +
            "#{prenatalImmunityTime}," +
            "#{buildingToBeRelocated}," +
            "#{nutritionBeforeLambing}," +
            "#{lambingTime}," +
            "#{lambingNumber}," +
            "#{nutritionBreastFeeding}," +
            "#{nutritionInsteadBreastFeeding}," +
            "#{nutritionBeforeCutBreastFeeding}," +
            "#{nutritionCutBreastFeeding}," +
            "#{operatorTime}," +
            "#{operatorId}," +
            "#{operatorName}," +
            "#{ispassSup}," +
            "#{supervisorTime}," +
            "#{supervisorId}," +
            "#{supervisorName}," +
            "#{ispassCheck}," +
            "#{professorTime}," +
            "#{professorId}," +
            "#{professorName}," +
            "#{remark}" +
            ")")
    Long addARecord(BreedingPlanAnotherModel breedingPlanAnotherModel);

    /**
     * 操作员添加一条记录
     * @param breedingRequest 对象数据模型
     * @return 是否添加成功标志
     */
    @Insert("insert into breeding_plan_another(" +
            "gmt_create, " +
            "gmt_modify, " +
            "breeding_time, " +
            "building_after_breeding, " +
            "ram_sheep_trademark, " +
            "ewe_sheep_trademark, " +
            "manage_flag, " +
            "manage_average_time, " +
            "nutrition_before_pregnancy, " +
            "is_pregnancy, " +
            "nutrition_after_pregnancy, " +
            "prenatal_immunity_type, " +
            "prenatal_immunity_time, " +
            "building_to_be_relocated, " +
            "nutrition_before_lambing, " +
            "lambing_time, " +
            "lambing_number, " +
            "nutrition_breast_feeding, " +
            "nutrition_instead_breast_feeding, " +
            "nutrition_before_cut_breast_feeding, " +
            "nutrition_cut_breast_feeding, " +
            "operator_time, " +
            "operator_id, " +
            "operator_name, " +
            "remark, " +
            "factory_number, " +
            "factory_name," +
            "is_pass_sup," +
            "is_pass_check " +
            " ) values(" +
            "#{gmtCreate}, " +
            "#{gmtModify}, " +
            "#{breedingTime}, " +
            "#{buildingAfterBreeding}, " +
            "#{ramSheepTrademark}, " +
            "#{eweSheepTrademark}, " +
            "#{manageFlag}, " +
            "#{manageAverageTime}, " +
            "#{nutritionBeforePregnancy}, " +
            "#{isPregnancy}, " +
            "#{nutritionAfterPregnancy}, " +
            "#{prenatalImmunityType}, " +
            "#{prenatalImmunityTime}, " +
            "#{buildingToBeRelocated}, " +
            "#{nutritionBeforeLambing}, " +
            "#{lambingTime}, " +
            "#{lambingNumber}, " +
            "#{nutritionBreastFeeding}, " +
            "#{nutritionInsteadBreastFeeding}, " +
            "#{nutritionBeforeCutBreastFeeding}, " +
            "#{nutritionCutBreastFeeding}, " +
            "#{operatorTime}, " +
            "#{operatorId}, " +
            "#{operatorName}, " +
            "#{remark}, " +
            "#{factoryNumber}, " +
            "#{factoryName}," +
            "2," +
            "2" +
            ")")
    Long addARecordByOperator(BreedingRequest breedingRequest);

    /**
     * 修改一条记录
     * @param breedingModifyRequest 对象模型
     * @return 修改是否成功标志
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{breedingModifyRequest.gmtModify}, " +
            "breeding_time = #{breedingModifyRequest.breedingTime}, " +
            "building_after_breeding = #{breedingModifyRequest.buildingAfterBreeding}, " +
            "ram_sheep_trademark = #{breedingModifyRequest.ramSheepTrademark}, " +
            "ewe_sheep_trademark = #{breedingModifyRequest.eweSheepTrademark}, " +
            "manage_flag = #{breedingModifyRequest.manageFlag}, " +
            "manage_average_time = #{breedingModifyRequest.manageAverageTime}, " +
            "nutrition_before_pregnancy = #{breedingModifyRequest.nutritionBeforePregnancy}, " +
            "is_pregnancy = #{breedingModifyRequest.isPregnancy}, " +
            "nutrition_after_pregnancy = #{breedingModifyRequest.nutritionAfterPregnancy}, " +
            "prenatal_immunity_type = #{breedingModifyRequest.prenatalImmunityType}, " +
            "prenatal_immunity_time = #{breedingModifyRequest.prenatalImmunityTime}, " +
            "building_to_be_relocated = #{breedingModifyRequest.buildingToBeRelocated}, " +
            "nutrition_before_lambing = #{breedingModifyRequest.nutritionBeforeLambing}, " +
            "lambing_time = #{breedingModifyRequest.lambingTime}, " +
            "lambing_number = #{breedingModifyRequest.lambingNumber}, " +
            "nutrition_breast_feeding = #{breedingModifyRequest.nutritionBreastFeeding}, " +
            "nutrition_instead_breast_feeding = #{breedingModifyRequest.nutritionInsteadBreastFeeding}, " +
            "nutrition_before_cut_breast_feeding = #{breedingModifyRequest.nutritionBeforeCutBreastFeeding}, " +
            "nutrition_cut_breast_feeding = #{breedingModifyRequest.nutritionCutBreastFeeding}, " +
            "remark = #{breedingModifyRequest.remark}, " +
            "operator_time = #{breedingModifyRequest.operatorTime}, " +
            "operator_id = #{breedingModifyRequest.operatorId}, " +
            "operator_name = #{breedingModifyRequest.operatorName}, " +
            "is_pass_check = 2," +
            "is_pass_sup = 2 " +
            "where id = #{id}")
    Long updateARecord(@Param("breedingModifyRequest") BreedingModifyRequest breedingModifyRequest , @Param("id") Long id);

    /**
     * 监督员审核一条记录
     * @param id 记录主键
     * @param modify 修改时间
     * @param time 审核时间
     * @return 是否修改成功标记
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{modify}, " +
            "is_pass_sup = #{pass}, " +
            "supervisor_time = #{time}, " +
            "supervisor_id = #{supervisor}, " +
            "supervisor_name = #{name} " +
            "where id = #{id}")
    Long updateARecordBySupervisor(@Param("id") Long id, @Param("modify") Timestamp modify, @Param("time") Timestamp time, @Param("pass") Byte pass, @Param("supervisor") Integer supervisor, @Param("name") String name);

    /**
     * 技术员审核一条记录
     * @param id 记录主键
     * @param modify 修改时间
     * @param time 审核时间
     * @return 是否修改成功标记
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{modify}, " +
            "is_pass_check = #{pass}, " +
            "professor_time = #{time}, " +
            "professor_id = #{professor}, " +
            "professor_name = #{name}," +
            "professor_not_pass_reason = #{reason} " +
            "where id = #{id}")
    Long updateARecordByProfessor(@Param("id") Long id, @Param("modify") Timestamp modify, @Param("time") Timestamp time, @Param("pass") Byte pass, @Param("professor") Integer professor, @Param("name") String name, @Param("reason") String reason);

    /**
     * 删除一条记录
     * @param id 代理主键
     * @return long类型
     */
    @Delete("delete from breeding_plan_another where id = #{id}")
    Long deleteARecords(Long id);
}
