package com.deep.infra.persistence.sql.mapper.product_chain;

import com.deep.api.request.OperationCoditionRequest;
import com.deep.domain.model.OperationFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by huangwenhai on 2018/4/17.
 */

@Mapper
public interface OperationFileMapper {

  List<OperationFile> getOperationFile(@Param("operationCoditionRequest")OperationCoditionRequest operationCoditionRequest);

  OperationFile getOperationFileById(@Param("id")int id);

  List<OperationFile> getOperationFileByFactoryNum(@Param("factoryNum")BigInteger factoryNum);

  int addOperationFile(@Param("operationFile")OperationFile operationFile);

  int getOperationFileCount(@Param("factoryNum")BigInteger factoryNum);

  Long getOperationFileOperator(@Param("id") Long id);

  int updateCheckStatus(@Param("id")int id,
                        @Param("ispassCheck") short checkStatus,
                        @Param("unpassReason") String unpassReason,
                        @Param("professorName") String professorName,
                        @Param("professorId") int professorId);

  int updateSupStatus(@Param("id")int id,
                      @Param("supStatus") short supStatus,
                      @Param("supervisorName") String supervisorName,
                      @Param("supervisorId") int supervisorId);


  int updateOperationFileByOperationFile(@Param("operationFile")OperationFile operationFile);

  int deleteOperationFile(@Param("id")int id);
}
