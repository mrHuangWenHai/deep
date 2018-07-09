package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.HumDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by zhongrui on 18-4-7.
 */
@Mapper
public interface HumDataMapper {

    void setHumData(@Param("humDataModel") HumDataModel humDataModel);

    HumDataModel getHumDataLatest();
    HumDataModel getHumDataByid(@Param("id") int id);
    List<HumDataModel> getHumDataBytime(@Param("timeStart") String timeStart,
                                        @Param("timeEnd") String timeEnd);

    int deleteHumData();
}
