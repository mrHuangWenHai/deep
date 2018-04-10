package com.deep.domain.service;

import com.deep.domain.model.AgentModel;
import com.deep.domain.model.UserModel;
import com.deep.infra.persistence.sql.mapper.AgentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgentService {
    @Resource
    private AgentMapper agentMapper;

    @Resource
    private UserService userService;

    /**
     * 获取所有的代理信息
     * @return
     */
    public List<AgentModel> getAll() {
        return agentMapper.queryAllAgent();
    }

    /**
     * 获取所有的子代理
     * @param id
     * @return
     */
    public List<AgentModel> getSons(int id) {
        return agentMapper.getSons(id);
    }

    /**
     * 查询一个代理的直属上级
     * @param id
     * @return
     */
    public AgentModel getFather(Long id) {
        AgentModel agentModel = agentMapper.queryAgentByID(id);
        if (agentModel == null) {
            return null;
        } else {
            return agentMapper.queryAgentByID((long)agentModel.getAgentFather());
        }
    }
    /**
     * 查询一个代理的所有上级
     * TODO 最好将其优化成数据库的查询, 提高查询的效率, 目前没有找到数据库树型查询的方法
     */
    public List<AgentModel> getAncestors(Long id) {
        List<AgentModel> lists = new ArrayList<>();
        AgentModel agentModel = agentMapper.queryAgentByID(id);
        while (agentModel != null && agentModel.getAgentFather() != 0) {
            agentModel = agentMapper.queryAgentByID((long)agentModel.getAgentFather());
            lists.add(agentModel);
        }
        return lists;
    }

    public Map<String, Object> getAncestorsProfessor(Long id) {
        List<AgentModel> lists = getAncestors(id);
        Map<String, Object> userRole = userService.getProfessor(lists);
        return userRole;
    }

    /**
     * 根据代理的主键查询单个代理信息
     * @param id
     * @return
     */
    public AgentModel getOneAgent(Long id) {
        return agentMapper.queryAgentByID(id);
    }

    /**
     * 插入一个新的代理
     * @param agentModel
     * @return
     */
    public Long addAgent(AgentModel agentModel) {
        return agentMapper.insertAgent(agentModel);
    }

    /**
     * 删除一个代理
     * @param id
     * @return
     */
    public Long deleteAgent(Long id) {
        return agentMapper.deleteAgent(id);
    }

    /**
     * 修改一个代理
     */
    public Long updateAgent(AgentModel agentModel) {
        return agentMapper.updateAgent(agentModel);
    }
}
