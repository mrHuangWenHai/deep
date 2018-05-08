package com.deep.domain.service;

import com.deep.api.request.OperationCoditionRequest;
import com.deep.domain.model.OperationFile;
import com.deep.infra.persistence.sql.mapper.OperationFileMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by huangwenhai on 2018/4/17.
 */

@Service
public class OperationFileService {

  @Resource
  private OperationFileMapper operationFileMapper;

  public List<OperationFile> getOperationFile(OperationCoditionRequest operationCoditionRequest) {
    return operationFileMapper.getOperationFile(operationCoditionRequest);
  }

  public OperationFile getOperationFileById(int id) {
    return operationFileMapper.getOperationFileById(id);
  }

  public List<OperationFile> getOperationFileByFactoryNum(BigInteger factoryNum){
    return operationFileMapper.getOperationFileByFactoryNum(factoryNum);
  }

  public int addOperationFile(OperationFile operationFile) {
    return operationFileMapper.addOperationFile(operationFile);
  }

  public int updateCheckStatus(int id, short checkStatus) {
    return operationFileMapper.updateCheckStatus(id, checkStatus);
  }

  public int updateSupStatus(int id, short supStatus) {
    return operationFileMapper.updateSupStatus(id, supStatus);
  }

  public int updateOperationFileByOperationFile(OperationFile operationFile) {
    return operationFileMapper.updateOperationFileByOperationFile(operationFile);
  }

  public int deleteOperationFile(int id) {
    return operationFileMapper.deleteOperationFile(id);
  }
}
