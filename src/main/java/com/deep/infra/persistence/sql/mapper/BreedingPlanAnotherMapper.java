package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.BreedingOperatorFirst;
import com.deep.api.request.BreedingOperatorSecond;
import com.deep.domain.model.BreedingPlanAnotherModel;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BreedingPlanAnotherMapper {
    /**
     * 查找某一个阶段的记录条数
     * @param stageFlag 阶段标志
     * @return 记录条数
     */
    @Select("select count(*) from breeding_plan_another where stage_flag = #{stageFlag}")
    Long queryCount(Byte stageFlag);

    /**
     * 查找某个阶段，某个羊场的所有记录
     * @param factoryNumber 羊场编号
     * @param stageFlag 阶段标志
     * @return 所有记录信息
     */
    @Select("select * from breeding_plan_another where stage_flag = #{stageFlag} and factory_number = #{factoryNumber} limit #{start}, #{size}")
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
        @Result(property = "remark", column = "remark")
    })
    List<BreedingPlanAnotherModel> findAllRecords(@Param("factoryNumber") Long factoryNumber, @Param("stageFlag") Byte stageFlag, @Param("start") Long start, @Param("size") Byte size);

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
            @Result(property = "ispassSup", column = "is_pass_up"),
            @Result(property = "supervisorTime", column = "supervisor_time"),
            @Result(property = "supervisorId", column = "supervisor_id"),
            @Result(property = "supervisorName", column = "supervisor_name"),
            @Result(property = "ispassCheck", column = "is_pass_check"),
            @Result(property = "professorTime", column = "professor_time"),
            @Result(property = "professorId", column = "professor_id"),
            @Result(property = "professorName", column = "professor_name"),
            @Result(property = "remark", column = "remark")
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
     * @param breedingOperatorFirst 对象数据模型
     * @return 是否添加成功标志
     */
    @Insert("insert into breeding_plan_another(" +
            "gmt_create, " +
            "gmt_modify, " +
            "building_after_breeding, " +
            "building_old, " +
            "ram_sheep_trademark, " +
            "ewe_sheep_trademark, " +
            "breeding_time, " +
            "pregnancy_time, " +
            "operator_time_first, " +
            "operator_id_first, " +
            "operator_name_first, " +
            "remark_first, " +
            "factory_number, " +
            "factory_name," +
            "stage_flag" +
            " ) values(" +
            "#{gmtCreate}," +
            "#{gmtModify}," +
            "#{buildingAfterBreeding}," +
            "#{buildingOld}," +
            "#{ramSheepTrademark}," +
            "#{eweSheepTrademark}," +
            "#{breedingTime}," +
            "#{pregnancyTime}," +
            "#{operatorTimeFirst}," +
            "#{operatorIdFirst}," +
            "#{operatorNameFirst}," +
            "#{remarkFirst}," +
            "#{factoryNumber}," +
            "#{factoryName}," +
            "0" +
            ")")
    Long addARecordByOperator(BreedingOperatorFirst breedingOperatorFirst);

    /**
     * 修改一条记录
     * @param breedingOperatorFirst 对象模型
     * @return 修改是否成功标志
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{breedingOperatorFirst.gmtModify}, " +
            "building_after_breeding = #{breedingOperatorFirst.buildingAfterBreeding}, " +
            "building_old = #{breedingOperatorFirst.buildingOld}, " +
            "ram_sheep_trademark = #{breedingOperatorFirst.ramSheepTrademark}, " +
            "ewe_sheep_trademark = #{breedingOperatorFirst.eweSheepTrademark}, " +
            "breeding_time = #{breedingOperatorFirst.breedingTime}, " +
            "pregnancy_time = #{breedingOperatorFirst.pregnancyTime}, " +
            "operator_time_first = #{breedingOperatorFirst.operatorTimeFirst}, " +
            "operator_id_first = #{breedingOperatorFirst.operatorIdFirst}, " +
            "operator_name_first = #{breedingOperatorFirst.operatorNameFirst}, " +
            "remark_first = #{breedingOperatorFirst.remarkFirst} " +
            "where id = #{id}")
    Long updateARecord(@Param("breedingOperatorFirst") BreedingOperatorFirst breedingOperatorFirst, @Param("id") Long id);

    /**
     * 操作员修改记录作为第二阶段记录
     * @param breedingOperatorSecond 第二阶段对象模型
     * @return 是否成功的标志
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{gmtModify}, " +
            "building_to_be_relocated = #{buildingToBeRelocated}, " +
            "lambing_time = #{lambingTime}, " +
            "lambing_number = #{lambingNumber}, " +
            "operator_time_second = #{operatorTimeSecond}, " +
            "operator_id_second = #{operatorIdSecond}, " +
            "operator_name_second = #{operatorNameSecond}, " +
            "remark_second = #{remarkSecond}, " +
            "prenatal_immunity_type = #{prenatalImmunityType}, " +
            "prenatal_immunity_time = #{prenatalImmunityTime} " +
            "where id = #{id}")
    Long updateARecordByOperator(BreedingOperatorSecond breedingOperatorSecond);

    /**
     * 第一阶段监督员审核一条记录
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param supervisorTimeFirst 审核时间
     * @return 是否修改成功标记
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{gmtModify}, " +
            "is_pass_sup = #{ispassSup}, " +
            "supervisor_time = #{supervisorTimeFirst}, " +
            "supervisor_id = #{supervisorId}, " +
            "supervisor_name = #{supervisorName} " +
            "where id = #{id}")
    Long updateARecordFirstBySupervisor(@Param("id") Long id, @Param("gmtModify") Timestamp gmtModify, @Param("supervisorTimeFirst") Timestamp supervisorTimeFirst, @Param("ispassSup") Byte ispassSup, @Param("supervisorId") Integer supervisorId, @Param("supervisorName") String supervisorName);

    /**
     * 第一阶段技术员审核一条记录
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param professorTimeFirst 审核时间
     * @return 是否修改成功标记
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{gmtModify}, " +
            "is_pass_check = #{ispassCheck}, " +
            "professor_time = #{professorTimeFirst}, " +
            "professor_id = #{professorId}, " +
            "professor_name = #{professorName} " +
            "where id = #{id}")
    Long updateARecordFirstByProfessor(@Param("id") Long id, @Param("gmtModify") Timestamp gmtModify, @Param("professorTimeFirst") Timestamp professorTimeFirst, @Param("ispassCheck") Byte ispassCheck, @Param("professorId") Integer professorId, @Param("professorName") String professorName);
    /**
     * 删除一条记录
     * @param id 代理主键
     * @return long类型
     */
    @Delete("delete from breeding_plan_another where id = #{id}")
    Long deleteARecords(Long id);
}
