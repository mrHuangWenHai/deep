package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.BuildingColumn;
import com.deep.domain.model.BuildingColumnExample;
import com.deep.domain.model.SheepInfo;
import com.deep.domain.model.SheepInfoExample;
import com.deep.domain.service.BuildingColumnService;
import com.deep.domain.service.SheepInfoService;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created By LeeBoom On 2018/7/22 23:14
 */
@RestController
@RequestMapping(value = "/bc")
public class BuildingColumnResource {
    private static final Logger loggr = LoggerFactory.getLogger(BuildingColumnResource.class);

    @Resource
    BuildingColumnService buildingColumnService;

    @Resource
    SheepInfoService sheepInfoService;


    /**
     * 查询本厂的所有栋号
     * @param factoryId 本厂编号
     * @return  本厂所有的栋号(已去重)
     */
    @GetMapping(value = "/{factoryId}/buildings")
    public Response getBuildings(@PathVariable(value = "factoryId")Integer factoryId)
    {
        BuildingColumnExample buildingColumnExample = new BuildingColumnExample();
        buildingColumnExample.setDistinct(true);
        BuildingColumnExample.Criteria criteria = buildingColumnExample.createCriteria();
        criteria.andFactoryEqualTo(factoryId.longValue());
        List<BuildingColumn>buildings =  buildingColumnService.selectAllBudildings(buildingColumnExample);

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("buildings",buildings);
        response.setData(data);
        return response;
    }

    /**
     *
     * @param factoryId 本厂编号
     * @param buildingId  栋号
     * @return
     */
    @GetMapping(value = "/{factoryId}/buildings/{buildingId}")
    public Response getCol(@PathVariable(value = "factoryId")Integer factoryId,
                           @PathVariable(value = "buildingId")Integer buildingId)
    {
        BuildingColumnExample buildingColumnExample = new BuildingColumnExample();
        BuildingColumnExample.Criteria criteria = buildingColumnExample.createCriteria();
        criteria.andFactoryEqualTo(factoryId.longValue());
        criteria.andBuildingEqualTo(buildingId);
        List<BuildingColumn> columns =  buildingColumnService.selectAllCol(buildingColumnExample);

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        response.setData(data);
        return response;
    }
    /**
     * 查询本厂内栏栋号为空的羊
     */
    @GetMapping(value = "/{factoryId}/nullbcid")
    public Response getNullBcIdSheep(@PathVariable(value = "factoryId")Integer factoryId)
    {
        SheepInfoExample sheepInfoExample = new SheepInfoExample();
        SheepInfoExample.Criteria criteria = sheepInfoExample.createCriteria();
        criteria.andFactoryEqualTo(factoryId);
        criteria.andBcIsNull();
        List<SheepInfo> sheepInfos = sheepInfoService.selectNullBcId(sheepInfoExample);

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("sheepInfos",sheepInfos);
        response.setData(data);
        return response;
    }





}
