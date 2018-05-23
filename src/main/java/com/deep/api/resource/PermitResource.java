package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.PermitModel;
import com.deep.domain.service.PermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/permit")
public class PermitResource {
    private final Logger logger = LoggerFactory.getLogger(PermitResource.class);

    @Resource
    private PermitService permitService;

    /**
     * 展示所有的权限,不更新新的页面
     * @return 展示所有权限的json格式
     */
    @Permit(authorities = "query_permit")
    @GetMapping(value = "/")
    public Response permitLists() {

        logger.info("invoke permitLists, url is /permit/");

        List<PermitModel> permitModels = permitService.getAll();
        if (permitModels.size() <= 0) {
            return Responses.errorResponse("无权限信息");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("allPermit", permitModels);
        data.put("nubmer", permitModels.size());
        response.setData(data);
        return response;
    }

    /**
     * 添加一个权限
     * @param permitModel 权限模型
     * @param bindingResult 相关信息
     * @return response信息
     */
    @Permit(authorities = "")
    @PostMapping(value = "/")
    public Response addRole(@Valid PermitModel permitModel, BindingResult bindingResult) {
        logger.info("invoke addRole{}, url is permit/add", permitModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("添加权限出错,请检查网络后重试");
        } else {
            permitModel.setPermitId(permitModel.getPermitId());
            permitModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            permitModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            Long addID = permitService.addPermit(permitModel);
            if (addID <= 0) {
                return Responses.errorResponse("添加失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", addID);
            response.setData(data);
            return response;
        }
    }
}
