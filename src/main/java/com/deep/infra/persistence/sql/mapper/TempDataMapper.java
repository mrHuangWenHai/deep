package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.TempDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by zhongrui on 18-4-7.
 */

@Mapper
public interface TempDataMapper {

    void setTempData(@Param("tempDataModel") TempDataModel tempDataModel);

    TempDataModel getTempDataLatest();
    TempDataModel getTempDataByid(@Param("id") int id);
    List<TempDataModel> getTempDataBytime(@Param("timeStart") String timeStart,
                                          @Param("timeEnd") String timeEnd);

    int deleteTempData();
}
