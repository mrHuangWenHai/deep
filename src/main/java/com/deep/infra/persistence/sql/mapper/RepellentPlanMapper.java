package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.RepellentPlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Mapper
public interface RepellentPlanMapper {
    void setRepellentPlanModel(@Param("repellentPlanModel")RepellentPlanModel repellentPlanModel);

    List<RepellentPlanModel> getRepellentPlanModel(@Param("factoryNum")BigInteger factoryNum,
                                                   @Param("crowdNum") String crowdNum,
                                                   @Param("repellentEartag") String repellentEartag,
                                                   @Param("repellentTimeStart") String repellentTimeStart,
                                                   @Param("repellentTimeEnd") String repellentTimeEnd,
                                                   @Param("repellentName") String repellentName,
                                                   @Param("repellentWay") String repellentWay,
                                                   @Param("repellentQuality") String repellentQuality,
                                                   @Param("operator") String operator,
                                                   @Param("professor") String professor,
                                                   @Param("supervisor") String supervisor,
                                                   @Param("remark") String remark,
                                                   @Param("isPass1") String  isPass1,
                                                   @Param("unpassReason1") String unpassReason1,
                                                   @Param("isPass2") String isPass2,
                                                   @Param("unpassReason2") String unpassReason2);

    RepellentPlanModel getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(@Param("factoryNum")BigInteger factoryNum,
                                                                                         @Param("repellentTime")String repellentTime,
                                                                                         @Param("repellentName")String repellentName);
    int deleteRepellentPlanModelByfactoryNum(@Param("factoryNum")BigInteger factoryNum);
    int deleteRepellentPlanModelByfactoryNumAndrepellentTime(@Param("factoryNum")BigInteger factoryNum,
                                                             @Param("repellentTime")String repellentTime);


}
