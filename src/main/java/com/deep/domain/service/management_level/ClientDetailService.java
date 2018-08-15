package com.deep.domain.service.management_level;

import com.deep.api.response.ClientDetailResponse;
import com.deep.domain.model.FactoryModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientDetailService {
    @Resource
    private AgentService agentService;
    @Resource
    private FactoryService factoryService;

    /**
     * 平台重新启动时候加载信息
     */
    public void clientDetailLoading() {
        // 首先获取所有的羊场信息，从上到下开始录入数据
        List<FactoryModel> lists = factoryService.getAll();
        if (lists == null) return;
        // 首先将羊场的信息加入
        int i = 0;
        while (i < lists.size()) {

            i++;
        }
    }

    /**
     * 通过一个方法获取所有的信息，数据全部从Redis中获取
     * @param factory 羊场ID号码
     * @param flag 羊场为0， 代理为1，其他为2
     * @return 结果列表
     */
    public List<ClientDetailResponse> getAllDetails(Long factory, Byte flag) {

    }
}
