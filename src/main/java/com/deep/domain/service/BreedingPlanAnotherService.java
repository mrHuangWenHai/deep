package com.deep.domain.service;

import com.deep.api.request.BreedingOperatorFirst;
import com.deep.api.request.BreedingOperatorSecond;
import com.deep.api.request.SupervisorRequest;
import com.deep.domain.model.BreedingPlanAnotherModel;
import com.deep.infra.persistence.sql.mapper.BreedingPlanAnotherMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.eventmodel.ERFListener;
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
     * @param breedingOperatorFirst 对象模型
     * @return response相关信息
     */
    public Long addARecordByOperator(BreedingOperatorFirst breedingOperatorFirst) {
        return breedingPlanAnotherMapper.addARecordByOperator(breedingOperatorFirst);
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
     * @param breedingOperatorFirst 修改对象模型
     * @return 是否修改成功标志
     */
    public Long updateARecordFirstByOperator(BreedingOperatorFirst breedingOperatorFirst, Long id) {
        return breedingPlanAnotherMapper.updateARecord(breedingOperatorFirst, id);
    }

    /**
     * 操作员在第二阶段修改一条记录
     * @param breedingOperatorSecond 修改对象模型
     * @return 是否修改成功标志
     */
    public Long updateARecordByOperator(BreedingOperatorSecond breedingOperatorSecond) {
        return breedingPlanAnotherMapper.updateARecordByOperator(breedingOperatorSecond);
    }

    /**
     * 审核员完成第一阶段审核
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param supervisorTimeFirst 审核时间
     * @param ispassSup 是否通过0表示不通过， 1 表示通过， 2表示未审核
     * @param supervisorId 审核员ID
     * @param supervisorName 审核员姓名
     * @return 修改成功标志
     */
    public Long updateARecordFirstBySupervisor(Long id, Timestamp gmtModify, Timestamp supervisorTimeFirst, Byte ispassSup, Integer supervisorId, String supervisorName) {
        return breedingPlanAnotherMapper.updateARecordFirstBySupervisor(id,gmtModify, supervisorTimeFirst, ispassSup, supervisorId, supervisorName);
    }

    /**
     * 审核员完成第二阶段审核
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param supervisorTimeFirst 审核时间
     * @param ispassSup 是否通过0表示不通过， 1 表示通过， 2表示未审核
     * @param supervisorId 审核员ID
     * @param supervisorName 审核员姓名
     * @return 修改成功标志
     */
    public Long updateARecordSecondBySupervisor(Long id, Timestamp gmtModify, Timestamp supervisorTimeFirst, Byte ispassSup, Integer supervisorId, String supervisorName) {
        return breedingPlanAnotherMapper.updateARecordSecondBySupervisor(id,gmtModify, supervisorTimeFirst, ispassSup, supervisorId, supervisorName);
    }

    /**
     * 技术员完成第一阶段审核
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param professorTimeFirst 审核时间
     * @param ispassCheck 是否通过0表示不通过， 1 表示通过， 2表示未审核
     * @param professorId 审核员ID
     * @param professorName 审核员姓名
     * @return 修改成功标志
     */
    public Long updateARecordFirstByProfessor(Long id, Timestamp gmtModify, Timestamp professorTimeFirst, Byte ispassCheck, Integer professorId, String professorName) {
        return breedingPlanAnotherMapper.updateARecordFirstByProfessor(id,gmtModify, professorTimeFirst, ispassCheck, professorId, professorName);
    }

    /**
     * 技术员完成第二阶段审核
     * @param id 记录主键
     * @param gmtModify 修改时间
     * @param professorTimeFirst 审核时间
     * @param ispassCheck 是否通过0表示不通过， 1 表示通过， 2表示未审核
     * @param professorId 审核员ID
     * @param professorName 审核员姓名
     * @return 修改成功标志
     */
    public Long updateARecordSecondByProfessor(Long id, Timestamp gmtModify, Timestamp professorTimeFirst, Byte ispassCheck, Integer professorId, String professorName) {
        return breedingPlanAnotherMapper.updateARecordSecondByProfessor(id,gmtModify, professorTimeFirst, ispassCheck, professorId, professorName);
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
     * @param stageFlag 阶段标志， 0为阶段1， 1为阶段二， 默认为阶段1
     * @param start 开始记录数目
     * @param size 每页条数
     * @return 记录列表
     */
    public List<BreedingPlanAnotherModel> findAllRecords(Long factoryNumber, Byte stageFlag, Long start, Byte size) {
        return breedingPlanAnotherMapper.findAllRecords(factoryNumber, stageFlag, start, size);
    }

    /**
     * 查找某个阶段的所有数据
     * @param stageFlag 阶段标志
     * @return 记录数目
     */
    public Long queryCount(Byte stageFlag) {
        return breedingPlanAnotherMapper.queryCount(stageFlag);
    }
}
