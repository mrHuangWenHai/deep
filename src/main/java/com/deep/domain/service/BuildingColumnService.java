package com.deep.domain.service;

import com.deep.domain.model.BuildingColumn;
import com.deep.domain.model.BuildingColumnExample;
import com.deep.domain.model.SheepBase;
import com.deep.infra.persistence.sql.mapper.BuildingColumnMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By LeeBoom On 2018/7/22 23:01
 */
@Service
public class BuildingColumnService {

    @Resource
    private BuildingColumnMapper buildingColumnMapper;

    public List<BuildingColumn> selectAllBudildingsByFactoryId(BuildingColumnExample buildingColumnExample) {
        List<BuildingColumn> buildings = buildingColumnMapper.selectByExample(buildingColumnExample);
        return buildings;
    }

    public List<SheepBase> getColumnBase(long factory){
        List<SheepBase> sheepBases = buildingColumnMapper.selectColumnBase(factory);
        return sheepBases;
    }

    public List<SheepBase> getBuildingBase(long factory){
        List<SheepBase> sheepBases = buildingColumnMapper.selectBuildingBase(factory);
        return sheepBases;
    }

    public List<SheepBase> getTypeBase(long factory){
        List<SheepBase> sheepBases = buildingColumnMapper.selectTypeBase(factory);
        return sheepBases;
    }

    public Integer batchInsert(List<BuildingColumn> list){
        return buildingColumnMapper.batchInsertBuildingColumn(list);
    }

    public Integer getColumnNum(long factory, int building){
        return buildingColumnMapper.getColumnNum(factory, building);
    }

    public Integer changeBuildingColumn(long factory, String brand, int building, int col){
        return buildingColumnMapper.changeBuildingColumn(factory, brand, building, col);
    }



}
