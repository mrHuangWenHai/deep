package com.deep.infra.persistence.sql.mapper.sheepInfo;

import com.deep.domain.model.sheepInfo.SalesRecordModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SalesRecordMapper {
    /**
     * 添加一条销售记录
     * @param salesRecordModel 销售记录
     * @return 返回处理的结果
     */
    @Insert("insert into sales_record(" +
            "start_factory, " +
            "end_factory, " +
            "start_name, " +
            "end_name, " +
            "trademark_ear_tag, " +
            "immune_ear_tag" +
            ") values(" +
            "#{startFactory}, " +
            "#{endFactory}, " +
            "#{startName}," +
            "#{endName}, " +
            "#{trademarkEarTag}, " +
            "#{immuneEarTag}" +
            ")")
    Long insertRecord(SalesRecordModel salesRecordModel);

    /**
     * 查看售出的羊只
     * @param factory 羊场编号
     * @return 返回售出的羊只信息
     */
    @Select("select * from sales_record where start_factory = #{factory} limit #{start}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startFactory", column = "start_factory"),
            @Result(property = "endFactory", column = "end_factory"),
            @Result(property = "startName", column = "start_name"),
            @Result(property = "endName", column = "end_name"),
            @Result(property = "trademarkEarTag", column = "trademark_ear_tag"),
            @Result(property = "immuneEarTag", column = "immune_ear_tag"),
            @Result(property = "salesTime", column = "sales_time")
    })
    List<SalesRecordModel> selectSaleRecords(@Param("factory") Long factory, @Param("start") Long start, @Param("size") Byte size);

    /**
     * 查看售出的羊只数目
     * @param factory 羊场编号
     * @return 售出羊只数目
     */
    @Select("select count(*) from sales_record where start_factory = #{factory}")
    Long selectSaleRecordsNumber(Long factory);

    /**
     * 查看买入的羊只
     * @param factory 羊场编号
     * @return 返回买入的羊只信息
     */
    @Select("select * from sales_record where end_factory = #{factory} limit #{start}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startFactory", column = "start_factory"),
            @Result(property = "endFactory", column = "end_factory"),
            @Result(property = "startName", column = "start_name"),
            @Result(property = "endName", column = "end_name"),
            @Result(property = "trademarkEarTag", column = "trademark_ear_tag"),
            @Result(property = "immuneEarTag", column = "immune_ear_tag"),
            @Result(property = "salesTime", column = "sales_time")
    })
    List<SalesRecordModel> selectBuyRecords(@Param("factory") Long factory, @Param("start") Long start, @Param("size") Byte size);

    /**
     * 查看已经买入的羊只数目
     * @param factory 羊场编号
     * @return 售出羊只数目
     */
    @Select("select count(*) from sales_record where end_factory = #{factory}")
    Long selectBuyRecordsNumber(Long factory);
}
