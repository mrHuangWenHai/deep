package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.CO2DataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by zhongrui on 18-4-7.
 */

@Mapper
public interface CO2DataMapper {

    void setCO2Data(@Param("co2DataModel") CO2DataModel co2DataModel);

    CO2DataModel getCO2DataLatest();
    CO2DataModel getCO2DataByid(@Param("id") long id);
    List<CO2DataModel> getCO2DataBytime(@Param("timeStart") String timeStart,
                                        @Param("timeEnd") String timeEnd);


    int deleteCO2Data();
}
