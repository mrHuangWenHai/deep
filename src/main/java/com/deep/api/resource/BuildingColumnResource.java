package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.BuildingColumn;
import com.deep.domain.model.BuildingColumnExample;
import com.deep.domain.model.MoveRecord;
import com.deep.domain.model.SheepBase;
import com.deep.domain.service.BuildingColumnService;
import com.deep.domain.service.MoveRecordService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    BuildingColumnService buildingColumnService;
    @Resource
    MoveRecordService moveRecordService;

    @GetMapping(value = "/buildings/{id}")
    public Response getBuildings(@PathVariable(value = "id") Integer id) {
        BuildingColumnExample buildingColumnExample = new BuildingColumnExample();
        buildingColumnExample.setDistinct(true);
        BuildingColumnExample.Criteria criteria = buildingColumnExample.createCriteria();
        criteria.andFactoryEqualTo(id.longValue());
        List<BuildingColumn> buildings = buildingColumnService.selectAllBudildingsByFactoryId(buildingColumnExample);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("buildings", buildings);
        response.setData(data);
        return response;
    }

//    @PostMapping("/insertBC")
//    public Response insertBuildingColumn(BuildingColumn buildingColumn) {
//        return buildingColumnService.insert(buildingColumn) == 1 ? Responses.successResponse() : Responses.errorResponse("insert error");
//    }

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
