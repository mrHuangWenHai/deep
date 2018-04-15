package com.deep.domain.service;

import com.deep.domain.model.FactoryModel;
import com.deep.infra.persistence.sql.mapper.FactoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FactoryService {
    @Resource
    private FactoryMapper factoryMapper;

    /**
     * 获取所有的羊场信息
     * @return
     */
    public List<FactoryModel> getAll() {
        return factoryMapper.queryAllFactory();
    }

    /**
     * 根据羊场的ID单个查询羊场的信息
     * @param id
     * @return
     */
    public FactoryModel getOneFactory(Long id) {
        return factoryMapper.queryFactoryByID(id);
    }

    /**
     * 根据羊场编号查询羊场的代理
     * @param factoryNumber
     * @return
     */
    public short getAgentIDByFactoryNumber(String factoryNumber) {
        return factoryMapper.queryOneAgentByFactoryID(factoryNumber);
    }

    /**
     * 删除一个羊场的信息
     * @param factoryModel
     * @return
     */
    public Long addFactory(FactoryModel factoryModel) {
        return factoryMapper.insertFactory(factoryModel);
    }

    /**
     * 修改单个羊场的信息
     * @param factoryModel
     * @return
     */
    public Long updateFactory(FactoryModel factoryModel) {
        return factoryMapper.updateFactory(factoryModel);
    }

    /**
     * 删除羊场的信息
     * @param id
     * @return
     */
    public Long deleteFatory(Long id) {
        return factoryMapper.deleteFactory(id);
    }
}
