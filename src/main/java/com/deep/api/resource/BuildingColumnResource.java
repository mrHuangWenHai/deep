package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.*;
import com.deep.domain.service.BuildingColumnService;
import com.deep.domain.service.SheepInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deep.domain.service.MoveRecordService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created By LeeBoom On 2018/7/22 23:14
 */
@RestController
@Service
@RequestMapping(value = "/bc")
public class BuildingColumnResource {
    private static final Logger loggr = LoggerFactory.getLogger(BuildingColumnResource.class);

    @Resource
    private BuildingColumnService buildingColumnService;
    @Resource
    private MoveRecordService moveRecordService;

    @Resource
    private SheepInfoService sheepInfoService;


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
        HashMap<String, Object> data = new HashMap<>();
        data.put("buildings", buildings);
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

    @PostMapping("/batchCreateBC")
    public Response batchCreateBuildingColumn(long factory, int building, int colNum) {
        List<BuildingColumn> list = new ArrayList<>();
        Integer maxNum = buildingColumnService.getColumnNum(factory, building);
        Integer nowNum;
        for (int i = maxNum + 1; i <= colNum; i++) {
            BuildingColumn buildingColumn = new BuildingColumn();
            buildingColumn.setFactory(factory);
            buildingColumn.setBuilding(building);
            buildingColumn.setCol(i);
            list.add(buildingColumn);
        }
        if (list.size() > 0)
            nowNum = buildingColumnService.batchInsert(list);
        else
            return Responses.errorResponse("欲建栏数小于已有栏数");
        return nowNum == colNum - maxNum ? Responses.successResponse() : Responses.errorResponse("insert error");
    }

    @GetMapping("/getSheepBase/{factory}")
    public Response getSheepBase(@PathVariable("factory") Long factory) {
        HashMap<String, List<SheepBase>> map = new HashMap<>();
        List<SheepBase> columnBase = buildingColumnService.getColumnBase(factory);
        map.put("columnBase", columnBase);
        List<SheepBase> buildingBase = buildingColumnService.getBuildingBase(factory);
        map.put("buildingBase", buildingBase);
        List<SheepBase> typeBase = buildingColumnService.getTypeBase(factory);
        map.put("typeBase", typeBase);
        return Responses.successResponse(map);
    }

    @PostMapping("/changeBC")
    public Response changeBC(@RequestBody MoveRecord moveRecord) {
        moveRecord.setDate(new Date());
        moveRecordService.insert(moveRecord);
        return buildingColumnService.changeBuildingColumn(moveRecord.getFactory(), moveRecord.getBrand(), moveRecord.getDst_b(), moveRecord.getDst_c()) == 1 ? Responses.successResponse() : Responses.errorResponse("insert error");
    }
}
