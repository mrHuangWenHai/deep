package com.deep.infra.persistence.sql.mapper.sheepInfo;

import com.deep.api.response.DeadSheepInformationResponse;
import com.deep.api.response.SheepInformationResponse;
import com.deep.domain.model.sheepInfo.SheepInformationModel;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface SheepInformationMapper {
    /**
     * fuck Laowang, playing taoLu, maLeGeJi
     */
    @Insert("insert into sheep_information(" +
            "factory, " +
            "building_column, " +
            "type, " +
            "trademark_ear_tag, " +
            "immune_ear_tag) values(" +
            "#{factory}, " +
            "#{buildingColumn}, " +
            "#{type}, " +
            "#{trademarkEarTag}, " +
            "#{immuneEarTag})")
    public Long insertSheepInformation(SheepInformationModel sheepInformationModel);

    @Update("update sheep_information set " +
            "building_column = #{buildingColumn} " +
            " where id = #{id}")
    public Long updateSheepInformation(@Param("buildingColumn") Long buildingColumn, @Param("id") Long id);

    @Update("update sheep_information set " +
            "sale = #{flag} where id = #{id}")
    public Long updateSaleSheepInformation(@Param("id") Long id, @Param("flag") Byte flag);

    @Update("update sheep_information set " +
            "dead = #{dead}," +
            "reason = #{reason}," +
            "method = #{method}," +
            "date = #{date} " +
            "where id = #{id}")
    public Long updateDeadSheepInformation(@Param("dead") Byte dead, @Param("reason") String reason, @Param("method") String method, @Param("date")Timestamp date, @Param("id") Long id);

    @Select("select * from sheep_information where factory = #{factory} and dead = 1 limit #{start}, #{size}")
    @Results ({
            @Result(property = "trademarkEarTag", column = "trademark_ear_tag"),
            @Result(property = "reason", column = "reason"),
            @Result(property = "method", column = "method"),
            @Result(property = "date", column = "date")
    })
    public List<DeadSheepInformationResponse> selectDeadSheep(@Param("factory") Long factory, @Param("start") Long start, @Param("size") Byte size);

    @Select("select count(*) from sheep_information where factory = #{factory} and dead = 1")
    public Long countDeadSheep(Long factory);

    @Select("select sheep_information.trademark_ear_tag, sheep_information.immune_ear_tag, building_factory.building, building_factory.col, sheep_information.id from sheep_information, building_factory where sheep_information.factory = #{factory} and sheep_information.dead != 1 and sheep_information.sale = 0 and sheep_information.building_column = building_factory.id limit #{start}, #{size}")
    @Results ({
            @Result(property = "trademarkEarTag", column = "trademark_ear_tag"),
            @Result(property = "immuneEarTag", column = "immune_ear_tag"),
            @Result(property = "building", column = "building"),
            @Result(property = "col", column = "col"),
            @Result(property = "id", column = "id")
    })
    public List<SheepInformationResponse> selectAllSheep(@Param("factory") Long factory, @Param("start") Long start, @Param("size") Byte size);

    @Select("select * from sheep_information where id = #{id}")
    @Results ({
            @Result(property = "trademarkEarTag", column = "trademark_ear_tag"),
            @Result(property = "immuneEarTag", column = "immune_ear_tag"),
            @Result(property = "buildingColumn", column = "building_column"),
            @Result(property = "type", column = "type"),
            @Result(property = "factory", column = "factory")

    })
    public SheepInformationModel getOneSheep(Long id);

    @Select("select count(*) from sheep_information, building_factory where sheep_information.factory = #{factory} and sheep_information.dead != 1 and sheep_information.sale = 0 and sheep_information.building_column = building_factory.id")
    public Long countAllSheep(Long factory);
}
