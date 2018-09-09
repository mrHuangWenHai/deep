package com.deep.infra.persistence.sql.mapper.sheepInfo;

import com.deep.api.request.BCRequest;
import com.deep.api.response.BuildingColResponse;
import com.deep.domain.model.sheepInfo.BuildingColumnModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

@Mapper
public interface BuildingColMapper {

    @Insert("<script>\n" +
        "insert into  building_factory(factory,building,col)\n" +
        "<foreach collection='buildingColumnModels' item='buildingColumnModel' open='VALUES' separator=',' close=';'>\n" +
        "(#{buildingColumnModel.factory}, #{buildingColumnModel.building}, #{buildingColumnModel.col})\n" +
        "</foreach>\n" +
        "</script>")
    Long insertBuildingColumn(@Param("buildingColumnModels") List<BuildingColumnModel> buildingColumnModels);

    @Select("select building_factory.id, building_factory.building, building_factory.col, " +
            "count(0) as number " +
            "from building_factory join sheep_information " +
            "on sheep_information.building_column = building_factory.id and sheep_information.factory = #{factory} " +
            "group by sheep_information.building_column " +
            "having count(sheep_information.building_column) " +
            "order by building_factory.building, building_factory.col asc"
    )
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "building", column = "building"),
            @Result(property = "col", column = "col"),
            @Result(property = "number", column = "number")
    })
    List<BuildingColResponse> selectBuildingAndColumn(Long factory);

    @Select("select * from building_factory where factory = #{factory} order by building, col asc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "building", column = "building"),
            @Result(property = "col", column = "col"),
            @Result(property = "factory", column = "factory")
    })
    List<BuildingColumnModel> selectAllBuildingAndColumn(Long factory);

    @Select("select building from building_factory where factory = #{factory} order by building asc")
    Set<Integer> selectFactoryBuilding(Long factory);

    @Select("select col from building_factory where factory = #{factory} and building = #{building} order by col asc")
    List<Integer> selectFactoryBuildingColumn(@Param("factory") Long factory, @Param("building") Integer building);

    @Select("select max(col) as max from building_factory where factory = #{factory} and building = #{building}")
    Integer selectTheBigOne(@Param("factory") Long factory, @Param("building") Integer building);

    @Select("select id from building_factory where factory = #{factory} and building = #{building} and col = #{colNum}")
    Long selectBuildingFactoryId(BCRequest bcRequest);

    @Select("select id from building_factory where factory = #{factory} and building = #{building} and col = #{col}")
    Long findIdByBuildingAndCol(@Param("factory") Long factory, @Param("building") Integer building, @Param("col") Integer col);
}
