package com.deep.domain.service.SheepInfo;

import com.deep.api.request.BCRequest;
import com.deep.api.response.BuildingColResponse;
import com.deep.domain.model.sheepInfo.BuildingColumnModel;
import com.deep.infra.persistence.sql.mapper.sheepInfo.BuildingColMapper;
import com.deep.infra.persistence.sql.mapper.sheepInfo.SheepInformationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BuildingFactoryService {
    @Resource
    private BuildingColMapper buildingColMapper;
    @Resource
    private SheepInformationMapper sheepInformationMapper;

    // 获取所有的数据信息
    public List<BuildingColResponse> getBuildingAndColumn(Long factory) {
        return buildingColMapper.selectBuildingAndColumn(factory);
    }

    // 获取所有的栏栋信息
    public List<BuildingColumnModel> getAllBuildingAndColumn(Long factory) {
        return buildingColMapper.selectAllBuildingAndColumn(factory);
    }

    // 添加一个栏栋
    public Long insertBuildingAndColumn(BuildingColumnModel buildingColumnModel) {
        List<BuildingColumnModel> models = new ArrayList<>();
        for (int i = 1; i <= buildingColumnModel.getCol(); i++) {
            BuildingColumnModel model = new BuildingColumnModel();
            model.setFactory(buildingColumnModel.getFactory());
            model.setBuilding(buildingColumnModel.getBuilding());
            model.setCol(i);
            models.add(model);
        }
        return buildingColMapper.insertBuildingColumn(models);
    }

    // 编辑一个栏栋
    public Boolean updateBuildingAndColumn(BuildingColumnModel buildingColumnModel) {
        Integer max = buildingColMapper.selectTheBigOne(buildingColumnModel.getFactory(), buildingColumnModel.getBuilding());
        List<BuildingColumnModel> models = new ArrayList<>();
        if (max < buildingColumnModel.getCol()) {
            for (int i = max+1; i <= buildingColumnModel.getCol(); i++) {
                BuildingColumnModel model = new BuildingColumnModel();
                model.setCol(i);
                model.setBuilding(buildingColumnModel.getBuilding());
                model.setFactory(buildingColumnModel.getFactory());
                models.add(model);
            }
            return buildingColMapper.insertBuildingColumn(models) > 0;
        }
        return false;
    }

    // 修改羊的栏栋
    public Long moveSheep(BCRequest bcRequest, Long id) {
        // 首先找到待搬迁栏栋
        Long waiting = buildingColMapper.selectBuildingFactoryId(bcRequest);
        if (waiting == null) {
            return null;
        }
        return sheepInformationMapper.updateSheepInformation(waiting, id);
    }

    // 获取某个羊场的栏栋信息
    public Set<Integer> getBuildings(Long factory) {
        return buildingColMapper.selectFactoryBuilding(factory);
    }

    // 获取某个羊场某个栋的所有栏
    public List<Integer> getCols(Long factory, Integer building) {
        return buildingColMapper.selectFactoryBuildingColumn(factory, building);
    }

    // 根据羊场栋号和栏号获取栏栋的id
    public Long findIdByBuildingAndCol(Long factory, Integer building, Integer col) {

        return buildingColMapper.findIdByBuildingAndCol(factory, building, col);
    }
}
