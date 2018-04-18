package com.deep.domain.service;

import com.deep.api.request.OperationCoditionRequest;
import com.deep.domain.model.OperationFile;
import com.deep.infra.persistence.sql.mapper.OperationFileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

  public int addOperationFile(OperationFile operationFile) {
    return operationFileMapper.addOperationFile(operationFile);
  }

  public int updateOperationFile(int id, short checkStatus) {
    return operationFileMapper.updateOperationFile(id, checkStatus);
  }



}
