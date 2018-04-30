package com.deep.api.resource;

import com.deep.api.request.ModuleFindRequest;
import com.deep.api.request.ModuleUpdateRequest;
import com.deep.api.response.Response;
import com.deep.domain.service.ModuleUpdateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用于专家/监督员 查询 更新
 * create by zhongrui on 18-4-29.
 */
@RestController
@RequestMapping(value = "/modules")
public class ModuleUpdateResource {

    @Resource
    private ModuleUpdateService moduleUpdateService;

    /**
     * 需要身份验证
     * 集合查询
     * request.mark = 1 : dis 模块
     * request.mark = 2 : imm 模块
     * request.mark = 3 : rep 模块
     * @param request 查询请求
     * @return 查询结果
     */
    @RequestMapping(value = "/find" , method = RequestMethod.POST)
    public Response find(@RequestBody ModuleFindRequest request){
        return this.moduleUpdateService.findByChoice(request);
    }

    /**
     * 需要身份验证
     * 模块更新
     * request.mark = 1 : dis 模块
     * request.mark = 2 : imm 模块
     * request.mark = 3 : rep 模块
     * @param request 更新请求
     * @return 更新结果
     */
    @RequestMapping(value = "/update" , method = RequestMethod.PATCH)
    public Response update(@RequestBody ModuleUpdateRequest request){
        return this.moduleUpdateService.updateByChoice(request);
    }
}
