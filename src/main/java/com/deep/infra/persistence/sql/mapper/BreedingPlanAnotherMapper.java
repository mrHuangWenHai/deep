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
        @Result(property = "stageFlag", column = "stage_flag"),
        // 第一阶段的信息
        @Result(property = "buildingAfterBreeding", column = "building_after_breeding"),
        @Result(property = "buildingOld", column = "building_old"),
        @Result(property = "ramSheepTrademark", column = "ram_sheep_trademark"),
        @Result(property = "eweSheepTrademark", column = "ewe_sheep_trademark"),
        @Result(property = "breedingTime", column = "breeding_time"),
        @Result(property = "pregnancyTime", column = "pregnancy_time"),
        @Result(property = "operatorTimeFirst", column = "operator_time_first"),
        @Result(property = "operatorIdFirst", column = "operator_id_first"),
        @Result(property = "operatorNameFirst", column = "operator_name_first"),
        @Result(property = "isPassSupFirst", column = "is_pass_sup_first"),
        @Result(property = "supervisorTimeFirst", column = "supervisor_time_first"),
        @Result(property = "supervisorIdFirst", column = "supervisor_id_first"),
        @Result(property = "supervisorNameFirst", column = "supervisor_name_first"),
        @Result(property = "isPassCheckFirst", column = "is_pass_check_first"),
        @Result(property = "professorTimeFirst", column = "professor_time_first"),
        @Result(property = "professorIdFirst", column = "professor_id_first"),
        @Result(property = "professorNameFirst", column = "professor_name_first"),
        @Result(property = "remarkFirst", column = "remark_first"),
        // 第二阶段的信息
        @Result(property = "buildingToBeRelocated", column = "building_to_be_relocated"),
        @Result(property = "lambingTime", column = "lambing_time"),
        @Result(property = "lambingNumber", column = "lambing_number"),
        @Result(property = "operatorTimeSecond", column = "operator_time_second"),
        @Result(property = "operatorIdSecond", column = "operator_id_second"),
        @Result(property = "operatorNameSecond", column = "operator_name_second"),
        @Result(property = "isPassSupSecond", column = "is_pass_sup_second"),
        @Result(property = "supervisorTimeSecond", column = "supervisor_time_second"),
        @Result(property = "supervisorIdSecond", column = "supervisor_id_second"),
        @Result(property = "supervisorNameSecond", column = "supervisor_name_second"),
        @Result(property = "isPassCheckSecond", column = "is_pass_check_second"),
        @Result(property = "professorTimeSecond", column = "professor_time_second"),
        @Result(property = "professorIdSecond", column = "professor_id_second"),
        @Result(property = "professorNameSecond", column = "professor_name_second"),
        @Result(property = "remarkSecond", column = "remark_second"),
        @Result(property = "factoryNumber", column = "factory_number"),
        @Result(property = "factoryName", column = "factory_name"),
        @Result(property = "prenatalImmunityType", column = "prenatal_immunity_type"),
        @Result(property = "prenatalImmunityTime", column = "prenatal_immunity_time")
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
        @Result(property = "stageFlag", column = "stage_flag"),
        // 第一阶段的信息
        @Result(property = "buildingAfterBreeding", column = "building_after_breeding"),
        @Result(property = "buildingOld", column = "building_old"),
        @Result(property = "ramSheepTrademark", column = "ram_sheep_trademark"),
        @Result(property = "eweSheepTrademark", column = "ewe_sheep_trademark"),
        @Result(property = "breedingTime", column = "breeding_time"),
        @Result(property = "pregnancyTime", column = "pregnancy_time"),
        @Result(property = "operatorTimeFirst", column = "operator_time_first"),
        @Result(property = "operatorIdFirst", column = "operator_id_first"),
        @Result(property = "operatorNameFirst", column = "operator_name_first"),
        @Result(property = "isPassSupFirst", column = "is_pass_sup_first"),
        @Result(property = "supervisorTimeFirst", column = "supervisor_time_first"),
        @Result(property = "supervisorIdFirst", column = "supervisor_id_first"),
        @Result(property = "supervisorNameFirst", column = "supervisor_name_first"),
        @Result(property = "isPassCheckFirst", column = "is_pass_check_first"),
        @Result(property = "professorTimeFirst", column = "professor_time_first"),
        @Result(property = "professorIdFirst", column = "professor_id_first"),
        @Result(property = "professorNameFirst", column = "professor_name_first"),
        @Result(property = "remarkFirst", column = "remark_first"),
        // 第二阶段的信息
        @Result(property = "buildingToBeRelocated", column = "building_to_be_relocated"),
        @Result(property = "lambingTime", column = "lambing_time"),
        @Result(property = "lambingNumber", column = "lambing_number"),
        @Result(property = "operatorTimeSecond", column = "operator_time_second"),
        @Result(property = "operatorIdSecond", column = "operator_id_second"),
        @Result(property = "operatorNameSecond", column = "operator_name_second"),
        @Result(property = "isPassSupSecond", column = "is_pass_sup_second"),
        @Result(property = "supervisorTimeSecond", column = "supervisor_time_second"),
        @Result(property = "supervisorIdSecond", column = "supervisor_id_second"),
        @Result(property = "supervisorNameSecond", column = "supervisor_name_second"),
        @Result(property = "isPassCheckSecond", column = "is_pass_check_second"),
        @Result(property = "professorTimeSecond", column = "professor_time_second"),
        @Result(property = "professorIdSecond", column = "professor_id_second"),
        @Result(property = "professorNameSecond", column = "professor_name_second"),
        @Result(property = "remarkSecond", column = "remark_second"),
        @Result(property = "factoryNumber", column = "factory_number"),
        @Result(property = "factoryName", column = "factory_name"),
        @Result(property = "prenatalImmunityType", column = "prenatal_immunity_type"),
        @Result(property = "prenatalImmunityTime", column = "prenatal_immunity_time")
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
            "stage_flag, " +
            "building_after_breeding, " +
            "building_old, " +
            "ram_sheep_trademark, " +
            "ewe_sheep_trademark, " +
            "breeding_time, " +
            "pregnancy_time, " +
            "operator_time_first, " +
            "operator_id_first, " +
            "operator_name_first, " +
            "is_pass_sup_first, " +
            "supervisor_time_first, " +
            "supervisor_id_first, " +
            "supervisor_name_first, " +
            "is_pass_check_first, " +
            "professor_time_first, " +
            "professor_id_first, " +
            "professor_name_first, " +
            "remark_first, " +
            "building_to_be_relocated, " +
            "lambing_time, " +
            "lambing_number, " +
            "operator_time_second, " +
            "operator_id_second, " +
            "operator_name_second, " +
            "is_pass_sup_second, " +
            "supervisor_time_second, " +
            "supervisor_id_second, " +
            "supervisor_name_second, " +
            "is_pass_check_second, " +
            "professor_time_second, " +
            "professor_id_second, " +
            "professor_name_second, " +
            "remark_second, " +
            "factory_number, " +
            "factory_name, " +
            "prenatal_immunity_type, " +
            "prenatal_immunity_time" +
            " ) values(" +
            "#{gmtCreate}," +
            "#{getModify}," +
            "#{stageFlag}," +
            "#{buildingAfterBreeding}," +
            "#{buildingOld}," +
            "#{ramSheepTrademark}," +
            "#{eweSheepTrademark}," +
            "#{breedingTime}," +
            "#{pregnancyTime}," +
            "#{operatorTimeFirst}," +
            "#{operatorIdFirst}," +
            "#{operatorNameFirst}," +
            "#{isPassSupFirst}," +
            "#{supervisorTimeFirst}," +
            "#{supervisorIdFirst}," +
            "#{supervisorNameFirst}," +
            "#{isPassCheckFirst}," +
            "#{professorTimeFirst}," +
            "#{professorIdFirst}," +
            "#{professorNameFirst}," +
            "#{remarkFirst}," +
            "#{buildingToBeRelocated}," +
            "#{lambingTime}," +
            "#{lambingNumber}," +
            "#{operatorTimeSecond}," +
            "#{operatorIdSecond}," +
            "#{operatorNameSecond}," +
            "#{isPassSupSecond}," +
            "#{supervisorTimeSecond}," +
            "#{supervisorIdSecond}," +
            "#{supervisorNameSecond}," +
            "#{isPassCheckSecond}," +
            "#{professorTimeSecond}," +
            "#{professorIdSecond}," +
            "#{professorNameSecond}," +
            "#{remarkSecond}," +
            "#{factoryNumber}," +
            "#{factoryName}," +
            "#{prenatalImmunityType}," +
            "#{prenatalImmunityTime}" +
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
            "is_pass_sup_first = #{ispassSup}, " +
            "supervisor_time_first = #{supervisorTimeFirst}, " +
            "supervisor_id_first = #{supervisorId}, " +
            "supervisor_name_first = #{supervisorName}" +
            "where id = #{id}")
    Long updateARecordFirstBySupervisor(@Param("id") Long id, @Param("gmtModify") Timestamp gmtModify, @Param("supervisorTimeFirst") Timestamp supervisorTimeFirst, @Param("ispassSup") Byte ispassSup, @Param("supervisorId") Integer supervisorId, @Param("supervisorName") String supervisorName);

    /**
     * 第二阶段监督员审核一条记录
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param supervisorTimeFirst 审核时间
     * @return 是否修改成功标记
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{gmtModify}, " +
            "is_pass_sup_second = #{ispassSup}, " +
            "supervisor_time_second = #{supervisorTimeFirst}, " +
            "supervisor_id_second = #{supervisorId}, " +
            "supervisor_name_second = #{supervisorName}" +
            "where id = #{id}")
    Long updateARecordSecondBySupervisor(@Param("id") Long id, @Param("gmtModify") Timestamp gmtModify, @Param("supervisorTimeFirst") Timestamp supervisorTimeFirst, @Param("ispassSup") Byte ispassSup, @Param("supervisorId") Integer supervisorId, @Param("supervisorName") String supervisorName);

    /**
     * 第一阶段技术员审核一条记录
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param professorTimeFirst 审核时间
     * @return 是否修改成功标记
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{gmtModify}, " +
            "is_pass_check_first = #{ispassCheck}, " +
            "professor_time_first = #{professorTimeFirst}, " +
            "professor_id_first = #{professorId}, " +
            "professor_name_first = #{professorName}," +
            "stage_flag = 1" +
            "where id = #{id}")
    Long updateARecordFirstByProfessor(@Param("id") Long id, @Param("gmtModify") Timestamp gmtModify, @Param("professorTimeFirst") Timestamp professorTimeFirst, @Param("ispassCheck") Byte ispassCheck, @Param("professorId") Integer professorId, @Param("professorName") String professorName);

    /**
     * 第二阶段技术员审核一条记录
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param professorTimeFirst 审核时间
     * @return 是否修改成功标记
     */
    @Update("update breeding_plan_another set " +
            "gmt_modify = #{gmtModify}, " +
            "is_pass_check_second = #{ispassCheck}, " +
            "professor_time_second = #{professorTimeFirst}, " +
            "professor_id_second = #{professorId}, " +
            "professor_name_second = #{professorName}" +
            "where id = #{id}")
    Long updateARecordSecondByProfessor(@Param("id") Long id, @Param("gmtModify") Timestamp gmtModify, @Param("professorTimeFirst") Timestamp professorTimeFirst, @Param("ispassCheck") Byte ispassCheck, @Param("professorId") Integer professorId, @Param("professorName") String professorName);

    /**
     * 删除一条记录
     * @param id 代理主键
     * @return long类型
     */
    @Delete("delete from breeding_plan_another where id = #{id}")
    Long deleteARecords(Long id);
}
