package com.deep.api.resource.SheepInfo;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.Saled;
import com.deep.domain.model.SaledExample;
import com.deep.domain.service.SaledService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created By LeeBoom On 2018/7/26 19:57
 */
@RestController
@RequestMapping(value = "/s")
public class SaledResource {

    @Resource
    private SaledService saledService;

    @GetMapping("/ss")
    public Response getSaledSheep(@RequestBody FactoryModel factoryModel)
    {
        Integer facotoryId = factoryModel.getId().intValue();
        SaledExample saledExample  = new SaledExample();
        SaledExample.Criteria criteria = saledExample.createCriteria();
        criteria.andSrcEqualTo(facotoryId);
        List<Saled> saleds = saledService.selectSrc(saledExample);

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("saledSheep",saleds);
        response.setData(data);
        return response;
    }

    @GetMapping("/bs")
    public Response getBuySheep(@RequestBody FactoryModel factoryModel)
    {
        Integer facotoryId = factoryModel.getId().intValue();
        SaledExample saledExample  = new SaledExample();
        SaledExample.Criteria criteria = saledExample.createCriteria();
        criteria.andDstEqualTo(facotoryId);
        List<Saled> buy = saledService.selectSrc(saledExample);

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("buy",buy);
        response.setData(data);
        return response;
    }
}
