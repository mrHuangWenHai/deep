package com.deep.domain.service;

import com.deep.domain.model.AgentModel;
import com.deep.infra.persistence.sql.mapper.AgentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AgentService {
    @Resource
    private AgentMapper agentMapper;

    /**
     * 获取所有的代理信息
     * @return
     */
    public List<AgentModel> getAll() {
        return agentMapper.queryAllAgent();
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
