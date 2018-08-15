package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.MoveRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface MoveRecordMapper {
    //    @Insert("insert into move_record(factory,brand,src_b,src_c,dst_b,dst_c,date) values (#{factory},#{brand},#{src_b},#{src_c},#{dst_b},#{dst_c},#{date})")
//    Integer insert(@Param("factory") int factory, @Param("brand") String brand, @Param("src_b") int src_b, @Param("src_c") int src_c, @Param("dst_b") int dst_b, @Param("dst_c") int dst_c, @Param("date") Date date);
    @Insert("insert into move_record(factory,brand,src_b,src_c,dst_b,dst_c,date) values (#{factory},#{brand},#{src_b},#{src_c},#{dst_b},#{dst_c},#{date})")
    Integer insert(MoveRecord moveRecord);
}
