package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.RepellentPlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface RepellentPlanMapper {
    void setRepellentPlanModel(@Param("factoryNum")BigInteger factoryNum,
                               @Param("crowdNum") String crowdNum,
                               @Param("repellentEartag") String repellentEartag,
                               @Param("repellentTime") String repellentTime,
                               @Param("repellentName") String repellentName,
                               @Param("repellentWay") String repellentWay,
                               @Param("repellentQuality") String repellentQuality,
                               @Param("operator") String operator,
                               @Param("remark") String remark,
                               @Param("isPass1") String  isPass1,
                               @Param("isPass2") String isPass2,
                               @Param("gmtCreate") Timestamp gmtCreate);

    List<RepellentPlanModel> getRepellentPlanModel(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel,
                                                   RowBounds bounds);

    RepellentPlanModel getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(@Param("factoryNum")BigInteger factoryNum,
                                                                                         @Param("repellentTime")String repellentTime,
                                                                                         @Param("repellentName")String repellentName);


    List<RepellentPlanModel> getRepellentPlanModelByProfessor(@Param("isPass1") Integer isPass1,
                                                              RowBounds bounds);
    List<RepellentPlanModel> getRepellentPlanModelBySupervisor(@Param("isPass2") Integer isPass2,
                                                               RowBounds bounds);

    RepellentPlanModel getRepellentModelByid(@Param("id") BigInteger id);


    int deleteRepellentPlanModelByid(@Param("id") BigInteger id);

    int updateRepellentPlanModelByProfessor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

    int updateRepellentPlanModelBySupervisor(@Param("repellentPlanModel") RepellentPlanModel repellentPlanModel);

}
