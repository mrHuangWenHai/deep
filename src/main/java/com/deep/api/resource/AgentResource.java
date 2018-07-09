package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;

import com.deep.api.request.AgentRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.AgentModel;
import com.deep.domain.service.AgentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "agent")
public class AgentResource {

    private final Logger logger = LoggerFactory.getLogger(AgentResource.class);

    @Resource
    private AgentService agentService;

    private static Map<Byte, String> agentRankMap = new HashMap<>();

    private static Map<String, Byte> agentMapRank = new HashMap<>();

    static {
        agentRankMap.put((byte)1, "省级代理");
        agentRankMap.put((byte)2, "市级代理");
        agentRankMap.put((byte)3, "县级代理");

        agentMapRank.put("省级代理", (byte)1);
        agentMapRank.put("市级代理", (byte)2);
        agentMapRank.put("县级代理", (byte)3);
    }

    /**
     * 查找所有代理
     * @return response
     */
    @Permit(authorities = "query_agent")
    @GetMapping(value = "/{id}")
    public Response agentLists(
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "10") String size,
            @PathVariable("id") String id, HttpServletRequest request
    ) {
        logger.info("invoke agentLists, url is agent {}", id, page, size);
        Integer uid = StringToLongUtil.stringToInt(id);
        Long upage = StringToLongUtil.stringToLong(page);
        Byte usize = StringToLongUtil.stringToByte(size);
        String userId = TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION));
        if (userId == null) {
            return Responses.errorResponse("请求错误!");
        }
        Byte which = StringToLongUtil.stringToByte(userId);
        if (uid < 0 || upage < 0 || usize < 0) {
            return Responses.errorResponse("错误");
        }
        if (which == 0 || which == 2) {
            return Responses.errorResponse("error, have no permit");
        } else if (which == 1) {
            Map<Long, List<Integer>> agents = AgentUtil.getAllSubordinateAgent(id);
            if (agents == null) {
                return Responses.errorResponse("error request!");
            }
            List<AgentModel> models = new ArrayList<>();
            // direct people
            List<Integer> directAgents = agents.get((long) -1);
            List<AgentModel> theOther = new ArrayList<>();
            if (directAgents != null) {
                for (Integer directFactory : directAgents) {
                    System.out.println("directFactory = " + directFactory);
                    theOther.add(agentService.getOneAgent(Long.parseLong(directFactory.toString())));
                }
            }
            // un direct people
            List<Integer> undirectAgents = agents.get((long) 0);
            List<AgentModel> other = new ArrayList<>();
            if (undirectAgents != null) {
                for (Integer undirectFactory : undirectAgents) {
                    other.add(agentService.getOneAgent(Long.parseLong(undirectFactory.toString())));
                }
            }
            models.addAll(theOther);
            models.addAll(other);

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List", models);
            data.put("size", theOther.size());
            response.setData(data);
            return response;
        }
        return Responses.errorResponse("error, have no permit");
    }

    /**
     * 查找一个代理
     * @param id agentId
     * @return response
     */
    @Permit(authorities = "query_agent")
    @GetMapping(value = "/find/{id}")
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
        AgentRequest agentRequest = new AgentRequest();
        agentRequest.setAgentRank(agentRankMap.get(agentModel.getAgentRank()));
        agentRequest.setAgentArea(agentModel.getAgentArea());
        agentRequest.setFactoryNum(agentModel.getAgentFather());
        agentRequest.setAgentName(agentModel.getAgentName());
        agentRequest.setResponsibleId(agentModel.getResponsibleId());
        agentRequest.setResponsibleName(agentModel.getResponsibleName());

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", agentRequest);
        response.setData(data);
        return response;
    }

    /**
     * select un responsible agent
     * @return response
     */
    @Permit(authorities = "query_agent")
    @GetMapping(value = "/fr")
    public Response queryAgentWithoutResponsiblePersonId(HttpServletRequest request) {
        logger.info("agent/fr {}");
        Long userId = TokenAnalysis.getUserId(request.getHeader(Constants.AUTHORIZATION));
        if (userId == null) {
            return Responses.errorResponse("Error!");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        List<AgentModel> agentModels = agentService.queryAgentWithoutResponsiblePersonId(userId);
        data.put("List", agentModels);
        data.put("size", agentModels.size());
        response.setData(data);
        return response;
    }

    /**
     * 删除一个代理
     * @param id agentId
     */
    @Permit(authorities = "deleting_an_agent")
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
     * @param bindingResult binding
     * @return  response
     */
    @Permit(authorities = "add_agent")
    @PostMapping(value = "")
    public Response addOne(@Valid @RequestBody AgentRequest agentRequest, BindingResult bindingResult) {
        logger.info("invoke addOne{}, url is agent/add", agentRequest);
        if (bindingResult.hasErrors())  {
            Response response = Responses.errorResponse("添加代理失败, 验证错误!");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            AgentModel agentModel = new AgentModel();
            agentModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            agentModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            Byte agentRank = agentMapRank.get(agentRequest.getAgentRank());
            if (agentRank == null) {
                return Responses.errorResponse("代理等级不合法");
            }

            agentModel.setAgentRank(agentMapRank.get(agentRequest.getAgentRank()));
            agentModel.setAgentArea(agentRequest.getAgentArea());
            agentModel.setAgentFather(agentRequest.getFactoryNum());
            agentModel.setAgentName(agentRequest.getAgentName());
            agentModel.setResponsibleId(agentRequest.getResponsibleId());
            agentModel.setResponsibleName(agentRequest.getResponsibleName());

            Long addID = agentService.addAgent(agentModel);
            if (addID <= 0) {
                return Responses.errorResponse("添加用户信息失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", addID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 修改一个代理
     * @param bindingResult 错误信息提示
     * @param id 代理主键
     * @return response
     */
    @Permit(authorities = "modify_the_proxy")
    @PutMapping("/{id}")
    public Response agentUpdate(@Valid @RequestBody AgentRequest agentRequest, @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke agentUpdate{}, url is agent/{id}", agentRequest, id);
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        if (bindingResult.hasErrors())  {
            return Responses.errorResponse("修改代理失败");
        } else {

            AgentModel agentModel = new AgentModel();
            agentModel.setId(uid);
            agentModel.setGmtCreate(agentService.getOneAgent((long)uid).getGmtCreate());
            agentModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            agentModel.setAgentRank(agentMapRank.get(agentRequest.getAgentRank()));
            agentModel.setAgentArea(agentRequest.getAgentArea());
            agentModel.setAgentFather(agentRequest.getFactoryNum());
            agentModel.setAgentName(agentRequest.getAgentName());
            agentModel.setResponsibleId(agentRequest.getResponsibleId());
            agentModel.setResponsibleName(agentRequest.getResponsibleName());

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
     * 根据代理的主键获取其所有的直属子代理
     * @param id agentId
     * @return response
     */
    @Permit(authorities = "query_agent")
    @GetMapping(value = "/sons/{id}")
    public Response agentsSon(@PathVariable("id") String id) {
        logger.info("invoke agentsSon{}, url is agent/sons/{id}", id);
        int agentID = StringToLongUtil.stringToInt(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            List<AgentModel> agentModels = agentService.getSons(agentID);
            if (agentModels != null) {
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
     * 根据代理的主键查询所有的子代理
     * @param id agentId
     * @return response
     */
    @Permit(authorities = "query_agent")
    @GetMapping(value = "/allson/{id}")
    public Response agentsAllSon(@PathVariable("id") String id) {
        logger.info("invoke agentsSon{}, url is agent/sons/{id}", id);
        int agentID = StringToLongUtil.stringToInt(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            Map<String, Object> map = agentService.getAllSons(agentID);
            Response response = Responses.successResponse();
            Map<String, Object> data = new HashMap<>();
            data.put("sons", map);
            response.setData(data);
            return response;
        }
    }

    /**
     * 获取上级代理操作
     * @param id agentId
     * @return response
     */
    @Permit(authorities = "query_agent")
    @GetMapping(value = "/father/{id}")
    public Response agentFather(@PathVariable("id") String id) {
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
     * @param id agentId
     * @return response
     */
    @Permit(authorities = "query_agent")
    @GetMapping(value = "ancestors/{id}")
    public Response getAncestors(@PathVariable("id") String id) {
        logger.info("invoke getAncestors {}", id);
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            List<AgentModel> agents = agentService.getAncestors(agentID);
            if (agents != null) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("ancestors", agents);
                return Responses.successResponse(data);
            }
            return Responses.errorResponse("error");
        }
    }

    /**
     * 获取上级对应的所有专家
     * @param id agentId
     * @return response
     */
    @Permit(authorities = "query_expert")
    @GetMapping(value = "ancestors/professor/{id}")
    public Response getAncestorsProfessor(@PathVariable("id") String id) {
        logger.info("invoke getAncestors {}", id);
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return Responses.errorResponse("error");
        } else {
            Map<String, Object> agents = agentService.getAncestorsProfessor(agentID);
            if (agents != null) {
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
