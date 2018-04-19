package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.NH3DataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by zhongrui on 18-4-7.
 */

@Mapper
public interface NH3DataMapper {

    void setNH3Data(@Param("nh3DataModel") NH3DataModel nh3DataModel);

    NH3DataModel getNH3DataLatest();
    NH3DataModel getNH3DataByid(@Param("id") int id);
    List<NH3DataModel> getNH3DataBytime(@Param("timeStart") String timeStart,
                                        @Param("timeEnd") String timeEnd);

    int deleteNH3Data();
}
