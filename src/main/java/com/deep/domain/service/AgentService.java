package com.deep.domain.service;

import com.deep.domain.model.AgentModel;
import com.deep.domain.model.UserModel;
import com.deep.infra.persistence.sql.mapper.AgentMapper;
import org.apache.ibatis.annotations.Param;
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

    public Long queryCount() {
        return agentMapper.queryCount();
    }

    /**
     * find MinusSign
     * @return agentModel's List
     */
    public List<AgentModel> queryAgentWithoutResponsiblePersonId(Long id) {
        UserModel userModel = userService.getOneUser(id);
        if (userModel == null) {
            return null;
        }

        return agentMapper.queryAgentWithoutResponsiblePersonId(userModel.getUserFactory());
    }

    public int[] getSonsId(int id) {
        return agentMapper.getSonsId(id);
    }

    /**
     * 获取所有的代理信息
     * @return
     */
    public List<AgentModel> getAll(Long start, Byte size) {
        return agentMapper.queryAllAgent(start, size);
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
     * 获取所有的下级代理, 包括子代理的子代理, 采用递归方式
     * @param id
     * @return
     */
    public Map<String, Object> getAllSons(int id) {
        Map<String, Object> map = new HashMap<>();
        List<AgentModel> agentModels = getSons(id);
        if (agentModels != null) {
            for (int i = 0; i < agentModels.size(); i++) {
                // 0 表示下层数据
                map.put(String.valueOf(0), getAllSons(agentModels.get(i).getId()));
                map.put(String.valueOf(i+1), agentModels.get(i));
            }
        }
        return map;
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
        return userService.getProfessor(lists);
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
     * @param id 代理的主键
     * @return 删除成功的标记
     */
    public Long deleteAgent(Long id) {
        AgentModel agentModel = agentMapper.queryAgentByID(id);
        // 获取其父亲信息
        Integer father = agentModel.getAgentFather();
        // 获取此代理的所有下级代理
        List<AgentModel> lists = agentMapper.getSons(Integer.valueOf(String.valueOf(id)));
        for (AgentModel list : lists) {
            agentMapper.updateAgentFather(Long.valueOf(father), (long) list.getId());
        }
        // 删除此代理下面的所有用户
        userService.deleteUserByFactoryNumber(id, (byte)1);
        // 返回相关信息
        return agentMapper.deleteAgent(id);
    }

    /**
     * 修改一个代理
     */
    public Long updateAgent(AgentModel agentModel) {
        return agentMapper.updateAgent(agentModel);
    }
}
