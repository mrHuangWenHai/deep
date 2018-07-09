package com.deep.api.Utils;

import com.deep.domain.model.AgentModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.AgentService;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
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

    private Map<Long, List<Long> > allFactory;

    private List<Long> allFactoryList;

    private Long factoryCount;

    private Map<Long, List<Integer> > allAgent;

    private List<Integer>  allAgentList;

    private Long agentCount;

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
        // -1 表示直属羊场信息
        map.put(String.valueOf(-1), agentUtil.factoryService.getAllFactoryOfOneAgent((long)uid));
        map.put(String.valueOf(0), agentUtil.agentService.getOneAgent((long)uid));
        if (agentModels != null) {
            for (int i = 0; i < agentModels.size(); i++) {
                System.out.println(""+agentModels.get(i).getId());
                map.put(String.valueOf(i+1), getSubordinateFactory(""+agentModels.get(i).getId()));
            }
        }
        return map;
    }

    /**
     * 查找下级的羊场和代理的ID号
     * @param id agentId
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSubordinateFactoryID(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        List<AgentModel> agentModels = agentUtil.agentService.getSons(uid);
        // -1 表示直属羊场信息
        map.put(String.valueOf(-1), agentUtil.factoryService.queryFactoryIDByAgentID((long)uid));
        map.put(String.valueOf(0), agentUtil.agentService.getOneAgent((long)uid).getId());
        if (agentModels != null) {
            for (int i = 0; i < agentModels.size(); i++) {
                System.out.println(""+agentModels.get(i).getId());
                map.put(String.valueOf(i+1), getSubordinateFactoryID(""+agentModels.get(i).getId()));
            }
        }
        return map;
    }

    /**
     * 查找工厂所有的ID, 存放到一个数组中
     * @param id agentId
     */
    private static void getSubordinateAllFactory(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return;
        }
        if (agentUtil.factoryCount != 0) {
            long[] factoryIDByAgentIDs = agentUtil.factoryService.queryFactoryIDByAgentID((long)uid);
            for (long factoryIDByAgentID : factoryIDByAgentIDs) {
                agentUtil.allFactoryList.add(factoryIDByAgentID);
            }
        } else {
            agentUtil.factoryCount++;
        }
        List<AgentModel> agentModels = agentUtil.agentService.getSons(uid);
        if (agentModels != null) {
            for (AgentModel agentModel : agentModels) {
                getSubordinateAllFactory("" + agentModel.getId());
            }
        }
    }

    /**
     * 调用递归方法
     * @param id agentId
     * @return Map<Long, List<Long> >
     */
    public static Map<Long, List<Long> > getAllSubordinateFactory(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return null;
        }
        agentUtil.factoryCount = (long) 0;
        agentUtil.allFactory = new HashMap<>();
        agentUtil.allFactoryList = new ArrayList<>();
        long[] factoryIDByAgentIDs = agentUtil.factoryService.queryFactoryIDByAgentID((long)uid);
        List<Long> tempLong = new ArrayList<>();
        for (long factoryIDByAgentID : factoryIDByAgentIDs) {
            tempLong.add(factoryIDByAgentID);
        }
        agentUtil.allFactory.put((long) -1, tempLong);
        getSubordinateAllFactory(id);
        agentUtil.allFactory.put((long) 0, agentUtil.allFactoryList);
        return agentUtil.allFactory;
    }

    /**
     * find all agentId of an agent
     * @param id agent ID
     */
    private static void getSubordinateAllAgent(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return;
        }

        if (agentUtil.agentCount != 0) {
            int[] agentIDS = agentUtil.agentService.getSonsId(uid);
            for (int agentID : agentIDS) {
                agentUtil.allAgentList.add(agentID);
            }
        } else {
            agentUtil.agentCount++;
        }
        List<AgentModel> agentModels = agentUtil.agentService.getSons(uid);
        if (agentModels != null) {
            for (AgentModel agentModel : agentModels) {
                getSubordinateAllAgent("" + agentModel.getId());
            }
        }
    }

    /**
     * 调用递归方法
     * @param id agentId
     * @return Map<Long, List<Integer> >
     */
    public static Map<Long, List<Integer> > getAllSubordinateAgent(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return null;
        }
        agentUtil.agentCount = (long) 0;
        agentUtil.allAgent = new HashMap<>();
        agentUtil.allAgentList = new ArrayList<>();
        int[] agentIDs = agentUtil.agentService.getSonsId(uid);
        List<Integer> tempLong = new ArrayList<>();
        for (int agentID : agentIDs) {
            tempLong.add(agentID);
        }
        agentUtil.allAgent.put((long) -1, tempLong);
        getSubordinateAllAgent(id);
        agentUtil.allAgent.put((long) 0, agentUtil.allAgentList);
        return agentUtil.allAgent;
    }
}
