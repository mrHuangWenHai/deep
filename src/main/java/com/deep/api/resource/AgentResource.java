package com.deep.api.resource;

import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.AgentModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.AgentService;
import com.deep.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.ws.rs.POST;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "agent")
public class AgentResource {

    private final Logger logger = LoggerFactory.getLogger(AgentResource.class);

    @Resource
    private AgentService agentService;

    /**
     * 查找所有代理
     * @return
     */
    @Permit(modules = "dongxiang_factory_administrator", authorities = "select_agent")
    @GetMapping(value = "/")
    public Response agentLists() {
        logger.info("invoke agentLists, url is agent/");
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
    @GetMapping(value = "/{id}")
    public Response findOne(@PathVariable("id") String id) {
        logger.info("invoke findOne{}, url is agent/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        AgentModel agentModel = agentService.getOneAgent(uid);
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
    @DeleteMapping(value = "/{id}")
    public Response deleteOne(@PathVariable("id") String id) {
        logger.info("invoke deleteOne{}, url is agent/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Long deleteID = agentService.deleteAgent(uid);
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
    public Response addOne(@Valid @RequestBody AgentModel agentModel, BindingResult bindingResult) {
        logger.info("invoke addOne{}, url is agent/add", agentModel);
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
    @PutMapping("/{id}")
    public Response agentUpdate(@Valid @RequestBody AgentModel agentModel, @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke agentUpdate{}, url is agent/{id}", agentModel, id);
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        if (bindingResult.hasErrors())  {
            return Responses.errorResponse("修改代理失败");
        } else {
            agentModel.setId(uid);
            agentModel.setGmtCreate(agentService.getOneAgent(new Long(uid)).getGmtCreate());
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

    /**
     * 根据代理的主键获取其所有的子代理
     * @param id
     * @return
     */
    @GetMapping(value = "/sons/{id}")
    public Response AgentsSon(@PathVariable("id") String id) {
        logger.info("invoke agentsSon{}, url is agent/sons/{id}", id);
        int agentID = StringToLongUtil.stringToInt(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            List<AgentModel> agentModels = agentService.getSons(agentID);
            if (agentModels.size() > 0) {
                Response response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("sons", agentModels);
                data.put("size", agentModels.size());
                response.setData(data);
                return response;
            }
            return Responses.errorResponse("error");
        }
    }

    /**
     * 获取上级代理操作
     * @param id
     * @return
     */
    @GetMapping(value = "/father/{id}")
    public Response AgentFather(@PathVariable("id") String id) {
        logger.info("invoke agentFather {}, url is agent/father/{id}", id);
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            AgentModel agentModel = agentService.getFather(agentID);
            if (agentModel != null) {
                Response response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("father", agentModel);
                response.setData(data);
                return response;
            }
            return Responses.errorResponse("error");
        }
    }

    /**
     * 获取所有的上级操作
     * @param id
     * @return
     */
    @GetMapping(value = "ancestors/{id}")
    public Response getAncestors(@PathVariable("id") String id) {
        logger.info("invoke getAncestors {}", id);
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            List<AgentModel> agents = agentService.getAncestors(agentID);
            if (agents.size() > 0) {
                Response response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("ancestors", agents);
                response.setData(data);
                return response;
            }
            return Responses.errorResponse("error");
        }
    }

    /**
     * 获取上级对应的所有专家
     * @param id
     * @return
     */
    @GetMapping(value = "ancestors/professor/{id}")
    public Response getAncestorsProfessor(@PathVariable("id") String id) {
        logger.info("invoke getAncestors {}", id);
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            Map<String, Object> agents = agentService.getAncestorsProfessor(agentID);
            if (agents.size() > 0) {
                Response response = Responses.successResponse();
                Map<String, Object> data = new HashMap<>();
                data.put("ancestors", agents);
                response.setData(data);
                return response;
            }
            return Responses.errorResponse("error");
        }
    }
}
