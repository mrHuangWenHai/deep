package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.BuildingColumn;
import com.deep.domain.model.BuildingColumnExample;
import com.deep.domain.service.BuildingColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/buildings/{id}")
    public Response getBuildings(@PathVariable(value = "id")Integer id)
    {
        BuildingColumnExample buildingColumnExample = new BuildingColumnExample();
        buildingColumnExample.setDistinct(true);
        BuildingColumnExample.Criteria criteria = buildingColumnExample.createCriteria();
        criteria.andFactoryEqualTo(id.longValue());
        List<BuildingColumn>buildings =  buildingColumnService.selectAllBudildingsByFactoryId(buildingColumnExample);

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("buildings",buildings);
        response.setData(data);
        return response;


    }

}
