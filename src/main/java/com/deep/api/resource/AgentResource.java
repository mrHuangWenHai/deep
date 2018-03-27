package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.AgentModel;
import com.deep.domain.service.AgentService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "agent")
public class AgentResource {
    @Resource
    private AgentService agentService;

    /**
     * 查找所有代理
     * @return
     */
    @Permit(modules = "dongxiang_factory_administrator", authorities = "select_agent")
    @GetMapping(value = "/")
    public Response agentLists() {
        List<AgentModel> agents = agentService.getAll();
        if (agents.size() <= 0) {
            return Responses.errorResponse("系统中暂时没有代理");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("allAgent", agents);
        data.put("number", agents.size());
        response.setData(data);
        return response;
    }

    /**
     * 查找一个代理
     * @param id
     * @return
     */
    @Permit(modules = "dongxiang_factory_administrator", authorities = "select_agent")
    @GetMapping(value = "/{id:\\d+}")
    public Response findOne(@PathVariable("id") Long id) {
        AgentModel agentModel = agentService.getOneAgent(id);
        if (agentModel == null) {
            return Responses.errorResponse("短代理不存在");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", agentModel);
        response.setData(data);
        return response;
    }

    /**
     * 删除一个代理
     * @param id
     */
    @Permit(modules = "dongxiang_factory_administrator", authorities = "delete_agent")
    @DeleteMapping(value = "/{id:\\d+}")
    public Response deleteOne(@PathVariable("id") Long id) {
        Long deleteID = agentService.deleteAgent(id);
        if (deleteID <= 0) {
            return Responses.errorResponse("删除代理失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", deleteID);
        response.setData(data);
        return response;
    }

    /**
     * 添加一个代理
     * @param agentModel
     * @param bindingResult
     * @return
     */
    @Permit(modules = "dongxiang_factory_administrator", authorities = "create_agent")
    @PostMapping(value = "/add")
    public Response addOne(@Valid AgentModel agentModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors())  {
            return Responses.errorResponse("添加代理失败, 验证错误!");
        } else {
            agentModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            agentModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            Long addID = agentService.addAgent(agentModel);
            if (addID <= 0) {
                return Responses.errorResponse("添加用户信息失败");
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("oneAgent", addID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 修改一个代理
     * @param agentModel 代理实体
     * @param bindingResult 错误信息提示
     * @param id 代理主键
     * @return
     */
    @Permit(modules = "dongxiang_factory_administrator", authorities = "update_agent")
    @PutMapping("/{id:\\d+}")
    public Response agentUpdate(@Valid AgentModel agentModel, @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())  {
            return Responses.errorResponse("修改代理失败");
        } else {
            agentModel.setId(id);
            agentModel.setGmtCreate(agentService.getOneAgent(new Long((long)id)).getGmtCreate());
            agentModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            Long updateID = agentService.updateAgent(agentModel);
            if (updateID <= 0) {
                return Responses.errorResponse("修改代理失败");
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("oneUser", updateID);
            response.setData(data);
            return response;
        }
    }
}
