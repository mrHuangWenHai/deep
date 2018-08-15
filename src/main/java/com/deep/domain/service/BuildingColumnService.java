package com.deep.domain.service;

import com.deep.domain.model.BuildingColumn;
import com.deep.domain.model.BuildingColumnExample;
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
    public List<BuildingColumn> selectAllCol(BuildingColumnExample buildingColumnExample)
    {
        return buildingColumnMapper.selectByExample(buildingColumnExample);
    }

}
