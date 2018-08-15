package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.BuildingColumn;
import com.deep.domain.model.BuildingColumnExample;
import com.deep.domain.model.SheepBase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BuildingColumnMapper {
    int countByExample(BuildingColumnExample example);

    int deleteByExample(BuildingColumnExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuildingColumn record);

    int insertSelective(BuildingColumn record);

    List<BuildingColumn> selectByExampleWithRowbounds(BuildingColumnExample example, RowBounds rowBounds);

    List<BuildingColumn> selectByExample(BuildingColumnExample example);

    BuildingColumn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuildingColumn record, @Param("example") BuildingColumnExample example);

    int updateByExample(@Param("record") BuildingColumn record, @Param("example") BuildingColumnExample example);

    int updateByPrimaryKeySelective(BuildingColumn record);

    int updateByPrimaryKey(BuildingColumn record);

    @Select("select count(col) from building_column where factory = #{factory} and building = #{building}")
    Integer getColumnNum(@Param("factory") long factory, @Param("building") int building);

    @Select("SELECT building,col,building_column.type,count(bc) as sheepNum\n" +
            "FROM building_column\n" +
            "INNER JOIN sheep_info\n" +
            "ON building_column.id = sheep_info.bc AND building_column.factory = #{factory}\n" +
            "GROUP BY bc")
    List<SheepBase> selectColumnBase(@Param("factory") long factory);

    @Select("SELECT building,count(bc) as sheepNum\n" +
            "FROM building_column\n" +
            "INNER JOIN sheep_info\n" +
            "ON building_column.id = sheep_info.bc AND building_column.factory = #{factory}\n" +
            "GROUP BY building")
    List<SheepBase> selectBuildingBase(@Param("factory") long factory);

    @Select("SELECT building_column.type,count(bc) as sheepNum\n" +
            "FROM building_column\n" +
            "INNER JOIN sheep_info\n" +
            "ON building_column.id = sheep_info.bc AND building_column.factory = #{factory}\n" +
            "GROUP BY building_column.type")
    List<SheepBase> selectTypeBase(@Param("factory") long factory);

    @Insert("<script>\n" +
            "insert into building_column (factory,building,col)\n" +
            "<foreach collection='buildingColumns' item='buildingColumn' open='VALUES' separator=',' close=';'>\n" +
            "(#{buildingColumn.factory},#{buildingColumn.building},#{buildingColumn.col})\n" +
            "</foreach>\n" +
            "</script>")
    Integer batchInsertBuildingColumn(@Param("buildingColumns") List<BuildingColumn> buildingColumns);

    @Update("update sheep_info set bc=(select id from building_column where building=#{building} and col=#{col} and factory=#{factory}) where brand=#{brand}")
    Integer changeBuildingColumn(@Param("factory") Long factory, @Param("brand") String brand, @Param("building") int building, @Param("col") int col);

}