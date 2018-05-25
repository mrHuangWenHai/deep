package com.deep.infra.persistence.sql.mapper;


import com.deep.api.request.DiagnosisRequest;
import com.deep.domain.model.DiagnosisPlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import java.util.List;

@Mapper
public interface DiagnosisPlanMapper {




    int deleteByPrimaryKey(Integer id);

    int insert(@Param("diagnosisPlanModel") DiagnosisPlanModel diagnosisPlanModel);


    List<DiagnosisPlanModel> selectDiagnosisPlanModelByDiagnosisRequest(@Param("diagnosisRequest") DiagnosisRequest diagnosisRequest);

    DiagnosisPlanModel selectByPrimaryKey(@Param("id") Integer id);

    List<DiagnosisPlanModel> professorFindPlan(long factoryNum,
                                             int ispassSup,
                                             RowBounds rowBounds);

    int checkDiagnosisPlanModelById(@Param("id") int id,
                                    @Param("ispassCheck") short ispassCheck,
                                    @Param("professorId") int professorId,
                                    @Param("name") String name);

    int supCheckDiagnosisPlanModelById(@Param("id") int id,
                                       @Param("ispassSup") short ispassSup,
                                       @Param("upassReason") String upassReason,
                                       @Param("supervisorId") int supervisorId,
                                       @Param("name") String name);

    int updateDiagnosisPlanModel(@Param("diagnosisPlanModel") DiagnosisPlanModel diagnosisPlanModel);


}