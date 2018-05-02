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

    private Long count;

    private Map<Long, List<Long> > allAgent;

    private List<Long>  allAgentList;

//    private List<Long> allFactory;

//    private List<Long> allAgent;

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
     * @param id
     * @return
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
     * @param id
     */
    public static void getSubordinateAllFactory(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return;
        }
        System.out.println("count = " + agentUtil.count);
        if (agentUtil.count != 0) {
            long[] factoryIDByAgentIDs = agentUtil.factoryService.queryFactoryIDByAgentID((long)uid);
            for (long factoryIDByAgentID : factoryIDByAgentIDs) {
                agentUtil.allFactoryList.add(factoryIDByAgentID);
            }
            System.out.println(agentUtil.count);
        } else {
            agentUtil.count++;
        }
        List<AgentModel> agentModels = agentUtil.agentService.getSons(uid);
        if (agentModels != null) {
            for (AgentModel agentModel : agentModels) {
                System.out.println("agentModle.getId() = " + agentModel.getId());
                getSubordinateAllFactory("" + agentModel.getId());
            }
        }
    }

    /**
     * 调用递归方法
     * @param id
     * @return
     */
    public static Map<Long, List<Long> > getAllSubordinateFactory(String id) {
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return null;
        }
        agentUtil.count = (long) 0;
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

//    public static void getSubordinateAllAgent(String id) {
//        int uid = StringToLongUtil.stringToInt(id);
//        if (uid == -1) {
//            return;
//        }
//        List<AgentModel> agentModels = agentUtil.agentService.getSons(uid);
//        if (agentModels != null) {
//            for (AgentModel agentModel : agentModels) {
//                agentUtil.allAgent.add((long)agentModel.getId());
//                getSubordinateAllAgent("" + agentModel.getId());
//            }
//        }
//    }
//
//    public static List<Long> getAllSubordinateAgent(String id) {
//        agentUtil.allAgent = new ArrayList<>();
//        getSubordinateAllAgent(id);
//        return agentUtil.allAgent;
//    }
}
