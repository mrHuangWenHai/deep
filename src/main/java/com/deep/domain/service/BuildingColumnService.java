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

    public List<BuildingColumn> selectAllBudildings(BuildingColumnExample buildingColumnExample)
    {
        return buildingColumnMapper.selectByExample(buildingColumnExample);
    }
    public List<BuildingColumn> selectAllCol(BuildingColumnExample buildingColumnExample) {
        return buildingColumnMapper.selectByExample(buildingColumnExample);
    }
    public List<BuildingColumn> selectAllBudildingsByFactoryId(BuildingColumnExample buildingColumnExample) {
        return buildingColumnMapper.selectByExample(buildingColumnExample);
    }

    public List<SheepBase> getColumnBase(long factory){
        return buildingColumnMapper.selectColumnBase(factory);
    }

    public List<SheepBase> getBuildingBase(long factory){
        return buildingColumnMapper.selectBuildingBase(factory);
    }

    public List<SheepBase> getTypeBase(long factory){
        return buildingColumnMapper.selectTypeBase(factory);
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

    public Integer findTypeOfSheep(Long factory, String type) {
        return buildingColumnMapper.findTypeOfSheep(factory, type);
    }
}
