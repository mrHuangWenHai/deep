package com.deep.domain.service.management_level;

import com.alibaba.fastjson.JSON;
import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.ClientDetailUtil;
import com.deep.api.Utils.JedisUtil;
import com.deep.api.response.ClientDetailResponse;
import com.deep.domain.model.AgentModel;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.service.BuildingColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ClientDetailService {
    @Resource
    private AgentService agentService;
    @Resource
    private FactoryService factoryService;
    @Resource
    private BuildingColumnService buildingColumnService;

    /**
     * 代理的客户关系一览表设置
     * @param agent 代理编号
     * @param clientDetailResponse 相关数据
     * @param flag 级别
     */
    private void setClientOfAgent(Short agent, ClientDetailResponse clientDetailResponse, Byte flag) {
        // 首先查看Redis中是否存在该代理的信息
        if (agent == 0) return;
        String value = ClientDetailUtil.getClientToRedis((long) agent, (byte) 1);
        AgentModel agentModel = agentService.getOneAgent((long)agent);

        if (value == null) {
            // 需要进行存入操作
            clientDetailResponse.setProvincialPlatform(agentModel.getAgentName());
            clientDetailResponse.setFactory((long)agent);
            clientDetailResponse.setRank(agentModel.getAgentRank());

            // 判断代理的级别
            switch (agentModel.getAgentRank()) {
                case 3: {
                    // 县级代理
                    clientDetailResponse.setSheepTotal(1);
                    break;
                }
                case 2: {
                    // 市级代理
                    if (flag == 4) {
                        // 羊场
                        clientDetailResponse.setSheepTotal(1);
                    }
                    if (flag == 3) {
                        // 县级代理
                        clientDetailResponse.setCountryTotal(1);
                    }
                    break;
                }
                case 1: {
                    // 省级代理
                    if (flag == 4) {
                        // 羊场
                        clientDetailResponse.setSheepTotal(1);
                    }
                    if (flag == 3) {
                        // 县级代理
                        clientDetailResponse.setCountryTotal(1);
                    }
                    if (flag == 2) {
                        // 市级代理
                        clientDetailResponse.setMunicipaTotal(1);
                    }
                    break;
                }
                case 0: {
                    // 总公司
                    if (flag == 4) {
                        // 羊场
                        clientDetailResponse.setSheepTotal(1);
                    }
                    if (flag == 3) {
                        // 县级代理
                        clientDetailResponse.setCountryTotal(1);
                    }
                    if (flag == 2) {
                        // 市级代理
                        clientDetailResponse.setMunicipaTotal(1);
                    }
                    if (flag == 1) {
                        // 省级代理
                        clientDetailResponse.setProvincialTotal(1);
                    }
                    break;
                }
            }
            // 存入Redis
            ClientDetailUtil.setClientToRedis((long) agent, (byte) 1, clientDetailResponse);
            if (agentModel.getAgentRank() != 0) {
                // 继续其上级
                setClientOfAgent((short)agentModel.getAgentFather(), clientDetailResponse, agentModel.getAgentRank());
            }
        } else {
            // 已经存入Redis，进行重置
            ClientDetailResponse waiting = JSON.parseObject(value, ClientDetailResponse.class);
            System.out.println("sheep total = " + waiting.getSheepTotal());
            // 判断代理的级别
            switch (agentModel.getAgentRank()) {
                case 3: {
                    // 县级代理
                    waiting.setSheepTotal(waiting.getSheepTotal() + 1);
                    break;
                }
                case 2: {
                    // 市级代理
                    if (flag == 4) {
                        // 羊场
                        waiting.setSheepTotal(waiting.getSheepTotal() + 1);
                    }
                    if (flag == 3) {
                        // 县级代理
                        waiting.setCountryTotal(waiting.getCountryTotal() + 1);
                    }
                    break;
                }
                case 1: {
                    // 省级代理
                    if (flag == 4) {
                        // 羊场
                        waiting.setSheepTotal(waiting.getSheepTotal() + 1);
                    }
                    if (flag == 3) {
                        // 县级代理
                        waiting.setCountryTotal(waiting.getCountryTotal() + 1);
                    }
                    if (flag == 2) {
                        // 市级代理
                        waiting.setMunicipaTotal(waiting.getMunicipaTotal() + 1);
                    }
                    break;
                }
                case 0: {
                    // 总公司
                    if (flag == 4) {
                        // 羊场
                        waiting.setSheepTotal(waiting.getSheepTotal() + 1);
                    }
                    if (flag == 3) {
                        // 县级代理
                        waiting.setCountryTotal(waiting.getCountryTotal() + 1);
                    }
                    if (flag == 2) {
                        // 市级代理
                        waiting.setMunicipaTotal(waiting.getMunicipaTotal() + 1);
                    }
                    if (flag == 1) {
                        // 省级代理
                        waiting.setProvincialTotal(waiting.getProvincialTotal() + 1);
                    }
                    break;
                }
            }
            // 存入Redis
            ClientDetailUtil.resetClientToRedis((long) agent, (byte) 1, waiting);
            if (agentModel.getAgentRank() != 0) {
                // 继续其上级
                setClientOfAgent((short)agentModel.getAgentFather(), waiting, agentModel.getAgentRank());
            }
        }
    }

    /**
     * 平台重新启动时候加载信息
     */
    public void clientDetailLoading() {
        // 首先获取所有的羊场信息，从上到下开始录入数据
        List<FactoryModel> lists = factoryService.getAll();
        if (lists != null) {
            // 首先将羊场的信息加入
            for (FactoryModel list : lists) {
                // 得到每种羊的数目，总共有6种羊
                Integer ramTotal = buildingColumnService.findTypeOfSheep(list.getId(), "种公羊");
                Integer eweTotal = buildingColumnService.findTypeOfSheep(list.getId(), "种母羊");
                Integer commercialTotal = buildingColumnService.findTypeOfSheep(list.getId(), "商品羊");
                Integer lambTotal = buildingColumnService.findTypeOfSheep(list.getId(), "羔羊");
                Integer reserveRamTotal = buildingColumnService.findTypeOfSheep(list.getId(), "后备种公羊");
                Integer reserveEweTotal = buildingColumnService.findTypeOfSheep(list.getId(), "后备种母羊");

                ClientDetailResponse clientDetailResponse = new ClientDetailResponse(
                        list.getBreedName(),
                        list.getId(),
                        (byte) 4,
                        0, 0, 0, 0,
                        ramTotal + eweTotal + commercialTotal + lambTotal + reserveEweTotal + reserveRamTotal,
                        ramTotal, eweTotal, commercialTotal, lambTotal, reserveRamTotal, reserveEweTotal
                );

                ClientDetailUtil.setClientToRedis(list.getId(), (byte) 0, clientDetailResponse);
                // 根据羊场的上级代理去设置上级代理的相关信息
                setClientOfAgent(list.getAgent(), clientDetailResponse, (byte)4);
            }
        }

        // 其次将代理的信息加入
        List<AgentModel> agents = agentService.getAgents();
        if (agents != null) {
            for (AgentModel agent : agents) {
                ClientDetailResponse clientDetailResponse = new ClientDetailResponse(
                        agent.getAgentName(),
                        (long)agent.getId(),
                        agent.getAgentRank(),
                        0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0
                );

                ClientDetailUtil.setClientToRedis((long)agent.getId(), (byte) 1, clientDetailResponse);
                // 根据羊场的上级代理去设置上级代理的相关信息
//                setClientOfAgent((short)agent.getAgentFather(), clientDetailResponse, agent.getAgentRank());
            }
        }
        // 加载标志
        JedisUtil.setValue("clientDetail", "success");
    }

    private ClientDetailResponse getAllDetailsOfAgentsAndFactories(Long factory, Byte flag) {
        Integer ramTotal = 0;
        Integer eweTotal = 0;
        Integer commercialTotal = 0;
        Integer lambTotal = 0;
        Integer reserveRamTotal = 0;
        Integer reserveEweTotal = 0;
        int provincialTotal = 0;
        int municipaTotal = 0;
        int countryTotal = 0;
        int sheepTotal = 0;
        if (flag == 0) {
            // 这是羊场
            ramTotal = buildingColumnService.findTypeOfSheep(factory, "种公羊");
            eweTotal = buildingColumnService.findTypeOfSheep(factory, "种母羊");
            commercialTotal = buildingColumnService.findTypeOfSheep(factory, "商品羊");
            lambTotal = buildingColumnService.findTypeOfSheep(factory, "羔羊");
            reserveRamTotal = buildingColumnService.findTypeOfSheep(factory, "后备种公羊");
            reserveEweTotal = buildingColumnService.findTypeOfSheep(factory, "后备种母羊");
            FactoryModel model = factoryService.getOneFactory(factory);
            if (model == null) return null;
            return new ClientDetailResponse(
                    factoryService.getOneFactory(factory).getBreedName(),
                    factory,
                    (byte) 4,
                    0, 0, 0, 0,
                    ramTotal + eweTotal + commercialTotal + lambTotal + reserveEweTotal + reserveRamTotal,
                    ramTotal, eweTotal, commercialTotal, lambTotal, reserveRamTotal, reserveEweTotal
            );
        }
        if (flag == 1) {
            // 这是代理
            // 查找所有的子代理信息
            List<AgentModel> agents = agentService.getSons(Integer.valueOf(String.valueOf(factory)));
            // 递归查找所有的值
            for (AgentModel agent : agents) {
                switch (agent.getAgentRank()) {
                    case 1:
                        provincialTotal += 1;
                        break;
                    case 2:
                        municipaTotal += 1;
                        break;
                    case 3:
                        countryTotal += 1;
                        break;
                }

                ClientDetailResponse client = getAllDetailsOfAgentsAndFactories((long) agent.getId(), (byte) 1);
                if (client == null) continue;
                ramTotal += client.getRamTotal();
                eweTotal += client.getEweTotal();
                commercialTotal += client.getCommercialTotal();
                lambTotal += client.getLambTotal();
                reserveEweTotal += client.getEweTotal();
                reserveRamTotal += client.getRamTotal();
            }
            // 查找所有的子羊场信息
            List<FactoryModel> factories = factoryService.getAllFactoryOfOneAgent(factory);
            for (FactoryModel f : factories) {
                ramTotal += buildingColumnService.findTypeOfSheep(f.getId(), "种公羊");
                eweTotal += buildingColumnService.findTypeOfSheep(f.getId(), "种母羊");
                commercialTotal += buildingColumnService.findTypeOfSheep(f.getId(), "商品羊");
                lambTotal += buildingColumnService.findTypeOfSheep(f.getId(), "羔羊");
                reserveRamTotal += buildingColumnService.findTypeOfSheep(f.getId(), "后备种公羊");
                reserveEweTotal += buildingColumnService.findTypeOfSheep(f.getId(), "后备种母羊");
            }
            sheepTotal += factories.size();

            return new ClientDetailResponse(
                    agentService.getOneAgent(factory).getAgentName(),
                    factory,
                    (byte) 1,
                    provincialTotal, municipaTotal, countryTotal, sheepTotal,
                    ramTotal + eweTotal + commercialTotal + lambTotal + reserveEweTotal + reserveRamTotal,
                    ramTotal, eweTotal, commercialTotal, lambTotal, reserveRamTotal, reserveEweTotal
            );
        }
        return new ClientDetailResponse();
    }

    /**
     * 通过一个方法获取所有的信息，数据全部从Redis中获取
     * @param factory 羊场ID号码
     * @param flag 羊场为0， 代理为1，其他为2
     * @return 结果列表
     */
    public Map<String, List<ClientDetailResponse> > getAllDetails(Long factory, Byte flag) {
        if (flag == 2) return null;

        Map<String, List<ClientDetailResponse> > result = new HashMap<>();
        List<ClientDetailResponse> value = new ArrayList<>();
        ClientDetailResponse clientItSelf = getAllDetailsOfAgentsAndFactories(factory, flag);
        if (clientItSelf != null) value.add(clientItSelf);
        result.put("itself", value);
        if (flag == 0) {
            return result;
        }

        // 直属代理
        List<Long> directAgent = agentService.getDirectSonsOfId(factory);
        // 直属羊场
        long[] directFactory = AgentUtil.getFactory(factory.toString());

        // 从Redis中获取所有信息
        if (directAgent != null) {
            value = new ArrayList<>();
            for(Long list: directAgent) {
                ClientDetailResponse clientAgent = getAllDetailsOfAgentsAndFactories(list, (byte) 1);
                if (clientAgent != null) value.add(clientAgent);
            }
            result.put("agents", value);
        }
        if (directFactory != null) {
            value = new ArrayList<>();
            for (long aDirectFactory : directFactory) {
                ClientDetailResponse clientFactory = getAllDetailsOfAgentsAndFactories(aDirectFactory, (byte) 0);
                if (clientFactory != null) value.add(clientFactory);
            }
            result.put("factories", value);
        }
        return result;
    }
}
