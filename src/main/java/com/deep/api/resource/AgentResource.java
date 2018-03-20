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
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("allAgent", agentService.getAll());
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
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", agentService.getOneAgent(id));
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
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", agentService.deleteAgent(id));
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
            return Responses.errorResponse("添加代理失败");
        } else {
            agentModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            agentModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            agentModel.setAgentRank(agentModel.getAgentRank());
            agentModel.setAgentName(agentModel.getAgentName());
            agentModel.setAgentArea(agentModel.getAgentArea());
            agentModel.setAgentFather(agentModel.getAgentFather());

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("oneAgent", agentService.addAgent(agentModel));
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

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("oneUser", agentService.updateAgent(agentModel));
            response.setData(data);
            return response;
        }
    }
}
