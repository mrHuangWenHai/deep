package com.deep.domain.service;

import com.deep.api.request.*;
import com.deep.domain.model.BreedingPlanAnotherModel;
import com.deep.infra.persistence.sql.mapper.BreedingPlanAnotherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BreedingPlanAnotherService {
    @Resource
    private BreedingPlanAnotherMapper breedingPlanAnotherMapper;

    /**
     * 添加一条记录
     * @param breedingPlanAnotherModel 对象模型
     * @return 是否添加成功标志
     */
    public Long addARecord(BreedingPlanAnotherModel breedingPlanAnotherModel) {
        return breedingPlanAnotherMapper.addARecord(breedingPlanAnotherModel);
    }

    /**
     * 操作员添加一条记录信息
     * @param breedingRequest 对象模型
     * @return response相关信息
     */
    public Long addARecordByOperator(BreedingRequest breedingRequest) {
        return breedingPlanAnotherMapper.addARecordByOperator(breedingRequest);
    }

    /**
     * 删除一条记录
     * @param id 删除记录的ID
     * @return 是否删除成功标志
     */
    public Long deleteARecord(Long id) {
        return breedingPlanAnotherMapper.deleteARecords(id);
    }

    /**
     * 修改一条记录
     * @param breedingModifyRequest 修改对象模型
     * @return 是否修改成功标志
     */
    public Long updateARecordFirstByOperator(BreedingModifyRequest breedingModifyRequest, Long id) {
        return breedingPlanAnotherMapper.updateARecord(breedingModifyRequest, id);
    }

    /**
     * 审核员完成审核
     * @param id 记录主键
     * @param modify 修改时间
     * @param time 审核时间
     * @param pass 是否通过0表示不通过， 1 表示通过， 2表示未审核
     * @param supervisor 审核员ID
     * @param name 审核员姓名
     * @return 修改成功标志
     */
    public Long updateARecordBySupervisor(Long id, Timestamp modify, Timestamp time, Byte pass, Integer supervisor, String name) {
        return breedingPlanAnotherMapper.updateARecordBySupervisor(id, modify, time, pass, supervisor, name);
    }

    /**
     * 技术员完成审核
     * @param id 记录主键
     * @param modify 修改时间
     * @param time 审核时间
     * @param pass 是否通过0表示不通过， 1 表示通过， 2表示未审核
     * @param professor 审核员ID
     * @param name 审核员姓名
     * @return 修改成功标志
     */
    public Long updateARecordByProfessor(Long id, Timestamp modify, Timestamp time, Byte pass, Integer professor, String name, String reason) {
        return breedingPlanAnotherMapper.updateARecordByProfessor(id, modify, time, pass, professor, name, reason);
    }

    /**
     * 查找一条记录
     * @param id 要查找记录的主键
     * @return 查找的单条记录
     */
    public BreedingPlanAnotherModel findARecord(Long id) {
        return breedingPlanAnotherMapper.findARecords(id);
    }

    /**
     * 查找某个厂家的记录
     * @param factoryNumber 羊场主键
     * @return 记录列表
     */
    public List<BreedingPlanAnotherModel> findAllRecords(Long factoryNumber) {
        return breedingPlanAnotherMapper.findAllRecords(factoryNumber);
    }

    /**
     * 查找某个厂家的记录
     * @param factoryNumber 羊场主键
     * @return 记录列表
     */
    public List<BreedingPlanAnotherModel> findAllRecordsByIsPassCheck(Long factoryNumber, Byte pass) {
        return breedingPlanAnotherMapper.findAllRecordsByPass(factoryNumber, pass);
    }

    /**
     * @param factory 羊场标志
     * @return 记录数目
     */
    public Long queryCount(Long factory) {
        return breedingPlanAnotherMapper.queryCount(factory);
    }

    /**
     * @param factory 羊场标志
     * @return 记录数目
     */
    public Long queryCountByPass(Long factory, Byte pass) {
        return breedingPlanAnotherMapper.queryCountByCount(factory, pass);
    }
}
