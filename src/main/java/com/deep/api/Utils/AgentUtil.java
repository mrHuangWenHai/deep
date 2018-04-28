package com.deep.api.Utils;

import com.deep.domain.model.AgentModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.AgentService;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AgentUtil {
    @Resource
    private UserService userService;
    @Resource
    private AgentService agentService;
    @Resource
    private FactoryService factoryService;

    private static AgentUtil agentUtil;

    @PostConstruct
    public void init() {
        agentUtil = this;
    }

    /**
     * 判断某用户是否为代理, 如果是, 返回true, 如果不是, 返回false
     * @param id 用户主键
     * @return true / false
     */
    public static boolean isAgent(String id) {
        long agentID = StringToLongUtil.stringToLong(id);
        if (agentID == -1) {
            return false;
        }
        UserModel user = agentUtil.userService.getOneUser(agentID);
        return user != null && user.getIsFactory() == 1;
    }

    /**
     * 获取所有的下级代理
     * @param id 代理的主键
     * @return map
     */
    public static Map<String, Object> getSubordinate(String id) {
        int agentID = StringToLongUtil.stringToInt(id);
        if (agentID == -1) {
            return null;
        } else {
            return agentUtil.agentService.getAllSons(agentID);
        }
    }

    /**
     * 根据代理号ID查询直属羊场
     * @param id 代理号
     * @return 所有工厂的ID
     */
    public static long[] getFactory(String id) {
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return null;
        }
        long[] lists = agentUtil.factoryService.queryFactoryIDByAgentID(uid);
        if (lists == null) {
            return null;
        }
        return lists;
    }

    /**
     * 查找下级的代理和羊场信息
     * @param id 代理的ID
     * @return 所有信息
     */
    public static Map<String, Object> getSubordinateFactory(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        List<AgentModel> agentModels = agentUtil.agentService.getSons(uid);
        if (agentModels != null) {
            for (int i = 0; i < agentModels.size(); i++) {
                // -1 表示直属羊场信息
                map.put(String.valueOf(-1), agentUtil.factoryService.getAllFactoryOfOneAgent((long)uid));
                // 0 表示下层数据
                map.put(String.valueOf(0), agentUtil.agentService.getAllSons(agentModels.get(i).getId()));
                map.put(String.valueOf(i+1), agentModels.get(i));
            }
        }
        return map;
    }
}
