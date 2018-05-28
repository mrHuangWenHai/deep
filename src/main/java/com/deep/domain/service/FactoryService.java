package com.deep.domain.service;


import com.deep.api.response.FactoryResponse;
import com.deep.domain.model.FactoryModel;
import com.deep.domain.model.UserModel;
import com.deep.infra.persistence.sql.mapper.FactoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;


@Service
public class FactoryService {
    @Resource
    private FactoryMapper factoryMapper;

    @Resource
    private UserService userService;

    /**
     * 获取所有的羊场信息
     * @return
     */
    public List<FactoryModel> getAll(Long start, Byte size) {
        System.out.println(start);
        System.out.println(size);
        return factoryMapper.queryAllFactory(start, size);
    }

    /**
     * 获取所有的没有负责人的羊场信息, 主要是羊场的编号和羊场的名称
     * @return
     */
    public List<FactoryModel> findAllNoResponsibleFactory(Long userId) {
        UserModel userModel = userService.getOneUser(userId);
        if (userModel == null) {
            return null;
        }
        return factoryMapper.getAllNoResponsibleFactory(userModel.getUserFactory());
    }

    /**
     * 根据羊场的ID单个查询羊场的信息
     * @param id
     * @return
     */
    public FactoryModel getOneFactory(Long id) {
        return factoryMapper.queryFactoryByID(id);
    }

    public FactoryResponse getOneFactoryAgent(Long id) {
        return factoryMapper.queryFactoryAgentByID(id);
    }

    /**
     * 根据代理号查询所有的羊场(包括分页)
     * @param id 代理号
     * @return
     */
    public List<FactoryModel> getAllFactoryOfOneAgent(Long id) {
        return factoryMapper.queryFactoryByAgentID(id);
    }

    /**
     * 根据代理号查询所有的羊场(包括分页)
     * @param id 代理号
     * @return
     */
    public List<FactoryResponse> getAllFactoryOfOneAgentPage(Long id, Long start, Byte page) {
        return factoryMapper.queryFactoryByAgentIDPage(id, start, page);
    }

    /**
     * 根据羊场编号查询羊场的代理

     * @param factoryNumber 羊场编号
     * @return
     */
    public Short getAgentIDByFactoryNumber(String factoryNumber) {
        return factoryMapper.queryOneAgentByFactoryID(factoryNumber);

    }

    /**
     * 根据代理ID查找所有羊场的ID

     * @param id 代理号

     * @return
     */
    public long[] queryFactoryIDByAgentID(Long id) {
        return factoryMapper.queryFactoryIDByAgentID(id);
    }

    /**
     * 根据羊场的地理位置查询
     * @param location
     * @return
     */
    public List<FactoryModel> getAgentByBreadLocation(String location) {
        return factoryMapper.queryFactoryByLocation(location);
    }

    /**
     * 删除一个羊场的信息
     * @param factoryModel
     * @return
     */

    public int addFactory(FactoryModel factoryModel) {

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
    public Long deleteFactory(Long id) {
        // 删除此羊场下面的所有用户
        userService.deleteUserByFactoryNumber(id, (byte)0);
        return factoryMapper.deleteFactory(id);
    }

    public Short queryOneAgentByID(Long id) {
        return factoryMapper.queryOneAgentByID(id);
    }

}

